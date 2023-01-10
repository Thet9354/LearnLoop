package com.example.learnloop.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.example.learnloop.R;

public class DiscountFragment extends Fragment {

    private RelativeLayout rel_foodie, rel_shopping, rel_entertainment, rel_deal;

    private Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_discount, container, false);

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
        rel_foodie = v.findViewById(R.id.rel_foodie);
        rel_shopping = v.findViewById(R.id.rel_shopping);
        rel_entertainment = v.findViewById(R.id.rel_entertainment);
        rel_deal = v.findViewById(R.id.rel_deal);

        initUI();
    }

    private void initUI() {

    }
}