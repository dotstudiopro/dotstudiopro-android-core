package com.dotstudioz.dotstudioPRO.corelibrary.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.net.Uri;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dotstudioz.dotstudioPRO.corelibrary.R;
import com.dotstudioz.dotstudioPRO.services.constants.ApplicationConstants;
import com.dotstudioz.dotstudioPRO.services.constants.FontsConstants;
import com.dotstudioz.dotstudioPRO.models.dto.SpotLightCategoriesDTO;
import com.dotstudioz.dotstudioPRO.models.dto.SpotLightCategoriesDTOForCollection;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by Admin on 03-12-2014.
 */
public class CollectionAdapter_V1 extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<SpotLightCategoriesDTOForCollection> spotLightCategoriesDTOListForCollection;
    public GenericDraweeHierarchy hierarchy;
    public DisplayMetrics displayMetrics;
    public int imageWidth;
    public int imageHeight;
    public boolean tabletFlag;
    public int informationIconWidth;
    public int informationIconHeight;
    public int activeColour = Color.parseColor("#00000000");

    private boolean isCustomFontEnabledForVideoTitle = false;
    private Typeface customFontForVideoTitle;

    public ICollectionAdapter iCollectionAdapter;

    public CollectionAdapter_V1(Activity activity, List<SpotLightCategoriesDTOForCollection> spotLightCategoriesDTOListForCollection) {
        this.activity = activity;
        iCollectionAdapter = (ICollectionAdapter) activity;
        this.spotLightCategoriesDTOListForCollection = spotLightCategoriesDTOListForCollection;
    }

    @Override
    public int getCount() {
        return spotLightCategoriesDTOListForCollection.size();
    }

    @Override
    public Object getItem(int position) {
        return spotLightCategoriesDTOListForCollection.get(position);
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
            convertView = inflater.inflate(R.layout.collection_item_v1, parent, false);
        }

        try {
            ((RelativeLayout)convertView.findViewById(R.id.firstImageRelativeLayout)).getLayoutParams().width = (displayMetrics.widthPixels/2);
            ((RelativeLayout)convertView.findViewById(R.id.firstImageRelativeLayout)).getLayoutParams().height = imageHeight;
            RelativeLayout rosterImageRL1 = (RelativeLayout) convertView.findViewById(R.id.rosterImageRL1);
            if (tabletFlag)
                rosterImageRL1.setLayoutParams(new RelativeLayout.LayoutParams(imageWidth, imageHeight));
            else
                rosterImageRL1.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, imageHeight));

            convertView.findViewById(R.id.rosterItemProgressBar1).setPadding(0, 0, 0, 0);
            ((ProgressBar)convertView.findViewById(R.id.rosterItemProgressBar1)).getIndeterminateDrawable().setColorFilter(activeColour, PorterDuff.Mode.MULTIPLY);

            RelativeLayout.LayoutParams params1 = null;
            if (tabletFlag)
                params1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, imageHeight);
            else
                params1 = new RelativeLayout.LayoutParams(imageWidth, imageHeight);
            convertView.findViewById(R.id.iv1).setLayoutParams(params1);
            convertView.findViewById(R.id.iv1).setClickable(true);
            String channelImagePlusCategorySlug = "";
            try {
                channelImagePlusCategorySlug = spotLightCategoriesDTOListForCollection.get(position).getSpotLightCategoriesDTO1().getSpotLightChannelDTOList().get(0).getId() + "|" + "genre";
            } catch(Exception e) {

            }
            convertView.findViewById(R.id.iv1).setTag(convertView.findViewById(R.id.iv1).getId(), spotLightCategoriesDTOListForCollection.get(position).getSpotLightCategoriesDTO1().getCategorySlug());

            String imageString = "";
            try {
                //imageString = spotLightCategoriesDTOList.get((position*2)).getSpotLightChannelDTOList().get(0).getImage();
                imageString = spotLightCategoriesDTOListForCollection.get(position).getSpotLightCategoriesDTO1().getPoster();
            } catch(Exception e) {
                e.printStackTrace();
            }

            //do not assign the video thumbnail here, it should strictly be channel poster
            try {
                if(imageString != null && imageString.length() == 0) {
                    imageString = "https://images.dotstudiopro.com/" + spotLightCategoriesDTOListForCollection.get(position).getSpotLightCategoriesDTO1().getVideoInfoDTOList().get(0).getThumb();
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
            imageString = CommonUtils.replaceDotstudioproWithMyspotlightForImage(imageString);
            //In case the parent channel had not being assigned any image, then we are picking up the image from the first season
            /*try {
                if(imageString != null && imageString.length() == 0) {
                    imageString = spotLightCategoriesDTOList.get((position*2)).getSpotLightChannelDTOList().get(0).getSeasonsList().get(0).getImage();
                }
            } catch(Exception e) {
                e.printStackTrace();
            }*/
            String imageURLAddress = imageString + "/" + imageWidth + "/" + imageHeight;
            //System.out.println("CollectionAdapter ImageData==>" + imageURLAddress);
            //System.out.println("Position==>"+position);
            Uri uri = Uri.parse(imageURLAddress);
            ((SimpleDraweeView) convertView.findViewById(R.id.iv1)).setImageURI(uri);
            //convertView.findViewById(R.id.rosterItemProgressBar1).setVisibility(View.GONE);
            convertView.findViewById(R.id.iv1).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickHandlerForChannelsImage(v.getTag(v.getId()).toString());//clickHandlerForChannelsImage(v.getTag().toString());
                }
            });

            try {
                //if the poster image is not found then we are showing the Channel Name in text view instead
                if(spotLightCategoriesDTOListForCollection.get(position) != null &&
                        spotLightCategoriesDTOListForCollection.get(position).getSpotLightCategoriesDTO1().getPoster() != null &&
                        spotLightCategoriesDTOListForCollection.get(position).getSpotLightCategoriesDTO1().getPoster().length() < 1) {
                    if (spotLightCategoriesDTOListForCollection.get(position).getSpotLightCategoriesDTO1().getCategoryName() != null &&
                            spotLightCategoriesDTOListForCollection.get(position).getSpotLightCategoriesDTO1().getCategoryName().length() > 0) {
                        ((TextView) convertView.findViewById(R.id.tv1)).setText(spotLightCategoriesDTOListForCollection.get(position).getSpotLightCategoriesDTO1().getCategoryName());

                    }
                } else {
                    ((TextView) convertView.findViewById(R.id.tv1)).setVisibility(View.GONE);
                }
                //convertView.findViewById(R.id.rosterItemProgressBar1).setVisibility(View.GONE);
            } catch(Exception e) {
                e.printStackTrace();
            }

            //convertView.setBackgroundColor(Color.parseColor("#2C2F34"));
            int textHeight = getHeight(activity, "TestValue", (int)21f, imageWidth);
            if(position == 0) {
                convertView.findViewById(R.id.extraSpaceViewTop).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ApplicationConstants.TOP_PADDING_FOR_MAIN_ACTION_BAR));
                convertView.findViewById(R.id.extraSpaceViewTop).setVisibility(View.VISIBLE);
                //convertView.setBackgroundColor(activeColour);

                convertView.findViewById(R.id.extraSpaceViewGenre).setVisibility(View.GONE);
            } else if(position == spotLightCategoriesDTOListForCollection.size()-1 && position > 2) {
                convertView.findViewById(R.id.extraSpaceViewTop).setVisibility(View.GONE);
                convertView.findViewById(R.id.extraSpaceViewGenre).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ApplicationConstants.BOTTOM_PADDING_FOR_TAB_BAR));
                convertView.findViewById(R.id.extraSpaceViewGenre).setVisibility(View.VISIBLE);

                //((RelativeLayout.LayoutParams)categoryTitleTV.getLayoutParams()).setMargins(0, 0, 0, ApplicationConstants.BOTTOM_PADDING_FOR_TAB_BAR);

                ((AbsListView.LayoutParams)convertView.getLayoutParams()).width = displayMetrics.widthPixels;
            } else {
                convertView.findViewById(R.id.extraSpaceViewGenre).setVisibility(View.GONE);
            }


        } catch(Exception e) {
            //e.printStackTrace();
        }






        try {
            if(spotLightCategoriesDTOListForCollection.get(position).getSpotLightCategoriesDTO2() != null &&
                    spotLightCategoriesDTOListForCollection.get(position).getSpotLightCategoriesDTO2().getSpotLightChannelDTOList() != null &&
                    spotLightCategoriesDTOListForCollection.get(position).getSpotLightCategoriesDTO2().getSpotLightChannelDTOList().size() > 0 &&
                    spotLightCategoriesDTOListForCollection.get(position).getSpotLightCategoriesDTO2().getSpotLightChannelDTOList().get(0).getId() != null) {
                ((RelativeLayout) convertView.findViewById(R.id.secondImageRelativeLayout)).getLayoutParams().width = (displayMetrics.widthPixels/2);
                ((RelativeLayout) convertView.findViewById(R.id.secondImageRelativeLayout)).getLayoutParams().height = imageHeight;
                RelativeLayout rosterImageRL2 = (RelativeLayout) convertView.findViewById(R.id.rosterImageRL2);
                if (tabletFlag)
                    rosterImageRL2.setLayoutParams(new RelativeLayout.LayoutParams(imageWidth, imageHeight));
                else
                    rosterImageRL2.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, imageHeight));

                convertView.findViewById(R.id.rosterItemProgressBar2).setPadding(0, 0, 0, 0);
                ((ProgressBar)convertView.findViewById(R.id.rosterItemProgressBar2)).getIndeterminateDrawable().setColorFilter(activeColour, PorterDuff.Mode.MULTIPLY);

                RelativeLayout.LayoutParams params2 = null;
                if (tabletFlag)
                    params2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, imageHeight);
                else
                    params2 = new RelativeLayout.LayoutParams(imageWidth, imageHeight);
                convertView.findViewById(R.id.iv2).setLayoutParams(params2);
                convertView.findViewById(R.id.iv2).setClickable(true);
                String channelImagePlusCategorySlug = "";
                try {
                    channelImagePlusCategorySlug = spotLightCategoriesDTOListForCollection.get(position).getSpotLightCategoriesDTO2().getSpotLightChannelDTOList().get(0).getId() + "|" + "genre";
                } catch (Exception e) {

                }
                convertView.findViewById(R.id.iv2).setTag(convertView.findViewById(R.id.iv2).getId(), spotLightCategoriesDTOListForCollection.get(position).getSpotLightCategoriesDTO2().getCategorySlug());

                String imageString = "";
                try {
                    //imageString = spotLightCategoriesDTOList.get((position*2)+1).getSpotLightChannelDTOList().get(0).getImage();
                    imageString = spotLightCategoriesDTOListForCollection.get(position).getSpotLightCategoriesDTO2().getPoster();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                //do not assign the video thumbnail here, it should strictly be channel poster
                try {
                    if (imageString != null && imageString.length() == 0) {
                        imageString = "https://images.dotstudiopro.com/" + spotLightCategoriesDTOListForCollection.get(position).getSpotLightCategoriesDTO2().getVideoInfoDTOList().get(0).getThumb();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                imageString = CommonUtils.replaceDotstudioproWithMyspotlightForImage(imageString);
                //In case the parent channel had not being assigned any image, then we are picking up the image from the first season
                /*try {
                    if(imageString != null && imageString.length() == 0) {
                        imageString = spotLightCategoriesDTOList.get((position*2)+1).getSpotLightChannelDTOList().get(0).getSeasonsList().get(0).getImage();
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                }*/
                String imageURLAddress = imageString + "/" + imageWidth + "/" + imageHeight;
                //System.out.println("CollectionAdapter ImageData==>" + imageURLAddress);
                //System.out.println("Position==>"+position);
                Uri uri = Uri.parse(imageURLAddress);
                ((SimpleDraweeView) convertView.findViewById(R.id.iv2)).setImageURI(uri);
                //convertView.findViewById(R.id.rosterItemProgressBar1).setVisibility(View.GONE);
                convertView.findViewById(R.id.iv2).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickHandlerForChannelsImage(v.getTag(v.getId()).toString());//clickHandlerForChannelsImage(v.getTag().toString());
                    }
                });

                try {
                    //if the poster image is not found then we are showing the Channel Name in text view instead
                    if(spotLightCategoriesDTOListForCollection.get(position) != null &&
                            spotLightCategoriesDTOListForCollection.get(position).getSpotLightCategoriesDTO2().getPoster() != null &&
                            spotLightCategoriesDTOListForCollection.get(position).getSpotLightCategoriesDTO2().getPoster().length() < 1) {
                        if (spotLightCategoriesDTOListForCollection.get(position).getSpotLightCategoriesDTO2().getCategoryName() != null &&
                                spotLightCategoriesDTOListForCollection.get(position).getSpotLightCategoriesDTO2().getCategoryName().length() > 0) {
                            ((TextView) convertView.findViewById(R.id.tv2)).setText(spotLightCategoriesDTOListForCollection.get(position).getSpotLightCategoriesDTO2().getCategoryName());

                        }
                    } else {
                        ((TextView) convertView.findViewById(R.id.tv2)).setVisibility(View.GONE);
                    }
                    //convertView.findViewById(R.id.rosterItemProgressBar2).setVisibility(View.GONE);
                } catch(Exception e) {
                    e.printStackTrace();
                }

                //convertView.setBackgroundColor(Color.parseColor("#2C2F34"));
                int textHeight = getHeight(activity, "TestValue", (int) 21f, imageWidth);
                if (position == 0) {
                    convertView.findViewById(R.id.extraSpaceViewTop).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ApplicationConstants.TOP_PADDING_FOR_MAIN_ACTION_BAR));
                    convertView.findViewById(R.id.extraSpaceViewTop).setVisibility(View.VISIBLE);
                    //convertView.setBackgroundColor(activeColour);

                    convertView.findViewById(R.id.extraSpaceViewGenre).setVisibility(View.GONE);
                } else if (position == spotLightCategoriesDTOListForCollection.size() - 1 && (position) > 2) {
                    convertView.findViewById(R.id.extraSpaceViewTop).setVisibility(View.GONE);
                    convertView.findViewById(R.id.extraSpaceViewGenre).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ApplicationConstants.BOTTOM_PADDING_FOR_TAB_BAR));
                    convertView.findViewById(R.id.extraSpaceViewGenre).setVisibility(View.VISIBLE);

                    //((RelativeLayout.LayoutParams)categoryTitleTV.getLayoutParams()).setMargins(0, 0, 0, ApplicationConstants.BOTTOM_PADDING_FOR_TAB_BAR);

                    ((AbsListView.LayoutParams) convertView.getLayoutParams()).width = displayMetrics.widthPixels;
                } else {
                    convertView.findViewById(R.id.extraSpaceViewGenre).setVisibility(View.GONE);
                }
            } else {
                ((RelativeLayout) convertView.findViewById(R.id.secondImageRelativeLayout)).setVisibility(View.GONE);
                //System.out.println("THERE IS NO IMAGE FOR THIS");
                try {
                    ((TextView) convertView.findViewById(R.id.tv2)).setText(spotLightCategoriesDTOListForCollection.get(position).getSpotLightCategoriesDTO2().getCategoryName());
                    //convertView.findViewById(R.id.rosterItemProgressBar2).setVisibility(View.GONE);
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }


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
        iCollectionAdapter.genreImageClickHandler(tagSent);
    }

    public interface ICollectionAdapter {
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
