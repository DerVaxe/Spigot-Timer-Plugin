package me.dervaxe.timerplugin.timer;

import me.dervaxe.timerplugin.TimerPlugin;
import me.dervaxe.timerplugin.menu.TimerMenu;
import me.dervaxe.timerplugin.timer.MyTimerTask;
import me.dervaxe.timerplugin.utils.ActionBarUtil;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

public class RunTimer {

    private TimerPlugin plugin;

    public RunTimer(TimerPlugin plugin){
        this.plugin = plugin;
    }

    BukkitTask bukkitTask;
    MyTimerTask task;
    ActionBarUtil actionBar = new ActionBarUtil();
    int current_time = 0;
    boolean checkPaused = true; // checks if the current timer is paused

    public RunTimer() {
    }

    public void startTimer(){
        if(bukkitTask != null){
            bukkitTask.cancel();
            task.cancel();
            current_time = 0;
        }
        task = new MyTimerTask(current_time);
        bukkitTask = task.runTaskTimer(plugin, 0, 20);
    }

    public void pauseTimer(){
        bukkitTask.cancel();
        current_time = task.getTime();
        checkPaused = false;
        for(Player p : Bukkit.getOnlinePlayers()){
            actionBar.sendMessage(p, plugin.getConfig().getString("paused-message"));
        }
    }

    public void resumeTimer(){
        if(checkPaused && !task.isCancelled()){
            current_time = task.getTime();
            bukkitTask.cancel();
            task.cancel();
        }
        task = new MyTimerTask(current_time-1);
        bukkitTask = task.runTaskTimer(plugin, 0, 20);
        checkPaused = true;
    }

    public void stopTimer(){
        bukkitTask.cancel();
        task.cancel();
        current_time = 0;
        for(Player p : Bukkit.getOnlinePlayers()){
            actionBar.sendMessage(p, plugin.getConfig().getString("stopped-message"));
        }
    }

    public void setTime(Player p, int time){

        if(bukkitTask != null){
            bukkitTask.cancel();
            task.cancel();
        }
        current_time = time-1;
        task = new MyTimerTask(current_time);
        bukkitTask = task.runTaskTimer(plugin, 0, 20);

        int hours = time / 3600;
        int minutes = (time % 3600) / 60;
        int seconds = time% 60;
        String timeString;
        timeString = String.format("%02dh %02dm %02ds", hours, minutes, seconds);
        p.sendMessage(ChatColor.GOLD+"["+ChatColor.AQUA+"Timer"+ChatColor.GOLD+"] "+ChatColor.GREEN+"Time set to "+timeString);

        if(plugin.getConfig().getBoolean("timer-paused-when-set")){
            bukkitTask.cancel();
            checkPaused = false;
            p.sendMessage(ChatColor.GOLD+"["+ChatColor.AQUA+"Timer"+ChatColor.GOLD+"] "+ChatColor.GREEN+"/timer resume to resume with the set timer");
            for(Player player : Bukkit.getOnlinePlayers()){
                actionBar.sendMessage(player, plugin.getConfig().getString("paused-message"));
            }
        }

    }

    public void timer_desc(Player p, RunTimer runTimer){

        if(plugin.getConfig().getBoolean("gui")){
            TimerMenu menu = new TimerMenu(TimerPlugin.getPlayerMenuUtility(p), runTimer);
            menu.open();
        }else{
            p.sendMessage(ChatColor.GOLD+"["+ChatColor.AQUA+"Timer"+ChatColor.GOLD+"] "+ChatColor.GREEN +"/timer start - start a new timer");
            p.sendMessage(ChatColor.GOLD+"["+ChatColor.AQUA+"Timer"+ChatColor.GOLD+"] "+ChatColor.GREEN +"/timer pause - pause the current timer");
            p.sendMessage(ChatColor.GOLD+"["+ChatColor.AQUA+"Timer"+ChatColor.GOLD+"] "+ChatColor.GREEN +"/timer resume - resume the paused timer");
            p.sendMessage(ChatColor.GOLD+"["+ChatColor.AQUA+"Timer"+ChatColor.GOLD+"] "+ChatColor.GREEN +"/timer stop - delete and stop the current timer");
            p.sendMessage(ChatColor.GOLD+"["+ChatColor.AQUA+"Timer"+ChatColor.GOLD+"] "+ChatColor.GREEN+"/timer set <time> - set the time of the timer");
        }

    }

}
