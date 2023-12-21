package com.shakytimon.simplegamemode;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        CommandHandler commandHandler = new CommandHandler();
        getCommand("gms").setExecutor(commandHandler);
        getCommand("gmc").setExecutor(commandHandler);
        getLogger().info("SimpleGamemode plugin successfully loaded.");
    }

    @Override
    public void onDisable() { getLogger().info("SimpleGamemode plugin is shutting down."); }
}
