package net.soundsofthesun.happyGhastBoost.commands;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.soundsofthesun.happyGhastBoost.attachments.MAttachments;
import net.soundsofthesun.happyGhastBoost.attachments.SpeedBoostDuration;

import static net.minecraft.server.permissions.Permissions.COMMANDS_ADMIN;

public class MCommands {
    public static void init() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(Commands.literal("happy-ghast-boost")
                    .requires(source -> source.permissions().hasPermission(COMMANDS_ADMIN))
                    .executes(MCommands::help)
                    .then(Commands.literal("effect_duration")
                            .executes(MCommands::getEffectDuration)
                            .then(Commands.argument("seconds", IntegerArgumentType.integer(1))
                                    .executes(MCommands::setEffectDuration)))
            );
        });
    }

    private static int setEffectDuration(CommandContext<CommandSourceStack> context) {
        context.getSource().getLevel().setAttached(MAttachments.SPEED_BOOST_DURATION, new SpeedBoostDuration(context.getArgument("seconds", Integer.class)));
        context.getSource().sendSuccess(() -> Component.literal("Happy Ghast Speed Boost Duration: "+context.getSource().getLevel().getAttachedOrElse(MAttachments.SPEED_BOOST_DURATION, SpeedBoostDuration.DEFAULT).seconds()), false);
        return 1;
    }

    private static int getEffectDuration(CommandContext<CommandSourceStack> context) {
        context.getSource().sendSuccess(() -> Component.literal("Happy Ghast Speed Boost Duration: "+context.getSource().getLevel().getAttachedOrElse(MAttachments.SPEED_BOOST_DURATION, SpeedBoostDuration.DEFAULT).seconds()), false);
        return 1;
    }

    private static int help(CommandContext<CommandSourceStack> context) {
        context.getSource().sendSuccess(() -> Component.literal("/happy-ghast-boost {effect_duration} (Integer)"), false);
        context.getSource().sendSuccess(() -> Component.literal("Happy Ghast Speed Boost Duration: "+context.getSource().getLevel().getAttachedOrElse(MAttachments.SPEED_BOOST_DURATION, SpeedBoostDuration.DEFAULT).seconds()), false);
        return 1;
    }
    
}
