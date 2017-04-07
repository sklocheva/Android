package com.example.sophia.currency;

import android.app.AlertDialog;
import android.app.ApplicationErrorReport;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    EditText textCurrencyInput;
    EditText textInputCurrencyText;
    EditText textAmountToConvert;
    TextView textViewResult;
    Button buttonCalculate;
    Button buttonReset;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pref = getPreferences(Context.MODE_PRIVATE);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        textCurrencyInput = (EditText) findViewById(R.id.textCurrencyInput);
        textInputCurrencyText = (EditText) findViewById(R.id.textnputCurrencyText);
        textAmountToConvert = (EditText) findViewById(R.id.textAmountToConvert);
        textViewResult = (TextView) findViewById(R.id.textViewResult);
        buttonCalculate = (Button) findViewById(R.id.buttonCalculate);
        buttonReset = (Button) findViewById(R.id.buttonReset);

        buttonCalculate.setOnClickListener(onButtonClick);
        buttonReset.setOnClickListener(onButtonClick);

        textInputCurrencyText.setText(pref.getString("CYRRENCYSYMBOL", ""));
        textCurrencyInput.setText(pref.getString("CURRENCY", ""));
        textAmountToConvert.setText(pref.getString("VALUELEV", ""));

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

    private void resetCalculator(){

        SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();

        textCurrencyInput.setText("");
        editor.putString("CURRENCY", "");

        textInputCurrencyText.setText("");
        editor.putString("CYRRENCYSYMBOL", "");

        textAmountToConvert.setText("");
        editor.putString("VALUELEV", "");

        textViewResult.setText("");

        editor.commit();
    }

    View.OnClickListener onButtonClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (v.getId())
            {
                case R.id.buttonReset: {
                    resetCalculator();
                }break;

                case R.id.buttonCalculate: {

                    SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();

                    String currencySymbol =  textInputCurrencyText.getText().toString();
                    editor.putString("CYRRENCYSYMBOL", currencySymbol);

                    Float currency = Float.parseFloat(textCurrencyInput.getText().toString());
                    editor.putString("CURRENCY", String.valueOf(currency));

                    Float valueLev = Float.parseFloat(textAmountToConvert.getText().toString());
                    editor.putString("VALUELEV", String.valueOf(valueLev));

                    if((!Float.isNaN(currency) && !Float.isNaN(valueLev))&&!currencySymbol.isEmpty()) {

                        NumberFormat nformat = new DecimalFormat("#.00");
                        String result = nformat.format(valueLev * currency);

                        textViewResult.setText(result + " " + currencySymbol);

                    }

                    editor.commit();

                }break;
            }
        }
    };
}
