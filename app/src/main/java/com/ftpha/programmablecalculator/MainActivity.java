package com.ftpha.programmablecalculator;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.io.IOException;

import Model.Calc;
import Model.DataBaseHelper;
import Model.cBtn;
import Model.cLayout;
import Model.cMemory;

public class MainActivity extends Activity {

    //    -----------------------------------------------Fields
    private Button btnAdd;
    private Button btnEdit;
    private Button btnRowAdd;


    public static EditText mainD;

    private static WebView wv;





    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ftG.ctx = this;
        ifNoDbMakeDb();

        ftG.setMainAct(MainActivity.this);

        ftG.tlll = (LinearLayout) findViewById(R.id.tlll);
        ftG.currActivity = this;

        initBasic();


        ponLosAndamios(false);

    }

    private void ifNoDbMakeDb() {
        DataBaseHelper myDbHelper = new DataBaseHelper(this);

        try {

            myDbHelper.createDataBase(true);

        } catch (IOException ioe) {

            throw new Error("Unable to create database");

        }
    }

    public static String getDisplay(){
        return mainD.getText().toString();
    }

    public void ponLosAndamios(boolean padded) {

        cLayout.initDb();
        cBtn.initDb();
        cMemory.initDb();

        ftG.clc = new Calc(ftG.ctx,padded); //Aqui: Instead of new, getById with Id = 1

        for (cLayout l : ftG.clc.ltS) {
            l.createActual();

            for (cBtn b : l.btS) {
                b.createActual(l.lLL);
            }
        }

        for (cMemory mem : ftG.clc.memS) {
            mem.createActual();
        }


    }

    private void addAMem() {
        //Aqui:

        cMemory cMem = new cMemory(ftG.ctx);
        cMem.mText = mainD.getText().toString();
        cMem.mGroup = "Std";
        cMem.mName = "";
        cMem.mTxtSize = 14;
        cMem.mBColor = Color.parseColor("#FFCCCCCC");
        cMem.mTxtColor = Color.parseColor("#FF222222");
        cMem.mHeight = 80;


        cMem.create();
        cMem.createActual();

        ftG.clc.memS.add(cMem);
        for (cMemory m : ftG.clc.memS) {
            m.update(m.Id);
        }
        ftG.clc.memS = cMemory.listAll();

    }

    public void addAMem(String strToAdd) {
        //Aqui: thread issue. Fix
        //basically this has to be done in the UI thread.

        cMemory cMem = new cMemory(ftG.ctx);
        cMem.mText = strToAdd;

        cMem.create();
        cMem.createActual();

        ftG.clc.memS.add(cMem);
        for (cMemory m : ftG.clc.memS) {
            m.update(m.Id);
        }
        ftG.clc.memS = cMemory.listAll();

    }

    private void addAButtonToLayout(int theLayout) {
        //Aqui:
        cLayout lay;

        lay = ftG.clc.ltS.get(theLayout);


        cBtn but = new cBtn(ftG.ctx);
        but.lId = lay.Id;
        but.ubVisible = LinearLayout.VISIBLE; //0
        but.create();
        but.ubBelongToLayout = but.lId;
        but.ubPosInLayout = lay.btS.size();
        but.ubText = "new";
        but.update(but.Id);
        but.createActual(lay.lLL);
        lay.btS.add(but);

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        borraTuto();
        ponLosAndamios(false);
    }

    private void MoveBtnFromTo(cBtn moveBtn,
                               long toLayout,
                               int toPosition){


        for (cLayout l : ftG.clc.ltS) {
            if (l.Id == toLayout) {
                for (cBtn b : l.btS) {
                    if(b.ubPosInLayout >= toPosition){
                        b.moveRight();
                    }
                }
            }
        }

        moveBtn.moveTo(toLayout, toPosition);

    }


    private void addABtnInNewLayout() {
        //Aqui:
        cLayout lay;

        lay = new cLayout(ftG.ctx);
        lay.lRelativeH = 1f;
        lay.create();
        lay.createActual();


        cBtn but = new cBtn(ftG.ctx);
        but.lId = lay.Id;
        but.ubVisible = LinearLayout.VISIBLE; //0
        but.create();
        but.ubBelongToLayout = but.lId;
        but.ubPosInLayout = 0;
        but.ubText = "new " + but.lId + "-" + but.ubPosInLayout;
        but.update(but.Id);

        lay.btS.add(but);
        ftG.clc.ltS.add(lay);
        borraTuto();
        ponLosAndamios(false);
    }

    public void borraTuto(){
        ftG.tlll.removeAllViews();
        ftG.memLl = (LinearLayout) ftG.mA.findViewById(R.id.memLL);
        ftG.memLl.removeAllViews();
    }

    private void showAll() {
        for (cLayout l : ftG.clc.ltS) {
            l.autoShowAll();

            for (cBtn b : l.btS) {
                b.autoShowAll();
            }
        }
    }


    @SuppressLint("SetJavaScriptEnabled")
    private void initBasic() {
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnEdit = (Button) findViewById(R.id.btnEdit);
        btnRowAdd = (Button) findViewById(R.id.btnAddRow);

        initDisplay();

        ftG.clcMode = "Start";


        wv = (WebView) findViewById(R.id.wv);
        //wv.setWebChromeClient(new WebChromeClient());

        //calculate();


//        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
//        if (currentapiVersion >= 17) {
            // Do something for froyo and above versions

            wv.getSettings().setJavaScriptEnabled(true);
            wv.addJavascriptInterface(new preCalc(MainActivity.this), "co");
//        } else {
//            ftG.T(getString(R.string.youNeedGingerBread));
//        }
    }

    private void initDisplay() {
        mainD = (EditText) findViewById(R.id.txtMainDisplay);

        mainD.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mainD.setSelection(mainD.getText().length());

            }
        });
    }

    public void setD(String data) {

        mainD.setText(data);
        mainD.refreshDrawableState();
    }

    public void showRsltShortcut() {

        mainD.setText(ftG.result);
    }


    public void onClick(View v) {

        if (ftG.editM) {
            return;
        }

        if (ftG.clcMode.equals("Start")) {
//            ftG.setDisplay(MainActivity.this, "");
            ftG.YzS.add("");
            //ftG.thisNum = "";
            ftG.clcMode = "on-Going";
        }


        switch (v.getId()) {
            case R.id.b0:
                ftG.appendDisplay(MainActivity.this, "0", true);
                break;
            case R.id.b1:
                ftG.appendDisplay(MainActivity.this, "1", true);
                break;
            case R.id.b2:
                ftG.appendDisplay(MainActivity.this, "2", true);
                break;
            case R.id.b3:
                ftG.appendDisplay(MainActivity.this, "3", true);
                break;
            case R.id.b4:
                ftG.appendDisplay(MainActivity.this, "4", true);
                break;
            case R.id.b5:
                ftG.appendDisplay(MainActivity.this, "5", true);
                break;
            case R.id.b6:
                ftG.appendDisplay(MainActivity.this, "6", true);
                break;
            case R.id.b7:
                ftG.appendDisplay(MainActivity.this, "7", true);
                break;
            case R.id.b8:
                ftG.appendDisplay(MainActivity.this, "8", true);
                break;
            case R.id.b9:
                ftG.appendDisplay(MainActivity.this, "9", true);
                break;
            case R.id.bPlus:
                ftG.appendDisplay(MainActivity.this, " + ", false);
                ftG.clcMode = "Start";
                break;
            case R.id.bMinus:
                ftG.appendDisplay(MainActivity.this, " - ", false);
                ftG.clcMode = "Start";
                break;
            case R.id.bTimes:
                ftG.appendDisplay(MainActivity.this, " * ", false);
                ftG.clcMode = "Start";
                break;
            case R.id.bDiv:
                ftG.appendDisplay(MainActivity.this, " / ", false);
                ftG.clcMode = "Start";
                break;
            case R.id.bEnter:

                String myX = ftG.YzS.get();
                String myD = ftG.display;
                int lastOne = myD.lastIndexOf(myX);
                myD = myD.substring(0, lastOne);
                final String myR = myD + "";

                ftG.display = myR;

                mainD.setText(myR);

                ftG.clcMode = "Start";
                break;
            case R.id.bC:
                ftG.setDisplay(MainActivity.this, "");
                mainD.setText("0");
                ftG.clcMode = "Start";
                break;
            case R.id.bback:
                String x = mainD.getText().toString();
                if (x.length()>=1) {
                    mainD.setText(x.substring(0,x.length()-1));
                    ftG.history = x.substring(0, x.length() - 1);
                    ftG.display = mainD.getText().toString();
                }
                ftG.clcMode = "Start";
                break;
            case R.id.bDec:
                if (ftG.putDecimal()) {
                    ftG.appendDisplay(MainActivity.this, ".", true);
                }
                break;
            case R.id.bEqu:
                String a = "";
                preCalc.connDisp(mainD);

                //ftG.YzS.add("");
                calculate();
                //ftG.clcMode = "Start";
                a = mainD.getText().toString();
                ftG.display = a;

                break;
        }

    }

    private void calculate() {

        StringBuilder codeToSend = new StringBuilder();

        codeToSend.append("javascript:");
        codeToSend.append("a = ");
        codeToSend.append(ftG.display);
        codeToSend.append(";\n");
        codeToSend.append("co.setD(a);");
        String r = codeToSend.toString();

        wv.loadData("", "text/html", null);
        wv.loadUrl(r);

    }

    public static void doCalculate(String jsCode) {

        preCalc.connDisp(mainD);

        StringBuilder codeToSend = new StringBuilder();

        codeToSend.append("javascript:\n");
        codeToSend.append(jsCode);
        String r = codeToSend.toString();

        wv.loadData("", "text/html", null);
        wv.loadUrl(r);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void onNumClick(View v) {
        onClick(v);
    }

    public void onOpsClick(View v) {
        onClick(v);
    }

    public void addMem(View view) {
        if (ftG.editM) {
            addAButtonToLayout(0);
        } else {
            addAMem();
        }
//        ftG.dialogTitle = "Hello World!";
//        ftG.dialogHeading = "Algo";
//        ftG.dialogContent = "Hello World!";
//        ftG.dialogBtnPositive = "OK";
//        ftG.dialogBtnNegative = "No way!";
//        ftG.dialogBtnNeutral = "Cancel";
//        ftG.msgBox();

    }


    public void onAddB1(View view) {
        if (ftG.editM) {
            return;
        }
        addAButtonToLayout(0);
    }


    public void onEditBtn(View view) {
        changeEditMode();

    }

    public void changeEditMode() {
        //Aqui: bring in the floating '+'
//        #ffc8654c         //Rojo
//        #ff1e0978         //Azul
//        #fff9f7d5         //Blanquito
//        #ff1d8f16         //Verdecito
//        #ffd6d7d7         //Gris

        if (!ftG.editM) {
            btnAdd.setText("Add Button");
            btnAdd.setBackgroundColor(Color.parseColor("#ffc8654c"));
            btnAdd.setTextColor(Color.parseColor("#ff000000"));

            btnEdit.setText("Done");
            btnEdit.setBackgroundColor(Color.parseColor("#FF77A575"));
            btnEdit.setTextColor(Color.parseColor("#fff9f7d5"));

            btnRowAdd.setVisibility(View.VISIBLE);

            ftG.editM = true;

            //Aqui: Show all buttons
            borraTuto();
            ponLosAndamios(true);

        } else {
            btnAdd.setText("Add M");
            btnAdd.setBackgroundColor(Color.parseColor("#ff1e0978"));
            btnAdd.setTextColor(Color.parseColor("#fff9f7d5"));

            btnEdit.setText("Edit");
            btnEdit.setBackgroundColor(Color.parseColor("#ffd6d7d7"));
            btnEdit.setTextColor(Color.parseColor("#ff000000"));

            btnRowAdd.setVisibility(View.GONE);

            ftG.editM = false;

            borraTuto();
            ponLosAndamios(false);
        }
    }

    public void onAddRow(View view) {
        addABtnInNewLayout();
    }
}
