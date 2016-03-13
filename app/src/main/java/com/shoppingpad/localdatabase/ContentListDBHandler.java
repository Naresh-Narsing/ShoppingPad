package com.shoppingpad.localdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.shoppingpad.model.ContentInfoModel;


import java.util.List;

/**
 * Created by bridgelabz5 on 6/3/16.
 *
 * Purpose:
 * It Will Maintain Local Db Wherein All Get And Set Methods Will Be Written
 * Which In Turn Returns The Data.Also Whenever The SyncDate Is Changed this
 * Db Will Get Updated Accordingly.It Will Alo Fetch the Data To The User
 * Whenever There Is No Internet Connectivity From The Users End.
 */
public class ContentListDBHandler extends SQLiteOpenHelper {

    SQLiteDatabase db;
    public static final String DB_NAME = "ShoppingPad";
    public static final int DB_VERSION = 1;

    public static final String CONTENT_INFO = "Content_Info";
    public static final String CONTENT_VIEW = "Content_View";


    public static final String UserContentViews = "CREATE TABLE "+ CONTENT_VIEW +
            "(contentId INT PRIMARY KEY,noOfParticipants INT,noOfViews INT,lastSeenDateTime VARCHAR(24))";

    public static final String UserContentInfo ="CREATE TABLE "+ CONTENT_INFO +
            "(contentId INT PRIMARY KEY,Title VARCHAR(20)" +
            ",display_name VARCHAR(20),description VARCHAR(24)," +"contentTags " +
            "VARCHAR(20),imageLink VARCHAR(20),contentLink VARCHAR(20),syncDateTime" +
            " VARCHAR(20))";

    public static final String CONTENT_ID = "contentId";
    public static final String TITLE = "Title";
    public static final String IMAGE_LINK = "imageLink";
    public static final String NO_OF_PARTICIPANTS = "noOfParticipants";
    public static final String NO_OF_VIEWS = "noOfViews";
    public static final String LAST_SEEN_DATE_TIME = "lastSeenDateTime";

    public ContentListDBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UserContentViews);
        db.execSQL(UserContentInfo);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+CONTENT_INFO);
        db.execSQL("DROP TABLE IF EXISTS "+CONTENT_VIEW);
        onCreate(db);
    }

    public boolean setContentInfo(int contentId,String Title, int imageLink){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues c1 = new ContentValues();
        c1.put(CONTENT_ID,contentId);
        c1.put(TITLE,Title);
        c1.put(IMAGE_LINK,imageLink);
        long res= db.insert(CONTENT_INFO,null,c1);
        if(res == -1)
            return false;
        else
            return true;
    }

    public boolean setContentViews(int Content_id, String lastViewedDateTime,
                                   String numberOfViews,String participants)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues c2 = new ContentValues();
        c2.put(CONTENT_ID,Content_id);
        c2.put(NO_OF_VIEWS,numberOfViews);
        c2.put(LAST_SEEN_DATE_TIME,lastViewedDateTime);
        c2.put(NO_OF_PARTICIPANTS,participants);
        long res= db.insert(CONTENT_VIEW,null,c2);
        if(res == -1)
            return false;
        else
            return true;
    }

//    Retrieving the ContentInfo attribute from List and setting
//    in database
//    public void setContentInfoData(List<ContentInfoModel> info)
//    {
//        for (int i = 0;i<info.size();i++){
//            setContentInfo(info.get(i).mContentId, info.get(i).mTitle,
//                    info.get(i).mImageLink);
//        }
//    }

//    Retrieving the ContentViews attribute from List and setting
//    in database
//    public void setContentViewData(List<ContentInfoModel> viewData)
//    {
//        for (int i = 0;i < viewData.size();i++){
//            setContentViews(viewData.get(i).mContentId,viewData.get(i).
//                            mLastViewDateTimeM,
//                    viewData.get(i).mNumberOfViewsM,viewData.get(i).
//                            mNumberOfParticipantsM);
//        }
//    }


}
