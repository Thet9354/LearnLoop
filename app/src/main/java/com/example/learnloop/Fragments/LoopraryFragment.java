package com.example.learnloop.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.example.learnloop.Looprary.CourseDetailsFragment;
import com.example.learnloop.Looprary.EventDetailsActivity;
import com.example.learnloop.Looprary.CourseAdapter;
// import com.example.learnloop.Looprary.CourseModel;
import com.example.learnloop.R;

public class LoopraryFragment extends Fragment {

    //RecyclerView
    private RecyclerView rv_events, rv_inProgress;

    private CourseAdapter courseAdapter;
//    private ArrayList<CourseModel> mArrayListDestination = new ArrayList<>();

    private Context mContext;

    int[] coursePic = {R.drawable.course_investments101, R.drawable.course_crypto, R.drawable.img,
            R.drawable.course_taxes, R.drawable.course_inflation, R.drawable.course_recession};

    private static final String[] COURSENAME = new String[]{
            "Investment 101",
            "Cryptocurrency",
            "Consumer Rights in Singapore",
            "Tax in Singapore",
            "Inflation and its Impacts",
            "Recession and its Impacts"
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_looprary, container, false);

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

        //RecyclerView
        rv_events = v.findViewById(R.id.rv_events);
        rv_inProgress = v.findViewById(R.id.rv_inProgress);

        initUI();
    }

    private void initUI() {

        //TODO: Init all 3 recyclerView

    }
}
