package io.wlf.mc.SpigotRestAPI;

import com.sun.net.httpserver.HttpServer;
import io.wlf.mc.SpigotRestAPI.Controllers.CommandController;
import io.wlf.mc.SpigotRestAPI.Controllers.PlayerController;
import io.wlf.mc.SpigotRestAPI.Listeners.PlayerListener;
import io.wlf.mc.SpigotRestAPI.Services.CommandQueueService;
import io.wlf.mc.SpigotRestAPI.Services.CommandService;
import io.wlf.mc.SpigotRestAPI.Services.PlayerService;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import static spark.Spark.*;

public class SpigotRestAPI extends JavaPlugin {

    private HttpServer httpServer;

    private PlayerController playerController;
    private CommandController commandController;

    private CommandQueueService queueService;

    private PlayerListener playerListener;

    private FileConfiguration config;

    private ConfigurationSection tokens;

    @Override
    public void onEnable() {
        getLogger().info("Enabled !!!");

        this.saveDefaultConfig();

        this.setupConfig();

        //init();
        getLogger().info("Listening on port: " + config.getInt("port"));
        port(config.getInt("port"));

        if (config.getBoolean("authentication")) {
            getLogger().info("Authentication: On");
            before((req, res) -> {
                if (!this.authenticateToken(req.headers("token"))) {
                    halt(401, "Denied");
                }
            });
        } else {
            getLogger().info("Authentication: Off");
        }


        before((req, res) -> res.type("application/json"));

        exception(Exception.class, (e, req, res) -> {
            res.status(500);

            StringBuilder str = new StringBuilder();
            for (StackTraceElement elem : e.getStackTrace()) {
                str.append("\n")
                        .append(elem.getFileName())
                        .append(" : ")
                        .append(elem.getLineNumber());
            }
            res.body(ExceptionUtils.getStackTrace(e) + "\n\n" + e.getClass().getName() + "\n" + e.getMessage() + "\n\n" + str.toString());
        });

        initServices();
        initControllers();
        initListeners();
    }

    private void setupConfig() {
        this.config = this.getConfig();
        this.config.addDefault("port", 8765);
        this.config.addDefault("authentication", false);
        this.config.set("tokens.example-token-2","remove me 2");
        this.saveConfig();
    }

    @Override
    public void onDisable() {

        stop();

        getLogger().info("Disabled !!!");
    }

    private void initServices() {
        this.queueService = new CommandQueueService(this);
    }

    private void initControllers() {
        playerController = new PlayerController(new PlayerService(this));

        commandController = new CommandController(new CommandService(this, this.queueService));

    }

    private void initListeners() {
        playerListener = new PlayerListener(this, this.queueService);
    }

    private boolean authenticateToken(String token) {
        if(token != null) {

            if (token.equals("example-token")) {
                return false;
            }

            String user = this.config.getString("tokens." + token);

            return user != null;
        }
        return false;
    }
}
