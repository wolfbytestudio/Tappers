package com.example.zack.tapperstesting.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zack.tapperstesting.MainActivity;
import com.example.zack.tapperstesting.R;
import com.example.zack.tapperstesting.contact.*;
import com.example.zack.tapperstesting.contact.Character;
import com.example.zack.tapperstesting.util.ActivityUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Zack on 13/12/2015.
 */
public class MainListAdapter extends BaseAdapter {

    private Context context;

    private ArrayList<Contact> contacts;

    private static LayoutInflater inflater = null;

    private HashMap<String, Typeface> fonts;

    private MainActivity owner;

    public MainListAdapter(Context context, ArrayList<Contact> contacts
            , HashMap<String, Typeface> fonts, MainActivity owner)
    {
        this.fonts = fonts;
        this.context = context;
        this.contacts = contacts;
        this.owner = owner;
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
    public View getView(final int position, View convertView, ViewGroup parent) {

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


        ImageView character = (ImageView) view.findViewById(R.id.characterImage);

        Character person = Character.getCharacterForName(myContact.characterType);

        character.setImageResource(person.getCharacterFile());

        date.setText(myContact.date);

        if(myContact.backgroundColour.equalsIgnoreCase("default"))
        {
            character.setBackgroundResource(R.drawable.bgcol_default);
        }
        if(myContact.backgroundColour.equalsIgnoreCase("blue"))
        {
            character.setBackgroundResource(R.drawable.bgcol_blue);
        }
        if(myContact.backgroundColour.equalsIgnoreCase("green"))
        {
            character.setBackgroundResource(R.drawable.bgcol_green);
        }
        if(myContact.backgroundColour.equalsIgnoreCase("turq"))
        {
            character.setBackgroundResource(R.drawable.bgcol_turq);
        }
        if(myContact.backgroundColour.equalsIgnoreCase("purple"))
        {
            character.setBackgroundResource(R.drawable.bgcol_purple);
        }
        if(myContact.backgroundColour.equalsIgnoreCase("pink"))
        {
            character.setBackgroundResource(R.drawable.bgcol_pink);
        }
        if(myContact.backgroundColour.equalsIgnoreCase("red"))
        {
            character.setBackgroundResource(R.drawable.bgcol_red);
        }
        if(myContact.backgroundColour.equalsIgnoreCase("orange"))
        {
            character.setBackgroundResource(R.drawable.bgcol_orange);
        }
        if(myContact.backgroundColour.equalsIgnoreCase("gold"))
        {
            character.setBackgroundResource(R.drawable.bgcol_gold);
        }
        if(myContact.backgroundColour.equalsIgnoreCase("yellow"))
        {
            character.setBackgroundResource(R.drawable.bgcol_yellow);
        }
        if(myContact.backgroundColour.equalsIgnoreCase("black"))
        {
            character.setBackgroundResource(R.drawable.bgcol_black);
        }

        ImageView deleteContact = (ImageView) view.findViewById(R.id.btnDeleteContact);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int mPos = position;
                Intent intent = new Intent(view.getContext(), ContactPage.class);

                intent.putExtra("name", contacts.get(position).name);

                Contact c = contacts.get(position);
                ContactUtil.contact = c;
                c.setTotalString();
                intent.putExtra("total", c.total);
                owner.contactPagePosition = position;
                owner.startActivityForResult(intent, ActivityUtils.CONTACT);

            }
        });


        deleteContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                contacts.remove(position);
                                owner.save.save();
                                notifyDataSetChanged();
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(owner);
                builder.setMessage("Are you sure you want to delete the contact '" + contacts.get(position).name + "'?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();

            }
        });

        return view;
    }

}
