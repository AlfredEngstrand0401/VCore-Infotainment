package com.example.bedwars;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.Map;

public class ShopManager {
    private final Map<Integer, ShopItem> items = new HashMap<>();
    private final Inventory shopInventory;

    public ShopManager() {
        this.shopInventory = Bukkit.createInventory(null, 9, "Shop");
    }

    public void registerItem(int slot, ShopItem item) {
        items.put(slot, item);
        ItemStack stack = new ItemStack(item.getDisplayItem());
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(item.getName());
        stack.setItemMeta(meta);
        shopInventory.setItem(slot, stack);
    }

    public void openShop(Player player) {
        player.openInventory(shopInventory);
    }

    public void handleClick(InventoryClickEvent event) {
        if (!event.getInventory().equals(shopInventory)) return;
        event.setCancelled(true);
        int slot = event.getSlot();
        ShopItem item = items.get(slot);
        if (item != null && event.getWhoClicked() instanceof Player) {
            Player player = (Player) event.getWhoClicked();
            item.give(player);
        }
    }

    public interface ShopItem {
        String getName();
        Material getDisplayItem();
        void give(Player player);
    }
}
