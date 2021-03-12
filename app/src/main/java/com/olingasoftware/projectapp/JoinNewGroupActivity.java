package com.olingasoftware.projectapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class JoinNewGroupActivity extends AppCompatActivity {
    EditText GroupIdEdt,fullNames,idNo,phoneNo,email,password,confirmPassword;
    TextView GroupNameEdt,GroupTxtId;
    Button btnCancel,btnSave;
    FirebaseAuth fauth;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_new_group);
        GroupIdEdt=findViewById(R.id.GroupIdEdt);
        fullNames=findViewById(R.id.fullNames);
        idNo=findViewById(R.id.idNo);
        phoneNo=findViewById(R.id.phoneNo);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        confirmPassword=findViewById(R.id.confirmPassword);
        btnCancel=findViewById(R.id.btnCancel);
        fauth=FirebaseAuth.getInstance();
        btnSave=findViewById(R.id.btnSave);
        GroupTxtId=findViewById(R.id.GroupTxtId);
        GroupNameEdt=findViewById(R.id.GroupNameEdt);
        Bundle bundle=getIntent().getExtras();
        String groupname=bundle.getString("groupname");
        String groipid=bundle.getString("groupid");
        GroupNameEdt.setText(groupname);
        GroupIdEdt.setText(groipid);
        GroupTxtId.setText(groipid);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String names=fullNames.getText().toString();
                String id=idNo.getText().toString();
                String phone=phoneNo.getText().toString().trim();
                String Email=email.getText().toString().trim();
                String pass1=password.getText().toString();
                String GId=GroupIdEdt.getText().toString();
                String pass2=confirmPassword.getText().toString();
                if (names.isEmpty()){
                    fullNames.setError("Names are required");
                    fullNames.requestFocus();
                    return;
                }
                if (id.isEmpty()){
                    idNo.setError("Names are required");
                    idNo.requestFocus();
                    return;
                }
                if (phone.isEmpty()){
                    phoneNo.setError("Names are required");
                    phoneNo.requestFocus();
                    return;
                }
                if (Email.isEmpty()){
                    email.setError("Names are required");
                    email.requestFocus();
                    return;
                }
                if (pass1.isEmpty()){
                    password.setError("Names are required");
                    password.requestFocus();
                    return;
                }
                if (pass2.isEmpty()){
                    confirmPassword.setError("Names are required");
                    confirmPassword.requestFocus();
                    return;
                }
                if (pass1.length()<6){
                    password.setError("password must contain six characters");
                    password.requestFocus();
                    return;
                }
                if (pass2.length()<6){
                    password.setError("password did not match");
                    password.requestFocus();
                    return;
                }
                dialog=new ProgressDialog(JoinNewGroupActivity.this);
                dialog.setTitle("Submitting");
                dialog.setCanceledOnTouchOutside(false);
                dialog.setMessage("Please wait..");
                dialog.show();
                //account number
                Random ar=new Random();
                Integer ar1=ar.nextInt(3000);
                Integer ar2=ar.nextInt(1000);
                Integer ar3=ar.nextInt(10);
                Integer aranndom=ar1+ar2+ar3;
                String a="A";
                String arandom1=String.valueOf(aranndom);
                String accountNo=a+arandom1;
                String admin="no";
                Members members=new Members(names,id,phone,GId,Email,accountNo,groupname,admin);
                FirebaseDatabase db=FirebaseDatabase.getInstance();
                DatabaseReference nodeMembers=db.getReference("Members");
                nodeMembers.child(phone).setValue(members);
                fauth.createUserWithEmailAndPassword(Email,pass2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(JoinNewGroupActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainHomeActivity.class));
                            finish();
                        }
                    }
                });
            }
        });
    }
}