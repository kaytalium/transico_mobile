package com.transico.codezero.transico.ReportLog;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import com.transico.codezero.transico.CustomComponents.CustomRadioButtonOption.CustomRadioButton;
import com.transico.codezero.transico.CustomComponents.CustomRadioButtonOption.CustomRadioGroup;
import com.transico.codezero.transico.R;
import com.transico.codezero.transico.SystemHelper.Helper;

import static java.lang.Integer.parseInt;

public class GroupSelectionActivity extends AppCompatActivity {

    CustomRadioGroup mCustomRadioGroup;

    //Variables
    private String userOption;
    private int selectedRadioButtonId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_section);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();

        if(actionBar !=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Group Section");
        }

        //perform radio group action
        mCustomRadioGroup = findViewById(R.id.radioGroup);
        mCustomRadioGroup.setOnCheckedChangeListener(new CustomRadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(View radioGroup, CustomRadioButton radioButton, boolean isChecked, int checkedId) {
                if(radioButton != null){
                    userOption = Helper.stringBuilder("@ %s",radioButton.getName());
                    selectedRadioButtonId = checkedId;
                }


            }
        });

        //get the intent data from the parent activity
        Intent i = getIntent();
        selectedRadioButtonId = i.getIntExtra("buttonId",0);

        if(selectedRadioButtonId !=0){
            mCustomRadioGroup.check(selectedRadioButtonId);
        }


    }



    @Override
    public boolean onSupportNavigateUp() {
        //This is the code that is sending the information back to the main activity
        Intent intent = new Intent();
        intent.putExtra("ActivityResult", userOption);
        intent.putExtra("buttonCheckedId",selectedRadioButtonId);
        setResult(RESULT_OK, intent);
        finish();
        return super.onSupportNavigateUp();
    }
}
