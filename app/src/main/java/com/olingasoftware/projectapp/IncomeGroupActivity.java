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

public class IncomeGroupActivity extends AppCompatActivity {
    EditText edtSrc, edtGroupAmount;
    TextView  tvGrpName,tvGrpId;
    Button btnDepGroup;
    FirebaseAuth fAuth;
    FirebaseUser mUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_group);
        edtGroupAmount =findViewById(R.id.edtGamount);
        edtSrc =findViewById(R.id.edtGrpSrc);
        tvGrpName =findViewById(R.id.tvGNa);
        tvGrpId =findViewById(R.id.tvGId);
        btnDepGroup=findViewById(R.id.btnDepGroup);
        fAuth=FirebaseAuth.getInstance();
        mUser=fAuth.getCurrentUser();
        getGroupDetaild();
        btnDepGroup.setOnClickListener(new View.OnClickListener() {
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
                            String incomesrc=edtSrc.getText().toString();
                            Integer AmountWithdraw=0;
                            String Amount=edtGroupAmount.getText().toString().trim();
                            Integer DepAmount= Integer.parseInt(String.valueOf(edtGroupAmount));
                            String userInDb=members.getEmail();
                            if (userInDb.equals(currUser.trim())){
                                String udId= String.valueOf(System.currentTimeMillis());
                                GroupAccHolder groupAccHolder=new GroupAccHolder(DepAmount,AmountWithdraw,incomesrc,time,currUser);
                                DatabaseReference grpAccNode =FirebaseDatabase.getInstance().getReference("GroupAccount");
                                grpAccNode.child(udId).setValue(groupAccHolder);
                                Toast.makeText(IncomeGroupActivity.this, "Record saved", Toast.LENGTH_SHORT).show();
                                edtGroupAmount.setText("");
                                edtSrc.setText("");
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