package br.com.cardmagic;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.view.Window;
import android.widget.Button;


import roboguice.fragment.RoboDialogFragment;
import roboguice.inject.InjectView;

/**
 * Created by manoel on 01/10/15.
 */


public class DialogNextCard extends RoboDialogFragment {

    @InjectView(R.id.btnNo)
    private Button btnNo;

    @InjectView(R.id.btnYes)
    private Button btnYes;

    private CallbackNextCards callbackNextCards;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        this.setStyle(android.support.v4.app.DialogFragment.STYLE_NO_FRAME, R.style.dialog_default);
        this.setCancelable(false);
        super.onCreate(savedInstanceState);
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

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callbackNextCards.onClickNo();
                dismissAllowingStateLoss();
            }
        });


        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callbackNextCards.onClickYes();
                dismiss();
            }
        });
    }

    public void setOnClickYes( CallbackNextCards onClickYes) {
        callbackNextCards = onClickYes;
    }
}
