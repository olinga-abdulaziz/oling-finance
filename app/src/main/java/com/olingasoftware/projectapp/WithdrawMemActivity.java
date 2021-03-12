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

public class WithdrawMemActivity extends AppCompatActivity {
    TextView da,dp;
    EditText edtWithdamount;
    Button btnWithd;
    ProgressDialog dialog;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw_mem);
        da=findViewById(R.id.dA);
        dp=findViewById(R.id.dP);
        dialog=new ProgressDialog(this);
        dialog.setTitle("Withdraw Amount");
        dialog.setMessage("Please wait...");
        dialog.setCanceledOnTouchOutside(false);
        btnWithd =findViewById(R.id.saveAcc);
        edtWithdamount=findViewById(R.id.edtWithdamount);
        Intent intent=getIntent();
        String accname=intent.getStringExtra("AccName");
        String accphon=intent.getStringExtra("AccPho");
        da.setText(accname);
        dp.setText(accphon);
        btnWithd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                                String amnt=edtWithdamount.getText().toString().trim();
                                int finalAmount=Integer.parseInt(amnt);
                                if (amnt.isEmpty()){
                                   edtWithdamount.setError("Amount is required !");
                                   edtWithdamount.requestFocus();
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
                                //                                //get curr date/time
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

                                String DepAmount="0";
                                String WithdrawAmount=amnt;
                                String AccPhoNe=members.getPhone();
                                String AccName=accname.trim();
                                String AccIGroupId=members.getGroupId();
                                AccHolder accHolder=new AccHolder(AccName,AccPhoNe,AccIGroupId,DepAmount,WithdrawAmount,time);
                                DatabaseReference AccountNode=FirebaseDatabase.getInstance().getReference("Accounts");
                                AccountNode.child(cur).setValue(accHolder);
                                dialog.dismiss();
                                edtWithdamount.setText("");
                                Toast.makeText(getApplicationContext(), "You have successfully Withdraw for this account", Toast.LENGTH_SHORT).show();

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