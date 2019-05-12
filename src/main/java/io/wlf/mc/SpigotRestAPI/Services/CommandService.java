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

    private CommandQueueService queueService;

    public CommandService(JavaPlugin plugin, CommandQueueService queueService) {
        this.plugin = plugin;
        this.queueService = queueService;
        this.executor = new CommandExecutionServiceSync(this.plugin);
    }

    public wResponse runCommand(String command) {
        command = command.trim();
        executor.consoleExecute(command);
        return success();
    }

    public wResponse queueCommand(String command, String scenario) {
        return success(this.queueCommand(command, scenario, null));
    }

    public wResponse queueCommand(String command, String _scenario, String data) {
        wScenario scenario = wScenario.valueOf(_scenario.toUpperCase());
        wCondition condition = new wCondition(scenario, data);
        String key = this.queueService.queueCommand(command, condition);
        return success(key);
    }

    public wResponse runCommandAsPlayer(String command, String id) {
        id = id.trim().toLowerCase();
        Player p = getPlayerByID(id);
        if (p != null) {
            executor.playerExecute(command,id);
            return success();
        } else {
            return success(this.queueService.queueCommand(command, new wCondition(wScenario.PLAYER_JOIN, id), id));
        }
    }

    public wResponse cancelQueuedCommand(String key) {
        if (this.queueService.removeCommand(key)) {
            return success();
        }
        return failure();
    }

    private Player getPlayerByID(String id) {
        return plugin.getServer().getPlayer(
                UUID.fromString(id.trim())
        );
    }

    public wResponse getCommandQueue() {
        return success(this.queueService.getQueue());
    }

    public wResponse editQueuedCommand(String key, String command) {
        return failIfNull(this.queueService.editQueuedCommand(key, command));
    }
}
