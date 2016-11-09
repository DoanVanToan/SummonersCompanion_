package com.toandoan.lol.utility;

import android.util.Log;

import com.toandoan.lol.constant.Constant;

/**
 * Created by ToanDoan on 9/29/2016.
 */

public class LogUtil {
    public static void e(String title, String message){
        if (Constant.Config.DEBUG){
            Log.e(title, message);
        }
    }
}
