package com.dotstudioz.dotstudioPRO.corelibrary.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dotstudioz.dotstudioPRO.corelibrary.R;
import com.dotstudioz.dotstudioPRO.corelibrary.constants.FontsConstants;
import com.dotstudioz.dotstudioPRO.models.dto.SpotLightCategoriesDTO;
import com.dotstudioz.dotstudioPRO.models.dto.VideoInfoDTO;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class MyVideoOrChannelPagerAdapter extends PagerAdapter {

    public interface IVideoOrChannelItemPagerAdapter {
        void videoItemClickHandler(String desc, String clickedVideoID, int position);
        void channelItemClickHandler(String tagString);
    }
    public IVideoOrChannelItemPagerAdapter iVideoOrChannelItemPagerAdapter;

    public List<VideoInfoDTO> videoInfoDTOArrayList;
    public String categoryName;
    public SpotLightCategoriesDTO spotLightCategoriesDTO;
    private static final String TAG = "MyPagerAdapter";
    //private ArrayList<Utils.DummyItem> mDummyItems;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
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



    public MyVideoOrChannelPagerAdapter(Context context) {
        iVideoOrChannelItemPagerAdapter = (IVideoOrChannelItemPagerAdapter) context;
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
//    public MyVideoOrChannelPagerAdapter(ArrayList<Utils.DummyItem> dummyItems, Context context) {
//        this.mDummyItems = dummyItems;
//        mContext = context;
//        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//    }

    //Abstract method in PagerAdapter
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

    //Abstract method in PagerAdapter

    /**
     * @return true if the value returned from {@link #instantiateItem(ViewGroup, int)} is the
     * same object as the {@link View} added to the {@link android.support.v4.view.ViewPager}.
     */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return object == view;
    }

    /**
     * Instantiate the {@link View} which should be displayed at {@code position}. Here we
     * inflate a layout from the apps resources and then change the text view to signify the position.
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
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
                // System.out.println("channelIdPlusCategorySlug==>"+channelIdPlusCategorySlug);
                //  System.out.println("imageView get ID ==>"+imageView.getId());
                System.out.println("position ==> ==> ==>==>"+position+" ==>==>==>"+channelIdPlusCategorySlug);
                // imageView.setTag(imageView.getId(), channelIdPlusCategorySlug);
                // imageView.setTag(channelIdPlusCategorySlug);
                imageView.setTag(position);
                imageView.setContentDescription(channelIdPlusCategorySlug);

                if (spotLightCategoriesDTO.getCategorySlug().equals("hero-showcase"))
                    videoTitleTV.setText(spotLightCategoriesDTO.getSpotLightChannelDTOList().get(position).getTitle());

                if ((spotLightCategoriesDTO.getSpotLightChannelDTOList().get(position).getVideo() != null &&
                        spotLightCategoriesDTO.getSpotLightChannelDTOList().get(position).getVideo().length() > 0) ||
                        (spotLightCategoriesDTO.getSpotLightChannelDTOList().get(position).getPlaylist() != null &&
                                spotLightCategoriesDTO.getSpotLightChannelDTOList().get(position).getPlaylist().size() > 0)) {
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int index = (int) view.getTag();
                            System.out.println("indexindexindexindexindex" +index);
//                            System.out.println("view.getTag(view.getId()).toString()==>" + view.getContentDescription().toString());
//                            if(channelIdPlusCategorySlugFromMultiViewPager != null && channelIdPlusCategorySlugFromMultiViewPager.length() > 0) {
//                                System.out.println("channelIdPlusCategorySlugFromMultiViewPager==>" + channelIdPlusCategorySlugFromMultiViewPager);
//                                iVideoOrChannelItemPagerAdapter.channelItemClickHandler(channelIdPlusCategorySlugFromMultiViewPager);
//                            } else {
//                                System.out.println("view.getTag(view.getId()).toString()==>" + view.getTag().toString());
//                               // iVideoOrChannelItemPagerAdapter.channelItemClickHandler(view.getTag(view.getId()).toString());
//                                iVideoOrChannelItemPagerAdapter.channelItemClickHandler(view.getTag().toString());
//                            }
                        }
                    });
                } else {

                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int index = (int) view.getTag();
                            System.out.println("indexindexindexindexindex" +index);
                            System.out.println("channelIdPlusCategorySlugFromMultiViewPager else ==>" +1);
                            //  iVideoOrChannelItemPagerAdapter.channelItemClickHandler(view.getTag(view.getId()).toString());
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
        }
        // Add the newly created View to the ViewPager
        container.addView(itemView);
        Log.i(TAG, "instantiateItem() [position: " + position + "]" + " childCount:" + container.getChildCount());
        // Return the View
        return itemView;
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

    /**
     * Destroy the item from the {@link android.support.v4.view.ViewPager}. In our case this is simply removing the
     * {@link View}.
     */
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
        Log.i(TAG, "destroyItem() [position: " + position + "]" + " childCount:" + container.getChildCount());
    }

    /**
     * This method is only gets called when we invoke {@link #notifyDataSetChanged()} on this adapter.
     * Returns the index of the currently active fragments.
     * There could be minimum two and maximum three active fragments(suppose we have 3 or more  fragments to show).
     * If there is only one fragment to show that will be only active fragment.
     * If there are only two fragments to show, both will be in active state.
     * PagerAdapter keeps left and right fragments of the currently visible fragment in ready/active state so that it could be shown immediate on swiping.
     * Currently Active Fragments means one which is currently visible one is before it and one is after it.
     *
     * @param object Active Fragment reference
     * @return Returns the index of the currently active fragments.
     */
    //@Override
//    public int getItemPosition(Object object) {
//        Utils.DummyItem dummyItem = (Utils.DummyItem) ((View) object).getTag();
//        int position = mDummyItems.indexOf(dummyItem);
//        if (position >= 0) {
//            // The current data matches the data in this active fragment, so let it be as it is.
//            return position;
//        } else {
//            // Returning POSITION_NONE means the current data does not matches the data this fragment is showing right now.  Returning POSITION_NONE constant will force the fragment to redraw its view layout all over again and show new data.
//            return POSITION_NONE;
//        }
//    }
}
