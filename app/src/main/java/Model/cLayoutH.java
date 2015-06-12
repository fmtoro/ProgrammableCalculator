/*
 * Copyright (c) 2015. Fernando Toro. All rights reserved
 */

package Model;



import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Fernando on 2015-06-09.
 * Originally created as part of: Programmable Calculator
 * You will love this code and be awed by it's magnificence
 */
public class cLayoutH extends SQLiteOpenHelper {

    static  final   String      DB_Name     =   "ftProgCalc.db";
    static  final   int         DB_V        =   3;

    static  final   String      TABLE_N     =   "clayout";

    static  final   String      Id         =   "Id";
    static  final   String      cName       =   "cName";
    static  final   String      pName       =   "pName";
    static  final   String      lName       =   "lName";
    static  final   String      lRelativeH       =   "lRelativeH";

    // Creations
    static  final   String      CREATE_TABLE =
            "CREATE TABLE " + TABLE_N + " (" +
                    Id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    cName +  " TEXT , " +
                    pName +  " TEXT , " +
                    lName +  " TEXT , " +
                    lRelativeH +  " INTEGER " +
                    ")";


    public cLayoutH(Context context) {
        super(context, DB_Name, null, DB_V);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE);
        //Log internal (LOGTAG, TABLE_N + "  Created");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists " + TABLE_N);


        //Log internal(LOGTAG, TABLE_N + "  Droped");
        onCreate(db);
    }

}
