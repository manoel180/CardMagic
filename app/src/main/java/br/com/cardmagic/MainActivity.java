package br.com.cardmagic;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;


import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import javax.inject.Inject;

import roboguice.RoboGuice;
import roboguice.activity.RoboFragmentActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

import static br.com.cardmagic.R.layout.activity_main;
import static br.com.cardmagic.R.layout.card1;
import static br.com.cardmagic.R.layout.card2;
import static br.com.cardmagic.R.layout.card3;
import static br.com.cardmagic.R.layout.card4;
import static br.com.cardmagic.R.layout.card5;
import static br.com.cardmagic.R.layout.card6;
import static br.com.cardmagic.R.layout.layout_result;


@ContentView(activity_main)
public class MainActivity extends RoboFragmentActivity implements CallbackNextCards{


    @InjectView(R.id.cards)
    private ViewGroup gridLayout;

    @Nullable
    @InjectView(R.id.resultFinal)
    private TextView txtResult;

    @Nullable
    @InjectView(R.id.btnRecomecar)
    private Button btnRecomecar;

    @Inject
    private DialogNextCard nextCard;

    private Map<Integer, Integer> cards; /** Lista dos Cartoes **/
    private Map<Integer, Integer> cardsSorterds; /** Lista dos Cartoes **/
    private List<Integer> cardsSelected; /** Lista dos Cartoes escolhidos**/
    private int cardsNumbers[]; /** Lista com os numeros dos cartoes **/

    private int resultFinal;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private int card;
    private int index;

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

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
        cards = new android.support.v4.util.ArrayMap<Integer, Integer>();
        cardsSorterds = new android.support.v4.util.ArrayMap<Integer, Integer>();
        cardsSelected = new ArrayList<Integer>();
        cards.put(0, card1);
        cards.put(1, card2);
        cards.put(2, card3);
        cards.put(3, card4);
        cards.put(4, card5);
        cards.put(5, card6);

        resultFinal = 0;
        card = 0;
        index = 0;

        cardsNumbers = getResources().getIntArray(R.array.cards);
        getLayoutInflater().inflate(getCard(), gridLayout);
        counterDown().start();
    }


    /**
     * @return
     *
     */
    public CountDownTimer counterDown(){
        return  new CountDownTimer(2000, 1000) {

            public void onTick(long millisUntilFinished) {}

            public void onFinish() {
                nextCard.setOnClickYes(MainActivity.this);

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.add(nextCard, null);
                ft.commitAllowingStateLoss();


            }
        };
    }


    @Override
    public void onClickYes() {
        cardsSelected.add(index);
        updateCard();
    }

    @Override
    public void onClickNo() {
        updateCard();
    }


    private void updateCard(){
        if(cardsSorterds.size()<6) {
            gridLayout.removeAllViewsInLayout();
            getLayoutInflater().inflate(getCard(), gridLayout);
            counterDown().start();
        }else {
            gridLayout.removeAllViewsInLayout();
            getLayoutInflater().inflate(layout_result, gridLayout);
            countResult();
        }

    }

    private void countResult() {

        txtResult = (TextView) findViewById(R.id.resultFinal);
        btnRecomecar = (Button) findViewById(R.id.btnRecomecar);
        btnRecomecar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gridLayout.removeAllViewsInLayout();
                initCards();
            }
        });

        for(Integer i : cardsSelected){
          resultFinal +=  cardsNumbers[i];
        }
        txtResult.setText(String.valueOf(resultFinal));
    }

    private int getCard(){

        SecureRandom random = new SecureRandom();
        index = random.nextInt(6);
        Log.e("Card-Index-",String.valueOf(index));
        if(cardsSorterds.containsKey(index)){
            return getCard();
        }else{
            cardsSorterds.put(index,card);
            card = cards.get(index);
            return card;
        }
    }
}
