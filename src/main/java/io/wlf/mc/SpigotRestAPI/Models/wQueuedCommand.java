package io.wlf.mc.SpigotRestAPI.Models;

public class wQueuedCommand {
    public String command;
    public int time;
    public String executeAs;
    public wCondition condition;

    public wQueuedCommand(String command, wCondition condition){
        this.command = command;
        this.time = (int) (System.currentTimeMillis() / 1000L);
        this.condition = condition;
    }
}
