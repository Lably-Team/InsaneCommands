package me.itzdavidvzla.insanecommands.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import me.itzdavidvzla.insanecommands.InsaneCommands;

public class CommandFeed implements CommandExecutor{
	
	private final InsaneCommands plugin;
  	public CommandFeed(InsaneCommands plugin){
  		this.plugin = plugin;
  }
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		FileConfiguration messages = plugin.getMessages();
		if(!(sender instanceof Player)){	
			Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&cInsane&4Commands&8] &7This command not be executed from console"));
			return false;
    	}
		Player player = (Player)sender;
		if(!player.hasPermission("ic.feed")){
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("Messages.no_permission")));
			player.playSound(player.getLocation(), Sound.ANVIL_USE, 1.0F, 1.0F);
			return true;
    	}

		if(args.length == 0){

			player.setFoodLevel(20);
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("Messages.feed")));
			return true;
		}
		if(args.length == 1){
			Player playerfeeded = Bukkit.getServer().getPlayer(args[0]);

			if(playerfeeded == null){
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("Messages.player_disconnected")));
				player.playSound(player.getLocation(), Sound.ANVIL_USE, 1.0F, 1.0F);
				return true;
			}
			player.setFoodLevel(20);
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&cInsane&4Commands&8] &7Alimentaci�n de " +args[0]+ " &cRegenerada&8."));

		}else{

			player.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("Messages.unknown_command")));
			player.playSound(player.getLocation(), Sound.ANVIL_USE, 1.0F, 1.0F);

		}
    	return false;
	}
}
