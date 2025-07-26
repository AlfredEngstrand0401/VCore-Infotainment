package com.example.bedwars.commands;

import com.example.bedwars.GameManager;
import com.example.bedwars.GameTeam;
import com.example.bedwars.ShopManager;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.Location;

public class BedWarsCommand implements CommandExecutor {

    private final GameManager gameManager;
    private final ShopManager shopManager;

    public BedWarsCommand(GameManager gameManager, ShopManager shopManager) {
        this.gameManager = gameManager;
        this.shopManager = shopManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Player only command");
            return true;
        }
        Player player = (Player) sender;
        if (args.length == 0) {
            player.sendMessage("/bedwars join <team> | shop | loadmap <name>");
            return true;
        }
        switch (args[0].toLowerCase()) {
            case "join":
                if (args.length >= 2) {
                    try {
                        GameTeam team = GameTeam.valueOf(args[1].toUpperCase());
                        gameManager.addPlayer(player, team);
                        player.sendMessage("Joined team " + team.getDisplayName());
                    } catch (IllegalArgumentException e) {
                        player.sendMessage("Unknown team");
                    }
                }
                break;
            case "shop":
                shopManager.openShop(player);
                break;
            case "loadmap":
                if (args.length >= 2) {
                    World world = player.getWorld();
                    Location loc = player.getLocation();
                    gameManager.loadMap(args[1], world, loc);
                }
                break;
            default:
                player.sendMessage("Unknown command");
        }
        return true;
    }
}
