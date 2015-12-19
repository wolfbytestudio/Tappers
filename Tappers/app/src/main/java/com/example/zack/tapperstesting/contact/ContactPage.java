package com.example.zack.tapperstesting.contact;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zack.tapperstesting.transaction.NewTransaction;
import com.example.zack.tapperstesting.R;
import com.example.zack.tapperstesting.adapter.TransactionListAdapter;
import com.example.zack.tapperstesting.util.ActivityUtils;

import org.w3c.dom.Text;

import java.util.HashMap;


public class ContactPage extends Activity {

    private Contact contact;

    private HashMap<String, Typeface> fonts;

    private ListView transactionList = null;

    private TextView txtTotal;

    private TransactionListAdapter transactionListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_page);

        txtTotal = (TextView) findViewById(R.id.txtTotal);

        fonts = new HashMap<>();
        transactionList = (ListView) findViewById(R.id.lstTransaction);

        Typeface thin = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Thin.ttf");
        Typeface light = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
        Typeface regular = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");

        fonts.put("thin", thin);
        fonts.put("light", light);
        fonts.put("regular", regular);

        TextView lblBackContacts = (TextView) findViewById(R.id.lblBackContacts);
        lblBackContacts.setTypeface(fonts.get("regular"));

        txtTotal.setTypeface(fonts.get("light"));
        txtTotal.setText(getIntent().getStringExtra("total"));

        TextView lblTitle = (TextView) findViewById(R.id.contactPageTitle);
        TextView txtHistory = (TextView) findViewById(R.id.txtHistory);
        lblTitle.setTypeface(fonts.get("light"));
        lblTitle.setText(getIntent().getStringExtra("name").toString());
        txtHistory.setTypeface(fonts.get("light"));

        ImageView characterImageContact = (ImageView) findViewById(R.id.characterImageContact);

        contact = ContactUtil.contact;

        if(contact.characterType == CharacterType.MALE)
        {
            characterImageContact.setImageResource(R.drawable.male);
        }
        else
        {
            characterImageContact.setImageResource(R.drawable.female);
        }


        if(contact.backgroundColour.equalsIgnoreCase("default"))
        {
            characterImageContact.setBackgroundResource(R.drawable.bgcol_default);
        }
        if(contact.backgroundColour.equalsIgnoreCase("blue"))
        {
            characterImageContact.setBackgroundResource(R.drawable.bgcol_blue);
        }
        if(contact.backgroundColour.equalsIgnoreCase("green"))
        {
            characterImageContact.setBackgroundResource(R.drawable.bgcol_green);
        }
        if(contact.backgroundColour.equalsIgnoreCase("turq"))
        {
            characterImageContact.setBackgroundResource(R.drawable.bgcol_turq);
        }
        if(contact.backgroundColour.equalsIgnoreCase("purple"))
        {
            characterImageContact.setBackgroundResource(R.drawable.bgcol_purple);
        }
        if(contact.backgroundColour.equalsIgnoreCase("pink"))
        {
            characterImageContact.setBackgroundResource(R.drawable.bgcol_pink);
        }
        if(contact.backgroundColour.equalsIgnoreCase("red"))
        {
            characterImageContact.setBackgroundResource(R.drawable.bgcol_red);
        }
        if(contact.backgroundColour.equalsIgnoreCase("orange"))
        {
            characterImageContact.setBackgroundResource(R.drawable.bgcol_orange);
        }
        if(contact.backgroundColour.equalsIgnoreCase("gold"))
        {
            characterImageContact.setBackgroundResource(R.drawable.bgcol_gold);
        }
        if(contact.backgroundColour.equalsIgnoreCase("yellow"))
        {
            characterImageContact.setBackgroundResource(R.drawable.bgcol_yellow);
        }
        if(contact.backgroundColour.equalsIgnoreCase("black"))
        {
            characterImageContact.setBackgroundResource(R.drawable.bgcol_black);
        }

        ImageButton btnNewTrans = (ImageButton) findViewById(R.id.btnNewTransaction);

        btnNewTrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), NewTransaction.class);
                startActivityForResult(intent, 1);
            }
        });

        ImageButton btnClearHistory = (ImageButton) findViewById(R.id.btnClearHistory);

        btnClearHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                if (contact.transactions.size() == 0) {
                                    Toast.makeText(getApplicationContext(), "There is no history to delete!", Toast.LENGTH_LONG).show();
                                    return;
                                }
                                ContactUtil.contact.transactions.clear();
                                updateContactList();
                                contact.setTotalString();
                                txtTotal.setText(contact.total);
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setMessage("Are you sure you want to clear the history for " + ContactUtil.contact.name).setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();


            }
        });

        ImageButton btnBack = (ImageButton) findViewById(R.id.btnBackContact);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                setResult(ActivityUtils.CONTACT_RETURN, intent);
                finish();
            }
        });

        updateContactList();
    }

    /**
     * Overriden method speaks with other intents
     * 0 = new Contact
     * @param requestCode - the request coming in
     * @param resultCode - the result coming in
     * @param data - the intent data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if (resultCode == 1) {
                updateContactList();
                contact.setTotalString();
                txtTotal.setText(contact.total);
            }
        }
    }

    private void updateContactList()
    {
        for(int i = 0; i < contact.transactions.size(); i++)
        {
            if(contact.transactions.get(i).getAmount() == 0)
            {
                contact.transactions.remove(i);
            }
        }

        transactionListAdapter = new TransactionListAdapter(getApplicationContext(), contact, fonts);
        transactionList.setAdapter(transactionListAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contact_page, menu);
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
