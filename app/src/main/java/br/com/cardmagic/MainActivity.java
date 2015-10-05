package br.com.cardmagic;

import android.annotation.SuppressLint;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;

import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;

import roboguice.activity.RoboFragmentActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;


@ContentView(R.layout.activity_main)
public class MainActivity extends RoboFragmentActivity{


    @InjectView(R.id.cards)
    private ViewGroup gridLayout;
    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        //delayedHide(100);
        initCards();
    }

    @Override
    protected void onResume() {
        super.onResume();
        delayedHide(100);
    }

    private void hide() {

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            gridLayout.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };

    private final Handler mHideHandler = new Handler();
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };

    /**
     * Schedules a call to hide() in [delay] milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }

    private void initCards() {
        getLayoutInflater().inflate(R.layout.card1, gridLayout);
        counterDown().start();
    }


    /**
     * @return
     *
     */
    public CountDownTimer counterDown(){
        return  new CountDownTimer(10000, 1000) {

            public void onTick(long millisUntilFinished) {}

            public void onFinish() {
                DialogNextCard nextCard = new DialogNextCard();

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.add(nextCard, null);
                ft.commitAllowingStateLoss();


            }
        };
    }

}
