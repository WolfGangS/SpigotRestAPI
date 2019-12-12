# SpigotRestAPI

A simple http api for spigot minecraft servers.

This plugin currently does not save its command queue between restarts, (this is coming)

The plugin is meant to be a simple and easy to maintain as possible, as such it implements very little.

I hope for it to provide the bare minimum that is needed to implement most kinds of systems

It mostly allows you to get player info and run commands via a http request. (thus allowing it to be used with MOST other plugins)

There is a very basic token based authentication system, 
#### BUT THE PLUGIN IS NOT INTENDED TO BE SECURE, IT SHOULD REALLY BE RUN WITHOUT BEING EXPOSED TO THE INTERNET DIRECTLY 

You can load the postman file for examples

# Planned Features
 - Offline players
 - returning or storing results of commands for later retrieval

# Config

```yaml
port: 8765                 # the port the http server should listen on
authentication: false      # whether to use the simple token authentication system
tokens:
  24f8ea23-5882-6a6d-8217-a21e812bf9cd: "name"    # the key on the left should be passed as a header named "token" the value on the right does not matter but should be useful for labeling them
httpEvents:
  enabled: false           # Enable / Disable the event requests
  secure: true             # should the web request use http or https (default) 
  domain: localhost        # the domain the request should goto
  port: 8000               # the port
  endpoints:               # this lists the endpoints you want the events to use.
    server:
      online: /server/online
    player:
      online: /player/join
      offline: /player/leave
#      death: /player/death   # these are disabled by default
#      spawn: /player/spawn   # uncomment to enabled
```

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

# Http Events

The plugin supports a simple event system that will fire a http request to a given domain and path on certain events.

### Events

 - server
    - online
 - player
    - online (join)
    - offline (leave)
    - death
    - respawn
