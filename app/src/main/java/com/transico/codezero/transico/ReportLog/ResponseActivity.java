package com.transico.codezero.transico.ReportLog;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.widget.EditText;
import android.widget.Toast;

import com.transico.codezero.transico.CustomComponents.CustomChatBox.ChatBox;
import com.transico.codezero.transico.R;

public class ResponseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_response);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();

        if(actionBar !=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Responses");
        }

        ChatBox chatBox = findViewById(R.id.chat_controller);
        chatBox.setOnEnterClickListener(new ChatBox.OnEnterClickListener() {
            @Override
            public void enterClickListener(EditText input) {
                Toast.makeText(getApplicationContext(),"Text: "+input.getText(),Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
//        This is the code that is sending the information back to the calling Activity
        finish();
        return super.onSupportNavigateUp();
    }

}
