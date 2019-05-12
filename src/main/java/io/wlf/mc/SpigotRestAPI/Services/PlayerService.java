package io.wlf.mc.SpigotRestAPI.Services;

import io.wlf.mc.SpigotRestAPI.Models.wInventory;
import io.wlf.mc.SpigotRestAPI.Models.wPlayer;
import io.wlf.mc.SpigotRestAPI.Models.wPlayerInventory;
import io.wlf.mc.SpigotRestAPI.Models.wResponse;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerService extends WebResponseService {

    private JavaPlugin plugin;

    public PlayerService(JavaPlugin plugin) {
        this.plugin = plugin;
    }


    public wResponse getAllPlayers() {
        List<wPlayer> players = new ArrayList<>();
        for (Player p : plugin.getServer().getOnlinePlayers()) {
            players.add(new wPlayer(p));
        }
        return success(players.toArray(new wPlayer[0]));
    }

    public wResponse getPlayer(String id) {
        Player p = getPlayerByID(id);
        if (p != null) {
            return success(new wPlayer(getPlayerByID(id)));
        }
        return failure("not found");
    }

    public wResponse getPlayerInventory(String id) {
        Player p = getPlayerByID(id);
        if (p != null) {
            return success(new wPlayerInventory(p.getInventory()));
        }
        return failure("not found");
    }

    public wResponse getPlayerEnderChest(String id) {
        Player p = getPlayerByID(id);
        if(p != null) {
            return success(new wInventory(p.getEnderChest()));
        }
        return failure();
    }

    private Player getPlayerByID(String id) {
        return plugin.getServer().getPlayer(
                UUID.fromString(id.trim())
        );
    }
}
