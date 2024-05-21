package org.pistolplugin.Listeners;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.util.Vector;
import org.pistolplugin.PistolPlugin;

public class PistolShootListener implements Listener {

    private Plugin plugin = PistolPlugin.getPlugin(PistolPlugin.class);

    @EventHandler
    public void onPlayerShoot(PlayerInteractEvent event) {
        if (event.getHand() != EquipmentSlot.HAND) return;

        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();

        if (item != null && item.getType() == Material.IRON_HOE && item.getItemMeta().getDisplayName().equals(ChatColor.GRAY + "Pistol")) {
            Location eyeLocation = player.getEyeLocation();
            Vector direction = eyeLocation.getDirection();

            // Создание и запуск снежка (временная реализация пули)
            Projectile bullet = player.launchProjectile(Snowball.class);
            bullet.setVelocity(direction.multiply(2));
            bullet.setMetadata("pistolBullet", new FixedMetadataValue(plugin, true));

            // Воспроизведение звука выстрела
            player.playSound(player.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1.0f, 1.0f);

            // Визуальные эффекты
            player.getWorld().spawnParticle(Particle.SMOKE_LARGE, eyeLocation, 5, direction.getX(), direction.getY(), direction.getZ(), 0.1);
        }
    }

    @EventHandler
    public void onBulletHit(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();

        if (damager instanceof Projectile) {
            Projectile bullet = (Projectile) damager;
            if (bullet.hasMetadata("pistolBullet")) {
                ProjectileSource shooter = bullet.getShooter();

                if (shooter instanceof Player) {
                    double distance = bullet.getLocation().distance(((Player) shooter).getLocation());
                    double damage = 10 - (distance / 2); // Пример урона, уменьшающегося с расстоянием
                    event.setDamage(Math.max(damage, 1)); // Минимальный урон - 1
                }
            }
        }
    }
}