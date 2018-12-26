package com.dotstudioz.dotstudioPRO.corelibrary.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dotstudioz.dotstudioPRO.corelibrary.R;
import com.dotstudioz.dotstudioPRO.services.constants.FontsConstants;
import com.dotstudioz.dotstudioPRO.models.dto.SearchCategoryItem;

import java.util.List;

/**
 * Created by Admin on 19-11-2014.
 */
public class SearchCategoryAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<SearchCategoryItem> searchCategoryItemList;

    public SearchCategoryAdapter(Activity activity, List<SearchCategoryItem> searchCategoryItemList) {
        this.activity = activity;
        this.searchCategoryItemList = searchCategoryItemList;
    }

    @Override
    public int getCount() {
        return searchCategoryItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return searchCategoryItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(convertView == null) {
            convertView = inflater.inflate(R.layout.category_item, null);
            //convertView.setLayoutParams(new AbsListView.LayoutParams(AbsListView.LayoutParams.WRAP_CONTENT, AbsListView.LayoutParams.WRAP_CONTENT));
        }

        TextView categoryName = (TextView) convertView.findViewById(R.id.categoryName);
        ImageView categoryImage = (ImageView) convertView.findViewById(R.id.categoryImage);
        categoryName.setTypeface(FontsConstants.tfBold);

        SearchCategoryItem searchCategoryItem = searchCategoryItemList.get(position);
        categoryImage.setImageResource(R.drawable.category_search_button);
        categoryName.setText(searchCategoryItem.getCategory());

        if (position % 2 == 1) {
            convertView.setBackgroundColor(Color.BLACK);
        } else {
            convertView.setBackgroundColor(Color.parseColor("#101010"));
        }

        return convertView;
    }
}
