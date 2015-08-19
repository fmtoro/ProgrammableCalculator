/*
 * Copyright (c) 2015. Fernando Toro. All rights reserved
 */

package com.ftpha.programmablecalculator;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.EditText;


public class CodeEditorActivity extends Activity {
    EditText txtCode;
    Menu menU;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_code_editor);


        txtCode = ((EditText) findViewById(R.id.txtCode));


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("codeTxt");
            txtCode.setText(value);
        }

        getActionBar().setDisplayHomeAsUpEnabled(true);
    }


    protected void getOut() {

        Intent returnIntent = new Intent();
        returnIntent.putExtra("result",txtCode.getText().toString());
        setResult(RESULT_OK,returnIntent);
        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_code_editor, menu);
        menU = menu;
        initMenu();
        return true;
    }
    public void initMenu()
    {

        MenuItem mnuDone = menU.findItem(R.id.action_done);

        mnuDone.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_done) {
            getOut();
            return true;
        }
        
        return super.onOptionsItemSelected(item);
    }
}
