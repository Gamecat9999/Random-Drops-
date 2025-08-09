package me.james.drops;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;

import java.util.*;

public class DropManager {
    private final Drops plugin;
    private BukkitTask dropTask;
    private final List<LootItem> lootPool = new ArrayList<>();

    public DropManager(Drops plugin) {
        this.plugin = plugin;
        setupLootPool();
    }

    public void startDrops() {
        if (dropTask != null) return;

        dropTask = Bukkit.getScheduler().runTaskTimer(plugin, () -> {
            Bukkit.broadcastMessage("§b§l[Drops] §7The skies shimmer... loot is falling!");

            for (Player player : Bukkit.getOnlinePlayers()) {
                ItemStack drop = getWeightedRandomDrop();
                player.getInventory().addItem(drop);
                player.sendMessage("§6You received: §e" + drop.getType());
            }
        }, 0L, 600L); // every 30 seconds
    }

    public void stopDrops() {
        if (dropTask != null) {
            dropTask.cancel();
            dropTask = null;
        }
    }

    private void setupLootPool() {
        // Weapons
        lootPool.add(new LootItem(new ItemStack(Material.WOODEN_SWORD), 30));
        lootPool.add(new LootItem(new ItemStack(Material.STONE_SWORD), 25));
        lootPool.add(new LootItem(new ItemStack(Material.IRON_SWORD), 15));
        lootPool.add(new LootItem(new ItemStack(Material.DIAMOND_SWORD), 5));
        lootPool.add(new LootItem(new ItemStack(Material.NETHERITE_SWORD), 2));

        // Axes
        lootPool.add(new LootItem(new ItemStack(Material.STONE_AXE), 25));
        lootPool.add(new LootItem(new ItemStack(Material.DIAMOND_AXE), 5));
        lootPool.add(new LootItem(new ItemStack(Material.NETHERITE_AXE), 2));

        // Armor - Leather
        lootPool.add(new LootItem(new ItemStack(Material.LEATHER_HELMET), 30));
        lootPool.add(new LootItem(new ItemStack(Material.LEATHER_CHESTPLATE), 30));
        lootPool.add(new LootItem(new ItemStack(Material.LEATHER_LEGGINGS), 30));
        lootPool.add(new LootItem(new ItemStack(Material.LEATHER_BOOTS), 30));

        // Armor - Iron
        lootPool.add(new LootItem(new ItemStack(Material.IRON_HELMET), 15));
        lootPool.add(new LootItem(new ItemStack(Material.IRON_CHESTPLATE), 15));
        lootPool.add(new LootItem(new ItemStack(Material.IRON_LEGGINGS), 15));
        lootPool.add(new LootItem(new ItemStack(Material.IRON_BOOTS), 15));

        // Armor - Diamond
        lootPool.add(new LootItem(new ItemStack(Material.DIAMOND_HELMET), 5));
        lootPool.add(new LootItem(new ItemStack(Material.DIAMOND_CHESTPLATE), 5));
        lootPool.add(new LootItem(new ItemStack(Material.DIAMOND_LEGGINGS), 5));
        lootPool.add(new LootItem(new ItemStack(Material.DIAMOND_BOOTS), 5));

        // Armor - Netherite
        lootPool.add(new LootItem(new ItemStack(Material.NETHERITE_HELMET), 2));
        lootPool.add(new LootItem(new ItemStack(Material.NETHERITE_CHESTPLATE), 2));
        lootPool.add(new LootItem(new ItemStack(Material.NETHERITE_LEGGINGS), 2));
        lootPool.add(new LootItem(new ItemStack(Material.NETHERITE_BOOTS), 2));

        // Consumables
        lootPool.add(new LootItem(new ItemStack(Material.GOLDEN_APPLE), 5)); // Slim chance
        lootPool.add(new LootItem(new ItemStack(Material.ENCHANTED_GOLDEN_APPLE), 1)); // Very rare
    }

    private ItemStack getWeightedRandomDrop() {
        int totalWeight = lootPool.stream().mapToInt(LootItem::getWeight).sum();
        int random = new Random().nextInt(totalWeight);
        int current = 0;

        for (LootItem item : lootPool) {
            current += item.getWeight();
            if (random < current) return item.getItem();
        }
        return new ItemStack(Material.STONE); // fallback
    }
}