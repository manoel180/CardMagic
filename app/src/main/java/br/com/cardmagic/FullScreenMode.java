package br.com.cardmagic;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import roboguice.activity.RoboActivity;

/**
 * Created by manoel on 01/10/15.
 */
public class FullScreenMode {


    public static void setFullScreen(final boolean fullscreen) {
        // Hide/show the system bar
        final Window window = ((RoboActivity) context).getWindow();

        if (Build.VERSION.SDK_INT < 16) {
            window.setFlags(fullscreen ? WindowManager.LayoutParams.FLAG_FULLSCREEN : 0,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }


        if (fullscreen) {
            // Keep the system bar hidden in full screen mode
            window.getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
                @Override
                public void onSystemUiVisibilityChange(int visibility) {
                    if ((visibility & View.SYSTEM_UI_FLAG_HIDE_NAVIGATION) != 0) {
                        window.setFlags(
                                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                                WindowManager.LayoutParams.FLAG_FULLSCREEN);
                    }
                    else {
                        window.getDecorView().setSystemUiVisibility(
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
