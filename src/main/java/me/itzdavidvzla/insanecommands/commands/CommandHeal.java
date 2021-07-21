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

public class CommandHeal implements CommandExecutor {
  
	private InsaneCommands plugin;
  	public CommandHeal(InsaneCommands plugin){
  		this.plugin = plugin;
  }
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&cInsane&4Commands&8] &7This command not be executed from console"));
		    return false;
		} 
		Player player = (Player)sender;
		if(player.hasPermission("ic.heal")) {
			if(args.length == 0) {
				FileConfiguration messages = plugin.getMessages();
				player.setHealth(20);
				player.setFoodLevel(20);
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("Messages.heal")));
				return true;
			}
			if(args.length == 1){
				FileConfiguration messages = plugin.getMessages();
				Player playerhealed = Bukkit.getServer().getPlayer(args[0]);
				if(playerhealed == null){
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("Messages.player_disconnected")));
					player.playSound(player.getLocation(), Sound.ANVIL_USE, 1.0F, 1.0F);
					return true;
				} 
				player.setHealth(20);
				player.setFoodLevel(20);
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&cInsane&4Commands&8] &7Alimentaci�n de " +args[0]+ " &cRegenerada&8."));
			
		}else{
			FileConfiguration messages = plugin.getMessages();
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("Messages.unknown_command")));
			player.playSound(player.getLocation(), Sound.ANVIL_USE, 1.0F, 1.0F);
			
		}
	 }else{
	    FileConfiguration messages = plugin.getMessages();
	    player.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("Messages.no_permission")));
	    player.playSound(player.getLocation(), Sound.ANVIL_USE, 1.0F, 1.0F);
	      
	    } 
	   return false;
	}
}