package com.example.lorealerick.smartfridge2.Utils;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.lorealerick.smartfridge2.R;

/**
 * Created by itsadmin on 13/03/2018.
 */

public class UtilsAnimation {

    public static void startFadeInAnimation(View view, Context c) {

        Animation startAnimation = AnimationUtils.loadAnimation(c, R.anim.fade_in);
        view.startAnimation(startAnimation);
    }
}
