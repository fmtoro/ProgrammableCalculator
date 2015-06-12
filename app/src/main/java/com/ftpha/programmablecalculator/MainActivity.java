package com.ftpha.programmablecalculator;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.List;

import Model.Calc;
import Model.cPage;
import Model.cPageH;

public class MainActivity extends Activity{

//    -----------------------------------------------Fields
    private Button b0       ;
    private Button b1       ;
    private Button b2       ;
    private Button b3       ;
    private Button b4       ;
    private Button b5       ;
    private Button b6       ;
    private Button b7       ;
    private Button b8       ;
    private Button b9       ;


    private Button bPls     ;
    private Button bMin     ;
    private Button bMult    ;
    private Button bDiv     ;
    private Button bDec     ;
    private Button bEqu     ;
    public EditText mainD;

    private WebView wv;
    private preCalc pC;
    private Context xt;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ftG.ctx = this;
        ftG.tlll = (LinearLayout) findViewById(R.id.tlll);

        initBasic();
        testObjs();
    }



    private void testObjs(){

        cPage cP = new cPage(MainActivity.this);
        cPageH cpH = new cPageH(MainActivity.this);

        cP.cName = "Standard";
        cP.pName = "Pg1";

        cP.create();

        cP.cName = "cmhdcalcdm";
        cP.cName = "avanumdt, pe";

        cP = cPage.getById(cP.Id);

        Toast.makeText(MainActivity.this, cP.cName, Toast.LENGTH_LONG).show();

    }
    private void initBasic(){
        b0      = (Button) findViewById(R.id.b0);
        b1      = (Button) findViewById(R.id.b1);
        b2      = (Button) findViewById(R.id.b2);
        b3      = (Button) findViewById(R.id.b3);
        b4      = (Button) findViewById(R.id.b4);
        b5      = (Button) findViewById(R.id.b5);
        b6      = (Button) findViewById(R.id.b6);
        b7      = (Button) findViewById(R.id.b7);
        b8      = (Button) findViewById(R.id.b8);
        b9      = (Button) findViewById(R.id.b9);
        bPls    = (Button) findViewById(R.id.bPlus);
        bMin    = (Button) findViewById(R.id.bMinus);
        bMult   = (Button) findViewById(R.id.bTimes);
        bDiv    = (Button) findViewById(R.id.bDiv);
        bDec    = (Button) findViewById(R.id.bDec);
        bEqu    = (Button) findViewById(R.id.bEqu);

        initDisplay();

        ftG.clcMode = "Start";

        wv = (WebView) findViewById(R.id.wv);


        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion >= 17){
            // Do something for froyo and above versions

            wv.getSettings().setJavaScriptEnabled(true);
            wv.addJavascriptInterface(new preCalc(MainActivity.this), "ftphaCalcObj");
//\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\

//            wv.loadData("", "text/html", null);
//            wv.loadUrl("javascript:ftphaCalcObj.showToast('Hello wierd World!')");
//            wv.loadUrl("javascript:ftphaCalcObj.showToast(ftphaCalcObj.getN1() + ftphaCalcObj.getN2())");

//\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\
        }else{
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

    public void setD(String data){
        mainD.setText(data);
    }


    public void onClick(View v) {



        if (ftG.clcMode.equals("Start")) {
            ftG.setDisplay(MainActivity.this, "");
            ftG.thisNum = "";
            ftG.clcMode = "on-Going";
        } else if (ftG.clcMode.equals("newEquation")) {
            ftG.setDisplay(MainActivity.this, "");
            ftG.thisNum = "";
            ftG.clcMode = "on-Going";
        }else if (ftG.clcMode.equals("re-Start")) {
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
                ftG.appendDisplay (MainActivity.this, "8");
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

                ftG.setDisplay(MainActivity.this, calculate());
                ftG.clcMode = "newEquation";
                ftG.showDisplay(MainActivity.this);
                break;
        }
    }

    private String calculate(){

        StringBuilder codeToSend = new StringBuilder();

        codeToSend.append("javascript:");
        codeToSend.append("a = ");
        codeToSend.append(ftG.display);
        codeToSend.append(";\n");
        codeToSend.append("ftphaCalcObj.setResult(a);");
        String r = codeToSend.toString();

        ftG.setMainAct(MainActivity.this);
        wv.loadData("", "text/html", null);
        wv.loadUrl(r);
        return ftG.result;
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
}
