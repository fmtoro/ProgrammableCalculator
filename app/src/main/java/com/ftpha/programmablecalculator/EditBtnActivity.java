/*
 * Copyright (c) 2015. Fernando Toro. All rights reserved
 */

package com.ftpha.programmablecalculator;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.larswerkman.holocolorpicker.ColorPicker;
import com.larswerkman.holocolorpicker.OpacityBar;
import com.larswerkman.holocolorpicker.SVBar;

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
    Menu menU;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_edit_btn);

        initViewVariables();
        loadMeUp();
        ftG.usrBtnActivity = this;

        getActionBar().setDisplayHomeAsUpEnabled(true);

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

        if (wBc.ubColor == 0) {
            ftLlButtonColor.setBackgroundColor(Color.parseColor("#FFBBBBBB"));
            ftLlTextColorBG.setBackgroundColor(Color.parseColor("#FFBBBBBB"));
        }else{
            ftLlButtonColor.setBackgroundColor(wBc.ubColor);
            ftLlTextColorBG.setBackgroundColor(wBc.ubColor);
        }
        if (wBc.ubTextColor == 0) {
            ftLlTextColorTxt.setTextColor(Color.parseColor("#FF222222"));
        }else{
            ftLlTextColorTxt.setTextColor(wBc.ubTextColor);
        }


        ftTxtRowH.setText(String.valueOf(cLayout.getById(wBc.Id).lRelativeH));
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
        menU = menu;
        initMenu();
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent i = new Intent(EditBtnActivity.this, AppPreferences.class);
                startActivity(i);
                return true;

            case R.id.about:
                Intent j = new Intent(EditBtnActivity.this, AboutActivity.class);
                startActivity(j);
                return true;

            case R.id.save:
//                setSaveVisibility(false);
                doSave();
                return true;

            case R.id.delete:
                doDelete();
                return true;
        }



        return super.onOptionsItemSelected(item);
    }

    public void initMenu()
    {

        MenuItem mnuSave = menU.findItem(R.id.save);
        MenuItem mnuDelete = menU.findItem(R.id.delete);

        mnuSave.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        mnuDelete.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

    }

    public void onSaveBttn(View view) {

        doSave();

    }

    private void doSave() {
        updateAndSaveBtn();

        ftG.mA.changeEditMode();
        finish();
    }

    private void updateAndSaveBtn() {
        ftG.wB.ubActive         = ftChBxActive.isChecked()      ? 1 : 0;
        ftG.wB.ubTextVisible    = ftChBxShowText.isChecked()    ? 1 : 0;
        ftG.wB.ubLocked         = ftChBxLocked.isChecked()      ? 1 : 0;

        ftG.wB.ubText = ftTxtBttnText.getText().toString();

        ftG.wB.ubColor = ((ColorDrawable)ftLlButtonColor.getBackground()).getColor();
        ftG.wB.ubTextColor = ftLlTextColorTxt.getCurrentTextColor();

        cLayout.getById(ftG.wB.lId).lRelativeH = Float.valueOf(ftTxtRowH.getText().toString());
        ftG.wB.ubRelativeW = Float.valueOf(ftTxtBtnW.getText().toString());

        ftG.wB.ubCode = ftTxtButtonCode.getText().toString();
        ftG.wB.ubCodeDescription = ftTxtButtonDescription.getText().toString().trim();

        if (ftG.wB.ubActive == 0) {
            ftG.wB.ubRelativeW = 0;
        }

        long ax = ftG.wB.Id;
        ftG.wB.update(ax);

        ftG.wB.autoUpdate();
    }

    public void onExitWOSave(View view) {
        doExitWOSave();
    }

    private void doExitWOSave() {
        ftG.mA.changeEditMode();
        finish();
    }

    public void onActiveStatusSet(View view) {
        if (ftChBxActive.isChecked()) {
            ftTxtBtnW.setText("1.0");
        }else {
            ftTxtBtnW.setText("0");
        }
    }

    public void onShowTextStatusSet(View view) {
        //?
    }

    public void onLockedStatusSet(View view) {
        //?
    }

    public void onButtonColClicked(View view) {
        ftG.selColor = ((ColorDrawable)ftLlButtonColor.getBackground()).getColor();
        ftG.colorFor = "btn";
        Intent iT = new Intent(EditBtnActivity.this,ColorPkrActivity.class);
        startActivity(iT);
    }


    public void onTextColClicked(View view) {
        ftG.selColor = ftLlTextColorTxt.getCurrentTextColor();
        ftG.colorFor = "btnText";
        Intent iT = new Intent(EditBtnActivity.this,ColorPkrActivity.class);
        startActivity(iT);
    }

    public void onGoToBttnsCode(View view) {
        Intent i = new Intent(EditBtnActivity.this, CodeEditorActivity.class);
        updateAndSaveBtn();
        i.putExtra("codeTxt", ftTxtButtonCode.getText().toString());
        startActivityForResult(i, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if(resultCode == RESULT_OK){
                String result=data.getStringExtra("result");
                ftTxtButtonCode.setText(result);
            }
        }
        if (requestCode == 2) {
            if(resultCode == RESULT_OK){
                String result=data.getStringExtra("result");
                ftTxtButtonDescription.setText(result);
            }

        }
    }//onActivityResult
    public void onDelete(View view) {
        //
        doDelete();

    }

    private void doDelete() {
        ftG.wB.delete(ftG.wB.Id);
        ftG.mA.borraTuto();
        ftG.mA.ponLosAndamios(false);

        ftG.mA.changeEditMode();
        finish();
    }

    public void onGoToBttnsEqualCode(View view) {
        Intent i = new Intent(EditBtnActivity.this, CodeEditorActivity.class);
        updateAndSaveBtn();
        i.putExtra("codeTxt", ftTxtButtonDescription.getText().toString());
        startActivityForResult(i, 2);
    }
}
