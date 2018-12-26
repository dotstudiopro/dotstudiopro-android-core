package com.dotstudioz.dotstudioPRO.corelibrary.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dotstudioz.dotstudioPRO.corelibrary.R;
import com.dotstudioz.dotstudioPRO.services.constants.ApplicationConstants;
import com.dotstudioz.dotstudioPRO.services.constants.FontsConstants;
import com.dotstudioz.dotstudioPRO.models.dto.EpisodeItem;
import com.dotstudioz.dotstudioPRO.models.dto.LiveScheduleDataDTO;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;
import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by Admin on 03-12-2014.
 */
public class LiveScheduleListAdapter_V1 extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<LiveScheduleDataDTO> liveScheduleDataDTOList;
    private DisplayMetrics displayMetrics = new DisplayMetrics();
    public View convertViewAccessedFromOutside;
    public boolean numberOfLinesIs2 = true;

    public LiveScheduleListAdapter_V1(Activity activity, List<LiveScheduleDataDTO> liveScheduleDataDTOList) {
        this.activity = activity;
        this.liveScheduleDataDTOList = liveScheduleDataDTOList;

        /*if (activity instanceof LiveScheduleListAdapter_V1.ILiveScheduleListAdapter)
            iLiveScheduleListAdapter = (LiveScheduleListAdapter_V1.ILiveScheduleListAdapter) activity;
        else
            throw new RuntimeException(activity.toString()+ " must implement ILiveScheduleListAdapter_V1");*/
    }

    @Override
    public int getCount() {
        return liveScheduleDataDTOList.size();
    }

    @Override
    public Object getItem(int position) {
        return liveScheduleDataDTOList.get(position);
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
            convertView = inflater.inflate(R.layout.live_schedule_item_view_v1, parent, false);
            convertViewAccessedFromOutside = convertView;
        }

        LiveScheduleDataDTO liveScheduleDataDTO = liveScheduleDataDTOList.get(position);

        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int imageWidth = ((displayMetrics.widthPixels*35)/100);
        //int imageHeight = ((((displayMetrics.widthPixels*20)*9)/16)/100);
        int imageHeight = ((imageWidth * 9) / 16);

        ((TextView)convertView.findViewById(R.id.liveScheduleTimeTextView)).setTypeface(FontsConstants.sourcesansproBold);
        ((TextView)convertView.findViewById(R.id.liveScheduleDateTextView)).setTypeface(FontsConstants.sourcesansproBold);
        ((TextView)convertView.findViewById(R.id.titleLiveScheduleTextView)).setTypeface(FontsConstants.sourcesansproBold);
        ((TextView)convertView.findViewById(R.id.descriptionLiveScheduleTextView)).setTypeface(FontsConstants.sourcesansproRegular);

        ((LinearLayout) convertView.findViewById(R.id.firstRowLinearLayout)).getLayoutParams().width = ((displayMetrics.widthPixels*65)/100);
        convertView.findViewById(R.id.firstRowLinearLayout).setPadding(0, 0, 20, 0);
        ((LinearLayout) convertView.findViewById(R.id.seccondRowLinearLayout)).getLayoutParams().height = ((displayMetrics.widthPixels*23)/100);
        ((LinearLayout) convertView.findViewById(R.id.seccondRowLinearLayout)).getLayoutParams().width = ((displayMetrics.widthPixels*35)/100);

        String firstString = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("H:mm");
            Date dd = sdf.parse("" + liveScheduleDataDTO.getScheduledStartTime().getHours() + ":" + liveScheduleDataDTO.getScheduledStartTime().getMinutes());
            //System.out.println("" + dd);
            firstString = "" + new SimpleDateFormat("K:mm a").format(dd);
        } catch(Exception e) {
            e.printStackTrace();
            firstString = liveScheduleDataDTO.getScheduledStartTime().getHours()+":"+liveScheduleDataDTO.getScheduledStartTime().getMinutes();
        }

        String secondString = "";
        try {
            TimeZone tz = TimeZone.getDefault();
            secondString = "" + tz.getDisplayName(false, TimeZone.SHORT);
        } catch(Exception e) {
            e.printStackTrace();
            secondString = "GMT";
        }

        String thirdString = "";
        try {
            thirdString = liveScheduleDataDTO.getScheduledStartTime().getMonth()+"/"+liveScheduleDataDTO.getScheduledStartTime().getDate()+"/"+liveScheduleDataDTO.getScheduledStartTime().getYear();
        } catch(Exception e) {
            e.printStackTrace();
            thirdString = liveScheduleDataDTO.getScheduledStartTime().toString();
        }

        ((TextView)convertView.findViewById(R.id.liveScheduleTimeTextView)).setText(firstString);
        ((TextView)convertView.findViewById(R.id.liveScheduleZoneTextView)).setText(secondString);
        ((TextView)convertView.findViewById(R.id.liveScheduleDateTextView)).setText(thirdString);

        ((TextView)convertView.findViewById(R.id.titleLiveScheduleTextView)).setText(liveScheduleDataDTO.getTitle());
        ((TextView)convertView.findViewById(R.id.descriptionLiveScheduleTextView)).setText(liveScheduleDataDTO.getDescription());

        /*((TextView)convertView.findViewById(R.id.titleLiveScheduleTextView)).setSingleLine(true);
        ((TextView)convertView.findViewById(R.id.descriptionLiveScheduleTextView)).setSingleLine(true);*/

        //((SimpleDraweeView) convertView.findViewById(R.id.videoThumbnailImageView)).getLayoutParams().width = ((displayMetrics.widthPixels*20)/100);
        ((SimpleDraweeView) convertView.findViewById(R.id.videoThumbnailImageView)).setLayoutParams(new LinearLayout.LayoutParams(imageWidth, imageHeight));
        //((SimpleDraweeView) convertView.findViewById(R.id.videoThumbnailImageView)).getLayoutParams().height = ((displayMetrics.widthPixels*20)/100);

        Uri uri1 = Uri.parse(liveScheduleDataDTO.getThumb()+"/"+imageWidth+"/"+imageHeight);
        Picasso.with(activity).load(uri1).into(((ImageView)convertView.findViewById(R.id.videoThumbnailImageView)));

        ((TextView)convertView.findViewById(R.id.titleLiveScheduleTextView)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(numberOfLinesIs2) {
                    ((TextView)v).setMaxLines(Integer.MAX_VALUE);
                } else {
                    ((TextView)v).setMaxLines(2);
                }
                try {

                    if(numberOfLinesIs2) {
                        ((TextView)((LinearLayout)v.getParent()).getChildAt(2)).setMaxLines(Integer.MAX_VALUE);
                        numberOfLinesIs2 = false;
                    } else {
                        ((TextView)((LinearLayout)v.getParent()).getChildAt(2)).setMaxLines(2);
                        numberOfLinesIs2 = true;
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        });
        ((TextView)convertView.findViewById(R.id.descriptionLiveScheduleTextView)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(numberOfLinesIs2) {
                    ((TextView)v).setMaxLines(Integer.MAX_VALUE);
                } else {
                    ((TextView)v).setMaxLines(2);
                }
                try {

                    if(numberOfLinesIs2) {
                        ((TextView)((LinearLayout)v.getParent()).getChildAt(1)).setMaxLines(Integer.MAX_VALUE);
                        numberOfLinesIs2 = false;
                    } else {
                        ((TextView)((LinearLayout)v.getParent()).getChildAt(1)).setMaxLines(2);
                        numberOfLinesIs2 = true;
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return convertView;
    }

    public boolean isTextTruncated( String text, TextView textView )
    {
        if ( textView != null && text != null )
        {

            Layout layout = textView.getLayout();
            if ( layout != null )
            {
                int lines = layout.getLineCount();
                if ( lines > 0 )
                {
                    int ellipsisCount = layout.getEllipsisCount( lines - 1 );
                    if ( ellipsisCount > 0 )
                    {
                        return true;
                    }
                }
            }

        }
        return false;
    }
}
