package com.dotstudioz.dotstudioPRO.corelibrary.components;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.dotstudioz.dotstudioPRO.services.constants.ApplicationConstants;
import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;

/**
 * Created by mohsin on 21-03-2017.
 */

public class FeaturedSliderIndicatorComponent implements CategorySliderCenterTextViewExtraOne.ICategorySliderCenterTextViewExtraOne  {

    private Context context;
    private int backgroundColor;
    private int activeColor;
    private int numberOfIndicators;
    public boolean showIndicatorBackgroundColor = false;
    public boolean isIndicatorBackgroundResource = false;
    public int indicatorBackgroundResource;

    public HorizontalScrollView hsv;

    public FeaturedSliderIndicatorComponent(Context context) {
        this.context = context;
    }

    public RelativeLayout getFeaturedIndicatorComponent() throws NullPointerException {
    //public HorizontalScrollView getFeaturedIndicatorComponent() throws NullPointerException {
        RelativeLayout parentRL = new RelativeLayout(context);
        //RelativeLayout.LayoutParams parentRLP = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams parentRLP = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        //parentRLP.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        parentRLP.gravity = Gravity.CENTER;
        parentRL.setLayoutParams(parentRLP);
        if(showIndicatorBackgroundColor) {
            parentRL.setBackgroundColor(Color.parseColor("#ff0000"));
            //ABS
            //parentRL.setBackgroundColor(Color.parseColor("#ffffff"));
            //CelebrityPage & Nosey
            //parentRL.setBackgroundResource(indicatorBackgroundResource);
            //REVRY
            //parentRL.setBackgroundColor(Color.parseColor("#000000"));

            if(isIndicatorBackgroundResource)
                parentRL.setBackgroundResource(indicatorBackgroundResource);
            else
                parentRL.setBackgroundColor(indicatorBackgroundResource);
        }

        hsv = new HorizontalScrollView(context);
        RelativeLayout.LayoutParams hsvLP = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        //LinearLayout.LayoutParams hsvLP = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        hsvLP.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        hsv.setLayoutParams(hsvLP);
        hsv.setPadding(0, 0, 0, 10);

        LinearLayout llHSV = new LinearLayout(context);
        LinearLayout.LayoutParams llHSVLP = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        llHSVLP.gravity = Gravity.CENTER;
        llHSV.setLayoutParams(llHSVLP);



        for (int i = 0; i < numberOfIndicators; i++) {
            ImageView featuredIndicatorIV = new ImageView(context);
            featuredIndicatorIV.setLayoutParams(new LinearLayout.LayoutParams(ApplicationConstants.TEXT_INPUT_HEIGHT/4, ApplicationConstants.TEXT_INPUT_HEIGHT/4));
            ((LinearLayout.LayoutParams)featuredIndicatorIV.getLayoutParams()).setMargins(10,10,10,0);
            featuredIndicatorIV.setImageDrawable(new IconDrawable(context, FontAwesomeIcons.fa_circle).color(backgroundColor));
            llHSV.addView(featuredIndicatorIV);
        }

        hsv.addView(llHSV);
        parentRL.addView(hsv);

        //return hsv;
        return parentRL;
    }

    @Override
    public void selectedSliderIndex(int position) {
        //Log.d("FeaturedSliderIndicatorComponent", numberOfIndicators+"<==numberOfIndicators<==FeaturedSliderIndicatorComponent1==>position==>"+position);
        if(position == 0) {
            position = numberOfIndicators;
        } else if(position == 1) {
            position = 1;
        }


        //Log.d("FeaturedSliderIndicatorComponent", numberOfIndicators+"<==numberOfIndicators<==FeaturedSliderIndicatorComponent2==>position==>"+position);
        if(hsv != null) {
            if(hsv.getChildCount() > 0) {
                for(int i = 0; i < ((LinearLayout)hsv.getChildAt(0)).getChildCount(); i++) {
                    if(i == (position-1))
                        ((ImageView)((LinearLayout)hsv.getChildAt(0)).getChildAt(i)).setImageDrawable(new IconDrawable(context, FontAwesomeIcons.fa_circle).color(activeColor));
                    else
                        ((ImageView)((LinearLayout)hsv.getChildAt(0)).getChildAt(i)).setImageDrawable(new IconDrawable(context, FontAwesomeIcons.fa_circle).color(backgroundColor));
                }
            }
        }
    }
    @Override
    public void seeAllChannelsClickHandlerHelper(View v) {
        //probably this should already have been handled in the main activity
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public int getActiveColor() {
        return activeColor;
    }

    public void setActiveColor(int activeColor) {
        this.activeColor = activeColor;
    }

    public int getNumberOfIndicators() {
        return numberOfIndicators;
    }

    public void setNumberOfIndicators(int numberOfIndicators) {
        this.numberOfIndicators = numberOfIndicators;
    }
}
