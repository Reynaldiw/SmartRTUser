package com.reynaldiwijaya.smartrt.ui.Login;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.reynaldiwijaya.smartrt.Api.ApiClient;
import com.reynaldiwijaya.smartrt.Api.ApiInterface;
import com.reynaldiwijaya.smartrt.Helper.Constant;
import com.reynaldiwijaya.smartrt.Helper.MyFunction;
import com.reynaldiwijaya.smartrt.Helper.SessionManager;
import com.reynaldiwijaya.smartrt.MainActivity;
import com.reynaldiwijaya.smartrt.R;
import com.reynaldiwijaya.smartrt.model.login.ResponseLogin;
import com.reynaldiwijaya.smartrt.model.login.User;
import com.reynaldiwijaya.smartrt.ui.Register.RegisterActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends MyFunction {

    @BindView(R.id.edt_username)
    TextInputEditText edtUsername;
    @BindView(R.id.edt_password)
    EditText edtPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_register)
    TextView tvRegister;

    private String username, password;
    private ApiInterface apiInterface;
    private User user;
    private SessionManager sm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        sm = new SessionManager(LoginActivity.this);
        setRequestPermission();
    }

    private void setRequestPermission() {
        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }
        //And finally ask for the permission
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, Constant.STORAGE_REQUEST_PERMISSION);

    }


    @OnClick({R.id.btn_login, R.id.tv_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                getData();
                checkData();
                break;
            case R.id.tv_register:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
        }
    }

    private void checkData() {
        if (TextUtils.isEmpty(username)) {
            edtUsername.setError(getString(R.string.error_message));
            edtUsername.requestFocus();
        } else if (TextUtils.isEmpty(password)) {
            edtPassword.setError(getString(R.string.error_message));
            edtPassword.requestFocus();
        } else {
            sendData();
        }
    }

    private void sendData() {
        showProgressDialog("Try to Login");
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseLogin> call = apiInterface.loginUser(username, password);
        call.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {
                hideProgressDialog();

                ResponseLogin responseLogin = response.body();

                Log.d("login", "onResponse: " + response.body().toString());

                String result = null;
                if (responseLogin != null) {
                    result = responseLogin.getResult();
                }
                String msg = responseLogin.getMsg();

                if (result.equals("1")) {

                    user = responseLogin.getUser();
                    String id_user = user.getIdUser();
                    sm.storeLogin(user.getUsername());
                    sm.setIdUser(id_user);

                    Log.d("Login", user.getIdUser());

                    String urlImage = user.getFoto();
                    String nama = user.getNamaLengkap();
                    sm.setImageUser(urlImage);
                    sm.setNameUser(nama);
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    Toasty.success(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
                    finish();

                } else {
                    Toasty.info(LoginActivity.this, msg + " Maaf username atau password salah atau akun " +
                            "belum tervirifikasi. Silahkan hubungi ketua RT", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                hideProgressDialog();
                Toasty.error(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void getData() {
        username = edtUsername.getText().toString();
        password = edtPassword.getText().toString();
    }
}
