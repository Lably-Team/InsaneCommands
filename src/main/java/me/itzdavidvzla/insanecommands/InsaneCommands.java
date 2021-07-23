package me.itzdavidvzla.insanecommands;

import org.bukkit.plugin.java.JavaPlugin;

public final class InsaneCommands extends JavaPlugin {

    @Override
    public void onEnable() {
        PluginCore pluginCore = new PluginCore(this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
