package com.dotstudioz.dotstudioPRO.corelibrary.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dotstudioz.dotstudioPRO.corelibrary.R;
import com.dotstudioz.dotstudioPRO.services.constants.FontsConstants;
import com.dotstudioz.dotstudioPRO.models.dto.SearchResultItem;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by Admin on 19-11-2014.
 */
public class SearchResultItemAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<SearchResultItem> searchResultItemsList;

    public SearchResultItemAdapter(Activity activity, List<SearchResultItem> searchResultItemsList) {
        this.activity = activity;
        this.searchResultItemsList = searchResultItemsList;
    }

    @Override
    public int getCount() {
        return searchResultItemsList.size();
    }

    @Override
    public Object getItem(int position) {
        return searchResultItemsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(convertView == null)
            convertView = inflater.inflate(R.layout.search_result_row, null);

        SimpleDraweeView thumbnail = (SimpleDraweeView) convertView.findViewById(R.id.thumbnail);
        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView seasonInfo = (TextView) convertView.findViewById(R.id.seasonInfo);

        title.setTypeface(FontsConstants.tfBold);
        seasonInfo.setTypeface(FontsConstants.tfRegular);

        SearchResultItem searchResultItem = searchResultItemsList.get(position);
        Uri uri = Uri.parse(searchResultItem.getImageURL());
        thumbnail.setImageURI(uri);
        title.setText(searchResultItem.getTitle());
        seasonInfo.setText(searchResultItem.getSeasonInfo());

        if (position % 2 == 1) {
            convertView.setBackgroundColor(Color.BLACK);
        } else {
            convertView.setBackgroundColor(Color.parseColor("#101010"));
        }

        return convertView;
    }
}
