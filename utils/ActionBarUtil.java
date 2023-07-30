package me.dervaxe.timerplugin.utils;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ActionBarUtil {

    public void sendMessage(Player p, String message){
        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(String.valueOf(ChatColor.translateAlternateColorCodes('&', message))));
    }

}
