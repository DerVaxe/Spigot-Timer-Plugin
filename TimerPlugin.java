package me.dervaxe.timerplugin;


import me.dervaxe.timerplugin.menu.MenuListener;
import me.dervaxe.timerplugin.timer.RunTimer;
import me.dervaxe.timerplugin.timer.TimerCommand;
import me.dervaxe.timerplugin.utils.PlayerMenuUtility;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;


public final class TimerPlugin extends JavaPlugin {

    private static TimerPlugin plugin;
    private static final HashMap<Player, PlayerMenuUtility> playerUtilityMap = new HashMap<>();

    public static TimerPlugin getPlugin(){
        return plugin;
    }


    @Override
    public void onEnable() {

        getCommand("timer").setExecutor(new TimerCommand(new RunTimer(this)));
        getCommand("timer").setTabCompleter(new TimerCommand(new RunTimer(this)));

        getServer().getPluginManager().registerEvents(new MenuListener(), this);

        getConfig().options().copyDefaults();
        saveDefaultConfig();
        plugin = this;

    }

    public static PlayerMenuUtility getPlayerMenuUtility(Player p){
        PlayerMenuUtility playerMenuUtility;

        if(playerUtilityMap.containsKey(p)){
            return playerUtilityMap.get(p);
        }else{
            playerMenuUtility = new PlayerMenuUtility(p);
            playerUtilityMap.put(p, playerMenuUtility);
            return playerMenuUtility;
        }

    }

}
