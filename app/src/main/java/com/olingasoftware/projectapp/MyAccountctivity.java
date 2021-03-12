package com.olingasoftware.projectapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyAccountctivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    String user_email,group_id,user_name,user_id,user_phone,user_group;
     TextView myName,myId,myPhone,myGroup,myGroupId,myEmail,myAccountNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_accountctivity);
        myName=findViewById(R.id.myName);
        myId=findViewById(R.id.myId);
        myAccountNo=findViewById(R.id.myAccountNumber);
        myPhone=findViewById(R.id.myPhone);
        myGroup=findViewById(R.id.myGroup);
        myGroupId=findViewById(R.id.myGroupId);
        myEmail=findViewById(R.id.myEmail);
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();

        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Members");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snap:snapshot.getChildren()){
                    Members members=snap.getValue(Members.class);
                    String userInDb=members.getEmail();
                    String currUser=mUser.getEmail();
                    String pName=members.getNames();
                    String pPhone=members.getPhone();
                    String pId=members.getIdNumber();
                    String pGroup=members.getGroupName();
                    String pGroupId=members.getGroupId();
                    String pAccNo=members.getAccounntNo();
                    if (userInDb.equals(currUser.trim())){
                        myEmail.setText(currUser);
                        myName.setText(pName);
                        myPhone.setText(pPhone);
                        myGroupId.setText(pGroupId);
                        myGroup.setText(pGroup);
                        myId.setText(pId);
                        myAccountNo.setText(pAccNo);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}