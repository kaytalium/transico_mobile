package com.transico.codezero.transico.ReportLog;

import android.content.Context;
import android.util.Log;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.Query;
import com.transico.codezero.transico.FirestoreConnection.FirebaseManager;
import com.transico.codezero.transico.FirestoreConnection.FirestoreConnection;
import com.transico.codezero.transico.SystemHelper.databaseCommand;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

class ReportLogController {

    private RecyclerView mRecyclerview;
    private Context mContext;
    ReportLogAdapter reportLogAdapter;


    ReportLogController(RecyclerView mRecyclerview, Context mContext) {
        this.mRecyclerview = mRecyclerview;
        this.mContext = mContext;
    }

    // start getting data from the OL Database
    void init(){

        /* Load Result in Adapter*/
        reportLogAdapter = new ReportLogAdapter(FirestoreConnection.ReportManager.mainQuery("35264"), mContext);
//        reportLogAdapter.startListening();

        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerview.setAdapter(reportLogAdapter);

    }
}
