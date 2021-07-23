package me.itzdavidvzla.insanecommands.loader;

import me.itzdavidvzla.insanecommands.InsaneCommands;
import me.itzdavidvzla.insanecommands.api.Loader;
import me.itzdavidvzla.insanecommands.manager.FileManager;

public class FilesLoader implements Loader {

    private final InsaneCommands plugin;
    private FileManager configFile;

    public FilesLoader(InsaneCommands plugin){
        this.plugin = plugin;
    }
    @Override
    public void load() {
        // config.yml file
        configFile = new FileManager(plugin, "config.yml");

    }


    public FileManager getConfigFile() {
        return configFile;
    }
}
