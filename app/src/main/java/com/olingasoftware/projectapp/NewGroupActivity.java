  package com.olingasoftware.projectapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class NewGroupActivity extends AppCompatActivity {
    Button btnSave,btnCancel;
    EditText edtFullNames,edtIdNo,edtPhoneNNo,edtEmil,edtPassword,edtConfirmPassword,groupname;
    FirebaseAuth fauth;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_group);
        btnSave=findViewById(R.id.btnSave);
        btnCancel=findViewById(R.id.btnCancel);
        edtFullNames=findViewById(R.id.fullNames);
        edtIdNo=findViewById(R.id.idNo);
        edtPhoneNNo=findViewById(R.id.phoneNo);
        edtPassword=findViewById(R.id.password);
        groupname=findViewById(R.id.GroupN);
        edtEmil=findViewById(R.id.email);
        fauth=FirebaseAuth.getInstance();
        edtConfirmPassword=findViewById(R.id.confirmPassword);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthenticationProcess();
            }
        });

    }
    public void AuthenticationProcess(){
        String names=edtFullNames.getText().toString();
        String id=edtIdNo.getText().toString();
        String phone=edtPhoneNNo.getText().toString();
        String email=edtEmil.getText().toString().trim();
        String pass1=edtPassword.getText().toString();
        String GName=groupname.getText().toString();
        String pass2=edtConfirmPassword.getText().toString();
        if (GName.isEmpty()){
            groupname.setError("Group is required");
            groupname.requestFocus();
            return;
        }
        if (names.isEmpty()){
            edtFullNames.setError("Names are required");
            edtFullNames.requestFocus();
            return;
        }
        if (id.isEmpty()){
            edtIdNo.setError("ID is required");
            edtIdNo.requestFocus();
            return;
        }
        if (phone.isEmpty()){
            edtPhoneNNo.setError("phone is required");
            edtPhoneNNo.requestFocus();
            return;
        }
        if (email.isEmpty()){
            edtEmil.setError("Email is required");
            edtEmil.requestFocus();
        }
        if (pass1.isEmpty()){
            edtPassword.setError("Password is required");
            edtPassword.requestFocus();
            return;
        }
        if (pass2.isEmpty()){
            edtConfirmPassword.setError("password did not match");
            edtConfirmPassword.requestFocus();
            return;
        }
        if (pass1.length()<6){
            edtPassword.setError("password must contain six characters");
            edtPassword.requestFocus();
            return;
        }
        if (pass2.length()<6){
            edtPassword.setError("password did not match");
            edtPassword.requestFocus();
            return;
        }
        dialog=new ProgressDialog(NewGroupActivity.this);
        dialog.setTitle("Saving");
        dialog.setCanceledOnTouchOutside(false);
        dialog.setMessage("Please wait..");
        dialog.show();
        // generating group id............................................
        Random random=new Random();
        Random random1=new Random();
        Random random2=new Random();
        Integer rNo=random.nextInt(1000)+1;
        Integer rNo1=random1.nextInt(10)+1;
        Integer rNo2=random2.nextInt(100)+1;
        Integer rand=rNo+rNo1+rNo2;
        String cur=String.valueOf(rand);
        String g="G";
        String GroupIdNo=cur+g;
        //.......................................................................
        // generating account no
        Random ar=new Random();
        Integer ar1=ar.nextInt(3000);
        Integer ar2=ar.nextInt(1000);
        Integer ar3=ar.nextInt(10);
        Integer aranndom=ar1+ar2+ar3;
        String a="A";
        String arandom1=String.valueOf(aranndom);
        String accountNo=a+arandom1;
        //........................................
        // .........................
        String admin="yes";
        Members members=new Members(names,id,phone,GroupIdNo,email,accountNo,GName,admin);
        GroupHolder groupHolder=new GroupHolder(GroupIdNo,GName);
        FirebaseDatabase db=FirebaseDatabase.getInstance();
        DatabaseReference nodeGroup=db.getReference("Group");
        DatabaseReference nodeMembers=db.getReference("Members");
        nodeMembers.child(phone).setValue(members);
        nodeGroup.child(GroupIdNo).setValue(groupHolder);
        fauth.createUserWithEmailAndPassword(email,pass2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(NewGroupActivity.this, "Group Created successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),MainHomeActivity.class));
                    finish();
                }
            }
        });

    }

}