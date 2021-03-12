package com.olingasoftware.projectapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
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

import java.util.ArrayList;

public class AccountsActivity extends AppCompatActivity {
    ListView accountsList;
    ArrayList<AccountsHolder> accounts;
    AccountsCustomAdapter adapter;
    ProgressDialog dialog;
    FirebaseUser mUser;
    FirebaseAuth fAuth;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts);
        accountsList = findViewById(R.id.accList);
        ref = FirebaseDatabase.getInstance().getReference("Members");
        accounts = new ArrayList<>();
        fAuth = FirebaseAuth.getInstance();
        adapter = new AccountsCustomAdapter(this, accounts);
        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading Accounts");
        dialog.setMessage("Please wait...");
        dialog.setCanceledOnTouchOutside(false);
        mUser = fAuth.getCurrentUser();
        String currUser = mUser.getEmail();
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("Members");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()) {
                    Members members = ds.getValue(Members.class);
                    String memberInDb = members.getNames();
                    String memAdmin = members.getAdmin();
                    String user = currUser;
                    String dEmail = members.getEmail();
                    if (dEmail.equals(user.trim())) {
                        if (memAdmin.equals("yes")) {
                            accountsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    AccountsHolder a = accounts.get(position);
                                    String name = a.getNames();
                                    String phone = a.getPhone();
                                    Bundle bundle = new Bundle();
                                    bundle.putString("name", name);
                                    bundle.putString("phone", phone);
                                    Intent intent = new Intent(getApplicationContext(), ManageAccountActivity.class);
                                    intent.putExtras(bundle);
                                    startActivity(intent);
                                }
                            });

                        } else {
                            Toast.makeText(AccountsActivity.this, "Only Admin can Modify perform transactions", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


//       accountsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//           @Override
//           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//               DatabaseReference dbRef=FirebaseDatabase.getInstance().getReference("Members");
//                ref.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        for (DataSnapshot ds:snapshot.getChildren()) {
//                            Members members=ds.getValue(Members.class);
//                            String memberInDb=members.getNames();
//                            String memAdmin=members.getAdmin();
//                            AccountsHolder a=accounts.get(position);
//                            String name=a.getNames();
//                            String user=currUser;
//                            String dEmail=members.getEmail();
//                            String phone=a.getPhone();
//                            if (dEmail.equals(user.trim())){
//                               if (memAdmin.equals("yes")){
//                                   Bundle bundle=new Bundle();
//                                   bundle.putString("name",name);
//                                   bundle.putString("phone",phone);
//                                   Intent intent=new Intent(getApplicationContext(),ManageAccountActivity.class);
//                                   intent.putExtras(bundle);
//                                   startActivity(intent);
//                               }else {
//                                   Toast.makeText(AccountsActivity.this, "Only Admin can perform this action", Toast.LENGTH_SHORT).show();
//                               }
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
//
//
//
//
//           }
//       });
//
        ref = FirebaseDatabase.getInstance().getReference().child("Members");
        dialog.show();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                accounts.clear();
                for (DataSnapshot snap : snapshot.getChildren()) {
                    AccountsHolder accs = snap.getValue(AccountsHolder.class);
                    accounts.add(accs);

                }
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        accountsList.setAdapter(adapter);

    }


}