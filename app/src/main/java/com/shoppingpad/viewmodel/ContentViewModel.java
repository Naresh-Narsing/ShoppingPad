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

//Defining Dafault class which will hold content list view
public class ContentViewModel
{
    //Attributes
    public String mTitle;                      //Holds Content Title.
    public int    mImageLink;                  //ThumbView Link i.e dp link.
    public String mLastViewDateTime;           //Last seen Date and Time.
    public String mNumberOfViews;              //Total No. of Views.
    public String mNumberOfParticipants;       //Total No. of participants.
    public int    mContentId;
}