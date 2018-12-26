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
import android.widget.ImageView;
import android.widget.TextView;

import com.dotstudioz.dotstudioPRO.corelibrary.R;
import com.dotstudioz.dotstudioPRO.services.constants.ApplicationConstants;
import com.dotstudioz.dotstudioPRO.corelibrary.constants.FontsConstants;
import com.dotstudioz.dotstudioPRO.models.dto.SeasonItem;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;
import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Admin on 03-12-2014.
 */
public class SeasonAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<SeasonItem> seasonItemsList;
    public GenericDraweeHierarchy hierarchy;
    private DisplayMetrics displayMetrics = new DisplayMetrics();

    public SeasonAdapter(Activity activity, List<SeasonItem> seasonItemsList) {
        this.activity = activity;
        this.seasonItemsList = seasonItemsList;
    }

    @Override
    public int getCount() {
        return seasonItemsList.size();
    }

    @Override
    public Object getItem(int position) {
        return seasonItemsList.get(position);
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
            convertView = inflater.inflate(R.layout.episodes_list_item_view, parent, false);
        }


        SeasonItem seasonItem = seasonItemsList.get(position);

        if(seasonItem.isSelected())
            ((ImageView)convertView.findViewById(R.id.seasonPlayImage)).setImageDrawable(new IconDrawable(activity, FontAwesomeIcons.fa_play_circle_o).color(Color.parseColor("#4a9763")));
        else
            ((ImageView)convertView.findViewById(R.id.seasonPlayImage)).setImageDrawable(new IconDrawable(activity, FontAwesomeIcons.fa_play_circle_o).color(Color.parseColor("#eeeeee")));

        TextView seasonName = (TextView) convertView.findViewById(R.id.seasonName);
        seasonName.setTypeface(FontsConstants.tfBold);


        //seasonItem.setSeasonName(seasonItem.getSeasonName());

        seasonName.setText(seasonItem.getSeasonName());

        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int imageWidth = ((displayMetrics.widthPixels*20)/100);
        int imageHeight = ((((displayMetrics.widthPixels*20)*9)/16)/100);

        String imageString = seasonItem.getVideoThumbnailImageView()+"/"+imageWidth+"/"+imageHeight;

        Uri uri1 = Uri.parse(imageString);
        //System.out.println("seasonItem.getVideoThumbnailImageView():-"+seasonItem.getVideoThumbnailImageView()+"/"+imageWidth+"/"+imageHeight);
        SimpleDraweeView videoThumbnailImageView = (SimpleDraweeView) convertView.findViewById(R.id.videoThumbnailImageView);
        hierarchy = videoThumbnailImageView.getHierarchy();
        hierarchy.setPlaceholderImage(R.drawable.placeholder4);
        //videoThumbnailImageView.setImageURI(uri1);
        //ImageView videoThumbnailImageView = (ImageView) convertView.findViewById(R.id.videoThumbnailImageView);
        /*SimpleDraweeView videoThumbnailImageView = (SimpleDraweeView) convertView.findViewById(R.id.videoThumbnailImageView);
        hierarchy = videoThumbnailImageView.getHierarchy();
        hierarchy.setPlaceholderImage(R.drawable.placeholder4);*/
        //videoThumbnailImageView.setImageURI(uri1);

        /*if(CommonUtils.isActuallyTabletDevice(activity)) {
            new DownloadImageTask(videoThumbnailImageView).execute(imageString);
            videoThumbnailImageView.setVisibility(View.VISIBLE);
        } else {
            videoThumbnailImageView.setVisibility(View.GONE);
        }*/
        /*if(seasonItem.getVideoThumbnailBitmap() != null) {
            videoThumbnailImageView.setImageBitmap(seasonItem.getVideoThumbnailBitmap());
            videoThumbnailImageView.setVisibility(View.VISIBLE);
        }*/

        videoThumbnailImageView.setVisibility(View.VISIBLE);
        Picasso.with(activity).load(uri1).into(videoThumbnailImageView);
        //Glide.with(activity).load(uri1).placeholder(R.drawable.placeholder4).into(videoThumbnailImageView);


        if (position % 2 == 1) {
            convertView.setBackgroundColor(Color.parseColor(ApplicationConstants.EPISODE_LIST_COLOR_1));
        } else {
            convertView.setBackgroundColor(Color.parseColor(ApplicationConstants.EPISODE_LIST_COLOR_2));
        }

        if(position == seasonItemsList.size())
            convertView.findViewById(R.id.extraSpaceView).setVisibility(View.VISIBLE);

        return convertView;
    }

    public void changeAllPlayButtonToWhiteForPhones() {
        for(int i = 0; i < seasonItemsList.size(); i++) {
            seasonItemsList.get(i).setSelected(false);
        }
    }
    public void changePlayButtonToGreenForPhones(int positionInList) {
        changeAllPlayButtonToWhiteForPhones();
        try { seasonItemsList.get(positionInList).setSelected(true);}catch(Exception e){
            //e.printStackTrace();
        }
        notifyDataSetChanged();
    }
}
