package com.example.learnloop.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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

        System.out.println("CoursePic" + coursePic.length);

        // initUI(rootView);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        mContext = getActivity();

        /*
        TextView welcomeLoopraryMsg = (TextView) rootView.findViewById(R.id.welcomeLoopMsg);
        String text = "Welcome to Looprary!";
        Spannable spannable = new SpannableString(text);
        int startIndex = text.indexOf("Looprary");
        int endIndex = startIndex + "Looprary".length()

         */
        // Set the color of the string to red
        // spannable.setSpan(new ForegroundColorSpan(Color.RED), 0, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

//        textView.setText(spannable);

        //spannable.setSpan(new RelativeSizeSpan(1.4f), startIndex, endIndex, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        //welcomeLoopraryMsg.setText(spannable);

        // rvPopulardestination.setHasFixedSize(true);

        RelativeLayout smu_event_details = (RelativeLayout) rootView.findViewById(R.id.smu_event_details);
        RelativeLayout inprogress_courseRL = (RelativeLayout) rootView.findViewById(R.id.inprogress_courseRL);

        smu_event_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), EventDetailsActivity.class);
                startActivity(intent);
//                EventDetailsFragment fragment1 = new EventDetailsFragment();
//                FragmentManager fragmentManager = getFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(android.R.id.content, fragment1);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();

            }
        });

        inprogress_courseRL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CourseDetailsFragment fragment1 = new CourseDetailsFragment();
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(android.R.id.content, fragment1);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });


        return rootView;

/*
    private void initUI(View rootView) {

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
                android.R.layout.simple_dropdown_item_1line, COURSENAME);

        acLocation.setAdapter(adapter);

        RecyclerView courses_recycView = (RecyclerView) rootView.findViewById(R.id.courses_recycView);
        TextView courses_name = (TextView) rootView.findViewById(R.id.course_name);


        //------------rvpopular destination----------

        //for better performance of recyclerview.
        rvPopulardestination.setHasFixedSize(true);

        CourseAdapter = new CourseAdapter(getContext(), mArrayListDestination);
        rvPopulardestination.setAdapter(mBookFlightAdapter);

        //layout to contain recyclerview
        LinearLayoutManager llm = new LinearLayoutManager(mContext);
        llm.setSmoothScrollbarEnabled(true);
        // orientation of linearlayoutmanager.
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        llm.setAutoMeasureEnabled(true);

        //set layoutmanager for recyclerview.
        rvPopulardestination.setLayoutManager(llm);

        new LoadAllPopularDestination().execute();

        //------------rv long weekends----------

        //for better performance of recyclerview.
        rvlongWeekend.setHasFixedSize(true);

        //layout to contain recyclerview
        LinearLayoutManager llml = new LinearLayoutManager(mContext);
        llml.setSmoothScrollbarEnabled(true);
        // orientation of linearlayoutmanager.
        llml.setOrientation(LinearLayoutManager.HORIZONTAL);
        llml.setAutoMeasureEnabled(true);

        //set layoutmanager for recyclerview.
        rvlongWeekend.setLayoutManager(llml);

        new LoadAllLongWeekend().execute();


        //------------rv deals----------

        //for better performance of recyclerview.
        rvDeals.setHasFixedSize(true);

        //layout to contain recyclerview
        LinearLayoutManager llmd = new LinearLayoutManager(mContext);
        llmd.setSmoothScrollbarEnabled(true);
        // orientation of linearlayoutmanager.
        llmd.setOrientation(LinearLayoutManager.HORIZONTAL);
        llmd.setAutoMeasureEnabled(true);

        //set layoutmanager for recyclerview.
        rvDeals.setLayoutManager(llmd);

        new LoadAllDeals().execute();


        //------------rv cityguides----------

        //for better performance of recyclerview.
        rvCityGuides.setHasFixedSize(true);

        //layout to contain recyclerview
        LinearLayoutManager llmc = new LinearLayoutManager(mContext);
        llmc.setSmoothScrollbarEnabled(true);
        // orientation of linearlayoutmanager.
        llmc.setOrientation(LinearLayoutManager.HORIZONTAL);
        llmc.setAutoMeasureEnabled(true);

        //set layoutmanager for recyclerview.
        rvCityGuides.setLayoutManager(llmc);

        new LoadAllCityGuide().execute();
        */
    }
}
