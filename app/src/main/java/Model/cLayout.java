/*
 * Copyright (c) 2015. Fernando Toro. All rights reserved
 */

package Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.LinearLayout;

import com.ftpha.programmablecalculator.ftG;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fernando on 2015-06-09.
 * Originally created as part of: Programmable Calculator
 * You will love this code and be awed by it's magnificence
 */
public class cLayout{

    private LinearLayout lLL;

    private Context ctx;

    private static SQLiteOpenHelper dbH;
    private static SQLiteDatabase db;

    public long Id;
    public String cName;
    public String pName;
    public String lName;
    public long lRelativeH;


    public cLayout(){}

    @Override
    public String toString() {
        return this.cName;
    }


    //////////////////////////////////////////////////////////////////////////////////////////////////////////



    private static final String[] allCols = {
            cLayoutH.Id,
            cLayoutH.cName,
            cLayoutH.pName,
            cLayoutH.lName,
            cLayoutH.lRelativeH
    };

    public cLayout(Context context){

        dbH = new cLayoutH(context);
    }

    public static void Open(){


        db = dbH.getWritableDatabase();
        ftG.L(" The db has opened from cLayout");

    }


    public static void Close(){

        dbH.close();
        ftG.L(" The db has closed from cLayout");

    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////

    public void create(){

        Open();

        ContentValues values = new ContentValues();

        values.put(cLayoutH.cName, this.cName);
        values.put(cLayoutH.pName, this.pName);
        values.put(cLayoutH.lName, this.lName);
        values.put(cLayoutH.lRelativeH, this.lRelativeH);


        long insertId = db.insert(cLayoutH.TABLE_N, null,values );
        this.Id = insertId;

        Close();
    }

    public boolean update(long ID){

        Open();
        ContentValues vals = new ContentValues();

        vals.put(cLayoutH.cName, this.cName);
        vals.put(cLayoutH.pName, this.pName);
        vals.put(cLayoutH.lName, this.lName);
        vals.put(cLayoutH.lRelativeH, this.lRelativeH);


        boolean rslt;

        rslt = db.update(
                cLayoutH.TABLE_N,
                vals,
                cLayoutH.Id + " = " + ID,
                null
        ) == 1;
        Close();

        return rslt;
    }

    public static List<cLayout> listAll(){
        List<cLayout> loc_cLayout = new ArrayList<cLayout>();

        Cursor cursor = db.query(cLayoutH.TABLE_N, allCols, null, null, null, null, null);

        ftG.L("Found " + cursor.getCount() + " rows in " + cLayoutH.TABLE_N);

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                cLayout xx = new cLayout();
                xx.Id = cursor.getLong(cursor.getColumnIndex(cLayoutH.Id));
                xx.cName = cursor.getString(cursor.getColumnIndex(cLayoutH.cName));
                xx.pName = cursor.getString(cursor.getColumnIndex(cLayoutH.pName));
                xx.lName = cursor.getString(cursor.getColumnIndex(cLayoutH.lName));
                xx.lRelativeH = cursor.getLong(cursor.getColumnIndex(cLayoutH.lRelativeH));

                loc_cLayout.add(xx);
            }
        }

        return loc_cLayout;
    }




    public static cLayout getById(long ID){

        cLayout xx = new cLayout();

        Open();


        Cursor cursor = db.query(
                cLayoutH.TABLE_N,
                allCols,
                cLayoutH.Id + " = " + ID,
                null,
                null,
                null,
                null);


        if (cursor.getCount() > 0) {
            cursor.moveToNext();

            xx.Id = cursor.getLong(cursor.getColumnIndex(cLayoutH.Id));
            xx.cName = cursor.getString(cursor.getColumnIndex(cLayoutH.cName));
            xx.pName = cursor.getString(cursor.getColumnIndex(cLayoutH.pName));
            xx.lName = cursor.getString(cursor.getColumnIndex(cLayoutH.lName));
            xx.lRelativeH = cursor.getLong(cursor.getColumnIndex(cLayoutH.lRelativeH));

        }

        Close();
        return xx;
    }




}
