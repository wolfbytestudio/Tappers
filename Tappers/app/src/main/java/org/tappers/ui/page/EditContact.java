package org.tappers.ui.page;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.tappers.Contacts;
import org.tappers.R;
import org.tappers.contact.Contact;
import org.tappers.ui.data.CharacterBackground;
import org.tappers.util.ActivityConstants;

public class EditContact extends Activity
{

    private Contact contact;

    private ImageButton lastCharSelected;

    private String backgroundSelected = "default";
    private String characterSelected = "default male";

    private ImageButton charMale;
    private ImageButton charFemale;
    private ImageButton charStonerBob;
    private ImageButton charGothGirl;
    private ImageButton charStonerBobAlt;
    private ImageButton charAbudady;
    private ImageButton charAbiba;
    private ImageButton charGamerBob;

    private ImageButton charBlueDress;
    private ImageButton charRedDress;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        contact = Contacts.SINGLETON.getContacts().get(getIntent().getIntExtra("pos", -1));

        charMale = (ImageButton) findViewById(R.id.char_default_male_edit);
        charFemale = (ImageButton) findViewById(R.id.char_default_female_edit);
        charStonerBob = (ImageButton) findViewById(R.id.char_stoner_bob_edit);
        charGothGirl = (ImageButton) findViewById(R.id.char_goth_girl_edit);
        charStonerBobAlt = (ImageButton) findViewById(R.id.char_stoner_bob_alt_edit);
        charAbudady = (ImageButton) findViewById(R.id.char_abudady_edit);
        charAbiba = (ImageButton) findViewById(R.id.char_abiba_edit);
        charGamerBob = (ImageButton) findViewById(R.id.char_gamer_bob_edit);

        charRedDress = (ImageButton) findViewById(R.id.char_red_dress_edit);
        charBlueDress = (ImageButton) findViewById(R.id.char_blue_dress_edit);

        getCharacter().setBackgroundResource(CharacterBackground.getBackgroundForId(contact.getBackgroundColour()).getSmallBackground());
        backgroundSelected = contact.getBackgroundColour();
        characterSelected = contact.getCharacterType();


        final ImageButton bgDefault = (ImageButton) findViewById(R.id.bgcol_default_edit);
        final ImageButton bgBlue = (ImageButton) findViewById(R.id.bgcol_blue_edit);
        final ImageButton bgGreen = (ImageButton) findViewById(R.id.bgcol_green_edit);
        final ImageButton bgTurq = (ImageButton) findViewById(R.id.bgcol_turq_edit);
        final ImageButton bgPurple = (ImageButton) findViewById(R.id.bgcol_purple_edit);
        final ImageButton bgPink = (ImageButton) findViewById(R.id.bgcol_pink_edit);
        final ImageButton bgRed = (ImageButton) findViewById(R.id.bgcol_red_edit);
        final ImageButton bgOrange = (ImageButton) findViewById(R.id.bgcol_orange_edit);
        final ImageButton bgGold = (ImageButton) findViewById(R.id.bgcol_gold_edit);
        final ImageButton bgYellow = (ImageButton) findViewById(R.id.bgcol_yellow_edit);
        final ImageButton bgBlack = (ImageButton) findViewById(R.id.bgcol_black_edit);

        bgDefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {selectBackground("default");
            }
        });
        bgBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {selectBackground("blue");
            }
        });
        bgGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {selectBackground("green");
            }
        });
        bgTurq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {selectBackground("turq");
            }
        });
        bgPurple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {selectBackground("purple");
            }
        });
        bgPink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {selectBackground("pink");
            }
        });
        bgRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {selectBackground("red");
            }
        });
        bgOrange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {selectBackground("orange");
            }
        });
        bgGold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectBackground("gold");
            }
        });
        bgYellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectBackground("yellow");
            }
        });
        bgBlack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectBackground("black");
            }
        });


        charMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectCharacter(charMale, "default male");
            }
        });
        charFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectCharacter(charFemale, "default female");
            }
        });
        charStonerBob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectCharacter(charStonerBob, "stoner bob");
            }
        });
        charGothGirl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectCharacter(charGothGirl, "goth girl");
            }
        });

        charStonerBobAlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectCharacter(charStonerBobAlt, "stoner bob alt");
            }
        });

        charAbudady.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectCharacter(charAbudady, "abudady");
            }
        });

        charAbiba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectCharacter(charAbiba, "abiba");
            }
        });

        charGamerBob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectCharacter(charGamerBob, "gamer bob");
            }
        });

        charRedDress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectCharacter(charRedDress, "red dress female");
            }
        });

        charBlueDress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectCharacter(charBlueDress, "blue dress female");
            }
        });


        final TextView txtContact = (TextView) findViewById(R.id.txtEditConactName);

        txtContact.setText(contact.getName());

        Button update = (Button) findViewById(R.id.cmdConfirmEdit);
        final Intent returnIntent = getIntent();

        update.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                setResult(ActivityConstants.EDIT_CONTACT_RETURN, returnIntent);
                contact.setName(txtContact.getText().toString());
                contact.setBackgroundColour(backgroundSelected);
                contact.setCharacterType(characterSelected);
                finish();
            }
        });

        ImageButton back = (ImageButton) findViewById(R.id.btnBackEditContact);


        back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                setResult(-1, returnIntent);
                finish();
            }
        });

        setCharacterSelected();
    }


    private void selectBackground(String id)
    {
        backgroundSelected = id;
        CharacterBackground bg = CharacterBackground.getBackgroundForId(backgroundSelected);
        lastCharSelected.setBackgroundResource(bg.getSmallBackground());
    }

    private void resetBackgrounds()
    {
        charMale.setBackground(null);
        charFemale.setBackground(null);
        charStonerBob.setBackground(null);
        charGothGirl.setBackground(null);
        charStonerBobAlt.setBackground(null);
        charAbudady.setBackground(null);
        charAbiba.setBackground(null);
        charGamerBob.setBackground(null);
        charBlueDress.setBackground(null);
        charRedDress.setBackground(null);
    }

    private void selectCharacter(ImageButton character, String identifier)
    {
        resetBackgrounds();
        characterSelected = identifier;
        CharacterBackground bg = CharacterBackground.getBackgroundForId(backgroundSelected);
        character.setBackgroundResource(bg.getSmallBackground());
        lastCharSelected = character;
    }

    /**
     * Gets the imagebutton for character
     * @return
     */
    public ImageButton getCharacter()
    {
        if(contact.getCharacterType().equalsIgnoreCase("default male"))
        {
            return charMale;
        }
        if(contact.getCharacterType().equalsIgnoreCase("default female"))
        {
            return charFemale;
        }
        if(contact.getCharacterType().equalsIgnoreCase("stoner bob"))
        {
            return charStonerBob;
        }
        if(contact.getCharacterType().equalsIgnoreCase("stoner bob alt"))
        {
            return charStonerBobAlt;
        }
        if(contact.getCharacterType().equalsIgnoreCase("goth girl"))
        {
            return charGothGirl;
        }
        if(contact.getCharacterType().equalsIgnoreCase("abiba"))
        {
            return charAbiba;
        }
        if(contact.getCharacterType().equalsIgnoreCase("blue dress female"))
        {
            return charBlueDress;
        }
        if(contact.getCharacterType().equalsIgnoreCase("red dress female"))
        {
            return charRedDress;
        }
        if(contact.getCharacterType().equalsIgnoreCase("abudady"))
        {
            return charAbudady;
        }
        return null;
    }

    public void setCharacterSelected()
    {
        selectCharacter(getCharacter(), contact.getCharacterType());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_edit_contact, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
