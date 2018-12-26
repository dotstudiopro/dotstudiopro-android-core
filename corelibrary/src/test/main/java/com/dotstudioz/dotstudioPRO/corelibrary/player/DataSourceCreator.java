package com.dotstudioz.dotstudioPRO.dsplayer.player;

import android.content.Context;

import com.google.android.exoplayer.upstream.DataSource;
import com.google.android.exoplayer.upstream.TransferListener;

public interface DataSourceCreator {

    DataSource create(Context context, TransferListener listener, String userAgent);

}
