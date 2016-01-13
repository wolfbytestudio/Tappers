package org.tappers;

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

import org.tappers.adapter.MainListAdapter;
import org.tappers.contact.Contact;
import org.tappers.contact.ContactPage;
import org.tappers.contact.NewContact;
import org.tappers.transaction.Transaction;
import org.tappers.transaction.TransactionType;
import org.tappers.util.ActivityUtils;
import org.tappers.util.LoadHandler;
import org.tappers.util.SaveHandler;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends Activity {

    /**
     * All the contacts
     */
    public static ArrayList<Contact> contacts;

    public static int getPositionForContact(String name)
    {
        int counter = 0;
        for(Contact con : contacts)
        {
            if(con.name.equalsIgnoreCase(name))
            {
                return counter;
            }
            counter++;
        }
        return -1;
    }

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
     * Object for handling saving
     */
    public static SaveHandler save;

    /**
     * Displays all the contacts
     */
    private TextView txtContactCount;

    /**
     * Updates the character count
     */
    public void updateContactCount()
    {
        txtContactCount.setText(contacts.size() + "");
    }


    private TextView txtTotalOwe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contacts = new ArrayList<>();
        txtTotalOwe = (TextView) findViewById(R.id.txtTotalOwe);
        txtContactCount = (TextView) findViewById(R.id.contactCount);

        LoadHandler load = new LoadHandler(getApplicationContext());
        load.load();

        contacts = load.getContacts();
        //contacts.clear();
        save = new SaveHandler(getApplicationContext());
        //save.save();

        updateContactCount();
        generateTotal();

        Typeface thin = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Thin.ttf");
        Typeface light = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
        Typeface regular = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");




        txtTotalOwe.setTypeface(light);

        txtContactCount.setTypeface(light);



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
                c.setTotalString();

                intent.putExtra("total", c.total);
                intent.putExtra("pos", mPos);

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


    public void generateTotal()
    {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        if(contacts.size() == 0)
        {
            txtTotalOwe.setText("Add a new contact by clicking the top right button");
            return;
        }
        double total = 0;
        for(Contact c : contacts)
        {
            for(Transaction t : c.transactions)
            {
                if(t.getType() == TransactionType.FROM)
                {
                    total -= t.getAmount();
                }
                else
                {
                    total += t.getAmount();
                }
            }
        }

        if(total < 0)
        {
            txtTotalOwe.setText("You owe a total of " + formatter.format(Math.abs(total)));
        }
        else if(total > 0)
        {
            txtTotalOwe.setText("You are owed a total of " + formatter.format(Math.abs(total)));
        }
        else
        {
            txtTotalOwe.setText("Nobody owes anyone anything!");
        }
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
                    String charString = data.getStringExtra("character");
                    String backgroundCol = data.getStringExtra("bgcol");
                    String tofrom = data.getStringExtra("tofrom");

                    TransactionType type = TransactionType.valueOf(tofrom);

                    Contact newCon = new Contact(name, "", date, charString, backgroundCol);

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
                    updateContactCount();
                }
                catch(Exception e)
                {
                    Toast.makeText(getApplicationContext(), "Error adding contact!", Toast.LENGTH_LONG).show();
                }
            }
        }

        if(requestCode == ActivityUtils.CONTACT) {
            if (resultCode == ActivityUtils.CONTACT_RETURN) {

                for(Contact conn : contacts)
                {
                    if(conn.transactions.size() == 0)
                    {
                        conn.total = "You and " +
                                conn.name
                                + " don't owe each other anything!";
                        conn.transactions.add(
                                new Transaction(TransactionType.FROM, 0, "0/0/0", "Reason Unspecific")
                        );
                        customListViewAdapter.notifyDataSetChanged();
                    }
                    else
                    {
                        conn.setTotalString();
                        customListViewAdapter.notifyDataSetChanged();
                    }

                }


                SaveHandler saver = new SaveHandler(getApplicationContext());
                saver.save();
            }
        }

        generateTotal();

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
