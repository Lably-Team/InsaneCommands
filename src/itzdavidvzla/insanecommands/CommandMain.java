package itzdavidvzla.insanecommands;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class CommandMain implements CommandExecutor{
	private InsaneCommands plugin;
  
	public CommandMain(InsaneCommands plugin){
		this.plugin = plugin;
	}
  
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
		if(!(sender instanceof Player)){
			Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&cInsane&4Commands&8] &7This command not be executed from console"));
			return false;
		} 
		
		Player player = (Player) sender; //jonakulasos uwu
		
		if(player.hasPermission("ic.help")){
			if(args.length > 0){
				if(args[0].equalsIgnoreCase("help")){
					FileConfiguration messages = plugin.getMessages();
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&m-------------&8[&cInsane&4Commands&8]&8&m-------------"));
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&c/ic help&8: &7Comando de Administrador del Plugin&8."));
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&c/ic reload&8: &7Reiniciar la Configuración del Plugin&8."));
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8&m-------------&8[&cInsane&4Commands&8]&8&m-------------"));
					return true;
				}
				if(args[0].equalsIgnoreCase("reload")){
					FileConfiguration messages = plugin.getMessages();
					plugin.reloadMessages();
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("Messages.plugin-reload")));
					player.playSound(player.getLocation(), Sound.LEVEL_UP, 10.0F, 10.0F);
					PacketPlayOutTitle title = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, IChatBaseComponent.ChatSerializer.a("{\"text\":\"§cPlugin Reloaded!\"}"), 100, 20, 20);
					(((CraftPlayer)player).getHandle()).playerConnection.sendPacket(title);
					return true;
				
				}else{
					FileConfiguration messages = plugin.getMessages();
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("Messages.unknown_command")));
					player.playSound(player.getLocation(), Sound.ANVIL_USE, 1.0F, 1.0F);
				}
				
			}else{
				FileConfiguration messages = plugin.getMessages();
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("Messages.help_command")));
				player.playSound(player.getLocation(), Sound.CLICK, 1.0F, 1.0F);
			}  
		}	
    return false;
	}
}