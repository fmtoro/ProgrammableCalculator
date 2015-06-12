/*
 * Copyright (c) 2015. Fernando Toro. All rights reserved
 */

package Model;

import android.content.Context;
import android.widget.Button;


import android.content.ContentValues;
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
public class cBtn {

    private Context ctx;


    private static SQLiteOpenHelper dbH;
    private static SQLiteDatabase db;

    public long Id;
    public String cName;
    public String pName;
    public String lName;
    public String bName;
    public int ubColor;
    public int ubTextColor;
    public String ubText;
    public String ubCode;
    public String ubCodeDescription;
    public String ubAuthor;
    public boolean ubActive;
    public boolean ubVisible;
    public boolean ubLocked;
    public String ubBackgroundImage;
    public boolean ubTextVisible;
    public int ubRelativeW;


    public cBtn(){}

    public Button doButton(Button b) {
        if (!this.ubLocked) {
            b.setText(this.ubText);
            //TODO: set other properties etc
        }
        return b;
    }


    @Override
    public String toString() {
        return this.bName;
    }


    //////////////////////////////////////////////////////////////////////////////////////////////////////////



    private static final String[] allCols = {
            cBtnH.Id,
            cBtnH.cName,
            cBtnH.pName,
            cBtnH.lName,
            cBtnH.bName,
            cBtnH.ubColor,
            cBtnH.ubTextColor,
            cBtnH.ubText,
            cBtnH.ubCode,
            cBtnH.ubCodeDescription,
            cBtnH.ubAuthor,
            cBtnH.ubActive,
            cBtnH.ubVisible,
            cBtnH.ubLocked,
            cBtnH.ubBackgroundImage,
            cBtnH.ubTextVisible,
            cBtnH.ubRelativeW
    };

    public cBtn(Context context){

        dbH = new cBtnH(context);
    }

    public static void Open(){


        db = dbH.getWritableDatabase();
        ftG.L(" The db has opened from cBtn");

    }


    public static void Close(){

        dbH.close();
        ftG.L(" The db has closed from cBtn");

    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////

    public void create(){

        Open();

        ContentValues values = new ContentValues();

        values.put(cBtnH.cName, this.cName);
        values.put(cBtnH.pName, this.pName);
        values.put(cBtnH.lName, this.lName);
        values.put(cBtnH.bName, this.bName);
        values.put(cBtnH.ubColor, this.ubColor);
        values.put(cBtnH.ubTextColor, this.ubTextColor);
        values.put(cBtnH.ubText, this.ubText);
        values.put(cBtnH.ubCode, this.ubCode);
        values.put(cBtnH.ubCodeDescription, this.ubCodeDescription);
        values.put(cBtnH.ubAuthor, this.ubAuthor);
        values.put(cBtnH.ubActive, this.ubActive);
        values.put(cBtnH.ubVisible, this.ubVisible);
        values.put(cBtnH.ubLocked, this.ubLocked);
        values.put(cBtnH.ubBackgroundImage, this.ubBackgroundImage);
        values.put(cBtnH.ubTextVisible, this.ubTextVisible);
        values.put(cBtnH.ubRelativeW, this.ubRelativeW);


        long insertId = db.insert(cBtnH.TABLE_N, null,values );
        this.Id = insertId;

        Close();
    }

    public boolean update(long ID){

        Open();
        ContentValues vals = new ContentValues();

        vals.put(cBtnH.cName, this.cName);
        vals.put(cBtnH.pName, this.pName);
        vals.put(cBtnH.lName, this.lName);
        vals.put(cBtnH.bName, this.bName);
        vals.put(cBtnH.ubColor, this.ubColor);
        vals.put(cBtnH.ubTextColor, this.ubTextColor);
        vals.put(cBtnH.ubText, this.ubText);
        vals.put(cBtnH.ubCode, this.ubCode);
        vals.put(cBtnH.ubCodeDescription, this.ubCodeDescription);
        vals.put(cBtnH.ubAuthor, this.ubAuthor);
        vals.put(cBtnH.ubActive, this.ubActive);
        vals.put(cBtnH.ubVisible, this.ubVisible);
        vals.put(cBtnH.ubLocked, this.ubLocked);
        vals.put(cBtnH.ubBackgroundImage, this.ubBackgroundImage);
        vals.put(cBtnH.ubTextVisible, this.ubTextVisible);
        vals.put(cBtnH.ubRelativeW, this.ubRelativeW);


        boolean rslt;

        rslt = db.update(
                cBtnH.TABLE_N,
                vals,
                cBtnH.Id + " = " + ID,
                null
        ) == 1;
        Close();

        return rslt;
    }

    public static List<cBtn> listAll(){
        List<cBtn> loc_cBtn = new ArrayList<cBtn>();

        Cursor cursor = db.query(cBtnH.TABLE_N, allCols, null, null, null, null, null);

        ftG.L("Found " + cursor.getCount() + " rows in " + cBtnH.TABLE_N);

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                cBtn xx = new cBtn();
                xx.Id = cursor.getLong(cursor.getColumnIndex(cBtnH.Id));
                xx.cName = cursor.getString(cursor.getColumnIndex(cBtnH.cName));
                xx.pName = cursor.getString(cursor.getColumnIndex(cBtnH.pName));
                xx.lName = cursor.getString(cursor.getColumnIndex(cBtnH.lName));
                xx.bName = cursor.getString(cursor.getColumnIndex(cBtnH.bName));
                xx.ubColor = cursor.getInt(cursor.getColumnIndex(cBtnH.ubColor));
                xx.ubTextColor = cursor.getInt(cursor.getColumnIndex(cBtnH.ubTextColor));
                xx.ubText = cursor.getString(cursor.getColumnIndex(cBtnH.ubText));
                xx.ubCode = cursor.getString(cursor.getColumnIndex(cBtnH.ubCode));
                xx.ubCodeDescription = cursor.getString(cursor.getColumnIndex(cBtnH.ubCodeDescription));
                xx.ubAuthor = cursor.getString(cursor.getColumnIndex(cBtnH.ubAuthor));
                xx.ubActive = Boolean.getBoolean(cursor.getString(cursor.getColumnIndex(cBtnH.ubActive)));
                xx.ubVisible = Boolean.getBoolean(cursor.getString(cursor.getColumnIndex(cBtnH.ubVisible)));
                xx.ubLocked = Boolean.getBoolean(cursor.getString(cursor.getColumnIndex(cBtnH.ubLocked)));
                xx.ubBackgroundImage = cursor.getString(cursor.getColumnIndex(cBtnH.ubBackgroundImage));
                xx.ubTextVisible = Boolean.getBoolean(cursor.getString(cursor.getColumnIndex(cBtnH.ubTextVisible)));
                xx.ubRelativeW = cursor.getInt(cursor.getColumnIndex(cBtnH.ubRelativeW));

                loc_cBtn.add(xx);
            }
        }

        return loc_cBtn;
    }




    public static cBtn getById(long ID){

        cBtn xx = new cBtn();

        Open();


        Cursor cursor = db.query(
                cBtnH.TABLE_N,
                allCols,
                cBtnH.Id + " = " + ID,
                null,
                null,
                null,
                null);


        if (cursor.getCount() > 0) {
            cursor.moveToNext();

            xx.Id = cursor.getLong(cursor.getColumnIndex(cBtnH.Id));
            xx.cName = cursor.getString(cursor.getColumnIndex(cBtnH.cName));
            xx.pName = cursor.getString(cursor.getColumnIndex(cBtnH.pName));
            xx.lName = cursor.getString(cursor.getColumnIndex(cBtnH.lName));
            xx.bName = cursor.getString(cursor.getColumnIndex(cBtnH.bName));
            xx.ubColor = cursor.getInt(cursor.getColumnIndex(cBtnH.ubColor));
            xx.ubTextColor = cursor.getInt(cursor.getColumnIndex(cBtnH.ubTextColor));
            xx.ubText = cursor.getString(cursor.getColumnIndex(cBtnH.ubText));
            xx.ubCode = cursor.getString(cursor.getColumnIndex(cBtnH.ubCode));
            xx.ubCodeDescription = cursor.getString(cursor.getColumnIndex(cBtnH.ubCodeDescription));
            xx.ubAuthor = cursor.getString(cursor.getColumnIndex(cBtnH.ubAuthor));
            xx.ubActive = Boolean.getBoolean(cursor.getString(cursor.getColumnIndex(cBtnH.ubActive)));
            xx.ubVisible = Boolean.getBoolean(cursor.getString(cursor.getColumnIndex(cBtnH.ubVisible)));
            xx.ubLocked = Boolean.getBoolean(cursor.getString(cursor.getColumnIndex(cBtnH.ubLocked)));
            xx.ubBackgroundImage = cursor.getString(cursor.getColumnIndex(cBtnH.ubBackgroundImage));
            xx.ubTextVisible = Boolean.getBoolean(cursor.getString(cursor.getColumnIndex(cBtnH.ubTextVisible)));
            xx.ubRelativeW = cursor.getInt(cursor.getColumnIndex(cBtnH.ubRelativeW));

        }

        Close();
        return xx;
    }




}

