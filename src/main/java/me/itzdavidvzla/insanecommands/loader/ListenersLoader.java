package me.itzdavidvzla.insanecommands.loader;

import me.itzdavidvzla.insanecommands.InsaneCommands;
import me.itzdavidvzla.insanecommands.PluginCore;
import me.itzdavidvzla.insanecommands.api.Loader;
import me.itzdavidvzla.insanecommands.listener.DefaultEventListener;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;


public class ListenersLoader implements Loader {

    private final PluginCore pluginCore;

    public ListenersLoader(PluginCore pluginCore){
        this.pluginCore = pluginCore;
    }

    @Override
    public void load() {
        registerListeners(
                new DefaultEventListener(pluginCore));
    }

    public void registerListeners(Listener... listeners){
        PluginManager pluginManager = Bukkit.getPluginManager();
        InsaneCommands insaneCommands = pluginCore.getPlugin();

        for (Listener listener : listeners){
            pluginManager.registerEvents(listener, insaneCommands);
        }
    }
}
