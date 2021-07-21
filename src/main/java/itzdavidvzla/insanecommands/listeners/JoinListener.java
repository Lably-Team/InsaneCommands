package itzdavidvzla.insanecommands.listeners;

import itzdavidvzla.insanecommands.InsaneCommands;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {
	
	private InsaneCommands plugin;
  	public JoinListener(InsaneCommands plugin) {
  		this.plugin = plugin;
  }
  
  	@EventHandler
  	public void toEnter(PlayerJoinEvent event){
  		Player player = event.getPlayer();
  		FileConfiguration warps_and_spawn = plugin.getConfig();
  		FileConfiguration messages = plugin.getMessages();
  		String pathjoin = "Messages.welcome-motd-module";
  		if(messages.getString(pathjoin).equals("true")){
  			if(warps_and_spawn.contains("Spawn.x")){
  				double x = Double.valueOf(warps_and_spawn.getString("Spawn.x"));
  				double y = Double.valueOf(warps_and_spawn.getString("Spawn.y"));
  				double z = Double.valueOf(warps_and_spawn.getString("Spawn.z"));
  				float yaw = Float.valueOf(warps_and_spawn.getString("Spawn.yaw"));
  				float pitch = Float.valueOf(warps_and_spawn.getString("Spawn.pitch"));
  				World world = plugin.getServer().getWorld(warps_and_spawn.getString("Spawn.world"));
  				Location l = new Location(world, x, y, z, yaw, pitch);
  				player.teleport(l);
  		}
  	}
}
  	
  	@EventHandler
  	public void toEnterMotd(PlayerJoinEvent event){
  		Player player = event.getPlayer();
  		FileConfiguration messages = plugin.getMessages();
  		String pathjoin = "Messages.welcome-motd-module";
  		if(messages.getString(pathjoin).equals("true")){
  			List<String> message = messages.getStringList("Messages.welcome-motd");
  			for(int i = 0; i < message.size(); i++){
  				String textwelcome = message.get(i);
  				player.sendMessage(ChatColor.translateAlternateColorCodes('&', textwelcome).replaceAll("%player%", player.getName()));
  			}
  		}
  	}
  	
  	@EventHandler
  	public void toEnterAnnouncement(PlayerJoinEvent event){
  	  		Player player = event.getPlayer();
  	  		FileConfiguration messages = plugin.getMessages();
  	  		event.setJoinMessage("");
  	  		Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("Messages.messagejoin").replaceAll("%ic_player%", player.getName())));
  		}
	}
