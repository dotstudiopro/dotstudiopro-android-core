package com.dotstudioz.dotstudioPRO.corelibrary.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dotstudioz.dotstudioPRO.corelibrary.R;
import com.dotstudioz.dotstudioPRO.corelibrary.constants.FontsConstants;
import com.dotstudioz.dotstudioPRO.models.dto.TwoMagazineDTO;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

/**
 * Created by Admin on 03-12-2014.
 */
public class MagazineAdapter_V1 extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private ArrayList<TwoMagazineDTO> magazineDTOListCollection;
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

    public IMagazineAdapter iMagazineAdapter;

    public MagazineAdapter_V1(Activity activity, ArrayList<TwoMagazineDTO> magazineDTOListCollection) {
        this.activity = activity;
        iMagazineAdapter = (IMagazineAdapter) activity;
        this.magazineDTOListCollection = magazineDTOListCollection;
    }

    @Override
    public int getCount() {
        return magazineDTOListCollection.size();
    }

    @Override
    public Object getItem(int position) {
        return magazineDTOListCollection.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public int gcd(int p, int q) {
        if (q == 0) return p;
        else return gcd(q, p % q);
    }
    public void ratio(int a, int b) {
        final int gcd = gcd(a,b);
        if(a > b) {
            showAnswer(a/gcd, b/gcd);
        } else {
            showAnswer(b/gcd, a/gcd);
        }
    }
    public void showAnswer(int a, int b) {
        ratioWidth = a;
        ratioHeight = b;
        System.out.println(a + " " + b);
    }

    public int ratioWidth = 0;
    public int ratioHeight = 0;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(convertView == null) {
            convertView = inflater.inflate(R.layout.magazine_item_v1, parent, false);
        }

        int imageFeaturedWidth = 0;
        int imageFeaturedHeight = 0;

        imageFeaturedWidth = magazineDTOListCollection.get(position).getMagazineDTO1().getMagazineImageWidth();
        imageFeaturedHeight = magazineDTOListCollection.get(position).getMagazineDTO1().getMagazineImageHeight();

        //calculating the ration from the width and height from the api
        ratio(imageFeaturedWidth, imageFeaturedHeight);

        int singleWidthUnit = displayMetrics.widthPixels/ratioWidth;
        int heightToUse = (displayMetrics.widthPixels * ratioHeight)/ratioWidth;
        //heightToUse = ((displayMetrics.widthPixels*9)/16);
        heightToUse = ((displayMetrics.widthPixels / 2) + ((displayMetrics.widthPixels / 2)/2));

                Log.d("MagazineAdapter_V1", "displayMetrics.widthPixels==>"+displayMetrics.widthPixels);
        Log.d("MagazineAdapter_V1", "ratioWidth==>"+ratioWidth);
        Log.d("MagazineAdapter_V1", "ratioHeight==>"+ratioHeight);
        Log.d("MagazineAdapter_V1", "singleWidthUnit==>"+singleWidthUnit);
        Log.d("MagazineAdapter_V1", "heightToUse==>"+heightToUse);

        if(magazineDTOListCollection.get(position).getMagazineDTO1().getMagazineTitle() != null &&
                magazineDTOListCollection.get(position).getMagazineDTO1().getMagazineTitle().length() > 0) {
            ((View) convertView.findViewById(R.id.secondMagazineLinearLayout)).getLayoutParams().width = (displayMetrics.widthPixels / 2);
            ((View) convertView.findViewById(R.id.secondMagazineItemImageView)).getLayoutParams().width = (displayMetrics.widthPixels / 2);
            ((View) convertView.findViewById(R.id.secondMagazineItemImageView)).getLayoutParams().height = heightToUse;

            ((TextView) convertView.findViewById(R.id.secondMagazineTitleTextView)).setTypeface(FontsConstants.robotMediumFont);
            ((TextView) convertView.findViewById(R.id.secondMagazineTitleTextView)).setText(magazineDTOListCollection.get(position).getMagazineDTO1().getMagazineTitle());

            convertView.findViewById(R.id.secondMagazineItemImageView).setTag(convertView.findViewById(R.id.secondMagazineItemImageView).getId(), magazineDTOListCollection.get(position).getMagazineDTO1().getMagazineTitle());
            convertView.findViewById(R.id.secondMagazineItemImageView).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    iMagazineAdapter.magazinePageItemClickHandler(view.getTag(view.getId()).toString());
                }
            });

            String imageURLAddress2 = magazineDTOListCollection.get(position).getMagazineDTO1().getMagazineImageURL();
            Log.d("MagazineAdapter_V1", "getMagazineDTO2 imageURLAddress==>"+imageURLAddress2);
            Uri uri2 = Uri.parse(imageURLAddress2);
            ((SimpleDraweeView) convertView.findViewById(R.id.secondMagazineItemImageView)).setImageURI(uri2);
        }

        if(magazineDTOListCollection.get(position).getMagazineDTO2().getMagazineTitle() != null &&
                magazineDTOListCollection.get(position).getMagazineDTO2().getMagazineTitle().length() > 0) {
            ((View) convertView.findViewById(R.id.thirdMagazineLinearLayout)).getLayoutParams().width = (displayMetrics.widthPixels / 2);
            ((View) convertView.findViewById(R.id.thirdMagazineItemImageView)).getLayoutParams().width = (displayMetrics.widthPixels / 2);
            ((View) convertView.findViewById(R.id.thirdMagazineItemImageView)).getLayoutParams().height = heightToUse;

            ((TextView) convertView.findViewById(R.id.thirdMagazineTitleTextView)).setTypeface(FontsConstants.robotMediumFont);
            ((TextView) convertView.findViewById(R.id.thirdMagazineTitleTextView)).setText(magazineDTOListCollection.get(position).getMagazineDTO2().getMagazineTitle());

            convertView.findViewById(R.id.thirdMagazineItemImageView).setTag(convertView.findViewById(R.id.thirdMagazineItemImageView).getId(), magazineDTOListCollection.get(position).getMagazineDTO2().getMagazineTitle());
            convertView.findViewById(R.id.thirdMagazineItemImageView).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    iMagazineAdapter.magazinePageItemClickHandler(view.getTag(view.getId()).toString());
                }
            });

            String imageURLAddress2 = magazineDTOListCollection.get(position).getMagazineDTO2().getMagazineImageURL();
            Log.d("MagazineAdapter_V1", "getMagazineDTO2 imageURLAddress==>"+imageURLAddress2);
            Uri uri2 = Uri.parse(imageURLAddress2);
            ((SimpleDraweeView) convertView.findViewById(R.id.thirdMagazineItemImageView)).setImageURI(uri2);
        }

        return convertView;
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
        return getCount();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    private void clickHandlerForChannelsImage(String tagSent) {
        //iNewsAdapter.genreImageClickHandler(tagSent);
    }

    public interface IMagazineAdapter {
        void magazinePageItemClickHandler(String magazineItemID);
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
