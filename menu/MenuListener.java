package me.dervaxe.timerplugin.menu;

import me.dervaxe.timerplugin.utils.Menu;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryHolder;


public class MenuListener implements Listener {

    @EventHandler
    public void onMenuClick(InventoryClickEvent e){

        Player p = (Player) e.getWhoClicked();
        InventoryHolder holder = e.getInventory().getHolder();

        if(e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_GREEN+"Timer Menu")){
            e.setCancelled(true);
        }

        if(holder instanceof Menu){
            e.setCancelled(true);

            if(e.getCurrentItem() == null){
                return;
            }

            Menu menu = (Menu) holder;
            menu.handleMenu(e);
        }

    }
}
