package com.dotstudioz.dotstudioPRO.corelibrary.userauthenticationfragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.dotstudioz.dotstudioPRO.corelibrary.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentUserAuthentication#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentUserAuthentication extends Fragment implements View.OnFocusChangeListener {

    Fragment thisFragment;

    public static String KEY_IMAGE_RESOURCE_LOGO = "IMAGE_RESOURCE_LOGO";
    public static String KEY_AUTH0KEY = "AUTH0KEY";
    public static String KEY_AUTH0DOMAIIN = "AUTH0DOMAIIN";
    public static String KEY_USERNAME = "USERNAME";
    public static String KEY_PASSWORD = "PASSWORD";
    public static String KEY_COMPANYKEY = "COMPANYKEY";
    public static String KEY_CONNECTIONNAME = "CONNECTIONNAME";
    public static String KEY_SUBMITBUTTON = "SUBMITBUTTON";
    public static String KEY_SKIPBUTTON = "SKIPBUTTON";
    public static String KEY_FACEBOOKBUTTON = "FACEBOOKBUTTON";

    public int imageResourceLogo;
    public int facebookButtonResource;
    public int submitButtonResource;
    public int skipButtonResource;
    public String auth0Key = "KljEDmsIWjelTALDWPsM5i9eURjFN4nF";
    public String auth0Domain = "dotstudiopro.auth0.com";
    public String username = "mohsin@mohsin.com";
    public String password = "mohsin";
    public String companyKey = "55e0f8e597f8157975153ae6";
    public String connectionNameKey = "facebook-btvr";

    public EditText usernameEditText;
    public EditText passwordEditText;

    public FragmentUserAuthentication() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentUserAuthentication newInstance() {
        FragmentUserAuthentication fragment = new FragmentUserAuthentication();
        return fragment;
    }

    public void setInterfaceContext(Context context) {
        iFragmentUserAuthentication = (IFragmentUserAuthentication) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            Bundle args = getArguments();
            if (args != null) {
                imageResourceLogo = args.getInt(KEY_IMAGE_RESOURCE_LOGO);
                submitButtonResource = args.getInt(KEY_SUBMITBUTTON);
                skipButtonResource = args.getInt(KEY_SKIPBUTTON);
                facebookButtonResource = args.getInt(KEY_FACEBOOKBUTTON);
                auth0Key = args.getString(KEY_AUTH0KEY);
                auth0Domain = args.getString(KEY_AUTH0DOMAIIN);
                companyKey = args.getString(KEY_COMPANYKEY);
                connectionNameKey = args.getString(KEY_CONNECTIONNAME);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_user_authentication, container, false);
        View view = inflater.inflate(R.layout.fragment_user_authentication, container, false);
        //view.setBackgroundColor(Color.parseColor("#000000"));

        view.findViewById(R.id.usernameEditText).requestFocus();
        usernameEditText = (EditText) view.findViewById(R.id.usernameEditText);
        passwordEditText = (EditText) view.findViewById(R.id.passwordEditText);

        view.findViewById(R.id.skipButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(iFragmentUserAuthentication != null)
                    iFragmentUserAuthentication.skipClickHandlerFragmentUserAuthentication();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.remove(thisFragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                ft.commit();
            }
        });

        ((ImageView)view.findViewById(R.id.companyLogoImageView)).setImageResource(imageResourceLogo);

        if(facebookButtonResource != 0)
            (view.findViewById(R.id.facebookButton)).setBackgroundResource(facebookButtonResource);

        if(submitButtonResource != 0)
            ((Button)view.findViewById(R.id.submitButton)).setBackgroundResource(submitButtonResource);

        if(skipButtonResource != 0)
            ((Button)view.findViewById(R.id.skipButton)).setBackgroundResource(skipButtonResource);

        view.findViewById(R.id.submitButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*UsernamePasswordAuth0API.getInstance(getActivity()).authenticateUser(
                        "yox0owDeBdTrKHU0GkrfC3OO9USmgZQ5",
                        "dotstudiopro.auth0.com",
                        "mohsin@mohsin.com",
                        "mohsin",
                        "57e9fb9644afa8c50570d38dab7b5fe1c094c9b5"
                );*/
                try {
                    username = usernameEditText.getText().toString();
                    password = passwordEditText.getText().toString();
                } catch(Exception e) {
                    e.printStackTrace();
                }
                UsernamePasswordAuth0API.getInstance(getActivity()).authenticateUser(
                        auth0Key,
                        auth0Domain,
                        username,
                        password,
                        companyKey
                );
            }
        });

        view.findViewById(R.id.facebookButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*UsernamePasswordAuth0API.getInstance(getActivity()).authenticateFacebookUser(
                        auth0Key,
                        auth0Domain,
                        companyKey,
                        connectionNameKey
                );*/
                iFragmentUserAuthentication.facebookUserAuthentication();
            }
        });

        view.findViewById(R.id.facebook_container).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*UsernamePasswordAuth0API.getInstance(getActivity()).authenticateFacebookUser(
                        auth0Key,
                        auth0Domain,
                        companyKey,
                        connectionNameKey
                );*/
                iFragmentUserAuthentication.facebookUserAuthentication();
            }
        });

        thisFragment = this;

        view.findViewById(R.id.usernameEditText).setOnFocusChangeListener(this);
        view.findViewById(R.id.passwordEditText).setOnFocusChangeListener(this);
        view.findViewById(R.id.facebookButton).setOnFocusChangeListener(this);
        view.findViewById(R.id.facebook_container).setOnFocusChangeListener(this);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {

        float scalingFactor;

        if(hasFocus) {
            scalingFactor = 1.05f;
        } else {
            scalingFactor = 1f;
        }

        v.setScaleX(scalingFactor);
        v.setScaleY(scalingFactor);

//        if(v.getId() == R.id.start_free_trial_button
//                || v.getId() == R.id.skipButton1
//                || v.getId() == R.id.signinwithexisting
//                || v.getId() == R.id.deviceLoginButton
//                || v.getId() == R.id.submitButton
//                || v.getId() == R.id.skipButton) {
//
////            if(hasFocus) {
////                ((Button) v).setTextColor(Color.WHITE);
////            } else {
////                ((Button) v).setTextColor(Color.BLACK);
////            }
//        }

    }
    public IFragmentUserAuthentication iFragmentUserAuthentication;
    public interface IFragmentUserAuthentication {
        void skipClickHandlerFragmentUserAuthentication();
        void facebookUserAuthentication();
    }

}
