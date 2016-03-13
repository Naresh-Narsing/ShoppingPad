package com.shoppingpad.model;

import org.json.JSONObject;

/**
 * Created by bridgelabz5 on 13/3/16.
 */
public class ContentViewModels {

    public String mNumberOfViews;
    public String mNumberOfParticipants;
    public String mLastSeenDateTime;
    public int mContentId;
    public String mLastName;
    public String mFirstName;
    public String mEmail;
    public String mMobileNo;
    public int mUserContentId;

    public ContentViewModels() {

    }


//  Getting the Json object from Controller and populating the model
//  and passing the list again to the controller

    public void populateContentViews(JSONObject contentViewObject) {

        mContentId = Integer.parseInt
                (contentViewObject.optString("content_id").toString());
        mLastSeenDateTime = contentViewObject.
                optString("lastViewedDateTime");
        mNumberOfParticipants =
                (contentViewObject.optString("numberOfViews"));
        mNumberOfViews =
                (contentViewObject.optString("numberOfViews"));
        mUserContentId = Integer.parseInt(contentViewObject.optString
                ("userContentId").toString());
        mMobileNo = contentViewObject.optString("mobile").toString();
        mEmail = contentViewObject.optString("email").toString();
        mFirstName = contentViewObject.optString("firstName").toString();
        mLastName = contentViewObject.optString("lastName").toString();


    }
}