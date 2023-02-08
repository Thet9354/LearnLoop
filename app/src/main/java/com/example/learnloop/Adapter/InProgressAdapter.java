package com.example.learnloop.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learnloop.Model.InProgress;
import com.example.learnloop.Model.Transaction;
import com.example.learnloop.R;

import java.util.ArrayList;

public class InProgressAdapter extends RecyclerView.Adapter<InProgressAdapter.CardViewHolder>{

    private ArrayList<InProgress> inProgressArrayList;
    private Context mContext;

    public InProgressAdapter(Context mContext, ArrayList<InProgress> inProgressArrayList) {
        this.inProgressArrayList = inProgressArrayList;
        this.mContext = mContext;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    private InProgressAdapter.OnItemClickListener mItemClickListener;

    public void setOnItemClickListener(InProgressAdapter.OnItemClickListener listener) {
        this.mItemClickListener = listener;
    }

    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return mContext;
    }

    @NonNull
    @Override
    public InProgressAdapter.CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View itemView = LayoutInflater.from(context).
                inflate(R.layout.row_inprogress, parent, false);

        return new InProgressAdapter.CardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull InProgressAdapter.CardViewHolder holder, int position) {

        holder.txtView_inProgressCourse.setText(inProgressArrayList.get(position).getInProgressCourse());
        holder.txtView_inProgressHost.setText(inProgressArrayList.get(position).getInProgressHost());
        holder.txtView_inProgressHostDesc.setText(inProgressArrayList.get(position).getInProgressHostDesc());
    }

    @Override
    public int getItemCount() {
        return inProgressArrayList.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {

        private androidx.cardview.widget.CardView cv_inProgress;
        private RelativeLayout rel_inProgress;
        private ImageView imgView_inProgress, imgView_inProgressHost;
        private TextView txtView_inProgressCourse, txtView_inProgressHost, txtView_inProgressHostDesc;

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);

            cv_inProgress = itemView.findViewById(R.id.cv_inProgress);

            rel_inProgress = itemView.findViewById(R.id.rel_inProgress);

            imgView_inProgress = itemView.findViewById(R.id.imgView_inProgress);
            imgView_inProgressHost = itemView.findViewById(R.id.imgView_inProgressHost);

            txtView_inProgressCourse = itemView.findViewById(R.id.txtView_inProgressCourse);
            txtView_inProgressHost = itemView.findViewById(R.id.txtView_inProgressHost);
            txtView_inProgressHostDesc = itemView.findViewById(R.id.txtView_inProgressHostDesc);

        }
    }
}
