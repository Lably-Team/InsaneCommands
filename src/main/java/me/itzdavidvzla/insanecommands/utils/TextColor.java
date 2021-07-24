package me.itzdavidvzla.insanecommands.utils;

import org.bukkit.ChatColor;

public class TextColor {

    public static String colorized(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

}
