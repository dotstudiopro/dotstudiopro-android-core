package com.dotstudioz.dotstudioPRO.corelibrary.components;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v4.view.ViewPager;
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
import com.dotstudioz.dotstudioPRO.corelibrary.adapters.VideoOrChannelItemPagerAdapter;
import com.dotstudioz.dotstudioPRO.corelibrary.adapters.VideoOrChannelItemPagerWithBorderAdapter;
import com.dotstudioz.dotstudioPRO.services.constants.ApplicationConstants;
import com.dotstudioz.dotstudioPRO.services.constants.FontsConstants;
import com.dotstudioz.dotstudioPRO.models.dto.SpotLightCategoriesDTO;
import com.dotstudioz.dotstudioPRO.models.dto.SpotLightChannelDTO;
import com.dotstudioz.dotstudioPRO.models.dto.VideoInfoDTO;
import com.facebook.drawee.view.SimpleDraweeView;
import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;

import java.util.List;

/**
 * Created by Admin on 16-01-2016.
 */
public class CategoriesPageComponent_V1 implements CategorySliderCenterTextViewExtraOne.ICategorySliderCenterTextViewExtraOne {

    public interface ICategoriesPageComponent_V1 {
        void clickHandlerForChannelsImage(String tagSent);
        void onVideoImageClickedOnCategory();
        void seeAllChannelsClickHandler();
        void seeAllChannelsClickHandlerHelper(View v);
    }

    private ICategoriesPageComponent_V1 iCategoriesPageComponent_V1;

    @Override
    public void seeAllChannelsClickHandlerHelper(View v) {
        iCategoriesPageComponent_V1.seeAllChannelsClickHandlerHelper(v);
    }


    private Context activity;
    private LinearLayout container;
    private HorizontalScrollView hsv;
    public int titleFontSize = 16;
    public float videoOrChanneltitleFontSize = 16f;
    public float videoTitleTVFontSize = 12f;
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

    public float categoryNameTVFontSize = 14f;
    public Typeface categoryNameTVTypeFace = FontsConstants.alwaysForeverBold;
    public int categoryNameTVFontColor = Color.parseColor("#ffffff");
    public int viewAllIVColor = Color.parseColor("#eeeeee");

    public int featuredSliderIndicator = Color.parseColor("#eeeeee");
    public int activeFeaturedSliderIndicator = Color.parseColor("#6dec68");

    public boolean isVideoOrChannelItemPagerWithBorder = false;

    public CategoriesPageComponent_V1(Activity mContext, LinearLayout mContainer) {
        activity = (Context) mContext;
        container = mContainer;

        if (mContext instanceof CategoriesPageComponent_V1.ICategoriesPageComponent_V1)
            iCategoriesPageComponent_V1 = (CategoriesPageComponent_V1.ICategoriesPageComponent_V1) mContext;
        else
            throw new RuntimeException(mContext.toString()+ " must implement ICategoriesPageComponent_V1");
    }

    private void clickHandlerForChannelsImage(String tagSent) {
        /*activity.isComingFromChannelsPage = false;
        activity.isComingFromSearch = false;
        activity.onKeyDownGoBackToChannelsPage = false;
        activity.clickHandlerForChannelsImageInCategories(tagSent);*/
        iCategoriesPageComponent_V1.clickHandlerForChannelsImage(tagSent);
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
                iCategoriesPageComponent_V1.onVideoImageClickedOnCategory();
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

    private void setImageToSliderAndCenterTextExtraOne(SliderLayout sliderShow, VideoInfoDTO videoInfoDTO, String imageString, String categoryString, int index) {
        CategorySliderCenterTextViewExtraOne categorySliderCenterTextView = new CategorySliderCenterTextViewExtraOne(activity, this);
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

        String imageString1 = "https://images.dotstudiopro.com/"+imageString;
        imageString1 = CommonUtils.replaceDotstudioproWithMyspotlightForImage(imageString1);
        categorySliderCenterTextView.myImage(imageString1);
        categorySliderCenterTextView.setOnSliderClickListener((BaseSliderView.OnSliderClickListener)activity);
        sliderShow.addSlider(categorySliderCenterTextView);
    }

    public void createFeaturedSlider(SpotLightCategoriesDTO spotLightCategoriesDTO, DisplayMetrics displaymetrics) {
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
                setImageToSliderAndCenterTextExtraOne(slider1, spotLightCategoriesDTO.getVideoInfoDTOList().get(i), spotLightCategoriesDTO.getVideoInfoDTOList().get(i).getThumb() + "/" + width + "/" + imageHeight, spotLightCategoriesDTO.getCategoryName(), i);
            }

            //slider1.setDuration(10000L);
            slider1.setIndicatorVisibility(PagerIndicator.IndicatorVisibility.Invisible);
            //slider1.setPresetTransformer(SliderLayout.Transformer.Accordion);
            slider1.setPresetTransformer(SliderLayout.Transformer.Default);
            slider1.setIndicatorVisibility(PagerIndicator.IndicatorVisibility.Invisible);
            //slider1.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
            slider1.stopAutoCycle();
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
        int width = displaymetrics.widthPixels;
        int imageWidth = ((3* width)/4);
        int imageHeight = ((imageWidth * 9) / 16);

        RelativeLayout extraSpaceRL = new RelativeLayout(activity);
        extraSpaceRL.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, 10));
        container.addView(extraSpaceRL);

        for(int i = 0; i < sliderCreationList.size(); i++) {
            SpotLightCategoriesDTO spotLightCategoriesDTO1 = (SpotLightCategoriesDTO) sliderCreationList.get(i);
            if (spotLightCategoriesDTO1.getCategorySlug().equals("hero-showcase")) {
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
        pager.setBackgroundColor(pagerBackgroundColor);
        ((LinearLayout.LayoutParams)pager.getLayoutParams()).setMargins(15, 0, 0, 15);

        int textHeight = getHeight(activity, "TestValue", (int)videoOrChanneltitleFontSize, imageWidth);
        //System.out.println("TEXT_HEIGHT==>"+textHeight);

        ViewPager viewPager = new ViewPager(activity);
        if(spotLightCategoriesDTO1.getVideoInfoDTOList().size() > 0 ||
                spotLightCategoriesDTO1.isParentChannel() ||
                spotLightCategoriesDTO1.getCategorySlug().equals("hero-showcase"))
            viewPager.setLayoutParams(new FrameLayout.LayoutParams(imageWidth, imageHeight+textHeight));
        else
            viewPager.setLayoutParams(new FrameLayout.LayoutParams(imageWidth, imageHeight));



        if(spotLightCategoriesDTO1.getSpotLightChannelDTOList().size() > 0 &&
                spotLightCategoriesDTO1.getVideoInfoDTOList().size() == 0 &&
                !spotLightCategoriesDTO1.isParentChannel()) {

        } else if(spotLightCategoriesDTO1.getSpotLightChannelDTOList().size() > 0 &&
                spotLightCategoriesDTO1.getVideoInfoDTOList().size() == 0 &&
                spotLightCategoriesDTO1.isParentChannel()) {

        } else {
            viewPager.setLayoutParams(new FrameLayout.LayoutParams(imageWidth, (imageHeight+(textHeight*2))));
        }



        viewPager.setBackgroundColor(viewPagerBackgroundColor);

        if(isVideoOrChannelItemPagerWithBorder) {
            VideoOrChannelItemPagerWithBorderAdapter adapter = new VideoOrChannelItemPagerWithBorderAdapter(activity);
            adapter.spotLightCategoriesDTO = spotLightCategoriesDTO1;
            adapter.videoInfoDTOArrayList = spotLightCategoriesDTO1.getVideoInfoDTOList();
            adapter.categoryName = spotLightCategoriesDTO1.getCategoryName();
            adapter.imageHeight = imageHeight;
            adapter.imageWidth = imageWidth;
            adapter.videoOrChanneltitleFontSize = videoOrChanneltitleFontSize;
            //adapter.setCustomFontEnabledForVideoTitle(customFontEnabledForVideoTitle);
            adapter.setCustomFontEnabledForVideoTitle(viewPagerCustomFontEnabledForVideoTitle);
            adapter.setCustomFontForVideoTitle(viewPagerCustomFontForVideoTitle);
            //adapter.setTitleColour(viewPagerTitleColor);

            viewPager.setAdapter(adapter);
        } else {
            VideoOrChannelItemPagerAdapter adapter = new VideoOrChannelItemPagerAdapter(activity);
            adapter.spotLightCategoriesDTO = spotLightCategoriesDTO1;
            adapter.videoInfoDTOArrayList = spotLightCategoriesDTO1.getVideoInfoDTOList();
            adapter.categoryName = spotLightCategoriesDTO1.getCategoryName();
            adapter.imageHeight = imageHeight;
            adapter.imageWidth = imageWidth;
            adapter.videoTitleTVFontSize = videoTitleTVFontSize;
            adapter.setCustomFontEnabledForVideoTitle(true);
            adapter.setCustomFontForVideoTitle(FontsConstants.sourcesansproRegular);
            adapter.setCustomFontEnabledForVideoTitle(viewPagerCustomFontEnabledForVideoTitle);
            adapter.setCustomFontForVideoTitle(viewPagerCustomFontForVideoTitle);
            adapter.setTitleColour(Color.parseColor("#878d90"));
            viewPager.setAdapter(adapter);
        }

        if(CommonUtils.isActuallyTabletDevice((Activity)activity))
            viewPager.setOffscreenPageLimit(4);
        else
            viewPager.setOffscreenPageLimit(3);
        viewPager.setPageMargin(10);
        viewPager.setClipChildren(false);

        pager.mPager = viewPager;
        pager.addView(viewPager);

        LinearLayout rl = new LinearLayout(activity);
        LinearLayout.LayoutParams paramsRL = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        paramsRL.setMargins(0,20,0,10);
        paramsRL.gravity = Gravity.CENTER;
        rl.setLayoutParams(paramsRL);
        ((LinearLayout.LayoutParams)rl.getLayoutParams()).setMargins(0, 20, 0, 10);
        ((LinearLayout.LayoutParams)rl.getLayoutParams()).gravity = Gravity.CENTER;
        rl.setOrientation(LinearLayout.HORIZONTAL);
        rl.setTag(spotLightCategoriesDTO1.getCategorySlug());
        rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iCategoriesPageComponent_V1.seeAllChannelsClickHandlerHelper(v);
            }
        });

        TextView categoryNameTV = new TextView(activity);
        categoryNameTV.setLayoutParams(new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT,1f));
        categoryNameTV.setText(spotLightCategoriesDTO1.getCategoryName());
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
                iCategoriesPageComponent_V1.seeAllChannelsClickHandlerHelper(v);
            }
        });

        if(spotLightCategoriesDTO1.getCategorySlug().equals("hero-showcase"))
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
                iCategoriesPageComponent_V1.seeAllChannelsClickHandlerHelper(v);
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
                iCategoriesPageComponent_V1.seeAllChannelsClickHandlerHelper(v);
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
                iCategoriesPageComponent_V1.seeAllChannelsClickHandlerHelper(v);
            }
        });



        rl.addView(categoryNameTV);
        rl.addView(viewAllIV1);
        rl.addView(viewAllIV2);
        rl.addView(viewAllIV3);

        container.addView(rl);
        container.addView(pager);
    }

    public void createPortraitSlidersForTabletHelper(List<SpotLightCategoriesDTO> sliderCreationList, DisplayMetrics displaymetrics) {
        int width = displaymetrics.widthPixels;
        int imageWidth = ((2* width)/5);
        /*int imageWidth = ((3* width)/4);*/
        int imageHeight = ((imageWidth * 9) / 16);

        RelativeLayout extraSpaceRL = new RelativeLayout(activity);
        extraSpaceRL.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, 10));
        container.addView(extraSpaceRL);

        for(int i = 0; i < sliderCreationList.size(); i++) {
            SpotLightCategoriesDTO spotLightCategoriesDTO1 = (SpotLightCategoriesDTO) sliderCreationList.get(i);
            if (spotLightCategoriesDTO1.getCategorySlug().equals("hero-showcase")) {
                int newImageWidth = ((2* width)/5);
                int newImageHeight = ((newImageWidth * 16) / 9);
                createAndAddPagerToContainer(spotLightCategoriesDTO1, newImageWidth, newImageHeight);
            } else {
                createAndAddPagerToContainer(spotLightCategoriesDTO1, imageWidth, imageHeight);
            }
        }

        container.addView(CommonUtils.getInstance().getExtraSpaceView(activity, ApplicationConstants.BOTTOM_PADDING_FOR_TAB_BAR));
    }

    public void createLandscapeSlider(List<SpotLightCategoriesDTO> sliderCreationList, DisplayMetrics displaymetrics) {
        int width = displaymetrics.widthPixels;
        //int imageWidth = ((3* width)/4);
        //int imageHeight = ((imageWidth * 9) / 16);*/
        int imageWidth = ((2* width)/5);
        int imageHeight = ((imageWidth * 9) / 16);

        RelativeLayout extraSpaceRL = new RelativeLayout(activity);
        extraSpaceRL.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, 10));
        container.addView(extraSpaceRL);

        for(int i = 0; i < sliderCreationList.size(); i++) {
            SpotLightCategoriesDTO spotLightCategoriesDTO1 = (SpotLightCategoriesDTO) sliderCreationList.get(i);
            if (spotLightCategoriesDTO1.getCategorySlug().equals("hero-showcase")) {
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
        int width = displaymetrics.widthPixels;
        int imageWidth = ((2* width)/5);
        int imageHeight = ((imageWidth * 9) / 16);

        RelativeLayout extraSpaceRL = new RelativeLayout(activity);
        extraSpaceRL.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, 10));
        container.addView(extraSpaceRL);

        for(int i = 0; i < sliderCreationList.size(); i++) {
            SpotLightCategoriesDTO spotLightCategoriesDTO1 = (SpotLightCategoriesDTO) sliderCreationList.get(i);
            if (spotLightCategoriesDTO1.getCategorySlug().equals("hero-showcase")) {
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