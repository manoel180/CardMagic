package br.com.cardmagic;

import android.app.Application;
import android.content.Context;

import com.splunk.mint.Mint;

import roboguice.inject.ContextSingleton;

/**
 * Created by manoel on 02/10/15.
 */

@ContextSingleton
public class CardApplication extends Application {

    private static CardApplication instance;
    private static Context ctx;

    public CardApplication() {
        setInstance(this);
        ctx = CardApplication.getInstance();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // The following line triggers the initialization of ACRA
      //  ACRA.init(this);

    }

    public static Context getContext() {
        return ctx;

    }

    public static void setContext(Context ctx) {
        CardApplication.ctx = ctx;
    }



    public static CardApplication getInstance() {
        return instance;
    }



    public static void setInstance(CardApplication instance) {
        CardApplication.instance = instance;
    }
}
