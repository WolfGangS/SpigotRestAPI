package io.wlf.mc.SpigotRestAPI.Models;

public class wCondition {

    public static final String PLAYER_JOIN = "player_join";
    public static final String PLAYER_DEATH = "player_death";
    public static final String PLAYER_RESPAWN = "player_respawn";
    public static final String PLAYER_SPAWN = "player_spawn";

    public static final String SERVER_START = "server_start";

    public wScenario scenario;
    public String data;

    public wCondition(wScenario scenario, String data) {
        this.scenario = scenario;
        this.data = data;
    }

    public boolean isMet(wScenario scenario, String data) {
        if (scenario == this.scenario) {
            if (this.data == null) {
                return true;
            } else {
                return data.equals(this.data);
            }
        }
        return false;
    }
}
