package me.ifydev.commandscheduler.commands;

import me.ifydev.commandscheduler.CommandSchedulerPlugin;
import me.ifydev.commandscheduler.Constants;
import me.ifydev.commandscheduler.ScheduleManager;
import me.ifydev.commandscheduler.util.ChatUtil;
import me.ifydev.commandscheduler.util.MiscUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author Innectic
 * @since 06/20/2018
 */
public class ScheduleCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission("commandscheduler.command.schedule")) {
            sender.sendMessage(ChatUtil.convertString(Constants.PERMISSION_DENIED));
            return false;
        }

        if (args.length < 3) {
            sender.sendMessage(ChatUtil.convertString(Constants.NOT_ENOUGH_ARGUMENTS_SCHEDULE));
            return false;
        }
        Optional<Integer> timeCheck = MiscUtil.tryInt(args[1]);
        if (!timeCheck.isPresent()) {
            sender.sendMessage(ChatUtil.convertString(Constants.TIME_MUST_BE_A_NUMBER));
            return false;
        }

        String nickname = args[0];
        int time = timeCheck.get();
        String command = args[2];
        String[] arguments = ChatUtil.getRemainingArgs(3, args);

        CommandSchedulerPlugin plugin = CommandSchedulerPlugin.getPlugin();
        if (!plugin.getScheduleManager().isPresent()) return false;

        ScheduleManager scheduleManager = plugin.getScheduleManager().get();
        if (scheduleManager.nicknameUsed(nickname)) {
            sender.sendMessage(ChatUtil.convertString(Constants.CANNOT_REUSE_NICKNAME));
            return false;
        }

        scheduleManager.startScheduleCommand(nickname, command, Arrays.asList(arguments), time);

        sender.sendMessage(ChatUtil.convertString(Constants.COMMAND_SCHEDULED.replace("<CMD>", command).replace("<TIME>", Integer.toString(time))));

        return false;
    }
}
