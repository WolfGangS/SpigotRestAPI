package io.wlf.mc.SpigotRestAPI.Controllers;

import io.wlf.mc.SpigotRestAPI.Services.PlayerService;

import static io.wlf.mc.SpigotRestAPI.Util.Json.*;
import static spark.Spark.*;

public class PlayerController {

    public PlayerController(final PlayerService playerService) {
        get("/players", (req,res) -> playerService.getAllPlayers() , json());
        get("/players/:id", (req,res) -> playerService.getPlayer(req.params(":id")) , json());
        get("/players/:id/inventory", (req,res) -> playerService.getPlayerInventory(req.params(":id")) , json());
        get("/players/:id/enderchest", (req,res) -> playerService.getPlayerEnderChest(req.params(":id")) , json());
    }
}
