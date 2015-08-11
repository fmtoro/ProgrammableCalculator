package com.ftpha.programmablecalculator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.xmlpull.v1.XmlSerializer;

import java.security.PublicKey;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;

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
    public static int dbVersion = 4;

    public static int showLogs = 1;
    public static int showToasts = 1;
    public static String logTag = "ftpha - shdcalcdm - ";


    public static int screenW;
    public static int screenH;

    public static int memHeight = 76;
    public static int memColor = 0;
    public static int memTxtColor = 0;
    public static int memTxtSize = 14;
    public static int memMargin = 2;
    public static int memPadding = 0;


    public static String dialogBtnPositive = "";
    public static String dialogBtnNegative = "";
    public static String dialogBtnNeutral = "";
    public static String dialogTitle = "";
    public static String dialogHeading = "";
    public static String dialogContent = "";


    ////////////////////////////////////////////////////////////////////////////////
//    Preferences

    public static int prfDisplayTextSize;
    public static int prfDisplayTextColor;
    public static int prfDisplayBackgroundColor;
    public static int prfDisplayMargins;
    public static int prfDisplayPaddingRight;
    public static int prfDisplayPaddingBottom;
    public static int prfDisplayPaddingTop;
    public static int prfDisplayPaddingLeft;



    ////////////////////////////////////////////////////////////////////////////////
    public static String inBoxTitle = "";

    public static String inBoxLabel1 = "";
    public static String inBoxTxt1 = "";                    //Out
    public static String inBoxTxt1InputType = "";
    public static String inBoxSelNum1 = "";                 //Out
    public static String inBoxSelNum1Post = "";
    public static String inBoxSeekBar1Max = "";
    public static String inBoxSeekBar1Initial = "";

    public static String inBoxLabel2 = "";
    public static String inBoxTxt2 = "";                    //Out
    public static String inBoxTxt2InputType = "";
    public static String inBoxSelNum2 = "";                 //Out
    public static String inBoxSelNum2Post = "";
    public static String inBoxSeekBar2Max = "";
    public static String inBoxSeekBar2Initial = "";

    public static String inBoxLabel3 = "";
    public static String inBoxTxt3 = "";                    //Out
    public static String inBoxTxt3InputType = "";
    public static String inBoxSelNum3 = "";                 //Out
    public static String inBoxSelNum3Post = "";
    public static String inBoxSeekBar3Max = "";
    public static String inBoxSeekBar3Initial = "";




    ////////////////////////////////////////////////////////////////////////////////
    public static String usrFlag = "";
    public static boolean usrHasEqualFlag = false;
    public static String usrTheEqualCode = "";



    public static int selColor;
    public static String colorFor = "";


    public static MainActivity mA;
    public static EditBtnActivity usrBtnActivity;

    public static String equation;
    public static String display = "";
    public static String subDisplay;

    public static String elX;

    public static String clcMode;

    public static String result;

    public static String thisNum;
    public static String history;


    public static String answ;

    public static LinearLayout tlll;
    public  static LinearLayout memLl;

    public static Context ctx;

    public static Calc clc;
    public static CalcH clcH;

    public static cBtn wB; //cBtn that will be edited in the edit window.

    public static void msgBox(){

        Intent i = new Intent(ftG.ctx, MsgBoxActivity.class);
        ctx.startActivity(i);

    }

    public static int makeBtnId(long lid, long id){

        int a = (100*((int)lid + 3) + ((int)id + 1));

        return a;

    }

    public static void appendDisplay(MainActivity a,String strToAppend, boolean addToY) {
        if (addToY) {
            ftG.YzS.edit(strToAppend);
        }

        display += strToAppend;
        history += strToAppend;
        //9+**************86thisNum += strToAppend;
        a.setD(display);
    }

    public static boolean boolFromString(String strVal, boolean defVal){

        if (strVal.equals("true")||strVal.equals("True")||strVal.equals("TRUE")) {
            return true;
        } else {
            return false;
        }
    }

    public static int intFromString(String strVal, int defVal){

        if (stringIsNumeric(strVal)) {
            return Integer.parseInt(strVal);
        } else {
            return defVal;
        }
    }

    public static int intFromHex(String strVal, String defVal){

        int intColor;

        try {
            intColor = Color.parseColor(strVal);
        } catch (IllegalArgumentException iae) {
            intColor = Color.parseColor(defVal);
        }

        return intColor;

    }
    public static boolean stringIsNumeric( String str )
    {
        DecimalFormatSymbols currentLocaleSymbols = DecimalFormatSymbols.getInstance();
        char localeMinusSign = currentLocaleSymbols.getMinusSign();

        if ( !Character.isDigit( str.charAt( 0 ) ) && str.charAt( 0 ) != localeMinusSign ) return false;

        boolean isDecimalSeparatorFound = false;
        char localeDecimalSeparator = currentLocaleSymbols.getDecimalSeparator();

        for ( char c : str.substring( 1 ).toCharArray() )
        {
            if ( !Character.isDigit( c ) )
            {
                if ( c == localeDecimalSeparator && !isDecimalSeparatorFound )
                {
                    isDecimalSeparatorFound = true;
                    continue;
                }
                return false;
            }
        }
        return true;
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


        return isNumeric(ftG.YzS.get());

    }

    public static boolean isNumeric(String str)
    {
        String a = "0" + str + ".2";
        return a.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }

    public static void setX(String toSet){
        new preCalc(ftG.ctx).setX(toSet);
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

    public static class YzS{

        public static List<String> zS= new ArrayList<>();


        public static void add(String yz, boolean forceCreate) {

            if (yz.trim().equals("")) {
                if (!forceCreate) {
                    return;
                }
            }
            zS.add(0, yz);
            if (zS.size() > 50) {
                zS.remove(50);
            }
        }

//        public static void add(String yz) {
//
//            if (yz.trim().equals("") && !zS.isEmpty()) {
//                return;
//            }
//            zS.add(0, yz);
//            if (zS.size() > 50) {
//                zS.remove(50);
//            }
//        }

        public static void edit(String modYz) {
            if (zS.isEmpty()) {
                add("", true);
            }
            String x = zS.get(0);
            x += modYz;
            zS.remove(0);
            zS.add(0,x);
        }

        public static String get(int lvl) {
            int nLvl = lvl;

            if (zS.size() - 1 < lvl) {
                return "";
            } else {
                return zS.get(nLvl);
            }

        }

        public static String get() {
            if (zS.isEmpty()) {
                add("", true);
            }
            return zS.get(0);
        }

        public static void pop() {
            if (!zS.isEmpty()) {
                zS.remove(0);
            }
        }

    }
}
