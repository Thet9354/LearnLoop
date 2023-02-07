package com.example.learnloop.Settings.SettingsFragment;

import static com.facebook.FacebookSdk.getApplicationContext;

import android.content.Context;
import android.content.Intent;
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

public class YourAccount extends Fragment {

    private ImageView btn_back, img_accInfo, btn_accInfo, img_changePassword, btn_changePassword, img_download,
            btn_dataArchive, img_deactivate, btn_deactivateAcc;

    private TextView txtView_Done, txtView_accInfo, txtView_changePassword, txtView_dataArchive, txtView_deactivateAcc;

    private LinearLayout LL_accInfo, LL_changePassword, LL_dataArchive, LL_deactivateAcc;

    private RelativeLayout rel_accInfo, rel_changePassword, rel_dataArchive, rel_deactivateAcc;

    private Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_your_account, container, false);

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
        img_accInfo = view.findViewById(R.id.img_accInfo);
        btn_accInfo = view.findViewById(R.id.btn_accInfo);
        img_changePassword = view.findViewById(R.id.img_changePassword);
        btn_changePassword = view.findViewById(R.id.btn_changePassword);
        img_download = view.findViewById(R.id.img_download);
        btn_dataArchive = view.findViewById(R.id.btn_dataArchive);
        img_deactivate = view.findViewById(R.id.img_deactivate);
        btn_deactivateAcc = view.findViewById(R.id.btn_deactivateAcc);

        //--->TextView
        txtView_Done = view.findViewById(R.id.txtView_Done);
        txtView_accInfo = view.findViewById(R.id.txtView_accInfo);
        txtView_changePassword = view.findViewById(R.id.txtView_changePassword);
        txtView_dataArchive = view.findViewById(R.id.txtView_dataArchive);
        txtView_deactivateAcc = view.findViewById(R.id.txtView_deactivateAcc);

        //--->LinearLayout
        LL_accInfo = view.findViewById(R.id.LL_accInfo);
        LL_changePassword = view.findViewById(R.id.LL_changePassword);
        LL_dataArchive = view.findViewById(R.id.LL_dataArchive);
        LL_deactivateAcc = view.findViewById(R.id.LL_deactivateAcc);

        //--->RelativeLayout
        rel_accInfo = view.findViewById(R.id.rel_accInfo);
        rel_changePassword = view.findViewById(R.id.rel_changePassword);
        rel_dataArchive = view.findViewById(R.id.rel_dataArchive);
        rel_deactivateAcc = view.findViewById(R.id.rel_deactivateAcc);

        pageDirectories();

    }

    private void pageDirectories() {

        LL_accInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(getApplicationContext(), Account_Activity.class));
            }
        });

        LL_changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(getApplicationContext(), ChangePassword_Activity.class));
            }
        });

        LL_dataArchive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        LL_deactivateAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(getApplicationContext(), Deactivate_Account_Activity.class));
            }
        });

    }

}