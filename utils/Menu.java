package me.dervaxe.timerplugin.utils;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

public abstract class Menu implements InventoryHolder {

    protected Inventory inventory;
    protected PlayerMenuUtility playerMenuUtility;

    public Menu(PlayerMenuUtility playerMenuUtility){
        this.playerMenuUtility = playerMenuUtility;
    }

    public abstract String getMenuName();
    public abstract int getSlot();
    public abstract void handleMenu(InventoryClickEvent e);
    public abstract void setMenuItems();

    public void open(){
        inventory = Bukkit.createInventory(this, getSlot(), getMenuName());

        this.setMenuItems();
        playerMenuUtility.getOwner().openInventory(inventory);
    }

    @NotNull
    @Override
    public Inventory getInventory() {
        return inventory;
    }

}