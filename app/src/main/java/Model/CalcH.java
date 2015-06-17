/*
 * Copyright (c) 2015. Fernando Toro. All rights reserved
 */

package Model;



import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ftpha.programmablecalculator.ftG;

/**
 * Created by Fernando on 2015-06-09.
 * Originally created as part of: Programmable Calculator
 * You will love this code and be awed by it's magnificence
 */
public class CalcH extends SQLiteOpenHelper {

    static  final   String      DB_Name     = "ftPgCalc";
    static  final   int         DB_V        =   1;

    static  final   String      TABLE_N     =   "calc";

    static  final   String      Id         =   "Id";
    static  final   String      cName       =   "cName";
    static  final   String      cBackgroundImage       =   "cBackgroundImage";
    static  final   String      cDisplayColor       =   "cDisplayColor";
    static  final   String      cDisplayTextColor       =   "cDisplayTextColor";
    static  final   String      cDisplayTextSize       =   "cDisplayTextSize";
    static  final   String      cBasicBtnsColor       =   "cBasicBtnsColor";
    static  final   String      cBasicBtnsTextColor       =   "cBasicBtnsTextColor";
    static  final   String      cBasicBtnsTextSize       =   "cBasicBtnsTextSize";
    static  final   String      cBasicBtnsMargines       =   "cBasicBtnsMargines";
    static  final   String      cBasicBtnsPadding       =   "cBasicBtnsPadding";

    // Creations
    static  final   String      CREATE_TABLE =
            "CREATE TABLE " + TABLE_N + " (" +
                    Id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    cName +  " TEXT , " +
                    cBackgroundImage +  " TEXT , " +
                    cDisplayColor +  " INTEGER , " +
                    cDisplayTextColor +  " INTEGER , " +
                    cDisplayTextSize +  " INTEGER , " +
                    cBasicBtnsColor +  " INTEGER , " +
                    cBasicBtnsTextColor +  " INTEGER , " +
                    cBasicBtnsTextSize +  " INTEGER , " +
                    cBasicBtnsMargines +  " INTEGER , " +
                    cBasicBtnsPadding +  " INTEGER,  " +
                    ")";


    public CalcH(Context context) {
        super(context, DB_Name, null, DB_V);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE);
        ftG.L(TABLE_N + "  Created **from DB**");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists " + TABLE_N);


        ftG.L(TABLE_N + "  Dropped **from DB**");
        onCreate(db);
    }

}
