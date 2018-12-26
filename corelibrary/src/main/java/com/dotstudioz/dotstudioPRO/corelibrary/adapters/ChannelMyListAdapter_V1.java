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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dotstudioz.dotstudioPRO.corelibrary.R;
import com.dotstudioz.dotstudioPRO.services.constants.ApplicationConstants;
import com.dotstudioz.dotstudioPRO.corelibrary.constants.FontsConstants;
import com.dotstudioz.dotstudioPRO.models.dto.ChannelsMyListDTOForMyList;
import com.dotstudioz.dotstudioPRO.corelibrary.util.CommonUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;
import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;

import java.util.List;

/**
 * Created by Admin on 03-12-2014.
 */
public class ChannelMyListAdapter_V1 extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    public List<ChannelsMyListDTOForMyList> channelsMyListDTOForMyListArrayList;
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

    public IChannelMyListAdapter iChannelMyListAdapter;

    public ChannelMyListAdapter_V1(Activity activity, List<ChannelsMyListDTOForMyList> channelsMyListDTOForMyListArrayList) {
        this.activity = activity;
        iChannelMyListAdapter = (IChannelMyListAdapter) activity;
        this.channelsMyListDTOForMyListArrayList = channelsMyListDTOForMyListArrayList;
    }

    @Override
    public int getCount() {
        return channelsMyListDTOForMyListArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return channelsMyListDTOForMyListArrayList.get(position);
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
            convertView = inflater.inflate(R.layout.channel_my_list_item_v1, parent, false);
        }

        try {

            int textHeight = getHeight(activity, "TestValue", (int) 21f, imageWidth);

            if(channelsMyListDTOForMyListArrayList.get(position).getChannelMyListDTO1() != null &&
                    channelsMyListDTOForMyListArrayList.get(position).getChannelMyListDTO1().getPoster() != null) {

                ((RelativeLayout) convertView.findViewById(R.id.firstImageRelativeLayout)).getLayoutParams().width = imageWidth;
                ((RelativeLayout) convertView.findViewById(R.id.firstImageRelativeLayout)).getLayoutParams().height = imageHeight;
                int textHeight1 = getHeight(activity, "TestValue", (int) 21f, imageWidth);
                RelativeLayout rosterImageRL1 = (RelativeLayout) convertView.findViewById(R.id.rosterImageRL1);
                if (tabletFlag)
                    rosterImageRL1.setLayoutParams(new RelativeLayout.LayoutParams(imageWidth, (imageHeight + textHeight + ApplicationConstants.TOP_PADDING_FOR_MAIN_ACTION_BAR)));
                else
                    rosterImageRL1.setLayoutParams(new RelativeLayout.LayoutParams(imageWidth, (imageHeight + ApplicationConstants.TOP_PADDING_FOR_MAIN_ACTION_BAR)));

                convertView.findViewById(R.id.rosterItemProgressBar1).setPadding(0, 0, 0, 0);
                ((ProgressBar) convertView.findViewById(R.id.rosterItemProgressBar1)).getIndeterminateDrawable().setColorFilter(activeColour, PorterDuff.Mode.MULTIPLY);

                RelativeLayout.LayoutParams params1 = null;
                if (tabletFlag)
                    params1 = new RelativeLayout.LayoutParams(imageWidth, imageHeight);
                else
                    params1 = new RelativeLayout.LayoutParams(imageWidth, imageHeight);
                convertView.findViewById(R.id.iv1).setLayoutParams(params1);
                convertView.findViewById(R.id.iv1).setClickable(true);

                convertView.findViewById(R.id.iv1).setTag(convertView.findViewById(R.id.iv1).getId(), channelsMyListDTOForMyListArrayList.get(position).getChannelMyListDTO1().getSlug());

                String imageString = "";
                try {
                    //imageString = spotLightCategoriesDTOList.get((position*2)).getSpotLightChannelDTOList().get(0).getImage();
                    imageString = channelsMyListDTOForMyListArrayList.get(position).getChannelMyListDTO1().getSpotlightPoster();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                imageString = CommonUtils.replaceDotstudioproWithMyspotlightForImage(imageString);

                String imageURLAddress = imageString;// + "/" + imageWidth + "/" + imageHeight;
                Uri uri = Uri.parse(imageURLAddress);
                ((SimpleDraweeView) convertView.findViewById(R.id.iv1)).setImageURI(uri);
                convertView.findViewById(R.id.iv1).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickHandlerForChannelsImage(v.getTag(v.getId()).toString());//clickHandlerForChannelsImage(v.getTag().toString());
                    }
                });

                try {
                    //if the poster image is not found then we are showing the Channel Name in text view instead
                    if (channelsMyListDTOForMyListArrayList.get(position) != null &&
                            channelsMyListDTOForMyListArrayList.get(position).getChannelMyListDTO1().getSpotlightPoster() != null &&
                            channelsMyListDTOForMyListArrayList.get(position).getChannelMyListDTO1().getSpotlightPoster().length() < 1) {
                        if (channelsMyListDTOForMyListArrayList.get(position).getChannelMyListDTO1().getTitle() != null &&
                                channelsMyListDTOForMyListArrayList.get(position).getChannelMyListDTO1().getTitle().length() > 0) {
                            ((TextView) convertView.findViewById(R.id.tv1)).setText(channelsMyListDTOForMyListArrayList.get(position).getChannelMyListDTO1().getTitle());

                        }
                    } else {
                        ((TextView) convertView.findViewById(R.id.tv1)).setVisibility(View.GONE);
                    }

                    if (channelsMyListDTOForMyListArrayList.get(position).getChannelMyListDTO1().getTitle() != null) {
                        ((TextView) convertView.findViewById(R.id.tv1)).setText(channelsMyListDTOForMyListArrayList.get(position).getChannelMyListDTO1().getTitle());
                        ((TextView) convertView.findViewById(R.id.tv1)).setVisibility(View.VISIBLE);
                    }
                    ((TextView) convertView.findViewById(R.id.tv1)).setVisibility(View.GONE);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                setDeleteButtonUIandEventLitener(((ImageView)convertView.findViewById(R.id.deleteButton1)), position, 1);
            } else {
                convertView.findViewById(R.id.rosterItemProgressBar1).setVisibility(View.GONE);
            }





            ((RelativeLayout) convertView.findViewById(R.id.secondImageRelativeLayout)).getLayoutParams().width = imageWidth;
            ((RelativeLayout) convertView.findViewById(R.id.secondImageRelativeLayout)).getLayoutParams().height = imageHeight;
            if(channelsMyListDTOForMyListArrayList.get(position).getChannelMyListDTO2() != null &&
                    channelsMyListDTOForMyListArrayList.get(position).getChannelMyListDTO2().getSpotlightPoster() != null) {

                int textHeight1 = getHeight(activity, "TestValue", (int) 21f, imageWidth);
                RelativeLayout rosterImageRL1 = (RelativeLayout) convertView.findViewById(R.id.rosterImageRL2);
                if (tabletFlag)
                    rosterImageRL1.setLayoutParams(new RelativeLayout.LayoutParams(imageWidth, (imageHeight + textHeight + ApplicationConstants.TOP_PADDING_FOR_MAIN_ACTION_BAR)));
                else
                    rosterImageRL1.setLayoutParams(new RelativeLayout.LayoutParams(imageWidth, (imageHeight + ApplicationConstants.TOP_PADDING_FOR_MAIN_ACTION_BAR)));

                convertView.findViewById(R.id.rosterItemProgressBar2).setPadding(0, 0, 0, 0);
                ((ProgressBar) convertView.findViewById(R.id.rosterItemProgressBar2)).getIndeterminateDrawable().setColorFilter(activeColour, PorterDuff.Mode.MULTIPLY);

                RelativeLayout.LayoutParams params1 = null;
                if (tabletFlag)
                    params1 = new RelativeLayout.LayoutParams(imageWidth, imageHeight);
                else
                    params1 = new RelativeLayout.LayoutParams(imageWidth, imageHeight);
                convertView.findViewById(R.id.iv2).setLayoutParams(params1);
                convertView.findViewById(R.id.iv2).setClickable(true);

                convertView.findViewById(R.id.iv2).setTag(convertView.findViewById(R.id.iv2).getId(), channelsMyListDTOForMyListArrayList.get(position).getChannelMyListDTO2().getSlug());

                String imageString = "";
                try {
                    //imageString = spotLightCategoriesDTOList.get((position*2)).getSpotLightChannelDTOList().get(0).getImage();
                    imageString = channelsMyListDTOForMyListArrayList.get(position).getChannelMyListDTO2().getSpotlightPoster();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                imageString = CommonUtils.replaceDotstudioproWithMyspotlightForImage(imageString);

                String imageURLAddress = imageString;// + "/" + imageWidth + "/" + imageHeight;
                Uri uri = Uri.parse(imageURLAddress);
                ((SimpleDraweeView) convertView.findViewById(R.id.iv2)).setImageURI(uri);
                convertView.findViewById(R.id.iv2).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickHandlerForChannelsImage(v.getTag(v.getId()).toString());//clickHandlerForChannelsImage(v.getTag().toString());
                    }
                });

                try {
                    //if the poster image is not found then we are showing the Channel Name in text view instead
                    if (channelsMyListDTOForMyListArrayList.get(position) != null &&
                            channelsMyListDTOForMyListArrayList.get(position).getChannelMyListDTO2().getSpotlightPoster() != null &&
                            channelsMyListDTOForMyListArrayList.get(position).getChannelMyListDTO2().getSpotlightPoster().length() < 1) {
                        if (channelsMyListDTOForMyListArrayList.get(position).getChannelMyListDTO2().getTitle() != null &&
                                channelsMyListDTOForMyListArrayList.get(position).getChannelMyListDTO2().getTitle().length() > 0) {
                            ((TextView) convertView.findViewById(R.id.tv2)).setText(channelsMyListDTOForMyListArrayList.get(position).getChannelMyListDTO2().getTitle());

                        }
                    } else {
                        ((TextView) convertView.findViewById(R.id.tv2)).setVisibility(View.GONE);
                    }

                    if (channelsMyListDTOForMyListArrayList.get(position).getChannelMyListDTO2().getTitle() != null) {
                        ((TextView) convertView.findViewById(R.id.tv2)).setText(channelsMyListDTOForMyListArrayList.get(position).getChannelMyListDTO2().getTitle());
                        ((TextView) convertView.findViewById(R.id.tv2)).setVisibility(View.VISIBLE);
                    }
                    ((TextView) convertView.findViewById(R.id.tv2)).setVisibility(View.GONE);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                setDeleteButtonUIandEventLitener(((ImageView)convertView.findViewById(R.id.deleteButton2)), position, 2);


            } else {
                convertView.findViewById(R.id.rosterItemProgressBar2).setVisibility(View.INVISIBLE);
            }



            ((RelativeLayout) convertView.findViewById(R.id.thirdImageRelativeLayout)).getLayoutParams().width = imageWidth;
            ((RelativeLayout) convertView.findViewById(R.id.thirdImageRelativeLayout)).getLayoutParams().height = imageHeight;
            if(channelsMyListDTOForMyListArrayList.get(position).getChannelMyListDTO3() != null &&
                    channelsMyListDTOForMyListArrayList.get(position).getChannelMyListDTO3().getSpotlightPoster() != null) {

                int textHeight1 = getHeight(activity, "TestValue", (int) 21f, imageWidth);
                RelativeLayout rosterImageRL1 = (RelativeLayout) convertView.findViewById(R.id.rosterImageRL3);
                if (tabletFlag)
                    rosterImageRL1.setLayoutParams(new RelativeLayout.LayoutParams(imageWidth, (imageHeight + textHeight + ApplicationConstants.TOP_PADDING_FOR_MAIN_ACTION_BAR)));
                else
                    rosterImageRL1.setLayoutParams(new RelativeLayout.LayoutParams(imageWidth, (imageHeight + ApplicationConstants.TOP_PADDING_FOR_MAIN_ACTION_BAR)));

                convertView.findViewById(R.id.rosterItemProgressBar3).setPadding(0, 0, 0, 0);
                ((ProgressBar) convertView.findViewById(R.id.rosterItemProgressBar3)).getIndeterminateDrawable().setColorFilter(activeColour, PorterDuff.Mode.MULTIPLY);

                RelativeLayout.LayoutParams params1 = null;
                if (tabletFlag)
                    params1 = new RelativeLayout.LayoutParams(imageWidth, imageHeight);
                else
                    params1 = new RelativeLayout.LayoutParams(imageWidth, imageHeight);
                convertView.findViewById(R.id.iv3).setLayoutParams(params1);
                convertView.findViewById(R.id.iv3).setClickable(true);

                convertView.findViewById(R.id.iv3).setTag(convertView.findViewById(R.id.iv3).getId(), channelsMyListDTOForMyListArrayList.get(position).getChannelMyListDTO3().getSlug());

                String imageString = "";
                try {
                    //imageString = spotLightCategoriesDTOList.get((position*2)).getSpotLightChannelDTOList().get(0).getImage();
                    imageString = channelsMyListDTOForMyListArrayList.get(position).getChannelMyListDTO3().getSpotlightPoster();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                imageString = CommonUtils.replaceDotstudioproWithMyspotlightForImage(imageString);

                String imageURLAddress = imageString;// + "/" + imageWidth + "/" + imageHeight;
                Uri uri = Uri.parse(imageURLAddress);
                ((SimpleDraweeView) convertView.findViewById(R.id.iv3)).setImageURI(uri);
                convertView.findViewById(R.id.iv3).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickHandlerForChannelsImage(v.getTag(v.getId()).toString());//clickHandlerForChannelsImage(v.getTag().toString());
                    }
                });

                try {
                    //if the poster image is not found then we are showing the Channel Name in text view instead
                    if (channelsMyListDTOForMyListArrayList.get(position) != null &&
                            channelsMyListDTOForMyListArrayList.get(position).getChannelMyListDTO3().getSpotlightPoster() != null &&
                            channelsMyListDTOForMyListArrayList.get(position).getChannelMyListDTO3().getSpotlightPoster().length() < 1) {
                        if (channelsMyListDTOForMyListArrayList.get(position).getChannelMyListDTO3().getTitle() != null &&
                                channelsMyListDTOForMyListArrayList.get(position).getChannelMyListDTO3().getTitle().length() > 0) {
                            ((TextView) convertView.findViewById(R.id.tv3)).setText(channelsMyListDTOForMyListArrayList.get(position).getChannelMyListDTO3().getTitle());

                        }
                    } else {
                        ((TextView) convertView.findViewById(R.id.tv3)).setVisibility(View.GONE);
                    }

                    if (channelsMyListDTOForMyListArrayList.get(position).getChannelMyListDTO3().getTitle() != null) {
                        ((TextView) convertView.findViewById(R.id.tv3)).setText(channelsMyListDTOForMyListArrayList.get(position).getChannelMyListDTO3().getTitle());
                        ((TextView) convertView.findViewById(R.id.tv3)).setVisibility(View.VISIBLE);
                    }
                    ((TextView) convertView.findViewById(R.id.tv3)).setVisibility(View.GONE);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                setDeleteButtonUIandEventLitener(((ImageView)convertView.findViewById(R.id.deleteButton3)), position, 3);

            } else {
                convertView.findViewById(R.id.rosterItemProgressBar3).setVisibility(View.INVISIBLE);
            }





            ((RelativeLayout) convertView.findViewById(R.id.fourthImageRelativeLayout)).getLayoutParams().width = imageWidth;
            ((RelativeLayout) convertView.findViewById(R.id.fourthImageRelativeLayout)).getLayoutParams().height = imageHeight;
            if(channelsMyListDTOForMyListArrayList.get(position).getChannelMyListDTO4() != null &&
                    channelsMyListDTOForMyListArrayList.get(position).getChannelMyListDTO4().getSpotlightPoster() != null) {

                int textHeight1 = getHeight(activity, "TestValue", (int) 21f, imageWidth);
                RelativeLayout rosterImageRL1 = (RelativeLayout) convertView.findViewById(R.id.rosterImageRL4);
                if (tabletFlag)
                    rosterImageRL1.setLayoutParams(new RelativeLayout.LayoutParams(imageWidth, (imageHeight + textHeight + ApplicationConstants.TOP_PADDING_FOR_MAIN_ACTION_BAR)));
                else
                    rosterImageRL1.setLayoutParams(new RelativeLayout.LayoutParams(imageWidth, (imageHeight + ApplicationConstants.TOP_PADDING_FOR_MAIN_ACTION_BAR)));

                convertView.findViewById(R.id.rosterItemProgressBar4).setPadding(0, 0, 0, 0);
                ((ProgressBar) convertView.findViewById(R.id.rosterItemProgressBar4)).getIndeterminateDrawable().setColorFilter(activeColour, PorterDuff.Mode.MULTIPLY);

                RelativeLayout.LayoutParams params1 = null;
                if (tabletFlag)
                    params1 = new RelativeLayout.LayoutParams(imageWidth, imageHeight);
                else
                    params1 = new RelativeLayout.LayoutParams(imageWidth, imageHeight);
                convertView.findViewById(R.id.iv4).setLayoutParams(params1);
                convertView.findViewById(R.id.iv4).setClickable(true);

                convertView.findViewById(R.id.iv4).setTag(convertView.findViewById(R.id.iv4).getId(), channelsMyListDTOForMyListArrayList.get(position).getChannelMyListDTO4().getSlug());

                String imageString = "";
                try {
                    //imageString = spotLightCategoriesDTOList.get((position*2)).getSpotLightChannelDTOList().get(0).getImage();
                    imageString = channelsMyListDTOForMyListArrayList.get(position).getChannelMyListDTO4().getSpotlightPoster();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                imageString = CommonUtils.replaceDotstudioproWithMyspotlightForImage(imageString);

                String imageURLAddress = imageString;// + "/" + imageWidth + "/" + imageHeight;
                Uri uri = Uri.parse(imageURLAddress);
                ((SimpleDraweeView) convertView.findViewById(R.id.iv4)).setImageURI(uri);
                convertView.findViewById(R.id.iv4).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickHandlerForChannelsImage(v.getTag(v.getId()).toString());//clickHandlerForChannelsImage(v.getTag().toString());
                    }
                });

                try {
                    //if the poster image is not found then we are showing the Channel Name in text view instead
                    if (channelsMyListDTOForMyListArrayList.get(position) != null &&
                            channelsMyListDTOForMyListArrayList.get(position).getChannelMyListDTO4().getSpotlightPoster() != null &&
                            channelsMyListDTOForMyListArrayList.get(position).getChannelMyListDTO4().getPoster().length() < 1) {
                        if (channelsMyListDTOForMyListArrayList.get(position).getChannelMyListDTO4().getTitle() != null &&
                                channelsMyListDTOForMyListArrayList.get(position).getChannelMyListDTO4().getTitle().length() > 0) {
                            ((TextView) convertView.findViewById(R.id.tv4)).setText(channelsMyListDTOForMyListArrayList.get(position).getChannelMyListDTO4().getTitle());

                        }
                    } else {
                        ((TextView) convertView.findViewById(R.id.tv4)).setVisibility(View.GONE);
                    }

                    if (channelsMyListDTOForMyListArrayList.get(position).getChannelMyListDTO4().getTitle() != null) {
                        ((TextView) convertView.findViewById(R.id.tv4)).setText(channelsMyListDTOForMyListArrayList.get(position).getChannelMyListDTO4().getTitle());
                        ((TextView) convertView.findViewById(R.id.tv4)).setVisibility(View.VISIBLE);
                    }
                    ((TextView) convertView.findViewById(R.id.tv4)).setVisibility(View.GONE);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                setDeleteButtonUIandEventLitener(((ImageView)convertView.findViewById(R.id.deleteButton4)), position, 4);

            } else {
                convertView.findViewById(R.id.rosterItemProgressBar4).setVisibility(View.INVISIBLE);
            }


            if(position == 0) {
                convertView.findViewById(R.id.extraSpaceViewTop).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ApplicationConstants.TOP_PADDING_FOR_MAIN_ACTION_BAR));
                convertView.findViewById(R.id.extraSpaceViewTop).setVisibility(View.VISIBLE);
                //convertView.setBackgroundColor(activeColour);

                convertView.findViewById(R.id.extraSpaceViewGenre).setVisibility(View.GONE);
            } else if(position == channelsMyListDTOForMyListArrayList.size()-1 && position > 2) {
                convertView.findViewById(R.id.extraSpaceViewTop).setVisibility(View.GONE);
                convertView.findViewById(R.id.extraSpaceViewGenre).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ApplicationConstants.BOTTOM_PADDING_FOR_TAB_BAR));
                convertView.findViewById(R.id.extraSpaceViewGenre).setVisibility(View.VISIBLE);

                //((RelativeLayout.LayoutParams)categoryTitleTV.getLayoutParams()).setMargins(0, 0, 0, ApplicationConstants.BOTTOM_PADDING_FOR_TAB_BAR);

                ((AbsListView.LayoutParams)convertView.getLayoutParams()).width = displayMetrics.widthPixels;
            } else {
                ((AbsListView.LayoutParams)convertView.getLayoutParams()).width = displayMetrics.widthPixels;

                convertView.findViewById(R.id.extraSpaceViewGenre).setVisibility(View.GONE);
            }


        } catch(Exception e) {
            //e.printStackTrace();
        }

        return convertView;
    }

    public void setDeleteButtonUIandEventLitener(ImageView imv, int pos, int positionOfDTO) {
        imv.setImageDrawable(new IconDrawable(activity, FontAwesomeIcons.fa_trash).color(Color.parseColor("#ffffff")));
        if(positionOfDTO == 1)
            imv.setTag(imv.getId(), channelsMyListDTOForMyListArrayList.get(pos).getChannelMyListDTO1().getId());
        else if(positionOfDTO == 2)
            imv.setTag(imv.getId(), channelsMyListDTOForMyListArrayList.get(pos).getChannelMyListDTO2().getId());
        else if(positionOfDTO == 3)
            imv.setTag(imv.getId(), channelsMyListDTOForMyListArrayList.get(pos).getChannelMyListDTO3().getId());
        else if(positionOfDTO == 4)
            imv.setTag(imv.getId(), channelsMyListDTOForMyListArrayList.get(pos).getChannelMyListDTO4().getId());
        imv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iChannelMyListAdapter.deleteButtonClicked(view.getTag(view.getId()).toString());
                //Toast.makeText(activity, "Delete ==>"+view.getTag(view.getId()), Toast.LENGTH_SHORT).show();
            }
        });
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
        //types of views
        return 1;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    private void clickHandlerForChannelsImage(String tagSent) {
        iChannelMyListAdapter.channelMyListImageClickHandler(tagSent);
    }

    public interface IChannelMyListAdapter {
        void channelMyListImageClickHandler(String tagSent);
        void deleteButtonClicked(String channelID);
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
