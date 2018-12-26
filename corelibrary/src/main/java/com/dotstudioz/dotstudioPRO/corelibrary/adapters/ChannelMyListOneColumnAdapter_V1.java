package com.dotstudioz.dotstudioPRO.corelibrary.adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.util.Log;
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
import com.dotstudioz.dotstudioPRO.models.dto.ChannelMyListDTO;
import com.dotstudioz.dotstudioPRO.corelibrary.util.CommonUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;
import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;

import java.util.List;

/**
 * Created by Admin on 03-12-2014.
 */
public class ChannelMyListOneColumnAdapter_V1 extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    public List<ChannelMyListDTO> channelMyListDTOArrayList;
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

    public IChannelMyListOneColumnAdapter_V1 iChannelMyListOneColumnAdapter_V1;

    public ChannelMyListOneColumnAdapter_V1(Activity activity, List<ChannelMyListDTO> channelMyListDTOArrayList) {
        this.activity = activity;
        iChannelMyListOneColumnAdapter_V1 = (IChannelMyListOneColumnAdapter_V1) activity;
        this.channelMyListDTOArrayList = channelMyListDTOArrayList;
    }

    @Override
    public int getCount() {
        return channelMyListDTOArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return channelMyListDTOArrayList.get(position);
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
            convertView = inflater.inflate(R.layout.channel_my_list_one_column_item_v1, parent, false);
        }

        try {

            int textHeight = getHeight(activity, "TestValue", (int) 21f, imageWidth);

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

            convertView.findViewById(R.id.iv1).setTag(convertView.findViewById(R.id.iv1).getId(), channelMyListDTOArrayList.get(position).getSlug());

            String imageString = "";
            try {
                //imageString = spotLightCategoriesDTOList.get((position*2)).getSpotLightChannelDTOList().get(0).getImage();
                imageString = channelMyListDTOArrayList.get(position).getPoster();
                Log.d("iChannelMyListOneColumnAdapter_V1", "imageString==>"+imageString);
            } catch (Exception e) {
                e.printStackTrace();
            }

            imageString = CommonUtils.replaceDotstudioproWithMyspotlightForImage(imageString);

            String imageURLAddress = imageString;// + "/" + imageWidth + "/" + imageHeight;

            Log.d("ChannelMyListOneColumnAdapter_V1", "imageURLAddress==>"+imageURLAddress);
            Uri uri = Uri.parse(imageURLAddress);
            ((SimpleDraweeView) convertView.findViewById(R.id.iv1)).setImageURI(uri);
            convertView.findViewById(R.id.iv1).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickHandlerForChannelsImage(v.getTag(v.getId()).toString());//clickHandlerForChannelsImage(v.getTag().toString());
                }
            });

            setDeleteButtonUIandEventLitener(((ImageView)convertView.findViewById(R.id.deleteButton1)), position, 1);



            if(position == 0) {
                //convertView.findViewById(R.id.extraSpaceViewTop).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ApplicationConstants.TOP_PADDING_FOR_MAIN_ACTION_BAR));
                convertView.findViewById(R.id.extraSpaceViewTop).setVisibility(View.VISIBLE);
                //convertView.setBackgroundColor(activeColour);

                convertView.findViewById(R.id.extraSpaceViewGenre).setVisibility(View.GONE);
            } else if(position == channelMyListDTOArrayList.size()-1 && position > 2) {
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
        //imv.setImageDrawable(activity.getResources().getDrawable(R.drawable.heart_pink));
        imv.setImageDrawable(new IconDrawable(activity, FontAwesomeIcons.fa_heart).color(Color.parseColor("#D03B8F")));
        imv.setTag(imv.getId(), channelMyListDTOArrayList.get(pos).getId());

        imv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final View clickedView = view;
                AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(
                        activity);

                // Setting Dialog Title
                alertDialog2.setTitle("Popstar");

                // Setting Dialog Message
                alertDialog2.setMessage("Are you sure you want to remove this item from your list?");

                // Setting Positive "Yes" Btn
                alertDialog2.setPositiveButton("YES",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Write your code here to execute after dialog
                                iChannelMyListOneColumnAdapter_V1.deleteButtonClicked(clickedView.getTag(clickedView.getId()).toString());
                            }
                        });

                    // Setting Negative "NO" Btn
                alertDialog2.setNegativeButton("NO",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Write your code here to execute after dialog
                                dialog.cancel();
                            }
                        });

                // Showing Alert Dialog
                alertDialog2.show();
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
        iChannelMyListOneColumnAdapter_V1.channelMyListImageClickHandler(tagSent);
    }

    public interface IChannelMyListOneColumnAdapter_V1 {
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
