package com.fletchplugins.perworldcommands;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class PerWorldCommands extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getCommand("pw").setExecutor(new CommandClass());
        this.getCommand("pw").setTabCompleter(new PerWorldTabCompleter());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
