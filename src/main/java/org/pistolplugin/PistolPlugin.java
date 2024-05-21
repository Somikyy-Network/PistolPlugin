package org.pistolplugin;

import org.pistolplugin.Commands.GetPistolCommand;
import org.pistolplugin.Listeners.PistolShootListener;

import org.bukkit.plugin.java.JavaPlugin;

public class PistolPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getCommand("getpistol").setExecutor(new GetPistolCommand());
        getServer().getPluginManager().registerEvents(new PistolShootListener(), this);
        getLogger().info("PistolPlugin has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("PistolPlugin has been disabled.");
    }
}
