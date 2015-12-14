package com.example.zack.tapperstesting;

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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Zack on 13/12/2015.
 */
public class CustomListViewAdapter extends BaseAdapter {



    private Context context;
    private ArrayList<HashMap<String, String>> contact;
    private static LayoutInflater inflater = null;
    private HashMap<String, Typeface> fonts;

    public CustomListViewAdapter(Context context, ArrayList<HashMap<String, String>> contact
            , HashMap<String, Typeface> fonts)
    {
        this.fonts = fonts;
        this.context = context;
        this.contact = contact;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return contact.size();
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

        HashMap<String, String> myContact = new HashMap<>();
        myContact = contact.get(position);

        contactName.setText(myContact.get("name"));
        payment.setText(myContact.get("payment"));
        date.setText(myContact.get("date"));



        return view;
    }
}
