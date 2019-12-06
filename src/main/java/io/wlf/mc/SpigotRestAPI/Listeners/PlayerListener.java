package io.wlf.mc.SpigotRestAPI.Listeners;

import io.wlf.mc.SpigotRestAPI.Models.wCondition;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerListener implements Listener {
    private JavaPlugin plugin;

    public PlayerListener(JavaPlugin plugin) {
        this.plugin = plugin;

        this.registerEvents();
    }

    private void registerEvents() {
        this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event){

    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event){
    }
}
