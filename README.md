# SpigotRestAPI

A simple http api for spigot minecraft servers.

This plugin currently does not save its command queue between restarts, (this is coming)

The plugin is meant to be a simple and easy to maintain as possible, as such it implements very little.

It mostly allows you to get player info and run commands via a http request. (thus allowing it to be used with MOST other plugins)

# Data Endpoints

| endpoint | method | arguments | description |
| -------- | ------ | --------- | ----------- |
| /players | GET | n/a | get a list of online players and their basic info |
| /players/:id | GET | :id  =  a player uuid | get info for a specific player uuid |
| /players/:id/inventory | GET | :id  =  a player uuid | get an online players inventory |
| /players/:id/enderchest | GET | :id  =  a player uuid | get an online players enderchest inventory |


# Command Endpoints

### /commands/console `POST`
Run a command immediately as console 
<br>
`body = command sting ( e.g "give player diamond 1" )`
<br>
<br>
<br>
### /commands/player/:id `POST`
Run a command as player matching the uuid, if the player is not online the command will be queued till they join the server
<br>
`body = command sting ( e.g "give player diamond 1" )`
<br>
`:id = player uuid`
<br>
<br>
<br>
### /commands/console/queue/:for `POST`
Queue a command to run on the console at a trigger
<br>
`body = command sting ( e.g "give player diamond 1" )`
<br>
`:for = the scenario to run the command at (see below)`
<br>
<br>
<br>
### /commands/console/queue/:for/:option `POST`
Queue a command to run on the console at a trigger with data for that trigger
<br>
`body = command sting ( e.g "give player diamond 1" )`
<br>
`:for = the scenario to run the command at (see below)`
<br>
`:for = the scenario to run the command at (see below)`
<br>
<br>
<br>
