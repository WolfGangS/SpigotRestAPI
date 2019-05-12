package io.wlf.mc.SpigotRestAPI.Controllers;

import io.wlf.mc.SpigotRestAPI.Services.CommandService;

import static io.wlf.mc.SpigotRestAPI.Util.Json.json;
import static spark.Spark.*;

public class CommandController {
    public CommandController(CommandService commandService) {
        post("/commands/console", (req, res) -> commandService.runCommand(req.body()), json());
        post("/commands/player/:id", (req, res) -> commandService.runCommandAsPlayer(req.body(), req.params(":id")), json());
        post("/commands/console/queue/:scenario", (req, res) -> commandService.queueCommand(req.body(), req.params(":scenario")), json());
        post("/commands/console/queue/:scenario/:data", (req, res) -> commandService.queueCommand(req.body(), req.params(":scenario"), req.params(":data")), json());
        post("/commands/console/edit/:key", (req, res) -> commandService.editQueuedCommand(req.params(":key"), req.body()), json());
        get("/commands/console/list", (req, res) -> commandService.getCommandQueue(), json());
        delete("/commands/console/cancel/:key", (req, res) -> commandService.cancelQueuedCommand(req.params(":key")), json());
    }
}
