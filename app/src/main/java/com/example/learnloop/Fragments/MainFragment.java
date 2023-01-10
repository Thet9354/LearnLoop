package com.example.learnloop.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.learnloop.AboutActivity;
import com.example.learnloop.MainActivity;
import com.example.learnloop.R;

public class MainFragment extends Fragment {

    private Context mContext;

    private TextView txtView_welcomeMsg, txtView_cardName, txtView_cardBalance, txtView_cardLimit;

    private RecyclerView rv_recentTransaction;

    private androidx.cardview.widget.CardView cv_cardWallet;

    private ImageView btn_about;

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

        //Imageview
        btn_about = v.findViewById(R.id.btn_about);

        //RecyclerView
        rv_recentTransaction = v.findViewById(R.id.rv_recentTransaction);

        //CardView
        cv_cardWallet = v.findViewById(R.id.cv_cardWallet);

        initUI();
    }

    private void initUI() {


    }
}