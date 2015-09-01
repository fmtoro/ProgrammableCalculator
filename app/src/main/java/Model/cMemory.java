/*
 * Copyright (c) 2015. Fernando Toro. All rights reserved
 */

package Model;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.text.AndroidCharacter;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.ftpha.programmablecalculator.R;
import com.ftpha.programmablecalculator.ftG;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fernando on 2015-06-09.
 * Originally created by FT's Db Helper data generator
 * copyright 2015, all rights reserved
 */
public class cMemory {



    public Button b;

    private cMemory yo;

    private static SQLiteOpenHelper dbH;
    private static SQLiteDatabase db;

    public      long        Id;
    public      String      mGroup;
    public      String      mText;
    public      String      mName;
    public      String      mUtl;
    public      long        mTxtSize;
    public      long        mBColor;
    public      long        mTxtColor;
    public      float       mHeight;



    @Override
    public String toString() {
        return this.mText;
    }


    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void createActual() {
        long elId;//                               HAce falta?
        this.b = new Button(ftG.ctx);
        if (yo.mName.equals("")) {
            if (ftG.editM) {
                this.b.setCompoundDrawablesWithIntrinsicBounds(R.drawable.basura, 0, 0, 0);
            }
            this.b.setText(yo.mText);
        } else {
            if (ftG.editM) {
                this.b.setCompoundDrawablesWithIntrinsicBounds(R.drawable.basura, 0, 0, 0);
            }
            this.b.setText(yo.mName);

        }
//        elId = ftG.makeBtnId(2000, yo.Id );//                               HAce falta?
//        this.b.setId((int) elId);//                               HAce falta?

        this.b.setTag(this.Id);


        LinearLayout l = (LinearLayout) ftG.mA.findViewById(R.id.memLL);

        if (yo.mBColor == 0) {
            this.b.setBackgroundColor(Color.parseColor("#FFBBBBBB") );
        }else {
            this.b.setBackgroundColor((int)yo.mBColor);
        }
        if (yo.mTxtColor == 0) {
            this.b.setTextColor(Color.parseColor("#FF222222") );
        }else {
            this.b.setTextColor((int)yo.mTxtColor);
        }

        LinearLayout.LayoutParams lP = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);

        lP.height = (int)yo.mHeight;
        lP.gravity = Gravity.CENTER;

        lP.setMargins(ftG.memMargin, ftG.memMargin, ftG.memMargin, ftG.memMargin);
        this.b.setPadding(ftG.memPadding, ftG.memPadding, ftG.memPadding, ftG.memPadding);
        this.b.setTextSize(yo.mTxtSize);

        this.b.setLayoutParams(lP);


        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (!ftG.editM) {

                    //ftG.display = yo.mText;
                    ftG.YzS.add(yo.mText, false);
                    ftG.appendDisplay(ftG.mA, yo.mText, false);

                } else {
//                    //Aqui estamos en edit mode

                    deleteMem(v);

                }
            }
        });

            //b.setOnDragListener(new ftDragL()); Some day...


        b.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (ftG.editM) {
                    //deleteMem(v);

                } else {
                    //Aqui: Special add. Sugests thisNum, but it can be changed to anything. it also
                            // allows to name the mem, any string of max 10 characters
                }
                return true;

            }
        });


        l.addView(this.b, 0);
    }

    private void deleteMem(View v) {
        cMemory m = cMemory.getById((long) v.getTag() );

        m.delete(m.Id);
        ftG.mA.borraTuto();
        ftG.mA.ponLosAndamios(false);

    }


    private static final String[] allCols = {
            cMemoryH.Id,
            cMemoryH.mGroup,
            cMemoryH.mText,
            cMemoryH.mName,
            cMemoryH.mUtl,
            cMemoryH.mTxtSize,
            cMemoryH.mBColor,
            cMemoryH.mTxtColor,
            cMemoryH.mHeight

    };

    public static  void initDb(){

        dbH = new cMemoryH(ftG.ctx);

    }

    public cMemory(Context context){


        dbH = new cMemoryH(context);
        yo = this;
        yo.Id = 0;
        yo.mGroup = "Mem1";
        yo.mText = "0";
        yo.mName = "";
        yo.mUtl = "";
        yo.mTxtSize = 14;
        yo.mBColor = 0;
        yo.mTxtColor = 0;
        yo.mHeight = 100;
    }

    public static void Open(){


        db = dbH.getWritableDatabase();
        ftG.L(" The db has opened from cMemory");

    }


    public static void Close(){

        dbH.close();
        ftG.L(" The db has closed from cMemory");

    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////

    public void create(){

        Open();

        ContentValues values = new ContentValues();

        values.put(cMemoryH.mGroup, this.mGroup);
        values.put(cMemoryH.mText, this.mText);
        values.put(cMemoryH.mName, this.mText);
        values.put(cMemoryH.mUtl, this.mUtl);
        values.put(cMemoryH.mTxtSize, this.mTxtSize);
        values.put(cMemoryH.mBColor, this.mBColor);
        values.put(cMemoryH.mTxtColor, this.mTxtColor);
        values.put(cMemoryH.mHeight, this.mHeight);


        long insertId = db.insert(cMemoryH.TABLE_N, null,values );
        this.Id = insertId;

        Close();
    }

    public boolean update(long ID){

        Open();
        ContentValues values = new ContentValues();

        values.put(cMemoryH.mGroup, this.mGroup);
        values.put(cMemoryH.mText, this.mText);
        values.put(cMemoryH.mName, this.mText);
        values.put(cMemoryH.mUtl, this.mUtl);
        values.put(cMemoryH.mTxtSize, this.mTxtSize);
        values.put(cMemoryH.mBColor, this.mBColor);
        values.put(cMemoryH.mTxtColor, this.mTxtColor);
        values.put(cMemoryH.mHeight, this.mHeight);


        boolean rslt;

        rslt = db.update(
                cMemoryH.TABLE_N,
                values,
                cMemoryH.Id + " = " + ID,
                null
        ) == 1;
        Close();

        return rslt;
    }


    public boolean delete(long ID){
        boolean rslt;
        Open();
        rslt = db.delete(cMemoryH.TABLE_N, cMemoryH.Id + " = " + ID, null) > 0;
        Close();

        return rslt;
    }



    public static List<cMemory> listAll(){
        List<cMemory> loc_cMemory = new ArrayList<cMemory>();


        if(!TableExists(cMemoryH.TABLE_N,true)){
            dbH.onCreate(db);
        }


        Cursor cursor = db.query(
                cMemoryH.TABLE_N,
                allCols,
                null,
                null,
                null,
                null,
                "Id ASC"
        );

        ftG.L("Found " + cursor.getCount() + " rows in " + cMemoryH.TABLE_N);

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                cMemory xx = new cMemory(ftG.ctx);
                xx.Id = cursor.getLong(cursor.getColumnIndex(cMemoryH.Id));
                xx.mGroup = cursor.getString(cursor.getColumnIndex(cMemoryH.mGroup));
                xx.mText = cursor.getString(cursor.getColumnIndex(cMemoryH.mText));
                xx.mName = cursor.getString(cursor.getColumnIndex(cMemoryH.mName));
                xx.mUtl = cursor.getString(cursor.getColumnIndex(cMemoryH.mUtl));
                xx.mTxtSize = cursor.getLong(cursor.getColumnIndex(cMemoryH.mTxtSize));
                xx.mBColor = cursor.getLong(cursor.getColumnIndex(cMemoryH.mBColor));
                xx.mTxtColor = cursor.getLong(cursor.getColumnIndex(cMemoryH.mTxtColor));
                xx.mHeight = cursor.getFloat(cursor.getColumnIndex(cMemoryH.mHeight));

                loc_cMemory.add(xx);
            }
        }
Close();
        return loc_cMemory;
    }




    public static cMemory getById(long ID){

        cMemory xx = new cMemory(ftG.ctx);

        Open();


        Cursor cursor = db.query(
                cMemoryH.TABLE_N,
                allCols,
                cMemoryH.Id + " = " + ID,
                null,
                null,
                null,
                null);


        if (cursor.getCount() > 0) {
            cursor.moveToNext();

            xx.Id = cursor.getLong(cursor.getColumnIndex(cMemoryH.Id));
            xx.mGroup = cursor.getString(cursor.getColumnIndex(cMemoryH.mGroup));
            xx.mText = cursor.getString(cursor.getColumnIndex(cMemoryH.mText));
            xx.mName = cursor.getString(cursor.getColumnIndex(cMemoryH.mName));
            xx.mUtl = cursor.getString(cursor.getColumnIndex(cMemoryH.mUtl));
            xx.mTxtSize = cursor.getLong(cursor.getColumnIndex(cMemoryH.mTxtSize));
            xx.mBColor = cursor.getLong(cursor.getColumnIndex(cMemoryH.mBColor));
            xx.mTxtColor = cursor.getLong(cursor.getColumnIndex(cMemoryH.mTxtColor));
            xx.mHeight = cursor.getFloat(cursor.getColumnIndex(cMemoryH.mHeight));

        }

        Close();
        return xx;
    }

    public static boolean TableExists(String tableName, boolean openDb) {
        if (openDb) {
            if (db == null || !db.isOpen()) {
                db = dbH.getWritableDatabase();
            }

            if (!db.isReadOnly()) {
                db.close();
                db = dbH.getWritableDatabase();
            }
        }

        Cursor cursor = db.rawQuery("select DISTINCT tbl_name from sqlite_master where tbl_name = '" + tableName + "'", null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.close();
                return true;
            }
            cursor.close();
        }
        return false;
    }
}
