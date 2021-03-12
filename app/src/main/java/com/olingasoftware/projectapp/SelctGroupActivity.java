package com.olingasoftware.projectapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SelctGroupActivity extends AppCompatActivity {
    ListView groupList;
    ArrayList<GroupHolder> groups;
    SelectGroupCustomAdapter adapter;
    ProgressDialog dialog;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selct_group);
        getSupportActionBar().hide();
        groupList=findViewById(R.id.groupsList);
        groups=new ArrayList<>();
        adapter=new SelectGroupCustomAdapter(this,groups);
        dialog=new ProgressDialog(this);
        dialog.setTitle("Loading Groups");
        dialog.setMessage("Please wait...");
        dialog.setCanceledOnTouchOutside(false);
        groupList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GroupHolder a=groups.get(position);
                String groupname=a.getGroupName();
                String groupid=a.getGroupId();
                Bundle bundle=new Bundle();
                bundle.putString("groupname",groupname);
                bundle.putString("groupid",groupid);
                Intent intent=new Intent(getApplicationContext(),JoinNewGroupActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        ref= FirebaseDatabase.getInstance().getReference().child("Group");
        dialog.show();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                groups.clear();
                for (DataSnapshot snap:snapshot.getChildren()){
                    GroupHolder groupHolder=snap.getValue(GroupHolder.class);
                    groups.add(groupHolder);
                }
                adapter.notifyDataSetChanged();
                dialog.dismiss();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });
        groupList.setAdapter(adapter);
    }

}