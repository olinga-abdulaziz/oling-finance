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

public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    EditText user,password;
    TextView joinGroup;
    FirebaseAuth fAuth;
    ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        user = findViewById(R.id.edtuserName);
        joinGroup=findViewById(R.id.joinText);
        btnLogin = findViewById(R.id.btnLogin);
        password = findViewById(R.id.edtPassword);
        fAuth=FirebaseAuth.getInstance();
        if (fAuth.getCurrentUser() !=null){
            startActivity(new Intent(getApplicationContext(),MainHomeActivity.class));
            finish();
        }
        joinGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), JoinGroupInterfaceActivity.class));
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName=user.getText().toString();
                String pass=password.getText().toString();
                if (userName.isEmpty()){
                    user.setError("Email is Required");
                    user.requestFocus();
                    return;
                }
                if (pass.isEmpty()){
                    password.setError("Password is Required");
                    password.requestFocus();
                    return;
                }
                dialog=new ProgressDialog(LoginActivity.this);
                dialog.setTitle("Login");
                dialog.setMessage("please wait...");
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
                fAuth.signInWithEmailAndPassword(userName,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                           Intent intent=new Intent(LoginActivity.this,MainHomeActivity.class);
                           intent.putExtra("email",userName);
                           startActivity(intent);
                            dialog.dismiss();
                            finish();
                        }else {
                            Toast.makeText(LoginActivity.this, "Error !"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    }
                });

            }
        });
    }
}