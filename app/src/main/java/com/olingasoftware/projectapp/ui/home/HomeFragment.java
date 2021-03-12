package com.olingasoftware.projectapp.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.olingasoftware.projectapp.LoginActivity;
import com.olingasoftware.projectapp.Members;
import com.olingasoftware.projectapp.R;

public class HomeFragment extends Fragment {
        ImageView logout;
        FirebaseAuth mAuth;
        FirebaseUser mUser;
        String user_email,group_id;
        TextView mTvGroupName;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mTvGroupName = view.findViewById(R.id.tv_group_name);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        user_email = mUser.getEmail();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Members");
        logout=view.findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                getActivity().finish();
                startActivity(new Intent(getContext(), LoginActivity.class));
                Toast.makeText(getContext(), "Logged Out", Toast.LENGTH_SHORT).show();
            }
        });
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot snap:snapshot.getChildren()) {
                    Members members=snap.getValue(Members.class);
                    String userInDb=members.getEmail();
                    if (userInDb.equals(user_email.trim())){
                        String CurrGname=members.getGroupName();
                        mTvGroupName.setText(CurrGname);
                    }
                }}


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return view;
    }


}