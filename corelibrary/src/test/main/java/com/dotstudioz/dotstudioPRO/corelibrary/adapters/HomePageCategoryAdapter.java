package com.dotstudioz.dotstudioPRO.corelibrary.adapters;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dotstudioz.dotstudioPRO.corelibrary.R;
import com.dotstudioz.dotstudioPRO.services.constants.FontsConstants;
import com.dotstudioz.dotstudioPRO.models.dto.HomePageCategoryItem;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by Admin on 22-11-2014.
 */
public class HomePageCategoryAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<HomePageCategoryItem> homePageCategoryItemList;

    public HomePageCategoryAdapter(Activity activity, List<HomePageCategoryItem> homePageCategoryItemList) {
        this.activity = activity;
        this.homePageCategoryItemList = homePageCategoryItemList;
    }

    @Override
    public int getCount() {
        return homePageCategoryItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return homePageCategoryItemList.get(position);
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
            convertView = inflater.inflate(R.layout.test_item_1, null);
        }

        TextView categoryName = (TextView) convertView.findViewById(R.id.text1);
        categoryName.setTypeface(FontsConstants.tfBold);
        SimpleDraweeView categoryImage = (SimpleDraweeView) convertView.findViewById(R.id.image);

        HomePageCategoryItem homePageCategoryItem = homePageCategoryItemList.get(position);
        Uri uri = Uri.parse(homePageCategoryItem.getImageURL());
        categoryImage.setImageURI(uri);
        categoryName.setText(homePageCategoryItem.getVideoId());

        return convertView;
    }
}
