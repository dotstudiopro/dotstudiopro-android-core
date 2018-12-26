package com.dotstudioz.dotstudioPRO.dsplayer.player.streaming;


import com.dotstudioz.dotstudioPRO.dsplayer.player.EventProxy;
import com.google.android.exoplayer.drm.StreamingDrmSessionManager;

public class StreamingEventProxy extends EventProxy implements
        StreamingDrmSessionManager.EventListener {

    public interface InternalErrorListener {

        void onDrmSessionManagerError(Exception e);

        void onDrmKeysLoaded();
    }

    private InternalErrorListener internalErrorListener;

    public void setStreamingInternalErrorListener(InternalErrorListener internalErrorListener) {
        this.internalErrorListener = internalErrorListener;
    }

    /** StreamingDrmSessionManager.EventListener */
    @Override
    public void onDrmSessionManagerError(Exception e) {
        if (internalErrorListener != null) {
            internalErrorListener.onDrmSessionManagerError(e);
        }
    }

    @Override
    public void onDrmKeysLoaded() {
        if (internalErrorListener != null) {
            internalErrorListener.onDrmKeysLoaded();
        }
    }
}
