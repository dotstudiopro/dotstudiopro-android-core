package com.dotstudioz.dotstudioPRO.corelibrary.cardform;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.dotstudioz.dotstudioPRO.corelibrary.R;
import com.dotstudioz.dotstudioPRO.corelibrary.constants.FontsConstants;
import com.dotstudioz.dotstudioPRO.corelibrary.models.BillingAddress;
import com.dotstudioz.dotstudioPRO.corelibrary.models.Countries;
import com.dotstudioz.dotstudioPRO.corelibrary.models.CountriesData;
import com.dotstudioz.dotstudioPRO.corelibrary.models.States;
import com.dotstudioz.dotstudioPRO.corelibrary.models.StatesData;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CardForm extends LinearLayoutCompat implements View.OnFocusChangeListener {
    private static final char space = ' ';
    boolean isBackShowing = false;
    String amount = "$500.00";
    OnPayBtnClickListner onPayBtnClickListner;
    String cardNameError = "Correct Card Name is requierd";
    String cardNumberError = "Correct Card Number is requierd";
    String cvcError = "Correct  cvc is requierd";
    String expiryDateError = "Correct  expiry date is requierd";
    private EditText cardName;
    private EditText cardNumber;
    private TextView paymentAmountTextHolder;
    private TextView previewCardType;
    private EditText cvc;
    private EditText expiryDate;
    private TextView previewCardName;
    private TextView previewCardNumber;
    private TextView previewCvc;
    private TextView previewExpiry;
    private TextView paymentAmount;
    private ViewGroup cardBack;
    private ViewGroup cardFront;
    private Button btnPay;
    private char slash = '/';
    private Spinner countrySpinner;
    private Spinner stateSpinner;
    private String mSelectedCountryID = "231";
    private String mCountryShortName;
    private Countries[] countries;
    private List<String> statesList;
    private States[] states;
    private  ArrayAdapter<String> stateDataAdapter;

    private EditText addressLine1;
    private EditText addressLine2;
    private EditText city;
    private EditText zipCode;
    private EditText firstName;
    private EditText lastName;


    public CardForm(Context context) {
        super(context);
        init();
    }

    public CardForm(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CardForm(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setCardNameError(String cardNameError) {
        this.cardNameError = cardNameError;
    }

    public void setCardNumberError(String cardNumberError) {
        this.cardNumberError = cardNumberError;
    }

    public void setCvcError(String cvcError) {
        this.cvcError = cvcError;
    }

    public void setExpiryDateError(String expiryDateError) {
        this.expiryDateError = expiryDateError;
    }


    public BillingAddress getBillingAddress() {

        BillingAddress billingAddress = new BillingAddress();
        billingAddress.setFirst_name(getString(firstName));
        billingAddress.setLast_name(getString(lastName));
        billingAddress.setBilling_address(getString(addressLine1));
        billingAddress.setBilling_address_2(getString(addressLine2));
        billingAddress.setBilling_city(getString(city));
        billingAddress.setBilling_zip(getString(zipCode));
       // billingAddress.setCoupon(getString());
        String state = stateSpinner.getSelectedItem().toString();
        billingAddress.setBilling_country(mCountryShortName);
        billingAddress.setBilling_state(state);
       return billingAddress;
    }

    public Card getCard() {
        String expiry[] = getString(expiryDate).split(String.valueOf(slash));
        Integer month = 0, year = 0;
        if (expiry.length >= 2) {
            month = Integer.parseInt(expiry[0]);
            year = Integer.parseInt(parseDate(expiry[1]));
        }

        /*DateFormat sdfp = new SimpleDateFormat("yy");
        Date d = null;
        try {
            d = sdfp.parse(String.valueOf(year));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DateFormat sdff = new SimpleDateFormat("yyyy");
        year = Integer.parseInt(sdff.format(d));*/

        return new Card(getString(cardNumber).replaceAll(String.valueOf(space), "")
                , month, year, getString(cvc), getString(cardName), "", "", "", "", "", "", "");
    }

    private String parseDate(String str) {

        int year = Integer.valueOf(str);

        // Allow 5 years in the future for a 2 digit date
        if (year + 100 > new Date().getYear() + 5) {
            year = year + 1900;
        } else {
            year = year + 2000;
        }
        return String.valueOf(year);
    }


    private String getString(EditText ed) {
        return ed.getText().toString().trim();
    }

    private Integer getInt(EditText ed) {
        return Integer.parseInt(ed.getText().toString().trim());
    }

    // Add spinner data

    public void addListenerOnSpinnerItemSelection(){

        countrySpinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        stateSpinner.setOnItemSelectedListener(new CustomStateOnItemSelectedListener());
    }


    private void init() {

        inflate(getContext(), R.layout.cardformlayout1, this);
        /* inflate views */
        countrySpinner = (Spinner) findViewById(R.id.spinner1);
        stateSpinner = (Spinner) findViewById(R.id.stateSpinner);
        cardName = (EditText) findViewById(R.id.card_name);
        cardNumber = (EditText) findViewById(R.id.card_number);
        cvc = (EditText) findViewById(R.id.cvc);
        expiryDate = (EditText) findViewById(R.id.expiry_date);
        previewCardName = (TextView) findViewById(R.id.card_preview_name);
        previewCardNumber = (TextView) findViewById(R.id.card_preview_number);

        previewCvc = (TextView) findViewById(R.id.card_preview_cvc);
        previewExpiry = (TextView) findViewById(R.id.card_preview_expiry);
        paymentAmount = (TextView) findViewById(R.id.payment_amount);
        paymentAmountTextHolder = (TextView) findViewById(R.id.payment_amount_holder);
        previewCardType = (TextView) findViewById(R.id.card_preview_type);
        cardFront = (ViewGroup) findViewById(R.id.card_preview_front);
        cardBack = (ViewGroup) findViewById(R.id.card_preview_back);

        //Billing address

        firstName = (EditText) findViewById(R.id.firstname);
        lastName = (EditText) findViewById(R.id.lastname);
        addressLine1 = (EditText) findViewById(R.id.address_line1);
        addressLine2 = (EditText) findViewById(R.id.address_line2);
        city = (EditText) findViewById(R.id.city);
        zipCode = (EditText) findViewById(R.id.zip_code);


       //Set font style
        firstName.setTypeface(FontsConstants.tfMedium);
        lastName.setTypeface(FontsConstants.tfMedium);
        cardName.setTypeface(FontsConstants.tfMedium);
        cardNumber.setTypeface(FontsConstants.tfMedium);
        cvc.setTypeface(FontsConstants.tfMedium);
        expiryDate.setTypeface(FontsConstants.tfMedium);
        addressLine1.setTypeface(FontsConstants.tfMedium);
        addressLine2.setTypeface(FontsConstants.tfMedium);
        city.setTypeface(FontsConstants.tfMedium);
        zipCode.setTypeface(FontsConstants.tfMedium);
        previewCardName.setTypeface(FontsConstants.tfMedium);
        previewCardNumber.setTypeface(FontsConstants.tfMedium);
        previewCvc.setTypeface(FontsConstants.tfMedium);
        previewExpiry.setTypeface(FontsConstants.tfMedium);
        paymentAmount.setTypeface(FontsConstants.tfMedium);
        paymentAmountTextHolder.setTypeface(FontsConstants.tfMedium);
        previewCardType.setTypeface(FontsConstants.tfMedium);


        //Set Focus
        firstName.setOnFocusChangeListener(this);
        lastName.setOnFocusChangeListener(this);
        cardName.setOnFocusChangeListener(this);
        cardNumber.setOnFocusChangeListener(this);
        cvc.setOnFocusChangeListener(this);
        expiryDate.setOnFocusChangeListener(this);
        addressLine1.setOnFocusChangeListener(this);
        addressLine2.setOnFocusChangeListener(this);
        city.setOnFocusChangeListener(this);
        zipCode.setOnFocusChangeListener(this);

        //Testing purpose

//        firstName.setText("Kishore");
//        lastName.setText("Adduri");
//        cardName.setText("Kishore");
//        cardNumber.setText("4111 1111 1111 1111");
//        cvc.setText("789");
//        expiryDate.setText("02/20");
//        addressLine1.setText("1618 Quebec St");
//        addressLine1.setText("");
//        addressLine2.setText("Unit 1016");
//        city.setText("Hyderabad");
//        zipCode.setText("V6AOC5");


        btnPay = (Button) findViewById(R.id.btn_pay);
        btnPay.setTypeface(FontsConstants.tfMedium);
        paymentAmount.setText(amount);
        try {
            Gson gson = new Gson();
            //FileInputStream fis = new FileInputStream(getAssets().open("countries.json"));
            BufferedReader bfr = new BufferedReader(new InputStreamReader(getContext().getAssets().open("countries.json")));
            JsonReader reader = new JsonReader(bfr);
            CountriesData data = gson.fromJson(reader, CountriesData.class);
            countries = data.getCountries();

        }catch (Exception ex){
            ex.printStackTrace();

        }

        try {
            Gson gson = new Gson();
            //FileInputStream fis = new FileInputStream(getAssets().open("countries.json"));
            BufferedReader bfr = new BufferedReader(new InputStreamReader(getContext().getAssets().open("states.json")));
            JsonReader reader = new JsonReader(bfr);
            StatesData data = gson.fromJson(reader, StatesData.class);
            states = data.getStates();
            //Log.d("MainActivity","Country name"+statesData[0].getName());
        }catch (Exception ex){
            ex.printStackTrace();

        }
        int pos = 0;
        List<String> list = new ArrayList<String>();
        for(int i=0;i<countries.length;i++)
        {
            list.add(countries[i].getName());
           if(countries[i].getId().equals(mSelectedCountryID))
           {
               pos = i;
               mCountryShortName = countries[i].getSortname();
           }
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
                (getContext(), R.layout.custom_textview_to_spinner,list);
        dataAdapter.setDropDownViewResource(R.layout.custom_textview_to_spinner);
        countrySpinner.setAdapter(dataAdapter);

        countrySpinner.setSelection(pos);

        statesList = getStatesList(mSelectedCountryID);
        stateDataAdapter = new ArrayAdapter<String>
                (getContext(), R.layout.custom_textview_to_spinner,statesList);
        stateDataAdapter.setDropDownViewResource(R.layout.custom_textview_to_spinner);
        stateDataAdapter.clear();
        stateDataAdapter.addAll(statesList);
        stateSpinner.setAdapter(stateDataAdapter);
        stateSpinner.setSelection(0);
        addListenerOnSpinnerItemSelection();
        btnPay.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(getString(cardName))) {
                    cardName.setError(cardNameError);
                    return;
                }
                if (TextUtils.isEmpty(getString(cardNumber))) {
                    cardNumber.setError(cardNumberError);
                    return;
                }

                if (TextUtils.isEmpty(getString(cvc))) {
                    cvc.setError(cvcError);
                    return;
                }
//                if (TextUtils.isEmpty(getString(expiryDate))) {
//                    expiryDate.setError(expiryDateError);
//                    return;
//                }

                if (cardIsvalid()) {
                    Log.d("CardForm","cardIsvalid"+onPayBtnClickListner);
                    onPayBtnClickListner.onClick(getCard(),getBillingAddress());
                }
            }
        });


        cardNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Remove spacing char
                if (editable.length() > 0 && (editable.length() % 5) == 0) {
                    final char c = editable.charAt(editable.length() - 1);
                    if (space == c) {
                        editable.delete(editable.length() - 1, editable.length());
                    }
                }
                // Insert char where needed.
                if (editable.length() > 0 && (editable.length() % 5) == 0) {
                    char c = editable.charAt(editable.length() - 1);
                    // Only if its a digit where there should be a space we insert a space
                    if (Character.isDigit(c) && TextUtils.split(editable.toString(), String.valueOf(space)).length <= 3) {
                        editable.insert(editable.length() - 1, String.valueOf(space));

                    }
                }

                if (editable.length() >= 16) {
                    previewCardType.setText(new Card(editable.toString(), 0, 0, "").getBrand());
                }
                previewCardNumber.setText(editable.toString());
            }
        });

        expiryDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                switch (editable.length()) {
                    case 1:
                        if (Integer.parseInt(editable.toString()) > 1) {
                            editable.clear();
                        }
                        break;

                    case 2:
                        if (((int) editable.charAt(0)) > 0) {
                            if (((int) editable.charAt(1)) > 2) {
                                editable.delete(1, 1);
                            }
                        }
                }

                if (editable.length() > 0 && (editable.length() % 3) == 0) {
                    char c = editable.charAt(editable.length() - 1);

                    if (Character.isDigit(c)) {
                        editable.insert(editable.length() - 1, String.valueOf(slash));

                    }
                }

                previewExpiry.setText(editable.toString());
            }
        });


        cardName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (editable.toString().trim().length() > 0) {
                    previewCardName.setText(editable.toString());
                }
            }
        });


        cvc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().trim().length() > 0) {
                    previewCvc.setText(editable.toString());
                }
            }
        });


        cvc.setOnFocusChangeListener(
                new OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View view, boolean b) {
                        if (b) showBack();
                    }
                });

        cardName.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) showFront();
            }
        });

        cardNumber.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) showFront();
            }
        });

        expiryDate.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) showFront();
            }
        });
    }

    private boolean cardIsvalid() {
        Card card = getCard();
        if (!card.validateNumber()) {
            cardNumber.setError(cardNumberError);
        }
        if (!card.validateExpiryDate()) {
            expiryDate.setError(expiryDateError);
        }
        if (!card.validateCVC()) {
            cvc.setError(expiryDateError);
        }

        return card.validateCard();
    }

    private void showBack() {
        if (!isBackShowing) {
            Animator cardFlipLeftIn = AnimatorInflater.loadAnimator(getContext(), R.animator.card_flip_left_in);
            cardFlipLeftIn.setTarget(cardFront);
            cardFlipLeftIn.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    cardFront.setVisibility(GONE);
                    cardBack.setVisibility(VISIBLE);
                    isBackShowing = true;
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });
            cardFlipLeftIn.start();
        }
    }


    private void showFront() {


        if (isBackShowing) {
            Animator cardFlipRightIn = AnimatorInflater.loadAnimator(getContext(), R.animator.card_flip_right_in);
            cardFlipRightIn.setTarget(cardBack);
            cardFlipRightIn.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animator) {

                }

                @Override
                public void onAnimationEnd(Animator animator) {
                    cardBack.setVisibility(GONE);
                    cardFront.setVisibility(VISIBLE);
                    isBackShowing = false;
                }

                @Override
                public void onAnimationCancel(Animator animator) {

                }

                @Override
                public void onAnimationRepeat(Animator animator) {

                }
            });

            cardFlipRightIn.start();

        }

    }

    public void setPayBtnClickListner(OnPayBtnClickListner onPayBtnClickListner) {
        this.onPayBtnClickListner = onPayBtnClickListner;
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
    }

    public class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int pos,
                                   long id) {

            TextView tv = (TextView) view;
            tv.setTextColor(Color.parseColor("#000000"));
            tv.setText(parent.getItemAtPosition(pos).toString());
            ((TextView) parent.getChildAt(0)).setTextSize(20);

            mSelectedCountryID = countries[pos].getId();
            countries[pos].getSortname();
            statesList = getStatesList(mSelectedCountryID);

            stateDataAdapter.clear();
            stateDataAdapter.addAll(statesList);
            stateSpinner.setAdapter(stateDataAdapter);
            stateSpinner.setSelection(0);
          //  stateDataAdapter.notifyDataSetChanged();
            //stateSpinner.setSelection(0);

        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub

        }
    }
    private List<String> getStatesList(String id){
        List<String> statesList = new ArrayList<String>();
        for(int i=0;i<states.length;i++)
        {
            if(states[i].getCountry_id().equals(id))
            {
                statesList.add(states[i].getName());
            }
        }
        return statesList;
    }

    public class CustomStateOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view, int pos,
                                   long id) {

            TextView tv = (TextView) view;
            tv.setTextColor(Color.parseColor("#000000"));
            tv.setText(parent.getItemAtPosition(pos).toString());
            ((TextView) parent.getChildAt(0)).setTextSize(20);
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub

        }
    }
}
