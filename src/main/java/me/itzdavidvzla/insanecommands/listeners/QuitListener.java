package me.itzdavidvzla.insanecommands.listeners;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.itzdavidvzla.insanecommands.InsaneCommands;

public class QuitListener implements Listener{
	
	private InsaneCommands plugin;
	public QuitListener(InsaneCommands plugin){
		this.plugin = plugin;
	}
	
	@EventHandler
  	public void toLeaveAnnouncement(PlayerQuitEvent event) {
  		Player player = event.getPlayer();
  		FileConfiguration messages = plugin.getMessages();
  		event.setQuitMessage("");
  		Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("Messages.messageleave").replaceAll("%ic_player%", player.getName())));
  		
	}
}

