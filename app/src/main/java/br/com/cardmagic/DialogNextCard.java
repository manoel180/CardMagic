package br.com.cardmagic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.view.Window;
import android.widget.Button;


import roboguice.fragment.RoboDialogFragment;
import roboguice.inject.InjectView;

/**
 * Created by manoel on 01/10/15.
 * http://developer.android.com/reference/android/app/DialogFragment.html
 */


public class DialogNextCard extends RoboDialogFragment {

    @InjectView(R.id.btnCancel)
    private Button btnCancel;

    @InjectView(R.id.btnYes)
    private Button btnYes;

    @InjectView(R.id.cards)
    private ViewGroup gridLayout;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        this.setStyle(android.support.v4.app.DialogFragment.STYLE_NO_FRAME, R.style.dialog_default);
        this.setCancelable(false);
        super.onCreate( savedInstanceState );
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);


        return inflater.inflate(R.layout.dialog_card, container,
                false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();

    }


    private void init() {
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissAllowingStateLoss();
            }
        });


        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gridLayout.inflate(getContext(),R.layout.card2,gridLayout);
            }
        });
    }
}
