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

    public LinearLayout lLL;
    public List<cBtn> btS;


    private static SQLiteOpenHelper dbH;
    private static SQLiteDatabase db;

    public long Id;
    public String cName;
    public String pName;
    public String lName;
    public float lRelativeH;



    @Override
    public String toString() {
        return this.cName;
    }


    //////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void createActual(){

        this.lLL = new LinearLayout(ftG.ctx);
        this.lLL.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams lP = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        lP.height = 0;
        lP.weight=this.lRelativeH;
        this.lLL.setLayoutParams(lP);
        ftG.tlll.addView(this.lLL);
    }

    public void autoShowAll(){


        LinearLayout.LayoutParams lP = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        lP.height = 0;
        lP.weight= 1f;
        this.lLL.setLayoutParams(lP);

    }

    public void autoSetProps(){



    }

    private static final String[] allCols = {
            cLayoutH.Id,
            cLayoutH.cName,
            cLayoutH.pName,
            cLayoutH.lName,
            cLayoutH.lRelativeH
    };




    public cLayout(Context context){

        dbH = new cLayoutH(context);
        this.btS = cBtn.listForLayout(0);

    }

    public static  void initDb(){

        dbH = new cLayoutH(ftG.ctx);

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

        Open();

        Cursor cursor = db.query(cLayoutH.TABLE_N, allCols, null, null, null, null, null);

        ftG.L("Found " + cursor.getCount() + " rows in " + cLayoutH.TABLE_N);

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                cLayout xx = new cLayout(ftG.ctx);
                xx.Id = cursor.getLong(cursor.getColumnIndex(cLayoutH.Id));
                xx.cName = cursor.getString(cursor.getColumnIndex(cLayoutH.cName));
                xx.pName = cursor.getString(cursor.getColumnIndex(cLayoutH.pName));
                xx.lName = cursor.getString(cursor.getColumnIndex(cLayoutH.lName));
                xx.lRelativeH = cursor.getFloat(cursor.getColumnIndex(cLayoutH.lRelativeH));

                loc_cLayout.add(xx);
            }
        }
Close();
        return loc_cLayout;
    }




    public static cLayout getById(long ID){

        cLayout xx = new cLayout(ftG.ctx);

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
            xx.lRelativeH = cursor.getFloat(cursor.getColumnIndex(cLayoutH.lRelativeH));

        }

        Close();
        return xx;
    }


    public static boolean TableExists(String tableName, boolean openDb) {

        if(openDb) {
            if(db == null || !db.isOpen()) {
                db = dbH.getWritableDatabase();
            }

            if(db.isReadOnly()) {
                db.close();
                db = dbH.getWritableDatabase();
            }
        }

        Cursor cursor = db.rawQuery(
                "select DISTINCT tbl_name from sqlite_master where tbl_name = '"
                + tableName + "'", null);


        if(cursor!=null) {
            if(cursor.getCount()>0) {
                cursor.close();
                return true;
            } else {
                cursor.close();
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean delete(long ID){
        boolean rslt;
        Open();
        rslt = db.delete(cLayoutH.TABLE_N, cBtnH.Id + " = " + ID, null) > 0;
        Close();

        return rslt;
    }


}
