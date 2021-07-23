package me.itzdavidvzla.insanecommands;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public final class InsaneCommands extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&cInsaneCommands&8] &7Plugin &aenabled"));
        PluginCore pluginCore = new PluginCore(this);

    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&cInsaneCommands&8] &7Plugin &cdisabled"));
        // Plugin shutdown logic
    }
}
