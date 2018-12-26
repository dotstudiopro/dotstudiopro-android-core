package com.dotstudioz.dotstudioPRO.corelibrary.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dotstudioz.dotstudioPRO.corelibrary.R;
import com.dotstudioz.dotstudioPRO.corelibrary.constants.FontsConstants;

import java.util.List;

/**
 * Created by Admin on 07-05-2015.
 */
public class MenusAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<String> menusItemList;

    public MenusAdapter(Activity activity, List<String> menusItemList) {
        this.activity = activity;
        this.menusItemList = menusItemList;
    }

    @Override
    public int getCount() {
        return menusItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return menusItemList.get(position);
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
            convertView = inflater.inflate(R.layout.menu_item, parent, false);

        TextView username = (TextView) convertView.findViewById(R.id.menuItemName);
        username.setTypeface(FontsConstants.tfBold);

        String menusItem = menusItemList.get(position);
        username.setText(menusItem);

        /*if (position % 2 == 1) {
            convertView.setBackgroundColor(Color.BLACK);
        } else {
            convertView.setBackgroundColor(Color.parseColor("#101010"));
        }*/

        return convertView;
    }
}
