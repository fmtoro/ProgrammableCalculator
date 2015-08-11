/*
 * Copyright (c) 2015. Fernando Toro. All rights reserved
 */

package Model;

import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.internal.widget.ActivityChooserModel;
import android.view.DragEvent;
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

    public void createActual(LinearLayout l) {
        long elId;
        btnTagData tagData = new btnTagData();
        final boolean iAmASeparator=(yo.bName == "Separator");

        this.b = new Button(ftG.ctx);
        this.b.setText(yo.ubText);
        if (iAmASeparator) {
            elId = ftG.makeBtnId((yo.lId + 1000), Long.valueOf(yo.cName) );
        }else {
            elId = ftG.makeBtnId(yo.lId, yo.Id);
        }
        this.b.setId((int) elId);
        if (yo.ubColor == 0) {
            this.b.setBackgroundColor(Color.parseColor("#FFBBBBBB") );
        }else {
            this.b.setBackgroundColor(yo.ubColor);
        }
        if (yo.ubTextColor == 0) {
            this.b.setTextColor(Color.parseColor("#FF222222") );
        }else {
            this.b.setTextColor(yo.ubTextColor);
        }
        this.b.setTextSize(16);
        LinearLayout.LayoutParams lP = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        lP.width = 0;
        if (!iAmASeparator) {
            lP.setMargins(3,3,3,3);
        }
        if (iAmASeparator) {
            lP.weight = 0.04f;
            yo.b.setAlpha(0.01f);
            tagData.theLayout = yo.lId;
            tagData.Pos = Integer.valueOf( yo.cName);
            yo.b.setTag(tagData);
        }else {
            yo.b.setTag((int)this.Id);
            if (ftG.editM) {
                if (this.ubRelativeW <= 0.1) {
                    lP.weight = 1f;
                } else {
                    lP.weight = this.ubRelativeW;
                }
            }else{
                if (this.ubActive == 1) {
                    if (this.ubRelativeW <= 0.33) {
                        lP.weight = 1f;
                        this.ubRelativeW = 1f;
                    } else {
                        lP.weight = this.ubRelativeW;
                    }
                } else {
                    lP.weight = 0f;
                }
            }
        }
        this.b.setLayoutParams(lP);

        if (!iAmASeparator) {
            b.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {

                    if (!ftG.editM) {

                        ftG.elX = ftG.mA.getDisplay();




                        ftG.mA.doCalculate(yo.ubCode);

                        if (yo.ubCodeDescription.equals("")) {
                            ftG.usrHasEqualFlag = false;
                            ftG.usrTheEqualCode = "";
                        }else {
                            ftG.usrHasEqualFlag = true;
                            ftG.usrTheEqualCode = yo.ubCodeDescription;
                        }

                    } else {
                        //Aqui estamos en edit mode

//                        int ix = ftG.clc.ltS.get((int) yo.lId - 1).btS.indexOf(yo);
//                        if (ix < 0) {
//                            return;
//                        }
                        ftG.wB = yo;

                        Intent in = new Intent(ftG.currActivity, EditBtnActivity.class);
                        ftG.currActivity.startActivity(in);
                    }
                }
            });
        }else{
            b.setOnDragListener(new ftDragL());
        }

        b.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (ftG.editM) {
                    if(!iAmASeparator) {
                        ClipData data = ClipData.newPlainText("", "");
                        View.DragShadowBuilder shwBuldr = new View.DragShadowBuilder(v);

                        v.startDrag(data, shwBuldr, v, 0);
                        return true;
                    }else{
                        return false;
                    }

                }else {
                    //ftG.T(yo.ubCodeDescription);//ToDo: show differently
                    return false;
                }

            }
        });

        l.addView(this.b);
    }


    private class ftDragL implements View.OnDragListener {

        @Override
        public boolean onDrag(View v, DragEvent e) {
            Button receivingObj;
            LinearLayout.LayoutParams lP;
            View view;
            Button droppedObj;

            switch (e.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    //no action necessary
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    view = (View) e.getLocalState();
                    droppedObj = (Button) view;

                    receivingObj = (Button) v;
                    receivingObj.setAlpha(0.25f);
                    receivingObj.setText(droppedObj.getText());

                    lP= new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.MATCH_PARENT);
                    lP.width = 0;
                    lP.weight=1f;
                    receivingObj.setLayoutParams(lP);

                    break;
                case DragEvent.ACTION_DRAG_EXITED:

                    receivingObj = (Button) v;
                    receivingObj.setAlpha(0.01f);

                    lP = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.MATCH_PARENT);
                    lP.width = 0;
                    lP.weight=0.02f;
                    receivingObj.setLayoutParams(lP);


                    break;
                case DragEvent.ACTION_DROP:
                    //handle the dragged view being dropped over a drop view
                    view = (View) e.getLocalState();
                    droppedObj = (Button) view;
                    receivingObj = (Button) v;
                    btnTagData btd;
                    long drOjId = Long.parseLong(droppedObj.getTag().toString());
                    btd = (btnTagData) receivingObj.getTag();
//                    String a = "Dropped " + droppedObj.getText() + " - " + String.valueOf(drOjId ) + "\n" +
//                            " on "+ String.valueOf( btd.theLayout) + " - " + String.valueOf( btd.Pos);
//                    ftG.T(a);
                    completeMove(drOjId, btd.theLayout, btd.Pos);
                    ftG.mA.borraTuto();
                    ftG.mA.ponLosAndamios(true);
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    //no action necessary
                    break;
                default:
                    break;
            }
            return true;
        }
    }

    public class btnTagData{
        public long theLayout;
        public int Pos;

    }
    private void completeMove(long btnToMove, long dropLayout, int dropPos){

        cBtn btnInQuestion = new cBtn(ftG.ctx);

        for (cLayout l : ftG.clc.ltS) {
            for (cBtn btn : l.btS) {
                if (btn.Id == btnToMove) {
                    btnInQuestion = btn;
                    break;
                }
            }
        }

        for (cLayout l : ftG.clc.ltS) {
            if (l.Id == dropLayout) {
                for (cBtn btn : l.btS) {
                    if (btn.ubPosInLayout >= dropPos) {
                        btn.moveRight();
                    }
                }
            }

        }
        btnInQuestion.moveTo(dropLayout, dropPos);

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
        yo.cName = "";
        yo.pName = "";
        yo.lId = 0;
        yo.bName = "x";
        yo.ubColor = 0;
        yo.ubTextColor = 0;
        yo.ubText = "";
        yo.yo.ubCode = "";
        yo.ubCodeDescription = "";
        yo.ubAuthor = "";
        yo.ubActive = 1;
        yo.ubVisible = 1;
        yo.ubLocked = 0;
        yo.ubBackgroundImage = "";
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

    public void create() {

        if (yo.bName == "Separator") {
            return;
        }

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


        long insertId = db.insert(cBtnH.TABLE_N, null, values);
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

    public void moveTo(long theLayout, int thePos){
        yo.lId = theLayout;
        yo.ubPosInLayout = thePos;
        yo.update(yo.Id);
    }

    public void moveRight(){
        yo.ubPosInLayout++;
        yo.update(yo.Id);
    }
    public boolean delete(long ID){
    boolean rslt;
        Open();
        rslt = db.delete(cBtnH.TABLE_N, cBtnH.Id + " = " + ID, null) > 0;
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




    public static List<cBtn> listForLayoutPadded(long forLayout){
        List<cBtn> loc_cBtn = new ArrayList<cBtn>();



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

        int i = 0;

        cBtn zz = new cBtn(ftG.ctx);
        zz.lId = forLayout;
        zz.bName = "Separator";
        zz.cName = String.valueOf(i++);


        loc_cBtn.add(zz);

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
                xx.ubRelativeW = cursor.getFloat(cursor.getColumnIndex(cBtnH.ubRelativeW)); //Force to 1???
                xx.ubBelongToLayout = cursor.getLong(cursor.getColumnIndex(cBtnH.ubBelongToLayout));
                xx.ubPosInLayout = cursor.getLong(cursor.getColumnIndex(cBtnH.ubPosInLayout));

                cBtn yy = new cBtn(ftG.ctx);
                yy.lId = xx.lId;
                yy.bName = "Separator";
                yy.cName = String.valueOf(i++);

                loc_cBtn.add(xx);
                loc_cBtn.add(yy);
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

