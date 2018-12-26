package com.dotstudioz.dotstudioPRO.corelibrary.spotx;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.spotxchange.v3.SpotX;
import com.spotxchange.v3.SpotXAdBuilder;
import com.spotxchange.v3.SpotXAdGroup;

import java.util.ArrayList;

public class AdLoader extends AsyncTask<Void, Void, SpotXAdGroup> {

    public interface Callback {
        void adLoadingStarted();
        void adLoadingFinished(@Nullable SpotXAdGroup adGroup);
    }

    private final String _channelId;
    private final int _count;
    private final long _timeout;
    private final Callback _callback;


    public AdLoader(String channelId, int count, long timeout, @NonNull Callback callback) {
        _channelId = channelId;
        _count = count;
        _timeout = timeout;
        _callback = callback;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        _callback.adLoadingStarted();
    }

    @Override
    protected void onPostExecute(SpotXAdGroup adGroup) {
        super.onPostExecute(adGroup);
        _callback.adLoadingFinished(adGroup);
    }

    public ArrayList<AdLoaderParamDTO> paramsDTOList = new ArrayList<>();
    @Override
    protected SpotXAdGroup doInBackground(Void... params) {
        SpotXAdBuilder request = SpotX.newAdBuilder(_channelId);
        try {
            if (paramsDTOList != null && paramsDTOList.size() > 0) {
                for (int i = 0; i < paramsDTOList.size(); i++) {
                    if(paramsDTOList.get(i).paramName.equals("VPAID")) {
                        request.param(paramsDTOList.get(i).paramName, paramsDTOList.get(i).paramValue);
                    } else {
                        if(paramsDTOList.get(i).paramValue != null) {
                            request.custom(paramsDTOList.get(i).paramName, paramsDTOList.get(i).paramValue);
                        }
                    }
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        try {
            //return request.loadWithCount(_count).get(_timeout, TimeUnit.SECONDS);
            return request.load().get();
        } catch (Exception e) {
            Log.e(AdLoader.class.getSimpleName(), "Unable to load SpotX Ad", e);
            return null;
        }
    }

}