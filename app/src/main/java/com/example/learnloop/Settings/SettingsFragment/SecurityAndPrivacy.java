package com.example.learnloop.Settings.SettingsFragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.learnloop.R;

public class SecurityAndPrivacy extends Fragment {

    private ImageView btn_back, img_security, btn_security, img_apps, btn_apps_and_sessions, img_connectedAcc, btn_connectedAcc;

    private TextView txtView_Done, txtView_security, txtView_apps_and_sessions, txtView_connectedAcc;

    private LinearLayout LL_security, LL_apps_and_sessions, LL_connectedAcc;

    private RelativeLayout rel_security, rel_apps_and_sessions, rel_connectedAcc;

    private Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_security_and_privacy, container, false);

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

    private void findViews(View view) {

        //--->ImageView
        btn_back = view.findViewById(R.id.btn_back);
        img_security = view.findViewById(R.id.img_security);
        btn_security = view.findViewById(R.id.btn_security);
        img_apps = view.findViewById(R.id.img_apps);
        btn_apps_and_sessions = view.findViewById(R.id.btn_apps_and_sessions);
        img_connectedAcc = view.findViewById(R.id.img_connectedAcc);
        btn_connectedAcc = view.findViewById(R.id.btn_connectedAcc);

        //--->TextView
        txtView_Done = view.findViewById(R.id.txtView_Done);
        txtView_security = view.findViewById(R.id.txtView_security);
        txtView_apps_and_sessions = view.findViewById(R.id.txtView_apps_and_sessions);
        txtView_connectedAcc = view.findViewById(R.id.txtView_connectedAcc);

        //--->LinearLayout
        LL_security = view.findViewById(R.id.LL_security);
        LL_apps_and_sessions = view.findViewById(R.id.LL_apps_and_sessions);
        LL_connectedAcc = view.findViewById(R.id.LL_connectedAcc);

        //--->RelativeLayout
        rel_security = view.findViewById(R.id.rel_security);
        rel_apps_and_sessions = view.findViewById(R.id.rel_apps_and_sessions);
        rel_connectedAcc = view.findViewById(R.id.rel_connectedAcc);
    }


}