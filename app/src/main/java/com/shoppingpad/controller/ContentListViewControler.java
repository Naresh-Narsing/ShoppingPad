package com.shoppingpad.controller;

import android.content.Context;
import android.util.Log;

import com.shoppingpad.R;
import com.shoppingpad.localdatabase.ContentListDBHandler;
import com.shoppingpad.model.ContentInfoModel;
import com.shoppingpad.model.ContentViewModels;
import com.shoppingpad.restservice.ContentListServiceHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bridgelabz5 on 6/3/16.
 *
 * Purpose:
 * It Will Act Like A Manager Which WillControls The Flow Of Data In Between
 * The Models,Views And Services.Controller Will Get The Data From The Server
 * And Will Pass Data To DataBase.
 *
 * 1.It will interact with rest service to get data with the cloud
 * 2.Interacts with local db to save and retrieve content info
 * 3.It encapsulates content info model
 * 4.It is data controller in mvvm arch.
 * 5.This provides interface for viewmodel to interact with the controller
 * essentially abstracting the service layer,db layer and data model.
 */

public class ContentListViewControler
{
    //List Storing the contentlistinfo i.e Title and imglink.
    public List<ContentListInfo> mContentListInfos;

    //list containing contentviews info i.e lastseendatetime,no.of views
    //and no.of participants
    public List<ContentListInfo> mContentViews;

    public final static boolean mPERFORM_UNIT_TEST = false;

    ContentListServiceHandler mContentListServiceHandler;

    public List<ContentInfoModel> mContentInfoList;
    public List<ContentViewModels> mContentViewList;

    ContentListDBHandler mContentListDBHandler;

    //    Populating the dummy data in the list
    public ContentListViewControler(Context context)
    {
        if (mPERFORM_UNIT_TEST)
        {
            mContentListInfos = new ArrayList<>();
            mContentViews = new ArrayList<>();
            populateDummyData();
        }
        else
        {
            mContentInfoList = new ArrayList<>();
            mContentViewList = new ArrayList<>();
            mContentListDBHandler = new ContentListDBHandler(context);
            mContentListServiceHandler = new ContentListServiceHandler();
        }
    }

//    Method which will call the getContentInfo & getContentView and will allocate
//    the dummy data to associated lists
    //public List<ContentListInfo> populateDummyData()
    public void populateDummyData()
    {
        getContentInfo();
        getContentView();
    }


//  Method to populate dummy contentinfo data
    public List<ContentListInfo> getContentInfo() {

        int contentIdC[] = {0,1,2};
        int iconC[] = {R.drawable.ic_number1, R.drawable.ic_number2, R.drawable.
                ic_number3};
        String titleC[] = {"ABCD", "XYZ", "PQRS"};
        for (int i = 0; i<contentIdC.length && i<iconC.length && i<titleC.length
                ; i++)
        {
         ContentListInfo info = new ContentListInfo();
         info.mContentId = contentIdC[i];
         info.mControllerTitle = titleC[i];
         info.mControllerImageLink = iconC[i];
         mContentListInfos.add(info);
        }
        return mContentListInfos;
    }

//  Method to populate dummy contentview data
    public List<ContentListInfo> getContentView() {

        int contentIdC[] = {0,1,2};
        String lastViewDateTimeC[] = {"18-5-15", "10-2-16", "18-5-15"};
        String numberOfViewsC[] = {"10 Views","20 Views","30 Views"};
        String numberOfParticipantsC[] = {"50 Participants", "20 Participants",
                "10 Participants"};
        for (int i = 0; i < lastViewDateTimeC.
              length && i < numberOfViewsC.length && i < numberOfParticipantsC.
              length; i++)
        {
            ContentListInfo info2 = new ContentListInfo();
            info2.mContentId = contentIdC[i];
            info2.mControllerLastViewDateTime = lastViewDateTimeC[i];
            info2.mControllerNumberOfViews = numberOfViewsC[i];
            info2.mControllerNumberOfParticipants = numberOfParticipantsC[i];
            mContentViews.add(info2);
        }
        return mContentViews;
    }

//  Getting the object from rest and passing it to ContentInfoModel

    public List<ContentInfoModel> getContentInfoFromRests()
    {

        ContentInfoModel contentInfoModel =new ContentInfoModel();
        Log.e("Hi","IN getContentInfoFromRests");
        JSONArray jsonArray = mContentListServiceHandler.getContentInfoRest1();

        for (int i=0; i<jsonArray.length();i++){
            try {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                contentInfoModel.populateContentInfo(jsonObject);
                mContentInfoList.add(contentInfoModel);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return mContentInfoList;

    }

//  Getting the object from rest and passing it to ContentInfoModel

    public List<ContentViewModels> getContentViewsFromRest1(){

        ContentViewModels contentViewModel = new ContentViewModels();
        JSONArray jsonArray1 = mContentListServiceHandler.getContentViewRest1();
        for (int i=0; i<jsonArray1.length();i++){
            try {
                JSONObject jsonObject1 = jsonArray1.getJSONObject(i);
                contentViewModel.populateContentViews(jsonObject1);
                mContentViewList.add(contentViewModel);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return mContentViewList;
    }
}
