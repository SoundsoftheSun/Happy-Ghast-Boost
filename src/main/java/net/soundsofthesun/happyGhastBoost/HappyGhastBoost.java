package net.soundsofthesun.happyGhastBoost;

import net.fabricmc.api.ModInitializer;
import net.soundsofthesun.happyGhastBoost.attachments.MAttachments;
import net.soundsofthesun.happyGhastBoost.commands.MCommands;
import net.soundsofthesun.happyGhastBoost.effects.MEffects;

public class HappyGhastBoost implements ModInitializer {
    public static final String MOD_ID = "happy_ghast_boost";
    @Override
    public void onInitialize() {
        MEffects.init();
        MAttachments.init();
        MCommands.init();
    }
}
