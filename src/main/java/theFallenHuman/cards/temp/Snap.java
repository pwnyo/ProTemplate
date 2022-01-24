package theFallenHuman.cards.temp;

import com.megacrit.cardcrawl.actions.defect.EvokeOrbAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFallenHuman.cards.AbstractEasyCard;

import static theFallenHuman.UndertaleMod.makeID;

public class Snap extends AbstractEasyCard {
    public final static String ID = makeID(Snap.class.getSimpleName());

    public Snap() {
        super(ID, 1, CardType.SKILL, CardRarity.SPECIAL, CardTarget.SELF, CardColor.COLORLESS);
        selfRetain = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new EvokeOrbAction(1));
    }

    public void upp() {
        upgradeBaseCost(0);
    }
}