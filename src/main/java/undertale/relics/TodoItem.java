package undertale.relics;

    import undertale.TheFallenHuman;

import static undertale.Undertale.makeID;

public class TodoItem extends AbstractEasyRelic {
    public static final String ID = makeID("TodoItem");

    public TodoItem() {
        super(ID, RelicTier.STARTER, LandingSound.FLAT,TheFallenHuman.Enums.FALLENHUMAN_UT_BLACK);
    }
}
