/*
 * Copyright (c) 2015. Fernando Toro. All rights reserved
 */

package Model;




import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ftpha.programmablecalculator.ftG;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fernando on 2015-06-09.
 * Originally created as part of: Programmable Calculator
 * You will love this code and be awed by it's magnificence
 */
public class cPage {
    private Context ctx;

    private static SQLiteOpenHelper dbH;
    private static SQLiteDatabase db;

    public long Id;
    public String cName;
    public String pName;


    public cPage(){}

    @Override
    public String toString() {
        return this.cName;
    }


    //////////////////////////////////////////////////////////////////////////////////////////////////////////



    private static final String[] allCols = {
            cPageH.Id,
            cPageH.cName,
            cPageH.pName
    };

    public cPage(Context context){

        dbH = new cPageH(context);
    }

    public static void Open(){


        db = dbH.getWritableDatabase();
        ftG.L(" The db has opened from cPage");

    }


    public static void Close(){

        dbH.close();
        ftG.L(" The db has closed from cPage");

    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////

    //Aqui: One save method checks if the record exists, and decides weather to create or update...

    public void create(){

        Open();

        ContentValues values = new ContentValues();

        values.put(cPageH.cName, this.cName);
        values.put(cPageH.pName, this.pName);


        this.Id = db.insert(cPageH.TABLE_N, null,values );

        Close();
    }

    public boolean update(long ID){

        Open();
        ContentValues vals = new ContentValues();

        vals.put(cPageH.cName, this.cName);


        boolean rslt;

        rslt = db.update(
                cPageH.TABLE_N,
                vals,
                cPageH.Id + " = " + ID,
                null
        ) == 1;
        Close();

        return rslt;
    }

    public static List<cPage> listAll(){
        List<cPage> loc_cPage = new ArrayList<cPage>();

        Open();

        Cursor cursor = db.query(cPageH.TABLE_N, allCols, null, null, null, null, null);

        ftG.L("Found " + cursor.getCount() + " rows in " + cPageH.TABLE_N);

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                cPage xx = new cPage();
                xx.Id = cursor.getLong(cursor.getColumnIndex(cPageH.Id));
                xx.cName = cursor.getString(cursor.getColumnIndex(cPageH.cName));
                xx.pName = cursor.getString(cursor.getColumnIndex(cPageH.pName));

                loc_cPage.add(xx);
            }
        }

        Close();

        return loc_cPage;
    }




    public static cPage getById(long ID){/////

        cPage xx = new cPage();/////

        Open();


        Cursor cursor = db.query(
                cPageH.TABLE_N,
                allCols,
                cPageH.Id + " = " + ID,/////
                null,
                null,
                null,
                null);


        if (cursor.getCount() > 0) {
            cursor.moveToNext();

            xx.Id = cursor.getLong(cursor.getColumnIndex(cPageH.Id));
            xx.cName = cursor.getString(cursor.getColumnIndex(cPageH.cName));

        }

        Close();
        return xx;
    }




}
