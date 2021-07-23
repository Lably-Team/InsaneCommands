package me.itzdavidvzla.insanecommands;

import me.itzdavidvzla.insanecommands.utils.TextColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class InsaneCommands extends JavaPlugin {

    @Override
    public void onEnable() {
        PluginCore pluginCore = new PluginCore(this);
        pluginCore.init();
        getLogger().info(TextColor.colorized("&8[&cInsaneCommands&8] &7Plugin &aenabled"));

    }

    @Override
    public void onDisable() {
        getLogger().info(TextColor.colorized("&8[&cInsaneCommands&8] &7Plugin &cdisabled"));
        // Plugin shutdown logic
    }
}
