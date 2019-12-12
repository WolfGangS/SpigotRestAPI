package io.wlf.mc.SpigotRestAPI.Services;

import org.apache.commons.lang.text.StrSubstitutor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public class CommandExecutionService {

    protected JavaPlugin plugin;

    public CommandExecutionService(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void consoleExecute(String command) {
        command = this.subCommandString(command);
        plugin.getServer().dispatchCommand(this.plugin.getServer().getConsoleSender(), command);
    }

    public void playerExecute(String command, String id) {
        Player p = getPlayerByID(id);
        if (p != null) {
            HashMap<String, String> values = new HashMap<>();
            values.put("player_name", p.getName());
            command = subCommandString(command, values);
            p.performCommand(command);
        }
    }

    private String subCommandString(String command) {
        return this.subCommandString(command, null);
    }

    private String subCommandString(String command, HashMap<String, String> values) {
        values = addGlobalSubValues(values);

        StrSubstitutor sub = new StrSubstitutor(values);

        return sub.replace(command);
    }

    private HashMap<String, String> addGlobalSubValues(HashMap<String, String> values) {
        if (values == null) {
            values = new HashMap<>();
        }
        return values;
    }

    private Player getPlayerByID(String id) {
        return plugin.getServer().getPlayer(
                UUID.fromString(id.trim())
        );
    }
}
