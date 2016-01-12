package org.tappers.contact;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.tappers.MainActivity;
import org.tappers.transaction.NewTransaction;
import org.tappers.R;
import org.tappers.adapter.TransactionListAdapter;
import org.tappers.util.ActivityUtils;

import java.lang.*;
import java.util.HashMap;


public class ContactPage extends Activity {

    private Contact contact;

    private HashMap<String, Typeface> fonts;

    private ListView transactionList = null;

    public TextView txtTotal;

    private TransactionListAdapter transactionListAdapter;

    private int position;

    private String name;

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
        name = getIntent().getStringExtra("name").toString();
        ImageView characterImageContact = (ImageView) findViewById(R.id.characterImageContact);

        position = MainActivity.getPositionForContact(getIntent().getStringExtra("name").toString());

        contact = MainActivity.contacts.get(position);

        Character charType = Character.getCharacterForName(contact.characterType);
        CharacterBackground charBackground =
                CharacterBackground.getBackgroundForId(contact.backgroundColour);

        characterImageContact.setImageResource(charType.getCharacterFile());

        RelativeLayout charBack = (RelativeLayout) findViewById(R.id.character_background);

        charBack.setBackgroundResource(charBackground.getLargeBackground());

        ImageButton btnNewTrans = (ImageButton) findViewById(R.id.btnNewTransaction);

        btnNewTrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), NewTransaction.class);

                intent.putExtra("pos", position);
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
                                contact.transactions.clear();
                                updateContactList();
                                contact.setTotalString();
                                txtTotal.setText(contact.total);
                                MainActivity.save.save();
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setMessage("Are you sure you want to clear the history for " + contact.name)
                        .setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();


            }
        });

        ImageButton btnBack = (ImageButton) findViewById(R.id.btnBackContact);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                intent.putExtra("name", position);
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
                data.putExtra("pos", position);
                MainActivity.save.save();
            }
        }
    }

    public void updateContactList()
    {
        for(int i = 0; i < contact.transactions.size(); i++)
        {
            if(contact.transactions.get(i).getAmount() == 0)
            {
                contact.transactions.remove(i);
            }
        }

        transactionListAdapter = new TransactionListAdapter(getApplicationContext(), contact, fonts, this);
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
