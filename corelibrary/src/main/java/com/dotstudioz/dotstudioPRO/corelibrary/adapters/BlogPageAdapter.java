package com.dotstudioz.dotstudioPRO.corelibrary.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PointF;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dotstudioz.dotstudioPRO.corelibrary.R;
import com.dotstudioz.dotstudioPRO.services.constants.ApplicationConstants;
import com.dotstudioz.dotstudioPRO.models.dto.SpotLightBlogDTO;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by Admin on 03-12-2014.
 */
public class BlogPageAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<SpotLightBlogDTO> spotLightBlogDTOList;
    public GenericDraweeHierarchy hierarchy;
    public int imageWidth;
    public int imageHeight;
    public boolean tabletFlag;
    public DisplayMetrics displayMetrics;

    public IBlogPageAdapter iBlogPageAdapter;

    public BlogPageAdapter(Activity activity, List<SpotLightBlogDTO> spotLightBlogDTOList) {
        this.activity = activity;
        iBlogPageAdapter = (IBlogPageAdapter) activity;
        this.spotLightBlogDTOList = spotLightBlogDTOList;
    }

    @Override
    public int getCount() {
        return spotLightBlogDTOList.size();
    }

    @Override
    public Object getItem(int position) {
        return spotLightBlogDTOList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(convertView == null) {
            convertView = inflater.inflate(R.layout.blog_page_item, parent, false);
        }

        //((LinearLayout)convertView).addView(getRosterImage(imageWidth, imageHeight, spotLightChannelDTOList.get(position), tabletFlag));

        try {
            RelativeLayout blogPageImageRL = (RelativeLayout) convertView.findViewById(R.id.blogPageImageRL);
            if (tabletFlag)
                blogPageImageRL.setLayoutParams(new LinearLayout.LayoutParams(imageWidth, LinearLayout.LayoutParams.WRAP_CONTENT));
            else
                blogPageImageRL.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            //((RelativeLayout.LayoutParams) blogPageImageRL.getLayoutParams()).addRule(RelativeLayout.ALIGN_PARENT_RIGHT | RelativeLayout.ALIGN_PARENT_BOTTOM);

            convertView.findViewById(R.id.blogPageItemProgressBar).setPadding(0, ((imageHeight / 2) - 60), 0, 0);

            RelativeLayout.LayoutParams params1 = null;
            if (tabletFlag)
                params1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, imageHeight);
            else
                params1 = new RelativeLayout.LayoutParams(imageWidth, imageHeight);
            //convertView.findViewById(R.id.iv1).setPadding(3, 3, 3, 3);
            convertView.findViewById(R.id.iv1).setLayoutParams(params1);
            convertView.findViewById(R.id.iv1).setClickable(true);
            /*PointF focusPoint = new PointF(0f, 0f);
            ((SimpleDraweeView)convertView.findViewById(R.id.iv1))
                    .getHierarchy()
                    .setActualImageFocusPoint(focusPoint);*/
            //iv1.setTag(((CategoriesDTO) categoriesDTOArrayList.get(i)).getChannelImage());
            String channelImagePlusCategorySlug = "";
            channelImagePlusCategorySlug = spotLightBlogDTOList.get(position).getId();
            convertView.findViewById(R.id.iv1).setTag(convertView.findViewById(R.id.iv1).getId(), channelImagePlusCategorySlug);
            //System.out.println("ImageData:"+categoriesDTO.getChannelSpotlightImage());
            String imageString = "";
            if(spotLightBlogDTOList.get(position).getPostImage() != null &&
                    spotLightBlogDTOList.get(position).getPostImage().length() > 0) {
                imageString = spotLightBlogDTOList.get(position).getPostImage();

                try {
                    if(spotLightBlogDTOList.get(position).getPostImageWidth() != null &&
                            spotLightBlogDTOList.get(position).getPostImageWidth().length() > 0 &&
                            spotLightBlogDTOList.get(position).getPostImageHeight() != null &&
                            spotLightBlogDTOList.get(position).getPostImageHeight().length() > 0 ) {

                        int screenWidth = displayMetrics.widthPixels;
                        int screenHeight = displayMetrics.heightPixels;

                        int postImageWidth = Integer.parseInt(spotLightBlogDTOList.get(position).getPostImageWidth());
                        int postImageHeight = Integer.parseInt(spotLightBlogDTOList.get(position).getPostImageHeight());


                        float ratio = (float) postImageHeight / postImageWidth;
                        float finalVideoWidth = (float) screenWidth;
                        float finalVideoHeight = (float) screenWidth * ratio;

                        RelativeLayout.LayoutParams params2 = null;
                        params2 = new RelativeLayout.LayoutParams(screenWidth, (int)finalVideoHeight);
                        convertView.findViewById(R.id.iv1).setLayoutParams(params2);

                        System.out.println("Inside BlogPageAdapter==>");
                        System.out.println("postImageWidth==>"+postImageWidth);
                        System.out.println("postImageHeight==>"+postImageHeight);
                        System.out.println("ratio==>"+ratio);
                        System.out.println("finalVideoWidth==>"+finalVideoWidth);
                        System.out.println("finalVideoHeight==>"+finalVideoHeight);


                        //convertView.setLayoutParams(new LinearLayout.LayoutParams(screenWidth, (int)finalVideoHeight));
                        convertView.getLayoutParams().width = screenWidth;
                        convertView.getLayoutParams().height = (int)finalVideoHeight;
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                }

            } else if(spotLightBlogDTOList.get(position).getPostFeaturedImage() != null &&
                    spotLightBlogDTOList.get(position).getPostFeaturedImage().length() > 0) {
                imageString = spotLightBlogDTOList.get(position).getPostFeaturedImage();
            }
            //String imageURLAddress = imageString + "/" + imageWidth + "/" + imageHeight;
            String imageURLAddress = imageString;

            //imageURLAddress = "https://f9q4g5j6.ssl.hwcdn.net/5a2197ed97f815e93d721d54/300/170";
            //System.out.println("ImageData:" + imageURLAddress);
            Uri uri = Uri.parse(imageURLAddress);
            ((SimpleDraweeView) convertView.findViewById(R.id.iv1)).setImageURI(uri);
            convertView.findViewById(R.id.iv1).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickHandlerForBlogPageImage(v.getTag(v.getId()).toString());//clickHandlerForChannelsImage(v.getTag().toString());
                }
            });

            if(spotLightBlogDTOList.get(position).getPostCategory() != null &&
                    spotLightBlogDTOList.get(position).getPostCategory().length() > 0) {
                ((TextView)convertView.findViewById(R.id.blogPageTitleTextView)).setText(spotLightBlogDTOList.get(position).getPostCategory());
                ((TextView)convertView.findViewById(R.id.blogPageTitleTextView)).setShadowLayer(2f, -1, 1, Color.parseColor("#000000"));
            }
            if(spotLightBlogDTOList.get(position).getPostTitle() != null &&
                    spotLightBlogDTOList.get(position).getPostTitle().length() > 0) {
                /*((TextView)convertView.findViewById(R.id.blogPageTitleTextView)).setText(spotLightBlogDTOList.get(position).getPostTitle());
                ((TextView)convertView.findViewById(R.id.blogPageTitleTextView)).setShadowLayer(2f, -1, 1, Color.parseColor("#000000"));*/
                ((TextView)convertView.findViewById(R.id.blogPageDescriptionTextView)).setText(spotLightBlogDTOList.get(position).getPostTitle());
                ((TextView)convertView.findViewById(R.id.blogPageDescriptionTextView)).setShadowLayer(2f, -1, 1, Color.parseColor("#000000"));
            }
            /*if(spotLightBlogDTOList.get(position).getPostExcerpt() != null &&
                    spotLightBlogDTOList.get(position).getPostExcerpt().length() > 0) {
                ((TextView)convertView.findViewById(R.id.blogPageDescriptionTextView)).setText(spotLightBlogDTOList.get(position).getPostExcerpt());
                ((TextView)convertView.findViewById(R.id.blogPageDescriptionTextView)).setShadowLayer(2f, -1, 1, Color.parseColor("#000000"));
            }*/

            if(position == 0) {
                convertView.findViewById(R.id.extraSpaceViewTopBlogPage).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ApplicationConstants.TOP_PADDING_FOR_MAIN_ACTION_BAR));
                convertView.findViewById(R.id.extraSpaceViewTopBlogPage).setVisibility(View.GONE);
                convertView.findViewById(R.id.extraSpaceViewBlogPage).setVisibility(View.GONE);
            } else if(position == spotLightBlogDTOList.size()-1) {
                convertView.findViewById(R.id.extraSpaceViewTopBlogPage).setVisibility(View.GONE);
                convertView.findViewById(R.id.extraSpaceViewBlogPage).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ApplicationConstants.BOTTOM_PADDING_FOR_TAB_BAR));
                convertView.findViewById(R.id.extraSpaceViewBlogPage).setVisibility(View.VISIBLE);
            } else {
                convertView.findViewById(R.id.extraSpaceViewBlogPage).setVisibility(View.GONE);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }


        return convertView;
    }

    private void clickHandlerForBlogPageImage(String tagSent) {
        iBlogPageAdapter.blogPageClickHandler(tagSent);
    }

    public interface IBlogPageAdapter {
        void blogPageClickHandler(String tagSent);
    }
}
