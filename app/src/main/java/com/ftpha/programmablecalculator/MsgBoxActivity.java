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
import android.widget.TextView;


public class MsgBoxActivity extends Activity {
    TextView lblTitle;
    TextView lblHeading;
    TextView lblContent;
    Button btnPos;
    Button btnNeg;
    Button btnNeut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_box);


        lblTitle = (TextView) findViewById(R.id.txtTitle);
        lblHeading = (TextView) findViewById(R.id.txtHeading);
        lblContent = (TextView) findViewById(R.id.txtContent);

        btnPos = (Button) findViewById(R.id.btnPos);
        btnNeg = (Button) findViewById(R.id.btnNeg);
        btnNeut = (Button) findViewById(R.id.btnNeut);



        if (ftG.dialogTitle.isEmpty()) {
            lblTitle.setVisibility(View.GONE);
        } else {
            lblTitle.setVisibility(View.VISIBLE);
            lblTitle.setText(ftG.dialogTitle);
        }


        if (ftG.dialogHeading.isEmpty()) {
            lblHeading.setVisibility(View.GONE);
        } else {
            lblHeading.setVisibility(View.VISIBLE);
            lblHeading.setText(ftG.dialogHeading);
        }

        if (ftG.dialogContent.isEmpty()) {
            lblContent.setVisibility(View.GONE);
        } else {
            lblContent.setVisibility(View.VISIBLE);
            lblContent.setText(ftG.dialogContent);
        }



        if (ftG.dialogBtnPositive.isEmpty()) {
            btnPos.setVisibility(View.GONE);
        } else {
            btnPos.setVisibility(View.VISIBLE);
            btnPos.setText(ftG.dialogBtnPositive);
        }

        if (ftG.dialogBtnNegative.isEmpty()) {
            btnNeg.setVisibility(View.GONE);
        } else {
            btnNeg.setVisibility(View.VISIBLE);
            btnNeg.setText(ftG.dialogBtnNegative);
        }

        if (ftG.dialogBtnNeutral.isEmpty()) {
            btnNeut.setVisibility(View.GONE);
        } else {
            btnNeut.setVisibility(View.VISIBLE);
            btnNeut.setText(ftG.dialogBtnNeutral);
        }

    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_msg_box, menu);
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

    private void resetControls() {
        ftG.dialogTitle = "";
        ftG.dialogTitle = "";
        ftG.dialogTitle = "";
        ftG.dialogBtnPositive = "";
        ftG.dialogBtnNegative = "";
        ftG.dialogBtnNeutral = "";
    }


    public void OnPositive(View view) {
        resetControls();
        //Aqui
        finish();
    }

    public void OnNegative(View view) {
        resetControls();
        //Aqui
        finish();
    }

    public void OnNeutral(View view) {
        resetControls();
        //Aqui
        finish();
    }
}
