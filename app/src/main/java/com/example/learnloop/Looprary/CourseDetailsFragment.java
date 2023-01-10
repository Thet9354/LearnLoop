package com.example.learnloop.Looprary;

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
import android.widget.TextView;

import com.example.learnloop.R;
import com.google.android.material.button.MaterialButton;

public class CourseDetailsFragment extends Fragment {

    private Context mContext;

    private TextView txtView_back, txtView_courseTitle, txtView_courseStats, txtView_courseAttendee,
            txtView_attendeeDetail, txtView_lessonsProgress, txtView_courseHost, txtView_hostDesc,
            txtView_courseDesc, txtView_seeMore;

    private ImageView imgView_course;

    private MaterialButton btn_seeResource;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_course_details, container, false);

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
        txtView_back = v.findViewById(R.id.txtView_back);
        txtView_courseTitle = v.findViewById(R.id.txtView_courseTitle);
        txtView_courseStats = v.findViewById(R.id.txtView_courseStats);
        txtView_courseAttendee = v.findViewById(R.id.txtView_courseAttendee);
        txtView_attendeeDetail = v.findViewById(R.id.txtView_attendeeDetail);
        txtView_lessonsProgress = v.findViewById(R.id.txtView_lessonsProgress);
        txtView_courseHost = v.findViewById(R.id.txtView_courseHost);
        txtView_hostDesc = v.findViewById(R.id.txtView_hostDesc);
        txtView_courseDesc = v.findViewById(R.id.txtView_courseDesc);
        txtView_seeMore = v.findViewById(R.id.txtView_seeMore);

        //Imageview
        imgView_course = v.findViewById(R.id.imgView_course);

        //Button
        btn_seeResource = v.findViewById(R.id.btn_seeResource);

        initUI();
    }

    private void initUI() {

        initRecView();
    }

    private void initRecView() {
        //TODO: Initialize recyclerView
    }
}