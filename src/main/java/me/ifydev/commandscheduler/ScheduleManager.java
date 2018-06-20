package me.ifydev.commandscheduler;

import lombok.Getter;
import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Innectic
 * @since 06/20/2018
 */
public class ScheduleManager {

    private Map<String, Integer> scheduled = new HashMap<>();
    @Getter private Map<String, String> schedule = new HashMap<>();

    public void startScheduleCommand(String nickname, String command, List<String> arguments, int time) {
        int id = Bukkit.getScheduler().runTaskTimer(CommandSchedulerPlugin.getPlugin(), () -> {
            String cmd = command + " " + String.join(" ", arguments);
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd);
        }, 0L, time * 20).getTaskId();

        scheduled.put(nickname, id);
        schedule.put(nickname, command + " " + String.join(" ", arguments));

        CommandSchedulerPlugin plugin = CommandSchedulerPlugin.getPlugin();
        plugin.getConfig().set("commands." + nickname + ".command", command);
        plugin.getConfig().set("commands." + nickname + ".arguments", arguments);
        plugin.getConfig().set("commands." + nickname + ".time", time);

        try {
            plugin.saveConfig();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopCommand(String nickname) {
        Integer id = scheduled.getOrDefault(nickname, null);

        if (id == null) return;
        Bukkit.getScheduler().cancelTask(id);
        schedule.remove(nickname);

        CommandSchedulerPlugin.getPlugin().getConfig().set("commands." + nickname, null);
        try {
            CommandSchedulerPlugin.getPlugin().saveConfig();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean nicknameUsed(String command) {
        return scheduled.containsKey(command);
    }
}
