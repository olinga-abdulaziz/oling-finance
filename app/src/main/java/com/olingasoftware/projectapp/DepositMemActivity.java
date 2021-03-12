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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class DepositMemActivity extends AppCompatActivity {
        TextView da,dp;
        EditText amount;
        Button btnDep;
        ProgressDialog dialog;
        DatabaseReference ref;
        FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit_mem);
        da=findViewById(R.id.dA);
        dp=findViewById(R.id.dP);
        dialog=new ProgressDialog(this);
        dialog.setTitle("Depositing Amount");
        dialog.setMessage("Please wait...");
        dialog.setCanceledOnTouchOutside(false);
        btnDep=findViewById(R.id.saveAcc);
        amount=findViewById(R.id.edtWithdamount);
        Intent intent=getIntent();
        String accname=intent.getStringExtra("AccName");
        String accphon=intent.getStringExtra("AccPho");
        da.setText(accname);
        dp.setText(accphon);
        btnDep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String amnt=amount.getText().toString().trim();
                int finalAmount=Integer.parseInt(amnt);
                if (amnt.isEmpty()){
                    amount.setError("Amount is required !");
                    amount.requestFocus();
                    return;
                }
                dialog.show();
                ref=FirebaseDatabase.getInstance().getReference("Members");
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot snap:snapshot.getChildren()){
                            Members members=snap.getValue(Members.class);
                            String mtu=members.getNames().trim();
                            String currname=da.getText().toString().trim();
                            if (currname.equals(mtu.trim())){
                                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
                                Date date=new Date();
                                String time=format.format(date);
                                //...................
                                Random random=new Random();
                                Random random1=new Random();
                                Random random2=new Random();
                                Integer rNo=random.nextInt(1000)+1;
                                Integer rNo1=random1.nextInt(10)+1;
                                Integer rNo2=random2.nextInt(100)+1;
                                Integer rand=rNo+rNo1+rNo2;
                                String cur=String.valueOf(rand);

                                String DepAmount=amnt;
                                String WithdrawAmount="0";
                                String AccPhoNe=members.getPhone();
                                String AccName=accname.trim();
                                String udId= String.valueOf(System.currentTimeMillis());
                                String AccIGroupId=members.getGroupId();
                                AccHolder accHolder=new AccHolder(AccName,AccPhoNe,AccIGroupId,DepAmount,WithdrawAmount,time);
                                DatabaseReference AccountNode=FirebaseDatabase.getInstance().getReference("Accounts");
                                AccountNode.child(cur).setValue(accHolder);
                                dialog.dismiss();
                                amount.setText("");
                                Toast.makeText(getApplicationContext(), "You have successfully deposit for this account", Toast.LENGTH_SHORT).show();

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

}








//    String amnt=amount.getText().toString().trim();
//                                if (amnt.isEmpty()){
//                                    amount.setError("Amount is required !");
//                                    amount.requestFocus();
//                                }
//                                //get curr date/time
//                                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
//                                Date date=new Date();
//                                String time=format.format(date);
//                                //...................
//                                String AccPhoNe=m.getPhone();
//                                String AccName=da.toString().trim();
//                                String AccIGroupId=m.getGroupId();
//                                AccHolder accHolder=new AccHolder(AccName,AccPhoNe,AccIGroupId,amnt,time);
//                                DatabaseReference AccountNode=FirebaseDatabase.getInstance().getReference("Accounts");
//                                AccountNode.child(AccPhoNe).setValue(accHolder);
//                                dialog.dismiss();