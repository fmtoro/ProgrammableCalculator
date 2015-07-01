/*
 * Copyright (c) 2015. Fernando Toro. All rights reserved
 */

package com.ftpha.programmablecalculator;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.larswerkman.holocolorpicker.ColorPicker;
import com.larswerkman.holocolorpicker.OpacityBar;
import com.larswerkman.holocolorpicker.SVBar;
import com.larswerkman.holocolorpicker.SaturationBar;
import com.larswerkman.holocolorpicker.ValueBar;

import java.util.ArrayList;
import java.util.List;


public class ColorPkrActivity extends Activity implements ColorPicker.OnColorChangedListener{
    private ColorPicker picker;
    private ValueBar valueBar;
//    private TextView text;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_pkr);

        picker = (ColorPicker) findViewById(R.id.cPicker);
//        svBar = (SVBar) findViewById(R.id.svbar);
        SaturationBar saturationBar = (SaturationBar) findViewById(R.id.saturationbar);
        valueBar = (ValueBar) findViewById(R.id.valuebar);
//        opacityBar = (OpacityBar) findViewById(R.id.opacitybar);
        Button button = (Button) findViewById(R.id.button1);
//        text = (TextView) findViewById(R.id.textView1);

        picker.setOldCenterColor(ftG.selColor);
        picker.setNewCenterColor(ftG.selColor);
//        picker.addSVBar(svBar);
        picker.addSaturationBar(saturationBar);
        picker.addValueBar(valueBar);
//        picker.addOpacityBar(opacityBar);
        picker.setOnColorChangedListener(this);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ftG.selColor = picker.getColor();
                picker.setOldCenterColor(picker.getColor());
                finish();
            }
        });
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_color_pkr, menu);
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

    @Override
    public void onColorChanged(int i) {

        if (ftG.colorFor == "btn") {
            ftG.usrBtnActivity.ftLlButtonColor.setBackgroundColor(i);
            ftG.usrBtnActivity.ftLlTextColorBG.setBackgroundColor(i);
        } else if (ftG.colorFor.equals("btnText")) {
            ftG.usrBtnActivity.ftLlTextColorTxt.setTextColor(i);
        }
    }

    public void OnColorClick(View view) {


        picker.setOldCenterColor(((ColorDrawable) view.getBackground()).getColor());
        picker.setNewCenterColor(((ColorDrawable) view.getBackground()).getColor());

    }
}
