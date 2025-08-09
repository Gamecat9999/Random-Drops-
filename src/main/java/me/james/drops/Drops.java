package me.james.drops;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Drops extends JavaPlugin {
    private static Drops instance;
    private DropManager dropManager;

    @Override
    public void onEnable() {
        instance = this;
        dropManager = new DropManager(this);
        getCommand("startdrops").setExecutor(new DropCommand(dropManager, true));
        getCommand("stopdrops").setExecutor(new DropCommand(dropManager, false));

        getLogger().info("Drops plugin enabled on Bukkit version: " + Bukkit.getBukkitVersion());
    }

    public static Drops getInstance() {
        return instance;
    }
}