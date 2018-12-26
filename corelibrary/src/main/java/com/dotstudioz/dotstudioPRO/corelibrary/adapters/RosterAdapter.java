package com.dotstudioz.dotstudioPRO.corelibrary.adapters;

import android.animation.LayoutTransition;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dotstudioz.dotstudioPRO.corelibrary.R;
import com.dotstudioz.dotstudioPRO.services.constants.ApplicationConstants;
import com.dotstudioz.dotstudioPRO.corelibrary.constants.FontsConstants;
import com.dotstudioz.dotstudioPRO.models.dto.SpotLightChannelDTO;
import com.dotstudioz.dotstudioPRO.corelibrary.util.CommonUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;
import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;

import java.util.List;

/**
 * Created by Admin on 03-12-2014.
 */
public class RosterAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<SpotLightChannelDTO> spotLightChannelDTOList;
    public GenericDraweeHierarchy hierarchy;
    public int imageWidth;
    public int imageHeight;
    public boolean tabletFlag;
    public int informationIconWidth;
    public int informationIconHeight;

    public IRosterAdapter iRosterAdapter;

    public RosterAdapter(Activity activity, List<SpotLightChannelDTO> spotLightChannelDTOList) {
        this.activity = activity;
        iRosterAdapter = (IRosterAdapter) activity;
        this.spotLightChannelDTOList = spotLightChannelDTOList;
    }

    @Override
    public int getCount() {
        return spotLightChannelDTOList.size();
    }

    @Override
    public Object getItem(int position) {
        return spotLightChannelDTOList.get(position);
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
            convertView = inflater.inflate(R.layout.roster_item, parent, false);
        }

        //((LinearLayout)convertView).addView(getRosterImage(imageWidth, imageHeight, spotLightChannelDTOList.get(position), tabletFlag));

        try {
            RelativeLayout rosterImageRL = (RelativeLayout) convertView.findViewById(R.id.rosterImageRL);
            if (tabletFlag)
                rosterImageRL.setLayoutParams(new LinearLayout.LayoutParams(imageWidth, LinearLayout.LayoutParams.WRAP_CONTENT));
            else
                rosterImageRL.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

            //((RelativeLayout.LayoutParams) rosterImageRL.getLayoutParams()).addRule(RelativeLayout.ALIGN_PARENT_RIGHT | RelativeLayout.ALIGN_PARENT_BOTTOM);

            convertView.findViewById(R.id.rosterItemProgressBar).setPadding(0, ((imageHeight / 2) - 60), 0, 0);

            RelativeLayout.LayoutParams params1 = null;
            if (tabletFlag)
                params1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, imageHeight);
            else
                params1 = new RelativeLayout.LayoutParams(imageWidth, imageHeight);
            //convertView.findViewById(R.id.iv1).setPadding(3, 3, 3, 3);
            convertView.findViewById(R.id.iv1).setLayoutParams(params1);
            convertView.findViewById(R.id.iv1).setClickable(true);
            //iv1.setTag(((CategoriesDTO) categoriesDTOArrayList.get(i)).getChannelImage());
            String channelImagePlusCategorySlug = "";
            channelImagePlusCategorySlug = spotLightChannelDTOList.get(position).getId() + "|" + "roster";
            convertView.findViewById(R.id.iv1).setTag(convertView.findViewById(R.id.iv1).getId(), channelImagePlusCategorySlug);
            //System.out.println("ImageData:"+categoriesDTO.getChannelSpotlightImage());
            String imageString = spotLightChannelDTOList.get(position).getSpotlightImage();
            imageString = CommonUtils.replaceDotstudioproWithMyspotlightForImage(imageString);
            String imageURLAddress = imageString + "/" + imageWidth + "/" + imageHeight;
            //System.out.println("ImageData:" + imageURLAddress);
            Uri uri = Uri.parse(imageURLAddress);
            ((SimpleDraweeView) convertView.findViewById(R.id.iv1)).setImageURI(uri);
            convertView.findViewById(R.id.iv1).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickHandlerForChannelsImage(v.getTag(v.getId()).toString());//clickHandlerForChannelsImage(v.getTag().toString());
                }
            });

            ((LinearLayout) convertView.findViewById(R.id.informationLL)).setLayoutTransition(new LayoutTransition());
            LinearLayout.LayoutParams informationLLParams = (LinearLayout.LayoutParams) convertView.findViewById(R.id.informationLL).getLayoutParams();
            informationLLParams.setMargins(0, 0, 0, 0);

            final ImageView informationIV2 = ((ImageView) convertView.findViewById(R.id.informationIV2));
            informationIV2.setImageDrawable(new IconDrawable(activity, FontAwesomeIcons.fa_info_circle).color(Color.parseColor("#FF0065")));

            TextView channelTitleTV = ((TextView) convertView.findViewById(R.id.channelTitleTV));
            channelTitleTV.setText(spotLightChannelDTOList.get(position).getTitle());
            channelTitleTV.setTypeface(FontsConstants.gothamBold);
            channelTitleTV.setSingleLine(true);
            channelTitleTV.setPadding(30, 10, 30, 0);
            channelTitleTV.setEllipsize(TextUtils.TruncateAt.END);
            channelTitleTV.setTextSize(22f);
            channelTitleTV.setGravity(Gravity.LEFT);
            channelTitleTV.setTextColor(Color.WHITE);
            channelTitleTV.setIncludeFontPadding(false);

            final TextView channelDescriptionTV = ((TextView) convertView.findViewById(R.id.channelDescriptionTV));
            ((TextView) convertView.findViewById(R.id.channelTitleTV)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (channelDescriptionTV.getVisibility() == View.GONE) {
                        channelDescriptionTV.setVisibility(View.VISIBLE);
                        channelDescriptionTV.animate().alpha(1.0f);
                        informationIV2.setImageDrawable(new IconDrawable(activity, FontAwesomeIcons.fa_close).color(Color.parseColor("#FF0065")));
                    } else {
                        channelDescriptionTV.setVisibility(View.GONE);
                        channelDescriptionTV.animate().alpha(0f);
                        informationIV2.setImageDrawable(new IconDrawable(activity, FontAwesomeIcons.fa_info_circle).color(Color.parseColor("#FF0065")));
                    }
                }
            });
            informationIV2.setLayoutParams(new LinearLayout.LayoutParams(informationIconWidth, informationIconHeight));

            channelDescriptionTV.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            channelDescriptionTV.setText(spotLightChannelDTOList.get(position).getChannelDescription());
            channelDescriptionTV.setTypeface(FontsConstants.verdanaRegular);
            channelDescriptionTV.setSingleLine(false);
            channelDescriptionTV.setAlpha(0.0f);
            channelDescriptionTV.setPadding(30, 10, 30, 0);
            channelDescriptionTV.setEllipsize(TextUtils.TruncateAt.END);
            channelDescriptionTV.setTextSize(16f);
            channelDescriptionTV.setTextColor(Color.WHITE);
            channelDescriptionTV.setVisibility(View.GONE);
            channelDescriptionTV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (channelDescriptionTV.getVisibility() == View.GONE) {
                        channelDescriptionTV.setVisibility(View.VISIBLE);
                        channelDescriptionTV.animate().alpha(1.0f);
                        informationIV2.setImageDrawable(new IconDrawable(activity, FontAwesomeIcons.fa_close).color(Color.parseColor("#FF0065")));
                    } else {
                        channelDescriptionTV.setVisibility(View.GONE);
                        channelDescriptionTV.animate().alpha(0f);
                        informationIV2.setImageDrawable(new IconDrawable(activity, FontAwesomeIcons.fa_info_circle).color(Color.parseColor("#FF0065")));
                    }
                }
            });

            informationIV2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (channelDescriptionTV.getVisibility() == View.GONE) {
                        channelDescriptionTV.setVisibility(View.VISIBLE);
                        channelDescriptionTV.animate().alpha(1.0f);
                        informationIV2.setImageDrawable(new IconDrawable(activity, FontAwesomeIcons.fa_close).color(Color.parseColor("#FF0065")));
                    } else {
                        channelDescriptionTV.setVisibility(View.GONE);
                        channelDescriptionTV.animate().alpha(0f);
                        informationIV2.setImageDrawable(new IconDrawable(activity, FontAwesomeIcons.fa_info_circle).color(Color.parseColor("#FF0065")));
                    }
                }
            });

            if(position == 0) {
                convertView.findViewById(R.id.extraSpaceViewTopRoster).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ApplicationConstants.TOP_PADDING_FOR_MAIN_ACTION_BAR));
                convertView.findViewById(R.id.extraSpaceViewTopRoster).setVisibility(View.VISIBLE);
                convertView.findViewById(R.id.extraSpaceViewRoster).setVisibility(View.GONE);
            } else if(position == spotLightChannelDTOList.size()-1) {
                convertView.findViewById(R.id.extraSpaceViewTopRoster).setVisibility(View.GONE);
                convertView.findViewById(R.id.extraSpaceViewRoster).setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ApplicationConstants.BOTTOM_PADDING_FOR_TAB_BAR));
                convertView.findViewById(R.id.extraSpaceViewRoster).setVisibility(View.VISIBLE);
            } else {
                convertView.findViewById(R.id.extraSpaceViewRoster).setVisibility(View.GONE);
            }
        } catch(Exception e) {
            //System.out.println("HHHHHHHHHHHJJJJJJJJJJJJJKKKKKKKKKKKKK");
            //e.printStackTrace();
        }


        return convertView;
    }

    private void clickHandlerForChannelsImage(String tagSent) {
        iRosterAdapter.rosterImageClickHandler(tagSent);
    }

    public interface IRosterAdapter {
        void rosterImageClickHandler(String tagSent);
    }
}
