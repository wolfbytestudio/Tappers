package com.example.zack.tapperstesting.contact;

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
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zack.tapperstesting.util.ActivityUtils;
import com.example.zack.tapperstesting.R;

import java.util.Calendar;
import java.util.List;

public class NewContact extends Activity {

    private int yearDate, monthDate, dayDate;

    private List<String> contacts;

    private TextView lblDateSelected;

    private String backgroundSelected = "default";



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);

        contacts = getIntent().getStringArrayListExtra("contacts");

        Typeface thin = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Thin.ttf");
        Typeface light = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
        Typeface regular = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");


        TextView lblBackContacts = (TextView) findViewById(R.id.lblBackContactsNew);
        lblBackContacts.setTypeface(regular);

        TextView title = (TextView) findViewById(R.id.contact_title);
        TextView lblContactName = (TextView) findViewById(R.id.lblContactName);
        TextView lblTransaction = (TextView) findViewById(R.id.lblTransaction);
        TextView lblReason = (TextView) findViewById(R.id.lblReason);
        lblDateSelected = (TextView) findViewById(R.id.lblSetDate);
        TextView lblDate = (TextView) findViewById(R.id.lblDate);

        lblContactName.setTypeface(light);
        title.setTypeface(light);
        lblTransaction.setTypeface(light);
        lblReason.setTypeface(light);
        initializeDate();
        lblDateSelected.setTypeface(light);
        lblDate.setTypeface(light);
        loadBackgrounds();

        final EditText txtContactName = (EditText) findViewById(R.id.txtContactName);
        final EditText txtTransaction = (EditText) findViewById(R.id.txtTransaction);
        final EditText txtReason = (EditText) findViewById(R.id.txtReason);
        final RadioButton rdbTo = (RadioButton) findViewById(R.id.rdbTo);
        final RadioButton rdbFrom = (RadioButton) findViewById(R.id.rdbFrom);
        final RadioButton rdbMale = (RadioButton) findViewById(R.id.rdbMale);
        final RadioButton rdbFemale = (RadioButton) findViewById(R.id.rdbFemale);
        final Button newDate = (Button) findViewById(R.id.btnPickNewDate);
        final Button confirm = (Button) findViewById(R.id.cmdConfirm);

        txtContactName.setTypeface(light);
        txtTransaction.setTypeface(light);
        txtReason.setTypeface(light);
        rdbTo.setTypeface(light);
        rdbFrom.setTypeface(light);


        newDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(0);
            }
        });


        txtContactName.setSelected(false);

        ImageButton btnBack = (ImageButton) findViewById(R.id.btnBackNewContact);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = getIntent();
                setResult(-1, returnIntent);
                finish();
            }
        });


        TextView backgroundLabel = (TextView) findViewById(R.id.lblBackgroundColours);
        backgroundLabel.setTypeface(light);

        final ImageButton bgDefault = (ImageButton) findViewById(R.id.bgcol_default);
        final ImageButton bgBlue = (ImageButton) findViewById(R.id.bgcol_blue);
        final ImageButton bgGreen = (ImageButton) findViewById(R.id.bgcol_green);
        final ImageButton bgTurq = (ImageButton) findViewById(R.id.bgcol_turq);
        final ImageButton bgPurple = (ImageButton) findViewById(R.id.bgcol_purple);
        final ImageButton bgPink = (ImageButton) findViewById(R.id.bgcol_pink);
        final ImageButton bgRed = (ImageButton) findViewById(R.id.bgcol_red);
        final ImageButton bgOrange = (ImageButton) findViewById(R.id.bgcol_orange);
        final ImageButton bgGold = (ImageButton) findViewById(R.id.bgcol_gold);
        final ImageButton bgYellow = (ImageButton) findViewById(R.id.bgcol_yellow);
        final ImageButton bgBlack = (ImageButton) findViewById(R.id.bgcol_black);

        bgDefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backgroundSelected = "default";
                Toast.makeText(getApplicationContext(), "White Background Selected", Toast.LENGTH_LONG).show();
            }
        });

        bgBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backgroundSelected = "blue";
                Toast.makeText(getApplicationContext(), "Blue Background Selected", Toast.LENGTH_LONG).show();
            }
        });

        bgGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backgroundSelected = "green";
                Toast.makeText(getApplicationContext(), "Green Background Selected", Toast.LENGTH_LONG).show();
            }
        });
        bgTurq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backgroundSelected = "turq";
                Toast.makeText(getApplicationContext(), "Turqoise Background Selected", Toast.LENGTH_LONG).show();
            }
        });
        bgPurple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backgroundSelected = "purple";
                Toast.makeText(getApplicationContext(), "Purple Background Selected", Toast.LENGTH_LONG).show();
            }
        });
        bgPink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backgroundSelected = "pink";
                Toast.makeText(getApplicationContext(), "Pink Background Selected", Toast.LENGTH_LONG).show();
            }
        });
        bgRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backgroundSelected = "red";
                Toast.makeText(getApplicationContext(), "Red Background Selected", Toast.LENGTH_LONG).show();
            }
        });
        bgOrange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backgroundSelected = "Orange";
                Toast.makeText(getApplicationContext(), "Orange Background Selected", Toast.LENGTH_LONG).show();
            }
        });
        bgGold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backgroundSelected = "gold";
                Toast.makeText(getApplicationContext(), "Gold Background Selected", Toast.LENGTH_LONG).show();
            }
        });
        bgYellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backgroundSelected = "yellow";
                Toast.makeText(getApplicationContext(), "Yellow Background Selected", Toast.LENGTH_LONG).show();
            }
        });
        bgBlack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backgroundSelected = "black";
                Toast.makeText(getApplicationContext(), "Black Background Selected", Toast.LENGTH_LONG).show();
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(txtContactName.getText().toString().equals("")
                        || txtContactName.getText().toString().equals(" "))
                {
                    Toast.makeText(getApplicationContext(), "Cannot add a nameless contact ",
                            Toast.LENGTH_LONG).show();
                    return;
                }

                if(txtReason.getText().toString().equals(""))
                {
                    txtReason.setText("Reason Unspecific");
                }

                if(contacts.contains(txtContactName.getText().toString()))
                {
                    Toast.makeText(getApplicationContext(), "Contact Already Exists",
                            Toast.LENGTH_LONG).show();
                    return;
                }

                if(txtTransaction.getText().toString().equals(""))
                {
                    txtTransaction.setText("0");
                }

                Intent returnIntent = getIntent();


                String toFrom = "To";
                if(rdbTo.isChecked()) { toFrom = "to"; }
                else { toFrom = "from"; }



                String maleFemale = "Male";
                if(rdbMale.isChecked()) { maleFemale = "Male"; }
                else { maleFemale = "Female"; }

                returnIntent.putExtra("name", txtContactName.getText().toString());
                returnIntent.putExtra("transaction", txtTransaction.getText().toString());
                returnIntent.putExtra("reason", txtReason.getText().toString());
                returnIntent.putExtra("date", lblDateSelected.getText().toString().substring(15));
                returnIntent.putExtra("character", maleFemale.toUpperCase());
                returnIntent.putExtra("bgcol", backgroundSelected);
                returnIntent.putExtra("tofrom", toFrom.toUpperCase());

                setResult(ActivityUtils.NEW_CONTACT_RETURN, returnIntent);
                finish();
            }
        });
    }


    private void loadBackgrounds()
    {


    }

    private void initializeDate()
    {
        Calendar cal = Calendar.getInstance();

        yearDate = cal.get(Calendar.YEAR);
        monthDate = cal.get(Calendar.MONTH);
        dayDate = cal.get(Calendar.DAY_OF_MONTH);

        lblDateSelected.setText("Date Selected: " + dayDate +"/" + (monthDate + 1) + "/" + yearDate);
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
