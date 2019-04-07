package com.transico.codezero.transico.ReportLog;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;

import com.transico.codezero.transico.CustomComponents.CustomRadioButtonOption.CustomRadioButton;
import com.transico.codezero.transico.CustomComponents.CustomRadioButtonOption.CustomRadioGroup;
import com.transico.codezero.transico.R;

public class TitleSelectionActivity extends AppCompatActivity {

    private CustomRadioGroup mCustomRadioGroup;
    private String mUserSelection = null;
    private int mSelectionObjectId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title_selection);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();

        if(actionBar !=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Title Section");
        }

        mCustomRadioGroup = findViewById(R.id.custom_radio_group_title_list);
        mCustomRadioGroup.setOnCheckedChangeListener(new CustomRadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(View radioGroup, CustomRadioButton radioButton, boolean isChecked, int checkedId) {
                if(radioButton !=null){
                    mUserSelection = radioButton.getName();
                    mSelectionObjectId = checkedId;
                }
            }
        });

        //Get the child id that was originally selected when the user did a selection
        //If no option was selected do nothing however if there was a selection get the id and set that object to select
        Intent i = getIntent();
        mSelectionObjectId = i.getIntExtra("TitleActivityObjectId",0);

        if(mSelectionObjectId !=0){
            mCustomRadioGroup.check(mSelectionObjectId);
            Log.d("Ovel","Info from Parent "+mSelectionObjectId);
        }

    }


    @Override
    public boolean onSupportNavigateUp() {
//        This is the code that is sending the information back to the calling Activity
        Intent intent = new Intent();
        intent.putExtra("ActivityTitleResult", mUserSelection);
        intent.putExtra("TitleButtonCheckedId",mSelectionObjectId);
        setResult(RESULT_OK, intent);
        finish();
        return super.onSupportNavigateUp();
    }
}
