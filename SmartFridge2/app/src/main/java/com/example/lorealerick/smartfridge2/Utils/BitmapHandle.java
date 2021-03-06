package com.example.lorealerick.smartfridge2.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

/**
 * Created by LoreAleRick on 10/03/2018.
 */

public class BitmapHandle {

    public static byte [] getBytes (Bitmap bitmap){

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,50,stream);

        return stream.toByteArray();
    }

    public static Bitmap getBitmap (byte [] image){

        return BitmapFactory.decodeByteArray(image,0,image.length);
    }
}
