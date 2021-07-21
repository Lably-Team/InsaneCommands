package me.itzdavidvzla.insanecommands.listeners;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import me.itzdavidvzla.insanecommands.InsaneCommands;

public class ChatListener implements Listener{

	private final InsaneCommands plugin;
	public ChatListener(InsaneCommands plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void setChatFormat(AsyncPlayerChatEvent event){
		Player player = event.getPlayer();
		FileConfiguration messages = plugin.getMessages();
		event.setFormat(ChatColor.translateAlternateColorCodes('&', "&c"+player.getDisplayName()+ " &8>&7 " +event.getMessage()+ ""));
	}
}
