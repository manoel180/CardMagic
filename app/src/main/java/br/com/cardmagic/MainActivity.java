package br.com.cardmagic;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        executeDelayed();
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView(){
        ViewGroup gridLayout = (ViewGroup) findViewById(R.id.cards);
        initCards(gridLayout);
    }
    private void initCards(ViewGroup layout) {
//        for (Integer value : getResources().getIntArray(R.array.card2)){
//            TextView txt = new TextView(this);
//
//            txt.setText(value.toString());
//            gridLayout.addView(txt);
//
//        }

        //layout.addView((ViewGroup)(R.layout.card1));
        this.setContentView(R.layout.card1);
    }


    private void executeDelayed() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // execute after 500ms
                hideNavBar();
            }
        }, 500);
    }


    private void hideNavBar() {
        if (Build.VERSION.SDK_INT >= 19) {
            View v = getWindow().getDecorView();
            v.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN);
                //    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

}
