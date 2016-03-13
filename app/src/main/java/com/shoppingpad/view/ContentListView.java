package com.shoppingpad.view;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.shoppingpad.R;
import com.shoppingpad.controller.ContentListViewControler;
import com.shoppingpad.localdatabase.ContentListDBHandler;
import com.shoppingpad.observer.ContentListViewObserver;
import com.shoppingpad.viewmodel.ContentListViewModel;

/**
 * Created by bridgelabz5 on 6/3/16.
 *
 * Purpose:
 * It Is The View Of MVVM Design Pattern.
 * It Is The UI Class Which Hold The UI Elements.
 * It Listens To Action Performed In UI class.
 * It Implements And The Observer Pattern To Listen Changes In The View model.
 * It Holds The View model To Update Its State Of The UI.
 *
 * Displays The RecyclerView Containing All ContentList.It is The MainActivity
 * Which Need To Be Included In Manifest.xml File.
 *
 */

public class ContentListView extends AppCompatActivity
                             implements ContentListViewObserver
{
    //ContentListViewModel Object
    ContentListViewModel mContentListViewModel;

    //Initializing recyclerview
    RecyclerView mRecyclerView;

    //adapter object inorder to set the adapter on RecyclerView
    ContentListViewAdapter mContentListViewAdapter;

    ContentListDBHandler mContentListDBHandler;

    ContentListViewControler mContentListViewControler;

    //onCreate will set the layout to a particular RecyclerView
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_view);

        //Setting The Adapter On RecyclerView
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                this,LinearLayoutManager.VERTICAL,false);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(linearLayoutManager);


        mContentListViewModel = new ContentListViewModel(getApplicationContext());
        mContentListViewAdapter =  new ContentListViewAdapter
                                       (getApplicationContext(), mContentListViewModel);

        mRecyclerView.setAdapter(mContentListViewAdapter);

        GetContentData getContentData = new GetContentData();
        getContentData.execute();

    }

    public class GetContentData extends AsyncTask<Void,Void,Void>
    {
        ProgressDialog progressDialog = new ProgressDialog(ContentListView.this);

        public GetContentData() {}


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setMessage("DOWNLOADING");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            mContentListViewModel.getContentViewDetails();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            mContentListViewAdapter.notifyDataSetChanged();
            progressDialog.dismiss();
        }
    }
}
