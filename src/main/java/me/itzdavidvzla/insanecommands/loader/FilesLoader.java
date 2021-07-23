package me.itzdavidvzla.insanecommands.loader;

import me.itzdavidvzla.insanecommands.InsaneCommands;
import me.itzdavidvzla.insanecommands.api.Loader;
import me.itzdavidvzla.insanecommands.manager.FileManager;

public class FilesLoader implements Loader {

    private final InsaneCommands plugin;
    private FileManager config;
    private FileManager messages;

    public FilesLoader(InsaneCommands plugin){
        this.plugin = plugin;
    }
    @Override
    public void load() {
        config = new FileManager(plugin, "config.yml");
        messages = new FileManager(plugin, "messages.yml");

    }

    public FileManager getConfig() {
        return config;
    }

    public FileManager getMessages() {
        return messages;
    }
}
