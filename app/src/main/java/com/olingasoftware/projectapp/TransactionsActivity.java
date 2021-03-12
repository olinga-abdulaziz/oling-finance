package com.olingasoftware.projectapp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TransactionsActivity extends AppCompatActivity {
    ListView transactionsList;
    ArrayList<TransHolder> transactions;
    TransactionsCustomAdapter adapter;
    ProgressDialog dialog;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);
        transactionsList=findViewById(R.id.listTransaction);
        transactions=new ArrayList<>();
        adapter= new TransactionsCustomAdapter(this,transactions);
        dialog=new ProgressDialog(this);
        dialog.setTitle("Loading Transactions");
        dialog.setMessage("Please wait...");
        dialog.setCanceledOnTouchOutside(false);
        ref= FirebaseDatabase.getInstance().getReference().child("Accounts");
        dialog.show();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                transactions.clear();
                for (DataSnapshot snap:snapshot.getChildren()){
                    TransHolder accs=snap.getValue(TransHolder.class);
                    transactions.add(accs);
                }
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        transactionsList.setAdapter(adapter);
    }
}