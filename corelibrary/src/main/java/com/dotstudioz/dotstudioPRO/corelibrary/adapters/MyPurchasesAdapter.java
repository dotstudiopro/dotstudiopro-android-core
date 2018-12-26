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
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dotstudioz.dotstudioPRO.corelibrary.R;
import com.dotstudioz.dotstudioPRO.corelibrary.constants.FontsConstants;
import com.dotstudioz.dotstudioPRO.models.dto.MyPurchaseItemDTO;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by Admin on 17-06-2015.
 */
public class MyPurchasesAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<MyPurchaseItemDTO> myPurchaseItemList;

    private MyPurchasesAdapterCallback callback;

    public MyPurchasesAdapter(Activity activity, List<MyPurchaseItemDTO> myPurchaseItemList) {
        this.activity = activity;
        this.myPurchaseItemList = myPurchaseItemList;
    }

    @Override
    public int getCount() {
        return myPurchaseItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return myPurchaseItemList.get(position);
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
            convertView = inflater.inflate(R.layout.my_purchase_item, null);

        SimpleDraweeView videoImage = (SimpleDraweeView) convertView.findViewById(R.id.my_purchase_video_image);
        TextView videoTitle = (TextView) convertView.findViewById(R.id.my_purchase_video_title);
        TextView videoStatusText = (TextView) convertView.findViewById(R.id.my_purchase_video_status);
        ImageButton videoStatusButton = (ImageButton) convertView.findViewById(R.id.my_purchase_video_watch);
        ProgressBar pb1 = (ProgressBar) convertView.findViewById(R.id.videoImageProgressView);

        videoTitle.setShadowLayer(2f, -1, 1, Color.BLACK);
        videoTitle.setTypeface(FontsConstants.tfBold);
        videoStatusText.setShadowLayer(2f, -1, 1, Color.WHITE);
        videoStatusText.setTypeface(FontsConstants.tfRegular);

        DisplayMetrics displaymetrics1 = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics1);
        int height = displaymetrics1.heightPixels, width = (displaymetrics1.widthPixels);
        //int imageWidth = (width/2);
        int imageWidth = width;
        int imageHeight = ((imageWidth * 9) / 16);

        //convertView.findViewById(R.id.fl_my_purchases).setLayoutParams(new LinearLayout.LayoutParams(imageWidth, imageHeight));
        videoImage.setLayoutParams(new RelativeLayout.LayoutParams(imageWidth, imageHeight));
        convertView.findViewById(R.id.my_purchase_text_button).setLayoutParams(new LinearLayout.LayoutParams(imageWidth, imageHeight));

        final MyPurchaseItemDTO myPurchaseItemDTO = myPurchaseItemList.get(position);
        //System.out.println("Video_Thumb details:"+myPurchaseItemDTO.getImageURL());
        try {
            //videoImage.setImageUrl(myPurchaseItemDTO.getImageURL()+"/"+imageWidth+"/"+imageHeight);
            Uri uri = Uri.parse(myPurchaseItemDTO.getImageURL()+"/"+imageWidth+"/"+imageHeight);
            videoImage.setImageURI(uri);
        } catch(Exception e) {
            System.out.println("Problem with Image Loager, I guess!!!");
        }
        videoTitle.setText(myPurchaseItemDTO.getTitle());
        if(myPurchaseItemDTO.isStatus()) {
            videoStatusText.setVisibility(View.GONE);
            videoStatusButton.setVisibility(View.VISIBLE);
            videoStatusButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(callback != null) {
                        callback.watchPurchasedVideo(myPurchaseItemDTO.getVideoID(), myPurchaseItemDTO.getCategory(), myPurchaseItemDTO.getChannel());
                    }
                }
            });
        } else {
            videoStatusText.setVisibility(View.VISIBLE);
            videoStatusText.setText("Expired");
            videoStatusButton.setVisibility(View.GONE);
        }

        if (position % 2 == 1) {
            convertView.setBackgroundColor(Color.BLACK);
        } else {
            convertView.setBackgroundColor(Color.parseColor("#101010"));
        }

        return convertView;
    }

    public void setCallback(MyPurchasesAdapterCallback callback) {
        this.callback = callback;
    }

    public interface MyPurchasesAdapterCallback {
        public void watchPurchasedVideo(String videoID, String category, String channel);
    }
}
