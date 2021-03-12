package com.olingasoftware.projectapp;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class NewAccountActivity extends AppCompatActivity {
    EditText edtGroupName;
    TextView groupId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);
        edtGroupName=findViewById(R.id.edtGroupName);
        groupId=findViewById(R.id.grId);
        String listHolder=getIntent().getStringExtra("ListValues");
        edtGroupName.setText(listHolder);


    }
}