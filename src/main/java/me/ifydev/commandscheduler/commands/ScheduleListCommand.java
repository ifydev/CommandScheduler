package me.ifydev.commandscheduler.commands;

import me.ifydev.commandscheduler.CommandSchedulerPlugin;
import me.ifydev.commandscheduler.Constants;
import me.ifydev.commandscheduler.util.ChatUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * @author Innectic
 * @since 06/20/2018
 */
public class ScheduleListCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("commandscheduler.command.list")) {
            sender.sendMessage(ChatUtil.convertString(Constants.PERMISSION_DENIED));
            return false;
        }

        CommandSchedulerPlugin.getPlugin().getScheduleManager().ifPresent(manager -> {
            manager.getSchedule().forEach((nickname, cmd) ->
                    sender.sendMessage(ChatColor.YELLOW + "  > " + ChatColor.DARK_BLUE + "" + ChatColor.BOLD +nickname + ChatColor.WHITE + " - " + ChatColor.GREEN + ChatColor.BOLD + cmd));
        });

        return false;
    }
}
