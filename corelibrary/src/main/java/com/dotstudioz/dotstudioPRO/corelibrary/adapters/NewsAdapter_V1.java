package com.dotstudioz.dotstudioPRO.corelibrary.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.text.Html;
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
import com.dotstudioz.dotstudioPRO.models.dto.ThreeNewsItemDTO;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Admin on 03-12-2014.
 */
public class NewsAdapter_V1 extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private ArrayList<ThreeNewsItemDTO> newsDTOListCollection;
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

    public INewsAdapter iNewsAdapter;

    public NewsAdapter_V1(Activity activity, ArrayList<ThreeNewsItemDTO> newsDTOListCollection) {
        this.activity = activity;
        iNewsAdapter = (INewsAdapter) activity;
        this.newsDTOListCollection = newsDTOListCollection;
    }

    @Override
    public int getCount() {
        return newsDTOListCollection.size();
    }

    @Override
    public Object getItem(int position) {
        return newsDTOListCollection.get(position);
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
            convertView = inflater.inflate(R.layout.news_item_v1, parent, false);
        }

        ((TextView)convertView.findViewById(R.id.topNewsDateTextView)).setTypeface(FontsConstants.latoRegular);
        ((TextView)convertView.findViewById(R.id.topNewsAuthorTextView)).setTypeface(FontsConstants.latoRegular);
        ((TextView)convertView.findViewById(R.id.topNewsTitleTextView)).setTypeface(FontsConstants.robotMediumFont);
        ((TextView)convertView.findViewById(R.id.topNewsDescriptionTextView)).setTypeface(FontsConstants.latoRegular);
        ((TextView)convertView.findViewById(R.id.topNewsReadAllTextView)).setTypeface(FontsConstants.latoRegular);

        ((TextView)convertView.findViewById(R.id.secondNewsDateTextView)).setTypeface(FontsConstants.latoRegular);
        ((TextView)convertView.findViewById(R.id.secondNewsAuthorTextView)).setTypeface(FontsConstants.latoRegular);
        ((TextView)convertView.findViewById(R.id.secondNewsTitleTextView)).setTypeface(FontsConstants.robotMediumFont);
        ((TextView)convertView.findViewById(R.id.secondNewsDescriptionTextView)).setTypeface(FontsConstants.latoRegular);
        ((TextView)convertView.findViewById(R.id.secondNewsReadAllTextView)).setTypeface(FontsConstants.latoRegular);

        ((TextView)convertView.findViewById(R.id.thirdNewsDateTextView)).setTypeface(FontsConstants.latoRegular);
        ((TextView)convertView.findViewById(R.id.thirdNewsAuthorTextView)).setTypeface(FontsConstants.latoRegular);
        ((TextView)convertView.findViewById(R.id.thirdNewsTitleTextView)).setTypeface(FontsConstants.robotMediumFont);
        ((TextView)convertView.findViewById(R.id.thirdNewsDescriptionTextView)).setTypeface(FontsConstants.latoRegular);
        ((TextView)convertView.findViewById(R.id.thirdNewsReadAllTextView)).setTypeface(FontsConstants.latoRegular);


        int imageFeaturedWidth = 0;
        int imageFeaturedHeight = 0;

        imageFeaturedWidth = newsDTOListCollection.get(position).getNewsDTO1().getNewsFeaturedImageWidth();
        imageFeaturedHeight = newsDTOListCollection.get(position).getNewsDTO1().getNewsFeaturedImageHeight();

        //calculating the ration from the width and height from the api
        ratio(imageFeaturedWidth, imageFeaturedHeight);

        int singleWidthUnit = displayMetrics.widthPixels/ratioWidth;
        int heightToUse = (displayMetrics.widthPixels * ratioHeight)/ratioWidth;
        heightToUse = ((displayMetrics.widthPixels*9)/16);

        Log.d("NewsAdapter_V1", "displayMetrics.widthPixels==>"+displayMetrics.widthPixels);
        Log.d("NewsAdapter_V1", "ratioWidth==>"+ratioWidth);
        Log.d("NewsAdapter_V1", "ratioHeight==>"+ratioHeight);
        Log.d("NewsAdapter_V1", "singleWidthUnit==>"+singleWidthUnit);
        Log.d("NewsAdapter_V1", "heightToUse==>"+heightToUse);

        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = (Date) formatter.parse(newsDTOListCollection.get(position).getNewsDTO1().getNewsDate());
            ((TextView)convertView.findViewById(R.id.topNewsDateTextView)).setText(date.toLocaleString().substring(0,date.toLocaleString().indexOf(':')-3));
        } catch(Exception e) {
            e.printStackTrace();
        }

        //((TextView)convertView.findViewById(R.id.topNewsDateTextView)).setText(newsDTOListCollection.get(position).getNewsDTO1().getNewsDate());
        ((TextView)convertView.findViewById(R.id.topNewsAuthorTextView)).setText(newsDTOListCollection.get(position).getNewsDTO1().getNewsAuthor());
        ((TextView)convertView.findViewById(R.id.topNewsTitleTextView)).setText(newsDTOListCollection.get(position).getNewsDTO1().getNewsTitle());
        ((TextView)convertView.findViewById(R.id.topNewsDescriptionTextView)).setText(Html.fromHtml(newsDTOListCollection.get(position).getNewsDTO1().getNewsExcerpt()));

        convertView.findViewById(R.id.topNewsReadAllTextView).setTag(convertView.findViewById(R.id.topNewsReadAllTextView).getId(), newsDTOListCollection.get(position).getNewsDTO1().getNewsId());
        convertView.findViewById(R.id.topNewsReadAllTextView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iNewsAdapter.newsPageItemClickHandler(view.getTag(view.getId()).toString());
            }
        });

        convertView.findViewById(R.id.topNewsTitleTextView).setTag(convertView.findViewById(R.id.topNewsTitleTextView).getId(), newsDTOListCollection.get(position).getNewsDTO1().getNewsId());
        convertView.findViewById(R.id.topNewsTitleTextView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iNewsAdapter.newsPageItemClickHandler(view.getTag(view.getId()).toString());
            }
        });

        convertView.findViewById(R.id.topNewsDescriptionTextView).setTag(convertView.findViewById(R.id.topNewsDescriptionTextView).getId(), newsDTOListCollection.get(position).getNewsDTO1().getNewsId());
        convertView.findViewById(R.id.topNewsDescriptionTextView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iNewsAdapter.newsPageItemClickHandler(view.getTag(view.getId()).toString());
            }
        });

        String imageURLAddress = newsDTOListCollection.get(position).getNewsDTO1().getNewsFeaturedImage();
        Uri uri = Uri.parse(imageURLAddress);
        Log.d("NewsAdapter_V1", "getNewsDTO1 imageURLAddress==>"+imageURLAddress);
        ((SimpleDraweeView) convertView.findViewById(R.id.topNewsItemImageView)).setImageURI(uri);
        ((View) convertView.findViewById(R.id.topNewsItemImageView)).getLayoutParams().height = heightToUse;

        convertView.findViewById(R.id.topNewsItemImageView).setTag(convertView.findViewById(R.id.topNewsItemImageView).getId(), newsDTOListCollection.get(position).getNewsDTO1().getNewsId());
        convertView.findViewById(R.id.topNewsItemImageView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iNewsAdapter.newsPageItemClickHandler(view.getTag(view.getId()).toString());
            }
        });

        if(newsDTOListCollection.get(position).getNewsDTO2().getNewsId() != null &&
                newsDTOListCollection.get(position).getNewsDTO2().getNewsId().length() > 0) {
            ((View) convertView.findViewById(R.id.secondNewsLinearLayout)).getLayoutParams().width = (displayMetrics.widthPixels / 2);
            ((View) convertView.findViewById(R.id.secondNewsItemImageView)).getLayoutParams().width = (displayMetrics.widthPixels / 2);
            ((View) convertView.findViewById(R.id.secondNewsItemImageView)).getLayoutParams().height = (heightToUse / 2);

            try {
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = (Date) formatter.parse(newsDTOListCollection.get(position).getNewsDTO2().getNewsDate());
                ((TextView)convertView.findViewById(R.id.secondNewsDateTextView)).setText(date.toLocaleString().substring(0,date.toLocaleString().indexOf(':')-3));
            } catch(Exception e) {
                e.printStackTrace();
            }
            //((TextView) convertView.findViewById(R.id.secondNewsDateTextView)).setText(newsDTOListCollection.get(position).getNewsDTO2().getNewsDate());
            ((TextView) convertView.findViewById(R.id.secondNewsAuthorTextView)).setText(newsDTOListCollection.get(position).getNewsDTO2().getNewsAuthor());
            ((TextView) convertView.findViewById(R.id.secondNewsTitleTextView)).setText(newsDTOListCollection.get(position).getNewsDTO2().getNewsTitle());
            ((TextView) convertView.findViewById(R.id.secondNewsDescriptionTextView)).setText(Html.fromHtml(newsDTOListCollection.get(position).getNewsDTO2().getNewsExcerpt()));

            convertView.findViewById(R.id.secondNewsReadAllTextView).setTag(convertView.findViewById(R.id.secondNewsReadAllTextView).getId(), newsDTOListCollection.get(position).getNewsDTO2().getNewsId());
            convertView.findViewById(R.id.secondNewsReadAllTextView).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    iNewsAdapter.newsPageItemClickHandler(view.getTag(view.getId()).toString());
                }
            });

            convertView.findViewById(R.id.secondNewsTitleTextView).setTag(convertView.findViewById(R.id.secondNewsTitleTextView).getId(), newsDTOListCollection.get(position).getNewsDTO2().getNewsId());
            convertView.findViewById(R.id.secondNewsTitleTextView).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    iNewsAdapter.newsPageItemClickHandler(view.getTag(view.getId()).toString());
                }
            });

            convertView.findViewById(R.id.secondNewsDescriptionTextView).setTag(convertView.findViewById(R.id.secondNewsDescriptionTextView).getId(), newsDTOListCollection.get(position).getNewsDTO2().getNewsId());
            convertView.findViewById(R.id.secondNewsDescriptionTextView).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    iNewsAdapter.newsPageItemClickHandler(view.getTag(view.getId()).toString());
                }
            });

            String imageURLAddress2 = newsDTOListCollection.get(position).getNewsDTO2().getNewsFeaturedImage();
            Log.d("NewsAdapter_V1", "getNewsDTO2 imageURLAddress==>"+imageURLAddress2);
            Uri uri2 = Uri.parse(imageURLAddress2);
            ((SimpleDraweeView) convertView.findViewById(R.id.secondNewsItemImageView)).setImageURI(uri2);

            convertView.findViewById(R.id.secondNewsItemImageView).setTag(convertView.findViewById(R.id.secondNewsItemImageView).getId(), newsDTOListCollection.get(position).getNewsDTO2().getNewsId());
            convertView.findViewById(R.id.secondNewsItemImageView).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    iNewsAdapter.newsPageItemClickHandler(view.getTag(view.getId()).toString());
                }
            });
        }

        if(newsDTOListCollection.get(position).getNewsDTO3().getNewsId() != null &&
                newsDTOListCollection.get(position).getNewsDTO3().getNewsId().length() > 0) {
            ((View) convertView.findViewById(R.id.thirdNewsItemImageView)).getLayoutParams().width = (displayMetrics.widthPixels / 2);
            ((View) convertView.findViewById(R.id.thirdNewsItemImageView)).getLayoutParams().width = (displayMetrics.widthPixels / 2);
            ((View) convertView.findViewById(R.id.thirdNewsItemImageView)).getLayoutParams().height = (heightToUse / 2);

            try {
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = (Date) formatter.parse(newsDTOListCollection.get(position).getNewsDTO3().getNewsDate());
                ((TextView)convertView.findViewById(R.id.thirdNewsDateTextView)).setText(date.toLocaleString().substring(0,date.toLocaleString().indexOf(':')-3));
            } catch(Exception e) {
                e.printStackTrace();
            }
            //((TextView) convertView.findViewById(R.id.thirdNewsDateTextView)).setText(newsDTOListCollection.get(position).getNewsDTO3().getNewsDate());
            ((TextView) convertView.findViewById(R.id.thirdNewsAuthorTextView)).setText(newsDTOListCollection.get(position).getNewsDTO3().getNewsAuthor());
            ((TextView) convertView.findViewById(R.id.thirdNewsTitleTextView)).setText(newsDTOListCollection.get(position).getNewsDTO3().getNewsTitle());
            ((TextView) convertView.findViewById(R.id.thirdNewsDescriptionTextView)).setText(Html.fromHtml(newsDTOListCollection.get(position).getNewsDTO3().getNewsExcerpt()));

            convertView.findViewById(R.id.thirdNewsReadAllTextView).setTag(convertView.findViewById(R.id.thirdNewsReadAllTextView).getId(), newsDTOListCollection.get(position).getNewsDTO3().getNewsId());
            convertView.findViewById(R.id.thirdNewsReadAllTextView).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    iNewsAdapter.newsPageItemClickHandler(view.getTag(view.getId()).toString());
                }
            });

            convertView.findViewById(R.id.thirdNewsTitleTextView).setTag(convertView.findViewById(R.id.thirdNewsTitleTextView).getId(), newsDTOListCollection.get(position).getNewsDTO3().getNewsId());
            convertView.findViewById(R.id.thirdNewsTitleTextView).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    iNewsAdapter.newsPageItemClickHandler(view.getTag(view.getId()).toString());
                }
            });

            convertView.findViewById(R.id.thirdNewsDescriptionTextView).setTag(convertView.findViewById(R.id.thirdNewsDescriptionTextView).getId(), newsDTOListCollection.get(position).getNewsDTO3().getNewsId());
            convertView.findViewById(R.id.thirdNewsDescriptionTextView).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    iNewsAdapter.newsPageItemClickHandler(view.getTag(view.getId()).toString());
                }
            });

            String imageURLAddress3 = newsDTOListCollection.get(position).getNewsDTO3().getNewsFeaturedImage();
            Log.d("NewsAdapter_V1", "getNewsDTO3 imageURLAddress==>"+imageURLAddress3);
            Uri uri3 = Uri.parse(imageURLAddress3);
            ((SimpleDraweeView) convertView.findViewById(R.id.thirdNewsItemImageView)).setImageURI(uri3);

            convertView.findViewById(R.id.thirdNewsItemImageView).setTag(convertView.findViewById(R.id.thirdNewsItemImageView).getId(), newsDTOListCollection.get(position).getNewsDTO3().getNewsId());
            convertView.findViewById(R.id.thirdNewsItemImageView).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    iNewsAdapter.newsPageItemClickHandler(view.getTag(view.getId()).toString());
                }
            });
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

    public interface INewsAdapter {
        void newsPageItemClickHandler(String newsItemID);
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
