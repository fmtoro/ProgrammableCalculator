/*
 * Copyright (c) 2015. Fernando Toro. All rights reserved
 */

package Model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.ftpha.programmablecalculator.ftG;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Fernando on 2015-06-17.
 * Originally created as part of: Programmable Calculator
 * You will love this code and be awed by it's magnificence
 */

public class DataBaseHelper extends SQLiteOpenHelper {


    private static String DB_PATH;
    private static String DB_NAME = "ftPgCalc";
    private static String myPath;

    private SQLiteDatabase db;

    private final Context myContext;


    /**
     * Constructor
     * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
     * @param context
     */
    public DataBaseHelper(Context context) {

        super(context, DB_NAME, null, 1);
        this.myContext = context;
//\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/
        DB_PATH  = context.getDatabasePath(DataBaseHelper.DB_NAME).getParent();
        myPath = context.getDatabasePath(DataBaseHelper.DB_NAME).getPath();


    }

    /**
     * Creates a empty database on the system and rewrites it with your own database.
     * */
    public void createDataBase(boolean checkExsists) throws IOException {

        boolean dbExist;

        if (checkExsists) {
            dbExist = (checkDataBase()) && (cLayout.TableExists(cLayoutH.TABLE_N ,false));
        } else {
            dbExist = false;
        }



        if (dbExist) {
            //do nothing - database already exist
        } else {


            //By calling this method and empty database will be created into the default system path
            //of your application so we are gonna be able to overwrite that database with our database.
            this.getReadableDatabase();

            try {


                copyDataBase();

            } catch (IOException e) {

                throw new Error("Error copying database");

            }
        }

        if (!checkDataBase()) {
            ftG.L("NO WORKY   :(");
        }

    }

    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase(){

        SQLiteDatabase checkDB = null;

        try{

            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

        }catch(SQLiteException e){


            //database does't exist yet.

        }

        if(checkDB != null){

            checkDB.close();

        }

        return checkDB != null ? true : false;
    }

    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     * */
    private void copyDataBase() throws IOException{


        //Open your local db as the input stream
        InputStream myInput = myContext.getAssets().open(DB_NAME);


        // Path to the just created empty db
        String outFileName = myPath;


        //Open the empty db as the output stream


        OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){

            myOutput.write(buffer, 0, length);
        }


        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();


    }


    public void openDataBase() throws Exception{


        //Open the database

        db = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);


    }




    @Override
    public synchronized void close() {

        if(db != null)
            db.close();


        super.close();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // Add your public helper methods to access and get content from the database.
    // You could return cursors by doing "return db.query(....)" so it'd be easy
    // to you to create adapters for your views.

}