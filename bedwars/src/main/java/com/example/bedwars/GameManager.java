package com.example.bedwars;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import java.util.HashMap;
import java.util.Map;

public class GameManager {
    private final Map<GameTeam, Team> scoreboardTeams = new HashMap<>();
    private final Scoreboard scoreboard;
    private final MapManager mapManager;

    public GameManager(MapManager mapManager) {
        this.mapManager = mapManager;
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        this.scoreboard = manager.getNewScoreboard();
        for (GameTeam team : GameTeam.values()) {
            Team sbTeam = scoreboard.registerNewTeam(team.name());
            sbTeam.setColor(team.getColor());
            sbTeam.setDisplayName(team.getDisplayName());
            scoreboardTeams.put(team, sbTeam);
        }
    }

    public void addPlayer(Player player, GameTeam team) {
        scoreboardTeams.get(team).addEntry(player.getName());
        player.setScoreboard(scoreboard);
    }

    public void removePlayer(Player player) {
        for (Team team : scoreboardTeams.values()) {
            team.removeEntry(player.getName());
        }
        player.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
    }

    public void loadMap(String name, World world, Location pasteLocation) {
        try {
            mapManager.loadMap(name, world, pasteLocation);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Additional game logic like bed destruction, shops, etc. would go here
}
