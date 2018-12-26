package com.dotstudioz.dotstudioPRO.corelibrary.adapters;

/**
 * Created by mohsin on 15-10-2016.
 */

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dotstudioz.dotstudioPRO.corelibrary.R;
import com.dotstudioz.dotstudioPRO.services.constants.ApplicationConstants;
import com.dotstudioz.dotstudioPRO.services.constants.FontsConstants;
import com.dotstudioz.dotstudioPRO.models.dto.SpotLightCategoriesDTO;
import com.dotstudioz.dotstudioPRO.models.dto.VideoInfoDTO;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;


public class VideoOrChannelItemPagerAdapter_V2 extends PagerAdapter {

    public IVideoOrChannelItemPagerAdapter iVideoOrChannelItemPagerAdapter;

    public List<VideoInfoDTO> videoInfoDTOArrayList;
    public String categoryName;
    public SpotLightCategoriesDTO spotLightCategoriesDTO;
    public boolean isNextItemClicked = false;
    public boolean isNextNextItemClicked = false;
    public boolean isNextNextNextItemClicked = false;
    public boolean isNextNextNextNextItemClicked = false;
    public String channelIdPlusCategorySlugFromMultiViewPager = "";
    public int imageHeight;
    public int imageWidth;
    public int channelPosterWidth;
    public int channelPosterHeight;
    private int titleColour = Color.parseColor("#eeeeee");
    private int actorsColour = Color.parseColor("#eeeeee");
    public boolean showActor = false;
    private int childProgressColour = Color.parseColor("#F3AB13");

    public float videoTitleTVFontSize;

    private boolean isCustomFontEnabledForVideoTitle = false;
    private Typeface customFontForVideoTitle;

    Context mContext;
    LayoutInflater mLayoutInflater;

    public VideoOrChannelItemPagerAdapter_V2(Context context) {
        iVideoOrChannelItemPagerAdapter = (IVideoOrChannelItemPagerAdapter) context;
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View itemView = mLayoutInflater.inflate(R.layout.video_item_frame_layout_view_v1, container, false);

        RelativeLayout fl = (RelativeLayout) itemView.findViewById(R.id.frameLayout);
        //fl.setLayoutParams(new LinearLayout.LayoutParams((imageWidth+20), (imageHeight+5)));
        fl.setLayoutParams(new LinearLayout.LayoutParams((imageWidth+20), (imageHeight)));

        TextView videoTitleTV = (TextView) itemView.findViewById(R.id.videoTitleTV);
        videoTitleTV.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        if(!showActor) {
            videoTitleTV.setSingleLine(false);
            videoTitleTV.setMaxLines(2);
        } else {
            videoTitleTV.setSingleLine(true);
        }
        videoTitleTV.setPadding(0, 0, 20, 5);
        videoTitleTV.setEllipsize(TextUtils.TruncateAt.END);
        videoTitleTV.setTextSize(videoTitleTVFontSize);
        if(isCustomFontEnabledForVideoTitle)
            videoTitleTV.setTypeface(customFontForVideoTitle);
        else
            videoTitleTV.setTypeface(FontsConstants.tfRegular);
        videoTitleTV.setGravity(Gravity.LEFT);
        //videoTitleTV.setShadowLayer(2f, -1, 1, Color.BLACK);
        videoTitleTV.setTextColor(Color.WHITE);
        videoTitleTV.setTextColor(titleColour);

        TextView actorsTV = (TextView) itemView.findViewById(R.id.actorsTV);
        actorsTV.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        actorsTV.setSingleLine(true);
        actorsTV.setPadding(0, 0, 20, 10);
        actorsTV.setEllipsize(TextUtils.TruncateAt.END);
        actorsTV.setTextSize(12f);
        if(isCustomFontEnabledForVideoTitle)
            actorsTV.setTypeface(customFontForVideoTitle);
        else
            actorsTV.setTypeface(FontsConstants.tfRegular);
        actorsTV.setGravity(Gravity.LEFT);
        //actorsTV.setShadowLayer(2f, -1, 1, Color.BLACK);
        //actorsTV.setTextColor(Color.parseColor("#6dec68"));
        actorsTV.setTextColor(actorsColour);

        final SimpleDraweeView imageView = (SimpleDraweeView) itemView.findViewById(R.id.siv);


        if(spotLightCategoriesDTO.getSpotLightChannelDTOList().size() > 0 &&
                spotLightCategoriesDTO.getVideoInfoDTOList().size() == 0 &&
                !spotLightCategoriesDTO.isParentChannel()) {


            try {
                if(channelPosterWidth > 0 && channelPosterHeight > 0)
                    fl.setLayoutParams(new LinearLayout.LayoutParams((channelPosterWidth+20), (channelPosterHeight)));
                /*else
                    fl.setLayoutParams(new LinearLayout.LayoutParams((imageWidth+20), (imageHeight)));*/

                if(channelPosterWidth > 0 && channelPosterHeight > 0)
                    imageView.setLayoutParams(new RelativeLayout.LayoutParams(channelPosterWidth, channelPosterHeight));
                else
                    imageView.setLayoutParams(new RelativeLayout.LayoutParams(imageWidth, imageHeight));


                String channelIdPlusCategorySlug = spotLightCategoriesDTO.getSpotLightChannelDTOList().get(position).getId() + "|" + spotLightCategoriesDTO.getCategorySlug();
                System.out.println("channelIdPlusCategorySlug==>"+channelIdPlusCategorySlug);
                imageView.setTag(imageView.getId(), channelIdPlusCategorySlug);

                if (spotLightCategoriesDTO.getCategorySlug().equals("hero-showcase"))
                    videoTitleTV.setText(spotLightCategoriesDTO.getSpotLightChannelDTOList().get(position).getTitle());

                if ((spotLightCategoriesDTO.getSpotLightChannelDTOList().get(position).getVideo() != null &&
                        spotLightCategoriesDTO.getSpotLightChannelDTOList().get(position).getVideo().length() > 0) ||
                        (spotLightCategoriesDTO.getSpotLightChannelDTOList().get(position).getPlaylist() != null &&
                                spotLightCategoriesDTO.getSpotLightChannelDTOList().get(position).getPlaylist().size() > 0)) {
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            System.out.println("Onclick Slug ==>" + view.getTag(view.getId()).toString());
                          /*  if(channelIdPlusCategorySlugFromMultiViewPager != null && channelIdPlusCategorySlugFromMultiViewPager.length() > 0) {
                                System.out.println("channelIdPlusCategorySlugFromMultiViewPager==>" + channelIdPlusCategorySlugFromMultiViewPager);
                                iVideoOrChannelItemPagerAdapter.channelItemClickHandler(channelIdPlusCategorySlugFromMultiViewPager);
                            } else {
                                System.out.println("view.getTag(view.getId()).toString()==>" + view.getTag(view.getId()).toString());
                                iVideoOrChannelItemPagerAdapter.channelItemClickHandler(view.getTag(view.getId()).toString());
                            }*/

                            if (isNextNextNextNextItemClicked) {
                                String tagValue = view.getTag(view.getId()).toString();
                                tagValue = tagValue.substring(0, tagValue.indexOf("|"));

                                System.out.println("isNextNextNextNextItemClicked view.getTag(view.getId()).toString()==>" + tagValue +"position"+position);
                                // tagValue = tagValue.substring((tagValue.indexOf("|")) + 1, tagValue.length());
                                //System.out.println("tagValue"+tagValue);
                                for (int i = 0; i < spotLightCategoriesDTO.getSpotLightChannelDTOList().size(); i++) {
                                    if (spotLightCategoriesDTO.getSpotLightChannelDTOList().get(i).getId().equals(tagValue)) {
                                        //System.out.println("spotLightCategoriesDTO.getSpotLightChannelDTOList().get(0).getVideoInfoDTOList().get(position).getThumb()"+spotLightCategoriesDTO.getSpotLightChannelDTOList().get(0).getVideoInfoDTOList().get(i+1).getThumb());
                                        if (CommonUtils.isActuallyTabletDevice((Activity) mContext)) {
                                            iVideoOrChannelItemPagerAdapter.channelItemClickHandler(spotLightCategoriesDTO.getSpotLightChannelDTOList().get(i).getId());

                                        } else {
                                            iVideoOrChannelItemPagerAdapter.channelItemClickHandler(spotLightCategoriesDTO.getSpotLightChannelDTOList().get(i+4).getId());
                                        }
                                        isNextItemClicked = false;
                                    }
                                }
                            } else if (isNextNextNextItemClicked){
                                String tagValue = view.getTag(view.getId()).toString();
                                tagValue = tagValue.substring(0, tagValue.indexOf("|"));

                                System.out.println("isNextNextNextItemClicked view.getTag(view.getId()).toString()==>" + tagValue +"position"+position);
                                // tagValue = tagValue.substring((tagValue.indexOf("|")) + 1, tagValue.length());
                                //System.out.println("tagValue"+tagValue);
                                for (int i = 0; i < spotLightCategoriesDTO.getSpotLightChannelDTOList().size(); i++) {
                                    if (spotLightCategoriesDTO.getSpotLightChannelDTOList().get(i).getId().equals(tagValue)) {
                                        //System.out.println("spotLightCategoriesDTO.getSpotLightChannelDTOList().get(0).getVideoInfoDTOList().get(position).getThumb()"+spotLightCategoriesDTO.getSpotLightChannelDTOList().get(0).getVideoInfoDTOList().get(i+1).getThumb());
                                        if (CommonUtils.isActuallyTabletDevice((Activity) mContext)) {
                                            iVideoOrChannelItemPagerAdapter.channelItemClickHandler(spotLightCategoriesDTO.getSpotLightChannelDTOList().get(i).getId());

                                        } else {
                                            iVideoOrChannelItemPagerAdapter.channelItemClickHandler(spotLightCategoriesDTO.getSpotLightChannelDTOList().get(i+3).getId());
                                        }
                                        isNextItemClicked = false;
                                    }
                                }
                            } else if (isNextNextItemClicked) {
                                String tagValue = view.getTag(view.getId()).toString();
                                tagValue = tagValue.substring(0, tagValue.indexOf("|"));

                                System.out.println("isNextNextItemClicked view.getTag(view.getId()).toString()==>" + tagValue +"position"+position);
                                // tagValue = tagValue.substring((tagValue.indexOf("|")) + 1, tagValue.length());
                                //System.out.println("tagValue"+tagValue);
                                for (int i = 0; i < spotLightCategoriesDTO.getSpotLightChannelDTOList().size(); i++) {
                                    if (spotLightCategoriesDTO.getSpotLightChannelDTOList().get(i).getId().equals(tagValue)) {
                                        //System.out.println("spotLightCategoriesDTO.getSpotLightChannelDTOList().get(0).getVideoInfoDTOList().get(position).getThumb()"+spotLightCategoriesDTO.getSpotLightChannelDTOList().get(0).getVideoInfoDTOList().get(i+1).getThumb());
                                        if (CommonUtils.isActuallyTabletDevice((Activity) mContext)) {
                                            iVideoOrChannelItemPagerAdapter.channelItemClickHandler(spotLightCategoriesDTO.getSpotLightChannelDTOList().get(i).getId());

                                        } else {
                                            iVideoOrChannelItemPagerAdapter.channelItemClickHandler(spotLightCategoriesDTO.getSpotLightChannelDTOList().get(i+2).getId());
                                        }
                                        isNextItemClicked = false;
                                    }
                                }
                            }else if (isNextItemClicked) {
                                String tagValue = view.getTag(view.getId()).toString();
                                tagValue = tagValue.substring(0, tagValue.indexOf("|"));

                                System.out.println("isNextItemClicked view.getTag(view.getId()).toString()==>" + tagValue +"position"+position);
                                // tagValue = tagValue.substring((tagValue.indexOf("|")) + 1, tagValue.length());
                                //System.out.println("tagValue"+tagValue);
                                for (int i = 0; i < spotLightCategoriesDTO.getSpotLightChannelDTOList().size(); i++) {
                                    if (spotLightCategoriesDTO.getSpotLightChannelDTOList().get(i).getId().equals(tagValue)) {
                                        //System.out.println("spotLightCategoriesDTO.getSpotLightChannelDTOList().get(0).getVideoInfoDTOList().get(position).getThumb()"+spotLightCategoriesDTO.getSpotLightChannelDTOList().get(0).getVideoInfoDTOList().get(i+1).getThumb());
                                        if (CommonUtils.isActuallyTabletDevice((Activity) mContext)) {
                                            iVideoOrChannelItemPagerAdapter.channelItemClickHandler(spotLightCategoriesDTO.getSpotLightChannelDTOList().get(i).getId());

                                        } else {
                                            iVideoOrChannelItemPagerAdapter.channelItemClickHandler(spotLightCategoriesDTO.getSpotLightChannelDTOList().get(i+1).getId());
                                        }
                                        isNextItemClicked = false;
                                    }
                                }
                            }else {

                                String tagString = view.getTag(view.getId()).toString();
                                System.out.println("Else isNextNextNextNextItemClicked view.getTag(view.getId()).toString()==>" + tagString +"position"+position);
                                iVideoOrChannelItemPagerAdapter.channelItemClickHandler(tagString);
                            }



                        }
                    });
                } else {
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            System.out.println("Else Onclick Slug ==>" + view.getTag(view.getId()).toString());
                            iVideoOrChannelItemPagerAdapter.channelItemClickHandler(view.getTag(view.getId()).toString());
                        }
                    });
                }

                String imageURL = "";
                if (spotLightCategoriesDTO.getCategorySlug().equals("hero-showcase"))
                    imageURL = spotLightCategoriesDTO.getSpotLightChannelDTOList().get(position).getImage() + "/" + imageWidth + "/" + imageHeight;
                else {
                    if(channelPosterWidth > 0 && channelPosterHeight > 0)
                        imageURL = spotLightCategoriesDTO.getSpotLightChannelDTOList().get(position).getSpotlightImage() + "/" + channelPosterWidth + "/" + channelPosterHeight;
                    else
                        imageURL = spotLightCategoriesDTO.getSpotLightChannelDTOList().get(position).getSpotlightImage() + "/" + imageWidth + "/" + imageHeight;
                }
                Uri uri = Uri.parse(imageURL);
                imageView.setImageURI(uri);
                //Picasso.with(mContext).load(uri).into(imageView);
                //System.out.println("IMAGE_PATH:-"+imageURL);
            } catch(Exception e) {
                e.printStackTrace();
            }
        } else if(spotLightCategoriesDTO.getSpotLightChannelDTOList().size() > 0 &&
                spotLightCategoriesDTO.getVideoInfoDTOList().size() == 0 &&
                spotLightCategoriesDTO.isParentChannel()) {
            try {
                imageView.setLayoutParams(new RelativeLayout.LayoutParams(imageWidth, imageHeight));

                String tagString = spotLightCategoriesDTO.getCategoryName() + "|" + spotLightCategoriesDTO.getSpotLightChannelDTOList().get(0).getVideoInfoDTOList().get(position).getThumb();
                imageView.setTag(imageView.getId(), tagString);

                if (spotLightCategoriesDTO.getCategorySlug().equals("hero-showcase"))
                    videoTitleTV.setText(spotLightCategoriesDTO.getSpotLightChannelDTOList().get(0).getVideoInfoDTOList().get(position).getVideoTitle());
                actorsTV.setVisibility(View.GONE);
                //actorsTV.setText(videoInfoDTOArrayList.get(position).getCasting());

                String imageURL = "https://images.dotstudiopro.com/" + spotLightCategoriesDTO.getSpotLightChannelDTOList().get(0).getVideoInfoDTOList().get(position).getThumb() + "/" + imageWidth + "/" + imageHeight;
                Uri uri = Uri.parse(imageURL);
                imageView.setImageURI(uri);
                //Picasso.with(mContext).load(uri).into(imageView);
                //System.out.println("IMAGE_PATH:-"+imageURL);

                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (isNextNextNextNextItemClicked) {
                            String tagValue = view.getTag(view.getId()).toString();
                            tagValue = tagValue.substring((tagValue.indexOf("|")) + 1, tagValue.length());
                            //System.out.println("tagValue"+tagValue);
                            for (int i = 0; i < spotLightCategoriesDTO.getSpotLightChannelDTOList().get(0).getVideoInfoDTOList().size(); i++) {
                                if (spotLightCategoriesDTO.getSpotLightChannelDTOList().get(0).getVideoInfoDTOList().get(i).getThumb().equals(tagValue)) {
                                    //System.out.println("spotLightCategoriesDTO.getSpotLightChannelDTOList().get(0).getVideoInfoDTOList().get(position).getThumb()"+spotLightCategoriesDTO.getSpotLightChannelDTOList().get(0).getVideoInfoDTOList().get(i+1).getThumb());
                                    if (CommonUtils.isActuallyTabletDevice((Activity) mContext)) {
                                        iVideoOrChannelItemPagerAdapter.videoItemClickHandler(categoryName, spotLightCategoriesDTO.getSpotLightChannelDTOList().get(0).getVideoInfoDTOList().get(i).getThumb(), position);
                                    } else {
                                        iVideoOrChannelItemPagerAdapter.videoItemClickHandler(categoryName, spotLightCategoriesDTO.getSpotLightChannelDTOList().get(0).getVideoInfoDTOList().get(i + 4).getThumb(), position);
                                    }
                                    isNextItemClicked = false;
                                }
                            }
                        } else if (isNextNextNextItemClicked) {
                            String tagValue = view.getTag(view.getId()).toString();
                            tagValue = tagValue.substring((tagValue.indexOf("|")) + 1, tagValue.length());
                            //System.out.println("tagValue"+tagValue);
                            for (int i = 0; i < spotLightCategoriesDTO.getSpotLightChannelDTOList().get(0).getVideoInfoDTOList().size(); i++) {
                                if (spotLightCategoriesDTO.getSpotLightChannelDTOList().get(0).getVideoInfoDTOList().get(i).getThumb().equals(tagValue)) {
                                    //System.out.println("spotLightCategoriesDTO.getSpotLightChannelDTOList().get(0).getVideoInfoDTOList().get(position).getThumb()"+spotLightCategoriesDTO.getSpotLightChannelDTOList().get(0).getVideoInfoDTOList().get(i+1).getThumb());
                                    if (CommonUtils.isActuallyTabletDevice((Activity) mContext)) {
                                        iVideoOrChannelItemPagerAdapter.videoItemClickHandler(categoryName, spotLightCategoriesDTO.getSpotLightChannelDTOList().get(0).getVideoInfoDTOList().get(i).getThumb(), position);
                                    } else {
                                        iVideoOrChannelItemPagerAdapter.videoItemClickHandler(categoryName, spotLightCategoriesDTO.getSpotLightChannelDTOList().get(0).getVideoInfoDTOList().get(i + 3).getThumb(), position);
                                    }
                                    isNextItemClicked = false;
                                }
                            }
                        } else if (isNextNextItemClicked) {
                            String tagValue = view.getTag(view.getId()).toString();
                            tagValue = tagValue.substring((tagValue.indexOf("|")) + 1, tagValue.length());
                            //System.out.println("tagValue"+tagValue);
                            for (int i = 0; i < spotLightCategoriesDTO.getSpotLightChannelDTOList().get(0).getVideoInfoDTOList().size(); i++) {
                                if (spotLightCategoriesDTO.getSpotLightChannelDTOList().get(0).getVideoInfoDTOList().get(i).getThumb().equals(tagValue)) {
                                    //System.out.println("spotLightCategoriesDTO.getSpotLightChannelDTOList().get(0).getVideoInfoDTOList().get(position).getThumb()"+spotLightCategoriesDTO.getSpotLightChannelDTOList().get(0).getVideoInfoDTOList().get(i+1).getThumb());
                                    if (CommonUtils.isActuallyTabletDevice((Activity) mContext)) {
                                        iVideoOrChannelItemPagerAdapter.videoItemClickHandler(categoryName, spotLightCategoriesDTO.getSpotLightChannelDTOList().get(0).getVideoInfoDTOList().get(i).getThumb(), position);
                                    } else {
                                        iVideoOrChannelItemPagerAdapter.videoItemClickHandler(categoryName, spotLightCategoriesDTO.getSpotLightChannelDTOList().get(0).getVideoInfoDTOList().get(i + 2).getThumb(), position);
                                    }
                                    isNextItemClicked = false;
                                }
                            }
                        } else if (isNextItemClicked) {
                            String tagValue = view.getTag(view.getId()).toString();
                            tagValue = tagValue.substring((tagValue.indexOf("|")) + 1, tagValue.length());
                            //System.out.println("tagValue"+tagValue);
                            for (int i = 0; i < spotLightCategoriesDTO.getSpotLightChannelDTOList().get(0).getVideoInfoDTOList().size(); i++) {
                                try {
                                    if (spotLightCategoriesDTO.getSpotLightChannelDTOList().get(0).getVideoInfoDTOList().get(i).getThumb() != null &&
                                            tagValue != null &&
                                            spotLightCategoriesDTO.getSpotLightChannelDTOList().get(0).getVideoInfoDTOList().get(i).getThumb().equals(tagValue)) {
                                        //System.out.println("spotLightCategoriesDTO.getSpotLightChannelDTOList().get(0).getVideoInfoDTOList().get(position).getThumb()"+spotLightCategoriesDTO.getSpotLightChannelDTOList().get(0).getVideoInfoDTOList().get(i+1).getThumb());
                                        if (CommonUtils.isActuallyTabletDevice((Activity) mContext)) {
                                            iVideoOrChannelItemPagerAdapter.videoItemClickHandler(categoryName, spotLightCategoriesDTO.getSpotLightChannelDTOList().get(0).getVideoInfoDTOList().get(i).getThumb(), position);
                                        } else {
                                            iVideoOrChannelItemPagerAdapter.videoItemClickHandler(categoryName, spotLightCategoriesDTO.getSpotLightChannelDTOList().get(0).getVideoInfoDTOList().get(i + 1).getThumb(), position);
                                        }
                                        isNextItemClicked = false;
                                    }
                                } catch(Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            String tagString = view.getTag(view.getId()).toString();
                            String desc = tagString.substring(0, tagString.indexOf("|"));
                            String clickedVideoID = tagString.substring((tagString.indexOf("|") + 1), tagString.length());
                            iVideoOrChannelItemPagerAdapter.videoItemClickHandler(desc, clickedVideoID, position);
                        }
                    }
                });
            } catch(Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                imageView.setLayoutParams(new RelativeLayout.LayoutParams(imageWidth, imageHeight));

                String tagString = spotLightCategoriesDTO.getCategoryName() + "|" + spotLightCategoriesDTO.getVideoInfoDTOList().get(position).getThumb();
                imageView.setTag(imageView.getId(), tagString);

                String episodeNameWithSeriesTitle = "";
                if (spotLightCategoriesDTO.getVideoInfoDTOList().get(position).getSeriesTitle() != null && spotLightCategoriesDTO.getVideoInfoDTOList().get(position).getSeriesTitle().length() > 0) {
                    episodeNameWithSeriesTitle = spotLightCategoriesDTO.getVideoInfoDTOList().get(position).getSeriesTitle();
                }
                if (episodeNameWithSeriesTitle.length() > 0)
                    episodeNameWithSeriesTitle = episodeNameWithSeriesTitle + " - " + spotLightCategoriesDTO.getVideoInfoDTOList().get(position).getVideoTitle();
                else
                    episodeNameWithSeriesTitle = spotLightCategoriesDTO.getVideoInfoDTOList().get(position).getVideoTitle();

                if (spotLightCategoriesDTO.getVideoInfoDTOList().get(position).getVideoPausedPoint() > 0 &&
                        spotLightCategoriesDTO.getVideoInfoDTOList().get(position).getVideoDuration() > 0) {
                    int videoDuration = spotLightCategoriesDTO.getVideoInfoDTOList().get(position).getVideoDuration();
                    int videoPausedPointPercentage = ((spotLightCategoriesDTO.getVideoInfoDTOList().get(position).getVideoPausedPoint() * 100) / videoDuration);
                    itemView.findViewById(R.id.parentProgressContanier).setBackgroundColor(Color.parseColor("#33ffffff"));
                    //ABS
                    //itemView.findViewById(R.id.childProgressContainer).setBackgroundColor(Color.parseColor("#F91686"));
                    //Nosey
                    itemView.findViewById(R.id.childProgressContainer).setBackgroundColor(Color.parseColor("#6dec68"));

                    itemView.findViewById(R.id.childProgressContainer).setBackgroundColor(childProgressColour);

                    int heightForProgressBar = 0;
                    if (ApplicationConstants.TEXT_INPUT_HEIGHT > 0)
                        heightForProgressBar = ApplicationConstants.TEXT_INPUT_HEIGHT / 6;
                    else if (ApplicationConstants.TOP_PADDING_FOR_MAIN_ACTION_BAR > 0)
                        heightForProgressBar = ApplicationConstants.TOP_PADDING_FOR_MAIN_ACTION_BAR / 8;
                    itemView.findViewById(R.id.parentProgressContanier).setLayoutParams(new RelativeLayout.LayoutParams(imageWidth, heightForProgressBar));
                    ((RelativeLayout.LayoutParams) itemView.findViewById(R.id.parentProgressContanier).getLayoutParams()).addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                    int widthOfProgress = ((imageWidth * videoPausedPointPercentage) / 100);
                    itemView.findViewById(R.id.childProgressContainer).setLayoutParams(new LinearLayout.LayoutParams(widthOfProgress, heightForProgressBar));

                    if (videoPausedPointPercentage > 95
                            && !spotLightCategoriesDTO.getCategorySlug().equals(ApplicationConstants.CONTINUE_WATCHING_SLUG)
                            && !spotLightCategoriesDTO.getCategorySlug().equals(ApplicationConstants.WATCH_AGAIN_SLUG)) {
                        itemView.setAlpha(0.35f);
                    }
                }

                videoTitleTV.setText(spotLightCategoriesDTO.getVideoInfoDTOList().get(position).getVideoTitle());

                if(showActor)
                    actorsTV.setText(spotLightCategoriesDTO.getVideoInfoDTOList().get(position).getSeriesTitle());
                //actorsTV.setVisibility(View.GONE);
                //actorsTV.setText(videoInfoDTOArrayList.get(position).getCasting());

                String imageURL = "https://images.dotstudiopro.com/" + spotLightCategoriesDTO.getVideoInfoDTOList().get(position).getThumb() + "/" + imageWidth + "/" + imageHeight;
                Uri uri = Uri.parse(imageURL);
                imageView.setImageURI(uri);
                //Picasso.with(mContext).load(uri).into(imageView);
                //System.out.println("IMAGE_PATH:-"+imageURL);

                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (isNextNextNextNextItemClicked) {
                            String tagValue = view.getTag(view.getId()).toString();
                            tagValue = tagValue.substring((tagValue.indexOf("|")) + 1, tagValue.length());
                            //System.out.println("tagValue"+tagValue);
                            for (int i = 0; i < videoInfoDTOArrayList.size(); i++) {
                                if (videoInfoDTOArrayList.get(i).getThumb().equals(tagValue)) {
                                    if (CommonUtils.isActuallyTabletDevice((Activity) mContext)) {
                                        iVideoOrChannelItemPagerAdapter.videoItemClickHandler(categoryName, videoInfoDTOArrayList.get(i).getThumb(), position);
                                    } else {
                                        if (videoInfoDTOArrayList.size() > (i + 4))
                                            iVideoOrChannelItemPagerAdapter.videoItemClickHandler(categoryName, videoInfoDTOArrayList.get(i + 4).getThumb(), position + 1);
                                        else if (videoInfoDTOArrayList.size() > (i + 3))
                                            iVideoOrChannelItemPagerAdapter.videoItemClickHandler(categoryName, videoInfoDTOArrayList.get(i + 3).getThumb(), position + 1);
                                        else if (videoInfoDTOArrayList.size() > (i + 2))
                                            iVideoOrChannelItemPagerAdapter.videoItemClickHandler(categoryName, videoInfoDTOArrayList.get(i + 2).getThumb(), position + 1);
                                        else if (videoInfoDTOArrayList.size() > (i + 1))
                                            iVideoOrChannelItemPagerAdapter.videoItemClickHandler(categoryName, videoInfoDTOArrayList.get(i + 1).getThumb(), position + 1);
                                        else
                                            iVideoOrChannelItemPagerAdapter.videoItemClickHandler(categoryName, videoInfoDTOArrayList.get(i).getThumb(), position);
                                    }
                                    isNextItemClicked = false;
                                }
                            }
                        } else if (isNextNextNextItemClicked) {
                            String tagValue = view.getTag(view.getId()).toString();
                            tagValue = tagValue.substring((tagValue.indexOf("|")) + 1, tagValue.length());
                            //System.out.println("tagValue"+tagValue);
                            for (int i = 0; i < videoInfoDTOArrayList.size(); i++) {
                                if (videoInfoDTOArrayList.get(i).getThumb().equals(tagValue)) {
                                    if (CommonUtils.isActuallyTabletDevice((Activity) mContext)) {
                                        iVideoOrChannelItemPagerAdapter.videoItemClickHandler(categoryName, videoInfoDTOArrayList.get(i).getThumb(), position);
                                    } else {
                                        if (videoInfoDTOArrayList.size() > (i + 3))
                                            iVideoOrChannelItemPagerAdapter.videoItemClickHandler(categoryName, videoInfoDTOArrayList.get(i + 3).getThumb(), position + 1);
                                        else if (videoInfoDTOArrayList.size() > (i + 2))
                                            iVideoOrChannelItemPagerAdapter.videoItemClickHandler(categoryName, videoInfoDTOArrayList.get(i + 2).getThumb(), position + 1);
                                        else if (videoInfoDTOArrayList.size() > (i + 1))
                                            iVideoOrChannelItemPagerAdapter.videoItemClickHandler(categoryName, videoInfoDTOArrayList.get(i + 1).getThumb(), position + 1);
                                        else
                                            iVideoOrChannelItemPagerAdapter.videoItemClickHandler(categoryName, videoInfoDTOArrayList.get(i).getThumb(), position);
                                    }
                                    isNextItemClicked = false;
                                }
                            }
                        } else if (isNextNextItemClicked) {
                            String tagValue = view.getTag(view.getId()).toString();
                            tagValue = tagValue.substring((tagValue.indexOf("|")) + 1, tagValue.length());
                            //System.out.println("tagValue"+tagValue);
                            for (int i = 0; i < videoInfoDTOArrayList.size(); i++) {
                                if (videoInfoDTOArrayList.get(i).getThumb().equals(tagValue)) {
                                    if (CommonUtils.isActuallyTabletDevice((Activity) mContext)) {
                                        iVideoOrChannelItemPagerAdapter.videoItemClickHandler(categoryName, videoInfoDTOArrayList.get(i).getThumb(), position);
                                    } else {
                                        if (videoInfoDTOArrayList.size() > (i + 2))
                                            iVideoOrChannelItemPagerAdapter.videoItemClickHandler(categoryName, videoInfoDTOArrayList.get(i + 2).getThumb(), position + 1);
                                        else if (videoInfoDTOArrayList.size() > (i + 1))
                                            iVideoOrChannelItemPagerAdapter.videoItemClickHandler(categoryName, videoInfoDTOArrayList.get(i + 1).getThumb(), position + 1);
                                        else
                                            iVideoOrChannelItemPagerAdapter.videoItemClickHandler(categoryName, videoInfoDTOArrayList.get(i).getThumb(), position);
                                    }
                                    isNextItemClicked = false;
                                }
                            }
                        } else if (isNextItemClicked) {
                            String tagValue = view.getTag(view.getId()).toString();
                            tagValue = tagValue.substring((tagValue.indexOf("|")) + 1, tagValue.length());
                            //System.out.println("tagValue"+tagValue);
                            for (int i = 0; i < videoInfoDTOArrayList.size(); i++) {
                                if (videoInfoDTOArrayList.get(i).getThumb().equals(tagValue)) {
                                    if (CommonUtils.isActuallyTabletDevice((Activity) mContext)) {
                                        iVideoOrChannelItemPagerAdapter.videoItemClickHandler(categoryName, videoInfoDTOArrayList.get(i).getThumb(), position);
                                    } else {
                                        if (videoInfoDTOArrayList.size() > (i + 1))
                                            iVideoOrChannelItemPagerAdapter.videoItemClickHandler(categoryName, videoInfoDTOArrayList.get(i + 1).getThumb(), position);
                                        else
                                            iVideoOrChannelItemPagerAdapter.videoItemClickHandler(categoryName, videoInfoDTOArrayList.get(i).getThumb(), position);
                                    }
                                    isNextItemClicked = false;
                                }
                            }
                        } else {
                            String tagString = view.getTag(view.getId()).toString();
                            String desc = tagString.substring(0, tagString.indexOf("|"));
                            String clickedVideoID = tagString.substring((tagString.indexOf("|") + 1), tagString.length());
                            iVideoOrChannelItemPagerAdapter.videoItemClickHandler(desc, clickedVideoID, position);
                        }
                    }
                });
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    @Override
    public int getCount() {
        if(spotLightCategoriesDTO != null &&
                spotLightCategoriesDTO.getSpotLightChannelDTOList().size() > 0 &&
                !spotLightCategoriesDTO.isParentChannel()) {
            return spotLightCategoriesDTO.getSpotLightChannelDTOList().size();
        } else if(spotLightCategoriesDTO != null &&
                spotLightCategoriesDTO.getSpotLightChannelDTOList().size() > 0 &&
                spotLightCategoriesDTO.isParentChannel()) {
            return spotLightCategoriesDTO.getSpotLightChannelDTOList().get(0).getVideoInfoDTOList().size();
        } else {
            return videoInfoDTOArrayList.size();
        }
    }

    public int getTitleColour() {
        return titleColour;
    }

    public void setTitleColour(int titleColour) {
        this.titleColour = titleColour;
    }

    public int getActorsColour() {
        return actorsColour;
    }

    public void setActorsColour(int actorsColour) {
        this.actorsColour = actorsColour;
    }

    public int getChildProgressColour() {
        return childProgressColour;
    }

    public void setChildProgressColour(int childProgressColour) {
        this.childProgressColour = childProgressColour;
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

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == object);
    }

    public interface IVideoOrChannelItemPagerAdapter {
        void videoItemClickHandler(String desc, String clickedVideoID, int position);
        void channelItemClickHandler(String tagString);
    }
}
