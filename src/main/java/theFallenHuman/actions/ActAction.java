package theFallenHuman.actions;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theFallenHuman.powers.SparablePower;

import java.util.ArrayList;

public class ActAction extends AbstractGameAction {
    private AbstractMonster monster;

    public ActAction(AbstractMonster m) {
        this.monster = m;
    }

    @Override
    public void update() {
        for (AbstractPower p : monster.powers) {
            if (p instanceof SparablePower) {
                ((SparablePower) p).onSpare();
            }
        }
        switch (monster.name) {
            case ("Louse"):
                BaseMod.logger.info("this is a " + monster.name);
                break;
            case ("Cultist"):
                BaseMod.logger.info("this is a " + monster.name);
                break;
            case ("Jaw Worm"):
                BaseMod.logger.info("this is a " + monster.name);
                break;
        }
        isDone = true;
    }
}
