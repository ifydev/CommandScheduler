package me.ifydev.commandscheduler.commands;

import me.ifydev.commandscheduler.CommandSchedulerPlugin;
import me.ifydev.commandscheduler.Constants;
import me.ifydev.commandscheduler.util.ChatUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * @author Innectic
 * @since 06/20/2018
 */
public class StopScheduleCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission("commandscheduler.command.unschedule")) {
            sender.sendMessage(ChatUtil.convertString(Constants.PERMISSION_DENIED));
            return false;
        }

        if (args.length < 1) {
            sender.sendMessage(ChatUtil.convertString(Constants.NOT_ENOUGH_ARGUMENTS_UNSCHEDULE));
            return false;
        }

        CommandSchedulerPlugin plugin = CommandSchedulerPlugin.getPlugin();

        String nickname = args[0];
        if (plugin.getScheduleManager().isPresent() && !plugin.getScheduleManager().get().nicknameUsed(nickname)) {
            sender.sendMessage(ChatUtil.convertString(Constants.COMMAND_IS_NOT_SCHEDULED));
            return false;
        }

        plugin.getScheduleManager().get().stopCommand(nickname);
        sender.sendMessage(ChatUtil.convertString(Constants.COMMAND_UNSCHEDULED.replace("<CMD>", nickname)));
        return false;
    }
}
