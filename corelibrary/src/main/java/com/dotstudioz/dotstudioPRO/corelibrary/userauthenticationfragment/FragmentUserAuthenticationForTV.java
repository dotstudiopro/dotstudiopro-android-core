package com.dotstudioz.dotstudioPRO.corelibrary.userauthenticationfragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dotstudioz.dotstudioPRO.corelibrary.R;
import com.dotstudioz.dotstudioPRO.corelibrary.constants.FontsConstants;
import com.dotstudioz.dotstudioPRO.corelibrary.util.TransparentProgressDialog;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentUserAuthenticationForTV#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentUserAuthenticationForTV extends Fragment implements View.OnFocusChangeListener {

    Fragment thisFragment;
    private Context mContext;

    public static String KEY_IMAGE_RESOURCE_BACKGROUND = "IMAGE_RESOURCE_BACKGROUND";
    public static String KEY_IMAGE_RESOURCE_LOGO = "IMAGE_RESOURCE_LOGO";
    public static String KEY_AUTH0KEY = "AUTH0KEY";
    public static String KEY_AUTH0DOMAIIN = "AUTH0DOMAIIN";
    public static String KEY_USERNAME = "USERNAME";
    public static String KEY_PASSWORD = "PASSWORD";
    public static String KEY_COMPANYKEY = "COMPANYKEY";
    public static String KEY_CONNECTIONNAME = "CONNECTIONNAME";
    public static String KEY_X_ACCESS = "ACCESSKEY";
    public static String KEY_IS_FIRST_TIME_LAUNCH = "isFirstTimeLaunch";

    public int imageResourceBackground;
    public int imageResourceLogo;
    public String auth0Key = "KljEDmsIWjelTALDWPsM5i9eURjFN4nF";
    public String auth0Domain = "dotstudiopro.auth0.com";
    public String username = "mohsin@mohsin.com";
    public String password = "mohsin";
    public String companyKey = "55e0f8e597f8157975153ae6";
    public String connectionNameKey = "facebook-btvr";
    public String accessKey = "";
private boolean isFirstTimeLaunch;

    public EditText usernameEditText;
    public EditText passwordEditText;
    private View view;

    public FragmentUserAuthenticationForTV() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentUserAuthenticationForTV newInstance() {
        FragmentUserAuthenticationForTV fragment = new FragmentUserAuthenticationForTV();
        return fragment;
    }

    public void setInterfaceContext(Context context) {
        iFragmentUserAuthentication = (IFragmentUserAuthentication) context;
        this.mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            Log.d("","onCreate");
            Bundle args = getArguments();
            if (args != null) {
                imageResourceBackground = args.getInt(KEY_IMAGE_RESOURCE_BACKGROUND);
                imageResourceLogo = args.getInt(KEY_IMAGE_RESOURCE_LOGO);
                auth0Key = args.getString(KEY_AUTH0KEY);
                auth0Domain = args.getString(KEY_AUTH0DOMAIIN);
                companyKey = args.getString(KEY_COMPANYKEY);
                connectionNameKey = args.getString(KEY_CONNECTIONNAME);
                accessKey = args.getString(KEY_X_ACCESS);
                isFirstTimeLaunch = args.getBoolean(KEY_IS_FIRST_TIME_LAUNCH);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("","onResume");
        final View firstLayout = view.findViewById(R.id.loginfirstLayout);
        final View secondLayout = view.findViewById(R.id.loginsecondLayout);

        if(isFirstTimeLaunch) {
            firstLayout.setVisibility(View.VISIBLE);
            view.findViewById(R.id.auth_description_layout2).setVisibility(View.VISIBLE);
            view.findViewById(R.id.auth_description_layout1).setVisibility(View.VISIBLE);
            secondLayout.setVisibility(View.GONE);
        } else
        {
            firstLayout.setVisibility(View.GONE);
            view.findViewById(R.id.auth_description_layout2).setVisibility(View.GONE);
            view.findViewById(R.id.auth_description_layout1).setVisibility(View.GONE);
            secondLayout.setVisibility(View.VISIBLE);

        }
//        view.findViewById(R.id.auth_description_layout2).setVisibility(View.GONE);
//        view.findViewById(R.id.auth_description_layout1).setVisibility(View.GONE);
        if(firstLayout.getVisibility() == View.VISIBLE)
        {
           // view.findViewById(R.id.signinwithexisting).requestFocus();
        }
        else
        {
           //view.findViewById(R.id.usernameEditText).requestFocus();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("","onDestroy");
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.remove(thisFragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        ft.commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("","onCreateView");
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_user_authentication, container, false);
         view = inflater.inflate(R.layout.fragment_user_authentication_tv, container, false);

         //SetFont
       // ((TextView)view.findViewById(R.id.your_account)).setTypeface(FontsConstants.tfBold);
        ((TextView)view.findViewById(R.id.streamTextView)).setTypeface(FontsConstants.tfBold);
        ((TextView)view.findViewById(R.id.outTextView)).setTypeface(FontsConstants.tfBold);
        ((TextView)view.findViewById(R.id.loudTextView)).setTypeface(FontsConstants.tfBold);

        //((TextView)view.findViewById(R.id.auth_description)).setTypeface(FontsConstants.tfRegular);

        ((TextView)view.findViewById(R.id.textview1)).setTypeface(FontsConstants.tfRegular);
        ((TextView)view.findViewById(R.id.textview2)).setTypeface(FontsConstants.tfRegular);
        ((TextView)view.findViewById(R.id.textview3)).setTypeface(FontsConstants.tfRegular);
        ((TextView)view.findViewById(R.id.textview4)).setTypeface(FontsConstants.tfRegular);
        ((TextView)view.findViewById(R.id.textview5)).setTypeface(FontsConstants.tfRegular);
        ((TextView)view.findViewById(R.id.textview6)).setTypeface(FontsConstants.tfRegular);
        ((TextView)view.findViewById(R.id.textview7)).setTypeface(FontsConstants.tfRegular);
        ((TextView)view.findViewById(R.id.textview8)).setTypeface(FontsConstants.tfRegular);


        ((Button)view.findViewById(R.id.start_free_trial_button)).setTypeface(FontsConstants.tfSemiBold);
        ((TextView)view.findViewById(R.id.watch_without_ads)).setTypeface(FontsConstants.tfRegular);
        ((Button)view.findViewById(R.id.skipButton1)).setTypeface(FontsConstants.tfSemiBold);
        ((TextView)view.findViewById(R.id.no_payment_info_required)).setTypeface(FontsConstants.tfRegular);
        ((Button)view.findViewById(R.id.signinwithexisting)).setTypeface(FontsConstants.tfSemiBold);
        ((TextView)view.findViewById(R.id.already_in_tribe_textview)).setTypeface(FontsConstants.tfRegular);
        ((Button)view.findViewById(R.id.deviceLoginButton)).setTypeface(FontsConstants.tfSemiBold);
        ((EditText)view.findViewById(R.id.usernameEditText)).setTypeface(FontsConstants.tfMedium);
        ((EditText)view.findViewById(R.id.passwordEditText)).setTypeface(FontsConstants.tfMedium);
        ((Button)view.findViewById(R.id.submitButton)).setTypeface(FontsConstants.tfSemiBold);
        ((Button)view.findViewById(R.id.facebookButton)).setTypeface(FontsConstants.tfSemiBold);
        ((Button)view.findViewById(R.id.skipButton)).setTypeface(FontsConstants.tfSemiBold);

        /*((TextView)view.findViewById(R.id.loginandregister)).setTypeface(FontsConstants.futuraMediumBt);
        ((TextView)view.findViewById(R.id.registeronline)).setTypeface(FontsConstants.futuraMediumBt);
        ((TextView)view.findViewById(R.id.ortv)).setTypeface(FontsConstants.futuraMediumBt);
        ((TextView)view.findViewById(R.id.tvor2)).setTypeface(FontsConstants.sfProBold);
        ((TextView)view.findViewById(R.id.dont_remember)).setTypeface(FontsConstants.futuraMediumBt);
        ((TextView)view.findViewById(R.id.signin_with_existing)).setTypeface(FontsConstants.futuraMediumBt);*/

        //view.setBackgroundColor(Color.parseColor("#000000"));
        final View firstLayout = view.findViewById(R.id.loginfirstLayout);
        final View secondLayout = view.findViewById(R.id.loginsecondLayout);
        if(isFirstTimeLaunch) {
            firstLayout.setVisibility(View.VISIBLE);
            view.findViewById(R.id.auth_description_layout2).setVisibility(View.VISIBLE);
            view.findViewById(R.id.auth_description_layout1).setVisibility(View.VISIBLE);
            secondLayout.setVisibility(View.GONE);
        } else
        {
            firstLayout.setVisibility(View.GONE);
            view.findViewById(R.id.auth_description_layout2).setVisibility(View.GONE);
            view.findViewById(R.id.auth_description_layout1).setVisibility(View.GONE);
            secondLayout.setVisibility(View.VISIBLE);

        }


//        view.findViewById(R.id.auth_description_layout2).setVisibility(View.GONE);
//        view.findViewById(R.id.auth_description_layout1).setVisibility(View.GONE);
        if(firstLayout.getVisibility() == View.VISIBLE)
        {
            view.findViewById(R.id.start_free_trial_button).requestFocus();
        }
        else
        {
            view.findViewById(R.id.usernameEditText).requestFocus();
        }

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
        view.findViewById(R.id.skipButton1).setOnClickListener(new View.OnClickListener() {
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
        view.findViewById(R.id.signinwithexisting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstLayout.setVisibility(View.GONE);
                view.findViewById(R.id.auth_description_layout2).setVisibility(View.GONE);
                view.findViewById(R.id.auth_description_layout1).setVisibility(View.GONE);
                secondLayout.setVisibility(View.VISIBLE);
                view.findViewById(R.id.usernameEditText).requestFocus();
            }
        });
        view.findViewById(R.id.start_free_trial_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstLayout.setVisibility(View.GONE);
                view.findViewById(R.id.auth_description_layout2).setVisibility(View.GONE);
                view.findViewById(R.id.auth_description_layout1).setVisibility(View.GONE);
                secondLayout.setVisibility(View.VISIBLE);
                view.findViewById(R.id.usernameEditText).requestFocus();
            }
        });

        view.findViewById(R.id.deviceLoginButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iFragmentUserAuthentication.deviceCodeAuthentication();

                //createAndDisplayDialog();
//                if(iFragmentUserAuthentication != null)
//                    iFragmentUserAuthentication.skipClickHandlerFragmentUserAuthentication();
//                FragmentManager fm = getFragmentManager();
//                FragmentTransaction ft = fm.beginTransaction();
//                ft.remove(thisFragment);
//                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
//                ft.commit();

            }
        });

        ((ImageView)view.findViewById(R.id.companyLogoImageView)).setImageResource(imageResourceLogo);
         view.findViewById(R.id.login_root_view).setBackgroundResource(imageResourceBackground);

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
                showProgress("");
                UsernamePasswordAuth0API.getInstance(getActivity()).authenticateUser(
                        auth0Key,
                        auth0Domain,
                        username,
                        password,
                        companyKey
                );
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

        view.findViewById(R.id.signinwithexisting).setOnFocusChangeListener(this);
        view.findViewById(R.id.facebook_container).setOnFocusChangeListener(this);
        view.findViewById(R.id.skipButton).setOnFocusChangeListener(this);
        view.findViewById(R.id.deviceLoginButton).setOnFocusChangeListener(this);
        view.findViewById(R.id.submitButton).setOnFocusChangeListener(this);
        view.findViewById(R.id.skipButton1).setOnFocusChangeListener(this);
        view.findViewById(R.id.usernameEditText).setOnFocusChangeListener(this);
        view.findViewById(R.id.passwordEditText).setOnFocusChangeListener(this);
        view.findViewById(R.id.start_free_trial_button).setOnFocusChangeListener(this);

        //set spannable string
//        TextView TV = (TextView) view.findViewById(R.id.your_account);
//        Spannable wordtoSpan = new SpannableString("stream. out. loud.");
//        wordtoSpan.setSpan(new ForegroundColorSpan(Color.parseColor("#F00886")), 8, 12, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        TV.setText(wordtoSpan);

        TextView alreadyInTV = (TextView) view.findViewById(R.id.already_in_tribe_textview);
        String str = "Already in the tribe? Log In here";
        Spannable wordtoSpan = new SpannableString(str);
        wordtoSpan.setSpan(new ForegroundColorSpan(Color.parseColor("#11DDF5")), str.length() - 12, str.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        alreadyInTV.setText(wordtoSpan);

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

    public IFragmentUserAuthentication iFragmentUserAuthentication;

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


    public interface IFragmentUserAuthentication {
        void skipClickHandlerFragmentUserAuthentication();
        void facebookUserAuthentication();
        void deviceCodeAuthentication();
    }



    public boolean isProgressAlreadyVisible = false;

    public void hidePDialog() {
        /*super.hidePDialog();
        isProgressAlreadyVisible = false;*/
        hidePDialog(view.findViewById(R.id.loadingPanel));
    }


    public void hidePDialog(View view) {
        hidePDialogInFragment(view);
        isProgressAlreadyVisible = false;
    }


    public void showProgress(String msg) {
        /*if (!isProgressAlreadyVisible) {
            super.showProgress(msg);
            isProgressAlreadyVisible = true;
        }*/
        showProgress(view.findViewById(R.id.loadingPanel));
    }




    public void showProgress(View view) {
        if (!isProgressAlreadyVisible) {
            showProgressInFragment(view);
            isProgressAlreadyVisible = true;
        }
    }


    private ProgressDialog pDialog;
    public static String loadingMsg = "Loading...";
    private ProgressBar loadingProgressBar;
    private TransparentProgressDialog pd;

    protected void showProgressInFragment(String msg) {
        if (pDialog != null && pDialog.isShowing())
            hidePDialogInFragment();

        //pDialog = ProgressDialog.show(this, getResources().getString(R.string.app_name), msg);
        /*pDialog = ProgressDialog.show(this, "", msg);
        pDialog.setCancelable(false);
        pDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
        pDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        pDialog.show();*/

        /*pd = new TransparentProgressDialog(this, R.drawable.spinner_vikings);
        if (pd != null && pd.isShowing())
            hidePDialog();
        pd.show();*/
    }

    protected void hidePDialogInFragment() {
        /*if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }*/
        if (pd != null) {
            pd.dismiss();
            pd = null;
        }
    }







    protected void showProgressInFragment(View view) {
        if (pDialog != null && pDialog.isShowing())
            hidePDialog(view);

        if(view != null) {
            loadingProgressBar = (ProgressBar) view.findViewById(R.id.loadingPanelProgressBar);
            loadingProgressBar.getIndeterminateDrawable().setColorFilter(0xFFF00886, PorterDuff.Mode.MULTIPLY);
            view.setVisibility(View.VISIBLE);
        }


    }

    protected void hidePDialogInFragment(View view) {
        if(view != null)
            view.setVisibility(View.GONE);
    }

}
