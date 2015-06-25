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
        return ftG.thisNum;
    }

    @JavascriptInterface
    public void setX(final String theResult){

        String myX = ftG.thisNum;
        String myD = ftG.display;
        myD = myD.substring(0, myD.lastIndexOf(myX));
        final String myR = myD + theResult;

        ftG.thisNum = theResult;
        ftG.display = myR;

        theDisp.post(new Runnable() {
            @Override
            public void run() {
                theDisp.setText(myR);
            }
        });
    }

    @JavascriptInterface
    public String getFlag(){
        return ftG.usrFlag;
    }

    @JavascriptInterface
    public void setFlag(final String theResult){

        ftG.usrFlag = theResult;
    }

    @JavascriptInterface
    public void addM(final String strToAdd){
        ftG.mA.addAMem(strToAdd);
    }
    @JavascriptInterface
    public void setD(final String theResult){

        ftG.display = theResult;
        ftG.thisNum = theResult;

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
