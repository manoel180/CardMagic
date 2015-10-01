package br.com.cardmagic;

import android.app.Activity;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TableLayout;
import android.widget.Toast;

import static br.com.cardmagic.R.layout.activity_main;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        ViewGroup gridLayout = (ViewGroup) findViewById(R.id.cards);
        initCards(gridLayout);

    }
    private void initCards(ViewGroup layout) {
        getLayoutInflater().inflate(R.layout.card1, layout);
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
