package br.com.cardmagic;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

/**
 * Created by manoel on 01/10/15.
 */
public class DialogNextCard extends Dialog {

    private Button btnCancel;
    private Button btnYes;

    public DialogNextCard(Context context) {
        super(context);
    }

    public DialogNextCard(Context context, int themeResId) {
        super(context, themeResId);
    }

    protected DialogNextCard(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_card);
        init();
    }

    private void init() {
        btnCancel = (Button) this.findViewById(R.id.btnCancel);
        btnYes = (Button) this.findViewById(R.id.btnYes);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
