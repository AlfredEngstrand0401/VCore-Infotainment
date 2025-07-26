package com.example.bedwars;

import org.bukkit.ChatColor;

public enum GameTeam {
    RED(ChatColor.RED, "Red"),
    BLUE(ChatColor.BLUE, "Blue"),
    GREEN(ChatColor.GREEN, "Green"),
    YELLOW(ChatColor.YELLOW, "Yellow");

    private final ChatColor color;
    private final String displayName;

    GameTeam(ChatColor color, String displayName) {
        this.color = color;
        this.displayName = displayName;
    }

    public ChatColor getColor() {
        return color;
    }

    public String getDisplayName() {
        return displayName;
    }
}
