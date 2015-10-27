package br.com.cardmagic;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

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

//    InterstitialAd mInterstitialAd;

    @InjectView(R.id.cards)
    private ViewGroup gridLayout;

    private TextView txtResult;

    private Button btnRecomecar;

    private Button btnStart;


    private Button btnExit;


    private AdView mAdView;

    @Inject
    private DialogNextCard nextCard;

    private Map<Integer, Integer> cards; /** Lista dos Cartoes **/
    private Map<Integer, Integer> cardsSorterds; /** Lista dos Cartoes **/
    private List<Integer> cardsSelected; /** Lista dos Cartoes escolhidos**/
    private int cardsNumbers[]; /** Lista com os numeros dos cartoes **/

    private int resultFinal;

    private AdRequest adRequest;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private int card;
    private int index;

    private void requestNewInterstitial() {

      adRequest = new AdRequest.Builder()
               // .addTestDevice("4E441F0D2F1FEBCDB67463195D4E85A2")
                //.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
    }


    private void hideSystemUI() {
        // Set the IMMERSIVE flag.
        // Set the content to appear under the system bars so that the content
        // doesn't resize when the system bars hide and show.
        final View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

      @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
          mAdView = (AdView) findViewById(R.id.ad_view);
//          mInterstitialAd = new InterstitialAd(this);
//          mInterstitialAd.setAdUnitId("ca-app-pub-4789799531129270/8979008505");

          requestNewInterstitial();
        // Start loading the ad in the background.
          mAdView.loadAd(adRequest);

          initGame();
        final View decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(
                new View.OnSystemUiVisibilityChangeListener() {
                    @Override
                    public void onSystemUiVisibilityChange(int i) {
                        hideSystemUI();
                    }
                });
    }

    private void initGame() {
        mAdView.setVisibility(View.VISIBLE);
       // showInterstitial();
        getLayoutInflater().inflate(R.layout.layout_quest, gridLayout);
        btnStart = (Button) findViewById(R.id.btnStart);

            btnStart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    initCards();
                }
            });


        btnExit  = (Button) findViewById(R.id.btnExit);
        if(btnExit != null) {
            btnExit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    exit();
                }
            });
        }
    }


    private void initCards() {
        mAdView.setVisibility(View.INVISIBLE);
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
        return  new CountDownTimer(5000, 1000) {

            public void onTick(long millisUntilFinished) {}

            public void onFinish() {
                if(this != null) {
                    nextCard.setOnClickYes(MainActivity.this);

                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.add(nextCard, null);
                    ft.commitAllowingStateLoss();
                }

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

//    public void showInterstitial() {
//        if (mInterstitialAd.isLoaded()) {
//            mInterstitialAd.show();
//        }
//    }


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
               // showInterstitial();
                gridLayout.removeAllViewsInLayout();
                initGame();
            }
        });


        btnExit  = (Button) findViewById(R.id.btnExit);
        if(btnExit != null) {
            btnExit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    exit();
                }
            });
        }

        for(Integer i : cardsSelected){
          resultFinal +=  cardsNumbers[i];
        }
        txtResult.setText(String.valueOf(resultFinal));
    }

    private int getCard(){

        SecureRandom random = new SecureRandom();
        index = random.nextInt(6);
        if(cardsSorterds.containsKey(index)){
            return getCard();
        }else{
            cardsSorterds.put(index, card);
            card = cards.get(index);
            return card;
        }
    }

    private void exit(){ finishAffinity();

    }

    @Override
    protected void onStop() {
        super.onStop();
        counterDown().cancel();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                getWindow().getDecorView().setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_FULLSCREEN
                                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
            }
        }
    }
}
