package com.example.learnloop.Fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.example.learnloop.Adapter.CourseAdapter;
import com.example.learnloop.Adapter.EventsAdapter;
// import com.example.learnloop.Looprary.CourseModel;
import com.example.learnloop.Model.Courses;
import com.example.learnloop.Model.Events;
import com.example.learnloop.R;
import com.example.learnloop.SpaceItemDecoration;

import java.util.ArrayList;

public class LoopraryFragment extends Fragment {

    //RecyclerView
    private RecyclerView rv_events, rv_inProgress, rv_course;

    private final ArrayList<Events> eventsArrayList = new ArrayList<>();

    private EventsAdapter eventsAdapter;

    private CourseAdapter courseAdapter;
    private ArrayList<Courses> coursesArrayList = new ArrayList<>();

    private Context mContext;

    int[] coursePic = {R.drawable.course_investments101, R.drawable.course_investments101, R.drawable.course_investments101,
            R.drawable.course_investments101, R.drawable.course_investments101, R.drawable.course_investments101, R.drawable.course_investments101,
            R.drawable.course_investments101, R.drawable.course_investments101, R.drawable.course_investments101};

    int[] hostPic = {R.drawable.course_investments101, R.drawable.course_investments101, R.drawable.course_investments101,
            R.drawable.course_investments101, R.drawable.course_investments101, R.drawable.course_investments101, R.drawable.course_investments101,
            R.drawable.course_investments101, R.drawable.course_investments101, R.drawable.course_investments101};

    int[] universityPic = {R.drawable.course_investments101, R.drawable.course_investments101, R.drawable.course_investments101,
            R.drawable.course_investments101, R.drawable.course_investments101, R.drawable.course_investments101, R.drawable.course_investments101,
            R.drawable.course_investments101, R.drawable.course_investments101, R.drawable.course_investments101};

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
        rv_course = v.findViewById(R.id.rv_course);

        initUI();
    }

    private void initUI() {

        //TODO: Init all 3 recyclerView
        initEventRecView();

        initCourseRecView();

    }

    private void initCourseRecView() {

        //for better performance of recyclerview.

        int spaceInPixels = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, getResources().getDisplayMetrics());
        rv_course.addItemDecoration(new SpaceItemDecoration(spaceInPixels));

        rv_course.setHasFixedSize(true);

        courseAdapter = new CourseAdapter(getContext(), coursesArrayList);
        rv_course.setAdapter(eventsAdapter);

        //layout to contain recyclerview
        LinearLayoutManager llm = new LinearLayoutManager(mContext);
        llm.setSmoothScrollbarEnabled(true);
        // orientation of linearlayoutmanager.
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        llm.setAutoMeasureEnabled(true);



        //set layoutmanager for recyclerview.
        rv_course.setLayoutManager(llm);

        new loadCourse().execute();
    }

    private void initEventRecView() {

        //for better performance of recyclerview.

        int spaceInPixels = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16, getResources().getDisplayMetrics());
        rv_course.addItemDecoration(new SpaceItemDecoration(spaceInPixels));

        rv_events.setHasFixedSize(true);

        eventsAdapter = new EventsAdapter(getContext(), eventsArrayList);
        rv_events.setAdapter(eventsAdapter);

        //layout to contain recyclerview
        LinearLayoutManager llm = new LinearLayoutManager(mContext);
        llm.setSmoothScrollbarEnabled(true);
        // orientation of linearlayoutmanager.
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        llm.setAutoMeasureEnabled(true);

        //set layoutmanager for recyclerview.
        rv_events.setLayoutManager(llm);

        new loadEvents().execute();

    }

    Events events;
    Courses courses;

    class loadEvents extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected String doInBackground(String... args) {
            try {

                String[] transactionTitle = getResources().getStringArray(R.array.transaction_title);
                String[] transactionPurpose = getResources().getStringArray(R.array.transaction_purpose);
                String[] transactionAmount = getResources().getStringArray(R.array.transaction_amount);


                for (int i = 0 ; i < transactionTitle.length; i++)
                {
                    events = new Events();

//                    events.setTransactionPic(transactionPic[i]);
//                    events.setTitle(transactionTitle[i]);
//                    events.setPurpose(transactionPurpose[i]);
//                    events.setAmount(transactionAmount[i]);
                    eventsArrayList.add(events);
                    events = null;
                }


            } catch (Exception e) {
                e.printStackTrace();

            }

            return null;
        }

        protected void onPostExecute(String file_url) {

//            pgbPopulardestination.setVisibility(View.GONE);

            if (eventsArrayList != null && eventsArrayList.size() > 0) {
                eventsAdapter = new EventsAdapter(mContext, eventsArrayList);
                rv_events.setAdapter(eventsAdapter);
                eventsAdapter.notifyDataSetChanged();
            }
        }
    }

    class loadCourse extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected String doInBackground(String... args) {
            try {

                String[] courseTitle = getResources().getStringArray(R.array.course_title);
                String[] courseHost = getResources().getStringArray(R.array.course_host);
                String[] hostDesc = getResources().getStringArray(R.array.courseHost_Desc);
                String[] courseDuration = getResources().getStringArray(R.array.course_duration);
                String[] courseLesson = getResources().getStringArray(R.array.course_lesson);

                String[] courseDesc = getResources().getStringArray(R.array.course_desc);
                String[] courseUniversity = getResources().getStringArray(R.array.course_university);
                String[] courseUniversityDesc = getResources().getStringArray(R.array.course_universityDesc);
                String[] coursePublishedDate = getResources().getStringArray(R.array.course_publishedDates);



                for (int i = 0 ; i < courseTitle.length; i++)
                {
                    courses = new Courses();
                    courses.setCourseImage(coursePic[i]);

                    courses.setCourseTitle(courseTitle[i]);
                    courses.setHostName(courseHost[i]);
                    courses.setHostDesc(hostDesc[i]);
                    courses.setCourseDuration(courseDuration[i]);
                    courses.setCourseLessons(courseLesson[i]);

                    courses.setCourseDesc(courseDesc[i]);
                    courses.setCourseUniversity(courseUniversity[i]);
                    courses.setCourseUniversityDesc(courseUniversityDesc[i]);
                    courses.setCoursePublishedDate(coursePublishedDate[i]);

                    coursesArrayList.add(courses);
                    courses = null;
                }


            } catch (Exception e) {
                e.printStackTrace();

            }

            return null;
        }

        protected void onPostExecute(String file_url) {

//            pgbPopulardestination.setVisibility(View.GONE);

            if (coursesArrayList != null && coursesArrayList.size() > 0) {
                courseAdapter = new CourseAdapter(mContext, coursesArrayList);
                rv_course.setAdapter(courseAdapter);
                courseAdapter.notifyDataSetChanged();
            }
        }
    }

}
