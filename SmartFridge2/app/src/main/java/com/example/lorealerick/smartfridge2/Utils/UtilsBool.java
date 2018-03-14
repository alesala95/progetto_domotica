package com.example.lorealerick.smartfridge2.Utils;

/**
 * Created by itsadmin on 14/03/2018.
 */

public class UtilsBool {

    public static boolean intToBool (int intero){

        boolean convert = false;

        if (intero == 1){

            convert = true;
        }else if(intero == 0){

            convert = false;
        }

        return convert;
    }

    public static int boolToInt (boolean bool){

        int convert = 0;

        if(bool){

            convert = 1;
        }else{

            convert = 0;
        }

        return convert;
    }

}
