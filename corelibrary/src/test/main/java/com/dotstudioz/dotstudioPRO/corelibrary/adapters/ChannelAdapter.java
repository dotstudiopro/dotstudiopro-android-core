package com.dotstudioz.dotstudioPRO.corelibrary.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dotstudioz.dotstudioPRO.corelibrary.R;
import com.dotstudioz.dotstudioPRO.models.dto.CategoriesDTO;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by Admin on 05-12-2014.
 */
public class ChannelAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<CategoriesDTO> channelItemDTOList;
    private DisplayMetrics displayMetrics;

    public ChannelAdapter(Activity activity, List<CategoriesDTO> channelItemDTOList, DisplayMetrics displaymetrics) {
        this.activity = activity;
        this.channelItemDTOList = channelItemDTOList;
        this.displayMetrics = displaymetrics;
    }

    @Override
    public int getCount() {
        return channelItemDTOList.size();
    }

    @Override
    public Object getItem(int position) {
        return channelItemDTOList.get(position);
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
            convertView = inflater.inflate(R.layout.channel_item, null);

        //NetworkImageView videoImage = (NetworkImageView) convertView.findViewById(R.id.channelItemImage);
        SimpleDraweeView videoImage = (SimpleDraweeView) convertView.findViewById(R.id.channelItemImage);
        TextView channelItemTextView = (TextView) convertView.findViewById(R.id.channelItemTextView);

        CategoriesDTO channelItemDTO = channelItemDTOList.get(position);
        //videoImage.setImageUrl(channelItemDTO.getVideoImageURL(), imageLoader);
        //Picasso.with(activity).load(categories.getVideoImageURL()).into(videoImage);

        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        int imageHeight = ((width * 9) / 16);

        String imageString = ((CategoriesDTO) channelItemDTOList.get(position)).getChannelSpotlightImage();
        imageString = CommonUtils.replaceDotstudioproWithMyspotlightForImage(imageString);
        //System.out.println("ImageData:"+imageString);

        String imageURLAddress = imageString + "/" + width + "/" + imageHeight;
        //Picasso.with(this).load(imageURLAddress).into(iv1);
        //iv1.setImageUrl(imageURLAddress, imageLoader);
        Uri uri = Uri.parse(imageURLAddress);
        videoImage.setImageURI(uri);

        channelItemTextView.setText(channelItemDTO.getChannelName());

        if (position % 2 == 1) {
            convertView.setBackgroundColor(Color.BLACK);
        } else {
            convertView.setBackgroundColor(Color.parseColor("#101010"));
        }

        return convertView;
    }
}
