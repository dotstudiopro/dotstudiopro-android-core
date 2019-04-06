package com.dotstudioz.dotstudioPRO.corelibrary.components;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.text.Layout;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.dotstudioz.dotstudioPRO.corelibrary.adapters.VideoOrChannelItemPagerAdapter_V1;
import com.dotstudioz.dotstudioPRO.corelibrary.adapters.VideoOrChannelItemPagerWithBorderAdapter_V2;
import com.dotstudioz.dotstudioPRO.services.constants.ApplicationConstants;
import com.dotstudioz.dotstudioPRO.corelibrary.constants.FontsConstants;
import com.dotstudioz.dotstudioPRO.models.dto.LiveScheduleDataDTO;
import com.dotstudioz.dotstudioPRO.models.dto.SpotLightCategoriesDTO;
import com.dotstudioz.dotstudioPRO.models.dto.SpotLightChannelDTO;
import com.dotstudioz.dotstudioPRO.models.dto.VideoInfoDTO;
import com.dotstudioz.dotstudioPRO.corelibrary.util.CommonUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;
import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Admin on 16-01-2016.
 */
public class CategoriesPageComponent_V2 implements CategorySliderCenterTextViewExtraOne_V1.ICategorySliderCenterTextViewExtraOne {

    public interface ICategoriesPageComponent_V2 {
        void clickHandlerForChannelsImage(String tagSent);
        void onVideoImageClickedOnCategory();
        void seeAllChannelsClickHandler();
        void seeAllChannelsClickHandlerHelper(View v);
        void onSliderClickWatchButton(String descToPass, String myImageToPass, int index);
    }

    private ICategoriesPageComponent_V2 iCategoriesPageComponent_V2;

    @Override
    public void seeAllChannelsClickHandlerHelper(View v) {
        if(ApplicationConstants.CURRNET_LOADED_VIEW != ApplicationConstants.CATEGORIES_VIEW)
            return;
        iCategoriesPageComponent_V2.seeAllChannelsClickHandlerHelper(v);
    }


    private Context activity;
    private LinearLayout container;
    private HorizontalScrollView hsv;
    public int titleFontSize = 16;
    public float videoOrChanneltitleFontSize = 16f;
    public float videoTitleTVFontSize = 12f;
    public int videoTitleColour = 0;
    public int actorColour = 0;
    public boolean showActor = false;
    public int childProgressColour = Color.parseColor("#000000");
    public Typeface titleFontTypeFace = FontsConstants.sourcesansproBold;
    public int descFontSize = 14;
    public Typeface descFontTypeFace = FontsConstants.sourcesansproRegular;
    public int titleShadowColor;
    public int titleFontColor;
    public int descShadowColor;
    public int descFontColor = Color.parseColor("#d6d6d6");
    public int titleFontVisibility = View.GONE;
    public int descFontVisibility = View.GONE;

    public int featuredTitleFontVisibility = View.GONE;
    public int featuredDescFontVisibility = View.GONE;

    public boolean customFontEnabledForVideoTitle;
    public Typeface customFontForVideoTitle = FontsConstants.sourcesansproBold;
    public boolean customFontEnabledForVideoDescription;
    public Typeface customFontForVideoDescription = FontsConstants.sourcesansproBold;

    public int pagerBackgroundColor = Color.parseColor("#2C2F34");
    public int viewPagerBackgroundColor = Color.parseColor("#2C2F34");
    public boolean viewPagerCustomFontEnabledForVideoTitle;
    public Typeface viewPagerCustomFontForVideoTitle = FontsConstants.sourcesansproRegular;
    public int viewPagerTitleColor = Color.parseColor("#2C2F34");

    public boolean categoryNameTVBold = false;
    public float categoryNameTVFontSize = 14f;
    public Typeface categoryNameTVTypeFace = FontsConstants.alwaysForeverBold;
    public int categoryNameTVFontColor = Color.parseColor("#ffffff");
    public int viewAllIVColor = Color.parseColor("#eeeeee");

    public boolean showViewAllText = false;
    public Typeface showViewAllTextTypeFace = FontsConstants.alwaysForeverBold;
    public boolean isShowViewAllTextCustomFont = false;
    public boolean isShowViewAllTextFontSize = false;
    public int showViewAllTextFontSize;
    public boolean isShowViewAllTextFontColor = false;
    public int showViewAllTextFontColor = Color.parseColor("#ffffff");

    public int featuredTitleColour = Color.parseColor("#eeeeee");
    public int featuredSliderIndicator = Color.parseColor("#eeeeee");
    public int activeFeaturedSliderIndicator = Color.parseColor("#6dec68");
    public boolean showWatchVideoButton = false;

    public boolean isVideoOrChannelItemPagerWithBorder = false;

    public DisplayMetrics genericdisplaymetrics;

    public boolean displayChannelPoster = false;
    public boolean hide3Dots = false;

    public boolean isFeaturedCategoryNotPresent = false;

    public boolean autoSlideFlag = true;

    public CategoriesPageComponent_V2(Activity mContext, LinearLayout mContainer) {
        activity = (Context) mContext;
        if(mContainer != null) {
            container = mContainer;
            container.removeAllViews();
        }

        if (mContext instanceof CategoriesPageComponent_V2.ICategoriesPageComponent_V2)
            iCategoriesPageComponent_V2 = (CategoriesPageComponent_V2.ICategoriesPageComponent_V2) mContext;
        else
            throw new RuntimeException(mContext.toString()+ " must implement ICategoriesPageComponent_V2");
    }

    private void clickHandlerForChannelsImage(String tagSent) {
        /*activity.isComingFromChannelsPage = false;
        activity.isComingFromSearch = false;
        activity.onKeyDownGoBackToChannelsPage = false;
        activity.clickHandlerForChannelsImageInCategories(tagSent);*/
        if(ApplicationConstants.CURRNET_LOADED_VIEW != ApplicationConstants.CATEGORIES_VIEW)
            return;
        iCategoriesPageComponent_V2.clickHandlerForChannelsImage(tagSent);
    }

    public void onVideoImageClickedOnCategory(String descToPass, String myImageToPass, int index) {
        if(ApplicationConstants.CURRNET_LOADED_VIEW != ApplicationConstants.CATEGORIES_VIEW)
            return;
        iCategoriesPageComponent_V2.onSliderClickWatchButton(descToPass, myImageToPass, index);
    }

    private FrameLayout getFrameLayout(SpotLightCategoriesDTO spotLightCategoriesDTO, VideoInfoDTO videoInfoDTO, int imageWidth, int imageHeight) {
        FrameLayout fl = new FrameLayout(activity);
        fl.setLayoutParams(new FrameLayout.LayoutParams((imageWidth+20), (imageHeight+5)));

        SimpleDraweeView siv = new SimpleDraweeView(activity);
        siv.setLayoutParams(new LinearLayout.LayoutParams(imageWidth, imageHeight));
        String imageURL = "https://images.dotstudiopro.com/"+videoInfoDTO.getThumb() + "/" + imageWidth + "/" + imageHeight;
        Log.d("createPortraitSlider", imageURL);
        String tagString = spotLightCategoriesDTO.getCategoryName()+"|"+videoInfoDTO.getThumb();
        siv.setTag(siv.getId(), tagString);
        siv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iCategoriesPageComponent_V2.onVideoImageClickedOnCategory();
            }
        });
        Uri uri = Uri.parse(imageURL);
        siv.setImageURI(uri);

        fl.addView(siv);


        LinearLayout ll = new LinearLayout(activity);
        ll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setGravity(Gravity.BOTTOM);

        TextView videoTitleTV = new TextView(activity);
        videoTitleTV.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        videoTitleTV.setText(videoInfoDTO.getVideoTitle());
        videoTitleTV.setSingleLine(true);
        videoTitleTV.setPadding(15, 0, 20, 10);
        videoTitleTV.setEllipsize(TextUtils.TruncateAt.END);
        videoTitleTV.setTextSize(titleFontSize);
        videoTitleTV.setTypeface(titleFontTypeFace);
        videoTitleTV.setGravity(Gravity.LEFT);
        videoTitleTV.setShadowLayer(2f, -1, 1, titleShadowColor);
        videoTitleTV.setTextColor(titleFontColor);
        videoTitleTV.setVisibility(titleFontVisibility);

        TextView actorsTV = new TextView(activity);
        actorsTV.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        actorsTV.setText(videoInfoDTO.getCasting());
        actorsTV.setSingleLine(true);
        actorsTV.setPadding(15, 0, 20, 10);
        actorsTV.setEllipsize(TextUtils.TruncateAt.END);
        actorsTV.setTextSize(descFontSize);
        actorsTV.setTypeface(descFontTypeFace);
        actorsTV.setGravity(Gravity.LEFT);
        actorsTV.setShadowLayer(2f, -1, 1, descShadowColor);
        actorsTV.setTextColor(descFontColor);
        actorsTV.setVisibility(descFontVisibility);

        ll.addView(videoTitleTV);
        ll.addView(actorsTV);
        fl.addView(ll);

        return fl;
    }

    private FrameLayout getFrameLayout(SpotLightCategoriesDTO spotLightCategoriesDTO, SpotLightChannelDTO spotLightChannelDTO, int imageWidth, int imageHeight) {
        FrameLayout fl = new FrameLayout(activity);
        fl.setLayoutParams(new FrameLayout.LayoutParams((imageWidth+20), (imageHeight+5)));

        SimpleDraweeView siv = new SimpleDraweeView(activity);
        siv.setLayoutParams(new LinearLayout.LayoutParams(imageWidth, imageHeight));
        String imageURL = spotLightChannelDTO.getSpotlightImage() + "/" + imageWidth + "/" + imageHeight;
        Log.d("createPortraitSlider", imageURL);
        siv.setTag(spotLightCategoriesDTO.getCategorySlug());
        /*String tagString = spotLightCategoriesDTO.getCategoryName()+"|"+spotLightChannelDTO.getSlug();
        siv.setTag(siv.getId(), tagString);
        siv.setOnClickListener(activity.seeAllChannelsClickHandler);*/

        String channelIdPlusCategorySlug = spotLightChannelDTO.getId()+"|"+spotLightCategoriesDTO.getCategorySlug();
        siv.setTag(siv.getId(), channelIdPlusCategorySlug);
        siv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickHandlerForChannelsImage(v.getTag(v.getId()).toString());//clickHandlerForChannelsImage(v.getTag().toString());
            }
        });

        Uri uri = Uri.parse(imageURL);
        siv.setImageURI(uri);

        fl.addView(siv);


        LinearLayout ll = new LinearLayout(activity);
        ll.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setGravity(Gravity.BOTTOM);

        TextView videoTitleTV = new TextView(activity);
        videoTitleTV.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        //videoTitleTV.setText(spotLightChannelDTO.getTitle());
        videoTitleTV.setSingleLine(true);
        videoTitleTV.setPadding(15, 0, 20, 0);
        videoTitleTV.setEllipsize(TextUtils.TruncateAt.END);
        videoTitleTV.setTextSize(titleFontSize);
        videoTitleTV.setTypeface(titleFontTypeFace);
        videoTitleTV.setGravity(Gravity.LEFT);
        videoTitleTV.setShadowLayer(2f, -1, 1, titleShadowColor);
        videoTitleTV.setTextColor(titleFontColor);
        videoTitleTV.setVisibility(titleFontVisibility);

        TextView actorsTV = new TextView(activity);
        actorsTV.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        //actorsTV.setText(videoInfoDTO.getCasting());
        actorsTV.setSingleLine(true);
        actorsTV.setPadding(15, 0, 20, 10);
        actorsTV.setEllipsize(TextUtils.TruncateAt.END);
        actorsTV.setTextSize(descFontSize);
        actorsTV.setTypeface(descFontTypeFace);
        actorsTV.setGravity(Gravity.LEFT);
        actorsTV.setShadowLayer(2f, -1, 1, descShadowColor);
        actorsTV.setTextColor(descFontColor);
        actorsTV.setVisibility(descFontVisibility);

        ll.addView(videoTitleTV);
        ll.addView(actorsTV);
        fl.addView(ll);

        return fl;
    }

    private void setImageToSliderAndCenterTextExtraOne(SliderLayout sliderShow, VideoInfoDTO videoInfoDTO, String imageString, String categoryString, int index, int activeColor, int inActiveColor, int featuredTitleColour, boolean showWatchVidBut) {
        CategorySliderCenterTextViewExtraOne_V1 categorySliderCenterTextView = new CategorySliderCenterTextViewExtraOne_V1(activity, this);
        //imageString = "http://image.dotstudiopro.com/55c8e76397f8154b12b8c5b9";
        categorySliderCenterTextView.description(categoryString);
        if(videoInfoDTO.getDescription() != null && videoInfoDTO.getDescription().length() > 0)
            categorySliderCenterTextView.setVideoDescription(videoInfoDTO.getDescription());

        if(videoInfoDTO.getSeriesTitle() != null && videoInfoDTO.getSeriesTitle().length() > 0)
            categorySliderCenterTextView.setVideoDescription(videoInfoDTO.getSeriesTitle());

        /*else
            categorySliderCenterTextView.setVideoDescription("Description 'place holder' as there is no description assigned for this video!");*/
        categorySliderCenterTextView.setIndexOfImage(index);
        categorySliderCenterTextView.setFeaturedTitleVisibility(featuredTitleFontVisibility);
        categorySliderCenterTextView.setVideoTitle(videoInfoDTO.getVideoTitle());
        categorySliderCenterTextView.setFeaturedDescVisibility(featuredDescFontVisibility);
        categorySliderCenterTextView.setVideoActors(videoInfoDTO.getCasting());
        categorySliderCenterTextView.setFeaturedTag("featured");
        categorySliderCenterTextView.setCustomFontEnabledForVideoTitle(customFontEnabledForVideoTitle);
        categorySliderCenterTextView.setCustomFontForVideoTitle(customFontForVideoTitle);
        categorySliderCenterTextView.setCustomFontEnabledForVideoDescription(customFontEnabledForVideoDescription);
        categorySliderCenterTextView.setCustomFontForVideoDescription(customFontForVideoDescription);
        categorySliderCenterTextView.activeColor = activeColor;
        categorySliderCenterTextView.inActiveColor = inActiveColor;
        categorySliderCenterTextView.featuredTitleColour = featuredTitleColour;
        categorySliderCenterTextView.showWatchVideoButton = showWatchVidBut;
        if(videoInfoDTO != null && videoInfoDTO.getSource() != null && videoInfoDTO.getSource().toLowerCase().equals("live")) {
            categorySliderCenterTextView.isLiveVideo = true;
        } else {
            categorySliderCenterTextView.isLiveVideo = false;
        }
        String imageString1 = "https://images.dotstudiopro.com/"+imageString;
        imageString1 = CommonUtils.replaceDotstudioproWithMyspotlightForImage(imageString1);
        categorySliderCenterTextView.myImage(imageString1);
        categorySliderCenterTextView.setOnSliderClickListener((BaseSliderView.OnSliderClickListener)activity);
        sliderShow.addSlider(categorySliderCenterTextView);
    }

    public void addLiveScheduleView(DisplayMetrics displayMetrics, LiveScheduleDataDTO liveScheduleDataDTO) {
        //container.addView(getLiveSchduleView(displayMetrics, liveScheduleDataDTO));
        getLiveSchduleView(displayMetrics, liveScheduleDataDTO);
    }

    public LinearLayout liveScheduleLinearLayout;
    public LinearLayout getLiveSchduleView(DisplayMetrics displayMetrics, LiveScheduleDataDTO liveScheduleDataDTO) {

        if(liveScheduleLinearLayout == null) {
            liveScheduleLinearLayout = new LinearLayout(activity);
            liveScheduleLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            liveScheduleLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
            container.addView(liveScheduleLinearLayout);
        }
        liveScheduleLinearLayout.removeAllViews();

        LinearLayout llContainer1 = new LinearLayout(activity);
        llContainer1.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));
        llContainer1.setOrientation(LinearLayout.VERTICAL);

        TextView tv = new TextView(activity);
        tv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        if(liveScheduleDataDTO != null && liveScheduleDataDTO.getScheduledStartTime() != null) {
            tv.setText("" + liveScheduleDataDTO.getScheduledStartTime().getHours() + ":" + liveScheduleDataDTO.getScheduledStartTime().getMinutes());
        } else
            tv.setText("");
        tv.setTypeface(FontsConstants.sourcesansproBold);
        tv.setTextColor(Color.parseColor("#4A68F2"));
        tv.setTextSize(16);

        /*TextView tv2 = new TextView(activity);
        tv2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        tv2.setPadding(7, 0, 0, 0);
        tv2.setText("EST");
        tv2.setTextColor(Color.parseColor("#ffffff"));
        tv2.setTextSize(14);
        tv2.setTypeface(FontsConstants.sourcesansproBold);*/

        LinearLayout llC1 = new LinearLayout(activity);
        llC1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        llC1.setOrientation(LinearLayout.HORIZONTAL);
        llC1.addView(tv);
        //llC1.addView(tv2);



        final TextView tv3 = new TextView(activity);
        tv3.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        if(liveScheduleDataDTO != null && liveScheduleDataDTO.getTitle() != null)
            tv3.setText(liveScheduleDataDTO.getTitle());
        else
            tv3.setText("");
        tv3.setTextColor(Color.parseColor("#ffffff"));
        tv3.setTextSize(17);
        tv3.setSingleLine(true);
        tv3.setEllipsize(TextUtils.TruncateAt.END);
        tv3.setTypeface(FontsConstants.sourcesansproBold);
        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean flag = isTextTruncated(((TextView)v).getText().toString(), ((TextView)v));
                if(flag) {
                    ((TextView)v).setSingleLine(false);
                } else {
                    ((TextView)v).setSingleLine(true);
                }
                try {

                    boolean flag1 = isTextTruncated(((TextView)((LinearLayout)v.getParent()).getChildAt(2)).getText().toString(), ((TextView)((LinearLayout)v.getParent()).getChildAt(2)));
                    if(flag1) {
                        ((TextView)((LinearLayout)v.getParent()).getChildAt(2)).setSingleLine(false);
                    } else {
                        ((TextView)((LinearLayout)v.getParent()).getChildAt(2)).setSingleLine(true);
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        });


        TextView tv4 = new TextView(activity);
        tv4.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        if(liveScheduleDataDTO != null && liveScheduleDataDTO.getDescription() != null)
            tv4.setText(liveScheduleDataDTO.getDescription());
        else
            tv4.setText("");
        tv4.setTextColor(Color.parseColor("#ffffff"));
        tv4.setTextSize(12);
        tv4.setEllipsize(TextUtils.TruncateAt.END);
        tv4.setSingleLine(true);
        tv4.setTypeface(FontsConstants.sourcesansproRegular);
        tv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean flag = isTextTruncated(((TextView)v).getText().toString(), ((TextView)v));
                if(flag) {
                    ((TextView)v).setSingleLine(false);
                } else {
                    ((TextView)v).setSingleLine(true);
                }
                try {

                    boolean flag1 = isTextTruncated(((TextView)((LinearLayout)v.getParent()).getChildAt(1)).getText().toString(), ((TextView)((LinearLayout)v.getParent()).getChildAt(1)));
                    if(flag1) {
                        ((TextView)((LinearLayout)v.getParent()).getChildAt(1)).setSingleLine(false);
                    } else {
                        ((TextView)((LinearLayout)v.getParent()).getChildAt(1)).setSingleLine(true);
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        });

        llContainer1.addView(llC1);
        llContainer1.addView(tv3);
        llContainer1.addView(tv4);


        /*ImageView imv1 = new ImageView(activity);
        imv1.setLayoutParams(new LinearLayout.LayoutParams(150, 150));
        imv1.setImageResource(R.drawable.placeholder4);
        imv1.setScaleType(ImageView.ScaleType.CENTER_CROP);*/

        int imageWidth = ((displayMetrics.widthPixels*20)/100);
        int imageHeight = ((displayMetrics.widthPixels*20)/100);
        String imageString = liveScheduleDataDTO.getThumb()+"/"+imageWidth+"/"+imageHeight;
        Uri uri1 = Uri.parse(imageString);
        SimpleDraweeView videoThumbnailImageView = new SimpleDraweeView(activity);
        videoThumbnailImageView.setLayoutParams(new LinearLayout.LayoutParams(imageWidth, imageHeight));
        videoThumbnailImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        GenericDraweeHierarchy hierarchy;
        hierarchy = videoThumbnailImageView.getHierarchy();
        Picasso.with(activity).load(uri1).into(videoThumbnailImageView);

        liveScheduleLinearLayout.addView(llContainer1);

        liveScheduleLinearLayout.addView(videoThumbnailImageView);
        liveScheduleLinearLayout.setPadding(10, 10, 10, 10);

        return liveScheduleLinearLayout;
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

    public boolean showIndicatorBackgroundColor = false;
    public int indicatorBackgroundResource = 0;
    public boolean isIndicatorBackgroundResource = false;
    public void createFeaturedSlider(SpotLightCategoriesDTO spotLightCategoriesDTO, DisplayMetrics displaymetrics) {
        if(displaymetrics != null)
            genericdisplaymetrics = displaymetrics;
        if (spotLightCategoriesDTO.getVideoInfoDTOList().size() > 0) {
            int height = displaymetrics.heightPixels;
            int width = displaymetrics.widthPixels;

            LinearLayout llContainer = new LinearLayout(activity);
            llContainer.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            llContainer.setOrientation(LinearLayout.HORIZONTAL);

            SliderLayout slider1 = new SliderLayout(activity);
            slider1.setAlwaysDrawnWithCacheEnabled(true);

            int imageHeight = 0;
            if (activity.getResources().getConfiguration().orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT ||
                    activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                //imageHeight = (width / 2);
                imageHeight = ((width * 9) / 16);
            } else if (activity.getResources().getConfiguration().orientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE ||
                    activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                //imageHeight = height;
                imageHeight = ((width * 9) / 16);
            }
            LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, imageHeight, 1f);
            slider1.setLayoutParams(params1);
            for (int i = 0; i < spotLightCategoriesDTO.getVideoInfoDTOList().size(); i++) {
            //for (int i = 0; i < ((spotLightCategoriesDTO.getVideoInfoDTOList().size()>4)?5:spotLightCategoriesDTO.getVideoInfoDTOList().size()); i++) {
                System.out.println("spotLightCategoriesDTO.getVideoInfoDTOList().get(i).getSource()==>"+spotLightCategoriesDTO.getVideoInfoDTOList().get(i).getSource());
                setImageToSliderAndCenterTextExtraOne(slider1, spotLightCategoriesDTO.getVideoInfoDTOList().get(i), spotLightCategoriesDTO.getVideoInfoDTOList().get(i).getThumb() + "/" + width + "/" + imageHeight, spotLightCategoriesDTO.getCategoryName(), i, activeFeaturedSliderIndicator, featuredSliderIndicator, featuredTitleColour, showWatchVideoButton);
            }

            //slider1.setDuration(10000L);
            slider1.setIndicatorVisibility(PagerIndicator.IndicatorVisibility.Invisible);
            //slider1.setPresetTransformer(SliderLayout.Transformer.Accordion);
            slider1.setPresetTransformer(SliderLayout.Transformer.Default);
            slider1.setIndicatorVisibility(PagerIndicator.IndicatorVisibility.Invisible);
            //slider1.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
            slider1.stopAutoCycle();
            if(autoSlideFlag)
                slider1.startAutoCycle(3000L, 3000L, true);
            llContainer.addView(slider1);
            slider1.setVisibility(View.VISIBLE);

            if(ApplicationConstants.TOP_PADDING_FOR_MAIN_ACTION_BAR == 0)
                ApplicationConstants.TOP_PADDING_FOR_MAIN_ACTION_BAR = ((ApplicationConstants.BOTTOM_PADDING_FOR_TAB_BAR*3)/4) + (((((ApplicationConstants.BOTTOM_PADDING_FOR_TAB_BAR*3)/4))*14)/100);

            RelativeLayout extraSpaceRL = new RelativeLayout(activity);
            extraSpaceRL.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, (ApplicationConstants.TOP_PADDING_FOR_MAIN_ACTION_BAR>0)?ApplicationConstants.TOP_PADDING_FOR_MAIN_ACTION_BAR:ApplicationConstants.TEXT_INPUT_HEIGHT));
            container.addView(extraSpaceRL);

            container.addView(llContainer);

            //Adding the carousal indicator just below the featured categories slider
            featuredSliderIndicatorComponent = new FeaturedSliderIndicatorComponent(activity);
            featuredSliderIndicatorComponent.showIndicatorBackgroundColor = showIndicatorBackgroundColor;
            featuredSliderIndicatorComponent.indicatorBackgroundResource = indicatorBackgroundResource;
            featuredSliderIndicatorComponent.isIndicatorBackgroundResource = isIndicatorBackgroundResource;
            featuredSliderIndicatorComponent.setBackgroundColor(featuredSliderIndicator);
            featuredSliderIndicatorComponent.setActiveColor(activeFeaturedSliderIndicator);
            featuredSliderIndicatorComponent.setNumberOfIndicators(spotLightCategoriesDTO.getVideoInfoDTOList().size());
            try {
                container.addView(featuredSliderIndicatorComponent.getFeaturedIndicatorComponent());
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    FeaturedSliderIndicatorComponent featuredSliderIndicatorComponent;

    @Override
    public void selectedSliderIndex(int position) {
        if(featuredSliderIndicatorComponent != null)
            featuredSliderIndicatorComponent.selectedSliderIndex(position);
    }

    public void createPortraitSlider(List<SpotLightCategoriesDTO> sliderCreationList, DisplayMetrics displaymetrics) {
        if(displaymetrics != null)
            genericdisplaymetrics = displaymetrics;
        int width = displaymetrics.widthPixels;
        int imageWidth = ((3* width)/4);
        int imageHeight = ((imageWidth * 9) / 16);

        RelativeLayout extraSpaceRL = new RelativeLayout(activity);
        extraSpaceRL.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, 10));
        container.addView(extraSpaceRL);

        int startingIndex = 0;
        if(isFeaturedCategoryNotPresent)
            startingIndex = 1;
        else
            startingIndex = 0;
        //for(int i = 1; i < sliderCreationList.size(); i++) {
        for(int i = startingIndex; i < sliderCreationList.size(); i++) {
            SpotLightCategoriesDTO spotLightCategoriesDTO1 = (SpotLightCategoriesDTO) sliderCreationList.get(i);
            if (spotLightCategoriesDTO1.getCategorySlug() != null &&
                    spotLightCategoriesDTO1.getCategorySlug().equals("hero-showcase")) {
                int newImageWidth = ((2* width)/5);
                int newImageHeight = ((newImageWidth * 16) / 9);
                createAndAddPagerToContainer(spotLightCategoriesDTO1, newImageWidth, newImageHeight);
            } else {
                createAndAddPagerToContainer(spotLightCategoriesDTO1, imageWidth, imageHeight);
            }
        }

        container.addView(CommonUtils.getInstance().getExtraSpaceView(activity, ApplicationConstants.BOTTOM_PADDING_FOR_TAB_BAR));
    }

    public int getHeight(Context context, String text, int textSize, int deviceWidth) {
        TextView textView = new TextView(context);
        textView.setTypeface(FontsConstants.sourcesansproRegular);
        textView.setPadding(15, 0, 20, 5);
        textView.setText(text);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(deviceWidth, View.MeasureSpec.AT_MOST);
        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        textView.measure(widthMeasureSpec, heightMeasureSpec);
        return textView.getMeasuredHeight();
    }

    private void createAndAddPagerToContainer(SpotLightCategoriesDTO spotLightCategoriesDTO1, int imageWidth, int imageHeight) {
        MultiViewPagerContainer pager = new MultiViewPagerContainer(activity);
        pager.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        //pager.setBackgroundColor(pagerBackgroundColor);
        pager.setBackgroundColor(Color.TRANSPARENT);
        ((LinearLayout.LayoutParams)pager.getLayoutParams()).setMargins(15, 0, 0, 0);

        int textHeight = getHeight(activity, "TestValue", (int)videoOrChanneltitleFontSize, imageWidth);
        //System.out.println("TEXT_HEIGHT==>"+textHeight);

        final ViewPager viewPager = new ViewPager(activity);
        if(spotLightCategoriesDTO1.getVideoInfoDTOList().size() > 0 ||
                spotLightCategoriesDTO1.isParentChannel() ||
                (spotLightCategoriesDTO1.getCategorySlug() != null &&
                spotLightCategoriesDTO1.getCategorySlug().equals("hero-showcase")))
            viewPager.setLayoutParams(new FrameLayout.LayoutParams(imageWidth, imageHeight+textHeight));
        else
            viewPager.setLayoutParams(new FrameLayout.LayoutParams(imageWidth, imageHeight));



        if(spotLightCategoriesDTO1.getSpotLightChannelDTOList().size() > 0 &&
                spotLightCategoriesDTO1.getVideoInfoDTOList().size() == 0 &&
                !spotLightCategoriesDTO1.isParentChannel()) {
            if(displayChannelPoster)  {
                int channelPosterWidth = imageWidth;
                int channelPosterHeight = imageHeight;
                if(genericdisplaymetrics != null) {
                    channelPosterWidth = ((genericdisplaymetrics.widthPixels * 23)/100);
                    channelPosterHeight = channelPosterWidth + (channelPosterWidth/2);
                }
                viewPager.setLayoutParams(new FrameLayout.LayoutParams(channelPosterWidth, channelPosterHeight));
            }
        } else if(spotLightCategoriesDTO1.getSpotLightChannelDTOList().size() > 0 &&
                spotLightCategoriesDTO1.getVideoInfoDTOList().size() == 0 &&
                spotLightCategoriesDTO1.isParentChannel()) {

        } else {
            viewPager.setLayoutParams(new FrameLayout.LayoutParams(imageWidth, (imageHeight+(textHeight*2))));
        }



        //viewPager.setBackgroundColor(viewPagerBackgroundColor);
        viewPager.setBackgroundColor(Color.TRANSPARENT);

        final int sizeOfVideoList;// = spotLightCategoriesDTO1.getVideoInfoDTOList().size();

        if(spotLightCategoriesDTO1.getChildrenSpotLightCategoriesDTOList().size() > 0) {
            sizeOfVideoList = spotLightCategoriesDTO1.getChildrenSpotLightCategoriesDTOList().size();
        } else if(spotLightCategoriesDTO1.getSpotLightChannelDTOList().size() > 0 &&
                spotLightCategoriesDTO1.getVideoInfoDTOList().size() == 0 &&
                !spotLightCategoriesDTO1.isParentChannel()) {
            sizeOfVideoList = spotLightCategoriesDTO1.getSpotLightChannelDTOList().size();
        } else if(spotLightCategoriesDTO1.getSpotLightChannelDTOList().size() > 0 &&
                spotLightCategoriesDTO1.getVideoInfoDTOList().size() == 0 &&
                spotLightCategoriesDTO1.isParentChannel()) {
            sizeOfVideoList = spotLightCategoriesDTO1.getSpotLightChannelDTOList().size();
        } else if(spotLightCategoriesDTO1.getSpotLightChannelDTOList().size() > 0 &&
                spotLightCategoriesDTO1.getVideoInfoDTOList().size() > 0 &&
                !spotLightCategoriesDTO1.isParentChannel()) {
            sizeOfVideoList = spotLightCategoriesDTO1.getVideoInfoDTOList().size();
        } else {
            sizeOfVideoList = spotLightCategoriesDTO1.getVideoInfoDTOList().size();
        }

        if(isVideoOrChannelItemPagerWithBorder) {
            VideoOrChannelItemPagerWithBorderAdapter_V2 adapter = new VideoOrChannelItemPagerWithBorderAdapter_V2(activity);
            adapter.spotLightCategoriesDTO = spotLightCategoriesDTO1;
            adapter.videoInfoDTOArrayList = spotLightCategoriesDTO1.getVideoInfoDTOList();
            try {
                if (spotLightCategoriesDTO1.getSpotLightChannelDTOList().size() == 1 && spotLightCategoriesDTO1.getVideoInfoDTOList().size() == 0) {
                    adapter.videoInfoDTOArrayList = spotLightCategoriesDTO1.getSpotLightChannelDTOList().get(0).getVideoInfoDTOList();
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
            adapter.categoryName = spotLightCategoriesDTO1.getCategoryName();
            adapter.imageHeight = imageHeight;
            adapter.imageWidth = imageWidth;

            int channelPosterWidth = imageWidth;
            int channelPosterHeight = imageHeight;
            if(displayChannelPoster) {
                if (genericdisplaymetrics != null) {
                    channelPosterWidth = ((genericdisplaymetrics.widthPixels * 23) / 100);
                    channelPosterHeight = channelPosterWidth + (channelPosterWidth / 2);
                }
                adapter.channelPosterWidth = channelPosterWidth;
                adapter.channelPosterHeight = channelPosterHeight;
            } else {
                adapter.channelPosterWidth = 0;
                adapter.channelPosterHeight = 0;
            }

            adapter.videoTitleTVFontSize = videoTitleTVFontSize;
            adapter.setCustomFontEnabledForVideoTitle(true);
            adapter.setCustomFontForVideoTitle(FontsConstants.sourcesansproRegular);
            adapter.setCustomFontEnabledForVideoTitle(viewPagerCustomFontEnabledForVideoTitle);
            adapter.setCustomFontForVideoTitle(viewPagerCustomFontForVideoTitle);
            //adapter.setTitleColour(Color.parseColor("#878d90"));
            adapter.setTitleColour(videoTitleColour);
            adapter.setActorColour(actorColour);
            adapter.showActor = showActor;
            adapter.setChildProgressColour(childProgressColour);
            viewPager.setAdapter(adapter);
        } else {
            VideoOrChannelItemPagerAdapter_V1 adapter = new VideoOrChannelItemPagerAdapter_V1(activity);
            adapter.spotLightCategoriesDTO = spotLightCategoriesDTO1;
            adapter.videoInfoDTOArrayList = spotLightCategoriesDTO1.getVideoInfoDTOList();
            try {
                if (spotLightCategoriesDTO1.getSpotLightChannelDTOList().size() == 1 && spotLightCategoriesDTO1.getVideoInfoDTOList().size() == 0) {
                    adapter.videoInfoDTOArrayList = spotLightCategoriesDTO1.getSpotLightChannelDTOList().get(0).getVideoInfoDTOList();
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
            adapter.categoryName = spotLightCategoriesDTO1.getCategoryName();
            adapter.imageHeight = imageHeight;
            adapter.imageWidth = imageWidth;

            int channelPosterWidth = imageWidth;
            int channelPosterHeight = imageHeight;
            if(displayChannelPoster) {
                if (genericdisplaymetrics != null) {
                    channelPosterWidth = ((genericdisplaymetrics.widthPixels * 23) / 100);
                    channelPosterHeight = channelPosterWidth + (channelPosterWidth / 2);
                }
                adapter.channelPosterWidth = channelPosterWidth;
                adapter.channelPosterHeight = channelPosterHeight;
            } else {
                adapter.channelPosterWidth = 0;
                adapter.channelPosterHeight = 0;
            }

            adapter.videoTitleTVFontSize = videoTitleTVFontSize;
            adapter.setCustomFontEnabledForVideoTitle(true);
            adapter.setCustomFontForVideoTitle(FontsConstants.sourcesansproRegular);
            adapter.setCustomFontEnabledForVideoTitle(viewPagerCustomFontEnabledForVideoTitle);
            adapter.setCustomFontForVideoTitle(viewPagerCustomFontForVideoTitle);
            //adapter.setTitleColour(Color.parseColor("#878d90"));
            adapter.setTitleColour(videoTitleColour);
            adapter.setActorsColour(actorColour);
            adapter.showActor = showActor;
            adapter.setChildProgressColour(childProgressColour);
            viewPager.setAdapter(adapter);
        }

        if(CommonUtils.isActuallyTabletDevice((Activity)activity))
            viewPager.setOffscreenPageLimit(4);
        else
            viewPager.setOffscreenPageLimit(3);
        viewPager.setPageMargin(10);
        viewPager.setClipChildren(false);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(position == (sizeOfVideoList-2)) {
                    return;
                }
            }
            @Override
            public void onPageSelected(int position) {
                if(position > (sizeOfVideoList-2)) {
                    viewPager.setCurrentItem(sizeOfVideoList-2, true);
                    return;
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        pager.mPager = viewPager;
        pager.addView(viewPager);

        LinearLayout rl = new LinearLayout(activity);
        LinearLayout.LayoutParams paramsRL = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        paramsRL.setMargins(0,20,0,10);
        paramsRL.gravity = Gravity.CENTER;
        rl.setLayoutParams(paramsRL);
        ((LinearLayout.LayoutParams)rl.getLayoutParams()).setMargins(0, 20, 5, 10);
        ((LinearLayout.LayoutParams)rl.getLayoutParams()).gravity = Gravity.CENTER;
        rl.setOrientation(LinearLayout.HORIZONTAL);
        rl.setTag(spotLightCategoriesDTO1.getCategorySlug());
        rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iCategoriesPageComponent_V2.seeAllChannelsClickHandlerHelper(v);
            }
        });

        TextView categoryNameTV = new TextView(activity);
        categoryNameTV.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT,1f));
        categoryNameTV.setText(spotLightCategoriesDTO1.getCategoryName());
        if(categoryNameTVBold)
            categoryNameTV.setTypeface(categoryNameTVTypeFace, Typeface.BOLD);
        else
            categoryNameTV.setTypeface(categoryNameTVTypeFace);
        categoryNameTV.setSingleLine(true);
        categoryNameTV.setPadding(15, 0, 0, 0);
        categoryNameTV.setEllipsize(TextUtils.TruncateAt.END);
        categoryNameTV.setTextSize(categoryNameTVFontSize);
        categoryNameTV.setAllCaps(false);
        categoryNameTV.setGravity(Gravity.LEFT);
        //categoryNameTV.setTextColor(Color.WHITE);
        categoryNameTV.setTextColor(categoryNameTVFontColor);
        categoryNameTV.setTag(spotLightCategoriesDTO1.getCategorySlug());
        categoryNameTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iCategoriesPageComponent_V2.seeAllChannelsClickHandlerHelper(v);
            }
        });

        if(spotLightCategoriesDTO1.getCategorySlug() != null && spotLightCategoriesDTO1.getCategorySlug().equals("hero-showcase"))
            categoryNameTV.setVisibility(View.INVISIBLE);

        /*Button viewAllTV = new Button(activity);
        viewAllTV.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        viewAllTV.setBackground(null);
        viewAllTV.setText("...");
        viewAllTV.setTypeface(FontsConstants.sourcesansproRegular);
        viewAllTV.setGravity(Gravity.RIGHT);
        viewAllTV.setTextSize(50f);
        //viewAllTV.setTextColor(Color.parseColor("#6f6f6f"));
        viewAllTV.setTextColor(Color.parseColor("#78c4e3"));
        viewAllTV.setTag(spotLightCategoriesDTO1.getCategorySlug());
        viewAllTV.setOnClickListener(activity.seeAllChannelsClickHandler);*/

        ImageView viewAllIV1 = new ImageView(activity);
        viewAllIV1.setLayoutParams(new LinearLayout.LayoutParams(textHeight/5, textHeight/5));
        //viewAllIV1.setPadding(10, 0, 10, 0);
        ((LinearLayout.LayoutParams)viewAllIV1.getLayoutParams()).setMargins(5,0,5,0);
        viewAllIV1.setImageDrawable(new IconDrawable(activity, FontAwesomeIcons.fa_circle).color(viewAllIVColor));
        viewAllIV1.setTag(spotLightCategoriesDTO1.getCategorySlug());
        ((LinearLayout.LayoutParams)viewAllIV1.getLayoutParams()).gravity = Gravity.CENTER;
        viewAllIV1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iCategoriesPageComponent_V2.seeAllChannelsClickHandlerHelper(v);
            }
        });
        ImageView viewAllIV2 = new ImageView(activity);
        viewAllIV2.setLayoutParams(new LinearLayout.LayoutParams(textHeight/5, textHeight/5));
        //viewAllIV2.setPadding(10, 0, 10, 0);
        ((LinearLayout.LayoutParams)viewAllIV2.getLayoutParams()).setMargins(5,0,5,0);
        viewAllIV2.setImageDrawable(new IconDrawable(activity, FontAwesomeIcons.fa_circle).color(viewAllIVColor));
        viewAllIV2.setTag(spotLightCategoriesDTO1.getCategorySlug());
        ((LinearLayout.LayoutParams)viewAllIV2.getLayoutParams()).gravity = Gravity.CENTER;
        viewAllIV2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iCategoriesPageComponent_V2.seeAllChannelsClickHandlerHelper(v);
            }
        });
        ImageView viewAllIV3 = new ImageView(activity);
        viewAllIV3.setLayoutParams(new LinearLayout.LayoutParams(textHeight/5, textHeight/5));
        //viewAllIV3.setPadding(10, 0, 10, 0);
        ((LinearLayout.LayoutParams)viewAllIV3.getLayoutParams()).setMargins(5,0,5,0);
        viewAllIV3.setImageDrawable(new IconDrawable(activity, FontAwesomeIcons.fa_circle).color(viewAllIVColor));
        viewAllIV3.setTag(spotLightCategoriesDTO1.getCategorySlug());
        ((LinearLayout.LayoutParams)viewAllIV3.getLayoutParams()).gravity = Gravity.CENTER;
        viewAllIV3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iCategoriesPageComponent_V2.seeAllChannelsClickHandlerHelper(v);
            }
        });



        rl.addView(categoryNameTV);
        //hide the dots only for decentric
        if(!hide3Dots) {
            rl.addView(viewAllIV1);
            rl.addView(viewAllIV2);
            rl.addView(viewAllIV3);
        }

        if(showViewAllText) {
            TextView showViewAllTextTV = new TextView(activity);
            showViewAllTextTV.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            showViewAllTextTV.setText("View All");
            if(isShowViewAllTextCustomFont)
                showViewAllTextTV.setTypeface(showViewAllTextTypeFace);
            showViewAllTextTV.setPadding(0, 0, 15, 0);
            if(isShowViewAllTextFontSize)
                showViewAllTextTV.setTextSize(showViewAllTextFontSize);
            showViewAllTextTV.setAllCaps(false);
            showViewAllTextTV.setGravity(Gravity.RIGHT);
            if(isShowViewAllTextFontColor)
                showViewAllTextTV.setTextColor(showViewAllTextFontColor);
            showViewAllTextTV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iCategoriesPageComponent_V2.seeAllChannelsClickHandlerHelper(v);
                }
            });

            rl.addView(showViewAllTextTV);
        }

        container.addView(rl);
        container.addView(pager);
    }

    public void createPortraitSlidersForTabletHelper(List<SpotLightCategoriesDTO> sliderCreationList, DisplayMetrics displaymetrics) {
        if(displaymetrics != null)
            genericdisplaymetrics = displaymetrics;
        int width = displaymetrics.widthPixels;
        int imageWidth = ((2* width)/5);
        /*int imageWidth = ((3* width)/4);*/
        int imageHeight = ((imageWidth * 9) / 16);

        RelativeLayout extraSpaceRL = new RelativeLayout(activity);
        extraSpaceRL.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, 10));
        container.addView(extraSpaceRL);

        int startingIndex = 0;
        if(isFeaturedCategoryNotPresent)
            startingIndex = 1;
        else
            startingIndex = 0;
        //for(int i = 1; i < sliderCreationList.size(); i++) {
        for(int i = startingIndex; i < sliderCreationList.size(); i++) {
            SpotLightCategoriesDTO spotLightCategoriesDTO1 = (SpotLightCategoriesDTO) sliderCreationList.get(i);
            if (spotLightCategoriesDTO1.getCategorySlug() != null &&
                    spotLightCategoriesDTO1.getCategorySlug().equals("hero-showcase")) {
                int newImageWidth = ((2* width)/5);
                int newImageHeight = ((newImageWidth * 16) / 9);
                createAndAddPagerToContainer(spotLightCategoriesDTO1, newImageWidth, newImageHeight);
            } else {
                createAndAddPagerToContainer(spotLightCategoriesDTO1, imageWidth, imageHeight);
            }
        }

        container.addView(CommonUtils.getInstance().getExtraSpaceView(activity, (ApplicationConstants.BOTTOM_PADDING_FOR_TAB_BAR+(ApplicationConstants.BOTTOM_PADDING_FOR_TAB_BAR*3/4))));
    }

    public void createLandscapeSlider(List<SpotLightCategoriesDTO> sliderCreationList, DisplayMetrics displaymetrics) {
        if(displaymetrics != null)
            genericdisplaymetrics = displaymetrics;
        int width = displaymetrics.widthPixels;
        //int imageWidth = ((3* width)/4);
        //int imageHeight = ((imageWidth * 9) / 16);*/
        int imageWidth = ((2* width)/5);
        int imageHeight = ((imageWidth * 9) / 16);

        RelativeLayout extraSpaceRL = new RelativeLayout(activity);
        extraSpaceRL.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, 10));
        container.addView(extraSpaceRL);

        int startingIndex = 0;
        if(isFeaturedCategoryNotPresent)
            startingIndex = 1;
        else
            startingIndex = 0;
        //for(int i = 1; i < sliderCreationList.size(); i++) {
        for(int i = startingIndex; i < sliderCreationList.size(); i++) {
            SpotLightCategoriesDTO spotLightCategoriesDTO1 = (SpotLightCategoriesDTO) sliderCreationList.get(i);
            if (spotLightCategoriesDTO1.getCategorySlug() != null &&
                    spotLightCategoriesDTO1.getCategorySlug().equals("hero-showcase")) {
                int newImageWidth = ((2* width)/5);
                int newImageHeight = ((newImageWidth * 16) / 9);
                createAndAddPagerToContainer(spotLightCategoriesDTO1, newImageWidth, newImageHeight);
            } else {
                createAndAddPagerToContainer(spotLightCategoriesDTO1, imageWidth, imageHeight);
            }
        }

        container.addView(CommonUtils.getInstance().getExtraSpaceView(activity, ApplicationConstants.BOTTOM_PADDING_FOR_TAB_BAR));
    }

    public void createLandscapeSlidersForTabletHelper(List<SpotLightCategoriesDTO> sliderCreationList, DisplayMetrics displaymetrics) {
        if(displaymetrics != null)
            genericdisplaymetrics = displaymetrics;
        int width = displaymetrics.widthPixels;
        int imageWidth = ((2* width)/5);
        int imageHeight = ((imageWidth * 9) / 16);

        RelativeLayout extraSpaceRL = new RelativeLayout(activity);
        extraSpaceRL.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, 10));
        container.addView(extraSpaceRL);

        int startingIndex = 0;
        if(isFeaturedCategoryNotPresent)
            startingIndex = 1;
        else
            startingIndex = 0;
        //for(int i = 1; i < sliderCreationList.size(); i++) {
        for(int i = startingIndex; i < sliderCreationList.size(); i++) {
            SpotLightCategoriesDTO spotLightCategoriesDTO1 = (SpotLightCategoriesDTO) sliderCreationList.get(i);
            if (spotLightCategoriesDTO1.getCategorySlug() != null &&
                    spotLightCategoriesDTO1.getCategorySlug().equals("hero-showcase")) {
                int newImageWidth = ((2* width)/5);
                int newImageHeight = ((newImageWidth * 16) / 9);
                createAndAddPagerToContainer(spotLightCategoriesDTO1, newImageWidth, newImageHeight);
            } else {
                createAndAddPagerToContainer(spotLightCategoriesDTO1, imageWidth, imageHeight);
            }
        }

        container.addView(CommonUtils.getInstance().getExtraSpaceView(activity, ApplicationConstants.BOTTOM_PADDING_FOR_TAB_BAR));
    }
}