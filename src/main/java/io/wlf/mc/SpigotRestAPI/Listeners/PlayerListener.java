package io.wlf.mc.SpigotRestAPI.Listeners;

import io.wlf.mc.SpigotRestAPI.Models.HttpEvent;
import io.wlf.mc.SpigotRestAPI.Models.HttpEventType;
import io.wlf.mc.SpigotRestAPI.Models.wCondition;
import io.wlf.mc.SpigotRestAPI.Models.wPlayer;
import io.wlf.mc.SpigotRestAPI.Services.HttpEventService;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerListener implements Listener {
    private JavaPlugin plugin;
    private HttpEventService httpEventService;

    public PlayerListener(JavaPlugin plugin, HttpEventService httpEventService) {
        this.plugin = plugin;
        this.httpEventService = httpEventService;

        this.registerEvents();
    }

    private void registerEvents() {
        this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        this.httpEventService.fire(HttpEventType.player, HttpEvent.offline, new wPlayer(event.getPlayer()));
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        this.httpEventService.fire(HttpEventType.player, HttpEvent.online, new wPlayer(event.getPlayer()));
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event){
        this.httpEventService.fire(HttpEventType.player, HttpEvent.death, new wPlayer(event.getEntity()));
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event){
        this.httpEventService.fire(HttpEventType.player, HttpEvent.spawn, new wPlayer(event.getPlayer()));
    }
}
