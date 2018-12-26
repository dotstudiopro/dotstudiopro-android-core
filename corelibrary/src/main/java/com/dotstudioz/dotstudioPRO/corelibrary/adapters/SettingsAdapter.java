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
import com.dotstudioz.dotstudioPRO.corelibrary.constants.FontsConstants;
import com.dotstudioz.dotstudioPRO.models.dto.SettingsItem;

import java.util.List;

/**
 * Created by Admin on 12-12-2014.
 */
public class SettingsAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<SettingsItem> settingsItemList;

    public SettingsAdapter(Activity activity, List<SettingsItem> settingsItemList) {
        this.activity = activity;
        this.settingsItemList = settingsItemList;
    }

    @Override
    public int getCount() {
        return settingsItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return settingsItemList.get(position);
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
            convertView = inflater.inflate(R.layout.settings_item, null);

        ImageView itemImage = (ImageView) convertView.findViewById(R.id.settingItemImage);
        TextView username = (TextView) convertView.findViewById(R.id.settingItemName);
        username.setTypeface(FontsConstants.tfBold);

        SettingsItem settingsItem = settingsItemList.get(position);
        if(settingsItem.getItemName().equals("My Account"))
            itemImage.setImageResource(R.drawable.my_account);
        else if(settingsItem.getItemName().equals("My Purchases"))
            itemImage.setImageResource(R.drawable.my_purchases);
        else if(settingsItem.getItemName().equals("Preference"))
            itemImage.setImageResource(R.drawable.preference);
        else if(settingsItem.getItemName().equals("Help"))
            itemImage.setImageResource(R.drawable.help);
        else if(settingsItem.getItemName().equals("Privacy Policy"))
            itemImage.setImageResource(R.drawable.privacy_policy);
        else if(settingsItem.getItemName().equals("Log Out"))
            itemImage.setImageResource(R.drawable.logout);

        username.setText(settingsItem.getItemName());

        convertView.setBackgroundColor(Color.parseColor("#151415"));

        /*if (position % 2 == 1) {
            convertView.setBackgroundColor(Color.BLACK);
        } else {
            convertView.setBackgroundColor(Color.parseColor("#101010"));
        }*/

        return convertView;
    }
}
