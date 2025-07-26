package com.example.bedwars;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.session.ClipboardHolder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MapManager {

    private final Map<String, String> mapFiles = new HashMap<>();

    public void registerMap(String name, String schematicFile) {
        mapFiles.put(name, schematicFile);
    }

    public void loadMap(String name, World world, Location pasteLocation) throws IOException, WorldEditException {
        String schematicPath = mapFiles.get(name);
        if (schematicPath == null) {
            Bukkit.getLogger().warning("Unknown map " + name);
            return;
        }
        File schematic = new File(BedWarsPlugin.getInstance().getDataFolder(), schematicPath);
        ClipboardFormat format = ClipboardFormats.findByFile(schematic);
        if (format == null) {
            Bukkit.getLogger().warning("Invalid schematic for map " + name);
            return;
        }
        try (ClipboardReader reader = format.getReader(new FileInputStream(schematic))) {
            Clipboard clipboard = reader.read();
            try (EditSession editSession = WorldEdit.getInstance().newEditSession(BukkitAdapter.adapt(world))) {
                ClipboardHolder holder = new ClipboardHolder(clipboard);
                holder.createPaste(editSession)
                        .to(BlockVector3.at(pasteLocation.getBlockX(), pasteLocation.getBlockY(), pasteLocation.getBlockZ()))
                        .ignoreAirBlocks(false)
                        .build()
                        .complete();
            }
        }
    }
}
