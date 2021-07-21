package me.itzdavidvzla.insanecommands.titles;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class TitleBuilder {
  private String title;
  
  private String subtitle;
  
  private int titleFadeIn;
  
  private int titleStay;
  
  private int titleFadeOut;
  
  private int subFadeIn;
  
  private int subStay;
  
  private int subFadeOut;
  
  public TitleBuilder(String title, String subtitle) {
    this.title = title;
    this.subtitle = subtitle;
  }
  
  public TitleBuilder setTimings(int titleFadeIn, int titleStay, int titleFadeOut, int subFadeIn, int subStay, int subFadeOut) {
    this.titleFadeIn = titleFadeIn;
    this.titleStay = titleStay;
    this.titleFadeOut = titleFadeOut;
    this.subFadeIn = subFadeIn;
    this.subStay = subStay;
    this.subFadeOut = subFadeOut;
    return this;
  }
  
  public TitleBuilder send(Player p) {
    PacketPlayOutTitle packet1 = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TIMES, IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + this.title + "\"}"), this.titleFadeIn, this.titleStay, this.titleFadeOut);
    PacketPlayOutTitle packet2 = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TIMES, IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + this.subtitle + "\"}"), this.subFadeIn, this.subStay, this.subFadeOut);
    PacketPlayOutTitle packet3 = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + this.title + "\"}"));
    PacketPlayOutTitle packet4 = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + this.subtitle + "\"}"));
    sendPacket(p, (Packet<?>)packet1);
    sendPacket(p, (Packet<?>)packet2);
    sendPacket(p, (Packet<?>)packet3);
    sendPacket(p, (Packet<?>)packet4);
    return this;
  }
  
  private static void sendPacket(Player player, Packet<?> packet) {
    (((CraftPlayer)player).getHandle()).playerConnection.sendPacket(packet);
  }
}