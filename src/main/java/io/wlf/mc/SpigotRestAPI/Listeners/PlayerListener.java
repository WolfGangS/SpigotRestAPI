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
    private CommandQueueService queueService;

    public PlayerListener(JavaPlugin plugin, CommandQueueService queueService) {
        this.plugin = plugin;
        this.queueService = queueService;

        this.registerEvents();
    }

    private void registerEvents() {
        this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        this.queueService.handleEvent(wCondition.PLAYER_JOIN,event.getPlayer().getUniqueId().toString());
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event){
        this.queueService.handleEvent(wCondition.PLAYER_DEATH,event.getEntity().getUniqueId().toString());
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event){
        String playerID = event.getPlayer().getUniqueId().toString();
        this.plugin.getServer().getScheduler().scheduleSyncDelayedTask(
                this.plugin,
                () -> {
                    this.queueService.handleEvent(wCondition.PLAYER_RESPAWN,playerID);
                },
                20L
        );
    }
}
