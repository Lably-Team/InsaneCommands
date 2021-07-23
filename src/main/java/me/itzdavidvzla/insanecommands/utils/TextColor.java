package me.itzdavidvzla.insanecommands.utils;

import org.bukkit.ChatColor;

public class TextColor {

    public static String colorized(String path) {
        return ChatColor.translateAlternateColorCodes('&', path);
    }

}
