package org.tappers.ui.data;

import org.tappers.R;

/**
 * Created by Zack on 20/12/2015.
 */
public enum Character
{

    DEFAULT_MALE("default male", R.drawable.male, R.drawable.male_small, R.id.char_default_male_edit),
    DEFAULT_FEMALE("default female", R.drawable.female, R.drawable.female_small, R.id.char_default_female_edit),
    STONER_BOB("stoner bob", R.drawable.stoner_bob, R.drawable.stoner_bob_small, R.id.char_stoner_bob_edit),
    STONER_BOB_ALT("stoner bob alt", R.drawable.stoner_bob_alt, R.drawable.stoner_bob_alt_small, R.id.char_stoner_bob_alt_edit),
    GOTH_GIRL("goth girl", R.drawable.goth_girl, R.drawable.goth_girl_small, R.id.char_goth_girl_edit),
    ABIBA("abiba", R.drawable.abiba, R.drawable.abiba_small, R.id.char_abiba_edit),
    GAMER_BOB("gamer bob", R.drawable.gamer_bob, R.drawable.gamer_bob_small, R.id.char_gamer_bob_edit),

    BLUE_DRESS_FEMALE("blue dress female", R.drawable.blue_dress_female, R.drawable.blue_dress_female_small, R.id.char_blue_dress_edit),
    RED_DRESS_FEMALE("red dress female", R.drawable.red_dress_female, R.drawable.red_dress_female_small, R.id.char_red_dress_edit),

    ABUDADY("abudady", R.drawable.abudady, R.drawable.abudady_small, R.id.char_abudady_edit);

    private String identifier;
    private int characterFile;
    private int characterFileSmall;
    private int characterIdEdits;

    Character(String identifier, int characterFile, int characterFileSmall, int characerIdEdits)
    {
        this.setIdentifier(identifier);
        this.setCharacterFile(characterFile);
        this.setCharacterSmallFile(characterFileSmall);
    }


    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public int getCharacterFile() {
        return characterFile;
    }

    public void setCharacterFile(int characterFile) {
        this.characterFile = characterFile;
    }

    public int getCharacterSmallFile() {
        return characterFileSmall;
    }

    public void setCharacterSmallFile(int characterFile) {
        this.characterFileSmall = characterFile;
    }

    public static Character getCharacterForName(String name)
    {
        for(Character character : values())
        {
            if(character.identifier.equalsIgnoreCase(name))
            {
                return character;
            }
        }
        return null;
    }

    public static Character getCharacterForId(int id)
    {
        for(Character character : values())
        {
            if(character.getCharacterIdEdits() == id)
            {
                return character;
            }
        }
        return null;
    }

    public int getCharacterIdEdits() {
        return characterIdEdits;
    }

    public void setCharacterIdEdits(int characterIdEdits) {
        this.characterIdEdits = characterIdEdits;
    }
}
