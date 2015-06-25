package com.ftpha.programmablecalculator;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import Model.Calc;
import Model.CalcH;
import Model.cBtn;

/**
 * Created by Fernando on 2015-06-01.
 * Originally created as part of: Programmable Calculator
 * You will love this code and be awed by it's magnificence
 */
public class ftG {

    public static boolean editM = false;
    public static Activity currActivity;

    public static String dbName = "ftProgCalc.db";
    public static int dbVersion = 1;

    public static int showLogs = 1;
    public static int showToasts = 1;
    public static String logTag = "ftpha - shdcalcdm - ";


    public static int memHeight = 76;
    public static int memColor = 0;
    public static int memTxtColor = 0;
    public static int memTxtSize = 14;
    public static int memMargin = 2;
    public static int memPadding = 0;


    public static String usrFlag = "";


    public static int selColor;
    public static String colorFor = "";


    public static MainActivity mA;
    public static EditBtnActivity usrBtnActivity;

    public static String equation;
    public static String display = "";
    public static String subDisplay;

    public static String elX;
    public static String elY;
    public static String elZ;
    public static String elZ4;
    public static String elZ5;
    public static String elZ6;
    public static String elZ7;
    public static String elZ8;

    public static String clcMode;

    public static String result;

    public static String thisNum;
    public static String history;


    public static String answ;

    public static LinearLayout tlll;

    public static Context ctx;

    public static Calc clc;
    public static CalcH clcH;

    public static cBtn wB; //cBtn that will be edited in the edit window.


    public static int makeBtnId(long lid, long id){

        int a = (100*((int)lid + 3) + ((int)id + 1));

        return a;

    }

    public static void appendDisplay(MainActivity a,String strToAppend){
        display += strToAppend;
        history += strToAppend;
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
        //android.os.SystemClock.sleep(1000);
        mA.setD(result);

    }
    public static void showResult2(String r){
        //android.os.SystemClock.sleep(1000);
        mA.setD(r);

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
        switch (showLogs) {
            case 0:

                break;
            case 1:
                Log.i(logTag, msg);
                break;
            case 2:
                Toast.makeText(ctx,msg,Toast.LENGTH_SHORT).show();
                break;
        }



    }

    public static void T(String msg) {
        if (showToasts == 1) {
            Toast.makeText(ftG.ctx, msg, Toast.LENGTH_LONG).show();
        }
    }

}
