package me.itzdavidvzla.insanecommands.loader;

import me.itzdavidvzla.insanecommands.PluginCore;
import me.itzdavidvzla.insanecommands.api.Loader;

public class ManagerLoader implements Loader {

    private PluginCore pluginCore;

    public ManagerLoader(PluginCore pluginCore){
        this.pluginCore = pluginCore;
    }

    @Override
    public void load() {

    }
}
