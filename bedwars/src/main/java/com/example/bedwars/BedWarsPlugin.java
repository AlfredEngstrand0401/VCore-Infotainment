package com.example.bedwars;

import com.example.bedwars.listeners.ShopListener;
import com.example.bedwars.commands.BedWarsCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class BedWarsPlugin extends JavaPlugin {

    private static BedWarsPlugin instance;

    private MapManager mapManager;
    private GameManager gameManager;
    private ShopManager shopManager;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        this.mapManager = new MapManager();
        this.gameManager = new GameManager(mapManager);
        this.shopManager = new ShopManager();

        getServer().getPluginManager().registerEvents(new ShopListener(shopManager), this);
        getCommand("bedwars").setExecutor(new BedWarsCommand(gameManager, shopManager));

        Bukkit.getConsoleSender().sendMessage("[BedWars] Plugin enabled");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("[BedWars] Plugin disabled");
        // TODO: clean up running games
    }

    public static BedWarsPlugin getInstance() {
        return instance;
    }
}
