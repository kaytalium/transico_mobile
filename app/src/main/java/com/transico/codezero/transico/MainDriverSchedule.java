package com.transico.codezero.transico;

import android.os.Bundle;

import com.transico.codezero.transico.ui.maindriverschedule.MainDriverScheduleFragment;

import androidx.appcompat.app.AppCompatActivity;

public class MainDriverSchedule extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_driver_schedule_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainDriverScheduleFragment.newInstance())
                    .commitNow();
        }
    }
}
