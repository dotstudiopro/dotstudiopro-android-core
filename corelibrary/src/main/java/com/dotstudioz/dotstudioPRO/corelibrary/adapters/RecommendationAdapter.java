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
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dotstudioz.dotstudioPRO.corelibrary.R;
import com.dotstudioz.dotstudioPRO.corelibrary.constants.FontsConstants;
import com.dotstudioz.dotstudioPRO.models.dto.RecommendedItemPairDTO;
import com.dotstudioz.dotstudioPRO.corelibrary.util.CommonUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by Admin on 03-12-2014.
 */
public class RecommendationAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<RecommendedItemPairDTO> recommendedItemPairDTOList;
    public GenericDraweeHierarchy hierarchy;
    public int imageWidth;
    public int imageHeight;
    public boolean tabletFlag;

    private boolean isCustomFontEnabledForVideoTitle = false;
    private Typeface customFontForVideoTitle;

    public IRecommendationAdapter iRecommendationAdapter;

    public RecommendationAdapter(Activity activity, List<RecommendedItemPairDTO> recommendedItemPairDTOList) {
        this.activity = activity;
        iRecommendationAdapter = (IRecommendationAdapter) activity;
        this.recommendedItemPairDTOList = recommendedItemPairDTOList;
    }

    @Override
    public int getCount() {
        return recommendedItemPairDTOList.size();
    }

    @Override
    public Object getItem(int position) {
        return recommendedItemPairDTOList.get(position);
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
            convertView = inflater.inflate(R.layout.recommended_item, parent, false);
        }

        try {
            /*RelativeLayout recommendedItemRL = (RelativeLayout) convertView.findViewById(R.id.recommendedItemRL);
            recommendedItemRL.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            convertView.findViewById(R.id.recommendedItemProgressBar).setPadding(0, ((imageHeight / 2) - 60), 0, 0);*/

            RelativeLayout.LayoutParams params1 = null;
            params1 = new RelativeLayout.LayoutParams(imageWidth, imageHeight);
            //convertView.findViewById(R.id.iv1).setPadding(3, 3, 3, 3);
            convertView.findViewById(R.id.recommendedItemIV).setLayoutParams(params1);
            convertView.findViewById(R.id.recommendedItemIV).setClickable(true);
            convertView.findViewById(R.id.recommendedItemIV).setTag(convertView.findViewById(R.id.recommendedItemIV).getId(), recommendedItemPairDTOList.get(position).getRecommendedItemDTO1().getId());

            String imageString = "https://images.dotstudiopro.com/"+recommendedItemPairDTOList.get(position).getRecommendedItemDTO1().getThumb();
            imageString = CommonUtils.replaceDotstudioproWithMyspotlightForImage(imageString);

            String imageURLAddress = imageString + "/" + imageWidth + "/" + imageHeight;
            //System.out.println("ImageData:" + imageURLAddress);
            Uri uri = Uri.parse(imageURLAddress);
            ((SimpleDraweeView) convertView.findViewById(R.id.recommendedItemIV)).setImageURI(uri);
            convertView.findViewById(R.id.recommendedItemIV).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickHandlerForChannelsImage(v.getTag(v.getId()).toString());//clickHandlerForChannelsImage(v.getTag().toString());
                }
            });

            TextView recommendedItemTV = ((TextView) convertView.findViewById(R.id.recommendedItemTV));
            recommendedItemTV.setText(recommendedItemPairDTOList.get(position).getRecommendedItemDTO1().getTitle());
            if(isCustomFontEnabledForVideoTitle)
                recommendedItemTV.setTypeface(customFontForVideoTitle);
            else
                recommendedItemTV.setTypeface(FontsConstants.tfRegular);
            recommendedItemTV.setSingleLine(true);
            recommendedItemTV.setPadding(0, 10, 0, 0);
            recommendedItemTV.setEllipsize(TextUtils.TruncateAt.END);
            if(CommonUtils.isActuallyTabletDevice(activity))
                recommendedItemTV.setTextSize(14f);
            else
                recommendedItemTV.setTextSize(12f);
            recommendedItemTV.setGravity(Gravity.LEFT);
            recommendedItemTV.setTextColor(Color.parseColor("#878d90"));
            recommendedItemTV.setIncludeFontPadding(false);
            recommendedItemTV.setShadowLayer(2f, -1, 1, Color.BLACK);

            TextView recommendedSeriesItemTV = ((TextView) convertView.findViewById(R.id.recommendedSeriesItemTV));
            recommendedSeriesItemTV.setText(recommendedItemPairDTOList.get(position).getRecommendedItemDTO1().getSeriesTitle());
            if(isCustomFontEnabledForVideoTitle)
                recommendedSeriesItemTV.setTypeface(customFontForVideoTitle);
            else
                recommendedSeriesItemTV.setTypeface(FontsConstants.tfRegular);
            recommendedSeriesItemTV.setSingleLine(true);
            recommendedSeriesItemTV.setPadding(0, 0, 0, 10);
            recommendedSeriesItemTV.setEllipsize(TextUtils.TruncateAt.END);
            if(CommonUtils.isActuallyTabletDevice(activity))
                recommendedSeriesItemTV.setTextSize(14f);
            else
                recommendedSeriesItemTV.setTextSize(12f);
            recommendedSeriesItemTV.setGravity(Gravity.LEFT);
            recommendedSeriesItemTV.setTextColor(Color.parseColor("#6dec68"));
            recommendedSeriesItemTV.setIncludeFontPadding(false);
            recommendedSeriesItemTV.setShadowLayer(2f, -1, 1, Color.BLACK);
        } catch(Exception e) {
            e.printStackTrace();
        }




        if(recommendedItemPairDTOList.get(position).getRecommendedItemDTO2() != null) {
            try {
                /*RelativeLayout recommendedItemRL = (RelativeLayout) convertView.findViewById(R.id.recommendedItemRL2);
                recommendedItemRL.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                convertView.findViewById(R.id.recommendedItemProgressBar2).setPadding(0, ((imageHeight / 2) - 60), 0, 0);*/

                RelativeLayout.LayoutParams params1 = null;
                params1 = new RelativeLayout.LayoutParams(imageWidth, imageHeight);
                //convertView.findViewById(R.id.iv1).setPadding(3, 3, 3, 3);
                convertView.findViewById(R.id.recommendedItemIV2).setLayoutParams(params1);
                convertView.findViewById(R.id.recommendedItemIV2).setClickable(true);
                convertView.findViewById(R.id.recommendedItemIV2).setTag(convertView.findViewById(R.id.recommendedItemIV2).getId(), recommendedItemPairDTOList.get(position).getRecommendedItemDTO2().getId());

                String imageString = "https://images.dotstudiopro.com/"+recommendedItemPairDTOList.get(position).getRecommendedItemDTO2().getThumb();
                imageString = CommonUtils.replaceDotstudioproWithMyspotlightForImage(imageString);

                String imageURLAddress = imageString + "/" + imageWidth + "/" + imageHeight;
                //System.out.println("ImageData:" + imageURLAddress);
                Uri uri = Uri.parse(imageURLAddress);
                ((SimpleDraweeView) convertView.findViewById(R.id.recommendedItemIV2)).setImageURI(uri);
                convertView.findViewById(R.id.recommendedItemIV2).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickHandlerForChannelsImage(v.getTag(v.getId()).toString());//clickHandlerForChannelsImage(v.getTag().toString());
                    }
                });

                TextView recommendedItemTV = ((TextView) convertView.findViewById(R.id.recommendedItemTV2));
                recommendedItemTV.setText(recommendedItemPairDTOList.get(position).getRecommendedItemDTO2().getTitle());
                if(isCustomFontEnabledForVideoTitle)
                    recommendedItemTV.setTypeface(customFontForVideoTitle);
                else
                    recommendedItemTV.setTypeface(FontsConstants.tfRegular);
                recommendedItemTV.setSingleLine(true);
                recommendedItemTV.setPadding(0, 10, 0, 0);
                recommendedItemTV.setEllipsize(TextUtils.TruncateAt.END);
                if(CommonUtils.isActuallyTabletDevice(activity))
                    recommendedItemTV.setTextSize(14f);
                else
                    recommendedItemTV.setTextSize(12f);
                recommendedItemTV.setGravity(Gravity.LEFT);
                recommendedItemTV.setTextColor(Color.parseColor("#878d90"));
                recommendedItemTV.setIncludeFontPadding(false);
                recommendedItemTV.setShadowLayer(2f, -1, 1, Color.BLACK);

                TextView recommendedSeriesItemTV = ((TextView) convertView.findViewById(R.id.recommendedSeriesItemTV2));
                recommendedSeriesItemTV.setText(recommendedItemPairDTOList.get(position).getRecommendedItemDTO2().getSeriesTitle());
                if(isCustomFontEnabledForVideoTitle)
                    recommendedSeriesItemTV.setTypeface(customFontForVideoTitle);
                else
                    recommendedSeriesItemTV.setTypeface(FontsConstants.tfRegular);
                recommendedSeriesItemTV.setSingleLine(true);
                recommendedSeriesItemTV.setPadding(0, 0, 0, 10);
                recommendedSeriesItemTV.setEllipsize(TextUtils.TruncateAt.END);
                if(CommonUtils.isActuallyTabletDevice(activity))
                    recommendedSeriesItemTV.setTextSize(14f);
                else
                    recommendedSeriesItemTV.setTextSize(12f);
                recommendedSeriesItemTV.setGravity(Gravity.LEFT);
                recommendedSeriesItemTV.setTextColor(Color.parseColor("#6dec68"));
                recommendedSeriesItemTV.setIncludeFontPadding(false);
                recommendedSeriesItemTV.setShadowLayer(2f, -1, 1, Color.BLACK);
            } catch(Exception e) {
                e.printStackTrace();
            }
        } else {
            convertView.findViewById(R.id.recommendedItemRL2).setVisibility(View.GONE);
        }


        return convertView;
    }

    private void clickHandlerForChannelsImage(String tagSent) {
        iRecommendationAdapter.recommendedImageClickHandler(tagSent);
    }

    public interface IRecommendationAdapter {
        void recommendedImageClickHandler(String tagSent);
    }

    public boolean isCustomFontEnabledForVideoTitle() {
        return isCustomFontEnabledForVideoTitle;
    }

    public void setCustomFontEnabledForVideoTitle(boolean customFontEnabledForVideoTitle) {
        isCustomFontEnabledForVideoTitle = customFontEnabledForVideoTitle;
    }

    public Typeface getCustomFontForVideoTitle() {
        return customFontForVideoTitle;
    }

    public void setCustomFontForVideoTitle(Typeface customFontForVideoTitle) {
        this.customFontForVideoTitle = customFontForVideoTitle;
    }
}
