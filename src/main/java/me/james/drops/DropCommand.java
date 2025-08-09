package me.james.drops;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DropCommand implements CommandExecutor {
    private final DropManager dropManager;
    private final boolean start;

    public DropCommand(DropManager dropManager, boolean start) {
        this.dropManager = dropManager;
        this.start = start;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (start) {
            dropManager.startDrops();
            sender.sendMessage("§aDrops started.");
        } else {
            dropManager.stopDrops();
            sender.sendMessage("§cDrops stopped.");
        }
        return true;
    }
}