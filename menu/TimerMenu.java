package me.dervaxe.timerplugin.menu;

import me.dervaxe.timerplugin.timer.RunTimer;
import me.dervaxe.timerplugin.utils.Menu;
import me.dervaxe.timerplugin.utils.PlayerMenuUtility;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class TimerMenu extends Menu {

    public final RunTimer runTimer;

    public TimerMenu(PlayerMenuUtility playerMenuUtility, RunTimer runTimer) {
        super(playerMenuUtility);
        this.runTimer = runTimer;
    }

    @Override
    public String getMenuName() {
        return ChatColor.DARK_GREEN+"Timer Menu";
    }

    @Override
    public int getSlot() {
        return 27;
    }


    @Override
    public void handleMenu(InventoryClickEvent e) {

        switch (e.getCurrentItem().getType()){
            case NETHER_STAR:
                runTimer.startTimer();
                break;
            case RED_DYE:
                runTimer.pauseTimer();
                break;
            case GREEN_DYE:
                runTimer.resumeTimer();
                break;
            case BARRIER:
                runTimer.stopTimer();
                break;
        }
    }

    @Override
    public void setMenuItems() {

        ItemStack start = new ItemStack(Material.NETHER_STAR);
        ItemMeta startMeta = start.getItemMeta();
        startMeta.setDisplayName(ChatColor.DARK_GREEN+"Start");
        start.setItemMeta(startMeta);

        ItemStack pause = new ItemStack(Material.RED_DYE);
        ItemMeta pauseMeta = pause.getItemMeta();
        pauseMeta.setDisplayName(ChatColor.RED+"Pause");
        pause.setItemMeta(pauseMeta);

        ItemStack resume = new ItemStack(Material.GREEN_DYE);
        ItemMeta resumeMeta = resume.getItemMeta();
        resumeMeta.setDisplayName(ChatColor.GREEN+"Resume");
        resume.setItemMeta(resumeMeta);

        ItemStack stop = new ItemStack(Material.BARRIER);
        ItemMeta stopMeta = stop.getItemMeta();
        stopMeta.setDisplayName(ChatColor.DARK_RED+"Stop");
        stop.setItemMeta(stopMeta);

        ItemStack placeholder = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta placeholderMeta = placeholder.getItemMeta();
        placeholderMeta.setDisplayName(ChatColor.BLACK+"-");
        placeholder.setItemMeta(placeholderMeta);

        inventory.setItem(9, placeholder);
        inventory.setItem(10, start);
        inventory.setItem(11, placeholder);
        inventory.setItem(12, pause);
        inventory.setItem(13, placeholder);
        inventory.setItem(14, resume);
        inventory.setItem(15, placeholder);
        inventory.setItem(16, stop);
        inventory.setItem(17, placeholder);

        for(int i = 0; i<9; i++){
            inventory.setItem(i, placeholder);
        }

        for(int i = 18; i<27; i++){
            inventory.setItem(i, placeholder);
        }

    }

}
