package me.itzdavidvzla.insanecommands.listener;

import me.itzdavidvzla.insanecommands.PluginCore;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class DefaultEventListener implements Listener {

    private PluginCore pluginCore;

    public DefaultEventListener(PluginCore pluginCore){
        this.pluginCore = pluginCore;
    }
    // Event model
    @EventHandler
    public void onBreak(BlockBreakEvent event){

    }
}
