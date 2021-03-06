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
 * Originally created by FT's Db Helper data generator
 * copyright 2015, all rights reserved
 */
public class cBtnH extends SQLiteOpenHelper {

    static  final   String      DB_Name     = "ftPgCalc";
    static  final   int         DB_V        =   ftG.dbVersion;

    static  final   String      TABLE_N     =   "cbtn";

    static  final   String      Id         =   "Id";
    static  final   String      cName       =   "cName";
    static  final   String      pName       =   "pName";
    static  final   String      lId       =   "lId";
    static  final   String      bName       =   "bName";
    static  final   String      ubColor       =   "ubColor";
    static  final   String      ubTextColor       =   "ubTextColor";
    static  final   String      ubText       =   "ubText";
    static  final   String      ubCode       =   "ubCode";
    static  final   String      ubCodeDescription       =   "ubCodeDescription";
    static  final   String      ubAuthor       =   "ubAuthor";
    static  final   String      ubActive       =   "ubActive";
    static  final   String      ubVisible       =   "ubVisible";
    static  final   String      ubLocked       =   "ubLocked";
    static  final   String      ubBackgroundImage       =   "ubBackgroundImage";
    static  final   String      ubTextVisible       =   "ubTextVisible";
    static  final   String      ubRelativeW       =   "ubRelativeW";
    static  final   String      ubBelongToLayout       =   "ubBelongToLayout";
    static  final   String      ubPosInLayout       =   "ubPosInLayout";

    // Creations
    static  final   String      CREATE_TABLE =
            "CREATE TABLE " + TABLE_N + " (" +
                    Id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    cName +  " TEXT , " +
                    pName +  " TEXT , " +
                    lId +  " INTEGER , " +
                    bName +  " TEXT , " +
                    ubColor +  " TEXT , " +
                    ubTextColor +  " TEXT , " +
                    ubText +  " TEXT , " +
                    ubCode +  " TEXT , " +
                    ubCodeDescription +  " TEXT , " +
                    ubAuthor +  " TEXT,  " +
                    ubActive +  " INTEGER , " +
                    ubVisible +  " INTEGER , " +
                    ubLocked +  " INTEGER , " +
                    ubBackgroundImage +  " TEXT , " +
                    ubTextVisible +  " INTEGER , " +
                    ubRelativeW +  " REAL , " +
                    ubBelongToLayout +  " INTEGER , " +
                    ubPosInLayout +  " INTEGER " +
            ")";


    public cBtnH(Context context) {
        super(context, DB_Name, null, DB_V);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE);
        ftG.L(TABLE_N + "  Created  **from DB**");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists " + TABLE_N);


        ftG.L(TABLE_N + "  Dropped  **from DB**");

        onCreate(db);
    }

}