package com.example.zack.tapperstesting;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zack.tapperstesting.adapter.MainListAdapter;
import com.example.zack.tapperstesting.contact.Contact;
import com.example.zack.tapperstesting.contact.ContactPage;
import com.example.zack.tapperstesting.contact.ContactUtil;
import com.example.zack.tapperstesting.contact.NewContact;
import com.example.zack.tapperstesting.transaction.Transaction;
import com.example.zack.tapperstesting.transaction.TransactionType;
import com.example.zack.tapperstesting.util.ActivityUtils;
import com.example.zack.tapperstesting.util.LoadHandler;
import com.example.zack.tapperstesting.util.SaveHandler;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends Activity {

    /**
     * All the contacts
     */
    private ArrayList<Contact> contacts;

    /**
     * The list view
     */
    private ListView listView;

    /**
     * Custom view adapter for custom view controls
     */
    private MainListAdapter customListViewAdapter;

    /**
     * The types of font
     */
    private HashMap<String, Typeface> typeFaces = new HashMap<>();

    /**
     * Contains the last contact page position
     */
    public int contactPagePosition = 0;

    /**
     * Object for handling saving
     */
    public SaveHandler save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contacts = new ArrayList<>();


        LoadHandler load = new LoadHandler(getApplicationContext());
        load.load();

        contacts = load.getContacts();

        //contacts.clear();
        save = new SaveHandler(contacts, getApplicationContext());



        Typeface thin = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Thin.ttf");
        Typeface light = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
        Typeface regular = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");



        typeFaces.put("thin", thin);
        typeFaces.put("light", light);
        typeFaces.put("regular", regular);


        TextView title = (TextView) findViewById(R.id.txtTitle);
        title.setTypeface(light);


        listView = (ListView) findViewById(R.id.lstContacts);

        customListViewAdapter = new MainListAdapter(getApplicationContext(), contacts, typeFaces, this);
        listView.setAdapter(customListViewAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int mPos = position;
                Intent intent = new Intent(view.getContext(), ContactPage.class);

                intent.putExtra("name", contacts.get(position).name);

                Contact c = contacts.get(position);
                ContactUtil.contact = c;
                c.setTotalString();
                intent.putExtra("total", c.total);
                contactPagePosition = position;
                startActivityForResult(intent, ActivityUtils.CONTACT);

            }
        });

        ImageView img = (ImageView) findViewById(R.id.newContact);


        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(v.getContext(), NewContact.class);
                ArrayList<String> contactNames = new ArrayList<String>();
                for (Contact c : contacts) {
                    contactNames.add(c.name);
                }

                intent.putStringArrayListExtra("contacts", contactNames);
                startActivityForResult(intent, ActivityUtils.NEW_CONTACT);
            }
        });

    }

    /**
     * Adds a new Contact to the list and repopulates the list view
     * @param contact - the contact being added
     */
    public void addContact(Contact contact)
    {
        contact.setTotalString();
        contacts.add(0, contact);


        save.save();

        customListViewAdapter.notifyDataSetChanged();

    }

    public void removeContact(int index)
    {
        contacts.remove(index);
        customListViewAdapter.notifyDataSetChanged();

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

        if(requestCode == ActivityUtils.NEW_CONTACT)
        {
            if(resultCode == ActivityUtils.NEW_CONTACT_RETURN)
            {
                Log.d("a", "Hi1");
                try
                {
                    Log.d("a", "Hi2");
                    String name = data.getStringExtra("name");
                    String amount = data.getStringExtra("transaction");
                    String reason = data.getStringExtra("reason");
                    String date = data.getStringExtra("date");
                    String tofrom = data.getStringExtra("tofrom");

                    TransactionType type = TransactionType.valueOf(tofrom);
                    Contact newCon = new Contact(name, "", date);

                    Log.d("a", "Hi3");
                    double am = 0;
                    try
                    {
                        am = Double.parseDouble(amount);
                    }
                    catch(Exception e) { }

                    newCon.addTransaction(new Transaction(type,
                            am, date, reason));

                    if (reason.equalsIgnoreCase(""))
                    {
                        reason = "Reason Unspecific";
                    }
                    System.out.print("WHYYYYY?");
                    newCon.setTotalString();
                    addContact(newCon);

                }
                catch(Exception e)
                {
                    Toast.makeText(getApplicationContext(), "Error adding contact!", Toast.LENGTH_LONG).show();
                }
            }
        }

        if(requestCode == ActivityUtils.CONTACT) {
            if (resultCode == ActivityUtils.CONTACT_RETURN) {

                if(ContactUtil.contact.transactions.size() == 0)
                {
                    contacts.get(contactPagePosition).total = "You and " +
                            contacts.get(contactPagePosition).name
                            + " don't owe each other anything!";
                    contacts.get(contactPagePosition).transactions.add(
                            new Transaction(TransactionType.FROM, 0, "0/0/0", "Reason Unspecific")
                    );
                    customListViewAdapter.notifyDataSetChanged();
                    SaveHandler saver = new SaveHandler(contacts, getApplicationContext());
                    saver.save();
                    return;
                }

                removeContact(contactPagePosition);
                try {
                    Log.d("abc", ContactUtil.contact.name);
                    Log.d("abc", ContactUtil.contact.total);
                    Log.d("abc", ContactUtil.contact.date);
                    addContact(ContactUtil.contact);
                }catch(Exception e) {
                    Log.d("abc", "ERROR: " + e.toString());
                }
                SaveHandler saver = new SaveHandler(contacts, getApplicationContext());
                saver.save();
                ContactUtil.contact = null;
            }
        }



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

}
