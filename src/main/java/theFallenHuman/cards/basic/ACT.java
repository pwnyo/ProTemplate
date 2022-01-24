package theFallenHuman.cards.basic;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFallenHuman.cards.AbstractEasyCard;

import static theFallenHuman.UndertaleMod.makeID;

public class ACT extends AbstractEasyCard {
    public final static String ID = makeID(ACT.class.getSimpleName());

    public ACT() {
        super(ID, 1, CardType.SKILL, CardRarity.BASIC, CardTarget.ENEMY);
        baseBlock = block = 4;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
    }

    public void upp() {
        upgradeBlock(3);
    }
}