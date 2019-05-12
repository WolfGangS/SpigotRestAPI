package io.wlf.mc.SpigotRestAPI.Services;

import io.wlf.mc.SpigotRestAPI.Models.wQueuedCommand;
import io.wlf.mc.SpigotRestAPI.Models.wCondition;
import io.wlf.mc.SpigotRestAPI.Models.wScenario;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class CommandQueueService {

    private JavaPlugin plugin;

    private CommandExecutionService executor;

    private HashMap<String, wQueuedCommand> commandQueue;

    public CommandQueueService(JavaPlugin plugin) {
        this.plugin = plugin;
        commandQueue = new HashMap<>();
        this.executor = new CommandExecutionService(this.plugin);
    }

    public HashMap<String, wQueuedCommand> getQueue() {
        return commandQueue;
    }

    public void handleEvent(String _scenario, String data) {
        List<String> run = new ArrayList<>();
        for (Map.Entry<String, wQueuedCommand> set : this.commandQueue.entrySet()) {
            String key = set.getKey();
            wQueuedCommand qCommand = set.getValue();
            wScenario scenario = wScenario.valueOf(_scenario.toUpperCase());
            if (qCommand.condition.isMet(scenario, data)) {
                this.executeCommand(key);
                run.add(key);
            }
        }
        this.removeCommands(run.toArray(new String[0]));
    }

    public void executeCommand(String key) {
        wQueuedCommand qCommand = this.commandQueue.get(key);
        if (qCommand != null) {
            String command = qCommand.command;
            if (qCommand.executeAs == null) {
                executor.consoleExecute(command);
            } else {
                executor.playerExecute(command, qCommand.executeAs);
            }
        }
    }

    public String queueCommand(String command, wCondition condition) {
        return this.queueCommand(command, condition, null);
    }

    public String queueCommand(String command, wCondition condition, String executeAs) {
        wQueuedCommand qCommand = new wQueuedCommand(command, condition);
        String key = this.getUniqueKey();
        this.commandQueue.put(key, qCommand);
        return key;
    }

    public void removeCommands(String[] keys) {
        for (String key : keys) {
            this.removeCommand(key);
        }
    }

    public boolean removeCommand(String key) {
        key = cleanKey(key);
        return this.commandQueue.remove(key) != null;
    }

    private String getUniqueKey() {
        String key = generateKey();
        while (this.commandQueue.containsKey(key)) {
            key = generateKey();
        }
        return key;
    }

    private String generateKey() {
        return cleanKey(UUID.randomUUID().toString());
    }

    private String cleanKey(String key) {
        return key.trim().toLowerCase();
    }

    public wQueuedCommand editQueuedCommand(String key, String command) {
        key = cleanKey(key);
        wQueuedCommand qCommand = this.commandQueue.get(key);
        if (qCommand != null) {
            qCommand.command = command;
            return qCommand;
        }
        return null;
    }

    private Player getPlayerByID(String id) {
        return plugin.getServer().getPlayer(
                UUID.fromString(id.trim())
        );
    }

}
