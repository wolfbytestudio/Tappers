package org.tappers.ui.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.tappers.MainActivity;
import org.tappers.R;

import org.tappers.ui.data.Character;
import org.tappers.ui.data.CharacterBackground;
import org.tappers.contact.Contact;
import org.tappers.ui.page.ContactPage;

import org.tappers.Contacts;
import org.tappers.util.ActivityConstants;
import org.tappers.util.CustomTypeFaces;

/**
 * Created by Zack on 13/12/2015.
 */
public class MainListAdapter extends BaseAdapter
{

    private Context context;

    private static LayoutInflater inflater = null;

    private MainActivity owner;

    public MainListAdapter(Context context, MainActivity owner)
    {
        this.context = context;
        this.owner = owner;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    @Override
    public int getCount()
    {
        return Contacts.SINGLETON.getContacts().size();
    }

    @Override
    public Object getItem(int position)
    {
        return position;
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {

        View view = convertView;

        if(convertView == null)
        {
            view = inflater.inflate(R.layout.list_row, null);
        }

        TextView contactName = (TextView) view.findViewById(R.id.name);
        TextView payment = (TextView) view.findViewById(R.id.payment);
        TextView date = (TextView) view.findViewById(R.id.date);


        contactName.setTypeface(CustomTypeFaces.get("light"));
        payment.setTypeface(CustomTypeFaces.get("light"));
        date.setTypeface(CustomTypeFaces.get("regular"));

        Contact myContact = Contacts.SINGLETON.getContacts().get(position);

        contactName.setText(myContact.getName());

        payment.setText(myContact.getTotalString());

        ImageView character = (ImageView) view.findViewById(R.id.characterImage);

        Character person = Character.getCharacterForName(myContact.getCharacterType());

        character.setImageResource(person.getCharacterSmallFile());

        date.setText(myContact.getDate());

        CharacterBackground charBackground =
                CharacterBackground.getBackgroundForId(myContact.getBackgroundColour());

        character.setBackgroundResource(charBackground.getSmallBackground());


        ImageView deleteContact = (ImageView) view.findViewById(R.id.btnDeleteContact);

        view.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                int mPos = position;
                Intent intent = new Intent(view.getContext(), ContactPage.class);
                intent.putExtra("pos", mPos);
                owner.startActivityForResult(intent, ActivityConstants.CONTACT_PAGE);

            }
        });

        deleteContact.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        switch (which)
                        {
                            case DialogInterface.BUTTON_POSITIVE:
                                Contacts.SINGLETON.getContacts().remove(position);
                                Contacts.SINGLETON.save(context);
                                owner.updateContactCount();
                                notifyDataSetChanged();
                                owner.txtTotalOwe.setText(Contacts.SINGLETON.getTotal());
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(owner);
                builder.setMessage("Are you sure you want to delete the contact '"
                        + Contacts.SINGLETON.getContacts().get(position).getName() + "'?")
                        .setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();

            }
        });

        return view;
    }
}
