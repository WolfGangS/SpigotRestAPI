# SpigotRestAPI

A simple http api for spigot minecraft servers.

The enpoints are as follows

| endpoint | method | arguments | description |
| -------- | ------ | --------- | ----------- |
| /players | GET | n/a | get a list of online players and their basic info |
| /players/:id | GET | :id  =  a player uuid | get info for a specific player uuid |
| /players/:id/inventory | GET | :id  =  a player uuid | get an online players inventory |
| /players/:id/enderchest | GET | :id  =  a player uuid | get an online players enderchest inventory |
|||||
