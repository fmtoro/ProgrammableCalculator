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
    public class Calc {

        public List<cLayout> ltS;

        public List<cMemory> memS;



        private Context ctx;

        private static SQLiteOpenHelper dbH;
        private static SQLiteDatabase db;

        //    Muy importante
        public long Id;
        public String cName;

        public String cBackgroundImage;

        // Display
        public int cDisplayColor;
        public int cDisplayTextColor;
        public int cDisplayTextSize;

        // Basic Buttons
        public int cBasicBtnsColor;
        public int cBasicBtnsTextColor;
        public int cBasicBtnsTextSize;
        public int cBasicBtnsMargines;
        public int cBasicBtnsPadding;




        @Override
        public String toString() {
            return this.cName;
        }


        //////////////////////////////////////////////////////////////////////////////////////////////////////////



        private static final String[] allCols = {
                CalcH.Id,
                CalcH.cName,
                CalcH.cBackgroundImage,
                CalcH.cDisplayColor,
                CalcH.cDisplayTextColor,
                CalcH.cDisplayTextSize,
                CalcH.cBasicBtnsColor,
                CalcH.cBasicBtnsTextColor,
                CalcH.cBasicBtnsTextSize,
                CalcH.cBasicBtnsMargines,
                CalcH.cBasicBtnsPadding
        };

        public Calc(Context context, boolean padded ){

            dbH = new CalcH(context);
            this.ltS = cLayout.listAll();
            this.memS = cMemory.listAll();

            for (cLayout l : ltS) {
                if (padded) {
                    l.btS = cBtn.listForLayoutPadded(l.Id);
                } else {
                    l.btS = cBtn.listForLayout(l.Id);
                }
            }



        }

        public static void Open(){


            db = dbH.getWritableDatabase();
            ftG.L(" The db has opened from Calc");

        }


        public static void Close(){

            dbH.close();
            ftG.L(" The db has closed from Calc");

        }


        ///////////////////////////////////////////////////////////////////////////////////////////////////

        public void create(){

            Open();

            ContentValues values = new ContentValues();

            values.put(CalcH.cName, this.cName);
            values.put(CalcH.cBackgroundImage, this.cBackgroundImage);
            values.put(CalcH.cDisplayColor, this.cDisplayColor);
            values.put(CalcH.cDisplayTextColor, this.cDisplayTextColor);
            values.put(CalcH.cDisplayTextSize, this.cDisplayTextSize);
            values.put(CalcH.cBasicBtnsColor, this.cBasicBtnsColor);
            values.put(CalcH.cBasicBtnsTextColor, this.cBasicBtnsTextColor);
            values.put(CalcH.cBasicBtnsTextSize, this.cBasicBtnsTextSize);
            values.put(CalcH.cBasicBtnsMargines, this.cBasicBtnsMargines);
            values.put(CalcH.cBasicBtnsPadding, this.cBasicBtnsPadding);


            long insertId = db.insert(CalcH.TABLE_N, null,values );
            this.Id = insertId;

            Close();
        }

        public boolean update(long ID){

            Open();
            ContentValues vals = new ContentValues();

            vals.put(CalcH.cName, this.cName);
            vals.put(CalcH.cBackgroundImage, this.cBackgroundImage);
            vals.put(CalcH.cDisplayColor, this.cDisplayColor);
            vals.put(CalcH.cDisplayTextColor, this.cDisplayTextColor);
            vals.put(CalcH.cDisplayTextSize, this.cDisplayTextSize);
            vals.put(CalcH.cBasicBtnsColor, this.cBasicBtnsColor);
            vals.put(CalcH.cBasicBtnsTextColor, this.cBasicBtnsTextColor);
            vals.put(CalcH.cBasicBtnsTextSize, this.cBasicBtnsTextSize);
            vals.put(CalcH.cBasicBtnsMargines, this.cBasicBtnsMargines);
            vals.put(CalcH.cBasicBtnsPadding, this.cBasicBtnsPadding);


            boolean rslt;

            rslt = db.update(
                    CalcH.TABLE_N,
                    vals,
                    CalcH.Id + " = " + ID,
                    null
            ) == 1;
            Close();

            return rslt;
        }

        public static List<Calc> listAll(){
            List<Calc> loc_Calc = new ArrayList<Calc>();

            Open();

            Cursor cursor = db.query(CalcH.TABLE_N, allCols, null, null, null, null, null);

            ftG.L("Found " + cursor.getCount() + " rows in " + CalcH.TABLE_N);

            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    Calc xx = new Calc(ftG.ctx, false);
                    xx.Id = cursor.getLong(cursor.getColumnIndex(CalcH.Id));
                    xx.cName = cursor.getString(cursor.getColumnIndex(CalcH.cName));
                    xx.cBackgroundImage = cursor.getString(cursor.getColumnIndex(CalcH.cBackgroundImage));
                    xx.cDisplayColor = cursor.getInt(cursor.getColumnIndex(CalcH.cDisplayColor));
                    xx.cDisplayTextColor = cursor.getInt(cursor.getColumnIndex(CalcH.cDisplayTextColor));
                    xx.cDisplayTextSize = cursor.getInt(cursor.getColumnIndex(CalcH.cDisplayTextSize));
                    xx.cBasicBtnsColor = cursor.getInt(cursor.getColumnIndex(CalcH.cBasicBtnsColor));
                    xx.cBasicBtnsTextColor = cursor.getInt(cursor.getColumnIndex(CalcH.cBasicBtnsTextColor));
                    xx.cBasicBtnsTextSize = cursor.getInt(cursor.getColumnIndex(CalcH.cBasicBtnsTextSize));
                    xx.cBasicBtnsMargines = cursor.getInt(cursor.getColumnIndex(CalcH.cBasicBtnsMargines));
                    xx.cBasicBtnsPadding = cursor.getInt(cursor.getColumnIndex(CalcH.cBasicBtnsPadding));

                    loc_Calc.add(xx);
                }
            }
            Close();
            return loc_Calc;
        }




        public static Calc getById(long ID){

            Calc xx = new Calc(ftG.ctx, false);

            Open();


            Cursor cursor = db.query(
                    CalcH.TABLE_N,
                    allCols,
                    CalcH.Id + " = " + ID,
                    null,
                    null,
                    null,
                    null);


            if (cursor.getCount() > 0) {
                cursor.moveToNext();

                xx.Id = cursor.getLong(cursor.getColumnIndex(CalcH.Id));
                xx.cName = cursor.getString(cursor.getColumnIndex(CalcH.cName));
                xx.cBackgroundImage = cursor.getString(cursor.getColumnIndex(CalcH.cBackgroundImage));
                xx.cDisplayColor = cursor.getInt(cursor.getColumnIndex(CalcH.cDisplayColor));
                xx.cDisplayTextColor = cursor.getInt(cursor.getColumnIndex(CalcH.cDisplayTextColor));
                xx.cDisplayTextSize = cursor.getInt(cursor.getColumnIndex(CalcH.cDisplayTextSize));
                xx.cBasicBtnsColor = cursor.getInt(cursor.getColumnIndex(CalcH.cBasicBtnsColor));
                xx.cBasicBtnsTextColor = cursor.getInt(cursor.getColumnIndex(CalcH.cBasicBtnsTextColor));
                xx.cBasicBtnsTextSize = cursor.getInt(cursor.getColumnIndex(CalcH.cBasicBtnsTextSize));
                xx.cBasicBtnsMargines = cursor.getInt(cursor.getColumnIndex(CalcH.cBasicBtnsMargines));
                xx.cBasicBtnsPadding = cursor.getInt(cursor.getColumnIndex(CalcH.cBasicBtnsPadding));

            }

            Close();
            return xx;
        }




    }