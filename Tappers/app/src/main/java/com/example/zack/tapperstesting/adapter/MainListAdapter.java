package com.example.zack.tapperstesting.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zack.tapperstesting.R;
import com.example.zack.tapperstesting.contact.Contact;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Zack on 13/12/2015.
 */
public class MainListAdapter extends BaseAdapter {

    private Context context;

    private ArrayList<Contact> contacts;

    private static LayoutInflater inflater = null;

    private HashMap<String, Typeface> fonts;

    public MainListAdapter(Context context, ArrayList<Contact> contacts
            , HashMap<String, Typeface> fonts)
    {
        this.fonts = fonts;
        this.context = context;
        this.contacts = contacts;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return contacts.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if(convertView == null) {
            view = inflater.inflate(R.layout.list_row, null);
        }

        TextView contactName = (TextView) view.findViewById(R.id.name);
        TextView payment = (TextView) view.findViewById(R.id.payment);
        TextView date = (TextView) view.findViewById(R.id.date);


        contactName.setTypeface(fonts.get("light"));
        payment.setTypeface(fonts.get("light"));
        date.setTypeface(fonts.get("regular"));

        Contact myContact = contacts.get(position);

        contactName.setText(myContact.name);

        try
        {
            double a = Double.parseDouble(myContact.total);
            DecimalFormat precision = new DecimalFormat("0.00");
            payment.setText(precision.format(a));
        }
        catch(Exception e)
        {
            payment.setText(myContact.total);
        }

        date.setText(myContact.date);
        return view;
    }
}
