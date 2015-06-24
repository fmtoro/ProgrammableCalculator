/*
 * Copyright (c) 2015. Fernando Toro. All rights reserved
 */

package Model;



import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Fernando on 2015-06-09.
 * Originally created by FT's Db Helper data generator
 * copyright 2015, all rights reserved
 */
public class cMemoryH extends SQLiteOpenHelper {

    static  final   String      DB_Name     =   "ftPgCalc";
    static  final   int         DB_V        =   2;

    static  final   String      TABLE_N     =   "cmemory";

    static  final   String      Id         =   "Id";
    static  final   String      mText       =   "mText";

    // Creations
    static  final   String      CREATE_TABLE =
            "CREATE TABLE " + TABLE_N + " (" +
                    Id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    mText +  " TEXT " +
                    ")";


    public cMemoryH(Context context) {
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
