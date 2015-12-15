package com.example.zack.tapperstesting;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by 30008095 on 15/12/2015.
 */
public class TransactionListAdapter extends BaseAdapter {


    private Context context;
    private ArrayList<HashMap<String, Transaction>> transaction;
    private static LayoutInflater inflater = null;
    private HashMap<String, Typeface> fonts;


    public TransactionListAdapter(Context context, ArrayList<HashMap<String, Transaction>> transaction
            , HashMap<String, Typeface> fonts)
    {
        this.fonts = fonts;
        this.context = context;
        this.transaction = transaction;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return transaction.size();
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
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View view = convertView;

        if(view == null) {
            view = inflater.inflate(R.layout.payment_row, null);
        }

        TextView lblContactName = (TextView) view.findViewById(R.id.lblContactName);

        lblContactName.setTypeface(fonts.get("light"));

        HashMap<String, Transaction> myContact = new HashMap<>();

        myContact = transaction.get(i);





        return view;
    }
}
