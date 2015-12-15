package com.example.zack.tapperstesting;

import android.content.Context;
import android.graphics.Typeface;
import android.media.Image;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by 30008095 on 15/12/2015.
 */
public class TransactionListAdapter extends BaseAdapter {


    private Context context;
    private Contact contact;
    private static LayoutInflater inflater = null;
    private HashMap<String, Typeface> fonts;

    public TransactionListAdapter(Context context, Contact contact
            , HashMap<String, Typeface> fonts)
    {
        this.fonts = fonts;
        this.context = context;
        this.contact = contact;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return contact.transactions.size();
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

        TextView lblContactName = (TextView) view.findViewById(R.id.lblPaymentName);
        ImageView imgTransactionType = (ImageView) view.findViewById(R.id.imgTransactionType);
        TextView lblReason = (TextView) view.findViewById(R.id.lblPaymentReason);
        TextView lblTime = (TextView) view.findViewById(R.id.lblPaymentDate);


        lblContactName.setTypeface(fonts.get("light"));
        lblReason.setTypeface(fonts.get("light"));
        lblTime.setTypeface(fonts.get("regular"));

        Transaction transaction = contact.transactions.get(i);
        lblContactName.setText(getPayment(transaction, contact.name));

        if(transaction.getType() == TransactionType.FROM)
        {
            imgTransactionType.setImageDrawable(view.getResources().getDrawable(R.drawable.from_icon));
        }
        else
        {
            imgTransactionType.setImageDrawable(view.getResources().getDrawable(R.drawable.to_icon));
        }


        lblReason.setText(transaction.getReason());

        return view;
    }

    private String getPayment(Transaction t, String name)
    {
        String r = "";

        NumberFormat formatter = NumberFormat.getCurrencyInstance();

        if(t.getType() == TransactionType.FROM)
        {
            r = "You Borrowed " + formatter.format(Math.abs(t.getAmount())) + " from " + name;
        }
        else
        {
            r = "You Lent " + formatter.format(Math.abs(t.getAmount())) + " to " + name + ".";
        }

        return r;
    }
}
