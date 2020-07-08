package com.dotstudioz.dotstudioPRO.corelibrary.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.net.Uri;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dotstudioz.dotstudioPRO.corelibrary.R;
import com.dotstudioz.dotstudioPRO.corelibrary.constants.FontsConstants;
import com.dotstudioz.dotstudioPRO.corelibrary.util.CommonUtils;
import com.dotstudioz.dotstudioPRO.models.dto.SpotLightChannelDTOForCollection;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class Channels_TwoColumn_Adapter_V1 extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<SpotLightChannelDTOForCollection> spotLightChannelDTOForCollection;
    public GenericDraweeHierarchy hierarchy;
    public int imageWidth;
    public int imageHeight;
    public boolean tabletFlag;
    public int informationIconWidth;
    public int informationIconHeight;
    public boolean showchannelsName;
    public int progressBarColour;
    public boolean progressBarColourSetFlag = false;

    private boolean isCustomFontEnabledForVideoTitle = false;
    private Typeface customFontForVideoTitle;

    public IChannelAdapter iChannelAdapter;

    public Channels_TwoColumn_Adapter_V1(Activity activity, List<SpotLightChannelDTOForCollection> spotLightChannelDTOForCollection) {
        this.activity = activity;
        iChannelAdapter = (IChannelAdapter) activity;
        this.spotLightChannelDTOForCollection = spotLightChannelDTOForCollection;
    }

    @Override
    public int getCount() {
        return spotLightChannelDTOForCollection.size();
    }

    @Override
    public Object getItem(int position) {
        return spotLightChannelDTOForCollection.get(position);
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
            convertView = inflater.inflate(R.layout.channels_two_column, parent, false);
        }

        try {

            /*LinearLayout twoColumnCategoriesParentLinearLayout = (LinearLayout) convertView.findViewById(R.id.twoColumnCategoriesParentLinearLayout);
            twoColumnCategoriesParentLinearLayout.getLayoutParams().width = (imageWidth * 2);*/

            RelativeLayout rosterImageRL = (RelativeLayout) convertView.findViewById(R.id.rosterImageRL);
            if (tabletFlag)
                rosterImageRL.setLayoutParams(new LinearLayout.LayoutParams(imageWidth, imageHeight));
            else
                rosterImageRL.setLayoutParams(new LinearLayout.LayoutParams(imageWidth, imageHeight));

            convertView.findViewById(R.id.rosterItemProgressBar).setPadding(0, 0, 0, 0);
            if(progressBarColourSetFlag)
                ((ProgressBar)convertView.findViewById(R.id.rosterItemProgressBar)).getIndeterminateDrawable().setColorFilter(progressBarColour, PorterDuff.Mode.MULTIPLY);

            RelativeLayout.LayoutParams params11 = null;
            if (tabletFlag)
                params11 = new RelativeLayout.LayoutParams(imageWidth, imageHeight);
            else
                params11 = new RelativeLayout.LayoutParams(imageWidth, imageHeight);
            params11.setMargins(10, 10, 10, 10);
            convertView.findViewById(R.id.placeHolder1View).setLayoutParams(params11);

            RelativeLayout.LayoutParams params1 = null;
            if (tabletFlag)
                params1 = new RelativeLayout.LayoutParams(imageWidth, imageHeight);
            else
                params1 = new RelativeLayout.LayoutParams(imageWidth, imageHeight);
            convertView.findViewById(R.id.iv1).setPadding(10, 10, 10, 10);
            convertView.findViewById(R.id.iv1).setLayoutParams(params1);
            convertView.findViewById(R.id.iv1).setClickable(true);
            String channelSlug = "";
            try {
                channelSlug = spotLightChannelDTOForCollection.get(position).getSpotLightChannelDTO1().getSlug();
            } catch(Exception e) {

            }
            convertView.findViewById(R.id.iv1).setTag(convertView.findViewById(R.id.iv1).getId(), channelSlug);

            String imageString = "";
           try {
                imageString = spotLightChannelDTOForCollection.get(position).getSpotLightChannelDTO1().getPoster();
            } catch(Exception e) {
                e.printStackTrace();
            }

           imageString = CommonUtils.replaceDotstudioproWithMyspotlightForImage(imageString);

            Uri uri = Uri.parse(imageString);
            ((SimpleDraweeView) convertView.findViewById(R.id.iv1)).setImageURI(uri);
            convertView.findViewById(R.id.iv1).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickHandlerForChannelsImage(v.getTag(v.getId()).toString());
                }
            });

            TextView channelsTitleTV = ((TextView) convertView.findViewById(R.id.channelsTitleTV));
            channelsTitleTV.setText(spotLightChannelDTOForCollection.get(position).getSpotLightChannelDTO1().getTitle());
            channelsTitleTV.setTypeface(FontsConstants.tfRegular);
            channelsTitleTV.setSingleLine(true);
            channelsTitleTV.setPadding(30, 30, 30, 30);
            channelsTitleTV.setEllipsize(TextUtils.TruncateAt.END);
            channelsTitleTV.setTextSize(25f);
            if(isCustomFontEnabledForVideoTitle)
                channelsTitleTV.setTypeface(customFontForVideoTitle);
            channelsTitleTV.setGravity(Gravity.LEFT);
            channelsTitleTV.setTextColor(Color.parseColor("#eeeeee"));
            channelsTitleTV.setIncludeFontPadding(false);
            channelsTitleTV.setShadowLayer(4f, -2, 2, Color.BLACK);
            if(!showchannelsName)
                channelsTitleTV.setVisibility(View.GONE);


            try {
                if (spotLightChannelDTOForCollection.get(position).getSpotLightChannelDTO2() != null) {
                    RelativeLayout rosterImageRL2 = (RelativeLayout) convertView.findViewById(R.id.rosterImageRL2);
                    if (tabletFlag)
                        rosterImageRL2.setLayoutParams(new LinearLayout.LayoutParams(imageWidth, imageHeight));
                    else
                        rosterImageRL2.setLayoutParams(new LinearLayout.LayoutParams(imageWidth, imageHeight));

                    convertView.findViewById(R.id.rosterItemProgressBar2).setPadding(0, 0, 0, 0);
                    if(progressBarColourSetFlag)
                        ((ProgressBar)convertView.findViewById(R.id.rosterItemProgressBar2)).getIndeterminateDrawable().setColorFilter(progressBarColour, PorterDuff.Mode.MULTIPLY);

                    RelativeLayout.LayoutParams params21 = null;
                    if (tabletFlag)
                        params21 = new RelativeLayout.LayoutParams(imageWidth, imageHeight);
                    else
                        params21 = new RelativeLayout.LayoutParams(imageWidth, imageHeight);
                    params21.setMargins(10, 10, 10, 10);
                    convertView.findViewById(R.id.placeHolder2View).setLayoutParams(params21);

                    RelativeLayout.LayoutParams params2 = null;
                    if (tabletFlag)
                        params2 = new RelativeLayout.LayoutParams(imageWidth, imageHeight);
                    else
                        params2 = new RelativeLayout.LayoutParams(imageWidth, imageHeight);

                    convertView.findViewById(R.id.iv2).setPadding(10, 10, 10, 10);
                    convertView.findViewById(R.id.iv2).setLayoutParams(params2);
                    convertView.findViewById(R.id.iv2).setClickable(true);
                    //iv1.setTag(((CategoriesDTO) categoriesDTOArrayList.get(i)).getChannelImage());
                    String channelSlug2 = "";
                    try {
                        channelSlug2 = spotLightChannelDTOForCollection.get(position).getSpotLightChannelDTO2().getSlug();
                    } catch (Exception e) {

                    }
                    convertView.findViewById(R.id.iv2).setTag(convertView.findViewById(R.id.iv2).getId(), spotLightChannelDTOForCollection.get(position).getSpotLightChannelDTO2().getSlug());
                    String imageString2 = "";
                    try {
                        imageString2 = spotLightChannelDTOForCollection.get(position).getSpotLightChannelDTO2().getPoster();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    imageString2 = CommonUtils.replaceDotstudioproWithMyspotlightForImage(imageString2);
                    Uri uri2 = Uri.parse(imageString2);
                    ((SimpleDraweeView) convertView.findViewById(R.id.iv2)).setImageURI(uri2);
                    convertView.findViewById(R.id.iv2).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            clickHandlerForChannelsImage(v.getTag(v.getId()).toString());//clickHandlerForChannelsImage(v.getTag().toString());
                        }
                    });

                    TextView channelsTitleTV2 = ((TextView) convertView.findViewById(R.id.channelsTitleTV2));
                    channelsTitleTV2.setText(spotLightChannelDTOForCollection.get(position).getSpotLightChannelDTO2().getTitle());
                    channelsTitleTV2.setTypeface(FontsConstants.tfRegular);
                    channelsTitleTV2.setSingleLine(true);
                    channelsTitleTV2.setPadding(30, 30, 30, 30);
                    channelsTitleTV2.setEllipsize(TextUtils.TruncateAt.END);
                    channelsTitleTV2.setTextSize(25f);
                    if (isCustomFontEnabledForVideoTitle)
                        channelsTitleTV2.setTypeface(customFontForVideoTitle);
                    channelsTitleTV2.setGravity(Gravity.LEFT);
                    channelsTitleTV2.setTextColor(Color.parseColor("#eeeeee"));
                    channelsTitleTV2.setIncludeFontPadding(false);
                    channelsTitleTV2.setShadowLayer(4f, -2, 2, Color.BLACK);
                    if(!showchannelsName)
                        channelsTitleTV2.setVisibility(View.GONE);
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }


        return convertView;
    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    private void clickHandlerForChannelsImage(String tagSent) {
        iChannelAdapter.channelClickHandler(tagSent);
    }

    public interface IChannelAdapter {
        void channelClickHandler(String tagSent);
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
