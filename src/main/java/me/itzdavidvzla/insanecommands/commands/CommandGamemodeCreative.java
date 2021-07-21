package me.itzdavidvzla.insanecommands.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import me.itzdavidvzla.insanecommands.InsaneCommands;

public class CommandGamemodeCreative implements CommandExecutor {
	
	private InsaneCommands plugin;
	public CommandGamemodeCreative(InsaneCommands plugin){
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!(sender instanceof Player)) {
			Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&cInsane&4Commands&8] &7This command not be executed from console"));
        	return false;
		}
		Player player = (Player)sender;
		FileConfiguration messages = plugin.getMessages();
		if(player.hasPermission("ic.gamemode")) {
			if(args.length == 0){
				player.setGameMode(GameMode.CREATIVE);
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("Messages.gamemode_creative")));
				return true;
			} 
			if(args.length == 1){
				Player playergmc = Bukkit.getServer().getPlayer(args[0]);
				if(playergmc == null) {
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("Messages.player_disconnected")));
					player.playSound(player.getLocation(), Sound.ANVIL_USE, 1.0F, 1.0F);
					return true;
				} 
				player.setGameMode(GameMode.CREATIVE);
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&cInsane&4Commands&8] &7Modo de Juego de " + args[0] + " &7establecido a &ccreativo&8."));
			}else{
  	  			player.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("Messages.unknown_command")));
  	  			player.playSound(player.getLocation(), Sound.ANVIL_USE, 1.0F, 1.0F);
    			}
    		}else{
    			player.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("Messages.no_permission")));
    			player.playSound(player.getLocation(), Sound.ANVIL_USE, 1.0F, 1.0F);
    		}	 
		return false;
	}
}
