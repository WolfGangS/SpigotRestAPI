package io.wlf.mc.SpigotRestAPI;

//import com.sun.net.httpserver.HttpServer;

import io.wlf.mc.SpigotRestAPI.Controllers.CommandController;
import io.wlf.mc.SpigotRestAPI.Controllers.PlayerController;
import io.wlf.mc.SpigotRestAPI.Listeners.PlayerListener;
import io.wlf.mc.SpigotRestAPI.Models.HttpEvent;
import io.wlf.mc.SpigotRestAPI.Models.HttpEventType;
import io.wlf.mc.SpigotRestAPI.Services.CommandService;
import io.wlf.mc.SpigotRestAPI.Services.HttpEventService;
import io.wlf.mc.SpigotRestAPI.Services.PlayerService;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import static spark.Spark.*;

public class SpigotRestAPI extends JavaPlugin {

    //private HttpServer httpServer;

    private PlayerController playerController;
    private CommandController commandController;

    private PlayerListener playerListener;

    private HttpEventService httpEventService;

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
        if(this.config.getBoolean("httpEvents.enabled", false)) {
            this.httpEventService.fire(HttpEventType.server, HttpEvent.online, null);
        }
    }

    private void setupConfig() {
        this.config = this.getConfig();
        this.config.addDefault("port", 8765);
        this.config.addDefault("authentication", false);
        //this.config.set("tokens.example-token-2","remove me 2");
        this.saveConfig();
    }

    @Override
    public void onDisable() {

        stop();

        getLogger().info("Disabled !!!");
    }

    private void initServices() {
        if(this.config.getBoolean("httpEvents.enabled", false)) {
            getLogger().info("Setting up httpEvents");
            this.httpEventService = new HttpEventService(
                    this,
                    this.config.getBoolean("httpEvents.secure", false),
                    this.config.getString("httpEvents.domain", ""),
                    this.config.getInt("httpsEvents.port", 8000),
                    this.config.getConfigurationSection("httpEvents.endpoints")
            );
        } else {
            getLogger().info("httpEvents Disabled");
        }
    }

    private void initControllers() {
        this.playerController = new PlayerController(new PlayerService(this));

        this.commandController = new CommandController(new CommandService(this));

    }

    private void initListeners() {
        if(this.config.getBoolean("httpEvents.enabled", false)) {
            this.playerListener = new PlayerListener(this, this.httpEventService);
        }
    }

    private boolean authenticateToken(String token) {
        if (token != null) {

            if (token.equals("example-token")) {
                return false;
            }

            String user = this.config.getString("tokens." + token);

            return user != null;
        }
        return false;
    }
}
