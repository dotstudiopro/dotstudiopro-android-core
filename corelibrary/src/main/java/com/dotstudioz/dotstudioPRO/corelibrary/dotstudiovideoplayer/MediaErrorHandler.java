package com.dotstudioz.dotstudioPRO.corelibrary.dotstudiovideoplayer;

import android.media.MediaPlayer;



/**
 * Created by mohsin on 09-10-2016.
 */
public class MediaErrorHandler {
    private static MediaErrorHandler ourInstance = new MediaErrorHandler();

    public static MediaErrorHandler getInstance() {
        return ourInstance;
    }

    private MediaErrorHandler() {
    }

    public String getErrorMessage(int what) {
        String errorMessage = "";
        switch (what) {
            case MediaPlayer.MEDIA_ERROR_IO:
                errorMessage = "MEDIA_ERROR_IO";
                break;
            case MediaPlayer.MEDIA_ERROR_MALFORMED:
                errorMessage = "MEDIA_ERROR_MALFORMED";
                break;
            case MediaPlayer.MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK:
                errorMessage = "MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK";
                break;
            case MediaPlayer.MEDIA_ERROR_SERVER_DIED:
                errorMessage = "MEDIA_ERROR_SERVER_DIED";
                break;
            case MediaPlayer.MEDIA_ERROR_TIMED_OUT:
                errorMessage = "MEDIA_ERROR_TIMED_OUT";
                break;
            case MediaPlayer.MEDIA_ERROR_UNKNOWN:
                errorMessage = "MEDIA_ERROR_UNKNOWN";
                break;
            case MediaPlayer.MEDIA_ERROR_UNSUPPORTED:
                errorMessage = "MEDIA_ERROR_UNSUPPORTED";
                break;
            default:
                errorMessage = "Some unknown error occured:-" + what;
                break;
        }
        return errorMessage;
    }
}
