package net.prosavage.baseplugin.strings;

import org.bukkit.ChatColor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class StringProcessor {

    /**
     * Colors a string.
     *
     * @param string - string to color.
     * @return - returns a colored string.
     */
    public static String color(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    /**
     * Colors a list of strings.
     *
     * @param strings - list of strings to color.
     * @return - returns a colored list of strings.
     */
    public static List<String> color(List<String> strings) {
        return strings.stream().map(StringProcessor::color).collect(Collectors.toList());
    }


    public static String processMultiplePlaceholders(String line, Placeholder... placeholders) {
        for (Placeholder placeholder : placeholders) {
            line = color(placeholder.process(line));
        }
        return line;
    }




}
