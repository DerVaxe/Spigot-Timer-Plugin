package me.dervaxe.timerplugin.timer;

import me.dervaxe.timerplugin.TimerPlugin;
import me.dervaxe.timerplugin.utils.ActionBarUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class MyTimerTask extends BukkitRunnable {

    private int time;
    private final TimerPlugin plugin = TimerPlugin.getPlugin();
    private boolean show_units = plugin.getConfig().getBoolean("show-units");
    private boolean show_instant = plugin.getConfig().getBoolean("show-instant-min-hours");
    ActionBarUtil actionBar = new ActionBarUtil();

    public MyTimerTask(int time){
        this.time = time;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public void run() {
        time++;
        int hours = time / 3600;
        int minutes = (time % 3600) / 60;
        int seconds = time% 60;
        String timeString;

        if(show_units){
            if(hours >= 1 || show_instant){
                timeString = String.format("%02dh %02dm %02ds", hours, minutes, seconds);
            }
            else if(minutes >= 1){
                timeString = String.format("%02dm %02ds", minutes, seconds);
            }else{
                timeString = String.format("%02ds", seconds);
            }
        }

        else{
            if(hours >= 1|| show_instant){
                timeString = String.format("%02d:%02d:%02d", hours, minutes, seconds);
            }
            else if(minutes >= 1){
                timeString = String.format("%02d:%02d", minutes, seconds);
            }else{
                timeString = String.format("%02d", seconds);
            }
        }

        for(Player p : Bukkit.getOnlinePlayers()){
            actionBar.sendMessage(p, plugin.getConfig().getString("timer-color")+timeString);
        }

    }
}
