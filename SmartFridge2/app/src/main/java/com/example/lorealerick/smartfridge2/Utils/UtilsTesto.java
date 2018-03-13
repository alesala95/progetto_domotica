package com.example.lorealerick.smartfridge2.Utils;

/**
 * Created by itsadmin on 13/03/2018.
 */

public class UtilsTesto {

    public static String letteraMaiuscola (String parola){

        if(parola != null)
            if(parola.length() > 0)
                return Character.toUpperCase(parola.charAt(0))+parola.substring(1);

        return null;
    }
}
