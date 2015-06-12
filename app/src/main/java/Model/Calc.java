/*
 * Copyright (c) 2015. Fernando Toro. All rights reserved
 */

package Model;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.ftpha.programmablecalculator.ftG;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Fernando on 2015-06-05.
 * Originally created as part of: PartialTab
 * You will love this code and be awed by it's magnificence
 */
public class Calc{
    private Context ctx;

    private static SQLiteOpenHelper dbH;
    private static SQLiteDatabase db;

    //    Muy importante
    public long cId;
    public String cName;

    public String cBackgroundImage;

    // Display
    public int cDisplayColor;
    public int cDisplayTextColor;
    public int cDisplayTextSize;

    // Basic Buttons
    public int cBasicBtnsColor;
    public int cBasicBtnsTextColor;
    public int cBasicBtnsTextSize;
    public int cBasicBtnsMargines;
    public int cBasicBtnsPadding;

    // Memories
    public int cMemW;  // for the scrollable
    public int cMemH;  // for each button
    public int cMemColor;
    public int cMemMargins;
    public int cMemPaddings;
    public int cMemTextSize;
    public int cMemTextColor;


    public Calc(){}

    public Calc(Context c, String cname){

        this.ctx = c;
        this.cName = cname;

        this.cBackgroundImage = "na";
        this.cDisplayColor = -1;
        this.cDisplayTextColor = -1;
        this.cDisplayTextSize = -1;

        this.cBasicBtnsColor = -1;
        this.cBasicBtnsTextColor = -1;
        this.cBasicBtnsTextSize = -1;
        this.cBasicBtnsMargines = -1;
        this.cBasicBtnsPadding = -1;

        this.cMemW = -1;
        this.cMemH = -1;
        this.cMemColor = -1;
        this.cMemMargins = -1;
        this.cMemPaddings = -1;
        this.cMemTextSize = -1;
        this.cMemTextColor = -1;

    }






    @Override
    public String toString() {
        return this.cName;
    }


    //////////////////////////////////////////////////////////////////////////////////////////////////////////



    private static final String[] allUTCs = {
            CalcH.cId,
            CalcH.cName,

    };

    public Calc(Context context){

        dbH = new CalcH(context);
    }

    public static void Open(){


        db = dbH.getWritableDatabase();
        ftG.L(" The db has opened");

    }


    public static void Close(){

        dbH.close();
        ftG.L(" The db has closed");

    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////

    public void createUser(){

        Open();

        ContentValues values = new ContentValues();

        values.put(CalcH.cName, this.cName);


        long insertCId = db.insert(CalcH.TABLE_N, null,values );
        this.cId = insertCId;

        Close();
    }

    public boolean updateUser(long ID){

        Open();
        ContentValues vals = new ContentValues();

        vals.put(CalcH.cName, this.cName);
//        vals.put(CalcH.U_C_PHONE, this.Algo);


        boolean rslt;

        rslt = db.update(
                CalcH.TABLE_N,
                vals,
                CalcH.cId + " = " + ID,
                null
        ) == 1;
        Close();

        return rslt;
    }

    public static List<Calc> findAllUsers(){
        List<Calc> users = new ArrayList<Calc>();

        Cursor cursor = db.query(CalcH.TABLE_N, allUTCs, null, null, null, null, null);

        ftG.L("Found " + cursor.getCount() + " rows in " + CalcH.TABLE_N);

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                Calc user = new Calc();
                user.cId =cursor.getLong(cursor.getColumnIndex(CalcH.cId));
                user.cName =cursor.getString(cursor.getColumnIndex(CalcH.cName));

                users.add(user);
            }
        }

        return users;
    }




    public static Calc getUser(long uID){

        Calc user = new Calc();

        Open();


        Cursor cursor = db.query(
                CalcH.TABLE_N,
                allUTCs,
                CalcH.cId + " = " + uID,
                null,
                null,
                null,
                null);


        if (cursor.getCount() > 0) {
            cursor.moveToNext();

            user.cId = cursor.getLong(cursor.getColumnIndex(CalcH.cId));
            user.cName = cursor.getString(cursor.getColumnIndex(CalcH.cName));

        }

        Close();
        return user;
    }




}
