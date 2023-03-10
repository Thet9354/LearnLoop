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
import com.example.learnloop.Adapter.InProgressAdapter;
import com.example.learnloop.Model.Courses;
import com.example.learnloop.Model.Events;
import com.example.learnloop.Model.InProgress;
import com.example.learnloop.Model.Transaction;
import com.example.learnloop.R;
import com.example.learnloop.SpaceItemDecoration;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Array;
import java.util.ArrayList;

public class LoopraryFragment extends Fragment {

    //RecyclerView
    private RecyclerView rv_events, rv_inProgress, rv_course;

    private EventsAdapter eventsAdapter;
    private final ArrayList<Events> eventsArrayList = new ArrayList<>();

    private CourseAdapter courseAdapter;
    private ArrayList<Courses> coursesArrayList = new ArrayList<>();

    private InProgressAdapter inProgressAdapter;
    private ArrayList<InProgress> inProgressArrayList = new ArrayList<>();

    String phoneNumber = "93542856";

    private Context mContext;

    InProgress inProgress;

    int[] coursePic = {R.drawable.course_investments101, R.drawable.course_investments101, R.drawable.course_investments101,
            R.drawable.course_investments101, R.drawable.course_investments101, R.drawable.course_investments101, R.drawable.course_investments101,
            R.drawable.course_investments101, R.drawable.course_investments101, R.drawable.course_investments101};

    int[] hostPic = {R.drawable.course_investments101, R.drawable.course_investments101, R.drawable.course_investments101,
            R.drawable.course_investments101, R.drawable.course_investments101, R.drawable.course_investments101, R.drawable.course_investments101,
            R.drawable.course_investments101, R.drawable.course_investments101, R.drawable.course_investments101};

    int[] universityPic = {R.drawable.course_investments101, R.drawable.course_investments101, R.drawable.course_investments101,
            R.drawable.course_investments101, R.drawable.course_investments101, R.drawable.course_investments101, R.drawable.course_investments101,
            R.drawable.course_investments101, R.drawable.course_investments101, R.drawable.course_investments101};

    int[] eventPic = {R.drawable.smu_citi_financial_literacy, R.drawable.smu_citi_financial_literacy, R.drawable.smu_citi_financial_literacy,
            R.drawable.smu_citi_financial_literacy, R.drawable.smu_citi_financial_literacy};

    int[] eventHostPic = {R.drawable.smu_logo, R.drawable.smu_logo, R.drawable.smu_logo,
            R.drawable.smu_logo, R.drawable.smu_logo};

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://learnloop-1673224439925-default-rtdb.asia-southeast1.firebasedatabase.app/");
    DatabaseReference databaseReference  = database.getReference().child("users");

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

        getIntentData();
    }

    private void getIntentData() {

        phoneNumber = getArguments().getString("Phone Number");

        if (phoneNumber == null)
        {
            phoneNumber = "93542856";
        }
        else
            return;

        initUI();
    }

    private void initUI() {

        initEventRecView();
        initCourseRecView();
        initInProgressRecView();

    }

    private void initInProgressRecView() {

        //for better performance of recyclerview.

        int spaceInPixels = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16, getResources().getDisplayMetrics());
        rv_inProgress.addItemDecoration(new SpaceItemDecoration(spaceInPixels));

        rv_inProgress.setHasFixedSize(true);

        inProgressAdapter = new InProgressAdapter(getContext(), inProgressArrayList);
        rv_inProgress.setAdapter(inProgressAdapter);

        //layout to contain recyclerview
        LinearLayoutManager llm = new LinearLayoutManager(mContext);
        llm.setSmoothScrollbarEnabled(true);
        // orientation of linearlayoutmanager.
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        llm.setAutoMeasureEnabled(true);

        //set layoutmanager for recyclerview.
        rv_inProgress.setLayoutManager(llm);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.child(phoneNumber).child("User's Courses").getChildren())
                {
                    inProgress = dataSnapshot.getValue(InProgress.class);
                    inProgressArrayList.add(inProgress);
                }
                inProgressAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void initCourseRecView() {

        //for better performance of recyclerview.

        int spaceInPixels = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16, getResources().getDisplayMetrics());
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
        rv_events.addItemDecoration(new SpaceItemDecoration(spaceInPixels));

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

                String[] eventTitle = getResources().getStringArray(R.array.event_title);
                String[] eventHost = getResources().getStringArray(R.array.event_host);
                String[] eventHostDesc = getResources().getStringArray(R.array.host_desc);
                String[] eventParticipants = getResources().getStringArray(R.array.event_participants);
                String[] eventLink = getResources().getStringArray(R.array.event_link);


                for (int i = 0 ; i < eventTitle.length; i++)
                {
                    events = new Events();

                    events.setEventImage(eventPic[i]);
                    events.setHostImage(eventHostPic[i]);

                    events.setEventTitle(eventTitle[i]);
                    events.setEventHost(eventHost[i]);
                    events.setHostDesc(eventHostDesc[i]);
                    events.setEventParticipants(eventParticipants[i]);
                    events.setEventLink(eventLink[i]);

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
                String[] courseLink = getResources().getStringArray(R.array.course_link);


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
                    courses.setCourseLink(courseLink[i]);

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
