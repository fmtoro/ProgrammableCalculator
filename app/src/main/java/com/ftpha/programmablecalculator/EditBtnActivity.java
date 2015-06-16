/*
 * Copyright (c) 2015. Fernando Toro. All rights reserved
 */

package com.ftpha.programmablecalculator;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import Model.cBtn;
import Model.cLayout;


public class EditBtnActivity extends Activity {
    CheckBox ftChBxActive;
    CheckBox ftChBxShowText;
    CheckBox ftChBxLocked;

    EditText ftTxtBttnText;

    LinearLayout ftLlButtonColor;
    LinearLayout ftLlTextColorBG;
    TextView ftLlTextColorTxt;

    EditText ftTxtRowH;
    EditText ftTxtBtnW;

    EditText ftTxtButtonCode;
    EditText ftTxtButtonDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_btn);

        initViewVariables();
        loadMeUp();
    }

    private void loadMeUp(){

        Button wB;        // work Button
        cBtn wBc;        // work Button Class

        wBc = ftG.wB;
        wB = ftG.wB.b;

        ftChBxActive.setChecked(wBc.ubActive == 1);
        ftChBxShowText.setChecked(wBc.ubTextVisible == 1);
        ftChBxLocked.setChecked(wBc.ubLocked == 1);

        ftTxtBttnText.setText(wBc.ubText);

        ftLlButtonColor.setBackgroundColor(wBc.ubColor);
        ftLlTextColorBG.setBackgroundColor(wBc.ubColor);
        ftLlTextColorTxt.setTextColor(wBc.ubTextColor);

        ftTxtRowH.setText(String.valueOf(ftG.clc.ltS.get((int) wBc.lId - 1).lRelativeH));
        ftTxtBtnW.setText(String.valueOf(wBc.ubRelativeW));

        ftTxtButtonCode.setText(wBc.ubCode);
        ftTxtButtonDescription.setText(wBc.ubCodeDescription);

    }

    private void initViewVariables(){

        ftChBxActive = (CheckBox) findViewById(R.id.chBxActive);
        ftChBxShowText = (CheckBox) findViewById(R.id.chBxShowText);
        ftChBxLocked = (CheckBox) findViewById(R.id.chBxLocked);

        ftTxtBttnText = (EditText) findViewById(R.id.txtBttnText);

        ftLlButtonColor = (LinearLayout) findViewById(R.id.llButtonColor);
        ftLlTextColorBG = (LinearLayout) findViewById(R.id.llTextColorBG);
        ftLlTextColorTxt = (TextView) findViewById(R.id.llTextColorTxt);

        ftTxtRowH = (EditText) findViewById(R.id.txtRowH);
        ftTxtBtnW = (EditText) findViewById(R.id.txtBtnW);

        ftTxtButtonCode = (EditText) findViewById(R.id.txtButtonCode);
        ftTxtButtonDescription = (EditText) findViewById(R.id.txtButtonDescription);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_btn, menu);
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

    public void onSaveBttn(View view) {

        ftG.wB.ubActive         = ftChBxActive.isChecked()      ? 1 : 0;
        ftG.wB.ubTextVisible    = ftChBxShowText.isChecked()    ? 1 : 0;
        ftG.wB.ubLocked         = ftChBxLocked.isChecked()      ? 1 : 0;

        ftG.wB.ubText = ftTxtBttnText.getText().toString();

//        ftLlButtonColor.setBackgroundColor(wBc.ubColor);
//        ftLlTextColorBG.setBackgroundColor(wBc.ubColor);
//        ftLlTextColorTxt.setTextColor(wBc.ubTextColor);

        ftG.clc.ltS.get((int) ftG.wB.lId - 1).lRelativeH = Float.valueOf(ftTxtRowH.getText().toString());
        ftG.wB.ubRelativeW = Float.valueOf(ftTxtBtnW.getText().toString());

        ftG.wB.ubCode = ftTxtButtonCode.getText().toString();
        ftG.wB.ubCodeDescription = ftTxtButtonDescription.getText().toString();

        long ax = ftG.wB.Id;
        ftG.wB.update(ax);

        ftG.wB.autoUpdate();

        ftG.editM = false;
        finish();

    }

    public void onExitWOSave(View view) {
        ftG.editM = false;
        finish();
    }
}
