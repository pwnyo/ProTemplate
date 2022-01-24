package theFallenHuman.patches;

import com.badlogic.gdx.Gdx;
import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.ending.SpireShield;

public class SparePatch {
    @SpirePatch(clz= AbstractMonster.class, method = SpirePatch.CLASS)
    public static class Escape {
        public static SpireField<Boolean> isEscaping = new SpireField<>(() -> false);
    }
    @SpirePatch(clz= AbstractMonster.class, method = "updateEscapeAnimation")
    public static class Spare {
        public static void Postfix(AbstractMonster __instance) {
            if (Escape.isEscaping.get(__instance)) {
                __instance.flipHorizontal = true;

                if (__instance instanceof SpireShield) {
                    __instance.drawX -= Gdx.graphics.getDeltaTime() * 200.0F;
                } else {
                    __instance.drawX += Gdx.graphics.getDeltaTime() * 200.0F;
                }
            }
        }
    }
}
