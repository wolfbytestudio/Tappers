package org.tappers.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.tappers.MainActivity;
import org.tappers.R;
import org.tappers.contact.Contact;
import org.tappers.contact.ContactPage;
import org.tappers.transaction.Transaction;
import org.tappers.transaction.TransactionType;

import java.text.NumberFormat;
import java.util.HashMap;

/**
 * Created by 30008095 on 15/12/2015.
 */
public class TransactionListAdapter extends BaseAdapter {


    private Context context;
    private Contact contact;
    private ContactPage owner;
    private static LayoutInflater inflater = null;
    private HashMap<String, Typeface> fonts;

    public TransactionListAdapter(Context context, Contact contact
            , HashMap<String, Typeface> fonts, ContactPage owner)
    {
        this.fonts = fonts;
        this.context = context;
        this.contact = contact;
        this.owner = owner;
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
    public View getView(final int i, View convertView, ViewGroup viewGroup) {
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


        if(transaction.getReason().equals(""))
        {
            lblReason.setText("Reason unspecific");
        }
        else
        {
            lblReason.setText(transaction.getReason());
        }

        lblTime.setText(transaction.getDate());


        ImageButton btnDeleteHistoryItem = (ImageButton)view.findViewById(R.id.btnDeleteHistoryItem);

        btnDeleteHistoryItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                contact.transactions.remove(i);
                                owner.updateContactList();
                                contact.setTotalString();
                                owner.txtTotal.setText(contact.total);
                                MainActivity.save.save();
                                notifyDataSetChanged();
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(owner);
                builder.setMessage("Are you sure you want to delete this transaction?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
            }
        });

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
