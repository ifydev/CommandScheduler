package me.ifydev.commandscheduler.util;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Innectic
 * @since 06/20/2018
 */
public class ChatUtil {

    public static String convertString(String converting) {
        return ChatColor.translateAlternateColorCodes('&', converting);
    }

    public static String[] getRemainingArgs(int starting, String[] arguments) {
        List<String> args = new ArrayList<>(Arrays.asList(arguments));
        return args.subList(starting, args.size()).toArray(new String[]{});
    }
}
