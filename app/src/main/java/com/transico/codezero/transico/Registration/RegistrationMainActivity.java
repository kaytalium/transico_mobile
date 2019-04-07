package com.transico.codezero.transico.Registration;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;

import com.transico.codezero.transico.R;

public class RegistrationMainActivity extends AppCompatActivity {

    Button btn_driver;
    Button btn_commuter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


//        Driver Button
        btn_driver = findViewById(R.id.btn_driver);
        btn_driver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

//        Commuter Button
        btn_commuter = findViewById(R.id.btn_commuter);
        btn_commuter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}
