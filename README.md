# SpigotRestAPI

A simple http api for spigot minecraft servers.

This plugin currently does not save its command queue between restarts, (this is coming)

The plugin is meant to be a simple and easy to maintain as possible, as such it implements very little.

It mostly allows you to get player info and run commands via a http request. (thus allowing it to be used with MOST other plugins)

There is a very basic token based authentication system, 
#### BUT THE PLUGIN IS NOT INTENDED TO BE SECURE, IT SHOULD REALLY BE RUN WITHOUT BEING EXPOSED TO THE INTERNET DIRECTLY 

# Response

All responses are formatted like this.

``` js
{
    "status": "Okay" / "Fail",
    "data" : <response object>
}
```

# Data Endpoints

### /players `GET`
Get a list of online players and their basic info
<br>
`response = array of player objects`

### /players/:id `GET`
Get info for a specific player uuid
<br>
`:id =  a player uuid`
<br>
`response = a player object`
<br>
### /players/:id/inventory `GET`
Get an online players inventory
<br>
`:id  =  a player uuid`
<br>
`response = a player inventory`
<br>
### /players/:id/enderchest `GET`
get an online players enderchest inventory
<br>
`:id  =  a player uuid`
<br>
`response = a chest inventory`

# Command Endpoints

### /commands/console `POST`
Run a command immediately as console 
<br>
`body = command sting ( e.g "give player diamond 1" )`
<br>
`response = empty`
<br>
### /commands/player/:id `POST`
Run a command as player matching the uuid, if the player is not online the command will be queued till they join the server
<br>
`body = command sting ( e.g "give player diamond 1" )`
<br>
`:id = player uuid`
<br>
`response = empty or a key string for the queued command`
<br>
### /commands/console/queue/:scenario `POST`
Queue a command to run on the console at a trigger
<br>
`body = command sting ( e.g "give player diamond 1" )`
<br>
`:scenario = the scenario to run the command at (see below)`
<br>
`response = a key string for the queued command`
<br>
### /commands/console/queue/:scenario/:data `POST`
Queue a command to run on the console at a trigger with data for that trigger
<br>
`body = command sting ( e.g "give player diamond 1" )`
<br>
`:scenario = the scenario to run the command at (see below)`
<br>
`:data = the scenario to run the command at (see below)`
<br>
`response = a key string for the queued command`
<br>
### /commands/console/edit/:key `POST`
Edit a queued command
<br>
`body = command sting ( e.g "give player diamond 1" )`
<br>
`:key = the key of the queued command`
<br>
`response = edited command object`
<br>
### /commands/console/list `GET`
Get a list of all queued commands
<br>
`response = a list of command objects`
<br>
### /commands/console/cancel/:key `GET`
Cancel a queued command
<br>
`:key = the key of the queued command`
<br>
`response = empty`
<br>

