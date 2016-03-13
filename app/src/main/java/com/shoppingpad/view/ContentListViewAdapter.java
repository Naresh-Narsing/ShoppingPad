package com.shoppingpad.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shoppingpad.R;
import com.shoppingpad.viewmodel.ContentListViewModel;
import com.shoppingpad.viewmodel.ContentViewModel;

import java.util.List;

/**
 * Created by bridgelabz5 on 6/3/16.
 */
public class ContentListViewAdapter
             extends RecyclerView.Adapter<ContentListViewAdapter.ContentViewHolder>
{
    public LayoutInflater mInflater;
    public Context mContext;
    public ContentListViewModel mContentListViewModel;
    public ContentViewModel mContentViewModel;

    public ContentListViewAdapter(Context context, ContentListViewModel contentListViewModel){
        mContext = context;
        mContentListViewModel = contentListViewModel;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public ContentViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = mInflater.inflate(R.layout.content_view_rows,parent,false);
        ContentViewHolder holder = new ContentViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ContentViewHolder holder, int position)
    {
        mContentViewModel = mContentListViewModel.getContentViewPosition(position);
        holder.thumbView.setImageResource(mContentViewModel.mImageLink);
        holder.title.setText(mContentViewModel.mTitle);
        holder.lastSeenDateTime.setText(mContentViewModel.mLastViewDateTime);
        holder.noOfParticipant.setText(mContentViewModel.mNumberOfParticipants);
        holder.noOfViews.setText(mContentViewModel.mNumberOfViews);
    }

    @Override
    public int getItemCount() {
        return mContentListViewModel.getContentViewSize();


    }

    //Holder Class For Representing All The TextView And ImageView In A
    // Particular Row List
    public class ContentViewHolder extends RecyclerView.ViewHolder
    {
        TextView title, noOfViews, noOfParticipant, lastSeenDateTime;
        ImageView thumbView;
        public ContentViewHolder(View itemView) {
            super(itemView);
            thumbView        = (ImageView) itemView.findViewById(R.id.
                                thumbView);
            title            = (TextView) itemView.findViewById(R.id.
                                title);
            noOfViews        = (TextView) itemView.findViewById(R.id.
                                noOfViews);
            noOfParticipant  = (TextView) itemView.findViewById(R.id.
                                noOfParticipants);
            lastSeenDateTime = (TextView) itemView.findViewById(R.id.
                                lastSeenDateTime);
        }
    }
}
