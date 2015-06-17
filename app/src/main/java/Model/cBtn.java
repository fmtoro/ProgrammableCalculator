/*
 * Copyright (c) 2015. Fernando Toro. All rights reserved
 */

package Model;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.internal.widget.ActivityChooserModel;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.ftpha.programmablecalculator.EditBtnActivity;
import com.ftpha.programmablecalculator.MainActivity;
import com.ftpha.programmablecalculator.ftG;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fernando on 2015-06-09.
 * Originally created as part of: Programmable Calculator
 * You will love this code and be awed by it's magnificence
 */
public class cBtn {

    public Button b;

    private cBtn yo;

    private static SQLiteOpenHelper dbH;
    private static SQLiteDatabase db;

    public long         Id                      ;
    public String       cName                   ;
    public String       pName                   ;
    public long         lId                     ;
    public String       bName                   ;
    public int          ubColor                 ;
    public int          ubTextColor             ;
    public String       ubText                  ;
    public String       ubCode                  ;
    public String       ubCodeDescription       ;
    public String       ubAuthor                ;
    public int          ubActive                ;
    public int          ubVisible               ;
    public int          ubLocked                ;
    public String       ubBackgroundImage       ;
    public int          ubTextVisible           ;
    public float        ubRelativeW             ;

    public long ubBelongToLayout    ;
    public long ubPosInLayout       ;


    public Button doButton(Button b) {
        if (this.ubLocked != 1) {
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

    public void autoCreate(LinearLayout l){

        this.b = new Button(ftG.ctx);
        this.b.setText(yo.ubText);
        long elId = ftG.makeBtnId(yo.lId, yo.Id);
        this.b.setId((int) elId);
        LinearLayout.LayoutParams lP = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        lP.width = 0;
        lP.weight=this.ubRelativeW;
        this.b.setLayoutParams(lP);

        b.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {

                if (!ftG.editM) {

                    ftG.elX = ftG.mA.getDisplay();

                    ftG.mA.doCalculate(yo.ubCode.toString());

                } else {
                    //Aqui estamos en edit mode

                    int ix = ftG.clc.ltS.get((int) yo.lId - 1).btS.indexOf(yo);
                    ftG.wB = ftG.clc.ltS.get((int) yo.lId - 1).btS.get(ix);

                    Intent in = new Intent(ftG.currActivity, EditBtnActivity.class);
                    ftG.currActivity.startActivity(in);
                }
            }
        });

        b.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                Toast.makeText(ftG.ctx,yo.ubCodeDescription,Toast.LENGTH_LONG).show();

                return false;
            }
        });

        l.addView(this.b);
    }

    public void autoShowAll(){

        this.b.setText(yo.ubText + "  " + yo.lId + " - " + yo.Id);


        LinearLayout.LayoutParams lP = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        lP.width = 0;
        lP.weight = 1f;
        this.b.setLayoutParams(lP);
        this.ubVisible = LinearLayout.VISIBLE;
    }

    public void autoUpdate(){

        this.b.setText(yo.ubText);


        LinearLayout.LayoutParams lP = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        lP.width = 0;
        lP.weight = yo.ubRelativeW;
        this.b.setLayoutParams(lP);
        this.ubVisible = (yo.ubActive == 1) ? LinearLayout.VISIBLE : LinearLayout.GONE;

        //Aqui faltan los colores...

    }

    private static final String[] allCols = {
            cBtnH.Id,
            cBtnH.cName,
            cBtnH.pName,
            cBtnH.lId,
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
            cBtnH.ubRelativeW,
            cBtnH.ubBelongToLayout,
            cBtnH.ubPosInLayout

    };

    public static  void initDb(){

        dbH = new cBtnH(ftG.ctx);

    }

    public cBtn(Context context){

        dbH = new cBtnH(context);
        yo = this;
        yo.Id = 0;
        yo.cName = "x";
        yo.pName = "x";
        yo.lId = 0;
        yo.bName = "x";
        yo.ubColor = 0;
        yo.ubTextColor = 0;
        yo.ubText = "x";
        yo.yo.ubCode = "x";
        yo.ubCodeDescription = "x";
        yo.ubAuthor = "x";
        yo.ubActive = 1;
        yo.ubVisible = 1;
        yo.ubLocked = 0;
        yo.ubBackgroundImage = "x";
        yo.ubTextVisible = 1;
        yo.ubRelativeW = 1f;
        yo.ubBelongToLayout = 0;
        yo.ubPosInLayout = 0;
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
        values.put(cBtnH.lId, this.lId);
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
        values.put(cBtnH.ubBelongToLayout, this.ubBelongToLayout);
        values.put(cBtnH.ubPosInLayout, this.ubPosInLayout);


        long insertId = db.insert(cBtnH.TABLE_N, null,values );
        this.Id = insertId;

        Close();
    }

    public boolean update(long ID){

        Open();
        ContentValues vals = new ContentValues();

        vals.put(cBtnH.cName, this.cName);
        vals.put(cBtnH.pName, this.pName);
        vals.put(cBtnH.lId, this.lId);
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
        vals.put(cBtnH.ubBelongToLayout, this.ubBelongToLayout);
        vals.put(cBtnH.ubPosInLayout, this.ubPosInLayout);


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
Open();
        Cursor cursor = db.query(cBtnH.TABLE_N, allCols, null, null, null, null, null);

        ftG.L("Found " + cursor.getCount() + " rows in " + cBtnH.TABLE_N);

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                cBtn xx = new cBtn(ftG.ctx);
                xx.Id = cursor.getLong(cursor.getColumnIndex(cBtnH.Id));
                xx.cName = cursor.getString(cursor.getColumnIndex(cBtnH.cName));
                xx.pName = cursor.getString(cursor.getColumnIndex(cBtnH.pName));
                xx.lId = cursor.getLong(cursor.getColumnIndex(cBtnH.lId));
                xx.bName = cursor.getString(cursor.getColumnIndex(cBtnH.bName));
                xx.ubColor = cursor.getInt(cursor.getColumnIndex(cBtnH.ubColor));
                xx.ubTextColor = cursor.getInt(cursor.getColumnIndex(cBtnH.ubTextColor));
                xx.ubText = cursor.getString(cursor.getColumnIndex(cBtnH.ubText));
                xx.ubCode = cursor.getString(cursor.getColumnIndex(cBtnH.ubCode));
                xx.ubCodeDescription = cursor.getString(cursor.getColumnIndex(cBtnH.ubCodeDescription));
                xx.ubAuthor = cursor.getString(cursor.getColumnIndex(cBtnH.ubAuthor));
                xx.ubActive = cursor.getInt(cursor.getColumnIndex(cBtnH.ubActive));
                xx.ubVisible = cursor.getInt(cursor.getColumnIndex(cBtnH.ubVisible));
                xx.ubLocked = cursor.getInt(cursor.getColumnIndex(cBtnH.ubLocked));
                xx.ubBackgroundImage = cursor.getString(cursor.getColumnIndex(cBtnH.ubBackgroundImage));
                xx.ubTextVisible = cursor.getInt(cursor.getColumnIndex(cBtnH.ubTextVisible));
                xx.ubRelativeW = cursor.getFloat(cursor.getColumnIndex(cBtnH.ubRelativeW));
                xx.ubBelongToLayout = cursor.getLong(cursor.getColumnIndex(cBtnH.ubBelongToLayout));
                xx.ubPosInLayout = cursor.getLong(cursor.getColumnIndex(cBtnH.ubPosInLayout));

                loc_cBtn.add(xx);
            }
        }
Close();
        return loc_cBtn;
    }

    public static List<cBtn> listForLayout(long forLayout){
        List<cBtn> loc_cBtn = new ArrayList<cBtn>();
//Open();
        if(!TableExists(cBtnH.TABLE_N,true)){
            dbH.onCreate(db);
        }
        Cursor cursor = db.query(
                cBtnH.TABLE_N,
                allCols,
                cBtnH.lId + " = " + forLayout,
                null,
                null,
                null,
                "ubPosInLayout asc"
        );

        ftG.L("Found " + cursor.getCount() + " rows in " + cBtnH.TABLE_N);

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                cBtn xx = new cBtn(ftG.ctx);
                xx.Id = cursor.getLong(cursor.getColumnIndex(cBtnH.Id));
                xx.cName = cursor.getString(cursor.getColumnIndex(cBtnH.cName));
                xx.pName = cursor.getString(cursor.getColumnIndex(cBtnH.pName));
                xx.lId = cursor.getLong(cursor.getColumnIndex(cBtnH.lId));
                xx.bName = cursor.getString(cursor.getColumnIndex(cBtnH.bName));
                xx.ubColor = cursor.getInt(cursor.getColumnIndex(cBtnH.ubColor));
                xx.ubTextColor = cursor.getInt(cursor.getColumnIndex(cBtnH.ubTextColor));
                xx.ubText = cursor.getString(cursor.getColumnIndex(cBtnH.ubText));
                xx.ubCode = cursor.getString(cursor.getColumnIndex(cBtnH.ubCode));
                xx.ubCodeDescription = cursor.getString(cursor.getColumnIndex(cBtnH.ubCodeDescription));
                xx.ubAuthor = cursor.getString(cursor.getColumnIndex(cBtnH.ubAuthor));
                xx.ubActive = cursor.getInt(cursor.getColumnIndex(cBtnH.ubActive));
                xx.ubVisible = cursor.getInt(cursor.getColumnIndex(cBtnH.ubVisible));
                xx.ubLocked = cursor.getInt(cursor.getColumnIndex(cBtnH.ubLocked));
                xx.ubBackgroundImage = cursor.getString(cursor.getColumnIndex(cBtnH.ubBackgroundImage));
                xx.ubTextVisible = cursor.getInt(cursor.getColumnIndex(cBtnH.ubTextVisible));
                xx.ubRelativeW = cursor.getFloat(cursor.getColumnIndex(cBtnH.ubRelativeW));
                xx.ubBelongToLayout = cursor.getLong(cursor.getColumnIndex(cBtnH.ubBelongToLayout));
                xx.ubPosInLayout = cursor.getLong(cursor.getColumnIndex(cBtnH.ubPosInLayout));

                loc_cBtn.add(xx);
            }
        }
Close();
        return loc_cBtn;
    }




    public static cBtn getById(long ID){

        cBtn xx = new cBtn(ftG.ctx);

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
            xx.lId = cursor.getLong(cursor.getColumnIndex(cBtnH.lId));
            xx.bName = cursor.getString(cursor.getColumnIndex(cBtnH.bName));
            xx.ubColor = cursor.getInt(cursor.getColumnIndex(cBtnH.ubColor));
            xx.ubTextColor = cursor.getInt(cursor.getColumnIndex(cBtnH.ubTextColor));
            xx.ubText = cursor.getString(cursor.getColumnIndex(cBtnH.ubText));
            xx.ubCode = cursor.getString(cursor.getColumnIndex(cBtnH.ubCode));
            xx.ubCodeDescription = cursor.getString(cursor.getColumnIndex(cBtnH.ubCodeDescription));
            xx.ubAuthor = cursor.getString(cursor.getColumnIndex(cBtnH.ubAuthor));
            xx.ubActive = cursor.getInt(cursor.getColumnIndex(cBtnH.ubActive));
            xx.ubVisible = cursor.getInt(cursor.getColumnIndex(cBtnH.ubVisible));
            xx.ubLocked = cursor.getInt(cursor.getColumnIndex(cBtnH.ubLocked));
            xx.ubBackgroundImage = cursor.getString(cursor.getColumnIndex(cBtnH.ubBackgroundImage));
            xx.ubTextVisible = cursor.getInt(cursor.getColumnIndex(cBtnH.ubTextVisible));
            xx.ubRelativeW = cursor.getFloat(cursor.getColumnIndex(cBtnH.ubRelativeW));
            xx.ubBelongToLayout = cursor.getLong(cursor.getColumnIndex(cBtnH.ubBelongToLayout));
            xx.ubPosInLayout = cursor.getLong(cursor.getColumnIndex(cBtnH.ubPosInLayout));

        }

        Close();
        return xx;
    }

    public static boolean TableExists(String tableName, boolean openDb) {
        if(openDb) {
            if(db == null || !db.isOpen()) {
                db = dbH.getWritableDatabase();
            }

            if(!db.isReadOnly()) {
                db.close();
                db = dbH.getWritableDatabase();
            }
        }

        Cursor cursor = db.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '" + tableName + "'", null);
        if(cursor!=null) {
            if(cursor.getCount()>0) {
                cursor.close();
                return true;
            }
            cursor.close();
        }
        return false;
    }




}

