package me.ifydev.commandscheduler;

/**
 * @author Innectic
 * @since 06/20/2018
 */
public class Constants {

    public static final String PREFIX = "&2&lCommand Scheduler> ";

    public static final String PERMISSION_DENIED = PREFIX + "&c&lYou don't have permission for this command!";
    public static final String COMMAND_SCHEDULED = PREFIX + "&2&lCommand '<CMD>' scheduled to run every <TIME> seconds.";
    public static final String COMMAND_UNSCHEDULED = PREFIX + "&2&lCommand '<CMD>' is unscheduled.";
    public static final String NOT_ENOUGH_ARGUMENTS_SCHEDULE = PREFIX + "&2&lNot enough arguments! &e&l/schedule <name> <time_in_seconds> <command> [args...]";
    public static final String NOT_ENOUGH_ARGUMENTS_UNSCHEDULE = PREFIX + "&2&lNot enough arguments! &e&l/unschedule <name>";
    public static final String TIME_MUST_BE_A_NUMBER = PREFIX + "&2&lTime must be a number!";
    public static final String COMMAND_IS_NOT_SCHEDULED = PREFIX + "&2&lCommand is not scheduled!";
    public static final String CANNOT_REUSE_NICKNAME = PREFIX + "&2&lCannot reuse nicknames that are in use!";
}
