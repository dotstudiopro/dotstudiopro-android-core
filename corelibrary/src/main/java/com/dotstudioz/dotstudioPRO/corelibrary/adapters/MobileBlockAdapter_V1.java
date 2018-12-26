package com.dotstudioz.dotstudioPRO.corelibrary.adapters;

/**
 * Created by mohsin on 15-10-2016.
 */

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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dotstudioz.dotstudioPRO.corelibrary.R;
import com.dotstudioz.dotstudioPRO.corelibrary.constants.FontsConstants;
import com.dotstudioz.dotstudioPRO.models.dto.MobileBlockDTO;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;


public class MobileBlockAdapter_V1 extends PagerAdapter {

    public IMobileBlockAdapter iMobileBlockAdapter;

    public MobileBlockDTO mobileBlockDTO;
    public int imageHeight;
    public int imageWidth;
    private int titleColour = Color.parseColor("#eeeeee");
    public float videoTitleTVFontSize;
    public ArrayList<MobileBlockDTO> appsHomeMobileBlockDTOArrayList;

    private boolean isCustomFontEnabledForVideoTitle = false;
    private Typeface customFontForVideoTitle;

    Context mContext;
    LayoutInflater mLayoutInflater;

    public MobileBlockAdapter_V1(Context context) {
        iMobileBlockAdapter = (IMobileBlockAdapter) context;
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View itemView = mLayoutInflater.inflate(R.layout.mobile_block_frame_layout_view, container, false);

        ((TextView)itemView.findViewById(R.id.mobileBlockTitleTextView)).setTypeface(FontsConstants.robotMediumFont);
        ((TextView)itemView.findViewById(R.id.mobileBlockDescriptionTextView)).setTypeface(FontsConstants.latoRegular);
        ((TextView)itemView.findViewById(R.id.nonPostDescription2TextView)).setTypeface(FontsConstants.robotMediumFont);
        ((TextView)itemView.findViewById(R.id.mobileBlockReadMoreTextView)).setTypeface(FontsConstants.robotMediumFont);

        ((TextView)itemView.findViewById(R.id.mobileBlockMovieTitleTextView)).setTypeface(FontsConstants.robotMediumFont);
        ((TextView)itemView.findViewById(R.id.mobileBlockMovieDescTextView)).setTypeface(FontsConstants.latoRegular);

        if(mobileBlockDTO.getPostType().equalsIgnoreCase("video")) {
            itemView.findViewById(R.id.mobileBlockPostRelativeLayout).setVisibility(View.GONE);
            itemView.findViewById(R.id.mobileBlockMovieRelativeLayout).setVisibility(View.VISIBLE);

            itemView.findViewById(R.id.mobileBlockMovieRelativeLayout).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    iMobileBlockAdapter.postItemClicked("video", "", "", "", "");
                }
            });

            RelativeLayout fl = (RelativeLayout) itemView.findViewById(R.id.mobileBlockMovieRelativeLayout);
            fl.setLayoutParams(new LinearLayout.LayoutParams(imageWidth, imageHeight));

            TextView videoTitleTV = (TextView) itemView.findViewById(R.id.mobileBlockMovieTitleTextView);
            videoTitleTV.setSingleLine(true);
            videoTitleTV.setPadding(0, 0, 20, 5);
            videoTitleTV.setEllipsize(TextUtils.TruncateAt.END);
            if(isCustomFontEnabledForVideoTitle)
                videoTitleTV.setTypeface(customFontForVideoTitle);
            else
                videoTitleTV.setTypeface(FontsConstants.tfRegular);
            //videoTitleTV.setShadowLayer(2f, -1, 1, Color.BLACK);
            videoTitleTV.setTextColor(Color.WHITE);
            videoTitleTV.setText(mobileBlockDTO.getTitle());

            TextView mobileBlockMovieDescTextView = (TextView) itemView.findViewById(R.id.mobileBlockMovieDescTextView);
            mobileBlockMovieDescTextView.setEllipsize(TextUtils.TruncateAt.END);
            if(isCustomFontEnabledForVideoTitle)
                mobileBlockMovieDescTextView.setTypeface(customFontForVideoTitle);
            else
                mobileBlockMovieDescTextView.setTypeface(FontsConstants.tfRegular);
            //videoTitleTV.setShadowLayer(2f, -1, 1, Color.BLACK);
            mobileBlockMovieDescTextView.setTextColor(Color.WHITE);
            Log.d("MobileBlockAdapter_V1", "mobileBlockDTO.getTitle()==>"+mobileBlockDTO.getTitle());
            Log.d("MobileBlockAdapter_V1", "mobileBlockDTO.getDescription()==>"+mobileBlockDTO.getDescription());
            mobileBlockMovieDescTextView.setText(mobileBlockDTO.getDescription());

            SimpleDraweeView imageView = (SimpleDraweeView) itemView.findViewById(R.id.siv1);
            imageView.getLayoutParams().width = imageWidth;
            imageView.getLayoutParams().height = imageHeight;

            itemView.findViewById(R.id.siv1RelativeLayout).getLayoutParams().width = imageWidth;
            itemView.findViewById(R.id.siv1RelativeLayout).getLayoutParams().height = (imageHeight/2);
            //imageView.setLayoutParams(new RelativeLayout.LayoutParams(imageWidth, imageHeight));
            try {
                String imageURL = mobileBlockDTO.getImageURL() + "/" + imageWidth + "/" + imageHeight;
                Uri uri = Uri.parse(imageURL);
                imageView.setImageURI(uri);
            } catch(Exception e) {
                e.printStackTrace();
            }

        } else if(mobileBlockDTO.getPostType().equalsIgnoreCase("post") ||
                mobileBlockDTO.getPostType().equalsIgnoreCase("news") ||
                mobileBlockDTO.getPostType().equalsIgnoreCase("music") ||
                mobileBlockDTO.getPostType().equalsIgnoreCase("radio")) {
            itemView.findViewById(R.id.mobileBlockPostRelativeLayout).setVisibility(View.VISIBLE);
            itemView.findViewById(R.id.mobileBlockMovieRelativeLayout).setVisibility(View.GONE);

            itemView.findViewById(R.id.mobileBlockPostRelativeLayout).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mobileBlockDTO.getPostType().equalsIgnoreCase("news")) {
                        iMobileBlockAdapter.postItemClicked("news", mobileBlockDTO.getTitle(), "", "", "");
                    } else if(mobileBlockDTO.getPostType().equalsIgnoreCase("music")) {
                        iMobileBlockAdapter.postItemClicked("music", mobileBlockDTO.getTitle(), "", "", "");
                    } else if(mobileBlockDTO.getPostType().equalsIgnoreCase("radio")) {
                        iMobileBlockAdapter.postItemClicked("radio", mobileBlockDTO.getTitle(), "", "" ,"");
                    } else if(mobileBlockDTO.getPostType().equalsIgnoreCase("post")) {
                        iMobileBlockAdapter.postItemClicked("post", mobileBlockDTO.getTitle(), mobileBlockDTO.getAuthor(), mobileBlockDTO.getDate(), mobileBlockDTO.getPostContent());
                    }
                }
            });

            if(mobileBlockDTO.getPostType().equalsIgnoreCase("news") ||
                    mobileBlockDTO.getPostType().equalsIgnoreCase("music") ||
                    mobileBlockDTO.getPostType().equalsIgnoreCase("radio")) {
                itemView.findViewById(R.id.mobileBlockDescriptionTextView).setVisibility(View.VISIBLE);
                itemView.findViewById(R.id.nonPostLinearLayout).setVisibility(View.VISIBLE);
                itemView.findViewById(R.id.mobileBlockReadMoreTextView).setVisibility(View.GONE);

                ((TextView) itemView.findViewById(R.id.mobileBlockDescriptionTextView)).setText(mobileBlockDTO.getDescription());

                if(mobileBlockDTO.getPostType().equalsIgnoreCase("news")) {
                    ((ImageView) itemView.findViewById(R.id.nonPostDescription2ImageView)).setImageDrawable(mContext.getResources().getDrawable(R.drawable.play_now));
                    ((TextView) itemView.findViewById(R.id.nonPostDescription2TextView)).setText("Play Now");
                } else if(mobileBlockDTO.getPostType().equalsIgnoreCase("music")) {
                    ((ImageView) itemView.findViewById(R.id.nonPostDescription2ImageView)).setImageDrawable(mContext.getResources().getDrawable(R.drawable.read_more));
                    ((TextView) itemView.findViewById(R.id.nonPostDescription2TextView)).setText("Read More");
                } else if(mobileBlockDTO.getPostType().equalsIgnoreCase("radio")) {
                    ((ImageView) itemView.findViewById(R.id.nonPostDescription2ImageView)).setImageDrawable(mContext.getResources().getDrawable(R.drawable.listen));
                    ((TextView) itemView.findViewById(R.id.nonPostDescription2TextView)).setText("Listen Now");
                }
            } else {
                itemView.findViewById(R.id.mobileBlockDescriptionTextView).setVisibility(View.GONE);
                itemView.findViewById(R.id.nonPostLinearLayout).setVisibility(View.GONE);
                itemView.findViewById(R.id.mobileBlockReadMoreTextView).setVisibility(View.VISIBLE);
            }

            itemView.findViewById(R.id.mobileBlockPostRelativeLayout).getLayoutParams().height = (((imageWidth/2)*2)/3);

            TextView videoTitleTV = (TextView) itemView.findViewById(R.id.mobileBlockTitleTextView);
            videoTitleTV.getLayoutParams().width = (imageWidth/2);
            //videoTitleTV.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            //videoTitleTV.setTextSize(videoTitleTVFontSize);
            if(isCustomFontEnabledForVideoTitle)
                videoTitleTV.setTypeface(customFontForVideoTitle);
            else
                videoTitleTV.setTypeface(FontsConstants.tfRegular);
            videoTitleTV.setGravity(Gravity.LEFT);
            //videoTitleTV.setShadowLayer(2f, -1, 1, Color.BLACK);
            videoTitleTV.setTextColor(Color.WHITE);
            videoTitleTV.setText(mobileBlockDTO.getTitle());

            SimpleDraweeView imageView = (SimpleDraweeView) itemView.findViewById(R.id.siv);
            if(mobileBlockDTO.getPostType().equalsIgnoreCase("post")) {
                //imageView.setLayoutParams(new RelativeLayout.LayoutParams((imageWidth / 2), (((imageWidth / 2) * 2) / 3)));
                imageView.getLayoutParams().width = (imageWidth / 2);
                imageView.getLayoutParams().height = (((imageWidth / 2) * 2) / 3);

                int actualPosition = 0;
                if(appsHomeMobileBlockDTOArrayList != null) {
                    for (int i = 0; i < appsHomeMobileBlockDTOArrayList.size(); i++) {
                        if(appsHomeMobileBlockDTOArrayList.get(i).getPostType().equalsIgnoreCase("post")) {
                            if(appsHomeMobileBlockDTOArrayList.get(i).getTitle().equalsIgnoreCase(mobileBlockDTO.getTitle())) {
                                actualPosition = i;
                            }
                        }
                    }
                }
                if(((mobileBlockDTO.getIndexForGradient()+1) % 3) == 1) {
                    ((RelativeLayout)itemView.findViewById(R.id.sivRelativeLayout)).setBackground(mContext.getResources().getDrawable(R.drawable.red_gradient));
                } else if(((mobileBlockDTO.getIndexForGradient()+1) % 3) == 2) {
                    ((RelativeLayout)itemView.findViewById(R.id.sivRelativeLayout)).setBackground(mContext.getResources().getDrawable(R.drawable.black_gradient));
                } else if(((mobileBlockDTO.getIndexForGradient()+1) % 3) == 0) {
                    ((RelativeLayout)itemView.findViewById(R.id.sivRelativeLayout)).setBackground(mContext.getResources().getDrawable(R.drawable.blue_gradient));
                }

                itemView.findViewById(R.id.sivRelativeLayout).getLayoutParams().width = imageWidth;
                itemView.findViewById(R.id.sivRelativeLayout).getLayoutParams().height = ((imageWidth * 2) / 3);

                ((RelativeLayout.LayoutParams)itemView.findViewById(R.id.sivRelativeLayout).getLayoutParams()).setMargins(0, 0, 0, 0);
            } else {
                imageView.getLayoutParams().width = imageWidth;
                imageView.getLayoutParams().height = imageHeight;

                itemView.findViewById(R.id.sivRelativeLayout).setVisibility(View.GONE);
            }
            try {
                String imageURL = mobileBlockDTO.getImageURL();
                Uri uri = Uri.parse(imageURL);
                imageView.setImageURI(uri);
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
        return 1;
    }

    public int getTitleColour() {
        return titleColour;
    }

    public void setTitleColour(int titleColour) {
        this.titleColour = titleColour;
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

    public interface IMobileBlockAdapter {
        /*void videoItemClickHandler(String desc, String clickedVideoID, int position);*/
        void postItemClicked(String postType, String postTitle, String postAuthor, String postDate, String postContent);
    }
}
