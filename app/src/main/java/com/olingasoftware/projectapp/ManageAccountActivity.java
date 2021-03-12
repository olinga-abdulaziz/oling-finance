package com.olingasoftware.projectapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class ManageAccountActivity extends AppCompatActivity {
    TextView ttname,ttphone;
    CardView cardDepAcc,cardWithd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_account);
        ttname=findViewById(R.id.accnameHere);
        ttphone=findViewById(R.id.accphoneHere);
        cardWithd=findViewById(R.id.cardWithdraw);
        cardDepAcc=findViewById(R.id.cardDepAcc);
        Bundle bundle=getIntent().getExtras();
        String name=bundle.getString("name");
        String phone=bundle.getString("phone");
        ttname.setText(name);
        ttphone.setText(phone);
        cardDepAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String acc=ttname.getText().toString();
                String pho=ttphone.getText().toString();
                Intent intent=new Intent(getApplicationContext(),DepositMemActivity.class);
                intent.putExtra("AccName",acc);
                intent.putExtra("AccPho",pho);
                startActivity(intent);
            }
        });
        cardWithd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String acc=ttname.getText().toString();
                String pho=ttphone.getText().toString();
                Intent intent=new Intent(getApplicationContext(),WithdrawMemActivity.class);
                intent.putExtra("AccName",acc);
                intent.putExtra("AccPho",pho);
                startActivity(intent);
            }
        });

    }
}