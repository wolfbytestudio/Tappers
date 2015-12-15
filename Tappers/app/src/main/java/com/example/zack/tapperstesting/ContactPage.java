package com.example.zack.tapperstesting;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ContactPage extends Activity {

    private Contact contact;

    private HashMap<String, Typeface> fonts;

    private ListView transactionList = null;

    private TextView txtTotal;

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

        txtTotal.setTypeface(fonts.get("light"));
        txtTotal.setText(getIntent().getStringExtra("total"));

        TextView lblTitle = (TextView) findViewById(R.id.contactPageTitle);
        TextView txtHistory = (TextView) findViewById(R.id.txtHistory);
        lblTitle.setTypeface(fonts.get("light"));
        lblTitle.setText(getIntent().getStringExtra("name").toString());
        txtHistory.setTypeface(fonts.get("light"));

        contact = ContactUtil.contact;

        ImageButton btnNewTrans = (ImageButton) findViewById(R.id.btnNewTransaction);

        btnNewTrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), NewTransaction.class);
                startActivityForResult(intent, 1);
            }
        });


        TransactionListAdapter transactionListAdapter= new TransactionListAdapter(getApplicationContext(), contact, fonts);
        transactionList.setAdapter(transactionListAdapter);
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
                TransactionListAdapter transactionListAdapter= new TransactionListAdapter(getApplicationContext(), contact, fonts);
                transactionList.setAdapter(transactionListAdapter);
                contact.setTotalString();
                txtTotal.setText(contact.total);
            }
        }
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
