package com.dotstudioz.dotstudioPRO.corelibrary.components;

import android.content.Context;
import android.graphics.Color;
import android.graphics.SurfaceTexture;
import android.graphics.Typeface;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dotstudioz.dotstudioPRO.corelibrary.R;
import com.dotstudioz.dotstudioPRO.corelibrary.util.CommonUtils;
import com.dotstudioz.dotstudioPRO.dsplayer.player.assets.AssetsVideoSource;
import com.dotstudioz.dotstudioPRO.services.constants.ApplicationConstants;

import com.dotstudioz.dotstudioPRO.dsplayer.player.PlayControlControllerView;
import com.dotstudioz.dotstudioPRO.dsplayer.player.VideoSource;
import com.dotstudioz.dotstudioPRO.dsplayer.player.VideoTexturePresenter;
import com.dotstudioz.dotstudioPRO.dsplayer.player.VideoTextureView;
import com.dotstudioz.dotstudioPRO.dsplayer.player.hls.HlsVideoSource;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.exoplayer.ExoPlayer;
import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;

/**
 * Created by mohsin on 15-12-2016.
 */

public class PopularCategoriesFragmentNewPlayer_V1 extends Fragment implements TextureView.SurfaceTextureListener {
    public PopularCategoriesFragmentNewPlayer_V1() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private View view;
    private String blogID;
    private String blogType;
    private String videoID;
    private String categorySlug;
    private String channelSlug;
    private boolean titleFontSizeEnabled;
    private float titleFontSize;
    private String title;
    private String titleLogo;
    private String desc;
    private String videoPosterURL;
    public boolean showPosterAndNotPlayVideo;
    public String colorVaule;
    private boolean isDancingArrowVisible = false;
    private int indexOfThisPopularCategoriesFragment = 0;

    public SimpleDraweeView imageView;
    public ProgressBar busyCursorProgressBar;
    public TextView titleTextView;
    public WrapContentDraweeView titleImageView;
    public TextView descTextView;
    public ImageView muteImageView;
    public TextView tapToLaunchTextView;
    public ImageView tapToLaunchImageView;
    public boolean enableTapToLaunchListener = false;
    public LinearLayout titleParentContainerLL;
    public RelativeLayout titleParentParentContainerRL;
    public boolean isAlreadyStartedOnce = false;

    public String videoToPlayURL = "";
    public int screenWidth;
    public int screenHeight;
    public boolean isTopBarVisible = false;
    public boolean isBottomBarVisible = true;
    public VideoTexturePresenter videoTexturePresenter;
    public MediaController mediaController;
    public PlayControlControllerView playControlControllerView;
    public FrameLayout videoTextureViewFrameLayout;
    public VideoTextureView videoTextureView;
    public boolean playerDimensionsUpdated = false;
    public int lastSeekedPosition = 0;
    public int topPadding = 0;
    public int bottomPadding = 0;

    public int tapToLaunchColor;
    public int volumeButtonColor;
    public int busyCursorColor;
    public Typeface tapToLaunchTypeFace;
    public Typeface titleTypeFace;
    public Typeface descTypeFace;

    public OnFragmentInteractionListener mListener;

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void popularCategoriesClickHandler(String categorySlug, String channelSlug, String videoID);
        void popularCategoriesClickHandler(String blogID, String blogType);
        void popularCategoriesMoveHandler(String direction);
        void changeTheOffscreenLimit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.popular_categories_item, container, false);
        /*ImageView upArrowImageView = (ImageView) view.findViewById(R.id.upArrowImageView);
        upArrowImageView.setImageDrawable(new IconDrawable(getActivity(), FontAwesomeIcons.fa_angle_up).color(Color.parseColor("#eeeeee")));
        Animation mAnimation = new TranslateAnimation(
                TranslateAnimation.ABSOLUTE, 0f,
                TranslateAnimation.ABSOLUTE, 0f,
                TranslateAnimation.RELATIVE_TO_SELF, 0f,
                TranslateAnimation.RELATIVE_TO_SELF, 1f);
        //TranslateAnimation.RELATIVE_TO_SELF, 0.4f);
        mAnimation.setDuration(600);
        mAnimation.setRepeatCount(-1);
        mAnimation.setRepeatMode(Animation.REVERSE);
        mAnimation.setInterpolator(new LinearInterpolator());
        upArrowImageView.setAnimation(mAnimation);
        upArrowImageView.setDrawingCacheEnabled(true);
        upArrowImageView.buildDrawingCache();

        if(isDancingArrowVisible)
            upArrowImageView.setVisibility(View.VISIBLE);
        else
            upArrowImageView.setVisibility(View.GONE);
        upArrowImageView.setVisibility(View.GONE);*/

        /*((TextView)view.findViewById(R.id.swipeUpTextView)).setTypeface(FontsConstants.sourcesansproRegular);*/

        try {
            if(categorySlug == null || channelSlug == null || videoID == null
                    || categorySlug.length() == 0 || channelSlug.length() == 0 || videoID.length() == 0) {
                if(blogID != null && blogID.length() > 0 &&
                        blogType != null && blogType.length() > 0) {
                    enableTapToLaunchListener = true;
                } else {
                    enableTapToLaunchListener = false;
                }
            } else {
                enableTapToLaunchListener = true;
            }
        } catch(Exception e) {
            enableTapToLaunchListener = false;
        }

        imageView = (SimpleDraweeView) view.findViewById(R.id.popularCategoriesImageView);
        busyCursorProgressBar = (ProgressBar) view.findViewById(R.id.busyCursorProgressBar);
        busyCursorProgressBar.setVisibility(View.VISIBLE);
        busyCursorProgressBar.getIndeterminateDrawable().setColorFilter(busyCursorColor, android.graphics.PorterDuff.Mode.MULTIPLY);
        if(enableTapToLaunchListener) {
            //imageView.setOnClickListener(popularCategoriesClickHandler);
        }
        if(showPosterAndNotPlayVideo) {
            Uri uri = Uri.parse((titleLogo!=null)?"https://images.dotstudiopro.com/"+titleLogo:"");
            imageView.setImageURI(uri);
        } else {
            Uri uri = Uri.parse((videoPosterURL != null) ? videoPosterURL : "");
            imageView.setImageURI(uri);
        }

        if(enableTapToLaunchListener) {
            imageView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                        x1 = motionEvent.getX();
                        y1 = motionEvent.getY();
                        touchDown = true;
                        return true;
                    }
                    else if(motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
                        Log.d("enabledToLaunch", "Moving");
                        x2 = motionEvent.getX();
                        y2 = motionEvent.getY();
                        dx = x2-x1;
                        dy = y2-y1;

                        // Use dx and dy to determine the direction of the move
                        if(Math.abs(dx) > Math.abs(dy)) {
                            if(dx>0) {
                                //direction = "right";
                            } else {
                                //direction = "left";
                            }
                        } else {
                            Log.d("enabledToLaunch", "dy==>"+dy);
                            if(dy>100) {
                                //down
                                if(touchDown) {
                                    mListener.popularCategoriesMoveHandler("up");
                                    touchDown = false;
                                }
                            } else if(dy<-100) {
                                //up
                                if(touchDown) {
                                    mListener.popularCategoriesMoveHandler("down");
                                    touchDown = false;
                                }
                            }
                        }
                        return true;
                    }
                    else if(motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        Log.d("enabledToLaunch", "ACTION_UP");
                        if(touchDown) {
                            if (categorySlug != null && channelSlug != null && videoID != null
                                    && categorySlug.length() > 0 && channelSlug.length() > 0 && videoID.length() > 0) {
                                try {
                                    videoTexturePresenter.pause();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                System.out.println("categorySlug, channelSlug, videoID==>" + categorySlug + "," + channelSlug + "," + videoID);
                                mListener.popularCategoriesClickHandler(categorySlug, channelSlug, videoID);
                            } else if (blogID != null && blogID.length() > 0 &&
                                    blogType != null && blogType.length() > 0) {
                                System.out.println("blogID, blogType==>" + blogID + "," + blogType);
                                mListener.popularCategoriesClickHandler(blogID, blogType);
                            }
                        }
                        return true;
                    }


                    return false;
                }
            });
            //imageView.setOnClickListener(popularCategoriesClickHandler);
        }

        tapToLaunchTextView = (TextView) view.findViewById(R.id.tapToLaunchTextView);
        tapToLaunchTextView.setShadowLayer(2f, -1, 1, Color.BLACK);
        tapToLaunchTextView.setTypeface(tapToLaunchTypeFace);
        if(enableTapToLaunchListener) {
            tapToLaunchTextView.setVisibility(View.VISIBLE);
            tapToLaunchTextView.setOnClickListener(popularCategoriesClickHandler);
        } else {
            tapToLaunchTextView.setVisibility(View.GONE);
        }
        tapToLaunchTextView.setTextSize(12f);

        tapToLaunchImageView = (ImageView) view.findViewById(R.id.tapToLaunchImageView);
        tapToLaunchTextView.setTextColor(tapToLaunchColor);
        tapToLaunchTextView.setText("Tap to learn more");
        //tapToLaunchImageView.setImageDrawable(new IconDrawable(getActivity(), FontAwesomeIcons.fa_dot_circle_o).color(volumeButtonColor));
        tapToLaunchImageView.setImageDrawable(new IconDrawable(getActivity(), FontAwesomeIcons.fa_dot_circle_o).color(tapToLaunchColor));
        if(enableTapToLaunchListener) {
            tapToLaunchImageView.setVisibility(View.VISIBLE);
            tapToLaunchImageView.setOnClickListener(popularCategoriesClickHandler);
        } else {
            tapToLaunchImageView.setVisibility(View.GONE);
        }


        muteImageView = (ImageView) view.findViewById(R.id.muteImageView);
        muteImageView.setImageDrawable(new IconDrawable(getActivity(), FontAwesomeIcons.fa_volume_off).color(volumeButtonColor));

        try {
            if (ApplicationConstants.isMute) {
                muteImageView.setImageDrawable(new IconDrawable(getActivity(), FontAwesomeIcons.fa_volume_off).color(volumeButtonColor));
                if (videoTexturePresenter != null) {
                    videoTexturePresenter.setMute(true);
                }
            } else {
                muteImageView.setImageDrawable(new IconDrawable(getActivity(), FontAwesomeIcons.fa_volume_up).color(volumeButtonColor));
                if (videoTexturePresenter != null) {
                    videoTexturePresenter.setMute(false);
                }
            }
        } catch(Exception e) {}

        muteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(videoTexturePresenter != null && videoTexturePresenter.isPlaying()) {
                        if (ApplicationConstants.isMute) {
                            muteImageView.setImageDrawable(new IconDrawable(getActivity(), FontAwesomeIcons.fa_volume_up).color(volumeButtonColor));
                            if (videoTexturePresenter != null) {
                                videoTexturePresenter.setMute(false);
                                ApplicationConstants.isMute = false;
                            }
                        } else {
                            muteImageView.setImageDrawable(new IconDrawable(getActivity(), FontAwesomeIcons.fa_volume_off).color(volumeButtonColor));
                            if (videoTexturePresenter != null) {
                                videoTexturePresenter.setMute(true);
                                ApplicationConstants.isMute = true;
                            }
                        }
                    }
                } catch(Exception e) {}
            }
        });

        titleParentContainerLL = (LinearLayout) view.findViewById(R.id.titleParentContainerLL);
        titleParentContainerLL.setOnClickListener(popularCategoriesClickHandler);
        titleParentParentContainerRL = (RelativeLayout) view.findViewById(R.id.titleParentParentContainerRL);
        titleParentParentContainerRL.setOnClickListener(popularCategoriesClickHandler);
        titleParentParentContainerRL.setPadding(0, 0, bottomPadding/2, bottomPadding);

        titleTextView = (TextView) view.findViewById(R.id.titleTextView);
        titleTextView.setText(title);
        if(titleFontSizeEnabled)
            titleTextView.setTextSize(titleFontSize);
        titleTextView.setShadowLayer(2f, -1, 1, Color.BLACK);
        titleTextView.setTextColor(Color.WHITE);
        titleTextView.setTypeface(titleTypeFace);
        if(enableTapToLaunchListener) {
            titleTextView.setOnClickListener(popularCategoriesClickHandler);
        }

        titleImageView = (WrapContentDraweeView) view.findViewById(R.id.titleImageView);
        if(titleLogo != null && titleLogo.length() > 0) {
            titleImageView.setLayoutParams(new LinearLayout.LayoutParams(screenWidth/2, LinearLayout.LayoutParams.WRAP_CONTENT));
            //titleImageView.setScaleType(ImageView.ScaleType.FIT_START);
            //titleImageView.setBackgroundColor(Color.LTGRAY);
            Uri titleLogoURI = Uri.parse("https://images.dotstudiopro.com/"+titleLogo);
            titleImageView.setImageURI(titleLogoURI);
            if(enableTapToLaunchListener) {
                titleImageView.setOnClickListener(popularCategoriesClickHandler);
            }

            titleImageView.setVisibility(View.VISIBLE);
            titleTextView.setVisibility(View.GONE);
        } else {
            titleImageView.setVisibility(View.GONE);
            titleTextView.setVisibility(View.VISIBLE);
        }

        descTextView = (TextView) view.findViewById(R.id.descTextView);
        descTextView.setText(desc);
        descTextView.setShadowLayer(2f, -1, 1, Color.BLACK);
        descTextView.setTextColor(Color.WHITE);
        descTextView.setTypeface(descTypeFace);
        if(enableTapToLaunchListener) {
            descTextView.setOnClickListener(popularCategoriesClickHandler);
        }




        if(showPosterAndNotPlayVideo && titleLogo != null && titleLogo.length() > 0) {
            titleImageView.setVisibility(View.GONE);
            busyCursorProgressBar.setVisibility(View.GONE);
        } else {
            videoTextureViewFrameLayout = (FrameLayout) view.findViewById(R.id.videoTextureViewFrameLayout);
            /*videoTextureView = (VideoTextureView) view.findViewById(R.id.videoTextureView);
            videoTextureView.setSurfaceTextureListener(this);*/

            initializeVideoTexturePresenterPlayer();
        }
        return view;
    }

    public void reInitializeVideoTexturePresenterPlayer() {
        /*videoTextureViewFrameLayout = (FrameLayout) view.findViewById(R.id.videoTextureViewFrameLayout);
        videoTextureView = (VideoTextureView) view.findViewById(R.id.videoTextureView);
        videoTextureView.setSurfaceTextureListener(this);*/

        Log.d("PopCatFraNPla", "reInitializeVideoTexturePresenterPlayer() called!!!");

        initializeVideoTexturePresenterPlayer();
    }
    public boolean touchDown = false;
    public float x1 = 0;
    public float x2 = 0;
    public float y1 = 0;
    public float y2 = 0;
    public float dx = 0;
    public float dy = 0;
    private void initializeVideoTexturePresenterPlayer() {
        videoTextureViewFrameLayout.removeAllViews();
        videoTextureView = new VideoTextureView(getContext());
        videoTextureView.setSurfaceTextureListener(this);
        videoTextureViewFrameLayout.addView(videoTextureView);

        videoTextureView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {


                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    x1 = motionEvent.getX();
                    y1 = motionEvent.getY();
                    touchDown = true;
                    return true;
                }
                else if(motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
                    Log.d("PopCatFraNPla", "Moving");
                    x2 = motionEvent.getX();
                    y2 = motionEvent.getY();
                    dx = x2-x1;
                    dy = y2-y1;

                    // Use dx and dy to determine the direction of the move
                    if(Math.abs(dx) > Math.abs(dy)) {
                        if(dx>0) {
                            //direction = "right";
                        } else {
                            //direction = "left";
                        }
                    } else {
                        Log.d("PopCatFraNPla", "dy==>"+dy);
                        if(dy>100) {
                            //down
                            if(touchDown) {
                                mListener.popularCategoriesMoveHandler("up");
                                touchDown = false;
                            }
                        } else if(dy<-100) {
                            //up
                            if(touchDown) {
                                mListener.popularCategoriesMoveHandler("down");
                                touchDown = false;
                            }
                        }
                    }
                    return true;
                }
                else if(motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    Log.d("PopCatFraNPla", "ACTION_UP");
                    if(touchDown) {
                        if (categorySlug != null && channelSlug != null && videoID != null
                                && categorySlug.length() > 0 && channelSlug.length() > 0 && videoID.length() > 0) {
                            try {
                                videoTexturePresenter.pause();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            System.out.println("categorySlug, channelSlug, videoID==>" + categorySlug + "," + channelSlug + "," + videoID);
                            mListener.popularCategoriesClickHandler(categorySlug, channelSlug, videoID);
                        } else if (blogID != null && blogID.length() > 0 &&
                                blogType != null && blogType.length() > 0) {
                            System.out.println("blogID, blogType==>" + blogID + "," + blogType);
                            mListener.popularCategoriesClickHandler(blogID, blogType);
                        }
                    }
                    return true;
                }

                return false;
            }
        });
        //videoTextureView.setOnClickListener(popularCategoriesClickHandler);

        videoTexturePresenter = new VideoTexturePresenter(videoTextureView);
        videoTexturePresenter.onCreate();
        /*mediaController = new MediaController(getContext());
        mediaController.setAnchorView(videoTextureView);*/
        /*playControlControllerView = new PlayControlControllerView(getContext());
        playControlControllerView.setAnchorView(videoTextureViewFrameLayout);*/
        //videoTexturePresenter.setMediaControllerForThePlayer(mediaController);
        //videoTexturePresenter.setMediaControllerForThePlayer(playControlControllerView);


        VideoSource source;
        if(videoToPlayURL.substring((videoToPlayURL.length()-4), videoToPlayURL.length()).equalsIgnoreCase("m3u8")) {
            source = HlsVideoSource
                    .newBuilder(Uri.parse(videoToPlayURL), "UserAgent")
                    .bufferSegmentSize(64 * 1024)
                    .bufferSegmentCount(256)
                    .eventHandler(new Handler())
                    .build();
        } else {
            source = AssetsVideoSource
                    //.newBuilder(Uri.parse(convertToURLEscapingIllegalCharacters(videoUrl).toString()), "UserAgent")
                    .newBuilder(Uri.parse(CommonUtils.getInstance().attachHttpsProtocolToUrl(videoToPlayURL.toString())), "UserAgent")
                    .bufferSegmentSize(64 * 1024)
                    .bufferSegmentCount(256)
                    .build();
        }



        videoTexturePresenter.setSource(source);
        //videoTexturePresenter.prepare();
        videoTexturePresenter.addOnErrorListener(new VideoTexturePresenter.OnErrorListener() {
            @Override
            public void onError(Exception e) {
                //System.out.println("onError .... Caught inside the popular categories fragment number==>"+getIndexOfThisPopularCategoriesFragment());
                e.printStackTrace();
                Log.d("PopCatFraNPla", "videoToPlayURL ==>"+videoToPlayURL);
                Log.d("PopCatFraNPla", "Inside onErroristener ==>"+e.getMessage());
                try {
                    if (e != null && e.getMessage() != null && e.getMessage().equalsIgnoreCase("Response code: 403")) {
                        videoToPlayURL = videoToPlayURL.replace("m3u8", "mp4");
                        VideoSource source = AssetsVideoSource
                                //.newBuilder(Uri.parse(convertToURLEscapingIllegalCharacters(videoUrl).toString()), "UserAgent")
                                .newBuilder(Uri.parse(CommonUtils.getInstance().attachHttpsProtocolToUrl(videoToPlayURL.toString())), "UserAgent")
                                .bufferSegmentSize(64 * 1024)
                                .bufferSegmentCount(256)
                                .build();
                        videoTexturePresenter.setSource(source);
                    }
                } catch(Exception ee) {
                    ee.printStackTrace();
                }
                if (ApplicationConstants.CURRNET_LOADED_VIEW == ApplicationConstants.HOME_VIEW &&
                        ApplicationConstants.currentlySelectedPopularCategoriesPage == getIndexOfThisPopularCategoriesFragment()) {
                    reInitializeVideoTexturePresenterPlayer();
                    //videoTexturePresenter.getPlayer().setSelectedTrack(0, 2);
                    videoTexturePresenter.play();
                    /*videoTexturePresenter.release();
                    videoTexturePresenter.prepare();*/
                }
            }
        });
        videoTexturePresenter.addOnVideoSizeChangedListener(new VideoTexturePresenter.OnVideoSizeChangedListener() {
            @Override
            public void onVideoSizeChanged(int width, int height, float pixelWidthHeightRatio) {
                System.out.println("onVideoSizeChanged");
                System.out.println("width==>"+width);
                System.out.println("height==>"+height);
                System.out.println("pixelWidthHeightRatio==>"+pixelWidthHeightRatio);
                //System.out.println(width+"<==width----height==>"+height+" for fragment number==>"+getIndexOfThisPopularCategoriesFragment());
                mVideoWidth = width;
                mVideoHeight = height;

                System.out.println("ApplicationConstants.TOP_PADDING_FOR_MAIN_ACTION_BAR==>"+ApplicationConstants.TOP_PADDING_FOR_MAIN_ACTION_BAR);
                System.out.println("ApplicationConstants.BOTTOM_PADDING_FOR_TAB_BAR==>"+ApplicationConstants.BOTTOM_PADDING_FOR_TAB_BAR);
                int screenHeightToBeUsed = screenHeight;
                if(isTopBarVisible && isBottomBarVisible) {
                    if (ApplicationConstants.TOP_PADDING_FOR_MAIN_ACTION_BAR > 0 && ApplicationConstants.BOTTOM_PADDING_FOR_TAB_BAR > 0) {
                        screenHeightToBeUsed = (screenHeight - (ApplicationConstants.BOTTOM_PADDING_FOR_TAB_BAR + ApplicationConstants.BOTTOM_PADDING_FOR_TAB_BAR + (ApplicationConstants.BOTTOM_PADDING_FOR_TAB_BAR / 4)));
                    } else if (ApplicationConstants.TOP_PADDING_FOR_MAIN_ACTION_BAR > 0 && ApplicationConstants.BOTTOM_PADDING_FOR_TAB_BAR < 1) {
                        screenHeightToBeUsed = (screenHeight - (ApplicationConstants.TOP_PADDING_FOR_MAIN_ACTION_BAR + ApplicationConstants.TOP_PADDING_FOR_MAIN_ACTION_BAR));
                    } else if (ApplicationConstants.BOTTOM_PADDING_FOR_TAB_BAR > 0 && ApplicationConstants.TOP_PADDING_FOR_MAIN_ACTION_BAR < 1) {
                        screenHeightToBeUsed = (screenHeight - (ApplicationConstants.BOTTOM_PADDING_FOR_TAB_BAR + ApplicationConstants.BOTTOM_PADDING_FOR_TAB_BAR));
                    } else if (ApplicationConstants.BOTTOM_PADDING_FOR_TAB_BAR < 1 && ApplicationConstants.TOP_PADDING_FOR_MAIN_ACTION_BAR < 1) {
                        screenHeightToBeUsed = ((screenHeight * 15) / 100);
                    }
                } else if(!isTopBarVisible && isBottomBarVisible) {
                    if (ApplicationConstants.BOTTOM_PADDING_FOR_TAB_BAR > 0) {
                        screenHeightToBeUsed = (screenHeight - (ApplicationConstants.BOTTOM_PADDING_FOR_TAB_BAR + (ApplicationConstants.BOTTOM_PADDING_FOR_TAB_BAR / 4)));
                    } else if (ApplicationConstants.BOTTOM_PADDING_FOR_TAB_BAR < 1) {
                        screenHeightToBeUsed = ((screenHeight * 15) / 100);
                    }
                } else {
                    screenHeightToBeUsed = ((screenHeight * 15) / 100);
                }
                System.out.println("updateTextureViewSize==>"+(screenHeight-(ApplicationConstants.TOP_PADDING_FOR_MAIN_ACTION_BAR+ApplicationConstants.BOTTOM_PADDING_FOR_TAB_BAR)));

                try { updateTextureViewSize(screenWidth, screenHeightToBeUsed); } catch(Exception e){}
                //System.out.println("pixelWidthHeightRatio==>"+pixelWidthHeightRatio+" for fragment number==>"+getIndexOfThisPopularCategoriesFragment());
                //System.out.println("onVideoSizeChangedonVideoSizeChangedonVideoSizeChanged"+videoTexturePresenter.getPlayer().getTrackCount(0));
                //System.out.println("videoTexturePresenter.getPlayer().getSelectedTrack(0)==>"+videoTexturePresenter.getPlayer().getSelectedTrack(0));

                for(int i = 0; i < videoTexturePresenter.getPlayer().getTrackCount(0); i++) {
                    //System.out.println("videoTexturePresenter.getPlayer().getTrackFormat(0, "+i+")==>" + videoTexturePresenter.getPlayer().getTrackFormat(0, i));
                }

                //videoTexturePresenter.getPlayer().setSelectedTrack(0, videoTexturePresenter.getPlayer().getTrackCount(0)-1);
            }
        });

        videoTexturePresenter.addOnDrawnToSurfaceListener(new VideoTexturePresenter.OnDrawnToSurfaceListener() {
            @Override
            public void onDrawnToSurface(Surface surface) {
                imageView.setVisibility(View.GONE);
            }
        });

        videoTexturePresenter.addOnStateChangedListener(new VideoTexturePresenter.OnStateChangedListener() {
            @Override
            public void onStateChanged(boolean playWhenReady, int playbackState) {
                //System.out.println(playWhenReady+"INSIDE onStateChanged for "+indexOfThis+" !!! "+playbackState);
                if(playbackState == ExoPlayer.STATE_BUFFERING) {
                    busyCursorProgressBar.setVisibility(View.VISIBLE);
                }
                if(playbackState == ExoPlayer.STATE_READY) {
                    imageView.setVisibility(View.GONE);
                    busyCursorProgressBar.setVisibility(View.GONE);
                    videoTextureView.setVisibility(View.VISIBLE);
                    if (ApplicationConstants.CURRNET_LOADED_VIEW == ApplicationConstants.HOME_VIEW &&
                            ApplicationConstants.currentlySelectedPopularCategoriesPage == getIndexOfThisPopularCategoriesFragment()) {
                        if(!ApplicationConstants.scrollStarted && ApplicationConstants.scrollEnded) {
                            if(!playerDimensionsUpdated)
                                updateTextureViewSize(screenWidth, screenHeight);
                            busyCursorProgressBar.setVisibility(View.GONE);
                            //videoTexturePresenter.seekTo(lastSeekedPosition);
                            if (videoTexturePresenter != null) {
                                videoTexturePresenter.setMute(ApplicationConstants.isMute);
                            }
                            videoTexturePresenter.play();
                        }
                        //System.out.println("Play ExoPlayer.STATE_READYExoPlayer.STATE_READYExoPlayer.STATE_READY"+indexOfThisPopularCategoriesFragment);
                    } else {
                        videoTexturePresenter.pause();
                        //System.out.println("Pause ExoPlayer.STATE_READYExoPlayer.STATE_READYExoPlayer.STATE_READY"+indexOfThisPopularCategoriesFragment);
                    }
                }
                if(playbackState == ExoPlayer.STATE_ENDED) {
                    //System.out.println("ExoPlayer.STATE_ENDEDExoPlayer.STATE_ENDEDExoPlayer.STATE_ENDED"+indexOfThisPopularCategoriesFragment);
                    busyCursorProgressBar.setVisibility(View.VISIBLE);
                    lastSeekedPosition = 0;
                    videoTexturePresenter.seekTo(0);
                }
            }
        });

        /*videoTexturePresenter.play();
        videoTexturePresenter.seekTo(0);
        videoTexturePresenter.setMute(true);
        videoTexturePresenter.pause();*/
    }

    public void toggleControlsVisibility() {
        if (playControlControllerView.isShowing()) {
            playControlControllerView.hide();
        } else {
            playControlControllerView.show(3000);
        }
    }

    View.OnClickListener popularCategoriesClickHandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(categorySlug != null && channelSlug != null && videoID != null
                    && categorySlug.length() > 0 && channelSlug.length() > 0 && videoID.length() > 0) {
                try {
                    videoTexturePresenter.pause();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("categorySlug, channelSlug, videoID==>"+categorySlug+","+channelSlug+","+videoID);
                mListener.popularCategoriesClickHandler(categorySlug, channelSlug, videoID);
            } else if(blogID != null && blogID.length() > 0 &&
                    blogType != null && blogType.length() > 0) {
                System.out.println("blogID, blogType==>"+blogID+","+blogType);
                mListener.popularCategoriesClickHandler(blogID, blogType);
            }
        }
    };

    @Override
    public void onDestroyView() {
        if(videoTexturePresenter != null) {
            try {
                videoTexturePresenter.stop();
            } catch(Exception e) {
                e.printStackTrace();
            }
            //videoTexturePresenter.release();
            //videoTexturePresenter.onDestroy();
        }
        if(imageView != null)
            imageView.setVisibility(View.VISIBLE);
        super.onDestroyView();
    }

    public int mVideoWidth = 0;
    public int mVideoHeight = 0;
    private void updateTextureViewSize(int viewWidth, int viewHeight) {
        playerDimensionsUpdated = videoTextureView.updateTextureViewSize(screenWidth, viewHeight, mVideoWidth, mVideoHeight);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement PopularCategoriesFragmentNewPlayer");
        }
        //System.out.println("onAttach called!!!onAttach called!!!onAttach called!!!");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public String getColorVaule() {
        return colorVaule;
    }

    public void setColorVaule(String colorVaule) {
        this.colorVaule = colorVaule;
    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i1) {
        /*Surface s = new Surface(surfaceTexture);

        try {
            mediaPlayer= new MediaPlayer();
            System.out.println("videoToPlayURLvideoToPlayURLvideoToPlayURL:"+videoToPlayURL);
            mediaPlayer.setDataSource(videoToPlayURL);
            mediaPlayer.setSurface(s);
            mediaPlayer.setLooping(true);
            mediaPlayer.prepareAsync();
            //mediaPlayer.setOnBufferingUpdateListener(this);
            mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
                @Override
                public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
                    System.out.println("onBufferingUpdateonBufferingUpdateonBufferingUpdate:"+i);
                }
            });
            mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
                    System.out.println(i1+"onErroronErroronError:"+i);
                    return true;
                }
            });
            mediaPlayer.setOnInfoListener(new MediaPlayer.OnInfoListener() {
                @Override
                public boolean onInfo(MediaPlayer mediaPlayer, int i, int i1) {
                    //System.out.println("");
                    return true;
                }
            });
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    *//**//*try {
                        mediaPlayer.seekTo(100);
                        if (currentlySelected == indexOfThis)
                            mediaPlayer.start();
                    } catch(Exception e) {
                        //e.printStackTrace();
                    }*//**//*
                }
            });
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    try {
                        System.out.println("onPreparedonPreparedonPrepared");
                        *//**//*mediaPlayer.seekTo(100);
                        mediaPlayer.pause();*//**//*

                        if (currentlySelected == indexOfThis)
                            mediaPlayer.start();*//**//*
                        else
                            mediaPlayer.pause();*//**//*
                    } catch(Exception e) {
                        //e.printStackTrace();
                    }
                }
            });
            *//**//*mediaPlayer.setOnVideoSizeChangedListener(this);*//**//*
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            //mediaPlayer.prepareAsync();
            //mediaPlayer.start();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
        }*/
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i1) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        return (surfaceTexture == null);
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {

    }

    public int getIndexOfThisPopularCategoriesFragment() {
        return indexOfThisPopularCategoriesFragment;
    }

    public void setIndexOfThisPopularCategoriesFragment(int indexOfThisPopularCategoriesFragment) {
        this.indexOfThisPopularCategoriesFragment = indexOfThisPopularCategoriesFragment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleLogo() {
        return titleLogo;
    }

    public void setTitleLogo(String titleLogo) {
        this.titleLogo = titleLogo;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    public boolean isDancingArrowVisible() {
        return isDancingArrowVisible;
    }

    public void setDancingArrowVisible(boolean dancingArrowVisible) {
        isDancingArrowVisible = dancingArrowVisible;
    }

    public String getBlogID() {
        return blogID;
    }

    public void setBlogID(String blogID) {
        this.blogID = blogID;
    }

    public String getBlogType() {
        return blogType;
    }

    public void setBlogType(String blogType) {
        this.blogType = blogType;
    }

    public String getVideoID() {
        return videoID;
    }

    public void setVideoID(String videoID) {
        this.videoID = videoID;
    }

    public String getCategorySlug() {
        return categorySlug;
    }

    public void setCategorySlug(String categorySlug) {
        this.categorySlug = categorySlug;
    }

    public String getChannelSlug() {
        return channelSlug;
    }

    public void setChannelSlug(String channelSlug) {
        this.channelSlug = channelSlug;
    }

    public String getVideoToPlayURL() {
        return videoToPlayURL;
    }

    public void setVideoToPlayURL(String videoToPlayURL) {
        this.videoToPlayURL = videoToPlayURL;
    }

    public String getVideoPosterURL() {
        return videoPosterURL;
    }

    public void setVideoPosterURL(String videoPosterURL) {
        this.videoPosterURL = videoPosterURL;
    }

    public boolean isTitleFontSizeEnabled() {
        return titleFontSizeEnabled;
    }

    public void setTitleFontSizeEnabled(boolean titleFontSizeEnabled) {
        this.titleFontSizeEnabled = titleFontSizeEnabled;
    }

    public float getTitleFontSize() {
        return titleFontSize;
    }

    public void setTitleFontSize(float titleFontSize) {
        this.titleFontSize = titleFontSize;
    }
}
