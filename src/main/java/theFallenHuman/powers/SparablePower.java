package theFallenHuman.powers;

import basemod.helpers.ModalChoice;
import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.InvisiblePower;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.HideHealthBarAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theFallenHuman.UndertaleMod;
import theFallenHuman.actions.SpareAction;
import theFallenHuman.patches.MonsterDialogPatch;
import theFallenHuman.util.TexLoader;

import static theFallenHuman.UndertaleMod.makePowerPath;

public class SparablePower extends AbstractPower implements CloneablePowerInterface, InvisiblePower, ModalChoice.Callback {
    public static final String POWER_ID = UndertaleMod.makeID("SparablePower");
    protected static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    protected static final Texture tex84 = TexLoader.getTexture(makePowerPath("placeholder_power84.png"));
    protected static final Texture tex32 = TexLoader.getTexture(makePowerPath("placeholder_power32.png"));
    protected int timesActed;
    protected int timesActedGood;
    protected int timesActedBad;
    protected int timesSpared;
    protected int extraCheckCount = 0;
    protected int extraCheckStartIndex = -1;
    protected ModalChoice modal;

    public SparablePower(AbstractCreature owner) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        this.updateDescription();
    }
    public SparablePower(AbstractCreature owner, int extraCheckCount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;

        this.extraCheckCount = extraCheckCount;
        this.extraCheckStartIndex = 0;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        this.updateDescription();
    }
    public SparablePower(AbstractCreature owner, int extraCheckCount, int extraCheckStartIndex) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.type = PowerType.BUFF;

        this.extraCheckCount = extraCheckCount;
        this.extraCheckStartIndex = extraCheckStartIndex;

        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        this.updateDescription();
    }

    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }


    public void onAct() {
        onAct(1);
    }
    public void onAct(int count) {
        if (!(owner instanceof AbstractMonster)) {
            return;
        }
        timesActed += count;
    }
    public void onAct(boolean isGood) {
        onAct(1, isGood);
    }
    public void onAct(int count, boolean isGood) {
        if (!(owner instanceof AbstractMonster)) {
            return;
        }
        timesActed += count;
        if (isGood) {
            timesActedGood += count;
        }
        else {
            timesActedBad += count;
        }
    }
    public void onSpare() {
        onSpare(1);
    }
    public void onSpare(int count) {
        if (!(owner instanceof AbstractMonster)) {
            return;
        }
        timesSpared += count;
    }

    public void check(int index) {
        if (index >= 0)
            addToBot(new TalkAction(AbstractDungeon.player, getDialogAt(index)));
    }
    public void checkRandom() {
        if (extraCheckStartIndex > 0) {
            int temp = MathUtils.random(extraCheckStartIndex, extraCheckStartIndex + extraCheckCount - 1);
            check(temp);
        }
    }

    public void talk(int index) {
        if (index >= 0)
            addToBot(new TalkAction(owner, getMovesAt(index)));
    }
    public void yellowify()
    {
        addToBot(new ApplyPowerAction(owner, owner, new YellowPower(owner)));
    }

    public void spare() {
        addToBot(new HideHealthBarAction(owner));
        addToBot(new SpareAction((AbstractMonster)owner));
        talk(0);

        checkCombat();
    }
    public void checkCombat() {
        boolean allMonstersSpared = true;
        for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (m.currentHealth > 0) {
                allMonstersSpared = false;
            }
        }

        if (allMonstersSpared) {
            // Prevent softlocks
            AbstractDungeon.getCurrRoom().cannotLose = false;
        }
    }

    @Override
    public void optionSelected(AbstractPlayer p, AbstractMonster m, int i) {

    }
    protected String getDialogAt(int index) {
        if (index < 0) {
            return "DIDN'T FIND A STRING (CHECK)";
        }
        return MonsterDialogPatch.BonusDialog.DIALOG.get(owner)[index];
    }
    protected String getMovesAt(int index) {
        if (index < 0) {
            return "DIDN'T FIND A STRING (REACTION)";
        }
        return MonsterDialogPatch.BonusDialog.MOVES.get(owner)[index];
    }

    @Override
    public AbstractPower makeCopy() {
        return new SparablePower(owner);
    }
}