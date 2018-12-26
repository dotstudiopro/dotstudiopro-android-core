package com.dotstudioz.dotstudioPRO.corelibrary.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dotstudioz.dotstudioPRO.corelibrary.R;
import com.dotstudioz.dotstudioPRO.services.constants.ApplicationConstants;
import com.dotstudioz.dotstudioPRO.services.constants.FontsConstants;
import com.dotstudioz.dotstudioPRO.models.dto.SpotLightCategoriesDTO;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by Admin on 03-12-2014.
 */
public class GenreAdapter_V1 extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<SpotLightCategoriesDTO> spotLightCategoriesDTOList;
    public GenericDraweeHierarchy hierarchy;
    public int imageWidth;
    public int imageHeight;
    public boolean tabletFlag;
    public int informationIconWidth;
    public int informationIconHeight;
    public int activeColour = Color.parseColor("#00000000");

    private boolean isCustomFontEnabledForVideoTitle = false;
    private Typeface customFontForVideoTitle;

    public IGenreAdapter iGenreAdapter;

    public GenreAdapter_V1(Activity activity, List<SpotLightCategoriesDTO> spotLightCategoriesDTOList) {
        this.activity = activity;
        iGenreAdapter = (IGenreAdapter) activity;
        this.spotLightCategoriesDTOList = spotLightCategoriesDTOList;
    }

    @Override
    public int getCount() {
        return spotLightCategoriesDTOList.size();
    }

    @Override
    public Object getItem(int position) {
        return spotLightCategoriesDTOList.get(position);
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
            convertView = inflater.inflate(R.layout.genre_item_v1, parent, false);
            //convertView.setBackgroundColor(activeColour);
        }

        try {
            RelativeLayout rosterImageRL = (RelativeLayout) convertView.findViewById(R.id.rosterImageRL);
            if (tabletFlag)
                rosterImageRL.setLayoutParams(new LinearLayout.LayoutParams(imageWidth, imageHeight));
            else
                rosterImageRL.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, imageHeight));
            /*if (tabletFlag)
                rosterImageRL.setLayoutParams(new LinearLayout.LayoutParams(imageWidth, LinearLayout.LayoutParams.WRAP_CONTENT));
            else
                rosterImageRL.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));*/

            //((RelativeLayout.LayoutParams) rosterImageRL.getLayoutParams()).addRule(RelativeLayout.ALIGN_PARENT_RIGHT | RelativeLayout.ALIGN_PARENT_BOTTOM);

            convertView.findViewById(R.id.rosterItemProgressBar).setPadding(0, 0, 0, 0);

            RelativeLayout.LayoutParams params1 = null;
            if (tabletFlag)
                params1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, imageHeight);
            else
                params1 = new RelativeLayout.LayoutParams(imageWidth, imageHeight);
            //convertView.findViewById(R.id.iv1).setPadding(3, 3, 3, 3);
            convertView.findViewById(R.id.iv1).setLayoutParams(params1);
            convertView.findViewById(R.id.iv1).setClickable(true);
            //iv1.setTag(((CategoriesDTO) categoriesDTOArrayList.get(i)).getChannelImage());
            String channelImagePlusCategorySlug = "";
            try {
                channelImagePlusCategorySlug = spotLightCategoriesDTOList.get(position).getSpotLightChannelDTOList().get(0).getId() + "|" + "genre";
            } catch(Exception e) {

            }
            convertView.findViewById(R.id.iv1).setTag(convertView.findViewById(R.id.iv1).getId(), spotLightCategoriesDTOList.get(position).getCategorySlug());
            //System.out.println("ImageData:"+categoriesDTO.getChannelSpotlightImage());

            String imageString = "";
            try {
                imageString = spotLightCategoriesDTOList.get(position).getSpotLightChannelDTOList().get(0).getImage();
            } catch(Exception e) {
                e.printStackTrace();
            }

            //do not assign the video thumbnail here, it should strictly be channel poster
            try {
                if(imageString != null && imageString.length() == 0) {
                    imageString = "https://images.dotstudiopro.com/" + spotLightCategoriesDTOList.get(position).getVideoInfoDTOList().get(0).getThumb();
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
            imageString = CommonUtils.replaceDotstudioproWithMyspotlightForImage(imageString);
            //In case the parent channel had not being assigned any image, then we are picking up the image from the first season
            /*try {
                if(imageString != null && imageString.length() == 0) {
                    imageString = spotLightCategoriesDTOList.get(position).getSpotLightChannelDTOList().get(0).getSeasonsList().get(0).getImage();
                }
            } catch(Exception e) {
                e.printStackTrace();
            }*/
            String imageURLAddress = imageString + "/" + imageWidth + "/" + imageHeight;
            System.out.println("GenreAdapter ImageData==>" + imageURLAddress);
            Uri uri = Uri.parse(imageURLAddress);
            ((SimpleDraweeView) convertView.findViewById(R.id.iv1)).setImageURI(uri);
            convertView.findViewById(R.id.iv1).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickHandlerForChannelsImage(v.getTag(v.getId()).toString());//clickHandlerForChannelsImage(v.getTag().toString());
                }
            });

            TextView categoryTitleTV = ((TextView) convertView.findViewById(R.id.categoryTitleTV));
            categoryTitleTV.setText(spotLightCategoriesDTOList.get(position).getCategoryName());
            categoryTitleTV.setTypeface(FontsConstants.tfRegular);
            categoryTitleTV.setSingleLine(true);
            categoryTitleTV.setPadding(30, 0, 30, 0);
            categoryTitleTV.setEllipsize(TextUtils.TruncateAt.END);
            categoryTitleTV.setTextSize(22f);

            if(isCustomFontEnabledForVideoTitle)
                categoryTitleTV.setTypeface(customFontForVideoTitle);
            categoryTitleTV.setGravity(Gravity.LEFT);
            categoryTitleTV.setTextColor(Color.parseColor("#eeeeee"));
            categoryTitleTV.setIncludeFontPadding(false);
            categoryTitleTV.setShadowLayer(4f, -2, 2, Color.BLACK);
            categoryTitleTV.setBackgroundColor(activeColour);

            //convertView.setBackgroundColor(Color.parseColor("#2C2F34"));
            int textHeight = getHeight(activity, "TestValue", (int)21f, imageWidth);
            if(position == 0) {
                convertView.findViewById(R.id.extraSpaceViewTop).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ApplicationConstants.TOP_PADDING_FOR_MAIN_ACTION_BAR));
                convertView.findViewById(R.id.extraSpaceViewTop).setVisibility(View.VISIBLE);
                convertView.setBackgroundColor(activeColour);

                ((AbsListView.LayoutParams)convertView.getLayoutParams()).height = (imageHeight + ApplicationConstants.TOP_PADDING_FOR_MAIN_ACTION_BAR + textHeight);
                ((AbsListView.LayoutParams)convertView.getLayoutParams()).width = imageWidth;
                convertView.findViewById(R.id.extraSpaceViewGenre).setVisibility(View.GONE);
            } else if(position == spotLightCategoriesDTOList.size()-1 && position > 2) {
                convertView.findViewById(R.id.extraSpaceViewTop).setVisibility(View.GONE);
                convertView.findViewById(R.id.extraSpaceViewGenre).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ApplicationConstants.BOTTOM_PADDING_FOR_TAB_BAR));
                convertView.findViewById(R.id.extraSpaceViewGenre).setVisibility(View.VISIBLE);

                //((RelativeLayout.LayoutParams)categoryTitleTV.getLayoutParams()).setMargins(0, 0, 0, ApplicationConstants.BOTTOM_PADDING_FOR_TAB_BAR);

                ((AbsListView.LayoutParams)convertView.getLayoutParams()).height = (imageHeight + ApplicationConstants.BOTTOM_PADDING_FOR_TAB_BAR + textHeight);
                //((AbsListView.LayoutParams)convertView.getLayoutParams()).height = imageHeight;
                ((AbsListView.LayoutParams)convertView.getLayoutParams()).width = imageWidth;
            } else {
                ((AbsListView.LayoutParams) convertView.getLayoutParams()).height = imageHeight + textHeight;
                ((AbsListView.LayoutParams) convertView.getLayoutParams()).width = imageWidth;
                convertView.findViewById(R.id.extraSpaceViewGenre).setVisibility(View.GONE);
            }



            /*if(position == 0)
                convertView.setBackgroundColor(Color.parseColor("#ff0000"));
            else if(position == 1)
                convertView.setBackgroundColor(Color.parseColor("#00ff00"));
            else if(position == 2)
                convertView.setBackgroundColor(Color.parseColor("#0000ff"));*/


        } catch(Exception e) {
            //e.printStackTrace();
        }


        return convertView;
    }

    public int getHeight(Context context, String text, int textSize, int deviceWidth) {
        TextView textView = new TextView(context);
        textView.setTypeface(FontsConstants.tfRegular);
        textView.setPadding(15, 0, 20, 5);
        textView.setText(text);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(deviceWidth, View.MeasureSpec.AT_MOST);
        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        textView.measure(widthMeasureSpec, heightMeasureSpec);
        return textView.getMeasuredHeight();
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
        iGenreAdapter.genreImageClickHandler(tagSent);
    }

    public interface IGenreAdapter {
        void genreImageClickHandler(String tagSent);
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
