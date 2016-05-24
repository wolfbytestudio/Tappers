package org.tappers.ui.page;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.tappers.Contacts;
import org.tappers.contact.*;
import org.tappers.ui.data.*;
import org.tappers.R;
import org.tappers.ui.adapter.TransactionListAdapter;
import org.tappers.ui.data.Character;
import org.tappers.util.ActivityConstants;
import org.tappers.util.CustomTypeFaces;

import java.lang.*;


public class ContactPage extends Activity
{

    private Contact contact;

    private ListView transactionList = null;

    private TransactionListAdapter transactionListAdapter;

    private int position;

    public TextView txtTotal;

    public TextView lblTitle;

    private org.tappers.ui.data.Character charType;
    private CharacterBackground charBackground;
    private RelativeLayout charBack;
    private ImageView characterImageContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_page);

        position = getIntent().getIntExtra("pos", -1);

        contact = Contacts.SINGLETON.getContacts().get(position);

        txtTotal = (TextView) findViewById(R.id.txtTotal);

        transactionList = (ListView) findViewById(R.id.lstTransaction);
        lblTitle = (TextView) findViewById(R.id.contactPageTitle);
        Typeface thin = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Thin.ttf");
        Typeface light = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
        Typeface regular = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");

        TextView lblBackContacts = (TextView) findViewById(R.id.lblBackContacts);
        lblBackContacts.setTypeface(CustomTypeFaces.get("regular"));

        txtTotal.setTypeface(CustomTypeFaces.get("light"));
        txtTotal.setText(contact.getTotalString());


        TextView txtHistory = (TextView) findViewById(R.id.txtHistory);
        lblTitle.setTypeface(CustomTypeFaces.get("light"));
        lblTitle.setText(contact.getName());
        txtHistory.setTypeface(CustomTypeFaces.get("light"));

        characterImageContact = (ImageView) findViewById(R.id.characterImageContact);

        charBack = (RelativeLayout) findViewById(R.id.character_background);
        charBackground =  CharacterBackground.getBackgroundForId(contact.getBackgroundColour());
        charType = Character.getCharacterForName(contact.getCharacterType());

        characterImageContact.setImageResource(charType.getCharacterFile());
        charBack.setBackgroundResource(charBackground.getLargeBackground());

        ImageButton btnNewTrans = (ImageButton) findViewById(R.id.btnNewTransaction);

        btnNewTrans.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
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
                                if (contact.getTransactions().size() == 0) {
                                    Toast.makeText(getApplicationContext(),
                                            "There is no history to delete!",
                                            Toast.LENGTH_LONG).show();
                                    return;
                                }
                                contact.getTransactions().clear();
                                updateContactList();
                                txtTotal.setText(contact.getTotalString());
                                Contacts.SINGLETON.save(getApplicationContext());
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setMessage("Are you sure you want to clear all transactions for " + contact.getName() + "?")
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
                setResult(ActivityConstants.CONTACT_PAGE_RETURN, intent);
                finish();
            }
        });

        ImageButton edit = (ImageButton) findViewById(R.id.btnEditContact);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(view.getContext(), EditContact.class);
                intent.putExtra("pos", position);
                startActivityForResult(intent, ActivityConstants.EDIT_CONTACT);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == 1)
        {
            if (resultCode == 1)
            {
                updateContactList();
                txtTotal.setText(contact.getTotalString());
                data.putExtra("pos", position);
                Contacts.SINGLETON.save(this);
            }
        }
        if(requestCode == ActivityConstants.EDIT_CONTACT)
        {
            if(resultCode == ActivityConstants.EDIT_CONTACT_RETURN)
            {
                charBackground =  CharacterBackground.getBackgroundForId(contact.getBackgroundColour());
                charType = Character.getCharacterForName(contact.getCharacterType());

                characterImageContact.setImageResource(charType.getCharacterFile());
                charBack.setBackgroundResource(charBackground.getLargeBackground());

                txtTotal.setText(contact.getTotalString());
                lblTitle.setText(contact.getName());
                Contacts.SINGLETON.save(this);
                updateContactList();
            }
        }
    }

    public void updateContactList()
    {
        for(int i = 0; i < contact.getTransactions().size(); i++)
        {
            if(contact.getTransactions().get(i).getAmount() == 0)
            {
                contact.getTransactions().remove(i);
            }
        }

        transactionListAdapter = new TransactionListAdapter(getApplicationContext(), this, position);
        transactionList.setAdapter(transactionListAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contact_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
