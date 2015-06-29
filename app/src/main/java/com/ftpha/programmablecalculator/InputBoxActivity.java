/*
 * Copyright (c) 2015. Fernando Toro. All rights reserved
 */

package com.ftpha.programmablecalculator;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;


public class InputBoxActivity extends Activity {

    TextView lblTitle;

    TextView lblLabel1;
    EditText txt1;
    TextView lblSelectedNumber1;
    SeekBar seekBar1;

    TextView lblLabel2;
    EditText txt2;
    TextView lblSelectedNumber2;
    SeekBar seekBar2;

    TextView lblLabel3;
    EditText txt3;
    TextView lblSelectedNumber3;
    SeekBar seekBar3;


    TextView lblHeading;
    TextView lblContent;

    Button btnPos;
    Button btnNeg;
    Button btnNeut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_box);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_input_box, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        
        return super.onOptionsItemSelected(item);
    }
}
