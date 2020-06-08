package com.dotstudioz.dotstudioPRO.corelibrary.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dotstudioz.dotstudioPRO.corelibrary.R;
import com.dotstudioz.dotstudioPRO.services.constants.ApplicationConstants;
import com.dotstudioz.dotstudioPRO.corelibrary.constants.FontsConstants;
import com.dotstudioz.dotstudioPRO.models.dto.ChannelMyListDTO;
import com.dotstudioz.dotstudioPRO.models.dto.SpotLightCategoriesDTO;
import com.dotstudioz.dotstudioPRO.models.dto.VideoInfoDTO;
import com.dotstudioz.dotstudioPRO.corelibrary.util.CommonUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;

import java.util.ArrayList;
import java.util.List;

public class CategoriesPageRecyclerViewComponentAdapter_V1 extends
        RecyclerView.Adapter<CategoriesPageRecyclerViewComponentAdapter_V1.ViewHolder> {
    private static final String TAG = "CatPageRecVCompAdapV1";

    private Context mcontext;
    public int totalWidth = 0;
    public List<VideoInfoDTO> videoInfoDTOArrayList;
    public String categoryName;
    public SpotLightCategoriesDTO spotLightCategoriesDTO;
    public boolean isNextItemClicked = false;
    public boolean isNextNextItemClicked = false;
    public boolean isNextNextNextItemClicked = false;
    public boolean isNextNextNextNextItemClicked = false;
    public String channelIdPlusCategorySlugFromMultiViewPager = "";
    public static int indexOfChannelClicked = 0;
    public int imageHeight;
    public int imageWidth;
    public int channelPosterWidth;
    public int channelPosterHeight;
    private int titleColour = Color.parseColor("#eeeeee");
    private int actorsColour = Color.parseColor("#eeeeee");
    public boolean showActor = false;
    public boolean showPoster = false;
    public boolean showSpotLightImage = false;
    public boolean showParentChannelOnly = false;
    private int childProgressColour = Color.parseColor("#F3AB13");
    public boolean isMyListEnabled = false;
    public boolean isSubscriptionEnabled = false;
    public boolean isUserSubscribed = false;


    public float videoTitleTVFontSize;

    private boolean isCustomFontEnabledForVideoTitle = false;
    private Typeface customFontForVideoTitle;

    public ArrayList<ChannelMyListDTO> channelMyListDTOArrayList = new ArrayList<>();

    public ICategoriesPageRecyclerViewComponentAdapter iCategoriesPageRecyclerViewComponentAdapter;
    public interface ICategoriesPageRecyclerViewComponentAdapter {
        void videoItemClickHandler(String desc, String clickedVideoID, int position);
        void channelItemClickHandler(String tagString);
        void childCategoryItemClickHandler(String tagString);
        void childChannelItemClickHandler(String tagString);
        void clickHandlerForChannelsImageInCategories(String tagString);
        void addToMyList(boolean flag, String channelID, String title, String slug, String poster, String spotlightImage);
        void deleteButtonClicked(String channelID);
    }

    public CategoriesPageRecyclerViewComponentAdapter_V1(Context mcontext) {
        this.mcontext = mcontext;
        iCategoriesPageRecyclerViewComponentAdapter = (ICategoriesPageRecyclerViewComponentAdapter) mcontext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, final int position) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item_heart_frame_layout_view_v1, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: Images Set");

        holder.fl.setLayoutParams(new LinearLayout.LayoutParams((imageWidth + 20), (imageHeight + 5)));

        holder.videoTitleTV.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        holder.videoTitleTV.setSingleLine(true);
        holder.videoTitleTV.setPadding(0, 0, 20, 5);
        holder.videoTitleTV.setEllipsize(TextUtils.TruncateAt.END);
        holder.videoTitleTV.setTextSize(videoTitleTVFontSize);
        if (isCustomFontEnabledForVideoTitle)
            holder.videoTitleTV.setTypeface(customFontForVideoTitle);
        else
            holder.videoTitleTV.setTypeface(FontsConstants.tfRegular);
        holder.videoTitleTV.setGravity(Gravity.LEFT);
        //holder.videoTitleTV.setShadowLayer(2f, -1, 1, Color.BLACK);
        holder.videoTitleTV.setTextColor(Color.WHITE);
        holder.videoTitleTV.setTextColor(titleColour);

        holder.actorsTV.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        holder.actorsTV.setSingleLine(true);
        holder.actorsTV.setPadding(0, 0, 20, 10);
        holder.actorsTV.setEllipsize(TextUtils.TruncateAt.END);
        holder.actorsTV.setTextSize(12f);
        if (isCustomFontEnabledForVideoTitle)
            holder.actorsTV.setTypeface(customFontForVideoTitle);
        else
            holder.actorsTV.setTypeface(FontsConstants.tfRegular);
        holder.actorsTV.setGravity(Gravity.LEFT);
        //holder.actorsTV.setShadowLayer(2f, -1, 1, Color.BLACK);
        //holder.actorsTV.setTextColor(Color.parseColor("#6dec68"));
        holder.actorsTV.setTextColor(actorsColour);

        final SimpleDraweeView imageView = holder.siv;

        if(spotLightCategoriesDTO.getChildrenSpotLightCategoriesDTOList().size() > 0) {
            //this condition was introduced in case of SpotNetwork, where there were nested categories
            try {
                if(channelPosterWidth > 0 && channelPosterHeight > 0)
                    holder.fl.setLayoutParams(new LinearLayout.LayoutParams((channelPosterWidth+20), (channelPosterHeight)));
                /*else
                    fl.setLayoutParams(new LinearLayout.LayoutParams((imageWidth+20), (imageHeight)));*/

                if(channelPosterWidth > 0 && channelPosterHeight > 0)
                    imageView.setLayoutParams(new RelativeLayout.LayoutParams(channelPosterWidth, channelPosterHeight));
                else
                    imageView.setLayoutParams(new RelativeLayout.LayoutParams(imageWidth, imageHeight));


                String channelIdPlusCategorySlug = spotLightCategoriesDTO.getCategoryName() + "|" + spotLightCategoriesDTO.getChildrenSpotLightCategoriesDTOList().get(position).getCategoryName();

                //if(spotLightCategoriesDTO.getChildrenSpotLightCategoriesDTOList().get(position).getChildrenSpotLightCategoriesDTOList().size() > 0) {
                imageView.setTag(imageView.getId(), channelIdPlusCategorySlug);
                /*} else {
                    imageView.setTag(imageView.getId(), spotLightCategoriesDTO.getChildrenSpotLightCategoriesDTOList().get(position).getCategoryId() + "|" + spotLightCategoriesDTO.getChildrenSpotLightCategoriesDTOList().get(position).getCategorySlug());
                }*/

                //if(spotLightCategoriesDTO.getChildrenSpotLightCategoriesDTOList().get(position).getChildrenSpotLightCategoriesDTOList().size() > 0) {
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                            /*if(indexOfChannelClicked == 2) {
                                iVideoOrChannelItemPagerAdapter.childCategoryItemClickHandler(channelIdPlusCategorySlugFromMultiViewPager);
                            } else {*/
                        iCategoriesPageRecyclerViewComponentAdapter.childCategoryItemClickHandler(view.getTag(view.getId()).toString());
                        //}
                    }
                });
                /*} else {
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            iVideoOrChannelItemPagerAdapter.childChannelItemClickHandler(view.getTag(view.getId()).toString());
                        }
                    });
                }*/

                String imageURL = "";
                imageURL = spotLightCategoriesDTO.getChildrenSpotLightCategoriesDTOList().get(position).getPoster();

                Uri uri = Uri.parse(imageURL);
                imageView.setImageURI(uri);
                //Picasso.with(mContext).load(uri).into(imageView);
                //System.out.println("IMAGE_PATH:-"+imageURL);
            } catch(Exception e) {
                e.printStackTrace();
            }

        } else if (spotLightCategoriesDTO.getSpotLightChannelDTOList().size() > 0 &&
                spotLightCategoriesDTO.getVideoInfoDTOList().size() == 0 &&
                !spotLightCategoriesDTO.isParentChannel()) {

            imageView.setLayoutParams(new RelativeLayout.LayoutParams(imageWidth, imageHeight));
            holder.fl.setLayoutParams(new LinearLayout.LayoutParams(imageWidth, imageHeight));
            holder.flParentLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(imageWidth+((totalWidth*2)/100), imageHeight+((totalWidth*2)/100)));

            //====================================HEART ICON - START==================================================
            if(isMyListEnabled) {
                boolean flagForHeart = false;
                try {
                    for (int i = 0; i < channelMyListDTOArrayList.size(); i++) {
                        if (!flagForHeart && spotLightCategoriesDTO.getSpotLightChannelDTOList().get(position).getId().equalsIgnoreCase(channelMyListDTOArrayList.get(i).getId())) {
                            flagForHeart = true;
                        }
                        if (flagForHeart)
                            break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    if (flagForHeart)
                        ((ImageView) holder.heartButton).setImageDrawable(new IconDrawable(mcontext, FontAwesomeIcons.fa_heart).color(Color.parseColor("#D03B8F")));
                    else
                        ((ImageView) holder.heartButton).setImageDrawable(new IconDrawable(mcontext, FontAwesomeIcons.fa_heart_o).color(Color.parseColor("#ffffff")));
                } catch (Exception e) {
                    e.printStackTrace();
                }

                holder.heartButton.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                            boolean flagForHeart = false;
                            try {
                                for (int i = 0; i < channelMyListDTOArrayList.size(); i++) {
                                    if (!flagForHeart && spotLightCategoriesDTO.getSpotLightChannelDTOList().get(position).getId().equalsIgnoreCase(channelMyListDTOArrayList.get(i).getId())) {
                                        flagForHeart = true;
                                    }
                                    if (flagForHeart)
                                        break;
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            if (flagForHeart) {
                                iCategoriesPageRecyclerViewComponentAdapter.deleteButtonClicked(spotLightCategoriesDTO.getSpotLightChannelDTOList().get(position).getId());
                                ((ImageView) holder.heartButton).setImageDrawable(new IconDrawable(mcontext, FontAwesomeIcons.fa_heart_o).color(Color.parseColor("#FFFFFF")));
                            } else {
                                iCategoriesPageRecyclerViewComponentAdapter.addToMyList(true,
                                        spotLightCategoriesDTO.getSpotLightChannelDTOList().get(position).getId(),
                                        spotLightCategoriesDTO.getSpotLightChannelDTOList().get(position).getTitle(),
                                        spotLightCategoriesDTO.getSpotLightChannelDTOList().get(position).getSlug(),
                                        spotLightCategoriesDTO.getSpotLightChannelDTOList().get(position).getPoster(),
                                        spotLightCategoriesDTO.getSpotLightChannelDTOList().get(position).getSpotlightImage());
                                ((ImageView) holder.heartButton).setImageDrawable(new IconDrawable(mcontext, FontAwesomeIcons.fa_heart).color(Color.parseColor("#D03B8F")));
                                //Toast.makeText(mcontext, "Heart Clicked", Toast.LENGTH_SHORT).show();
                            }
                        }
                        return true;
                    }
                });
            }
            //====================================HEART ICON  -  END==================================================

            if(isSubscriptionEnabled) {
                //code to draw a lock on the thumbnail in case this channel is part of subscription
                try {
                    if(spotLightCategoriesDTO.getSpotLightChannelDTOList().get(position).isProduct() && !isUserSubscribed) {
                        ((ImageView) holder.lockButton).setImageDrawable(new IconDrawable(mcontext, FontAwesomeIcons.fa_lock).color(Color.parseColor("#ffffff")));
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }

            //String channelIdPlusCategorySlug = spotLightCategoriesDTO.getSpotLightChannelDTOList().get(position).getId() + "|" + spotLightCategoriesDTO.getCategorySlug() + "|" + position;
            String channelIdPlusCategorySlug = spotLightCategoriesDTO.getSpotLightChannelDTOList().get(position).getId() + "|" + spotLightCategoriesDTO.getCategorySlug();
            //System.out.println("channelIdPlusCategorySlug==>"+channelIdPlusCategorySlug);
            imageView.setTag(imageView.getId(), channelIdPlusCategorySlug);

            if (spotLightCategoriesDTO.getCategorySlug().equals("hero-showcase"))
                holder.videoTitleTV.setText(spotLightCategoriesDTO.getSpotLightChannelDTOList().get(position).getTitle());

            if ((spotLightCategoriesDTO.getSpotLightChannelDTOList().get(position).getVideo() != null &&
                    spotLightCategoriesDTO.getSpotLightChannelDTOList().get(position).getVideo().length() > 0) ||
                    (spotLightCategoriesDTO.getSpotLightChannelDTOList().get(position).getPlaylist() != null &&
                            spotLightCategoriesDTO.getSpotLightChannelDTOList().get(position).getPlaylist().size() > 0)) {
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        iCategoriesPageRecyclerViewComponentAdapter.channelItemClickHandler(view.getTag(view.getId()).toString());
                    }
                });
            } else {
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        iCategoriesPageRecyclerViewComponentAdapter.channelItemClickHandler(view.getTag(view.getId()).toString());
                    }
                });
            }

            String imageURL = "";
            if (spotLightCategoriesDTO.getCategorySlug().equals("hero-showcase"))
                imageURL = spotLightCategoriesDTO.getSpotLightChannelDTOList().get(position).getImage() + "/" + imageWidth + "/" + imageHeight;
            else {
                if (channelPosterWidth > 0 && channelPosterHeight > 0) {
                    boolean flag = false;
                    if(spotLightCategoriesDTO.getCustomFieldDTOArrayList().size() > 0) {
                        for(int i = 0; i < spotLightCategoriesDTO.getCustomFieldDTOArrayList().size(); i++) {
                            if (spotLightCategoriesDTO.getCustomFieldDTOArrayList().get(i).getCustomFieldName().equalsIgnoreCase("aspect_ratio")) ; {
                                if(spotLightCategoriesDTO.getCustomFieldDTOArrayList().get(i).getCustomFieldValue().equalsIgnoreCase("2:3")) {
                                    flag = true;
                                }
                            }
                        }
                    }

                    if(flag) {
                        imageURL = spotLightCategoriesDTO.getSpotLightChannelDTOList().get(position).getSpotlightImage() + "/" + channelPosterWidth + "/" + channelPosterHeight;
                        Log.d(TAG, "imageURL==>"+imageURL);
                        imageView.setLayoutParams(new RelativeLayout.LayoutParams(channelPosterWidth, channelPosterHeight));
                        holder.fl.setLayoutParams(new LinearLayout.LayoutParams(channelPosterWidth, channelPosterHeight));
                        holder.flParentLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(channelPosterWidth+((totalWidth*2)/100), channelPosterHeight+((totalWidth*2)/100)));
                    } else {
                        imageURL = spotLightCategoriesDTO.getSpotLightChannelDTOList().get(position).getSpotlightImage() + "/" + imageWidth + "/" + imageHeight;
                        Log.d(TAG, "imageURL==>"+imageURL);
                        imageView.setLayoutParams(new RelativeLayout.LayoutParams(imageWidth, imageHeight));
                        holder.fl.setLayoutParams(new LinearLayout.LayoutParams(imageWidth, imageHeight));
                        holder.flParentLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(imageWidth+((totalWidth*2)/100), imageHeight+((totalWidth*2)/100)));
                    }
                } else {
                    boolean flag = false;
                    if(spotLightCategoriesDTO.getCustomFieldDTOArrayList().size() > 0) {
                        for(int i = 0; i < spotLightCategoriesDTO.getCustomFieldDTOArrayList().size(); i++) {
                            if (spotLightCategoriesDTO.getCustomFieldDTOArrayList().get(i).getCustomFieldName().equalsIgnoreCase("aspect_ratio")) ; {
                                if(spotLightCategoriesDTO.getCustomFieldDTOArrayList().get(i).getCustomFieldValue().equalsIgnoreCase("2:3")) {
                                    flag = true;
                                }
                            }
                        }
                    }

                    if(flag) {
                        imageURL = spotLightCategoriesDTO.getSpotLightChannelDTOList().get(position).getSpotlightImage() + "/" + channelPosterWidth + "/" + channelPosterHeight;
                        Log.d(TAG, "imageURL==>"+imageURL);
                        imageView.setLayoutParams(new RelativeLayout.LayoutParams(channelPosterWidth, channelPosterHeight));
                        holder.fl.setLayoutParams(new LinearLayout.LayoutParams(channelPosterWidth, channelPosterHeight));
                        holder.flParentLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(channelPosterWidth+((totalWidth*2)/100), channelPosterHeight+((totalWidth*2)/100)));
                    } else {
                        imageURL = spotLightCategoriesDTO.getSpotLightChannelDTOList().get(position).getPoster() + "/" + imageWidth + "/" + imageHeight;
                        Log.d(TAG, "imageURL2==>"+imageURL);
                        imageView.setLayoutParams(new RelativeLayout.LayoutParams(imageWidth, imageHeight));
                        holder.fl.setLayoutParams(new LinearLayout.LayoutParams(imageWidth, imageHeight));
                        holder.flParentLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(imageWidth+((totalWidth*2)/100), imageHeight+((totalWidth*2)/100)));
                    }
                }
            }
            Uri uri = Uri.parse(imageURL);
            imageView.setImageURI(uri);
            //Picasso.with(mContext).load(uri).into(imageView);
            //System.out.println("IMAGE_PATH:-"+imageURL);
        } else if(showParentChannelOnly &&
                spotLightCategoriesDTO.getSpotLightChannelDTOList().size() > 0 &&
                spotLightCategoriesDTO.getVideoInfoDTOList().size() == 0 &&
                spotLightCategoriesDTO.isParentChannel()) {
            try {
                if(channelPosterWidth > 0 && channelPosterHeight > 0)
                    imageView.setLayoutParams(new RelativeLayout.LayoutParams(channelPosterWidth, channelPosterHeight));
                else
                    imageView.setLayoutParams(new RelativeLayout.LayoutParams(imageWidth, imageHeight));
                //imageView.setLayoutParams(new RelativeLayout.LayoutParams(imageWidth, imageHeight));

                final String tagString = spotLightCategoriesDTO.getCategorySlug()+"|"+spotLightCategoriesDTO.getParentCategoryName();
                imageView.setTag(imageView.getId(), tagString);

                //code to draw a lock on the thumbnail in case this channel is part of subscription
                /*try {
                    if(spotLightCategoriesDTO.getSpotLightChannelDTOList().get(position).isProduct()) {
                        ((ImageView) itemView.findViewById(R.id.lockButton)).setImageDrawable(new IconDrawable(mContext, FontAwesomeIcons.fa_lock).color(Color.parseColor("#ffffff")));
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                }*/

                if (spotLightCategoriesDTO.getCategorySlug().equals("hero-showcase"))
                    holder.videoTitleTV.setText(spotLightCategoriesDTO.getSpotLightChannelDTOList().get(0).getVideoInfoDTOList().get(position).getVideoTitle());
                holder.actorsTV.setVisibility(View.GONE);
                //actorsTV.setText(videoInfoDTOArrayList.get(position).getCasting());

                String imageURL = spotLightCategoriesDTO.getPoster() + "/" + imageWidth + "/" + imageHeight;
                Uri uri = Uri.parse(imageURL);
                imageView.setImageURI(uri);
                //Picasso.with(mContext).load(uri).into(imageView);
                //System.out.println("IMAGE_PATH:-"+imageURL);

                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            String tagValue = view.getTag(view.getId()).toString();
                            iCategoriesPageRecyclerViewComponentAdapter.clickHandlerForChannelsImageInCategories(tagValue);
                        } catch(Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            } catch(Exception e) {
                e.printStackTrace();
            }
        } else if (spotLightCategoriesDTO.getSpotLightChannelDTOList().size() > 0 &&
                spotLightCategoriesDTO.getVideoInfoDTOList().size() == 0 &&
                spotLightCategoriesDTO.isParentChannel()) {
            imageView.setLayoutParams(new RelativeLayout.LayoutParams(imageWidth, imageHeight));

            String tagString = spotLightCategoriesDTO.getCategoryName() + "|" + spotLightCategoriesDTO.getSpotLightChannelDTOList().get(0).getVideoInfoDTOList().get(position).getThumb();
            imageView.setTag(imageView.getId(), tagString);

            if (spotLightCategoriesDTO.getCategorySlug().equals("hero-showcase"))
                holder.videoTitleTV.setText(spotLightCategoriesDTO.getSpotLightChannelDTOList().get(0).getVideoInfoDTOList().get(position).getVideoTitle());
            holder.actorsTV.setVisibility(View.GONE);
            //actorsTV.setText(videoInfoDTOArrayList.get(position).getCasting());

            String imageURL = "https://images.dotstudiopro.com/" + spotLightCategoriesDTO.getSpotLightChannelDTOList().get(0).getVideoInfoDTOList().get(position).getThumb() + "/" + imageWidth + "/" + imageHeight;
            Uri uri = Uri.parse(imageURL);
            imageView.setImageURI(uri);
            //Picasso.with(mContext).load(uri).into(imageView);
            System.out.println("IMAGE_PATH123:-" + imageURL);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (isNextNextItemClicked) {
                        String tagValue = view.getTag(view.getId()).toString();
                        tagValue = tagValue.substring((tagValue.indexOf("|")) + 1, tagValue.length());
                        //System.out.println("tagValue"+tagValue);
                        for (int i = 0; i < spotLightCategoriesDTO.getSpotLightChannelDTOList().get(0).getVideoInfoDTOList().size(); i++) {
                            if (spotLightCategoriesDTO.getSpotLightChannelDTOList().get(0).getVideoInfoDTOList().get(i).getThumb().equals(tagValue)) {
                                //System.out.println("spotLightCategoriesDTO.getSpotLightChannelDTOList().get(0).getVideoInfoDTOList().get(position).getThumb()"+spotLightCategoriesDTO.getSpotLightChannelDTOList().get(0).getVideoInfoDTOList().get(i+1).getThumb());
                                if (CommonUtils.isActuallyTabletDevice((Activity) mcontext)) {
                                    iCategoriesPageRecyclerViewComponentAdapter.videoItemClickHandler(categoryName, spotLightCategoriesDTO.getSpotLightChannelDTOList().get(0).getVideoInfoDTOList().get(i).getThumb(), position);
                                } else {
                                    iCategoriesPageRecyclerViewComponentAdapter.videoItemClickHandler(categoryName, spotLightCategoriesDTO.getSpotLightChannelDTOList().get(0).getVideoInfoDTOList().get(i + 2).getThumb(), position);
                                }
                                isNextItemClicked = false;
                            }
                        }
                    } else if (isNextItemClicked) {
                        String tagValue = view.getTag(view.getId()).toString();
                        tagValue = tagValue.substring((tagValue.indexOf("|")) + 1, tagValue.length());
                        //System.out.println("tagValue"+tagValue);
                        for (int i = 0; i < spotLightCategoriesDTO.getSpotLightChannelDTOList().get(0).getVideoInfoDTOList().size(); i++) {
                            if (spotLightCategoriesDTO.getSpotLightChannelDTOList().get(0).getVideoInfoDTOList().get(i).getThumb().equals(tagValue)) {
                                //System.out.println("spotLightCategoriesDTO.getSpotLightChannelDTOList().get(0).getVideoInfoDTOList().get(position).getThumb()"+spotLightCategoriesDTO.getSpotLightChannelDTOList().get(0).getVideoInfoDTOList().get(i+1).getThumb());
                                if (CommonUtils.isActuallyTabletDevice((Activity) mcontext)) {
                                    iCategoriesPageRecyclerViewComponentAdapter.videoItemClickHandler(categoryName, spotLightCategoriesDTO.getSpotLightChannelDTOList().get(0).getVideoInfoDTOList().get(i).getThumb(), position);
                                } else {
                                    iCategoriesPageRecyclerViewComponentAdapter.videoItemClickHandler(categoryName, spotLightCategoriesDTO.getSpotLightChannelDTOList().get(0).getVideoInfoDTOList().get(i + 1).getThumb(), position);
                                }
                                isNextItemClicked = false;
                            }
                        }
                    } else {
                        String tagString = view.getTag(view.getId()).toString();
                        String desc = tagString.substring(0, tagString.indexOf("|"));
                        String clickedVideoID = tagString.substring((tagString.indexOf("|") + 1), tagString.length());
                        iCategoriesPageRecyclerViewComponentAdapter.videoItemClickHandler(desc, clickedVideoID, position);
                    }
                }
            });
        } else {
            //this will render the video thumbnails
            //this could be the rails like continue_watching, last_watched
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
                holder.parentProgressContanier.setBackgroundColor(Color.parseColor("#33ffffff"));
                //ABS
                //itemView.findViewById(R.id.childProgressContainer).setBackgroundColor(Color.parseColor("#F91686"));
                //Celebrity Page
                holder.childProgressContainer.setBackgroundColor(Color.parseColor("#F3AB13"));

                holder.childProgressContainer.setBackgroundColor(childProgressColour);

                int heightForProgressBar = 0;
                if (ApplicationConstants.TEXT_INPUT_HEIGHT > 0)
                    heightForProgressBar = ApplicationConstants.TEXT_INPUT_HEIGHT / 6;
                else if (ApplicationConstants.TOP_PADDING_FOR_MAIN_ACTION_BAR > 0)
                    heightForProgressBar = ApplicationConstants.TOP_PADDING_FOR_MAIN_ACTION_BAR / 8;
                holder.parentProgressContanier.setLayoutParams(new RelativeLayout.LayoutParams(imageWidth, heightForProgressBar));
                ((RelativeLayout.LayoutParams) holder.parentProgressContanier.getLayoutParams()).addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                int widthOfProgress = ((imageWidth * videoPausedPointPercentage) / 100);
                holder.childProgressContainer.setLayoutParams(new LinearLayout.LayoutParams(widthOfProgress, heightForProgressBar));

                /*if (videoPausedPointPercentage > 95
                        && !spotLightCategoriesDTO.getCategorySlug().equals(ApplicationConstants.CONTINUE_WATCHING_SLUG)
                        && !spotLightCategoriesDTO.getCategorySlug().equals(ApplicationConstants.WATCH_AGAIN_SLUG)) {
                    itemView.setAlpha(0.35f);
                }*/
            }

            holder.videoTitleTV.setText(spotLightCategoriesDTO.getVideoInfoDTOList().get(position).getVideoTitle());
            if (showActor)
                holder.actorsTV.setText(spotLightCategoriesDTO.getVideoInfoDTOList().get(position).getSeriesTitle());
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
                    if (isNextNextItemClicked) {
                        String tagValue = view.getTag(view.getId()).toString();
                        tagValue = tagValue.substring((tagValue.indexOf("|")) + 1, tagValue.length());
                        //System.out.println("tagValue"+tagValue);
                        for (int i = 0; i < videoInfoDTOArrayList.size(); i++) {
                            if (videoInfoDTOArrayList.get(i).getThumb().equals(tagValue)) {
                                if (CommonUtils.isActuallyTabletDevice((Activity) mcontext)) {
                                    iCategoriesPageRecyclerViewComponentAdapter.videoItemClickHandler(categoryName, videoInfoDTOArrayList.get(i).getThumb(), position);
                                } else {
                                    if (videoInfoDTOArrayList.size() > (i + 2))
                                        iCategoriesPageRecyclerViewComponentAdapter.videoItemClickHandler(categoryName, videoInfoDTOArrayList.get(i + 2).getThumb(), position + 1);
                                    else if (videoInfoDTOArrayList.size() > (i + 1))
                                        iCategoriesPageRecyclerViewComponentAdapter.videoItemClickHandler(categoryName, videoInfoDTOArrayList.get(i + 1).getThumb(), position + 1);
                                    else
                                        iCategoriesPageRecyclerViewComponentAdapter.videoItemClickHandler(categoryName, videoInfoDTOArrayList.get(i).getThumb(), position);
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
                                if (CommonUtils.isActuallyTabletDevice((Activity) mcontext)) {
                                    iCategoriesPageRecyclerViewComponentAdapter.videoItemClickHandler(categoryName, videoInfoDTOArrayList.get(i).getThumb(), position);
                                } else {
                                    if (videoInfoDTOArrayList.size() > (i + 1))
                                        iCategoriesPageRecyclerViewComponentAdapter.videoItemClickHandler(categoryName, videoInfoDTOArrayList.get(i + 1).getThumb(), position);
                                    else
                                        iCategoriesPageRecyclerViewComponentAdapter.videoItemClickHandler(categoryName, videoInfoDTOArrayList.get(i).getThumb(), position);
                                }
                                isNextItemClicked = false;
                            }
                        }
                    } else {
                        String tagString = view.getTag(view.getId()).toString();
                        String desc = tagString.substring(0, tagString.indexOf("|"));
                        String clickedVideoID = tagString.substring((tagString.indexOf("|") + 1), tagString.length());
                        iCategoriesPageRecyclerViewComponentAdapter.videoItemClickHandler(desc, clickedVideoID, position);
                    }
                }
            });
        }
    }

    public int getTitleColour () {
        return titleColour;
    }

    public void setTitleColour ( int titleColour){
        this.titleColour = titleColour;
    }

    public int getActorsColour () {
        return actorsColour;
    }

    public void setActorsColour ( int actorColour){
        this.actorsColour = actorsColour;
    }

    public int getChildProgressColour () {
        return childProgressColour;
    }

    public void setChildProgressColour ( int childProgressColour){
        this.childProgressColour = childProgressColour;
    }

    public boolean isCustomFontEnabledForVideoTitle () {
        return isCustomFontEnabledForVideoTitle;
    }

    public void setCustomFontEnabledForVideoTitle ( boolean customFontEnabledForVideoTitle){
        isCustomFontEnabledForVideoTitle = customFontEnabledForVideoTitle;
    }

    public Typeface getCustomFontForVideoTitle () {
        return customFontForVideoTitle;
    }

    public void setCustomFontForVideoTitle (Typeface customFontForVideoTitle){
        this.customFontForVideoTitle = customFontForVideoTitle;
    }

    @Override
    public int getItemCount () {
        if(spotLightCategoriesDTO.getChildrenSpotLightCategoriesDTOList().size() > 0) {
            return spotLightCategoriesDTO.getChildrenSpotLightCategoriesDTOList().size();
        } else  if(spotLightCategoriesDTO != null &&
                spotLightCategoriesDTO.getSpotLightChannelDTOList().size() > 0 &&
                spotLightCategoriesDTO.getVideoInfoDTOList().size() == 0 &&
                !spotLightCategoriesDTO.isParentChannel()) {
            return spotLightCategoriesDTO.getSpotLightChannelDTOList().size();
        } else if(spotLightCategoriesDTO != null &&
                showParentChannelOnly &&
                spotLightCategoriesDTO.getSpotLightChannelDTOList().size() > 0 &&
                spotLightCategoriesDTO.getVideoInfoDTOList().size() == 0 &&
                spotLightCategoriesDTO.isParentChannel()) {
            return 1;
        }else if(spotLightCategoriesDTO != null &&
                spotLightCategoriesDTO.getSpotLightChannelDTOList().size() > 0 &&
                spotLightCategoriesDTO.getVideoInfoDTOList().size() == 0 &&
                spotLightCategoriesDTO.isParentChannel()) {
            return spotLightCategoriesDTO.getSpotLightChannelDTOList().get(0).getVideoInfoDTOList().size();
        } else {
            return videoInfoDTOArrayList.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout fl;
        TextView videoTitleTV;
        TextView actorsTV;
        SimpleDraweeView siv;
        LinearLayout parentProgressContanier;
        LinearLayout childProgressContainer;
        LinearLayout flParentLinearLayout;
        ImageView heartButton;
        ImageView lockButton;


        public ViewHolder( View itemView) {
            super(itemView);
            fl = itemView.findViewById(R.id.frameLayout);
            videoTitleTV = itemView.findViewById(R.id.videoTitleTV);
            actorsTV = itemView.findViewById(R.id.actorsTV);
            siv = itemView.findViewById(R.id.siv);
            parentProgressContanier = itemView.findViewById(R.id.parentProgressContanier);
            childProgressContainer = itemView.findViewById(R.id.childProgressContainer);
            flParentLinearLayout = itemView.findViewById(R.id.flParentLinearLayout);
            heartButton = itemView.findViewById(R.id.heartButton);
            lockButton = itemView.findViewById(R.id.lockButton);
        }
    }
}
