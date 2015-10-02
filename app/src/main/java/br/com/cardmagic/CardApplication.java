package br.com.cardmagic;

import android.app.Application;
import android.content.Context;

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
