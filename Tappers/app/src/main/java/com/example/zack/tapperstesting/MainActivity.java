package com.example.zack.tapperstesting;

import android.animation.Animator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends Activity {

    /**
     * All the contacts
     */
    private ArrayList<Contact> contacts = new ArrayList<>();

    /**
     * The list view
     */
    private ListView listView;

    /**
     * Custom view adapter for custom view controls
     */
    private MainListAdapter customListViewAdapter;

    /**
     * Contains all the contacts
     */
    private ArrayList<HashMap<String, String>> contactList = new ArrayList<>();

    /**
     * The types of font
     */
    private HashMap<String, Typeface> typeFaces = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        TextView title = (TextView) findViewById(R.id.txtTitle);

        Typeface thin = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Thin.ttf");
        Typeface light = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
        Typeface regular = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");



        typeFaces.put("thin", thin);
        typeFaces.put("light", light);
        typeFaces.put("regular", regular);

        title.setTypeface(light);


        listView = (ListView) findViewById(R.id.lstContacts);

        customListViewAdapter = new MainListAdapter(getApplicationContext(), contactList, typeFaces);
        listView.setAdapter(customListViewAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int mPos = position;
                int cx = view.getWidth() / 2;
                int cy = view.getHeight() / 2;

                int finalRadius = Math.max(view.getWidth(), view.getHeight());
                Animator anim =
                        ViewAnimationUtils.createCircularReveal(view, cx, cy, 0, finalRadius);

                anim.setDuration(1500);
                view.setVisibility(View.VISIBLE);
                anim.start();

                String itemClickId = listView.getItemAtPosition(mPos).toString();

                Toast.makeText(getApplicationContext(), "Id Clicked: " + itemClickId, Toast.LENGTH_LONG).show();
            }
        });


        ImageView img = (ImageView) findViewById(R.id.newContact);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(v.getContext(), NewContact.class);

                ArrayList<String> contactNames = new ArrayList<String>();

                for(Contact c : contacts)
                {
                    contactNames.add(c.name);
                }

                intent.putStringArrayListExtra("contacts", contactNames);

                startActivityForResult(intent, 0);


            }
        });

    }

    /**
     * Adds a new Contact to the list and repopulates the list view
     * @param contact - the contact being added
     */
    public void addContact(Contact contact)
    {
        contacts.add(0, contact);

        HashMap<String, String> data = new HashMap<>();
        data.put("name", contacts.get(0).name);
        data.put("total", contacts.get(0).total);
        data.put("date", contacts.get(0).date);

        contactList.add(0, data);

        customListViewAdapter = new MainListAdapter(getApplicationContext(), contactList, typeFaces);
        listView.setAdapter(customListViewAdapter);
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

        if(requestCode == 0)
        {
            if(resultCode == 0)
            {
                try
                {
                    String name = data.getStringExtra("name");
                    String amount = data.getStringExtra("transaction");
                    String reason = data.getStringExtra("reason");
                    String date = data.getStringExtra("date");
                    String tofrom = data.getStringExtra("tofrom");

                    TransactionType type = TransactionType.valueOf(tofrom);

                    Contact newCon = new Contact(name, "", date);

                    double am = 1;
                    try
                    {
                        am = Double.parseDouble(amount);
                    }
                    catch(Exception e)
                    {

                    }

                    newCon.addTransaction(new Transaction(type,
                            am, date, reason));

                    newCon.setTotalString();


                    addContact(newCon);
                }
                catch(Exception e)
                {
                    Toast.makeText(getApplicationContext(), "Error adding contact!", Toast.LENGTH_LONG).show();
                }



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
