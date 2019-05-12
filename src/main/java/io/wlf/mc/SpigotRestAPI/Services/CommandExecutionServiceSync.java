package io.wlf.mc.SpigotRestAPI.Services;

import org.bukkit.plugin.java.JavaPlugin;

public class CommandExecutionServiceSync extends CommandExecutionService {
    public CommandExecutionServiceSync(JavaPlugin plugin) {
        super(plugin);
    }

    @Override
    public void consoleExecute(String command) {
        this.plugin.getServer().getScheduler().callSyncMethod(
                this.plugin,
                () -> {
                    super.consoleExecute(command);
                    return true;
                }
        );
    }

    @Override
    public void playerExecute(String command, String id) {
        this.plugin.getServer().getScheduler().callSyncMethod(
                this.plugin,
                () -> {
                    super.playerExecute(command, id);
                    return true;
                }
        );
    }
}
