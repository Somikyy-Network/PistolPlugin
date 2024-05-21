package org.pistolplugin.Commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GetPistolCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            ItemStack pistol = new ItemStack(Material.IRON_HOE);
            ItemMeta meta = pistol.getItemMeta();
            meta.setDisplayName(ChatColor.GRAY + "Pistol");
            pistol.setItemMeta(meta);
            player.getInventory().addItem(pistol);
            player.sendMessage(ChatColor.GREEN + "You have received a pistol!");
            return true;
        }
        return false;
    }
}