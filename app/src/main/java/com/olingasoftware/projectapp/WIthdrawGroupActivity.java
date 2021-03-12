package com.olingasoftware.projectapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WIthdrawGroupActivity extends AppCompatActivity {
    EditText edtreason, edtGroupAmount;
    TextView tvGrpName,tvGrpId;
    Button btnWithdraw;
    FirebaseAuth fAuth;
    FirebaseUser mUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_w_ithdraw_group);
        edtGroupAmount =findViewById(R.id.edtGamount);
        edtreason =findViewById(R.id.edtReason);
        tvGrpName =findViewById(R.id.tvGNa);
        tvGrpId =findViewById(R.id.tvGId);
        btnWithdraw=findViewById(R.id.btnDepGroup);
        fAuth=FirebaseAuth.getInstance();
        mUser=fAuth.getCurrentUser();
        getGroupDetaild();
        btnWithdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference ref1= FirebaseDatabase.getInstance().getReference("Members");
                ref1.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot sn:snapshot.getChildren()) {
                            Members members=sn.getValue(Members.class);
                            String currUser=mUser.getEmail();
                            //time
                            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
                            Date date=new Date();
                            String time=format.format(date);
                            String reason=edtreason.getText().toString();
                            Integer AmountDeposit=0;
                            String Amount=edtGroupAmount.getText().toString().trim();
                            int AmountWithdraw=Integer.parseInt(Amount);
                            String userInDb=members.getEmail();
                            if (userInDb.equals(currUser.trim())){
                                String udId= String.valueOf(System.currentTimeMillis());
                                GroupAccHolder groupAccHolder=new GroupAccHolder(AmountDeposit,AmountWithdraw,reason,time,currUser);
                                DatabaseReference grpAccNode =FirebaseDatabase.getInstance().getReference("GroupAccount");
                                grpAccNode.child(udId).setValue(groupAccHolder);
                                Toast.makeText(WIthdrawGroupActivity.this, "Record saved", Toast.LENGTH_SHORT).show();
                                edtGroupAmount.setText("");
                                edtreason.setText("");
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });

    }
    public void getGroupDetaild(){
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Members");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snap:snapshot.getChildren()) {
                    Members members=snap.getValue(Members.class);
                    String userInDb=members.getEmail();
                    String currentUser=mUser.getEmail();
                    if (userInDb.equals(currentUser.trim())){
                        String currGrpName=members.getGroupName();
                        String currGrpId=members.getGroupId();
                        tvGrpName.setText(currGrpName);
                        tvGrpId.setText(currGrpId);
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}