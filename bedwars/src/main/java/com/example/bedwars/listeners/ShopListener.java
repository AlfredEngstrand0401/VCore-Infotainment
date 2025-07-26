package com.example.bedwars.listeners;

import com.example.bedwars.ShopManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ShopListener implements Listener {

    private final ShopManager shopManager;

    public ShopListener(ShopManager shopManager) {
        this.shopManager = shopManager;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        shopManager.handleClick(event);
    }
}
