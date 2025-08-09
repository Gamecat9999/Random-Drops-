package me.james.drops;

import org.bukkit.inventory.ItemStack;

public class LootItem {
    private final ItemStack item;
    private final int weight;

    public LootItem(ItemStack item, int weight) {
        this.item = item;
        this.weight = weight;
    }

    public ItemStack getItem() {
        return item;
    }

    public int getWeight() {
        return weight;
    }
}