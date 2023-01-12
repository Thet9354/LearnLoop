package com.example.learnloop.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learnloop.Model.Courses;
import com.example.learnloop.Model.Transaction;
import com.example.learnloop.R;

import java.util.ArrayList;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CardViewHolder>{

    private ArrayList<Courses> coursesArrayList;
    private Context mContext;

    public CourseAdapter(Context mContext, ArrayList<Courses> coursesArrayList) {
        this.coursesArrayList = coursesArrayList;
        this.mContext = mContext;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private CourseAdapter.OnItemClickListener mItemClickListener;

    public void setOnItemClickListener(CourseAdapter.OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }

    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return mContext;
    }


    @NonNull
    @Override
    public CourseAdapter.CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View itemView = LayoutInflater.from(context).
                inflate(R.layout.row_course, parent, false);

        return new CourseAdapter.CardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.CardViewHolder holder, int position) {

        holder.txtView_courseTitle.setText(coursesArrayList.get(position).getCourseTitle());
        holder.txtView_hostName.setText(coursesArrayList.get(position).getHostName());
        holder.txtView_hostDesc.setText(coursesArrayList.get(position).getHostDesc());
        holder.txtView_courseDuration.setText(coursesArrayList.get(position).getCourseDuration());
        holder.txtView_courseLessons.setText(coursesArrayList.get(position).getCourseLessons());

        holder.imgView_course.setImageResource(coursesArrayList.get(position).getCourseImage());
        holder.imgView_host.setImageResource(coursesArrayList.get(position).getHostImage());
    }

    @Override
    public int getItemCount() {
        return coursesArrayList.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {

        private CardView cv_course;
        private RelativeLayout rel_course;
        private ImageView imgView_course, imgView_host;
        private TextView txtView_courseTitle, txtView_hostName, txtView_hostDesc, txtView_courseDuration, txtView_courseLessons, txtView_save;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);

            //CardView
            cv_course = itemView.findViewById(R.id.cv_course);

            //RelativeLayout
            rel_course = itemView.findViewById(R.id.rel_course);

            //ImageView
            imgView_course = itemView.findViewById(R.id.imgView_course);
            imgView_host = itemView.findViewById(R.id.imgView_host);

            //TextView
            txtView_courseTitle = itemView.findViewById(R.id.txtView_courseTitle);
            txtView_hostName = itemView.findViewById(R.id.txtView_hostName);
            txtView_hostDesc = itemView.findViewById(R.id.txtView_hostDesc);
            txtView_courseDuration = itemView.findViewById(R.id.txtView_courseDuration);
            txtView_courseLessons = itemView.findViewById(R.id.txtView_courseLessons);
            txtView_save = itemView.findViewById(R.id.txtView_save);

        }
    }
}
