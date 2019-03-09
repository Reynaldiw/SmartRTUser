package com.reynaldiwijaya.smartrt;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.reynaldiwijaya.smartrt.ui.Home.Presenter.UI.HomeFragment;
import com.reynaldiwijaya.smartrt.ui.Profile.ProfileFragment;
import com.reynaldiwijaya.smartrt.ui.Report.ReportFragment;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                case R.id.home: ;
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
            transaction.commit();

        }
    }

}
