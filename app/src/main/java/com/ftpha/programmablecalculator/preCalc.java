/*
 * Copyright (c) 2015. Fernando Toro. All rights reserved
 */

package com.ftpha.programmablecalculator;

import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

/**
 * Created by Fernando on 2015-05-25.
 * Originally created as part of: preCalc2
 * You will love this code and be awed by it's magnificence
 */
public class preCalc {
//    public int n1 = 3;
//    public int n2 = 2;
//    public String Opp;
//
    private  Context cx;
//
    public preCalc(Context context){
        cx = context;
    }
//
//    @JavascriptInterface
//    public int getN1(){
//        return n1;
//    }
//    @JavascriptInterface
//    public int getN2(){
//        return n2;
//    }
//    @JavascriptInterface
//    public String getOpp(){
//        return Opp;
//    }
//
    @JavascriptInterface
    public void setResult(String theResult){
        ftG.result = theResult;
        ftG.answ = theResult;
        ftG.showResult();
    }
//
    @JavascriptInterface
    public void showToast(String mssg){
        Toast.makeText(cx, mssg, Toast.LENGTH_SHORT).show();
    }

    public void T(String mssg){
        showToast(mssg);
    }
//
//    public void myLog(String mssg){
//
//        Log.i("Xx. ftpha .xX -------->",
//                mssg);
//
//    }
//
//    @JavascriptInterface
//    public void g(String message){
//        myLog(message);
//    }

}
