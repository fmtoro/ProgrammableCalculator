/*
 * Copyright (c) 2015. Fernando Toro. All rights reserved
 */

package com.ftpha.programmablecalculator;

import android.content.Context;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.EditText;
import android.widget.Toast;

import Model.uVar;

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

/////////////////////////////////////////////////////////////////////////////////////////
    @JavascriptInterface
    public String get(int level){
        return ftG.YzS.get(level);
    }

    @JavascriptInterface
    public String getX(){
        return ftG.YzS.get();
    }
    @JavascriptInterface
    public void appendX(final String value){

        String myD = ftG.display;
        final String myR = myD + " " + value;

        //ftG.thisNum = theResult;
        ftG.YzS.add(value,true);
        ftG.display = myR;

        theDisp.post(new Runnable() {
            @Override
            public void run() {
                theDisp.setText(myR);
            }
        });
    }

    @JavascriptInterface
    public void setX(final String value){

        String myX = ftG.YzS.get();
        String myD = ftG.display;
        int lastOne = myD.lastIndexOf(myX);
        myD = myD.substring(0, lastOne);
        final String myR = myD + value;

        //ftG.thisNum = theResult;
        //ftG.YzS.pop();
        ftG.YzS.add(value,true);
        ftG.display = myR;

        theDisp.post(new Runnable() {
            @Override
            public void run() {
                theDisp.setText(myR);
            }
        });
    }

    @JavascriptInterface
    public void setD(final String value){

        ftG.display = value;
        //ftG.thisNum = theResult;
        ftG.YzS.add(value,true);

        theDisp.post(new Runnable() {
            @Override
            public void run() {
                theDisp.setText(value);
            }
        });
    }


/////////////////////////////////////////////////////////////////////////////////////////




//    @JavascriptInterface
//    public void setEqualFlag(){
//
//        ftG.usrHasEqualFlag = true;
//
//    }
//
//    @JavascriptInterface
//    public void setAfterEqualCode(final String flag){
//
//        ftG.usrTheEqualCode += flag;
//    }

    @JavascriptInterface
    public void putFlag(final String flag){

        ftG.usrFlag += muFg(flag);
    }

    @JavascriptInterface
    public boolean peekFlag(String flag){

        return ftG.usrFlag.contains(muFg(flag));

    }

    @JavascriptInterface
    public boolean takeFlag(String flag){

        boolean rv = ftG.usrFlag.contains(muFg(flag));

        if (rv) {
            ftG.usrFlag = ftG.usrFlag.replace(muFg(flag),"");
        }

        return rv;

    }


    private String muFg(String baseFg){
        return "<@" + baseFg + "@>";
    }



    /////////////////////////////////////////////////////////////////////////////////////////
//    @JavascriptInterface
//    public void addM(final String strToAdd){
////        No worky
//        ftG.mA.addAMem(strToAdd);
//    }



    @JavascriptInterface
    public void showToast(String mssg){
        Toast.makeText(cx, mssg, Toast.LENGTH_SHORT).show();
    }

    @JavascriptInterface
    public void T(String mssg){
        showToast(mssg);
    }

    @JavascriptInterface
    public void showDialog(){
        ftG.msgBox();
    }

    @JavascriptInterface
    public void dialogTitle(String title){
        ftG.dialogTitle = title;
    }

    @JavascriptInterface
    public void dialogHeading(String heading){
        ftG.dialogHeading = heading;
    }

    @JavascriptInterface
    public void dialogContent(String content){
        ftG.dialogContent = content;
    }

    @JavascriptInterface
    public void dialogBtnPositive(String btnText){
        ftG.dialogBtnPositive = btnText;
    }

    @JavascriptInterface
    public void dialogBtnNegative(String btnText){
        ftG.dialogBtnNegative = btnText;
    }

    @JavascriptInterface
    public void dialogBtnNeutral(String btnText){
        ftG.dialogBtnNeutral = btnText;
    }


    @JavascriptInterface
    public void setV(String key, String val){
        boolean found = false;

        for (uVar v : ftG.clc.uVars) {
            if (v.key.equals(key)) {
                v.val = val;
                found = true;
                break;
            }
        }

        if (!found) {
            uVar iV = new uVar();
            iV.key = key;
            iV.val = val;
        }

    }

    @JavascriptInterface
    public String getV(String key){

        for (uVar v : ftG.clc.uVars) {
            if (v.key.equals(key)) {
                return v.val;
            }
        }

        return  "";

    }

}
