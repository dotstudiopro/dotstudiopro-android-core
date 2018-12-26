package com.dotstudioz.dotstudioPRO.corelibrary.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dotstudioz.dotstudioPRO.corelibrary.R;
import com.dotstudioz.dotstudioPRO.services.constants.FontsConstants;
import com.dotstudioz.dotstudioPRO.models.dto.SpotLightCategoriesDTO;
import com.dotstudioz.dotstudioPRO.models.dto.VideoInfoDTO;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class VideoItemPagerAdapter extends PagerAdapter {

        public IVideoItemPagerAdapter iVideoItemPagerAdapter;

        public SpotLightCategoriesDTO spotLightCategoriesDTO;

        public List<VideoInfoDTO> videoInfoDTOArrayList;
        public String categoryName;
        public boolean isNextItemClicked = false;

        Context mContext;
        LayoutInflater mLayoutInflater;

        public VideoItemPagerAdapter(Context context) {
            mContext = context;
            iVideoItemPagerAdapter = (IVideoItemPagerAdapter) mContext;
            mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View itemView = mLayoutInflater.inflate(R.layout.video_item_btvr_frame_layout_view, container, false);

            DisplayMetrics dm = new DisplayMetrics();
            ((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(dm);

            int width = dm.widthPixels;
            int imageWidth = ((3* width)/4);
            int imageHeight = ((imageWidth * 9) / 16);

            FrameLayout fl = (FrameLayout) itemView.findViewById(R.id.frameLayout);
            fl.setLayoutParams(new RelativeLayout.LayoutParams((imageWidth+20), (imageHeight+5)));

            TextView videoTitleTV = (TextView) itemView.findViewById(R.id.videoTitleTV);
            videoTitleTV.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            videoTitleTV.setText(videoInfoDTOArrayList.get(position).getVideoTitle());
            videoTitleTV.setSingleLine(true);
            videoTitleTV.setPadding(15, 0, 20, 0);
            videoTitleTV.setEllipsize(TextUtils.TruncateAt.END);
            videoTitleTV.setTextSize(16);
            videoTitleTV.setTypeface(FontsConstants.tfBold);
            videoTitleTV.setGravity(Gravity.LEFT);
            videoTitleTV.setShadowLayer(2f, -1, 1, Color.BLACK);
            videoTitleTV.setTextColor(Color.WHITE);

            TextView actorsTV = (TextView) itemView.findViewById(R.id.actorsTV);
            actorsTV.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            actorsTV.setText(videoInfoDTOArrayList.get(position).getCasting());
            actorsTV.setSingleLine(true);
            actorsTV.setPadding(15, 0, 20, 10);
            actorsTV.setEllipsize(TextUtils.TruncateAt.END);
            actorsTV.setTextSize(14);
            actorsTV.setTypeface(FontsConstants.tfRegular);
            actorsTV.setGravity(Gravity.LEFT);
            actorsTV.setShadowLayer(2f, -1, 1, Color.BLACK);
            actorsTV.setTextColor(Color.parseColor("#d6d6d6"));

            SimpleDraweeView imageView = (SimpleDraweeView) itemView.findViewById(R.id.siv);
            String tagString = categoryName+"|"+videoInfoDTOArrayList.get(position).getThumb();
            imageView.setTag(imageView.getId(), tagString);

            String imageURL = "https://images.dotstudiopro.com/"+videoInfoDTOArrayList.get(position).getThumb() + "/" + imageWidth + "/" + imageHeight;
            Uri uri = Uri.parse(imageURL);
            imageView.setImageURI(uri);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(isNextItemClicked) {
                        String tagValue = view.getTag(view.getId()).toString();
                        tagValue = tagValue.substring((tagValue.indexOf("|"))+1, tagValue.length());
                        //System.out.println("tagValue"+tagValue);
                        for(int i = 0; i < videoInfoDTOArrayList.size(); i++) {
                            if(videoInfoDTOArrayList.get(i).getThumb().equals(tagValue)) {
                                //System.out.println("videoInfoDTOArrayList.get(position).getThumb()"+videoInfoDTOArrayList.get(i+1).getThumb());
                                if(CommonUtils.isActuallyTabletDevice((Activity)mContext)) {
                                    String tagToSend = categoryName + "|" + videoInfoDTOArrayList.get(i).getThumb();
                                    iVideoItemPagerAdapter.videoItemClickHandler(tagToSend);
                                } else {
                                    String tagToSend = categoryName + "|" + videoInfoDTOArrayList.get(i + 1).getThumb();
                                    iVideoItemPagerAdapter.videoItemClickHandler(tagToSend);
                                }
                                isNextItemClicked = false;
                            }
                        }
                    } else {
                        String tagToSend = view.getTag(view.getId()).toString();
                        iVideoItemPagerAdapter.videoItemClickHandler(tagToSend);
                        //System.out.println(view.getId() + "-view.getId()" + view.getTag(view.getId()));
                    }
                }
            });

            container.addView(itemView);
            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }

        @Override
        public int getCount() {
            return videoInfoDTOArrayList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return (view == object);
        }


        public interface IVideoItemPagerAdapter {
            void videoItemClickHandler(String videoID);
        }
    }