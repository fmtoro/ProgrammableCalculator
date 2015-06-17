package com.ftpha.programmablecalculator;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.internal.widget.ContentFrameLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ActionMenuView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import Model.Calc;
import Model.DataBaseHelper;
import Model.cBtn;
import Model.cLayout;
import Model.cLayoutH;
import Model.cPage;
import Model.cPageH;

public class MainActivity extends Activity {

    //    -----------------------------------------------Fields
    private Button b0;
    private Button b1;
    private Button b2;
    private Button b3;
    private Button b4;
    private Button b5;
    private Button b6;
    private Button b7;
    private Button b8;
    private Button b9;


    private Button bPls;
    private Button bMin;
    private Button bMult;
    private Button bDiv;
    private Button bDec;
    private Button bEqu;
    public static EditText mainD;

    private static WebView wv;
    private preCalc pC;
    private Context xt;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ifNoDbMakeDb();

        ftG.ctx = this;
        ftG.tlll = (LinearLayout) findViewById(R.id.tlll);
        ftG.currActivity = this;


//        if (cLayout.TableExists(cLayoutH.TABLE_N ,false)) {

//        }

        initBasic();
        ponLosAndamios();

        ftG.setMainAct(MainActivity.this);
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

    private void ponLosAndamios() {

        cLayout.initDb();
        cBtn.initDb();

        ftG.clc = new Calc(ftG.ctx);

        for (cLayout l : ftG.clc.ltS) {
            l.autoCreate();

            for (cBtn b : l.btS) {
                b.autoCreate(l.lLL);
            }
        }
        int a = 1;
    }

    private void addAButton() {
        //Aqui:
        cLayout lay;
        if (ftG.clc.ltS.isEmpty()) {
            lay = new cLayout(ftG.ctx);
            lay.lRelativeH = 1f;
            lay.create();
            lay.autoCreate();
            ftG.clc.ltS.add(lay);
        } else {
            lay = ftG.clc.ltS.get(0);
        }

        cBtn but = new cBtn(ftG.ctx);
        but.lId = lay.btS.size();
        but.ubVisible = LinearLayout.VISIBLE; //0
        but.create();
        but.ubBelongToLayout = but.lId;
        but.ubPosInLayout = but.Id;
        but.ubText = "new " + but.lId + "-" + but.ubPosInLayout;
        but.update(but.Id);
        but.autoCreate(lay.lLL);
        lay.btS.add(but);

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
        but.ubText = "new " + but.lId + "-" + but.ubPosInLayout;
        but.update(but.Id);
        but.autoCreate(lay.lLL);
        lay.btS.add(but);

    }

    private void addABtnInNewLayout() {
        //Aqui:
        cLayout lay;

        lay = new cLayout(ftG.ctx);
        lay.lRelativeH = 1f;
        lay.create();
        lay.autoCreate();


        cBtn but = new cBtn(ftG.ctx);
        but.lId = lay.Id;
        but.ubVisible = LinearLayout.VISIBLE; //0
        but.create();
        but.ubBelongToLayout = but.lId;
        but.ubPosInLayout = 0;
        but.ubText = "new " + but.lId + "-" + but.ubPosInLayout;
        but.update(but.Id);
//        but.autoCreate(lay.lLL);
        lay.btS.add(but);
        ftG.clc.ltS.add(lay);
        borraTuto();
        ponLosAndamios();
    }

    private void borraTuto(){
        ftG.tlll.removeAllViews();
    }

    private void showAll() {
        for (cLayout l : ftG.clc.ltS) {
            l.autoShowAll();

            for (cBtn b : l.btS) {
                b.autoShowAll();
            }
        }
    }


    private void initBasic() {
        b0 = (Button) findViewById(R.id.b0);
        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);
        b3 = (Button) findViewById(R.id.b3);
        b4 = (Button) findViewById(R.id.b4);
        b5 = (Button) findViewById(R.id.b5);
        b6 = (Button) findViewById(R.id.b6);
        b7 = (Button) findViewById(R.id.b7);
        b8 = (Button) findViewById(R.id.b8);
        b9 = (Button) findViewById(R.id.b9);
        bPls = (Button) findViewById(R.id.bPlus);
        bMin = (Button) findViewById(R.id.bMinus);
        bMult = (Button) findViewById(R.id.bTimes);
        bDiv = (Button) findViewById(R.id.bDiv);
        bDec = (Button) findViewById(R.id.bDec);
        bEqu = (Button) findViewById(R.id.bEqu);

        initDisplay();

        ftG.clcMode = "Start";


        wv = (WebView) findViewById(R.id.wv);


        //calculate();


        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion >= 17) {
            // Do something for froyo and above versions

            wv.getSettings().setJavaScriptEnabled(true);
            wv.addJavascriptInterface(new preCalc(MainActivity.this), "co");
        } else {
            pC.T(getString(R.string.youNeedGingerBread));
        }
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
            ftG.setDisplay(MainActivity.this, "");
            ftG.thisNum = "";
            ftG.clcMode = "on-Going";
        } else if (ftG.clcMode.equals("newEquation")) {
            ftG.setDisplay(MainActivity.this, "");
            ftG.thisNum = "";
            ftG.clcMode = "on-Going";
        } else if (ftG.clcMode.equals("re-Start")) {
            ftG.thisNum = "";
            ftG.clcMode = "on-Going";
        }


        switch (v.getId()) {
            case R.id.b0:
                ftG.appendDisplay(MainActivity.this, "0");
                break;
            case R.id.b1:
                ftG.appendDisplay(MainActivity.this, "1");
                break;
            case R.id.b2:
                ftG.appendDisplay(MainActivity.this, "2");
                break;
            case R.id.b3:
                ftG.appendDisplay(MainActivity.this, "3");
                break;
            case R.id.b4:
                ftG.appendDisplay(MainActivity.this, "4");
                break;
            case R.id.b5:
                ftG.appendDisplay(MainActivity.this, "5");
                break;
            case R.id.b6:
                ftG.appendDisplay(MainActivity.this, "6");
                break;
            case R.id.b7:
                ftG.appendDisplay(MainActivity.this, "7");
                break;
            case R.id.b8:
                ftG.appendDisplay(MainActivity.this, "8");
                break;
            case R.id.b9:
                ftG.appendDisplay(MainActivity.this, "9");
                break;
            case R.id.bPlus:
                ftG.appendDisplay(MainActivity.this, " + ");
                ftG.clcMode = "re-Start";
                break;
            case R.id.bMinus:
                ftG.appendDisplay(MainActivity.this, " - ");
                ftG.clcMode = "re-Start";
                break;
            case R.id.bTimes:
                ftG.appendDisplay(MainActivity.this, " * ");
                ftG.clcMode = "re-Start";
                break;
            case R.id.bDiv:
                ftG.appendDisplay(MainActivity.this, " / ");
                ftG.clcMode = "re-Start";
                break;
            case R.id.bDec:
                if (ftG.putDecimal()) {
                    ftG.appendDisplay(MainActivity.this, ".");
                }
                break;
            case R.id.bEqu:

                preCalc.connDisp(mainD);

                calculate();
                ftG.clcMode = "newEquation";

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
        //return ftG.result;
    }

    public static void doCalculate(String jsCode) {

        preCalc.connDisp(mainD);

        StringBuilder codeToSend = new StringBuilder();

        codeToSend.append("javascript:\n");
        codeToSend.append(jsCode);
        String r = codeToSend.toString();

        wv.loadData("", "text/html", null);
        wv.loadUrl(r);
        //return ftG.result;
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
            return;
        }
//        showAll();
        addAButton();
    }

    public void onAddLayout(View view) {
        if (ftG.editM) {
            return;
        }
        addABtnInNewLayout();
    }

    public void onAddB1(View view) {
        if (ftG.editM) {
            return;
        }
        addAButtonToLayout(0);
    }

    public void onAddB2(View view) {
        if (ftG.editM) {
            return;
        }
        addAButtonToLayout(1);
    }

    public void onAddB3(View view) {
        if (ftG.editM) {
            return;
        }
        addAButtonToLayout(2);
    }

    public void onAddB4(View view) {
//        if (ftG.editM) {
//            return;
//        }
//        addAButtonToLayout(3);

        ftG.showResult();
    }


    public void onEditBtn(View view) {
        ftG.editM = true;

        //Aqui: bring in the floating '+'

        //Aqui: Show all buttons

        //Aqui: Show a 'Done' button
    }
}
