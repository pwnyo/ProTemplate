package theFallenHuman.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFallenHuman.UndertaleMod;


public class MonsterDialogPatch {

    @SpirePatch(clz= AbstractMonster.class, method = SpirePatch.CLASS)
    public static class BonusDialog {
        public static SpireField<MonsterStrings> dialogStrings = new SpireField<MonsterStrings>(() -> new MonsterStrings());
        public static SpireField<String[]> DIALOG = new SpireField<String[]>(() -> new String[]{}); //Generally for Check
        public static SpireField<String[]> MOVES = new SpireField<String[]>(() -> new String[]{}); //Generally for Mercy process
    }
    @SpirePatch(clz= AbstractMonster.class, method = SpirePatch.CONSTRUCTOR, paramtypez = {
            String.class, String.class, int.class, float.class, float.class, float.class, float.class, String.class, float.class, float.class
    })
    public static class InitializeDialog {
        public static void Postfix(AbstractMonster __instance, String name, String id, int maxHealth, float hb_x, float hb_y, float hb_w, float hb_h, String imgUrl, float offsetX, float offsetY) {
            BonusDialog.dialogStrings.set(__instance, CardCrawlGame.languagePack.getMonsterStrings(UndertaleMod.makeID(__instance.id)));
            BonusDialog.DIALOG.set(__instance, BonusDialog.dialogStrings.get(__instance).DIALOG);
            BonusDialog.MOVES.set(__instance, BonusDialog.dialogStrings.get(__instance).MOVES);
        }
    }
}
