package com.example.learnloop.Fragments;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.telephony.SmsManager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.learnloop.AboutUs_Activity;
import com.example.learnloop.Adapter.RecentTransactionAdapter;
import com.example.learnloop.Model.Transaction;
import com.example.learnloop.R;
import com.example.learnloop.Settings.Setting_Activity;
import com.example.learnloop.SpaceItemDecoration;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainFragment extends Fragment {

    private Context mContext;

    private TextView txtView_welcomeMsg, txtView_cardName, txtView_cardBalance, txtView_cardLimit, txtView_allowanceType, txtView_noTransaction;

    private RecyclerView rv_recentTransaction;

    private androidx.cardview.widget.CardView cv_cardWallet;

    private ImageView btn_about, btn_setting;

    private RecentTransactionAdapter recentTransactionAdapter;

    private final ArrayList<Transaction> transactionArrayList = new ArrayList<>();

    private String name, phoneNumber, email, password;

    int[] transactionPic = {R.drawable.testing, R.drawable.testing, R.drawable.testing, R.drawable.testing};

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://learnloop-1673224439925-default-rtdb.asia-southeast1.firebasedatabase.app/");
    DatabaseReference databaseReference  = database.getReference().child("users");

    Transaction transaction;

    String getName;
    String getPhoneNumber;
    String getEmail;

    String getAllowanceType;
    String getBudgetGoal;
    String getBalance;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        mContext = getActivity();

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
    }

    private void findViews(View v) {

        //TextView
        txtView_welcomeMsg = v.findViewById(R.id.txtView_welcomeMsg);
        txtView_cardName = v.findViewById(R.id.txtView_cardName);
        txtView_cardBalance = v.findViewById(R.id.txtView_cardBalance);
        txtView_cardLimit = v.findViewById(R.id.txtView_cardLimit);
        txtView_allowanceType = v.findViewById(R.id.txtView_allowanceType);
        txtView_noTransaction = v.findViewById(R.id.txtView_noTransaction);

        //Imageview
        btn_about = v.findViewById(R.id.btn_about);
        btn_setting = v.findViewById(R.id.btn_setting);

        //RecyclerView
        rv_recentTransaction = v.findViewById(R.id.rv_recentTransaction);

        //CardView
        cv_cardWallet = v.findViewById(R.id.cv_cardWallet);

        getIntentData();

        initUI();

        pageDirectories();
    }

    private void expenditureAlert() {

        //if the balance is close to exceeding the budget or already exceeding, send sms to the user
        String alertMessage = "Hey nigga";

        int cardLimit = Integer.parseInt(getBudgetGoal);
        int cardBalance = Integer.parseInt(getBalance);
        int balanceDiff = (cardBalance/cardLimit)*100;

        SmsManager smsManager = SmsManager.getDefault();

        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            // Should we show an explanation?
            if (shouldShowRequestPermissionRationale(Manifest.permission.SEND_SMS)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                requestPermissions(new String[]{Manifest.permission.SEND_SMS, Manifest.permission.READ_SMS}, PackageManager.PERMISSION_GRANTED);
            }
        } else {
            // Permission has already been granted
            if (cardBalance > cardLimit)
            {
                //SEND ALERT
                alertMessage = "Hey" + name + " !" +" \n" +
                        "You've exceeded your budget!!";
                smsManager.sendTextMessage(phoneNumber, null, alertMessage, null, null);
            }
            else if (balanceDiff > 90)
            {
                //Send alert
                alertMessage = "Hey" + name + " !" +" \n" +
                        "You are about to exceed your budget. Be more mindful with your expenditures!!";
                smsManager.sendTextMessage(phoneNumber, null, alertMessage, null, null);
            }
            else
                return;
        }



        System.out.println("Passed through");

    }

    private void getIntentData() {

        name = getArguments().getString("Name");
        phoneNumber = getArguments().getString("Phone Number");
        email = getArguments().getString("Email");
        password = getArguments().getString("Password");

        if (phoneNumber == null)
        {
            phoneNumber = "93542856";
        }
        else
            return;

    }

    private void pageDirectories() {

        btn_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(mContext, AboutUs_Activity.class));
            }
        });

        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().startActivity(new Intent(mContext, Setting_Activity.class));
            }
        });
    }

    private void initUI() {

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChild(phoneNumber))
                {
                    // Name exist in firebase database
                    // now getting password of user from firebase data and match if with user entered password and username

                    getName = snapshot.child(phoneNumber).child("User's Information").child("Full Name").getValue(String.class);
                    getPhoneNumber = snapshot.child(phoneNumber).child("User's Information").child("Phone Number").getValue(String.class);
                    getEmail = snapshot.child(phoneNumber).child("User's Information").child("Email").getValue(String.class);

                    getAllowanceType = snapshot.child(phoneNumber).child("User's Information").child("Allowance Type").getValue(String.class);
                    getBudgetGoal = snapshot.child(phoneNumber).child("User's Information").child("Budget Goal").getValue(String.class);
                    getBalance = snapshot.child(phoneNumber).child("User's Information").child("Expenditure").getValue(String.class);

                    txtView_welcomeMsg.setText("Hey there, " + getName);
                    txtView_allowanceType.setText(getAllowanceType + " expenditures");
                    txtView_cardName.setText(getName + "'s" + " Little Card");
                    txtView_cardLimit.setText("/ $" + getBudgetGoal);
                    txtView_cardBalance.setText("$" + getBalance);

                    expenditureAlert();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        initRecView();

//        if (rv_recentTransaction.getAdapter().getItemCount() == 0)
//        {
//            //Hide recyclerView
//            rv_recentTransaction.setVisibility(View.GONE);
//            txtView_noTransaction.setVisibility(View.VISIBLE);
//        }
//        else
//        {
//            rv_recentTransaction.setVisibility(View.VISIBLE);
//            txtView_noTransaction.setVisibility(View.GONE);
//        }



    }

    private void initRecView() {
        //for better performance of recyclerview.

        rv_recentTransaction.setHasFixedSize(true);

        recentTransactionAdapter = new RecentTransactionAdapter(getContext(), transactionArrayList);
        rv_recentTransaction.setAdapter(recentTransactionAdapter);

        int spaceInPixels = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, getResources().getDisplayMetrics());
        rv_recentTransaction.addItemDecoration(new SpaceItemDecoration(spaceInPixels));

        //layout to contain recyclerview
        LinearLayoutManager llm = new LinearLayoutManager(mContext);
        llm.setSmoothScrollbarEnabled(true);
        // orientation of linearlayoutmanager.
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        llm.setAutoMeasureEnabled(true);

        //set layoutmanager for recyclerview.
        rv_recentTransaction.setLayoutManager(llm);

        rv_recentTransaction.setAdapter(recentTransactionAdapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.child(phoneNumber).child("User's Transaction").getChildren())
                {
                    transaction = dataSnapshot.getValue(Transaction.class);
                    transactionArrayList.add(transaction);
                }
                recentTransactionAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}