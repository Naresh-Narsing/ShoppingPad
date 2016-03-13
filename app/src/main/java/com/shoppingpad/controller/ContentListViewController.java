package com.shoppingpad.controller;

import com.shoppingpad.R;
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
public class ContentListViewController {

    //Holds the list
    public List<ContentListInfo> dataC;

    public final static boolean mPerformUnitTest = true;

    //Creating Constructor Of ContentListViewController
    public ContentListViewController() {
        dataC  = new ArrayList<>();
        populateDummyContentInfoData1();
    }

    //THIS METHOD INITIALIZES CONTENTLISTINFO AND SET ITS 2 PROPERTY
    // AND PASSES IN TO ANOTHER METHOD.

    public void populateDummyContentInfoData1() {
        int iconC[] = {R.drawable.ic_number1, R.drawable.ic_number2, R.drawable.
                ic_number3};
        String titleC[] = {"ABCD", "XYZ", "PQRS"};
        for (int i = 0; i < iconC.length && i < titleC.length; i++)
        {
            ContentListInfo currentC =  new ContentListInfo();
            currentC.mControllerImageLink = iconC[i];
            currentC.mControllerTitle = titleC[i];
            populateDummyContentViewData2(currentC);
        }
    }

    //IT ACCEPTS THE OBJECT OF CONTENTLISTINFO WITH 2 PROPERTY INITIALIZED
    //AND SETS REMAINIMG PROPERTY AND ADD IT IN TO THE LIST AND RETURNS IT.

    public List<ContentListInfo> populateDummyContentViewData2(ContentListInfo currentC2)
    {
        String lastViewDateTimeC[] = {"18-5-15", "10-2-16", "18-5-15"};
        String numberOfViewsC[] = {"10 Views","20 Views","30 Views"};
        String numberOfParticipantsC[] = {"50 Participants", "20 Participants",
                "10 Participants"};
        for (int i = 0; i < lastViewDateTimeC.
                length && i < numberOfViewsC.length && i < numberOfParticipantsC.
                length; i++)
        {
            currentC2.mControllerLastViewDateTime = lastViewDateTimeC[i];
            currentC2.mControllerNumberOfViews = numberOfViewsC[i];
            currentC2.mControllerNumberOfParticipants = numberOfParticipantsC[i];
            dataC.add(currentC2);
        }
        return dataC;
    }
}
