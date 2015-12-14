package com.example.zack.tapperstesting;

import android.animation.Animator;
import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends Activity {

    ArrayList<Contact> contacts = new ArrayList<>();

    private ListView listView;

    private CustomListViewAdapter customListViewAdapter;

    ArrayList<HashMap<String, String>> contactList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        TextView title = (TextView) findViewById(R.id.txtTitle);

        Typeface thin = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Thin.ttf");
        Typeface light = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Light.ttf");
        Typeface regular = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");

        HashMap<String, Typeface> typeFaces = new HashMap<>();

        typeFaces.put("thin", thin);
        typeFaces.put("light", light);
        typeFaces.put("regular", regular);

        title.setTypeface(light);

        contacts.add(new Contact("John Smith", "John Smith owes you a total of £65.03", "12/12/2015"));
        contacts.add(new Contact("Bank Loans", "You owe Bank Loans a total of £6250.00", "1/12/2015"));
        contacts.add(new Contact("Lauren Smith", "Lauren Smith owes you a total of £5.42", "27/11/2015"));
        contacts.add(new Contact("Jack Smith", "You and Jack Smith don't owe each other anything", "26/11/2015"));
        contacts.add(new Contact("Jordan Smith", "Jordan Smith owes you a total of £30.00", "20/11/2015"));
        contacts.add(new Contact("Company Loans", "You and Company Loans don't owe each other anything", "12/11/2015"));


        for(int i = 0; i < contacts.size(); i++)
        {
            HashMap<String, String> data = new HashMap<>();
            data.put("name", contacts.get(i).name);
            data.put("payment", contacts.get(i).payment);
            data.put("date", contacts.get(i).date);
            contactList.add(data);
        }

        listView = (ListView) findViewById(R.id.lstContacts);

        customListViewAdapter = new CustomListViewAdapter(getApplicationContext(), contactList, typeFaces);

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
