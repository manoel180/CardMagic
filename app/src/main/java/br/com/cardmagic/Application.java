package br.com.cardmagic;

import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by manoel on 01/10/15.
 */
public class Application extends android.app.Application {


    @Override
    public void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT < 16) {
            ((Activity) getApplicationContext()).getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                   WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
       setFullScreen(true);


    }



    private void setFullScreen(final boolean fullscreen) {
        // Hide/show the system bar
        Window window = getWindow();
        window.setFlags(fullscreen ?
                        WindowManager.LayoutParams.FLAG_FULLSCREEN : 0,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if (fullscreen) {
            // Keep the system bar hidden in full screen mode
            window.getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
                @Override
                public void onSystemUiVisibilityChange(int visibility) {
                    if ((visibility & View.SYSTEM_UI_FLAG_HIDE_NAVIGATION) != 0) {
                        getWindow().setFlags(
                                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                                WindowManager.LayoutParams.FLAG_FULLSCREEN);
                    }
                    else {
                        getWindow().getDecorView().setSystemUiVisibility(
                                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | 0x4);
                    }
                }
            });
        }
        else {
            window.getDecorView().setOnSystemUiVisibilityChangeListener(null);
        }
        window.getDecorView().setSystemUiVisibility(
                fullscreen ? (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | 0x4) : 0);
    }
}
