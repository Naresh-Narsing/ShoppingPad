package com.shoppingpad.viewmodel;

/**
 * Created by bridgelabz5 on 6/3/16.
 * <p>
 * Purpose:
 * 1.This Class Is The ViewModel Of MVVM Design Pattern.
 * 2.Holding The Model Required For The Content View List.
 * 3.This Class Has The ContentListController Object To Retrieve The Necessary
 * Model.
 * 4.This Class Has Attribute Like ContentImg,ContentTitle,LastView Date Time,
 * etc.
 *
 *
 *
 * Carries The Required Field Data To The View That Is If You Want Data From
 * Multiple Tables.
 * As In Eg:
 * From Our Project We ContentModel And ContentViewModels Both Require Different
 * Data So Over
 * Here We Can Take The Necessary Data From Both And Pass It To The View.
 */


import android.content.Context;
import android.util.Log;

import com.shoppingpad.controller.ContentListInfo;
import com.shoppingpad.controller.ContentListViewControler;
import com.shoppingpad.model.ContentInfoModel;
import com.shoppingpad.model.ContentViewModels;

import java.util.ArrayList;
import java.util.List;

public class ContentListViewModel {

    //unit test variable
    public final static boolean mPERFORM_UNIT_TEST = false;

    //Holds the list
    List<ContentViewModel> mContentInfoList;

    List<ContentListInfo> listInfos;

    //Controller Object
     ContentListViewControler mContentListViewControler;



    //Populating Dummy Data
    public ContentListViewModel(Context context) {
        if (mPERFORM_UNIT_TEST) {
            mContentInfoList = populateDummyData();
        }
        else {
            mContentInfoList = new ArrayList<>();
            mContentListViewControler = new ContentListViewControler(context);
            //getContentViewDetails();
        }
    }

    //code for populating the dummy data in content

    //SETTING CONTENTLISTINFO ATTRIBUTES IN CONTENTVIEWMODEL ATTRIBUTES.
    public List<ContentViewModel> populateDummyData() {

        List<ContentViewModel> data = new ArrayList<>();

        /*int icon[] = {R.drawable.ic_number1, R.drawable.ic_number2, R.drawable.
                ic_number3};
        String title[] =
        String lastViewDateTime[] = {"18-5-15", "10-2-16", "18-5-15"};
        int numberOfViews[] = {10, 20, 30};
        int numberOfParticipants[] = {50, 20, 10};*/

        for (int i = 1; i <= listInfos.size(); i++)
        {
            ContentListInfo info = listInfos.get(i);
            ContentViewModel current = new ContentViewModel();
            current.mTitle = info.mControllerTitle;
            current.mImageLink = info.mControllerImageLink;
            current.mLastViewDateTime = info.mControllerLastViewDateTime;
            current.mNumberOfViews = info.mControllerNumberOfViews;
            current.mNumberOfParticipants = info.mControllerNumberOfParticipants;
            data.add(current);
        }
        return data;
    }

    public ContentViewModel getContentViewPosition(int position)
    {
        return mContentInfoList.get(position);
    }

    public int getContentViewSize()
    {
        return mContentInfoList.size();
    }

//Method which will get the list from both contentlistinfo and contentviews

    public List<ContentViewModel> getContentViewDetails()
    {
        List<ContentInfoModel> contentInfo =  mContentListViewControler.
                                              getContentInfoFromRests();
        List<ContentViewModels> contentViews = mContentListViewControler.
                                               getContentViewsFromRest1();
        for (int i = 0; i < contentInfo.size(); i++){
            ContentViewModel contentViewModel = new ContentViewModel();
            contentViewModel.mContentId = contentInfo.get(i).mContentId;
            contentViewModel.mTitle = contentInfo.get(i).mTitle;
            contentViewModel.mNumberOfViews = contentViews.get(i)
                                          .mNumberOfViews;
            contentViewModel.mLastViewDateTime = contentViews.get(i)
                                          .mLastSeenDateTime;
            contentViewModel.mNumberOfParticipants = contentViews.get(i)
                                          .mNumberOfParticipants;
            mContentInfoList.add(contentViewModel);
        }
        for (int i = 0; i < mContentInfoList.size(); i++){
            ContentViewModel contentViewModel = mContentInfoList.get(i);
            for (int j = 0; j < contentViews.size(); j++){
                ContentViewModels contentViewModels = contentViews.get(j);
                if (contentViewModel.mContentId == contentViewModels.mContentId)
                {
                    contentViewModel.mLastViewDateTime = contentViewModels.mLastSeenDateTime;
                    contentViewModel.mNumberOfViews = contentViewModels.mNumberOfViews;
                    contentViewModel.mNumberOfParticipants = contentViewModels.mNumberOfParticipants;
                }
            }
        }
        Log.e("SIZE", "SIZE" + mContentInfoList.size());
        return mContentInfoList;

    }
}

