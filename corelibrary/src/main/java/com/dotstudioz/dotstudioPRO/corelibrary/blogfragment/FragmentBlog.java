package com.dotstudioz.dotstudioPRO.corelibrary.blogfragment;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dotstudioz.dotstudioPRO.corelibrary.R;
import com.dotstudioz.dotstudioPRO.corelibrary.constants.FontsConstants;
import com.dotstudioz.dotstudioPRO.corelibrary.util.CommonCoreLibraryUtils;

import java.util.ArrayList;

/**
 * Use the {@link FragmentBlog#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentBlog extends Fragment {
    private HorizontalScrollView blogTitleHorizontalView;
    private LinearLayout blogTitleLinearLayout;

    // saving this instance so that it can be used to reference
    // and remove it from the parent activity
    Fragment thisFragment;

    public String active_color;
    public String non_active_color;

    public ArrayList<String> dataProviderArrayList = new ArrayList<>();

    public FragmentBlog() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment FragmentYesNo.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentBlog newInstance() {
        FragmentBlog fragment = new FragmentBlog();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blog, container, false);
        view.setBackgroundColor(Color.parseColor("#000000"));

        blogTitleHorizontalView = (HorizontalScrollView) view.findViewById(R.id.blogTitleHorizontalView);
        blogTitleLinearLayout = (LinearLayout) view.findViewById(R.id.blogTitleLinearLayout);

        blogTitleLinearLayout.requestFocus();

        thisFragment = this;

        return view;
    }

    public void populateSeasonNumberInLinearLayout() {
        for(int i = 0; i < dataProviderArrayList.size(); i++) {
            blogTitleLinearLayout.addView(addChildComponentToLinearLayout(dataProviderArrayList.get(i), "season"+i, ""+i, false));
        }
    }

    private FrameLayout addChildComponentToLinearLayout(String childTitle, String childTag, String seasonNumber, boolean isActive) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);

        FrameLayout childFrameLayout = new FrameLayout(getActivity());
        childFrameLayout.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT, Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL));

        int seasonTitleWidth = CommonCoreLibraryUtils.getTextWidth(getActivity(), seasonNumber, 14, displaymetrics.widthPixels, FontsConstants.didactgothicRegular, 10, 0, 12, 10);
        int widthForSeasonTitleToUse = 0;
        if(seasonTitleWidth <= ((displaymetrics.widthPixels*40)/100))
            widthForSeasonTitleToUse = seasonTitleWidth;
        else
            widthForSeasonTitleToUse = ((displaymetrics.widthPixels*40)/100);

        TextView tv = new TextView(getActivity());
        FrameLayout.LayoutParams tvparams = new FrameLayout.LayoutParams(widthForSeasonTitleToUse, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER);
        tv.setLayoutParams(tvparams);
        tv.setTypeface(FontsConstants.didactgothicRegular);
        tv.setPadding(10, 0, 12, 10);
        tv.setMaxLines(2);
        tv.setEllipsize(TextUtils.TruncateAt.END);
        //((FrameLayout.LayoutParams)tv.getLayoutParams()).setMargins(10, 0, 12, 10);
        tv.setTextColor(Color.BLACK);


        if (isActive) {
            //tv.setTextColor(ContextCompat.getColor(getActivity(), active_color));
            tv.setTextColor(Color.parseColor(active_color));
        } else {
            //tv.setTextColor(ContextCompat.getColor(getActivity(), non_active_color));
            tv.setTextColor(Color.parseColor(non_active_color));
        }

        tv.setText(seasonNumber);
        String TAGValue1 = childTag;
        tv.setTag(TAGValue1);
        tv.setTextSize(14);

        childFrameLayout.addView(tv);

        return childFrameLayout;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
