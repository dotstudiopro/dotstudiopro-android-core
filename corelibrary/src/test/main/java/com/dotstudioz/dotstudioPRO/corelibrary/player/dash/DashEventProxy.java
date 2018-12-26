package com.dotstudioz.dotstudioPRO.dsplayer.player.dash;

import com.dotstudioz.dotstudioPRO.dsplayer.player.EventProxy;
import com.google.android.exoplayer.TimeRange;
import com.google.android.exoplayer.dash.DashChunkSource;

public class DashEventProxy extends EventProxy implements DashChunkSource.EventListener {

    /**
     * A listener for debugging information.
     */
    public interface InfoListener {

        void onAvailableRangeChanged(TimeRange seekRange);
    }

    private InfoListener infoListener;

    public void setDashInfoListener(InfoListener infoListener) {
        this.infoListener = infoListener;
    }

    /** DashChunkSource.EventListener */
    @Override
    public void onAvailableRangeChanged(int sourceId, TimeRange timeRange) {
        if (infoListener != null) {
            infoListener.onAvailableRangeChanged(timeRange);
        }
    }

}
