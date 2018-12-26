package com.dotstudioz.dotstudioPRO.corelibrary.yesnofragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dotstudioz.dotstudioPRO.corelibrary.R;

/**
 * Use the {@link FragmentYesNo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentYesNo extends Fragment {
    private Button yesButton;
    private Button noButton;

    // saving this instance so that it can be used to reference
    // and remove it from the parent activity
    Fragment thisFragment;

    public FragmentYesNo() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment FragmentYesNo.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentYesNo newInstance() {
        FragmentYesNo fragment = new FragmentYesNo();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_yes_no, container, false);
        view.setBackgroundColor(Color.parseColor("#000000"));

        yesButton = (Button) view.findViewById(R.id.yesButton);
        noButton = (Button) view.findViewById(R.id.noButton);

        yesButton.requestFocus();
        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.remove(thisFragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                ft.commit();
            }
        });

        thisFragment = this;

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
