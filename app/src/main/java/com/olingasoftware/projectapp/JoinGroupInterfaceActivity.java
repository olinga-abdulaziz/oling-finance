package com.olingasoftware.projectapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class JoinGroupInterfaceActivity extends AppCompatActivity {
    Button pJoin,selectGroup;
    TextView regNewGroup;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    EditText groupNamePrivate,groupIdPrivate;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_interface_group);
        pJoin=findViewById(R.id.pJoin);
        selectGroup=findViewById(R.id.selectGroup);
        groupIdPrivate=findViewById(R.id.groupIdPrivate);
        groupNamePrivate=findViewById(R.id.edtGroupName);
        regNewGroup=findViewById(R.id.regewGroup);
       firebaseDatabase=FirebaseDatabase.getInstance();


        selectGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SelctGroupActivity.class));
            }
        });
        pJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(JoinGroupInterfaceActivity.this, "These feature is coming soon", Toast.LENGTH_SHORT).show();
//                passGrouptoNextActivity();
            }
        });

        regNewGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),NewGroupActivity.class));
            }
        });
    }


//    public void passGrouptoNextActivity(){
//        String privateGrroupName=groupNamePrivate.getText().toString();
//        String privateGrroupId=groupIdPrivate.getText().toString();
//        if (privateGrroupId.isEmpty()){
//            groupIdPrivate.setError("Group Name is Required");
//            groupIdPrivate.requestFocus();
//        } else if (privateGrroupName.isEmpty()) {
//            groupNamePrivate.setError("Group ID is Required");
//            groupIdPrivate.requestFocus();
//        }else {
//            Intent intent = new Intent(getApplicationContext(), NewAccountActivity.class);
//            startActivity(intent);
//        }
//    }
}