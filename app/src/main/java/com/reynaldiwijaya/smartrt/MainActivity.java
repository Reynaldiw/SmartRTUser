package com.reynaldiwijaya.smartrt;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.reynaldiwijaya.smartrt.ui.Home.Presenter.UI.HomeFragment;
import com.reynaldiwijaya.smartrt.ui.Info.InfoActivity;
import com.reynaldiwijaya.smartrt.ui.Profile.ProfileFragment;
import com.reynaldiwijaya.smartrt.ui.Report.ReportFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().setColorMode(getResources().getColor(R.color.colorPrimary));

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        setTitle("");

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container_fragment, new HomeFragment());
        transaction.commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.home:
                    ;
                    // Membuat Objek Fragment Home
                    HomeFragment homeFragment = new HomeFragment();
                    loadFrgament(homeFragment);
                    return true;
                case R.id.laporan:
                    ReportFragment reportFragment = new ReportFragment();
                    loadFrgament(reportFragment);
                    return true;

                case R.id.profile:
                    ProfileFragment profileFragment = new ProfileFragment();
                    loadFrgament(profileFragment);
                    return true;
            }
            return false;
        }
    };

    private void loadFrgament(Fragment fragment) {
        // Membuat object transaction
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (fragment != null) {

            // Method transtaction untuk menggunakan method replace
            transaction.replace(R.id.container_fragment, fragment);
            transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_the_right);
            transaction.commit();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.info, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.info) {
            startActivity(new Intent(this, InfoActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }
}
