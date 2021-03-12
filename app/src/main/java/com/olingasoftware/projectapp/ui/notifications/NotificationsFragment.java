package com.olingasoftware.projectapp.ui.notifications;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.olingasoftware.projectapp.CustomAdapter;
import com.olingasoftware.projectapp.GroupTransHolder;
import com.olingasoftware.projectapp.R;

import java.util.ArrayList;

public class NotificationsFragment extends Fragment {
    ListView groupTranslist;
    ArrayList<GroupTransHolder> transList;
    CustomAdapter adapter;
    ProgressDialog dialog;
    FirebaseUser mUser;
    FirebaseAuth fAuth;
    DatabaseReference ref;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_notifications, container, false);
        groupTranslist =view.findViewById(R.id.groupTransList);
        ref = FirebaseDatabase.getInstance().getReference("Members");
        transList = new ArrayList<>();
        fAuth = FirebaseAuth.getInstance();
        adapter = new CustomAdapter(getContext(),transList);
        dialog = new ProgressDialog(getContext());
        dialog.setTitle("Loading Accounts");
        dialog.setMessage("Please wait...");
        dialog.setCanceledOnTouchOutside(false);
        mUser = fAuth.getCurrentUser();
        String currUser = mUser.getEmail();
        dialog.show();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                transList.clear();
                for (DataSnapshot snap:snapshot.getChildren()){
                    GroupTransHolder groupHolder=snap.getValue(GroupTransHolder.class);
                    transList.add(groupHolder);
                }
                adapter.notifyDataSetChanged();
                dialog.dismiss();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        groupTranslist.setAdapter(adapter);
        return view;
    }
}