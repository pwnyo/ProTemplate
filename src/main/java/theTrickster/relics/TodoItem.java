package theTrickster.relics;

import theTrickster.TheTrickster;

import static theTrickster.TheTricksterMod.makeID;

public class TodoItem extends AbstractEasyRelic {
    public static final String ID = makeID("TodoItem");

    public TodoItem() {
        super(ID, RelicTier.STARTER, LandingSound.FLAT, TheTrickster.Enums.TWILIGHT_BLACK);
    }
}
