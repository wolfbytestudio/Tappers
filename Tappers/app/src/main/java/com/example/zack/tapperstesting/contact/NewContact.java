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

    private String characterSelected = "default male";

    private ImageButton lastCharSelected;

    private ImageButton charMale;
    private ImageButton charFemale;
    private ImageButton charStonerBob;
    private ImageButton charGothGirl;
    private ImageButton charStonerBobAlt;
    private ImageButton charAbudady;
    private ImageButton charAbiba;

    private void resetBackgrounds()
    {
        charMale.setBackground(null);
        charFemale.setBackground(null);
        charStonerBob.setBackground(null);
        charGothGirl.setBackground(null);
        charStonerBobAlt.setBackground(null);
        charAbudady.setBackground(null);
        charAbiba.setBackground(null);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_contact);

        contacts = getIntent().getStringArrayListExtra("contacts");

        charMale = (ImageButton) findViewById(R.id.char_default_male);
        charFemale = (ImageButton) findViewById(R.id.char_default_female);
        charStonerBob = (ImageButton) findViewById(R.id.char_stoner_bob);
        charGothGirl = (ImageButton) findViewById(R.id.char_goth_girl);
        charStonerBobAlt = (ImageButton) findViewById(R.id.char_stoner_bob_alt);
        charAbudady = (ImageButton) findViewById(R.id.char_abudady);
        charAbiba = (ImageButton) findViewById(R.id.char_abiba);

        lastCharSelected = charMale;
        lastCharSelected.setBackgroundResource(CharacterBackground.DEFAULT.getSmallBackground());

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
            public void onClick(View v) {selectBackground("default");
            }
        });
        bgBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {selectBackground("blue");
            }
        });
        bgGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {selectBackground("green");
            }
        });
        bgTurq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {selectBackground("turq");
            }
        });
        bgPurple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {selectBackground("purple");
            }
        });
        bgPink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {selectBackground("pink");
            }
        });
        bgRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {selectBackground("red");
            }
        });
        bgOrange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {selectBackground("orange");
            }
        });
        bgGold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectBackground("gold");
            }
        });
        bgYellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectBackground("yellow");
            }
        });
        bgBlack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectBackground("black");
            }
        });


        TextView lblChars = (TextView) findViewById(R.id.lblCharacters);
        lblChars.setTypeface(light);


        charMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectCharacter(charMale, "default male");
            }
        });
        charFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectCharacter(charFemale, "default female");
            }
        });
        charStonerBob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectCharacter(charStonerBob, "stoner bob");
            }
        });
        charGothGirl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectCharacter(charGothGirl, "goth girl");
            }
        });

        charStonerBobAlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectCharacter(charStonerBobAlt, "stoner bob alt");
            }
        });

        charAbudady.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectCharacter(charAbudady, "abudady");
            }
        });

        charAbiba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectCharacter(charAbiba, "abiba");
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

                returnIntent.putExtra("name", txtContactName.getText().toString());
                returnIntent.putExtra("transaction", txtTransaction.getText().toString());
                returnIntent.putExtra("reason", txtReason.getText().toString());
                returnIntent.putExtra("date", lblDateSelected.getText().toString().substring(15));
                returnIntent.putExtra("character", characterSelected);
                returnIntent.putExtra("bgcol", backgroundSelected);
                returnIntent.putExtra("tofrom", toFrom.toUpperCase());

                setResult(ActivityUtils.NEW_CONTACT_RETURN, returnIntent);
                finish();
            }
        });
    }


    private void selectCharacter(ImageButton character, String identifier)
    {
        resetBackgrounds();
        characterSelected = identifier;
        CharacterBackground bg = CharacterBackground.getBackgroundForId(backgroundSelected);
        character.setBackgroundResource(bg.getSmallBackground());
        lastCharSelected = character;
    }

    private void selectBackground(String id)
    {
        backgroundSelected = id;
        CharacterBackground bg = CharacterBackground.getBackgroundForId(backgroundSelected);
        lastCharSelected.setBackgroundResource(bg.getSmallBackground());
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
