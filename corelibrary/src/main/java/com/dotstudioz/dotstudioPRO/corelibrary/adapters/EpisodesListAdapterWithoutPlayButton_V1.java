package com.dotstudioz.dotstudioPRO.corelibrary.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dotstudioz.dotstudioPRO.corelibrary.R;
import com.dotstudioz.dotstudioPRO.services.constants.ApplicationConstants;
import com.dotstudioz.dotstudioPRO.corelibrary.constants.FontsConstants;
import com.dotstudioz.dotstudioPRO.models.dto.EpisodeItem;
import com.dotstudioz.dotstudioPRO.corelibrary.util.CommonUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;
import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Admin on 03-12-2014.
 */
public class EpisodesListAdapterWithoutPlayButton_V1 extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<EpisodeItem> episodeItemsList;
    public GenericDraweeHierarchy hierarchy;
    public IEpisodesListAdapter iEpisodesListAdapter;
    private DisplayMetrics displayMetrics = new DisplayMetrics();
    private String activeColor = "#6dec68";
    //private String activeColor = "#FF0065";
    private String nonActiveColor = "#FFFFFF";
    public String title;
    public boolean showVideoThumbnail = false;

    public View convertViewAccessedFromOutside;

    private boolean isCustomFontEnabledForVideoTitle = false;
    private boolean isCustomFontEnabledForVideoDescription = false;
    private Typeface customFontForVideoTitle;
    private Typeface customFontForVideoDescription;

    public EpisodesListAdapterWithoutPlayButton_V1(Activity activity, List<EpisodeItem> episodeItemsList) {
        this.activity = activity;
        this.episodeItemsList = episodeItemsList;

        if (activity instanceof EpisodesListAdapterWithoutPlayButton_V1.IEpisodesListAdapter)
            iEpisodesListAdapter = (EpisodesListAdapterWithoutPlayButton_V1.IEpisodesListAdapter) activity;
        else
            throw new RuntimeException(activity.toString()+ " must implement IEpisodesListAdapter_V1");
    }

    @Override
    public int getCount() {
        return episodeItemsList.size();
    }

    @Override
    public Object getItem(int position) {
        return episodeItemsList.get(position);
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
            convertView = inflater.inflate(R.layout.episodes_list_without_play_button_item_view_v1, parent, false);
            convertViewAccessedFromOutside = convertView;
        }

        EpisodeItem episodeItem = episodeItemsList.get(position);

        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int imageWidth = ((displayMetrics.widthPixels*30)/100);
        int imageHeight = ((((displayMetrics.widthPixels*30)*9)/16)/100);

        final LinearLayout descLinearLayout = (LinearLayout) convertView.findViewById(R.id.descLinearLayout);
        final ImageView moreInfoImageView = (ImageView) convertView.findViewById(R.id.moreInfoImageView);
        final ImageView moreInfoImageView1 = (ImageView) convertView.findViewById(R.id.moreInfoImageView1);

        //((RelativeLayout.LayoutParams) descLinearLayout.getLayoutParams()).setMargins(imageWidth+20, 0, 0, 0);
        //((RelativeLayout.LayoutParams) descLinearLayout.getLayoutParams()).setMargins(0, 0, 0, 0);

        final View viewToShare = convertView;
        final int positionToShare = position;
        final ViewGroup parentToShare = parent;

        convertView.findViewById(R.id.seasonAndSeriesNameLL).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iEpisodesListAdapter.playButtonClicked(positionToShare, viewToShare, parentToShare);
            }
        });

        descLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iEpisodesListAdapter.playButtonClicked(positionToShare, viewToShare, parentToShare);
            }
        });

        //moreInfoImageView.setImageDrawable(new IconDrawable(activity, FontAwesomeIcons.fa_angle_down).color(Color.parseColor("#eeeeee")));
        moreInfoImageView.setImageDrawable(new IconDrawable(activity, FontAwesomeIcons.fa_ellipsis_v).color(Color.parseColor(nonActiveColor)));
        moreInfoImageView1.setImageDrawable(new IconDrawable(activity, FontAwesomeIcons.fa_ellipsis_v).color(Color.parseColor(nonActiveColor)));
        moreInfoImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(descLinearLayout.getVisibility() == View.GONE) {
                    //moreInfoImageView.setImageDrawable(new IconDrawable(activity, FontAwesomeIcons.fa_angle_up).color(Color.parseColor("#eeeeee")));
                    descLinearLayout.setVisibility(View.VISIBLE);
                } else if(descLinearLayout.getVisibility() == View.VISIBLE) {
                    //moreInfoImageView.setImageDrawable(new IconDrawable(activity, FontAwesomeIcons.fa_angle_down).color(Color.parseColor("#eeeeee")));
                    descLinearLayout.setVisibility(View.GONE);
                }

                ((LinearLayout) viewToShare.findViewById(R.id.descLinearLayout)).getViewTreeObserver().addOnGlobalLayoutListener(
                        new ViewTreeObserver.OnGlobalLayoutListener(){
                            @Override
                            public void onGlobalLayout() {
                                // gets called after layout has been done but before display
                                // so we can get the height then hide the view
                                iEpisodesListAdapter.resizeTheList(positionToShare);
                                ((LinearLayout) viewToShare.findViewById(R.id.descLinearLayout)).getViewTreeObserver().removeOnGlobalLayoutListener(this);
                            }

                        });

            }
        });

        //Validating the video paused point and respectively rendering the video progress bar
        if(episodeItem.getVideoPausedPoint() > 0 &&
                episodeItem.getVideoDuration() > 0) {
            int videoDuration = episodeItem.getVideoDuration();
            int videoPausedPointPercentage = ((episodeItem.getVideoPausedPoint() * 100)/videoDuration);
            convertView.findViewById(R.id.parentProgressContanier).setBackgroundColor(Color.parseColor("#33ffffff"));
            convertView.findViewById(R.id.childProgressContainer).setBackgroundColor(Color.parseColor(activeColor));
            int heightForProgressBar = 0;

            if (ApplicationConstants.TEXT_INPUT_HEIGHT > 0)
                heightForProgressBar = ApplicationConstants.TEXT_INPUT_HEIGHT / 8;
            else if (ApplicationConstants.TOP_PADDING_FOR_MAIN_ACTION_BAR > 0)
                heightForProgressBar = ApplicationConstants.TOP_PADDING_FOR_MAIN_ACTION_BAR / 10;
            ((LinearLayout.LayoutParams)convertView.findViewById(R.id.parentProgressContanier).getLayoutParams()).height = heightForProgressBar;

            int actualWidth = 0;
            actualWidth = displayMetrics.widthPixels - (imageWidth+((int) TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, 70, displayMetrics )));

            int widthOfProgress = 0;
            if(actualWidth <= 0)
                widthOfProgress = (((displayMetrics.widthPixels-(imageWidth+moreInfoImageView.getMeasuredWidth()+convertView.findViewById(R.id.seasonPlayImage).getMeasuredWidth()))*videoPausedPointPercentage)/100);
            else
                widthOfProgress = ((actualWidth*videoPausedPointPercentage)/100);

            /*System.out.println("ApplicationConstants.TOP_PADDING_FOR_MAIN_ACTION_BAR==>"+ApplicationConstants.TOP_PADDING_FOR_MAIN_ACTION_BAR);
            System.out.println("videoDuration==>"+videoDuration);
            System.out.println("episodeItem.getVideoPausedPoint()==>"+episodeItem.getVideoPausedPoint());
            System.out.println("videoPausedPointPercentage==>"+videoPausedPointPercentage);
            System.out.println("heightForProgressBar==>"+heightForProgressBar);
            System.out.println("actualWidth==>"+actualWidth);
            System.out.println("widthOfProgress==>"+widthOfProgress);
            System.out.println("(((displayMetrics.widthPixels-(imageWidth+moreInfoImageView.getMeasuredWidth()+convertView.findViewById(R.id.seasonPlayImage).getMeasuredWidth()))*videoPausedPointPercentage)/100)==>"+(((displayMetrics.widthPixels-(imageWidth+moreInfoImageView.getMeasuredWidth()+convertView.findViewById(R.id.seasonPlayImage).getMeasuredWidth()))*videoPausedPointPercentage)/100));*/
            convertView.findViewById(R.id.childProgressContainer).setLayoutParams(new LinearLayout.LayoutParams(widthOfProgress, heightForProgressBar));
        } else {
            //no need to do anything here as the linear layout used is transparent anyways
        }

        TextView episodeInfoTextView = (TextView) convertView.findViewById(R.id.episodeInfoTextView);
        TextView episodeDescTextView = (TextView) convertView.findViewById(R.id.episodeDescTextView);

        if(CommonUtils.isActuallyTabletDevice(activity))
            episodeDescTextView.setTextSize(14f);
        else
            episodeDescTextView.setTextSize(12f);

        episodeInfoTextView.setText(episodeItem.getYearLangCountryDurationString());
        episodeDescTextView.setText(episodeItem.getVideoDescription());

        if(isCustomFontEnabledForVideoTitle)
            episodeInfoTextView.setTypeface(customFontForVideoDescription);
        else
            episodeInfoTextView.setTypeface(FontsConstants.tfRegular);
        if(isCustomFontEnabledForVideoDescription)
            episodeDescTextView.setTypeface(customFontForVideoDescription);
        else
            episodeDescTextView.setTypeface(FontsConstants.tfRegular);


        if(episodeItem.isSelected())
            ((ImageView)convertView.findViewById(R.id.seasonPlayImage)).setImageDrawable(new IconDrawable(activity, FontAwesomeIcons.fa_play_circle_o).color(Color.parseColor(activeColor)));
        else
            ((ImageView)convertView.findViewById(R.id.seasonPlayImage)).setImageDrawable(new IconDrawable(activity, FontAwesomeIcons.fa_play_circle_o).color(Color.parseColor(nonActiveColor)));


        convertView.findViewById(R.id.seasonPlayImage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iEpisodesListAdapter.playButtonClicked(positionToShare, viewToShare, parentToShare);
            }
        });

        final TextView seasonName = (TextView) convertView.findViewById(R.id.seasonName);
        if(isCustomFontEnabledForVideoTitle)
            seasonName.setTypeface(customFontForVideoDescription);
        else
            seasonName.setTypeface(FontsConstants.tfRegular);
        String episodeNameWithSeriesTitle = "";
        /*if(episodeItem.getSeriesTitle() != null && episodeItem.getSeriesTitle().length() > 0) {
            episodeNameWithSeriesTitle = episodeItem.getSeriesTitle();
        }*/
        if(episodeNameWithSeriesTitle.length() > 0)
            episodeNameWithSeriesTitle = episodeNameWithSeriesTitle + " - " + episodeItem.getSeasonName();
        else
            episodeNameWithSeriesTitle = episodeItem.getSeasonName();
        title = episodeItem.getSeasonName();
        seasonName.setText(episodeNameWithSeriesTitle);
        CommonUtils.increaseClickAreaFor(seasonName, 60);

        if(episodeItem.isSelected()) {
            seasonName.setTextColor(Color.parseColor(activeColor));
        } else {
            seasonName.setTextColor(Color.parseColor(nonActiveColor));
        }





        final TextView seriesTitleName = (TextView) convertView.findViewById(R.id.seriesTitleName);
        if(isCustomFontEnabledForVideoTitle)
            seriesTitleName.setTypeface(customFontForVideoDescription);
        else
            seriesTitleName.setTypeface(FontsConstants.tfRegular);
        if(episodeItem.getSeriesTitle() != null && episodeItem.getSeriesTitle().length() > 0) {
            seriesTitleName.setText(episodeItem.getSeriesTitle());
            seriesTitleName.setVisibility(View.VISIBLE);
        } else {
            seriesTitleName.setVisibility(View.GONE);
        }
        CommonUtils.increaseClickAreaFor(seriesTitleName, 60);

        if(episodeItem.isSelected())
            seriesTitleName.setTextColor(Color.parseColor(activeColor));
        else
            seriesTitleName.setTextColor(Color.parseColor(nonActiveColor));






        ((RelativeLayout.LayoutParams)convertView.findViewById(R.id.videoThumbnailImageView).getLayoutParams()).width = imageWidth;
        ((RelativeLayout.LayoutParams)convertView.findViewById(R.id.videoThumbnailImageView).getLayoutParams()).height = imageHeight;
        ((LinearLayout.LayoutParams)convertView.findViewById(R.id.videoThumbnailImageView1).getLayoutParams()).width = imageWidth;
        ((LinearLayout.LayoutParams)convertView.findViewById(R.id.videoThumbnailImageView1).getLayoutParams()).height = imageHeight;
        convertView.findViewById(R.id.videoThumbnailImageView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(descLinearLayout.getVisibility() == View.GONE) {
                    //moreInfoImageView.setImageDrawable(new IconDrawable(activity, FontAwesomeIcons.fa_angle_up).color(Color.parseColor("#eeeeee")));
                    descLinearLayout.setVisibility(View.VISIBLE);
                } else if(descLinearLayout.getVisibility() == View.VISIBLE) {
                    //moreInfoImageView.setImageDrawable(new IconDrawable(activity, FontAwesomeIcons.fa_angle_down).color(Color.parseColor("#eeeeee")));
                    descLinearLayout.setVisibility(View.GONE);
                }

                ((LinearLayout) viewToShare.findViewById(R.id.descLinearLayout)).getViewTreeObserver().addOnGlobalLayoutListener(
                        new ViewTreeObserver.OnGlobalLayoutListener(){
                            @Override
                            public void onGlobalLayout() {
                                // gets called after layout has been done but before display
                                // so we can get the height then hide the view
                                iEpisodesListAdapter.resizeTheList(positionToShare);
                                ((LinearLayout) viewToShare.findViewById(R.id.descLinearLayout)).getViewTreeObserver().removeOnGlobalLayoutListener(this);
                            }

                        });
            }
        });

        seasonName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iEpisodesListAdapter.playButtonClicked(positionToShare, viewToShare, parentToShare);
            }
        });

        String imageString = episodeItem.getVideoThumbnailImageView()+"/"+imageWidth+"/"+imageHeight;

        if(!imageString.substring(0, 4).equals("http"))
            imageString = "https://images.dotstudiopro.com/"+imageString;
        Uri uri1 = Uri.parse(imageString);
        SimpleDraweeView videoThumbnailImageView = (SimpleDraweeView) convertView.findViewById(R.id.videoThumbnailImageView);
        hierarchy = videoThumbnailImageView.getHierarchy();
        //hierarchy.setPlaceholderImage(R.drawable.placeholder4);

        if(showVideoThumbnail) {
            videoThumbnailImageView.setVisibility(View.VISIBLE);
        } else {
            //hidingg his only for decentric app, have to add a condition to show or not dynamically
            videoThumbnailImageView.setVisibility(View.GONE);
        }
        Picasso.with(activity).load(uri1).into(videoThumbnailImageView);
        //Glide.with(activity).load(uri1).placeholder(R.drawable.placeholder4).into(videoThumbnailImageView);


        if (position % 2 == 1) {
            if(ApplicationConstants.EPISODE_LIST_COLOR_1 != null && ApplicationConstants.EPISODE_LIST_COLOR_1.length() > 0)
                convertView.setBackgroundColor(Color.parseColor(ApplicationConstants.EPISODE_LIST_COLOR_1));
        } else {
            if(ApplicationConstants.EPISODE_LIST_COLOR_2 != null && ApplicationConstants.EPISODE_LIST_COLOR_2.length() > 0)
                convertView.setBackgroundColor(Color.parseColor(ApplicationConstants.EPISODE_LIST_COLOR_2));
        }

        if(position == episodeItemsList.size())
            convertView.findViewById(R.id.extraSpaceView).setVisibility(View.VISIBLE);

        return convertView;
    }

    public void changeAllPlayButtonToWhiteForPhones() {
        for(int i = 0; i < episodeItemsList.size(); i++) {
            episodeItemsList.get(i).setSelected(false);
        }
    }
    public void changePlayButtonToGreenForPhones(int positionInList) {
        changeAllPlayButtonToWhiteForPhones();
        try { episodeItemsList.get(positionInList).setSelected(true);}catch(Exception e){
            //e.printStackTrace();
        }
        notifyDataSetChanged();
    }

    public interface IEpisodesListAdapter {
        void playButtonClicked(int position, View view, ViewGroup parent);
        void resizeTheList(int positionToShare);
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

    public String getActiveColor() {
        return activeColor;
    }

    public void setActiveColor(String activeColor) {
        this.activeColor = activeColor;
    }

    public String getNonActiveColor() {
        return nonActiveColor;
    }

    public void setNonActiveColor(String nonActiveColor) {
        this.nonActiveColor = nonActiveColor;
    }
}
