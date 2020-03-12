package com.toke1597.healthview;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Commands implements CommandExecutor {

    static List<String> toggled = new ArrayList<String>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(label.equalsIgnoreCase("healthview")){
            if(!(sender instanceof Player)){
                sender.sendMessage("You have to use this command in game!");
                return false;
            }
            Player player =(Player) sender;
            if(toggled.contains(player.getName())){
                player.sendMessage(ChatColor.RED+"체력보기를 비활성화했습니다.");
                toggled.remove(player.getName());
                return true;
            }
            toggled.add(player.getName());
            player.sendMessage(ChatColor.GREEN+"체력보기를 활성화했습니다.");
            return true;
        }
        return false;
    }
}
