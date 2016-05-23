package org.tappers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.tappers.ui.adapter.MainListAdapter;
import org.tappers.contact.Contact;
import org.tappers.ui.page.ContactPage;
import org.tappers.ui.page.NewContact;
import org.tappers.contact.Transaction;
import org.tappers.contact.TransactionType;
import org.tappers.util.ActivityConstants;
import org.tappers.util.CustomTypeFaces;

import java.util.ArrayList;

/**
 * Main Activity
 */
public class MainActivity extends Activity
{

    /**
     * The list view
     */
    private ListView listView;

    /**
     * Custom view adapter for custom view controls
     */
    private MainListAdapter customListViewAdapter;

    /**
     * Displays all the contacts
     */
    private TextView txtContactCount;

    /**
     * Updates the character count
     */
    public void updateContactCount()
    {
        txtContactCount.setText(Contacts.SINGLETON.getContacts().size() + "");
    }


    public TextView txtTotalOwe;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        CustomTypeFaces.initialize(this);

        setContentView(R.layout.activity_main);

        try
        {
            Contacts.SINGLETON.load(this);
        }
        catch(Exception e)
        {
            Log.d("abc", "Error loading");
        }


        txtTotalOwe = (TextView) findViewById(R.id.txtTotalOwe);
        txtContactCount = (TextView) findViewById(R.id.contactCount);

        updateContactCount();
        txtTotalOwe.setText(Contacts.SINGLETON.getTotal());

        txtTotalOwe.setTypeface(CustomTypeFaces.get("light"));
        txtContactCount.setTypeface(CustomTypeFaces.get("light"));

        TextView title = (TextView) findViewById(R.id.txtTitle);
        title.setTypeface(CustomTypeFaces.get("light"));


        listView = (ListView) findViewById(R.id.lstContacts);
        registerForContextMenu(txtTotalOwe);
        listView.setOnLongClickListener(new View.OnLongClickListener()
        {
            @Override
            public boolean onLongClick(View v)
            {
                return false;
            }
        });


        customListViewAdapter = new MainListAdapter(getApplicationContext(), this);

        listView.setAdapter(customListViewAdapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {

                int mPos = position;
                Intent intent = new Intent(view.getContext(), ContactPage.class);
                intent.putExtra("pos", mPos);
                startActivityForResult(intent, ActivityConstants.CONTACT_PAGE);
            }
        });

        ImageView img = (ImageView) findViewById(R.id.newContact);


        img.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(v.getContext(), NewContact.class);
                ArrayList<String> contactNames = new ArrayList<String>();
                for (Contact c : Contacts.SINGLETON.getContacts())
                {
                    contactNames.add(c.getName());
                }
                intent.putStringArrayListExtra("contacts", contactNames);
                startActivityForResult(intent, ActivityConstants.NEW_CONTACT);
            }
        });




    }

    
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId() == R.id.txtTotalOwe)
        {
            menu.add(0, v.getId(), 0, "Something");
            menu.add(0, v.getId(), 0, "Something else");
            menu.add(0, v.getId(), 0, "Something else");
            menu.add(0, v.getId(), 0, "Something else");
            menu.add(0, v.getId(), 0, "Something else");
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
        switch(item.getItemId()) {
            case R.id.edit_contact:
                // add stuff here
                return true;
            case R.id.edit_delete:
                // edit stuff here
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }





    /**
     * Adds a new Contact to the list and repopulates the list view
     * @param contact - the contact being added
     */
    public void addContact(Contact contact)
    {
        Contacts.SINGLETON.getContacts().add(0, contact);

        Contacts.SINGLETON.save(this);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(requestCode == ActivityConstants.NEW_CONTACT)
        {
            if(resultCode == ActivityConstants.NEW_CONTACT_RETURN)
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

                    Contact newCon = new Contact(name, date, charString, backgroundCol);

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

                    addContact(newCon);
                    updateContactCount();
                }
                catch(Exception e)
                {
                    Toast.makeText(getApplicationContext(), "Error adding contact!", Toast.LENGTH_LONG).show();
                }
            }
        }

        if(requestCode == ActivityConstants.CONTACT_PAGE)
        {
            if (resultCode == ActivityConstants.CONTACT_PAGE_RETURN)
            {

                for(Contact conn : Contacts.SINGLETON.getContacts())
                {
                    if(conn.getTransactions().size() == 0)
                    {
                        conn.getTransactions().add(
                                new Transaction(TransactionType.FROM, 0, "0/0/0", "Reason Unspecific")
                        );
                        customListViewAdapter.notifyDataSetChanged();
                    }
                    else
                    {
                        customListViewAdapter.notifyDataSetChanged();
                    }

                }
                Contacts.SINGLETON.save(this);
            }
        }

        txtTotalOwe.setText(Contacts.SINGLETON.getTotal());

    }

    /**
     * A MEGABYTE
     */
    public static final boolean MEGABYTE = false;

}
