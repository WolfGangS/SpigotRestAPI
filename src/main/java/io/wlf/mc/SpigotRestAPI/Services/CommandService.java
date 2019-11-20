package io.wlf.mc.SpigotRestAPI.Services;

import io.wlf.mc.SpigotRestAPI.Models.wCondition;
import io.wlf.mc.SpigotRestAPI.Models.wResponse;
import io.wlf.mc.SpigotRestAPI.Models.wScenario;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public class CommandService extends WebResponseService {

    private JavaPlugin plugin;

    private CommandExecutionService executor;

    public CommandService(JavaPlugin plugin) {
        this.plugin = plugin;
        this.executor = new CommandExecutionServiceSync(this.plugin);
    }

    public wResponse runCommand(String command) {
        command = command.trim();
        executor.consoleExecute(command);
        return success();
    }

    public wResponse runCommandAsPlayer(String command, String id) {
        id = id.trim().toLowerCase();
        Player p = getPlayerByID(id);
        if (p != null) {
            executor.playerExecute(command,id);
            return success();
        }
        return failure("player not online");
    }

    private Player getPlayerByID(String id) {
        return plugin.getServer().getPlayer(
                UUID.fromString(id.trim())
        );
    }
}
