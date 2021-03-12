package com.olingasoftware.projectapp.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.olingasoftware.projectapp.AccountsActivity;
import com.olingasoftware.projectapp.AdminActivity;
import com.olingasoftware.projectapp.GroupActivity;
import com.olingasoftware.projectapp.LoanActivity;
import com.olingasoftware.projectapp.Members;
import com.olingasoftware.projectapp.MyAccountctivity;
import com.olingasoftware.projectapp.R;
import com.olingasoftware.projectapp.TransactionsActivity;

public class DashboardFragment extends Fragment {
    FirebaseAuth fAuth;
    CardView cardGroup,cardAccounts,cardTransaction,cardLoan,cardMyAccount,cardAdmin;
    TextView myAccountIcon;
    FirebaseUser mUser;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        cardGroup = view.findViewById(R.id.cardGroup);
        myAccountIcon = view.findViewById(R.id.myAccountIcon);
        cardAccounts = view.findViewById(R.id.cardACcount);
        cardLoan = view.findViewById(R.id.cardLoan);
        cardMyAccount = view.findViewById(R.id.cardMyAccount);
        cardAdmin=view.findViewById(R.id.cardAdmin);
        cardTransaction = view.findViewById(R.id.cardTransaction);
        fAuth =FirebaseAuth.getInstance();
        mUser=fAuth.getCurrentUser();
        Intent intent=getActivity().getIntent();
        String curr=intent.getStringExtra("email");
        myAccountIcon.setText(curr);
        cardAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Members");
                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot snap:snapshot.getChildren()) {
                            Members members=snap.getValue(Members.class);
                            String userInDb=members.getEmail();
                            String admin=members.getAdmin();
                            String currUser=mUser.getEmail();
                            if (userInDb.equals(currUser.trim())){
                                if (admin.equals("yes")){
                                    startActivity(new Intent(getActivity(), AdminActivity.class));
                                }else {
                                    Toast.makeText(getContext(), "This feture is only allowed for Administrator", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
        cardGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), GroupActivity.class));
            }
        });
        myAccountIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MyAccountctivity.class));
            }
        });
        cardAccounts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AccountsActivity.class));
            }
        });
        cardLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LoanActivity.class));
            }
        });
        cardMyAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),MyAccountctivity.class));
            }
        });
        cardTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), TransactionsActivity.class));
            }
        });
        return view;
    }

}