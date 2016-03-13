package com.shoppingpad.model;

import android.util.Log;

import org.json.JSONObject;

/**
 * Created by bridgelabz5 on 6/3/16.
 *
 * Purpose:
 * It Will Contains The Data Object Only WhereIn If You Declare The Object
 * Private You Need To Use Getter And Setter.It Will Have The State And
 * Behaviour Of The Class.
 */
public class ContentInfoModel
{
    //Attributes
    public String mModifiedAt;
    public String mCreatedAt;
    public String mSyncDateTime;
    public String mDescription;
    public String mContentLink;
    public String mUrl;
    public String mContentType;
    public String mTitle;                   //Holds Content Title.
    public String mImageLink;               //ThumbView Link ie dp link.
    public int    mContentId;


    public ContentInfoModel() {

    }

//  Getting the Json object from Controller and populating the model
//  and passing the list again to the controller

    public void populateContentInfo(JSONObject contentInfoObject){

        mImageLink =contentInfoObject.optString("imagesLink").toString();
        mContentId = Integer.parseInt
                (contentInfoObject.optString("content_id").toString());
        mTitle = contentInfoObject.optString("display_name").
                toString();
        mModifiedAt = contentInfoObject.optString("modified_at").
                toString();
        mCreatedAt = contentInfoObject.optString("created_at").
                toString();
        mSyncDateTime = contentInfoObject.optString("syncDateTime").
                toString();
        mDescription = contentInfoObject.optString("decription").
                toString();
        mContentLink = contentInfoObject.optString("contentLink").
                toString();
        mUrl = contentInfoObject.optString("url").toString();
        mContentType = contentInfoObject.optString("contentType").toString();
    }
}
