/*
 * Copyright (c) 2015. Fernando Toro. All rights reserved
 */

package com.ftpha.programmablecalculator;

import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Fernando on 2015-05-25.
 * Originally created as part of: preCalc2
 * You will love this code and be awed by it's magnificence
 */
public class preCalc {

    private  Context cx;

    private static EditText theDisp;

    public preCalc(Context context){
        cx = context;
    }


    public static void connDisp(EditText disp){
        theDisp = disp;
    }

    @JavascriptInterface
    public String getX(){
        return ftG.elX;
    }

    @JavascriptInterface
    public void setD(final String theResult){

        ftG.result = theResult;
        ftG.answ = theResult;

        theDisp.post(new Runnable() {
            @Override
            public void run() {
                theDisp.setText(theResult);
            }
        });
    }

    @JavascriptInterface
    public void showToast(String mssg){
        Toast.makeText(cx, mssg, Toast.LENGTH_SHORT).show();
    }

    public void T(String mssg){
        showToast(mssg);
    }


}
