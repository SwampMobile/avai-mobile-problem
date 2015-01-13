package com.swampmobile.avaimobileproblem.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.swampmobile.avaimobileproblem.R;
import com.swampmobile.avaimobileproblem.app.net.models.DuckDuckGoRelatedTopic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by matt on 1/12/15.
 */
public class RelatedTopicsAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<DuckDuckGoRelatedTopic> mRelatedTopics;

    // Optimizations
    private DuckDuckGoRelatedTopic mRelatedTopic;
    private Holder mHolder;

    public RelatedTopicsAdapter(Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mRelatedTopics = new ArrayList<DuckDuckGoRelatedTopic>(0);
    }

    public void setData(List<DuckDuckGoRelatedTopic> data) {
        mRelatedTopics = data;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mRelatedTopics.size();
    }

    @Override
    public DuckDuckGoRelatedTopic getItem(int position) {
        return mRelatedTopics.get(position);
    }

    @Override
    public long getItemId(int position) {
        // doesn't matter for us.
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // I usually create custom Views rather than inflate layouts here, but that seemed like
        // overkill in this instance.

        if (null == convertView) {
            convertView = mLayoutInflater.inflate(R.layout.listitem_related_topic, null);

            mHolder = new Holder();
            mHolder.mNumberTextView = (TextView) convertView.findViewById(R.id.textview_number);
            mHolder.mContentTextView = (TextView) convertView.findViewById(R.id.textview_content);
            convertView.setTag(mHolder);
        }

        mRelatedTopic = getItem(position);

        mHolder = (Holder) convertView.getTag();
        // Seems that icons are never returned when disambigous results are "on"
//        if (mRelatedTopic.hasIcon()) {
//            Picasso.with(mContext).load(mRelatedTopic.getIcon().getUrl()).into(mHolder.mNumberTextView);
//        }
        mHolder.mNumberTextView.setText((position + 1) + "");
        mHolder.mContentTextView.setText(mRelatedTopic.getText());

        return convertView;
    }

    private static class Holder {
        public TextView mNumberTextView;
        public TextView mContentTextView;
    }
}
