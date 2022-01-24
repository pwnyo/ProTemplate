package theFallenHuman.relics;

import theFallenHuman.TheFallenHuman;

import static theFallenHuman.UndertaleMod.makeID;

public class TodoItem extends AbstractEasyRelic {
    public static final String ID = makeID("TodoItem");

    public TodoItem() {
        super(ID, RelicTier.STARTER, LandingSound.FLAT, TheFallenHuman.Enums.UT_BLACK);
    }
}
