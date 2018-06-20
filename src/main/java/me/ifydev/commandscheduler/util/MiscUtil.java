package me.ifydev.commandscheduler.util;

import java.util.Optional;

/**
 * @author Innectic
 * @since 06/20/2018
 */
public class MiscUtil {

    public static Optional<Integer> tryInt(String convert) {
        try {
            return Optional.of(Integer.valueOf(convert));
        } catch (NumberFormatException ignored) {
            return Optional.empty();
        }
    }
}
