package com.transico.codezero.transico.ReportLog;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.transico.codezero.transico.R;

import static java.lang.Integer.parseInt;

public class NewReportLogActivity extends AppCompatActivity {

//    TextView
    TextView txtCancel;
    TextView txtUserName;
    TextView txtCreateNew;
    TextView txtTile;
    TextView txtGroup;

//    EditText
    EditText userInput;

//    buttons
    Button btnTile;
    Button btnGroup;

//    Variables
    private static final int SECOND_ACTIVITY_REQUEST_CODE = 0;
    private static final int THIRD_ACTIVITY_REQUEST_CODE = 1;

    //this is the variable for the Group Activity
    private String returnValue = null;
    private int returnObjectId = 0;

    //This is the variable for the Title Activity
    private String returnValueForTitle = null;
    private int returnObjectIdForTitle = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_report_log);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        if(actionBar != null){
           actionBar.setDisplayHomeAsUpEnabled(true);
        }

//        Display text and button for Title
        txtTile = findViewById(R.id.txt_title);
        btnTile = findViewById(R.id.btnTitle);
        btnTile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),TitleSelectionActivity.class);
                i.putExtra("TitleActivityObjectId",returnObjectIdForTitle);
                startActivityForResult(i, THIRD_ACTIVITY_REQUEST_CODE);

            }
        });


//        Display text and button for group
        txtGroup = findViewById(R.id.txt_group);
        btnGroup = findViewById(R.id.btnGroup);
        btnGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),GroupSelectionActivity.class);
                i.putExtra("buttonId",returnObjectId);
                startActivityForResult(i, SECOND_ACTIVITY_REQUEST_CODE);
            }
        });


//      Create new issue button and click action
        txtCreateNew = findViewById(R.id.txt_create_new);
        txtCreateNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(),"Report Add",Toast.LENGTH_LONG).show();
                finish();
            }
        });

//      Cancel new issue button and click action
        txtCancel = findViewById(R.id.txt_cancel);
        txtCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

//      User fullname and setting the information received from the calling activity
        txtUserName = findViewById(R.id.tv_nrla_username);

//      User input control and setting type features
        userInput = findViewById(R.id.et_input);
        userInput.addTextChangedListener(mTextEditorWatcher);

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    private final TextWatcher mTextEditorWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
           if(s.length() > 81){
               userInput.setTextSize(16);
           }else{
               userInput.setTextSize(24);
           }
        }

        public void afterTextChanged(Editable s) {
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Information coming from the Group Activity
        if(requestCode == SECOND_ACTIVITY_REQUEST_CODE){
            if(resultCode == RESULT_OK){
                returnValue = data.getStringExtra("ActivityResult");
                returnObjectId = data.getIntExtra("buttonCheckedId",0);
                txtGroup.setText(returnValue);
            }
        }

        //Information coming from the Title Activity
        if(requestCode == THIRD_ACTIVITY_REQUEST_CODE){
            if(resultCode == RESULT_OK){
                returnValueForTitle = data.getStringExtra("ActivityTitleResult");
                returnObjectIdForTitle = data.getIntExtra("TitleButtonCheckedId",0);
                txtTile.setText(returnValueForTitle);
            }
        }
    }
}
