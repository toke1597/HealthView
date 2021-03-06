package com.toke1597.healthview;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

import static com.toke1597.healthview.Commands.toggled;

public class Healthview extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        System.out.println("[HealthView] " + ChatColor.AQUA + "On!");
        getServer().getPluginManager().registerEvents(this, this);
        getCommand("healthview").setExecutor(new Commands());
    }

    @EventHandler
    public void entityDamage(EntityDamageByEntityEvent e) {
        Player p = (Player) e.getDamager();
        LivingEntity livingEntity = (LivingEntity) e.getEntity();

        if (toggled.contains(p.getName())) {
            for (Entity entity : p.getNearbyEntities(10, 10, 10)) {
                double health = (livingEntity.getHealth() - e.getDamage());
                health = Math.round((health)*10)/10.0;
                if (health < 0) health = 0;
                p.sendTitle("", livingEntity.getName() + " : " + ChatColor.RED + health, 5, 5, 5);
            }
        }
    }
}
