package com.dotstudioz.dotstudioPRO.corelibrary.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dotstudioz.dotstudioPRO.corelibrary.R;
import com.dotstudioz.dotstudioPRO.services.constants.ApplicationConstants;
import com.dotstudioz.dotstudioPRO.services.constants.FontsConstants;
import com.dotstudioz.dotstudioPRO.models.dto.IndicatorDTO;
import com.dotstudioz.dotstudioPRO.models.dto.SpotLightCategoriesDTO;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;
import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;

import java.util.List;

/**
 * Created by Admin on 03-12-2014.
 */
public class IndicatorAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<IndicatorDTO> indicatorDTOList;

    private View convertViewInstance;

    public int imageWidth;
    public int imageHeight;

    public int nonActiveColor = Color.parseColor("#eeeeee");
    public int activeColor = Color.parseColor("#6dec68");

    public IndicatorAdapter(Activity activity, List<IndicatorDTO> indicatorDTOList) {
        this.activity = activity;
        this.indicatorDTOList = indicatorDTOList;
    }

    @Override
    public int getCount() {
        return indicatorDTOList.size();
    }

    @Override
    public Object getItem(int position) {
        return indicatorDTOList.get(position);
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
            convertView = inflater.inflate(R.layout.indicator_item, parent, false);
            convertViewInstance = convertView;
        }

        try {
            ((AbsListView.LayoutParams)convertView.getLayoutParams()).height = imageHeight * 2;
        } catch(Exception e) {
            e.printStackTrace();
        }

        try {
            ((RelativeLayout.LayoutParams)convertView.findViewById(R.id.simpleDraweeView).getLayoutParams()).width = imageWidth;
            ((RelativeLayout.LayoutParams)convertView.findViewById(R.id.simpleDraweeView).getLayoutParams()).height = imageHeight;
            ((RelativeLayout.LayoutParams)convertView.findViewById(R.id.simpleDraweeView).getLayoutParams()).setMargins(imageWidth, 0, 0, 0);
            //convertView.findViewById(R.id.simpleDraweeView).setPadding(imageWidth/2, 0, 0, 0);

            ((SimpleDraweeView)convertView.findViewById(R.id.simpleDraweeView)).setImageDrawable(new IconDrawable(activity, FontAwesomeIcons.fa_circle).color(nonActiveColor));
        } catch(Exception e) {
            e.printStackTrace();
        }

        try {
            if(indicatorDTOList.get(position).isActive())
                ((SimpleDraweeView)convertView.findViewById(R.id.simpleDraweeView)).setImageDrawable(new IconDrawable(activity, FontAwesomeIcons.fa_circle).color(activeColor));
            else
                ((SimpleDraweeView)convertView.findViewById(R.id.simpleDraweeView)).setImageDrawable(new IconDrawable(activity, FontAwesomeIcons.fa_circle).color(nonActiveColor));
        } catch(Exception e) {
            e.printStackTrace();
        }

        return convertView;
    }

    public void setThisAsActive() {
        ((SimpleDraweeView)convertViewInstance.findViewById(R.id.simpleDraweeView)).setImageDrawable(new IconDrawable(activity, FontAwesomeIcons.fa_circle).color(activeColor));
    }
}
