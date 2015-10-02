package br.com.cardmagic;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.ViewGroup;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_main)
public class MainActivity extends RoboActivity {

    @InjectView(R.id.cards)
    private ViewGroup gridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        FullScreenMode.setFullScreen(true);
    }

    private void initView(){
        initCards();

    }
    private void initCards() {
        getLayoutInflater().inflate(R.layout.card1, gridLayout);
        counterDown().start();
    }



    private CountDownTimer counterDown(){
       return  new CountDownTimer(10000, 1000) {

            public void onTick(long millisUntilFinished) {}

            public void onFinish() {
                DialogNextCard nextCard = new DialogNextCard(MainActivity.this);
                nextCard.show();
            }
        };
    }

}
