package com.transico.codezero.transico;

import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.transico.codezero.transico.CustomComponents.CustomViewPager;
import com.transico.codezero.transico.DriverScheduler.DriverScheduleFragment;
import com.transico.codezero.transico.ProfileManagement.ProfileManagerFragment;
import com.transico.codezero.transico.ReportLog.ReportLogFragment;
import com.transico.codezero.transico.SystemHelper.SectionsStatePageAdapter;
import com.transico.codezero.transico.TimeLoger.TimeLogFragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;



public class EmployeeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, DriverScheduleFragment.OnFragmentInteractionListener,
        TimeLogFragment.OnFragmentInteractionListener,
        ReportLogFragment.OnFragmentInteractionListener,
        ProfileManagerFragment.OnFragmentInteractionListener
{

    private SectionsStatePageAdapter mSectionsStatePageAdapter;
    private FrameLayout mframeLayout;
    private BottomNavigationView navigation;



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_schedule:
                    setFragmentView(new DriverScheduleFragment());
//                    setActivityTitle("December");
                    return true;
                case R.id.navigation_time_log:
                    setFragmentView(new TimeLogFragment());
                    setActivityTitle("Time Log");
                    return true;
                case R.id.navigation_report_log:
                    setFragmentView(new ReportLogFragment());
                    setActivityTitle("Report Log");
                    return true;
                case R.id.navigation_profile:
                    setFragmentView(new ProfileManagerFragment());
                    setActivityTitle("Profile");
                    return true;
            }

            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employee_main_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        setActivityTitle("Employee Activity");

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        setFragmentView(new DriverScheduleFragment());







    }

    private void setFragmentView(Fragment fragment){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container,fragment);
        ft.commit();
    }



    public void setActivityTitle(String title){
        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle(title);

//            this remove the arrow used with activity and fragment
//             getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
