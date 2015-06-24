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

    public long Id;
    public String mText;




    @Override
    public String toString() {
        return this.mText;
    }


    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void createActual() {
        long elId;
        this.b = new Button(ftG.ctx);
        this.b.setText(yo.mText);
        elId = ftG.makeBtnId(2000, yo.Id );
        this.b.setId((int) elId);

        LinearLayout l = (LinearLayout) ftG.mA.findViewById(R.id.memLL);

        if (ftG.memColor == 0) {
            this.b.setBackgroundColor(Color.parseColor("#FFBBBBBB") );
        }else {
            this.b.setBackgroundColor(ftG.memColor);
        }
        if (ftG.memTxtColor == 0) {
            this.b.setTextColor(Color.parseColor("#FF222222") );
        }else {
            this.b.setTextColor(ftG.memTxtColor);
        }

        LinearLayout.LayoutParams lP = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        lP.height = ftG.memHeight;
        lP.gravity = Gravity.CENTER;

        lP.setMargins(ftG.memMargin,ftG.memMargin,ftG.memMargin,ftG.memMargin);
        this.b.setPadding(ftG.memPadding, ftG.memPadding, ftG.memPadding, ftG.memPadding);

        this.b.setLayoutParams(lP);


        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (!ftG.editM) {

                    ftG.thisNum = yo.mText;
                    ftG.appendDisplay(ftG.mA, yo.mText);

                } else {
//                    //Aqui estamos en edit mode
//
//                    int ix = ftG.clc.ltS.get((int) yo.lId - 1).btS.indexOf(yo);
//                    ftG.wB = ftG.clc.ltS.get((int) yo.lId - 1).btS.get(ix);
//
//                    Intent in = new Intent(ftG.currActivity, EditBtnActivity.class);
//                    ftG.currActivity.startActivity(in);
                }
            }
        });

            //b.setOnDragListener(new ftDragL()); Some day...


//        b.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                if (ftG.editM) {
//                    if (!iAmASeparator) {
//                        ClipData data = ClipData.newPlainText("", "");
//                        View.DragShadowBuilder shwBuldr = new View.DragShadowBuilder(v);
//
//                        v.startDrag(data, shwBuldr, v, 0);
//                        return true;
//                    } else {
//                        return false;
//                    }
//
//                } else {
//                    ftG.T(yo.ubCodeDescription);//ToDo: show differently
//                    return false;
//                }
//
//            }
//        });


        l.addView(this.b,0);
    }


    private static final String[] allCols = {
            cMemoryH.Id,
            cMemoryH.mText
    };

    public static  void initDb(){

        dbH = new cMemoryH(ftG.ctx);

    }

    public cMemory(Context context){


        dbH = new cMemoryH(context);
        yo = this;
        yo.Id = 0;
        yo.mText = "0";
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

        values.put(cMemoryH.mText, this.mText);


        long insertId = db.insert(cMemoryH.TABLE_N, null,values );
        this.Id = insertId;

        Close();
    }

    public boolean update(long ID){

        Open();
        ContentValues vals = new ContentValues();

        vals.put(cMemoryH.mText, this.mText);


        boolean rslt;

        rslt = db.update(
                cMemoryH.TABLE_N,
                vals,
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
                xx.mText = cursor.getString(cursor.getColumnIndex(cMemoryH.mText));

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
            xx.mText = cursor.getString(cursor.getColumnIndex(cMemoryH.mText));

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
