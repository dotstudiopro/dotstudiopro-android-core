package com.dotstudioz.dotstudioPRO.corelibrary.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.dotstudioz.dotstudioPRO.corelibrary.R;
import com.dotstudioz.dotstudioPRO.services.constants.ApplicationConstantURL;
import com.dotstudioz.dotstudioPRO.services.constants.ApplicationConstants;
import com.dotstudioz.dotstudioPRO.services.constants.FontsConstants;
import com.dotstudioz.dotstudioPRO.models.dto.SearchSuggesterDTO;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 11-04-2016.
 */
public class SearchSuggesterAdapter extends BaseAdapter implements Filterable {

    private static final int MAX_RESULTS = 10;
    private Context mContext;
    private List<SearchSuggesterDTO> resultList = new ArrayList<>();

    private boolean isDirectorSet = false;
    private boolean isActorSet = false;
    private boolean isTitleSet = false;

    public SearchSuggesterAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return resultList.size();
    }

    @Override
    public SearchSuggesterDTO getItem(int index) {
        return resultList.get(index);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.search_suggester_item, parent, false);
        }

        /*if(getItem(position).getParent().equals(ApplicationConstants.DIRECTORS_SEARCH_SUGGESTER_STRING)) {
            ((TextView) convertView.findViewById(R.id.text1)).setText(getItem(position).getSuggestion());
            ((TextView) convertView.findViewById(R.id.text2)).setText(getItem(position).getParent());
        } else if(getItem(position).getParent().equals(ApplicationConstants.ACTORS_SEARCH_SUGGESTER_STRING)) {
            ((TextView) convertView.findViewById(R.id.suggestionTextView)).setText(getItem(position).getSuggestion());
            ((TextView) convertView.findViewById(R.id.suggestionTextView1)).setVisibility(View.GONE);
        } else if(getItem(position).getParent().equals(ApplicationConstants.TITLE_SEARCH_SUGGESTER_STRING)) {
            ((TextView) convertView.findViewById(R.id.suggestionTextView)).setText(getItem(position).getSuggestion());
            ((TextView) convertView.findViewById(R.id.suggestionTextView1)).setVisibility(View.GONE);
        }*/
        ((TextView) convertView.findViewById(R.id.text1)).setText(getItem(position).getSuggestion());
        if(getItem(position).getParent().equals("title"))
            ((TextView) convertView.findViewById(R.id.text2)).setText(ApplicationConstants.TITLE_SEARCH_SUGGESTER_STRING);
        else if(getItem(position).getParent().equals("directors"))
            ((TextView) convertView.findViewById(R.id.text2)).setText(ApplicationConstants.DIRECTORS_SEARCH_SUGGESTER_STRING);
        else if(getItem(position).getParent().equals("actors"))
            ((TextView) convertView.findViewById(R.id.text2)).setText(ApplicationConstants.ACTORS_SEARCH_SUGGESTER_STRING);
        //((TextView) convertView.findViewById(R.id.suggestionTextView)).setText(getItem(position).getParent()+" - "+getItem(position).getSuggestion());
        return convertView;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if (constraint != null) {
                    List<SearchSuggesterDTO> books = findBooks(mContext, constraint.toString());

                    // Assign the data to the FilterResults
                    filterResults.values = books;
                    filterResults.count = books.size();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results != null && results.count > 0) {
                    resultList = (List<SearchSuggesterDTO>) results.values;
                    notifyDataSetChanged();

                    InputMethodManager imm = (InputMethodManager)mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(ApplicationConstants.searchViewEditText.getWindowToken(), 0);
                } else {
                    notifyDataSetInvalidated();
                }
            }};
        return filter;
    }

    /**
     * Returns a search result for the given book title.
     */
    public List<SearchSuggesterDTO> findBooks(Context context, String bookTitle) {
        //reinitialize the variables
        ApplicationConstants.isActorsSet = false;
        ApplicationConstants.isDirectorsSet = false;
        ApplicationConstants.isTitleSet = false;

        // GoogleBooksProtocol is a wrapper for the Google Books API
        ApplicationConstants.searchSuggesterDTOArrayList = new ArrayList<>();

        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(ApplicationConstantURL.getInstance().SEARCH_SUGGESTER_API_URL+"?q="+ Uri.encode(bookTitle)+"&token="+ApplicationConstants.xAccessToken);

        System.out.println("URL SEARCH CALLED==>"+ApplicationConstantURL.getInstance().SEARCH_SUGGESTER_API_URL+"?q="+Uri.encode(bookTitle)+"&token="+ApplicationConstants.xAccessToken);

        try {
            try {
                ResponseHandler<String> responseHandler = new BasicResponseHandler();
                //String response = httpclient.execute(httppost, responseHandler);
                HttpResponse response = httpclient.execute(httpget);
                BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
                String json = reader.readLine();
                System.out.println("SEARCH_RESULT_SUGGESTER:-"+json);
                ApplicationConstants.searchSuggesterDTOArrayList = processSearchResult(json);
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }

        return ApplicationConstants.searchSuggesterDTOArrayList;
    }

    private ArrayList<SearchSuggesterDTO> processSearchResult(String resultString) {
        ArrayList<SearchSuggesterDTO> searchSuggesterDTOArrayList = new ArrayList<>();

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(resultString);

            JSONArray resultJSONArrray1 = ((JSONObject)jsonObject.getJSONObject("data").getJSONObject("title").getJSONArray("results").get(0)).getJSONArray("options");
            for(int i = 0; i < resultJSONArrray1.length(); i++) {
                JSONObject resultJSONObject = (JSONObject) resultJSONArrray1.get(i);
                SearchSuggesterDTO searchSuggesterDTO = new SearchSuggesterDTO();
                searchSuggesterDTO.setParent(ApplicationConstants.TITLE_SEARCH_SUGGESTER_STRING);
                searchSuggesterDTO.setScore(resultJSONObject.getInt("score"));
                searchSuggesterDTO.setSuggestion(resultJSONObject.getString("text"));
                searchSuggesterDTO.setChannelURL(resultJSONObject.getString("url"));
                searchSuggesterDTOArrayList.add(searchSuggesterDTO);
            }

            JSONArray resultJSONArrray2 = ((JSONObject)jsonObject.getJSONObject("data").getJSONObject("directors").getJSONArray("results").get(0)).getJSONArray("options");
            for(int i = 0; i < resultJSONArrray2.length(); i++) {
                JSONObject resultJSONObject = (JSONObject) resultJSONArrray2.get(i);
                SearchSuggesterDTO searchSuggesterDTO = new SearchSuggesterDTO();
                searchSuggesterDTO.setParent(ApplicationConstants.DIRECTORS_SEARCH_SUGGESTER_STRING);
                searchSuggesterDTO.setScore(resultJSONObject.getInt("score"));
                searchSuggesterDTO.setSuggestion(resultJSONObject.getString("text"));
                searchSuggesterDTO.setChannelURL(resultJSONObject.getString("url"));
                searchSuggesterDTOArrayList.add(searchSuggesterDTO);
            }

            JSONArray resultJSONArrray3 = ((JSONObject)jsonObject.getJSONObject("data").getJSONObject("actors").getJSONArray("results").get(0)).getJSONArray("options");
            for(int i = 0; i < resultJSONArrray3.length(); i++) {
                JSONObject resultJSONObject = (JSONObject) resultJSONArrray3.get(i);
                SearchSuggesterDTO searchSuggesterDTO = new SearchSuggesterDTO();
                searchSuggesterDTO.setParent(ApplicationConstants.ACTORS_SEARCH_SUGGESTER_STRING);
                searchSuggesterDTO.setScore(resultJSONObject.getInt("score"));
                searchSuggesterDTO.setSuggestion(resultJSONObject.getString("text"));
                searchSuggesterDTO.setChannelURL(resultJSONObject.getString("url"));
                searchSuggesterDTOArrayList.add(searchSuggesterDTO);
            }
        } catch(JSONException e) {
            //e.printStackTrace();
        }

        return searchSuggesterDTOArrayList;
    }
}

