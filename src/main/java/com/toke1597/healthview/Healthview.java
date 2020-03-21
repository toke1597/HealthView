package com.toke1597.healthview;

import org.bukkit.ChatColor;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
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
    public void onJoinEvent(PlayerJoinEvent e) {
        Player p = (Player) e.getPlayer();
        toggled.add(p.getName());
    }

    @EventHandler
    public void entityDamage(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player) {
            Player p = (Player) e.getDamager();

            LivingEntity livingEntity = (LivingEntity) e.getEntity();

            if (toggled.contains(p.getName())) {
                for (Entity entity : p.getNearbyEntities(10, 10, 10)) {

                    double health = (livingEntity.getHealth() - e.getDamage());
                    health = Math.round((health) * 10) / 10.0;
                    if (health < 0) health = 0;
                    p.sendTitle("", livingEntity.getName() + " : " + ChatColor.RED + health, 5, 5, 5);

                }
            }
        }
        if (e.getDamager() instanceof Arrow) {
            Arrow arrow = (Arrow) e.getDamager();
            Player shooter = (Player) arrow.getShooter();
            LivingEntity livingEntity = (LivingEntity) e.getEntity();

            if (toggled.contains(shooter.getName())) {
                for (Entity entity : shooter.getNearbyEntities(20, 20, 20)) {

                    double health = (livingEntity.getHealth() - e.getDamage());
                    health = Math.round((health) * 10) / 10.0;
                    if (health < 0) health = 0;
                    shooter.sendTitle("", livingEntity.getName() + " : " + ChatColor.RED + health, 5, 5, 5);

                }
            }
        }
        if (e.getDamager() instanceof Trident) {
            Trident trident = (Trident) e.getDamager();
            Player shooter = (Player) trident.getShooter();
            LivingEntity livingEntity = (LivingEntity) e.getEntity();

            if (toggled.contains(shooter.getName())) {
                for (Entity entity : shooter.getNearbyEntities(10, 10, 10)) {

                    double health = (livingEntity.getHealth() - e.getDamage());
                    health = Math.round((health) * 10) / 10.0;
                    if (health < 0) health = 0;
                    shooter.sendTitle("", livingEntity.getName() + " : " + ChatColor.RED + health, 5, 5, 5);

                }
            }
        }
    }
}

