package me.itzdavidvzla.insanecommands;

import me.itzdavidvzla.insanecommands.api.Core;
import me.itzdavidvzla.insanecommands.api.Loader;
import me.itzdavidvzla.insanecommands.loader.CommandsLoader;
import me.itzdavidvzla.insanecommands.loader.FilesLoader;
import me.itzdavidvzla.insanecommands.loader.ListenersLoader;
import me.itzdavidvzla.insanecommands.loader.ManagerLoader;

public class PluginCore implements Core {

    private final InsaneCommands plugin;

    private FilesLoader filesLoader;
    private ManagerLoader managerLoader;

    public PluginCore(InsaneCommands plugin){
        this.plugin = plugin;
    }

    @Override
    public void init() {
        filesLoader = new FilesLoader(plugin);
        filesLoader.load();

        managerLoader = new ManagerLoader(this);
        managerLoader.load();

        initLoaders(
                new CommandsLoader(this),
                new ListenersLoader(this));
    }


    private void initLoaders(Loader... loaders){
        for (Loader loader : loaders){
            loader.load();
        }
    }


    public FilesLoader getFilesLoader() {
        return filesLoader;
    }

    public InsaneCommands getPlugin(){
        return plugin;
    }

    public ManagerLoader getManagers(){
        return managerLoader;
    }
}
