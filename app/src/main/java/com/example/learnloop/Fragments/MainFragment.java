package com.example.learnloop.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.learnloop.AboutActivity;
import com.example.learnloop.MainActivity;
import com.example.learnloop.R;

public class MainFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        ImageView toAbout = (ImageView) rootView.findViewById(R.id.goToAbout_Button);


        toAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create an Intent to start the other activity
                Intent intent = new Intent(getActivity(), AboutActivity.class);

                // Start the activity
                startActivity(intent);
            }
        });


        return rootView;

    }
}