package com.example.learnloop.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.learnloop.AboutActivity;
import com.example.learnloop.Adapter.RecentTransactionAdapter;
import com.example.learnloop.MainActivity;
import com.example.learnloop.Model.Transaction;
import com.example.learnloop.R;

import java.util.ArrayList;

public class MainFragment extends Fragment {

    private Context mContext;

    private TextView txtView_welcomeMsg, txtView_cardName, txtView_cardBalance, txtView_cardLimit;

    private RecyclerView rv_recentTransaction;

    private androidx.cardview.widget.CardView cv_cardWallet;

    private ImageView btn_about;

    private RecentTransactionAdapter recentTransactionAdapter;

    private final ArrayList<Transaction> transactionArrayList = new ArrayList<>();

    int[] transactionPic = {R.drawable.testing, R.drawable.testing, R.drawable.testing, R.drawable.testing};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

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
        txtView_welcomeMsg = v.findViewById(R.id.txtView_welcomeMsg);
        txtView_cardName = v.findViewById(R.id.txtView_cardName);
        txtView_cardBalance = v.findViewById(R.id.txtView_cardBalance);
        txtView_cardLimit = v.findViewById(R.id.txtView_cardLimit);

        //Imageview
        btn_about = v.findViewById(R.id.btn_about);

        //RecyclerView
        rv_recentTransaction = v.findViewById(R.id.rv_recentTransaction);

        //CardView
        cv_cardWallet = v.findViewById(R.id.cv_cardWallet);

        initUI();
    }

    private void initUI() {

        //for better performance of recyclerview.

        rv_recentTransaction.setHasFixedSize(true);

        recentTransactionAdapter = new RecentTransactionAdapter(getContext(), transactionArrayList);
        rv_recentTransaction.setAdapter(recentTransactionAdapter);

        //layout to contain recyclerview
        LinearLayoutManager llm = new LinearLayoutManager(mContext);
        llm.setSmoothScrollbarEnabled(true);
        // orientation of linearlayoutmanager.
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        llm.setAutoMeasureEnabled(true);

        //set layoutmanager for recyclerview.
        rv_recentTransaction.setLayoutManager(llm);

        new loadRecentTransaction().execute();

    }

    Transaction transaction;

    class loadRecentTransaction extends AsyncTask<String, String, String> {

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
                    transaction = new Transaction();
//                    favNft.setNftID(i);
                    transaction.setTransactionPic(transactionPic[i]);
//                    favNft.setNftCategory(destinationID[i]);
                    transaction.setTitle(transactionTitle[i]);
                    transaction.setPurpose(transactionPurpose[i]);
                    transaction.setAmount(transactionAmount[i]);
                    transactionArrayList.add(transaction);
                    transaction = null;
                }


            } catch (Exception e) {
                e.printStackTrace();

            }

            return null;
        }

        protected void onPostExecute(String file_url) {

//            pgbPopulardestination.setVisibility(View.GONE);

            if (transactionArrayList != null && transactionArrayList.size() > 0) {
                recentTransactionAdapter = new RecentTransactionAdapter(mContext, transactionArrayList);
                rv_recentTransaction.setAdapter(recentTransactionAdapter);
                recentTransactionAdapter.notifyDataSetChanged();
            }
        }
    }
}