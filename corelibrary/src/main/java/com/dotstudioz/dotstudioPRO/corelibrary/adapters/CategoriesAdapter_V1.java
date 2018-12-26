package com.dotstudioz.dotstudioPRO.corelibrary.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.util.TypedValue;
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
import com.dotstudioz.dotstudioPRO.corelibrary.constants.FontsConstants;
import com.dotstudioz.dotstudioPRO.models.dto.SpotLightCategoriesDTOForCategories;
import com.dotstudioz.dotstudioPRO.corelibrary.util.CommonUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by Admin on 03-12-2014.
 */
public class CategoriesAdapter_V1 extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<SpotLightCategoriesDTOForCategories> spotLightCategoriesDTOListForCategories;
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

    public ICategoriesAdapter iCategoriesAdapter;

    public CategoriesAdapter_V1(Activity activity, List<SpotLightCategoriesDTOForCategories> spotLightCategoriesDTOListForCategories) {
        this.activity = activity;
        iCategoriesAdapter = (ICategoriesAdapter) activity;
        this.spotLightCategoriesDTOListForCategories = spotLightCategoriesDTOListForCategories;
    }

    @Override
    public int getCount() {
        return spotLightCategoriesDTOListForCategories.size();
    }

    @Override
    public Object getItem(int position) {
        return spotLightCategoriesDTOListForCategories.get(position);
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
            convertView = inflater.inflate(R.layout.category_item_v1, parent, false);
        }

        try {
            ((RelativeLayout) convertView.findViewById(R.id.firstImageRelativeLayout)).getLayoutParams().width = (displayMetrics.widthPixels / 3);
            ((RelativeLayout) convertView.findViewById(R.id.firstImageRelativeLayout)).getLayoutParams().height = imageHeight;
            if(spotLightCategoriesDTOListForCategories.get(position).getSpotLightCategoriesDTO1() != null &&
                    spotLightCategoriesDTOListForCategories.get(position).getSpotLightCategoriesDTO1().getPoster() != null) {

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
                    channelImagePlusCategorySlug = spotLightCategoriesDTOListForCategories.get(position).getSpotLightCategoriesDTO1().getSpotLightChannelDTOList().get(0).getId() + "|" + "genre";
                } catch (Exception e) {

                }
                convertView.findViewById(R.id.iv1).setTag(convertView.findViewById(R.id.iv1).getId(), spotLightCategoriesDTOListForCategories.get(position).getSpotLightCategoriesDTO1().getCategorySlug());

                String imageString = "";
                try {
                    //imageString = spotLightCategoriesDTOList.get((position*2)).getSpotLightChannelDTOList().get(0).getImage();
                    imageString = spotLightCategoriesDTOListForCategories.get(position).getSpotLightCategoriesDTO1().getPoster();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (imageString == null) {
                    try {
                        //if the poster image is not found then we are showing the Channel Name in text view instead
                        if (spotLightCategoriesDTOListForCategories.get(position) != null &&
                                spotLightCategoriesDTOListForCategories.get(position).getSpotLightCategoriesDTO1().getCategoryName() != null &&
                                spotLightCategoriesDTOListForCategories.get(position).getSpotLightCategoriesDTO1().getCategoryName().length() > 0) {

                            ((TextView) convertView.findViewById(R.id.tv1)).setText(spotLightCategoriesDTOListForCategories.get(position).getSpotLightCategoriesDTO1().getCategoryName());
                            ((TextView) convertView.findViewById(R.id.tv1)).setVisibility(View.VISIBLE);
                            convertView.findViewById(R.id.rosterItemProgressBar1).setVisibility(View.GONE);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    if (imageString != null)
                        imageString = CommonUtils.replaceDotstudioproWithMyspotlightForImage(imageString);

                    String imageURLAddress = imageString + "/" + imageWidth + "/" + imageHeight;
                    System.out.println("CategoriesAdapter ImageData==>" + imageURLAddress);
                    System.out.println("Position==>" + position);
                    Uri uri = Uri.parse(imageURLAddress);
                    ((SimpleDraweeView) convertView.findViewById(R.id.iv1)).setImageURI(uri);
                    convertView.findViewById(R.id.iv1).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            clickHandlerForChannelsImage(v.getTag(v.getId()).toString());//clickHandlerForChannelsImage(v.getTag().toString());
                        }
                    });
                }

                //convertView.setBackgroundColor(Color.parseColor("#2C2F34"));
                int textHeight = getHeight(activity, "TestValue", (int) 21f, imageWidth);
                if (position == 0) {
                    convertView.findViewById(R.id.extraSpaceViewTop).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ApplicationConstants.TOP_PADDING_FOR_MAIN_ACTION_BAR));
                    convertView.findViewById(R.id.extraSpaceViewTop).setVisibility(View.VISIBLE);
                    //convertView.setBackgroundColor(activeColour);

                    convertView.findViewById(R.id.extraSpaceViewGenre).setVisibility(View.GONE);
                } else if (position == spotLightCategoriesDTOListForCategories.size() - 1 && position > 2) {
                    convertView.findViewById(R.id.extraSpaceViewTop).setVisibility(View.GONE);
                    convertView.findViewById(R.id.extraSpaceViewGenre).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ApplicationConstants.BOTTOM_PADDING_FOR_TAB_BAR));
                    convertView.findViewById(R.id.extraSpaceViewGenre).setVisibility(View.VISIBLE);

                    //((RelativeLayout.LayoutParams)categoryTitleTV.getLayoutParams()).setMargins(0, 0, 0, ApplicationConstants.BOTTOM_PADDING_FOR_TAB_BAR);

                    ((AbsListView.LayoutParams) convertView.getLayoutParams()).width = displayMetrics.widthPixels;
                } else {
                    convertView.findViewById(R.id.extraSpaceViewGenre).setVisibility(View.GONE);
                }
            } else {
                //((RelativeLayout) convertView.findViewById(R.id.firstImageRelativeLayout)).setVisibility(View.GONE);
                System.out.println("THERE IS NO IMAGE FOR THIS");
                try {
                    if(spotLightCategoriesDTOListForCategories.get(position).getSpotLightCategoriesDTO1().getCategoryName() != null)
                        ((TextView) convertView.findViewById(R.id.tv1)).setText(spotLightCategoriesDTOListForCategories.get(position).getSpotLightCategoriesDTO1().getCategoryName());
                    else
                        ((TextView) convertView.findViewById(R.id.tv1)).setText("No Data Found");
                    ((TextView) convertView.findViewById(R.id.tv1)).setVisibility(View.VISIBLE);
                    ((SimpleDraweeView) convertView.findViewById(R.id.iv1)).setVisibility(View.GONE);
                    convertView.findViewById(R.id.rosterItemProgressBar1).setVisibility(View.GONE);
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }


        } catch(Exception e) {
            //e.printStackTrace();
        }






        try {
            ((RelativeLayout) convertView.findViewById(R.id.secondImageRelativeLayout)).getLayoutParams().width = (displayMetrics.widthPixels/3);
            ((RelativeLayout) convertView.findViewById(R.id.secondImageRelativeLayout)).getLayoutParams().height = imageHeight;
            if(spotLightCategoriesDTOListForCategories.get(position).getSpotLightCategoriesDTO2() != null &&
                    spotLightCategoriesDTOListForCategories.get(position).getSpotLightCategoriesDTO2().getPoster() != null) {

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
                    channelImagePlusCategorySlug = spotLightCategoriesDTOListForCategories.get(position).getSpotLightCategoriesDTO2().getSpotLightChannelDTOList().get(0).getId() + "|" + "genre";
                } catch (Exception e) {

                }
                convertView.findViewById(R.id.iv2).setTag(convertView.findViewById(R.id.iv2).getId(), spotLightCategoriesDTOListForCategories.get(position).getSpotLightCategoriesDTO2().getCategorySlug());

                String imageString = "";
                try {
                    //imageString = spotLightCategoriesDTOList.get((position*2)+1).getSpotLightChannelDTOList().get(0).getImage();
                    imageString = spotLightCategoriesDTOListForCategories.get(position).getSpotLightCategoriesDTO2().getPoster();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if(imageString == null) {
                    try {
                        //if the poster image is not found then we are showing the Channel Name in text view instead
                        if(spotLightCategoriesDTOListForCategories.get(position) != null &&
                                spotLightCategoriesDTOListForCategories.get(position).getSpotLightCategoriesDTO2().getCategoryName() != null &&
                                spotLightCategoriesDTOListForCategories.get(position).getSpotLightCategoriesDTO2().getCategoryName().length() > 0) {

                            ((TextView) convertView.findViewById(R.id.tv2)).setText(spotLightCategoriesDTOListForCategories.get(position).getSpotLightCategoriesDTO2().getCategoryName());
                            ((TextView) convertView.findViewById(R.id.tv2)).setVisibility(View.VISIBLE);
                            convertView.findViewById(R.id.rosterItemProgressBar2).setVisibility(View.GONE);
                        }
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    if (imageString != null)
                        imageString = CommonUtils.replaceDotstudioproWithMyspotlightForImage(imageString);

                    String imageURLAddress = imageString + "/" + imageWidth + "/" + imageHeight;
                    System.out.println("CategoriesAdapter ImageData==>" + imageURLAddress);
                    System.out.println("Position==>" + position);
                    Uri uri = Uri.parse(imageURLAddress);
                    ((SimpleDraweeView) convertView.findViewById(R.id.iv2)).setImageURI(uri);
                    convertView.findViewById(R.id.iv2).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            clickHandlerForChannelsImage(v.getTag(v.getId()).toString());//clickHandlerForChannelsImage(v.getTag().toString());
                        }
                    });
                }

                //convertView.setBackgroundColor(Color.parseColor("#2C2F34"));
                int textHeight = getHeight(activity, "TestValue", (int) 21f, imageWidth);
                if (position == 0) {
                    convertView.findViewById(R.id.extraSpaceViewTop).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ApplicationConstants.TOP_PADDING_FOR_MAIN_ACTION_BAR));
                    convertView.findViewById(R.id.extraSpaceViewTop).setVisibility(View.VISIBLE);
                    //convertView.setBackgroundColor(activeColour);

                    convertView.findViewById(R.id.extraSpaceViewGenre).setVisibility(View.GONE);
                } else if (position == spotLightCategoriesDTOListForCategories.size() - 1 && (position) > 2) {
                    convertView.findViewById(R.id.extraSpaceViewTop).setVisibility(View.GONE);
                    convertView.findViewById(R.id.extraSpaceViewGenre).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ApplicationConstants.BOTTOM_PADDING_FOR_TAB_BAR));
                    convertView.findViewById(R.id.extraSpaceViewGenre).setVisibility(View.VISIBLE);

                    //((RelativeLayout.LayoutParams)categoryTitleTV.getLayoutParams()).setMargins(0, 0, 0, ApplicationConstants.BOTTOM_PADDING_FOR_TAB_BAR);

                    ((AbsListView.LayoutParams) convertView.getLayoutParams()).width = displayMetrics.widthPixels;
                } else {
                    convertView.findViewById(R.id.extraSpaceViewGenre).setVisibility(View.GONE);
                }
            } else {
                //((RelativeLayout) convertView.findViewById(R.id.secondImageRelativeLayout)).setVisibility(View.GONE);
                System.out.println("THERE IS NO IMAGE FOR THIS");
                try {
                    if(spotLightCategoriesDTOListForCategories.get(position).getSpotLightCategoriesDTO2().getCategoryName() != null)
                        ((TextView) convertView.findViewById(R.id.tv2)).setText(spotLightCategoriesDTOListForCategories.get(position).getSpotLightCategoriesDTO2().getCategoryName());
                    else
                        ((TextView) convertView.findViewById(R.id.tv2)).setText("No Data Found");
                    ((TextView) convertView.findViewById(R.id.tv2)).setVisibility(View.VISIBLE);
                    ((SimpleDraweeView) convertView.findViewById(R.id.iv2)).setVisibility(View.GONE);
                    convertView.findViewById(R.id.rosterItemProgressBar2).setVisibility(View.GONE);
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }


        } catch(Exception e) {
            //e.printStackTrace();
        }


        try {
            ((RelativeLayout) convertView.findViewById(R.id.thirdImageRelativeLayout)).getLayoutParams().width = (displayMetrics.widthPixels / 3);
            ((RelativeLayout) convertView.findViewById(R.id.thirdImageRelativeLayout)).getLayoutParams().height = imageHeight;
            if(spotLightCategoriesDTOListForCategories.get(position).getSpotLightCategoriesDTO3() != null &&
                    spotLightCategoriesDTOListForCategories.get(position).getSpotLightCategoriesDTO3().getPoster() != null) {

                RelativeLayout rosterImageRL3 = (RelativeLayout) convertView.findViewById(R.id.rosterImageRL3);
                if (tabletFlag)
                    rosterImageRL3.setLayoutParams(new RelativeLayout.LayoutParams(imageWidth, imageHeight));
                else
                    rosterImageRL3.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, imageHeight));

                convertView.findViewById(R.id.rosterItemProgressBar3).setPadding(0, 0, 0, 0);
                ((ProgressBar)convertView.findViewById(R.id.rosterItemProgressBar3)).getIndeterminateDrawable().setColorFilter(activeColour, PorterDuff.Mode.MULTIPLY);

                RelativeLayout.LayoutParams params3 = null;
                if (tabletFlag)
                    params3 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, imageHeight);
                else
                    params3 = new RelativeLayout.LayoutParams(imageWidth, imageHeight);
                convertView.findViewById(R.id.iv3).setLayoutParams(params3);
                convertView.findViewById(R.id.iv3).setClickable(true);
                String channelImagePlusCategorySlug = "";
                try {
                    channelImagePlusCategorySlug = spotLightCategoriesDTOListForCategories.get(position).getSpotLightCategoriesDTO3().getSpotLightChannelDTOList().get(0).getId() + "|" + "genre";
                } catch (Exception e) {

                }
                convertView.findViewById(R.id.iv3).setTag(convertView.findViewById(R.id.iv3).getId(), spotLightCategoriesDTOListForCategories.get(position).getSpotLightCategoriesDTO3().getCategorySlug());

                String imageString = "";
                try {
                    //imageString = spotLightCategoriesDTOList.get((position*2)+1).getSpotLightChannelDTOList().get(0).getImage();
                    imageString = spotLightCategoriesDTOListForCategories.get(position).getSpotLightCategoriesDTO3().getPoster();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if(imageString == null) {
                    try {
                        //if the poster image is not found then we are showing the Channel Name in text view instead
                        if(spotLightCategoriesDTOListForCategories.get(position) != null &&
                                spotLightCategoriesDTOListForCategories.get(position).getSpotLightCategoriesDTO3().getCategoryName() != null &&
                                spotLightCategoriesDTOListForCategories.get(position).getSpotLightCategoriesDTO3().getCategoryName().length() > 0) {

                            ((TextView) convertView.findViewById(R.id.tv3)).setText(spotLightCategoriesDTOListForCategories.get(position).getSpotLightCategoriesDTO3().getCategoryName());
                            ((TextView) convertView.findViewById(R.id.tv3)).setVisibility(View.VISIBLE);
                            convertView.findViewById(R.id.rosterItemProgressBar3).setVisibility(View.GONE);
                        }
                    } catch(Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    if (imageString != null)
                        imageString = CommonUtils.replaceDotstudioproWithMyspotlightForImage(imageString);

                    String imageURLAddress = imageString + "/" + imageWidth + "/" + imageHeight;
                    System.out.println("CategoriesAdapter ImageData==>" + imageURLAddress);
                    System.out.println("Position==>" + position);
                    Uri uri = Uri.parse(imageURLAddress);
                    ((SimpleDraweeView) convertView.findViewById(R.id.iv3)).setImageURI(uri);
                    convertView.findViewById(R.id.iv3).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            clickHandlerForChannelsImage(v.getTag(v.getId()).toString());//clickHandlerForChannelsImage(v.getTag().toString());
                        }
                    });
                }

                //convertView.setBackgroundColor(Color.parseColor("#2C2F34"));
                int textHeight = getHeight(activity, "TestValue", (int) 21f, imageWidth);
                if (position == 0) {
                    convertView.findViewById(R.id.extraSpaceViewTop).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ApplicationConstants.TOP_PADDING_FOR_MAIN_ACTION_BAR));
                    convertView.findViewById(R.id.extraSpaceViewTop).setVisibility(View.VISIBLE);
                    //convertView.setBackgroundColor(activeColour);

                    convertView.findViewById(R.id.extraSpaceViewGenre).setVisibility(View.GONE);
                } else if (position == spotLightCategoriesDTOListForCategories.size() - 1 && (position) > 2) {
                    convertView.findViewById(R.id.extraSpaceViewTop).setVisibility(View.GONE);
                    convertView.findViewById(R.id.extraSpaceViewGenre).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ApplicationConstants.BOTTOM_PADDING_FOR_TAB_BAR));
                    convertView.findViewById(R.id.extraSpaceViewGenre).setVisibility(View.VISIBLE);

                    //((RelativeLayout.LayoutParams)categoryTitleTV.getLayoutParams()).setMargins(0, 0, 0, ApplicationConstants.BOTTOM_PADDING_FOR_TAB_BAR);

                    ((AbsListView.LayoutParams) convertView.getLayoutParams()).width = displayMetrics.widthPixels;
                } else {
                    convertView.findViewById(R.id.extraSpaceViewGenre).setVisibility(View.GONE);
                }
            } else {
                //((RelativeLayout) convertView.findViewById(R.id.thirdImageRelativeLayout)).setVisibility(View.GONE);
                System.out.println("THERE IS NO IMAGE FOR THIS");
                try {
                    if(spotLightCategoriesDTOListForCategories.get(position).getSpotLightCategoriesDTO3().getCategoryName() != null)
                        ((TextView) convertView.findViewById(R.id.tv3)).setText(spotLightCategoriesDTOListForCategories.get(position).getSpotLightCategoriesDTO3().getCategoryName());
                    else
                        ((TextView) convertView.findViewById(R.id.tv3)).setText("No Data Found");
                    ((TextView) convertView.findViewById(R.id.tv3)).setVisibility(View.VISIBLE);
                    ((SimpleDraweeView) convertView.findViewById(R.id.iv3)).setVisibility(View.GONE);
                    convertView.findViewById(R.id.rosterItemProgressBar3).setVisibility(View.GONE);
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
        iCategoriesAdapter.genreImageClickHandler(tagSent);
    }

    public interface ICategoriesAdapter {
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
