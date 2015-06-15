package com.ftpha.programmablecalculator;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.LinearLayout;

import Model.Calc;
import Model.CalcH;

/**
 * Created by Fernando on 2015-06-01.
 * Originally created as part of: Programmable Calculator
 * You will love this code and be awed by it's magnificence
 */
public class ftG {

    public static String dbName = "ftProgCalc.db";
    public static int dbVersion = 1;

    public static boolean showLogs = true;
    public static String logTag = "ftpha - shdcalcdm - ";

    public static MainActivity mA;

    public static String equation;
    public static String display;
    public static String subDisplay;

    public static String clcMode;

    public static String result;

    public static String thisNum;


    public static String answ;

    public static LinearLayout tlll;

    public static Context ctx;

    public static Calc clc;
    public static CalcH clcH;

    public static int makeBtnId(long lid, long id){

        int a = (100*((int)lid + 3) + ((int)id + 1));

        return a;

    }

    public static void appendDisplay(MainActivity a,String strToAppend){
        display += strToAppend;
        thisNum += strToAppend;
        a.setD(display);
    }


    public static void setDisplay(MainActivity a,String strToSet){
        display = strToSet;
        a.setD(display);
    }


    public static void showDisplay(MainActivity a){

        a.setD(display);

    }


    public static void setMainAct(MainActivity a){

        mA = a;

    }

    public static void showResult(){

        mA.setD(result);

    }

    public static boolean putDecimal(){


        return isNumeric(ftG.thisNum);
    }

    public static boolean isNumeric(String str)
    {
        String a = "0" + str + ".2";
        return a.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }

    public static void L(String msg) {
        if (showLogs) {
            Log.i(logTag, msg);
        }
    }

}
