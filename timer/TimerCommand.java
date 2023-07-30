package me.dervaxe.timerplugin.timer;



import me.dervaxe.timerplugin.timer.RunTimer;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class TimerCommand implements CommandExecutor, TabCompleter {

    private final RunTimer runTimer;

    public TimerCommand(RunTimer runTimer){
        this.runTimer = runTimer;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        if(sender instanceof Player p){

            if(strings.length == 0){
                runTimer.timer_desc(p, runTimer);
                return true;
            }

            switch (strings[0]){
                case "start":
                    runTimer.startTimer();
                    break;
                case "pause":
                    runTimer.pauseTimer();
                    break;
                case "resume":
                    runTimer.resumeTimer();
                    break;
                case "stop":
                    runTimer.stopTimer();
                    break;
                case "set":
                    try {
                        runTimer.setTime(p, Integer.parseInt(strings[1]));
                    }catch (Exception e){
                        p.sendMessage(ChatColor.GOLD+"["+ChatColor.AQUA+"Timer"+ChatColor.GOLD+"] "+ChatColor.GREEN +"Usage: /timer set <time>");
                    }
                    break;
                case "help":
                    p.sendMessage(ChatColor.GOLD+"["+ChatColor.AQUA+"Timer"+ChatColor.GOLD+"] "+ChatColor.GREEN +"/timer start - start a new timer");
                    p.sendMessage(ChatColor.GOLD+"["+ChatColor.AQUA+"Timer"+ChatColor.GOLD+"] "+ChatColor.GREEN +"/timer pause - pause the current timer");
                    p.sendMessage(ChatColor.GOLD+"["+ChatColor.AQUA+"Timer"+ChatColor.GOLD+"] "+ChatColor.GREEN +"/timer resume - resume the paused timer");
                    p.sendMessage(ChatColor.GOLD+"["+ChatColor.AQUA+"Timer"+ChatColor.GOLD+"] "+ChatColor.GREEN +"/timer stop - delete and stop the current timer");
                    p.sendMessage(ChatColor.GOLD+"["+ChatColor.AQUA+"Timer"+ChatColor.GOLD+"] "+ChatColor.GREEN+"/timer set <time> - set the time of the timer");
                    break;
                default:
                    runTimer.timer_desc(p, runTimer);
                    break;
            }

        }
        return true;

    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {

        if(strings.length == 1){
            List<String> args = new ArrayList<>();
            args.add("start");
            args.add("pause");
            args.add("resume");
            args.add("stop");
            args.add("set");
            args.add("help");
            return args;
        }

        if(strings.length == 2){
            List<String> args = new ArrayList<>();
            args.add("<time>");
            return args;
        }

        return null;
    }

}

