package com.shoppingpad.restservice;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bridgelabz5 on 6/3/16.
 *
 * Purpose:
 * This Will Call The RestService That Is The Server And Fetch The Data From
 * Server Which In turn Will Return To The Controller
 */
public class ContentListServiceHandler {

    List<ContentRestModel> mContentInfoRest;
    List<ContentRestModel> mContentViewsRest;

    static JSONArray jarray = null;

    public static final String URL_CONTENT_INFO =
                           "http://52.90.50.117:3046/api/v1/content_info";
    public static final String URL_CONTENT_VIEWS =
                           "http://52.90.50.117:3046/api/v1/user_content_view";

    public static final boolean mPERFORM_UNIT_TEST = false;

    public ContentListServiceHandler() {
        if (mPERFORM_UNIT_TEST) {
            mContentInfoRest = new ArrayList<>();
            mContentViewsRest = new ArrayList<>();
            populateDummyData();
        }
    }

    public void populateDummyData() {
        getContentInfoRest();
        getContentViewRest();
    }

    //    populate ContentInfo using jsonarray
    public List<ContentRestModel> getContentInfoRest() {
        String strJson = " {\"ContentInfo\" :[{\"cId\":\"0\", \"name\":\"Gopal " +
                "Varma\",\"imagePath\":\"1\"}]}";

        try {
            JSONObject jsonRootObject = new JSONObject(strJson);
            JSONArray jsonArray = jsonRootObject.optJSONArray("ContentInfo");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                ContentRestModel contentRestModel = new ContentRestModel();
                contentRestModel.mRestTitle = jsonObject.optString("name").
                        toString();
                contentRestModel.mRestImageLink = Integer.parseInt
                        (jsonObject.optString("imagePath").toString());
                contentRestModel.mContentId = Integer.parseInt
                        (jsonObject.optString("cId").toString());
                mContentInfoRest.add(contentRestModel);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mContentInfoRest;
    }

    //    populate ContentViewRest using jsonarray
    public List<ContentRestModel> getContentViewRest() {
        String strJson1 = " {\"ContentViews\" :[{\"cId\":\"0\", \"lastSeenDate" +
                "Time\":\"Gopal Varma\",\"noOfParticipants\":\"20\"," +
                "\"noOfViews\":\"50\"}]}";
        try {
            JSONObject jsonRootObject = new JSONObject(strJson1);
            JSONArray jsonArray = jsonRootObject.optJSONArray("ContentViews");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                ContentRestModel contentRestModel1 = new ContentRestModel();
                contentRestModel1.mContentId = Integer.parseInt(jsonObject.
                        optString("cId").toString());
                contentRestModel1.mRestLastViewDateTime = jsonObject.
                        optString("lastSeenDateTime");
                contentRestModel1.mRestNumberOfViews = Integer.parseInt
                        (jsonObject.optString("noOfViews"));
                contentRestModel1.mRestNumberOfParticipants = Integer.parseInt
                        (jsonObject.optString("noOfParticipants"));
                mContentViewsRest.add(contentRestModel1);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mContentViewsRest;
    }


//    Populate ContentInfo Dummy Data
   /* public List<ContentRestModel> getContentInfoRest() {
        int contentIdR[] = {0,1,2};
        int iconR[] = {R.drawable.ic_number1, R.drawable.ic_number2, R.drawable.
                ic_number3};
        String titleR[] = {"ABCD", "XYZ", "PQRS"};
        for (int i = 0; i<contentIdR.length && i<iconR.length && i<titleR.length
                ; i++)
        {
            ContentRestModel contentRestModel = new ContentRestModel();
            contentRestModel.mContentId = contentIdR[i];
            contentRestModel.mRestImageLink = iconR[i];
            contentRestModel.mRestTitle = titleR[i];
            mContentInfoRest.add(contentRestModel);
        }
        return mContentInfoRest;
    }

    //  Populate ContentView Dummy Data
    public List<ContentRestModel> getContentViewRest() {
        int contentIdR[] = {0,1,2};
        String lastViewDateTimeR[] = {"18-5-15", "10-2-16", "18-5-15"};
        int numberOfViewsR[] = {10, 20, 30};
        int numberOfParticipantsR[] = {50, 20, 10};
        for (int i = 0; i < lastViewDateTimeR.
                length && i < numberOfViewsR.length && i < numberOfParticipantsR.
                length; i++)
        {
            ContentRestModel data2 = new ContentRestModel();
            data2.mContentId = contentIdR[i];
            data2.mRestLastViewDateTime = lastViewDateTimeR[i];
            data2.mRestNumberOfParticipants = numberOfParticipantsR[i];
            data2.mRestNumberOfViews = numberOfViewsR[i];
            mContentViewsRest.add(data2);
        }
        return mContentViewsRest;
    }*/

//  Populating contentinfo with json and passing jsonobject to the controller

    public JSONArray getContentInfoRest1() {
        JSONArray json1 = getJSONFromUrl(URL_CONTENT_INFO);
        return json1;
    }

//  Populating contentViews with json and passing jsonObject to the controller

    public JSONArray getContentViewRest1(){
        JSONArray json2 = getJSONFromUrl(URL_CONTENT_VIEWS);
        return json2;
     }

//  Getting json from url

    public JSONArray getJSONFromUrl(String url){
        StringBuilder builder = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);

        try {
            HttpResponse response = client.execute(httpGet);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode == 200){
                HttpEntity entity= response.getEntity();
                InputStream content = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader
                                                          (content));
                String line;
                while ((line = reader.readLine())!=null ){
                    builder.append(line);
                }
            }
            else {
                Log.e("===>", "Failed To Download File");
            }
        }catch (ClientProtocolException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        try {
            jarray = new JSONArray(builder.toString());
        }catch (JSONException e){
            Log.e("JSON PARSER", "ERROR PARSING DATA " + e.toString());
        }
        return  jarray;
    }
}

