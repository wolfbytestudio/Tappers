package com.example.zack.tapperstesting;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class NewContact extends Activity {

    private int yearDate, monthDate, dayDate;

    private List<String> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);

        contacts = getIntent().getStringArrayListExtra("contacts");


        Typeface thin = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Thin.ttf");
        Typeface light = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
        Typeface regular = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");


        TextView title = (TextView) findViewById(R.id.contact_title);
        title.setTypeface(light);

        TextView lblContactName = (TextView) findViewById(R.id.lblContactName);
        lblContactName.setTypeface(light);

        TextView lblTransaction = (TextView) findViewById(R.id.lblTransaction);
        lblTransaction.setTypeface(light);

        TextView lblReason = (TextView) findViewById(R.id.lblReason);
        lblReason.setTypeface(light);

        TextView lblDate = (TextView) findViewById(R.id.lblDate);
        lblDate.setTypeface(light);

        final EditText txtContactName = (EditText) findViewById(R.id.txtContactName);
        txtContactName.setTypeface(light);

        final EditText txtTransaction = (EditText) findViewById(R.id.txtTransaction);
        txtTransaction.setTypeface(light);

        final EditText txtReason = (EditText) findViewById(R.id.txtReason);
        txtReason.setTypeface(light);

        final RadioButton rdbTo = (RadioButton) findViewById(R.id.rdbTo);
        rdbTo.setTypeface(light);

        final RadioButton rdbFrom = (RadioButton) findViewById(R.id.rdbFrom);
        rdbFrom.setTypeface(light);

        Calendar cal = Calendar.getInstance();

        yearDate = cal.get(Calendar.YEAR);
        monthDate = cal.get(Calendar.MONTH);
        dayDate = cal.get(Calendar.DAY_OF_MONTH);

        Button newDate = (Button) findViewById(R.id.btnPickNewDate);

        newDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(0);
            }
        });

        Button confirm = (Button) findViewById(R.id.cmdConfirm);

        final TextView text = (TextView) findViewById(R.id.lblSetDate);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(txtContactName.getText().toString().equals("")
                        || txtContactName.getText().toString().equals(null)
                        || txtContactName.getText().toString().equals(" "))
                {
                    Toast.makeText(getApplicationContext(), "Cannot add a nameless contact ",
                            Toast.LENGTH_LONG).show();
                    return;
                }

                if(contacts.contains(txtContactName.getText()))
                {
                    Toast.makeText(getApplicationContext(), "Contact Already Exists",
                            Toast.LENGTH_LONG).show();
                    return;
                }

                if(txtTransaction.getText().toString().equals("")
                        || txtTransaction.getText().toString().equals(null))
                {
                    Toast.makeText(getApplicationContext(), "Must add an amount",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                Intent returnIntent = getIntent();
                returnIntent.putExtra("name", txtContactName.getText().toString());
                returnIntent.putExtra("transaction", txtTransaction.getText().toString());
                returnIntent.putExtra("reason", txtReason.getText().toString());


                returnIntent.putExtra("date", text.getText().toString().substring(15));

                String tofrom = "";
                if(rdbTo.isChecked())
                {
                    tofrom = "to";
                }
                else
                {
                    tofrom = "from";
                }

                returnIntent.putExtra("tofrom", tofrom.toString().toUpperCase());
                setResult(0, returnIntent);
                finish();
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id)
    {
        if(id == 0)
        {
            return new DatePickerDialog(this, dPickerListener, yearDate, monthDate, dayDate);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener dPickerListener =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    yearDate = year;
                    monthDate = monthOfYear + 1;
                    dayDate = dayOfMonth;
                    TextView text = (TextView) findViewById(R.id.lblSetDate);
                    System.out.println();
                    text.setText("Date Selected: " + dayDate + "/" + monthDate + "/" + yearDate);
                }
            };

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_contact, menu);
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
}
