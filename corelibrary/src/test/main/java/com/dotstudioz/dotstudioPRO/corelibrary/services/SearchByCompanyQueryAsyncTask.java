package com.dotstudioz.dotstudioPRO.services.services;

import android.content.Context;
import android.os.AsyncTask;

import com.dotstudioz.dotstudioPRO.models.dto.SearchResultDTO;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Admin on 16-01-2016.
 */
public class SearchByCompanyQueryAsyncTask extends AsyncTask<String, String, String> {

    public SearchByCompanyQueryAsyncTask.ISearchByCompanyQueryAsyncTask iSearchByCompanyQueryAsyncTask;

    Context ctx;
    String searchByCompanyString;
    String SEARCH_BY_COMPANY_API_URL;
    String SEARCH_BY_COMPANY_DATA_FORMAT;

    public SearchByCompanyQueryAsyncTask(Context ctx, String mSearchByCompanyString, String SEARCH_BY_COMPANY_API_URL, String SEARCH_BY_COMPANY_DATA_FORMAT) {
        this.ctx = ctx;
        this.searchByCompanyString = mSearchByCompanyString;
        this.SEARCH_BY_COMPANY_API_URL = SEARCH_BY_COMPANY_API_URL;
        this.SEARCH_BY_COMPANY_DATA_FORMAT = SEARCH_BY_COMPANY_DATA_FORMAT;

        if (ctx instanceof SearchByCompanyQueryAsyncTask.ISearchByCompanyQueryAsyncTask)
            iSearchByCompanyQueryAsyncTask = (SearchByCompanyQueryAsyncTask.ISearchByCompanyQueryAsyncTask) ctx;
        else
            throw new RuntimeException(ctx.toString()+ " must implement ISearchByCompanyQueryAsyncTask");
    }

    @Override
    public void onPreExecute() {
        super.onPreExecute();
    }

    public void onPostExecute(String stringData) {
        super.onPostExecute(stringData);
    }

    public String doInBackground(String... params) {
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(SEARCH_BY_COMPANY_API_URL+searchByCompanyString+SEARCH_BY_COMPANY_DATA_FORMAT);
        //HttpPost httppost = new HttpPost(ApplicationConstantURL.getInstance().SEARCH_BY_COMPANY_API_URL+searchByCompanyString+ApplicationConstantURL.getInstance().SEARCH_BY_COMPANY_DATA_FORMAT);

        try {
            try {
                ArrayList<SearchResultDTO> searchByCompanyResultDTOArrayList = new ArrayList<>();

                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                //String response = httpclient.execute(httppost, responseHandler);
                HttpResponse response = httpclient.execute(httpGet);
                BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
                String json = reader.readLine();
                JSONObject finalObjectResult = new JSONObject();

                finalObjectResult = new JSONObject(json);

                JSONArray channelsArray = finalObjectResult.getJSONArray("channels");
                for (int i = 0; i < channelsArray.length(); i++) {
                    SearchResultDTO searchResultDTO = new SearchResultDTO();
                    searchResultDTO.setId(channelsArray.getJSONObject(i).getString("_id"));
                    //searchResultDTO.setPoster(channelsArray.getJSONObject(i).getString("poster"));
                    String imageString = channelsArray.getJSONObject(i).getString("spotlight_poster");
                    imageString = CommonUtils.replaceDotstudioproWithMyspotlightForImage(imageString);
                    searchResultDTO.setSpotlightPoster(imageString);
                    searchResultDTO.setSlug(channelsArray.getJSONObject(i).getString("slug"));
                    searchByCompanyResultDTOArrayList.add(searchResultDTO);
                }
                iSearchByCompanyQueryAsyncTask.searchByCompanyQueryAsyncTaskResponse(searchByCompanyResultDTOArrayList);
            } catch (ClientProtocolException e) {
                iSearchByCompanyQueryAsyncTask.searchByCompanyQueryAsyncTaskError(e.getMessage());
                //e.printStackTrace();
            } catch (IOException e) {
                iSearchByCompanyQueryAsyncTask.searchByCompanyQueryAsyncTaskError(e.getMessage());
                //e.printStackTrace();
            } catch (Exception e) {
                iSearchByCompanyQueryAsyncTask.searchByCompanyQueryAsyncTaskError(e.getMessage());
                //e.printStackTrace();
            }
        } catch (Exception e) {
            iSearchByCompanyQueryAsyncTask.searchByCompanyQueryAsyncTaskError(e.getMessage());
            //e.printStackTrace();
        }
        return "";
    }

    public interface ISearchByCompanyQueryAsyncTask {
        void searchByCompanyQueryAsyncTaskResponse(ArrayList searchByCompanyResultDTOArrayList);
        void searchByCompanyQueryAsyncTaskError(String error);
    }
}
