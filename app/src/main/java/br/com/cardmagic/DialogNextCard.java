package br.com.cardmagic;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;


import roboguice.fragment.RoboDialogFragment;
import roboguice.inject.ContentView;
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

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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

            }
        });
    }
}
