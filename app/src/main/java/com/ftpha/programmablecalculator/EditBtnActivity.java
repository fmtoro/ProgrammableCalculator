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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_btn);

        initViewVariables();
        loadMeUp();
        ftG.usrBtnActivity = this;
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

        updateAndSaveBtn();

        ftG.editM = false;
        finish();

    }

    private void updateAndSaveBtn() {
        ftG.wB.ubActive         = ftChBxActive.isChecked()      ? 1 : 0;
        ftG.wB.ubTextVisible    = ftChBxShowText.isChecked()    ? 1 : 0;
        ftG.wB.ubLocked         = ftChBxLocked.isChecked()      ? 1 : 0;

        ftG.wB.ubText = ftTxtBttnText.getText().toString();

        ftG.wB.ubColor = ((ColorDrawable)ftLlButtonColor.getBackground()).getColor();
        ftG.wB.ubTextColor = ftLlTextColorTxt.getCurrentTextColor();

        ftG.clc.ltS.get((int) ftG.wB.lId - 1).lRelativeH = Float.valueOf(ftTxtRowH.getText().toString());
        ftG.wB.ubRelativeW = Float.valueOf(ftTxtBtnW.getText().toString());

        ftG.wB.ubCode = ftTxtButtonCode.getText().toString();
        ftG.wB.ubCodeDescription = ftTxtButtonDescription.getText().toString();

        if (ftG.wB.ubActive == 0) {
            ftG.wB.ubRelativeW = 0;
        }

        long ax = ftG.wB.Id;
        ftG.wB.update(ax);

        ftG.wB.autoUpdate();
    }

    public void onExitWOSave(View view) {
        ftG.editM = false;
        finish();
    }

    public void onActiveStatusSet(View view) {
        //?
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
    private ColorPicker picker;
    private SVBar svBar;
    private OpacityBar opacityBar;
    private Button button;
    private TextView text;
//    private void showColorPickerDialog()
//    {
//        AlertDialog.Builder colorDialogBuilder = new AlertDialog.Builder(
//                EditBtnActivity.this);
//        LayoutInflater inflater = LayoutInflater.from(this);
//        View dialogview = inflater.inflate(R.layout.activity_color_pkr, null);
//        picker = (ColorPicker) findViewById(R.id.cPicker);
//        svBar = (SVBar) findViewById(R.id.svbar);
//        opacityBar = (OpacityBar) findViewById(R.id.opacitybar);
//        picker.addSVBar(svBar);
//        picker.addOpacityBar(opacityBar);
//        picker.setOnColorChangedListener(new ColorPicker.OnColorChangedListener()
//        {
//            @Override
//            public void onColorChanged(int color) {
//
//            }
//        });
//        colorDialogBuilder.setTitle("Choose Text Color");
//        colorDialogBuilder.setView(dialogview);
//        colorDialogBuilder.setPositiveButton(R.string.ok,
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        ftG.L("Color :" + picker.getColor());
//                        //colorPickerView.setTextColor(picker.getColor());
//                        picker.setOldCenterColor(picker.getColor());
//                    }
//                });
//        colorDialogBuilder.setNegativeButton("Cancel",
//                new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.cancel();
//                    }
//                });
//        AlertDialog colorPickerDialog = colorDialogBuilder.create();
//        colorPickerDialog.show();
//    }
    public void onTextColClicked(View view) {
        ftG.selColor = ftLlTextColorTxt.getCurrentTextColor();
        ftG.colorFor = "btnText";
        Intent iT = new Intent(EditBtnActivity.this,ColorPkrActivity.class);
        startActivity(iT);
    }

    public void onGoToBttnsCode(View view) {
        Intent i = new Intent(EditBtnActivity.this, CodeEditorActivity.class);
        updateAndSaveBtn();
        startActivity(i);
    }

    public void onDelete(View view) {
        //
        ftG.wB.delete(ftG.wB.Id);
        ftG.mA.borraTuto();
        ftG.mA.ponLosAndamios(false);

        ftG.editM = false;
        finish();

    }
}
