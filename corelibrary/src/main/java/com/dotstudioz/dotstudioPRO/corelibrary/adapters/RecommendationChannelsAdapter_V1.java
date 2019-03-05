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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dotstudioz.dotstudioPRO.corelibrary.R;
import com.dotstudioz.dotstudioPRO.models.dto.RecommendedItemDTO;
import com.dotstudioz.dotstudioPRO.services.constants.ApplicationConstants;
import com.dotstudioz.dotstudioPRO.corelibrary.constants.FontsConstants;
import com.dotstudioz.dotstudioPRO.models.dto.Recommended4ItemPairDTO;
import com.dotstudioz.dotstudioPRO.corelibrary.util.CommonUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by Admin on 03-12-2014.
 */
public class RecommendationChannelsAdapter_V1 extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<Recommended4ItemPairDTO> recommendedItemPairDTOList;
    public GenericDraweeHierarchy hierarchy;
    public int imageWidth;
    public int imageHeight;
    public int channelImageWidth;
    public int channelImageHeight;
    public boolean tabletFlag;

    public int titleColour = 0;
    public int seriesColour = 0;

    private boolean isCustomFontEnabledForVideoTitle = false;
    private Typeface customFontForVideoTitle;

    public boolean isLockToBeShown = false;

    public IRecommendationAdapter iRecommendationAdapter;

    public RecommendationChannelsAdapter_V1(Activity activity, List<Recommended4ItemPairDTO> recommendedItemPairDTOList) {
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
            convertView = inflater.inflate(R.layout.recommended_channel_item_v1, parent, false);
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
            convertView.findViewById(R.id.recommendedItemIV).setTag(convertView.findViewById(R.id.recommendedItemIV).getId(), recommendedItemPairDTOList.get(position).getRecommendedItemDTO1().getSlug());

            if(recommendedItemPairDTOList.get(position).getRecommendedItemDTO1().getVideoPausedPoint() > 0 &&
                    recommendedItemPairDTOList.get(position).getRecommendedItemDTO1().getVideoDuration() > 0) {
                if(recommendedItemPairDTOList.get(position).getRecommendedItemDTO1().getType() != null &&
                        recommendedItemPairDTOList.get(position).getRecommendedItemDTO1().getType().equals("channel")) {
                    RelativeLayout.LayoutParams params11 = null;
                    params11 = new RelativeLayout.LayoutParams(channelImageWidth, channelImageHeight);
                    //convertView.findViewById(R.id.iv1).setPadding(3, 3, 3, 3);
                    convertView.findViewById(R.id.recommendedItemIV).setLayoutParams(params11);
                }

                int videoDuration = recommendedItemPairDTOList.get(position).getRecommendedItemDTO1().getVideoDuration();
                int videoPausedPointPercentage = ((recommendedItemPairDTOList.get(position).getRecommendedItemDTO1().getVideoPausedPoint() * 100)/videoDuration);
                convertView.findViewById(R.id.parentProgressContanier).setBackgroundColor(Color.parseColor("#33ffffff"));
                convertView.findViewById(R.id.childProgressContainer).setBackgroundColor(seriesColour);
                int heightForProgressBar = 0;
                if (ApplicationConstants.TEXT_INPUT_HEIGHT > 0)
                    heightForProgressBar = ApplicationConstants.TEXT_INPUT_HEIGHT / 6;
                else if (ApplicationConstants.TOP_PADDING_FOR_MAIN_ACTION_BAR > 0)
                    heightForProgressBar = ApplicationConstants.TOP_PADDING_FOR_MAIN_ACTION_BAR / 8;
                if(recommendedItemPairDTOList.get(position).getRecommendedItemDTO1().getType() != null &&
                        recommendedItemPairDTOList.get(position).getRecommendedItemDTO1().getType().equals("channel")) {
                    ((RelativeLayout.LayoutParams)convertView.findViewById(R.id.parentProgressContanier).getLayoutParams()).width = channelImageWidth;
                } else {
                    ((RelativeLayout.LayoutParams) convertView.findViewById(R.id.parentProgressContanier).getLayoutParams()).width = imageWidth;
                }
                ((RelativeLayout.LayoutParams)convertView.findViewById(R.id.parentProgressContanier).getLayoutParams()).height = heightForProgressBar;
                //convertView.findViewById(R.id.parentProgressContanier).setLayoutParams(new RelativeLayout.LayoutParams(imageWidth, heightForProgressBar));
                //((RelativeLayout.LayoutParams) convertView.findViewById(R.id.parentProgressContanier).getLayoutParams()).addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                int widthOfProgress = ((imageWidth*videoPausedPointPercentage)/100);
                convertView.findViewById(R.id.childProgressContainer).setLayoutParams(new LinearLayout.LayoutParams(widthOfProgress, heightForProgressBar));

                if(videoPausedPointPercentage > 95) {
                    convertView.findViewById(R.id.recommendedItemRL).setAlpha(0.35f);
                }
            }

            String imageString = "";
            if(recommendedItemPairDTOList.get(position).getRecommendedItemDTO1().getType() != null &&
                    recommendedItemPairDTOList.get(position).getRecommendedItemDTO1().getType().equals("channel")) {
                imageString = recommendedItemPairDTOList.get(position).getRecommendedItemDTO1().getSpotLightPoster();
            } else {
                imageString = "https://images.dotstudiopro.com/"+recommendedItemPairDTOList.get(position).getRecommendedItemDTO1().getThumb();
            }
            imageString = CommonUtils.replaceDotstudioproWithMyspotlightForImage(imageString);

            String imageURLAddress = imageString + "/" + imageWidth + "/" + imageHeight;
            //System.out.println("ImageData1:" + imageURLAddress);
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
            recommendedItemTV.setTextColor(titleColour);
            recommendedItemTV.setIncludeFontPadding(false);
            //recommendedItemTV.setShadowLayer(2f, -1, 1, Color.BLACK);

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
            recommendedSeriesItemTV.setTextColor(seriesColour);
            recommendedSeriesItemTV.setIncludeFontPadding(false);
            //recommendedSeriesItemTV.setShadowLayer(2f, -1, 1, Color.BLACK);

            if(isLockToBeShown) {
                //do not delete this, as this is used to display the lock image on the channel poster
                addLockButton(recommendedItemPairDTOList.get(position).getRecommendedItemDTO1(), (RelativeLayout)convertView.findViewById(R.id.recommendedItemRL));
            }

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
                convertView.findViewById(R.id.recommendedItemIV2).setTag(convertView.findViewById(R.id.recommendedItemIV2).getId(), recommendedItemPairDTOList.get(position).getRecommendedItemDTO2().getSlug());

                if(recommendedItemPairDTOList.get(position).getRecommendedItemDTO2().getVideoPausedPoint() > 0 &&
                        recommendedItemPairDTOList.get(position).getRecommendedItemDTO2().getVideoDuration() > 0) {
                    if(recommendedItemPairDTOList.get(position).getRecommendedItemDTO2().getType() != null &&
                            recommendedItemPairDTOList.get(position).getRecommendedItemDTO2().getType().equals("channel")) {
                        RelativeLayout.LayoutParams params11 = null;
                        params11 = new RelativeLayout.LayoutParams(channelImageWidth, channelImageHeight);
                        //convertView.findViewById(R.id.iv1).setPadding(3, 3, 3, 3);
                        convertView.findViewById(R.id.recommendedItemIV2).setLayoutParams(params11);
                    }
                    int videoDuration = recommendedItemPairDTOList.get(position).getRecommendedItemDTO2().getVideoDuration();
                    int videoPausedPointPercentage = ((recommendedItemPairDTOList.get(position).getRecommendedItemDTO2().getVideoPausedPoint() * 100)/videoDuration);
                    convertView.findViewById(R.id.parentProgressContanier2).setBackgroundColor(Color.parseColor("#33ffffff"));
                    convertView.findViewById(R.id.childProgressContainer2).setBackgroundColor(seriesColour);
                    int heightForProgressBar = 0;
                    if (ApplicationConstants.TEXT_INPUT_HEIGHT > 0)
                        heightForProgressBar = ApplicationConstants.TEXT_INPUT_HEIGHT / 6;
                    else if (ApplicationConstants.TOP_PADDING_FOR_MAIN_ACTION_BAR > 0)
                        heightForProgressBar = ApplicationConstants.TOP_PADDING_FOR_MAIN_ACTION_BAR / 8;
                    if(recommendedItemPairDTOList.get(position).getRecommendedItemDTO2().getType() != null &&
                            recommendedItemPairDTOList.get(position).getRecommendedItemDTO2().getType().equals("channel")) {
                        ((RelativeLayout.LayoutParams)convertView.findViewById(R.id.parentProgressContanier2).getLayoutParams()).width = channelImageWidth;
                    } else {
                        ((RelativeLayout.LayoutParams) convertView.findViewById(R.id.parentProgressContanier2).getLayoutParams()).width = imageWidth;
                    }
                    ((RelativeLayout.LayoutParams)convertView.findViewById(R.id.parentProgressContanier2).getLayoutParams()).height = heightForProgressBar;
                    //convertView.findViewById(R.id.parentProgressContanier).setLayoutParams(new RelativeLayout.LayoutParams(imageWidth, heightForProgressBar));
                    //((RelativeLayout.LayoutParams) convertView.findViewById(R.id.parentProgressContanier).getLayoutParams()).addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                    int widthOfProgress = ((imageWidth*videoPausedPointPercentage)/100);
                    convertView.findViewById(R.id.childProgressContainer2).setLayoutParams(new LinearLayout.LayoutParams(widthOfProgress, LinearLayout.LayoutParams.MATCH_PARENT));

                    if(videoPausedPointPercentage > 95) {
                        convertView.findViewById(R.id.recommendedItemRL2).setAlpha(0.35f);
                    }
                }

                String imageString = "";
                if(recommendedItemPairDTOList.get(position).getRecommendedItemDTO2().getType() != null &&
                        recommendedItemPairDTOList.get(position).getRecommendedItemDTO2().getType().equals("channel")) {
                    imageString = recommendedItemPairDTOList.get(position).getRecommendedItemDTO2().getSpotLightPoster();
                } else {
                    imageString = "https://images.dotstudiopro.com/"+recommendedItemPairDTOList.get(position).getRecommendedItemDTO2().getThumb();
                }
                imageString = CommonUtils.replaceDotstudioproWithMyspotlightForImage(imageString);

                String imageURLAddress = imageString + "/" + imageWidth + "/" + imageHeight;
                //System.out.println("ImageData2:" + imageURLAddress);
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
                recommendedItemTV.setTextColor(titleColour);
                recommendedItemTV.setIncludeFontPadding(false);
                //recommendedItemTV.setShadowLayer(2f, -1, 1, Color.BLACK);

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
                recommendedSeriesItemTV.setTextColor(seriesColour);
                recommendedSeriesItemTV.setIncludeFontPadding(false);
                //recommendedSeriesItemTV.setShadowLayer(2f, -1, 1, Color.BLACK);
                if(isLockToBeShown) {
                    //do not delete this, as this is used to display the lock image on the channel poster
                    addLockButton(recommendedItemPairDTOList.get(position).getRecommendedItemDTO2(), (RelativeLayout)convertView.findViewById(R.id.recommendedItemRL2));
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        } else {
            convertView.findViewById(R.id.recommendedItemRL2).setVisibility(View.INVISIBLE);
        }

        if(recommendedItemPairDTOList.get(position).getRecommendedItemDTO3() != null) {
            try {
                /*RelativeLayout recommendedItemRL = (RelativeLayout) convertView.findViewById(R.id.recommendedItemRL2);
                recommendedItemRL.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                convertView.findViewById(R.id.recommendedItemProgressBar2).setPadding(0, ((imageHeight / 2) - 60), 0, 0);*/

                RelativeLayout.LayoutParams params1 = null;
                params1 = new RelativeLayout.LayoutParams(imageWidth, imageHeight);
                //convertView.findViewById(R.id.iv1).setPadding(3, 3, 3, 3);
                convertView.findViewById(R.id.recommendedItemIV3).setLayoutParams(params1);
                convertView.findViewById(R.id.recommendedItemIV3).setClickable(true);
                convertView.findViewById(R.id.recommendedItemIV3).setTag(convertView.findViewById(R.id.recommendedItemIV3).getId(), recommendedItemPairDTOList.get(position).getRecommendedItemDTO3().getSlug());

                if(recommendedItemPairDTOList.get(position).getRecommendedItemDTO3().getVideoPausedPoint() > 0 &&
                        recommendedItemPairDTOList.get(position).getRecommendedItemDTO3().getVideoDuration() > 0) {
                    if(recommendedItemPairDTOList.get(position).getRecommendedItemDTO3().getType() != null &&
                            recommendedItemPairDTOList.get(position).getRecommendedItemDTO3().getType().equals("channel")) {
                        RelativeLayout.LayoutParams params11 = null;
                        params11 = new RelativeLayout.LayoutParams(channelImageWidth, channelImageHeight);
                        //convertView.findViewById(R.id.iv1).setPadding(3, 3, 3, 3);
                        convertView.findViewById(R.id.recommendedItemIV3).setLayoutParams(params11);
                    }
                    int videoDuration = recommendedItemPairDTOList.get(position).getRecommendedItemDTO3().getVideoDuration();
                    int videoPausedPointPercentage = ((recommendedItemPairDTOList.get(position).getRecommendedItemDTO3().getVideoPausedPoint() * 100)/videoDuration);
                    convertView.findViewById(R.id.parentProgressContanier3).setBackgroundColor(Color.parseColor("#33ffffff"));
                    convertView.findViewById(R.id.childProgressContainer3).setBackgroundColor(seriesColour);
                    int heightForProgressBar = 0;
                    if (ApplicationConstants.TEXT_INPUT_HEIGHT > 0)
                        heightForProgressBar = ApplicationConstants.TEXT_INPUT_HEIGHT / 6;
                    else if (ApplicationConstants.TOP_PADDING_FOR_MAIN_ACTION_BAR > 0)
                        heightForProgressBar = ApplicationConstants.TOP_PADDING_FOR_MAIN_ACTION_BAR / 8;
                    if(recommendedItemPairDTOList.get(position).getRecommendedItemDTO3().getType() != null &&
                            recommendedItemPairDTOList.get(position).getRecommendedItemDTO3().getType().equals("channel")) {
                        ((RelativeLayout.LayoutParams)convertView.findViewById(R.id.parentProgressContanier3).getLayoutParams()).width = channelImageWidth;
                    } else {
                        ((RelativeLayout.LayoutParams) convertView.findViewById(R.id.parentProgressContanier3).getLayoutParams()).width = imageWidth;
                    }
                    ((RelativeLayout.LayoutParams)convertView.findViewById(R.id.parentProgressContanier3).getLayoutParams()).height = heightForProgressBar;
                    //convertView.findViewById(R.id.parentProgressContanier).setLayoutParams(new RelativeLayout.LayoutParams(imageWidth, heightForProgressBar));
                    //((RelativeLayout.LayoutParams) convertView.findViewById(R.id.parentProgressContanier).getLayoutParams()).addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                    int widthOfProgress = ((imageWidth*videoPausedPointPercentage)/100);
                    convertView.findViewById(R.id.childProgressContainer3).setLayoutParams(new LinearLayout.LayoutParams(widthOfProgress, LinearLayout.LayoutParams.MATCH_PARENT));

                    if(videoPausedPointPercentage > 95) {
                        convertView.findViewById(R.id.recommendedItemRL3).setAlpha(0.35f);
                    }
                }

                String imageString = "";
                if(recommendedItemPairDTOList.get(position).getRecommendedItemDTO3().getType() != null &&
                        recommendedItemPairDTOList.get(position).getRecommendedItemDTO3().getType().equals("channel")) {
                    imageString = recommendedItemPairDTOList.get(position).getRecommendedItemDTO3().getSpotLightPoster();
                } else {
                    imageString = "https://images.dotstudiopro.com/"+recommendedItemPairDTOList.get(position).getRecommendedItemDTO3().getThumb();
                }
                imageString = CommonUtils.replaceDotstudioproWithMyspotlightForImage(imageString);

                String imageURLAddress = imageString + "/" + imageWidth + "/" + imageHeight;
                //System.out.println("ImageData3:" + imageURLAddress);
                Uri uri = Uri.parse(imageURLAddress);
                ((SimpleDraweeView) convertView.findViewById(R.id.recommendedItemIV3)).setImageURI(uri);
                convertView.findViewById(R.id.recommendedItemIV3).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickHandlerForChannelsImage(v.getTag(v.getId()).toString());//clickHandlerForChannelsImage(v.getTag().toString());
                    }
                });

                TextView recommendedItemTV = ((TextView) convertView.findViewById(R.id.recommendedItemTV3));
                recommendedItemTV.setText(recommendedItemPairDTOList.get(position).getRecommendedItemDTO3().getTitle());
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
                recommendedItemTV.setTextColor(titleColour);
                recommendedItemTV.setIncludeFontPadding(false);
                //recommendedItemTV.setShadowLayer(2f, -1, 1, Color.BLACK);

                TextView recommendedSeriesItemTV = ((TextView) convertView.findViewById(R.id.recommendedSeriesItemTV3));
                recommendedSeriesItemTV.setText(recommendedItemPairDTOList.get(position).getRecommendedItemDTO3().getSeriesTitle());
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
                recommendedSeriesItemTV.setTextColor(seriesColour);
                recommendedSeriesItemTV.setIncludeFontPadding(false);
                //recommendedSeriesItemTV.setShadowLayer(2f, -1, 1, Color.BLACK);
            } catch(Exception e) {
                e.printStackTrace();
            }
            if(isLockToBeShown) {
                //do not delete this, as this is used to display the lock image on the channel poster
                addLockButton(recommendedItemPairDTOList.get(position).getRecommendedItemDTO3(), (RelativeLayout)convertView.findViewById(R.id.recommendedItemRL3));
            }
        } else {
            convertView.findViewById(R.id.recommendedItemRL3).setVisibility(View.INVISIBLE);
        }

        if(recommendedItemPairDTOList.get(position).getRecommendedItemDTO4() != null) {
            try {
                /*RelativeLayout recommendedItemRL = (RelativeLayout) convertView.findViewById(R.id.recommendedItemRL2);
                recommendedItemRL.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                convertView.findViewById(R.id.recommendedItemProgressBar2).setPadding(0, ((imageHeight / 2) - 60), 0, 0);*/

                RelativeLayout.LayoutParams params1 = null;
                params1 = new RelativeLayout.LayoutParams(imageWidth, imageHeight);
                //convertView.findViewById(R.id.iv1).setPadding(3, 3, 3, 3);
                convertView.findViewById(R.id.recommendedItemIV4).setLayoutParams(params1);
                convertView.findViewById(R.id.recommendedItemIV4).setClickable(true);
                convertView.findViewById(R.id.recommendedItemIV4).setTag(convertView.findViewById(R.id.recommendedItemIV4).getId(), recommendedItemPairDTOList.get(position).getRecommendedItemDTO4().getSlug());

                if(recommendedItemPairDTOList.get(position).getRecommendedItemDTO4().getVideoPausedPoint() > 0 &&
                        recommendedItemPairDTOList.get(position).getRecommendedItemDTO4().getVideoDuration() > 0) {
                    if(recommendedItemPairDTOList.get(position).getRecommendedItemDTO4().getType() != null &&
                            recommendedItemPairDTOList.get(position).getRecommendedItemDTO4().getType().equals("channel")) {
                        RelativeLayout.LayoutParams params11 = null;
                        params11 = new RelativeLayout.LayoutParams(channelImageWidth, channelImageHeight);
                        //convertView.findViewById(R.id.iv1).setPadding(3, 3, 3, 3);
                        convertView.findViewById(R.id.recommendedItemIV4).setLayoutParams(params11);
                    }
                    int videoDuration = recommendedItemPairDTOList.get(position).getRecommendedItemDTO4().getVideoDuration();
                    int videoPausedPointPercentage = ((recommendedItemPairDTOList.get(position).getRecommendedItemDTO4().getVideoPausedPoint() * 100)/videoDuration);
                    convertView.findViewById(R.id.parentProgressContanier4).setBackgroundColor(Color.parseColor("#33ffffff"));
                    convertView.findViewById(R.id.childProgressContainer4).setBackgroundColor(seriesColour);
                    int heightForProgressBar = 0;
                    if (ApplicationConstants.TEXT_INPUT_HEIGHT > 0)
                        heightForProgressBar = ApplicationConstants.TEXT_INPUT_HEIGHT / 6;
                    else if (ApplicationConstants.TOP_PADDING_FOR_MAIN_ACTION_BAR > 0)
                        heightForProgressBar = ApplicationConstants.TOP_PADDING_FOR_MAIN_ACTION_BAR / 8;
                    if(recommendedItemPairDTOList.get(position).getRecommendedItemDTO4().getType() != null &&
                            recommendedItemPairDTOList.get(position).getRecommendedItemDTO4().getType().equals("channel")) {
                        ((RelativeLayout.LayoutParams)convertView.findViewById(R.id.parentProgressContanier4).getLayoutParams()).width = channelImageWidth;
                    } else {
                        ((RelativeLayout.LayoutParams) convertView.findViewById(R.id.parentProgressContanier4).getLayoutParams()).width = imageWidth;
                    }
                    ((RelativeLayout.LayoutParams)convertView.findViewById(R.id.parentProgressContanier4).getLayoutParams()).height = heightForProgressBar;
                    //convertView.findViewById(R.id.parentProgressContanier).setLayoutParams(new RelativeLayout.LayoutParams(imageWidth, heightForProgressBar));
                    //((RelativeLayout.LayoutParams) convertView.findViewById(R.id.parentProgressContanier).getLayoutParams()).addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                    int widthOfProgress = ((imageWidth*videoPausedPointPercentage)/100);
                    convertView.findViewById(R.id.childProgressContainer4).setLayoutParams(new LinearLayout.LayoutParams(widthOfProgress, LinearLayout.LayoutParams.MATCH_PARENT));

                    if(videoPausedPointPercentage > 95) {
                        convertView.findViewById(R.id.recommendedItemRL4).setAlpha(0.35f);
                    }
                }

                String imageString = "";
                if(recommendedItemPairDTOList.get(position).getRecommendedItemDTO4().getType() != null &&
                        recommendedItemPairDTOList.get(position).getRecommendedItemDTO4().getType().equals("channel")) {
                    imageString = recommendedItemPairDTOList.get(position).getRecommendedItemDTO4().getSpotLightPoster();
                } else {
                    imageString = "https://images.dotstudiopro.com/"+recommendedItemPairDTOList.get(position).getRecommendedItemDTO4().getThumb();
                }
                imageString = CommonUtils.replaceDotstudioproWithMyspotlightForImage(imageString);

                String imageURLAddress = imageString + "/" + imageWidth + "/" + imageHeight;
                //System.out.println("ImageData4:" + imageURLAddress);
                Uri uri = Uri.parse(imageURLAddress);
                ((SimpleDraweeView) convertView.findViewById(R.id.recommendedItemIV4)).setImageURI(uri);
                convertView.findViewById(R.id.recommendedItemIV4).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickHandlerForChannelsImage(v.getTag(v.getId()).toString());//clickHandlerForChannelsImage(v.getTag().toString());
                    }
                });

                TextView recommendedItemTV = ((TextView) convertView.findViewById(R.id.recommendedItemTV4));
                recommendedItemTV.setText(recommendedItemPairDTOList.get(position).getRecommendedItemDTO4().getTitle());
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
                recommendedItemTV.setTextColor(titleColour);
                recommendedItemTV.setIncludeFontPadding(false);
                //recommendedItemTV.setShadowLayer(2f, -1, 1, Color.BLACK);

                TextView recommendedSeriesItemTV = ((TextView) convertView.findViewById(R.id.recommendedSeriesItemTV4));
                recommendedSeriesItemTV.setText(recommendedItemPairDTOList.get(position).getRecommendedItemDTO4().getSeriesTitle());
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
                recommendedSeriesItemTV.setTextColor(seriesColour);
                recommendedSeriesItemTV.setIncludeFontPadding(false);
                //recommendedSeriesItemTV.setShadowLayer(2f, -1, 1, Color.BLACK);

                if(isLockToBeShown) {
                    //do not delete this, as this is used to display the lock image on the channel poster
                    addLockButton(recommendedItemPairDTOList.get(position).getRecommendedItemDTO4(), (RelativeLayout)convertView.findViewById(R.id.recommendedItemRL4));
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        } else {
            convertView.findViewById(R.id.recommendedItemRL4).setVisibility(View.INVISIBLE);
        }


        return convertView;
    }

    //this is to add a lock button to the channel poster
    public void addLockButton(RecommendedItemDTO categoriesDTO, RelativeLayout fl) {
        if(categoriesDTO.isProduct()) {
            ImageView lockButton = new ImageView(activity);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.width = 57;
            params.height = 80;
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

            lockButton.setLayoutParams(new RelativeLayout.LayoutParams(40, 40));
            lockButton.setPadding(0, 0, 10, 10);
            lockButton.setLayoutParams(params);

            //lockButton.setImageDrawable(new IconDrawable(activity, FontAwesomeIcons.fa_lock).color(Color.parseColor("#ffffff")));
            lockButton.setImageDrawable(activity.getResources().getDrawable(R.drawable.lock));

            fl.addView(lockButton);

        }
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
