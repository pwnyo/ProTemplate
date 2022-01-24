package theFallenHuman.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.beyond.Darkling;
import com.megacrit.cardcrawl.monsters.beyond.Reptomancer;
import com.megacrit.cardcrawl.monsters.city.GremlinLeader;
import com.megacrit.cardcrawl.monsters.city.TheCollector;
import com.megacrit.cardcrawl.powers.SporeCloudPower;
import theFallenHuman.patches.SparePatch;

public class SpareAction extends AbstractGameAction {
    private AbstractMonster monster;

    public SpareAction(AbstractMonster m) {
        this.monster = m;
        duration = 0.0f;
        actionType = ActionType.DAMAGE;
    }

    public void triggerSpecialSpares(AbstractMonster mo) {
        if (mo.hasPower(SporeCloudPower.POWER_ID)) {
            mo.powers.remove(mo.getPower(SporeCloudPower.POWER_ID));
        }

        if (mo instanceof Darkling) {
            Darkling darkling = (Darkling)mo;
            darkling.halfDead = true;
        }
        else if ((mo instanceof GremlinLeader) || (mo instanceof Reptomancer) || (mo instanceof TheCollector)) {
            for (AbstractMonster m : AbstractDungeon.getCurrRoom().monsters.monsters) {
                if (!m.isDying) {
                    AbstractDungeon.actionManager.addToBottom(new SpareAction(m));
                }
            }
        }
    }
    @Override
    public void update() {
        if (duration == 0.0f) {
            monster.currentHealth = 0;
            triggerSpecialSpares(monster);
            monster.die(true);
            monster.healthBarUpdatedEvent();
            SparePatch.Escape.isEscaping.set(monster, true);
        }
        this.tickDuration();
    }
}
