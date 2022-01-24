package theFallenHuman.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFallenHuman.TheFallenHuman;
import theFallenHuman.powers.SparablePower;

public class MonsterActPatch {
    @SpirePatch(clz= AbstractMonster.class, method = "usePreBattleAction")
    public static class ApplySecretPower {
        public static void Postfix(AbstractMonster __instance) {
            if (AbstractDungeon.player.chosenClass != TheFallenHuman.Enums.UT_HUMAN) {
                return;
            }
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(__instance, __instance, new SparablePower(__instance)));
        }
    }
}
