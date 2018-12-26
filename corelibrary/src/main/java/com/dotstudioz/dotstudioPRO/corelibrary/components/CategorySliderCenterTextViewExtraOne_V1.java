package com.dotstudioz.dotstudioPRO.corelibrary.components;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.dotstudioz.dotstudioPRO.corelibrary.R;
import com.dotstudioz.dotstudioPRO.corelibrary.constants.FontsConstants;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by Admin on 03-08-2015.
 */
public class CategorySliderCenterTextViewExtraOne_V1 extends BaseSliderView implements ViewPagerEx.OnPageChangeListener {
    public CategorySliderCenterTextViewExtraOne_V1(Context context) {
        super(context);
    }
    public CategorySliderCenterTextViewExtraOne_V1(Context context, CategoriesPageComponent_V2 categoriesPageComponent) {
        super(context);
        iCategorySliderCenterTextViewExtraOne = (ICategorySliderCenterTextViewExtraOne) categoriesPageComponent;
    }

    ICategorySliderCenterTextViewExtraOne iCategorySliderCenterTextViewExtraOne;

    public String myImage;
    public String videoTitle;
    public String videoActors;
    public String featuredTag;
    private String videoDescription;
    private boolean isCustomFontEnabledForVideoTitle = false;
    private boolean isCustomFontEnabledForVideoDescription = false;
    private Typeface customFontForVideoTitle;
    private Typeface customFontForVideoDescription;
    private int indexOfImage;
    private int featuredTitleVisibility;
    private int featuredDescVisibility;
    public int activeColor;
    public int inActiveColor;
    public int featuredTitleColour;
    public boolean showWatchVideoButton = false;
    public boolean isLiveVideo = false;

    public String descToPass = null;
    public String myImageToPass = null;

    @Override
    public View getView() {
        View v = LayoutInflater.from(getContext()).inflate(R.layout.slider_render_center_text,null);
        final SimpleDraweeView target = (SimpleDraweeView) v.findViewById(R.id.daimajia_slider_image);
        ProgressBar sliderProgressBar = (ProgressBar)v.findViewById(R.id.slider_progress_bar);

        descToPass = this.videoDescription;
        myImageToPass = this.myImage;

        TextView featuredTextView = (TextView)v.findViewById(R.id.featuredTextView);
        featuredTextView.setTypeface(FontsConstants.tfBold);
        featuredTextView.setShadowLayer(2f, -1, 1, Color.BLACK);
        featuredTextView.setVisibility(View.GONE);

        TextView videoTitleTextView = (TextView) v.findViewById(R.id.videoTitleTextView);
        if (this.isCustomFontEnabledForVideoTitle)
            videoTitleTextView.setTypeface(customFontForVideoTitle);
        videoTitleTextView.setTextColor(featuredTitleColour);
        videoTitleTextView.setShadowLayer(4f, -2, 2, Color.BLACK);
        videoTitleTextView.setText(this.videoTitle);
        videoTitleTextView.setVisibility(View.VISIBLE);
        videoTitleTextView.setVisibility(featuredTitleVisibility);

        TextView videoActorsTextView = (TextView) v.findViewById(R.id.videoActorsTextView);
        if (this.isCustomFontEnabledForVideoDescription)
            videoActorsTextView.setTypeface(customFontForVideoDescription, Typeface.BOLD);
        videoActorsTextView.setShadowLayer(2f, -1, 1, Color.BLACK);
        //videoActorsTextView.setText(this.videoActors);
        videoActorsTextView.setText(this.videoDescription);
        //videoActorsTextView.setTextColor(Color.parseColor("#6dec68"));
        videoActorsTextView.setTextColor(featuredTitleColour);
        if (this.videoDescription != null && this.videoDescription.length() > 0) {
            videoActorsTextView.setVisibility(View.VISIBLE);
            videoTitleTextView.setPadding(0, 0, 0, 5);
        } else {
            videoActorsTextView.setVisibility(View.GONE);
            videoTitleTextView.setPadding(0, 0, 0, 5);
        }
        videoActorsTextView.setVisibility(featuredDescVisibility);

        if(showWatchVideoButton) {
            Button showWatchVideoButtonButton = (Button) v.findViewById(R.id.showWatchVideoButtonButton);
            showWatchVideoButtonButton.setVisibility(View.VISIBLE);
            videoActorsTextView.setVisibility(View.GONE);

            showWatchVideoButtonButton.setTag(this.videoDescription);
            //showWatchVideoButtonButton.setOnClickListener(onVideoImageClickedOnCategory);
            showWatchVideoButtonButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    iCategorySliderCenterTextViewExtraOne.onVideoImageClickedOnCategory(descToPass, myImageToPass, indexOfImage);
                    //target.performClick();
                }
            });

            videoTitleTextView.setTextColor(featuredTitleColour);

            //If there is a live video then hide the video title and change the button text to "Watch Live"
            if(isLiveVideo) {
                showWatchVideoButtonButton.setText("Watch Live");
                videoTitleTextView.setVisibility(View.GONE);
            }
        } else {
            Button showWatchVideoButtonButton = (Button) v.findViewById(R.id.showWatchVideoButtonButton);
            showWatchVideoButtonButton.setVisibility(View.GONE);
        }

        if(featuredTitleVisibility == View.GONE && featuredDescVisibility == View.GONE)
            v.findViewById(R.id.textLinearLayout).setVisibility(View.GONE);

        TextView featuredSeeAllTextView = (TextView)v.findViewById(R.id.featuredSeeAllTextView);
        featuredSeeAllTextView.setTypeface(FontsConstants.tfBold);
        featuredSeeAllTextView.setShadowLayer(2f, -1, 1, Color.BLACK);
        featuredSeeAllTextView.setTag(this.featuredTag);
        featuredSeeAllTextView.setOnClickListener(featuredSeeAllTextViewClickListener);
        featuredSeeAllTextView.setVisibility(View.GONE);

        TextView description = (TextView)v.findViewById(R.id.description);
        description.setText(this.videoDescription);
        //description.setText("This is random description for testing purpose! This is random description for testing purpose! This is random description for testing purpose! This is random description for testing purpose! This is random description for testing purpose! ");
        description.setTypeface(FontsConstants.tfBold);
        description.setVisibility(View.GONE);
        description.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);

        Uri uri = Uri.parse(this.myImage);
        target.setImageURI(uri);
        //System.out.println("Uri.parse(this.myImage)==>"+this.myImage);
        //Picasso.with(getContext()).load(uri).into(target);

        iCategorySliderCenterTextViewExtraOne.selectedSliderIndex(indexOfImage);

        bindEventAndShow(v, target);
        return v;
    }

    View.OnClickListener featuredSeeAllTextViewClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            iCategorySliderCenterTextViewExtraOne.seeAllChannelsClickHandlerHelper(v);
        }
    };

    /*View.OnClickListener onVideoImageClickedOnCategory = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            iCategorySliderCenterTextViewExtraOne.onVideoImageClickedOnCategory(v);
        }
    };*/

    public void myImage(String myImage) {
        this.myImage = myImage;
    }

    public String getMyImage() {
        return this.myImage;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getVideoActors() {
        return videoActors;
    }

    public void setVideoActors(String videoActors) {
        this.videoActors = videoActors;
    }

    public String getFeaturedTag() {
        return featuredTag;
    }

    public void setFeaturedTag(String featuredTag) {
        this.featuredTag = featuredTag;
    }

    public String getVideoDescription() {
        return videoDescription;
    }

    public void setVideoDescription(String videoDescription) {
        this.videoDescription = videoDescription;
    }

    public boolean isCustomFontEnabledForVideoTitle() {
        return isCustomFontEnabledForVideoTitle;
    }

    public void setCustomFontEnabledForVideoTitle(boolean customFontEnabledForVideoTitle) {
        isCustomFontEnabledForVideoTitle = customFontEnabledForVideoTitle;
    }

    public boolean isCustomFontEnabledForVideoDescription() {
        return isCustomFontEnabledForVideoDescription;
    }

    public void setCustomFontEnabledForVideoDescription(boolean customFontEnabledForVideoDescription) {
        isCustomFontEnabledForVideoDescription = customFontEnabledForVideoDescription;
    }

    public Typeface getCustomFontForVideoTitle() {
        return customFontForVideoTitle;
    }

    public void setCustomFontForVideoTitle(Typeface customFontForVideoTitle) {
        this.customFontForVideoTitle = customFontForVideoTitle;
    }

    public Typeface getCustomFontForVideoDescription() {
        return customFontForVideoDescription;
    }

    public void setCustomFontForVideoDescription(Typeface customFontForVideoDescription) {
        this.customFontForVideoDescription = customFontForVideoDescription;
    }

    public int getIndexOfImage() {
        return indexOfImage;
    }

    public void setIndexOfImage(int indexOfImage) {
        this.indexOfImage = indexOfImage;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        iCategorySliderCenterTextViewExtraOne.selectedSliderIndex(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public int getFeaturedTitleVisibility() {
        return featuredTitleVisibility;
    }

    public void setFeaturedTitleVisibility(int featuredTitleVisibility) {
        this.featuredTitleVisibility = featuredTitleVisibility;
    }

    public int getFeaturedDescVisibility() {
        return featuredDescVisibility;
    }

    public void setFeaturedDescVisibility(int featuredDescVisibility) {
        this.featuredDescVisibility = featuredDescVisibility;
    }

    public interface ICategorySliderCenterTextViewExtraOne {
        void selectedSliderIndex(int position);
        void seeAllChannelsClickHandlerHelper(View v);
        void onVideoImageClickedOnCategory(String desc, String myImage, int index);
    }
}
