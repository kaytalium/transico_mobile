package com.transico.codezero.transico;

import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

public class EmployeeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, DriverScheduleFragment.OnFragmentInteractionListener,
        TimeLogFragment.OnFragmentInteractionListener,
        ReportLogFragment.OnFragmentInteractionListener,
        ProfileManagerFragment.OnFragmentInteractionListener
{

    private SectionsStatePageAdapter mSectionsStatePageAdapter;
    private ViewPager mViewPager;
    private BottomNavigationView navigation;



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_schedule:
                    setViewPager(0);
                    setActivityTitle("December");
                    return true;
                case R.id.navigation_time_log:
                    setViewPager(1);
                    setActivityTitle("Time Log");
                    return true;
                case R.id.navigation_report_log:
                    setViewPager(2);
                    setActivityTitle("Report Log");
                    return true;
                case R.id.navigation_profile:
                    setViewPager(3);
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
        setActivityTitle("December");

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        mSectionsStatePageAdapter = new SectionsStatePageAdapter(getSupportFragmentManager());

        mViewPager = findViewById(R.id.fragment_container);
        setupViewPager(mViewPager);

        mViewPager.isEnabled();

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        navigation.setSelectedItemId(R.id.navigation_schedule);
                        break;
                    case 1:
                        navigation.setSelectedItemId(R.id.navigation_time_log);
                        break;
                    case 2:
                        navigation.setSelectedItemId(R.id.navigation_report_log);
                        break;
                    case 3:
                        navigation.setSelectedItemId(R.id.navigation_profile);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {


            }
        });




    }

    private void setupViewPager(ViewPager viewPager){
        SectionsStatePageAdapter adapter = new SectionsStatePageAdapter(getSupportFragmentManager());
        DriverScheduleFragment driverScheduleFragment = new DriverScheduleFragment();
        driverScheduleFragment.setBottomNavigation(navigation);
        adapter.addFragment(driverScheduleFragment, "Driver Schedule");
        adapter.addFragment(new TimeLogFragment(), "Time Log");
        adapter.addFragment(new ReportLogFragment(), "Report Log");
        adapter.addFragment(new ProfileManagerFragment(), "Profile");
        viewPager.setAdapter(adapter);
    }

    public void setViewPager(int fragmentNumber){
        mViewPager.setCurrentItem(fragmentNumber);
    }

    void setActivityTitle(String title){
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
