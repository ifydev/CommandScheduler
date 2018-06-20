package me.ifydev.commandscheduler;

import lombok.Getter;
import me.ifydev.commandscheduler.commands.ScheduleCommand;
import me.ifydev.commandscheduler.commands.ScheduleListCommand;
import me.ifydev.commandscheduler.commands.StopScheduleCommand;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;

/**
 * @author Innectic
 * @since 06/20/2018
 */
public class CommandSchedulerPlugin extends JavaPlugin {

    @Getter private Optional<ScheduleManager> scheduleManager = Optional.empty();

    @Override
    public void onEnable() {
        createConfig();

        this.scheduleManager = Optional.of(new ScheduleManager());

        getCommand("schedule").setExecutor(new ScheduleCommand());
        getCommand("unschedule").setExecutor(new StopScheduleCommand());
        getCommand("listschedule").setExecutor(new ScheduleListCommand());

        getLogger().info("Loading current schedules...");
        long start = System.currentTimeMillis();
        loadScheduledCommands();
        long end = System.currentTimeMillis() - start;

        getLogger().info("Loaded scheduled commands in " + (end / 1000) + " seconds.");
    }

    @Override
    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(this);
    }

    private void createConfig() {
        try {
            if (!getDataFolder().exists()) {
                boolean created = getDataFolder().mkdirs();
                if (!created) getLogger().log(Level.SEVERE, "Could not create config!");
            }
            File file = new File(getDataFolder(), "config.yml");
            if (!file.exists()) {
                getLogger().info("Config.yml not found, creating!");
                saveDefaultConfig();
            } else {
                getLogger().info("Config.yml found, loading!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadScheduledCommands() {
        FileConfiguration config = getConfig();

        ConfigurationSection commandSection = config.getConfigurationSection("commands");
        if (commandSection == null) return;

        commandSection.getKeys(false).forEach(section -> {
            if (commandSection.isString(section + ".command") && commandSection.isList(section + ".arguments") && commandSection.isInt(section + ".time")) {
                String command = commandSection.getString(section + ".command");
                List<String> arguments = commandSection.getStringList(section + ".arguments");
                int time = commandSection.getInt(section + ".time");

                this.scheduleManager.ifPresent(manager -> manager.startScheduleCommand(section, command, arguments, time));
            }
        });
    }

    public static CommandSchedulerPlugin getPlugin() {
        return CommandSchedulerPlugin.getPlugin(CommandSchedulerPlugin.class);
    }
}
