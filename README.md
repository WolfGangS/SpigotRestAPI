# SpigotRestAPI

A simple http api for spigot minecraft servers.

The enpoints are as follows

Data Endpoints
--

| endpoint | method | arguments | description |
| -------- | ------ | --------- | ----------- |
| /players | GET | n/a | get a list of online players and their basic info |
| /players/:id | GET | :id  =  a player uuid | get info for a specific player uuid |
| /players/:id/inventory | GET | :id  =  a player uuid | get an online players inventory |
| /players/:id/enderchest | GET | :id  =  a player uuid | get an online players enderchest inventory |


Command Endpoints
--
<table>
  <tr>
    <th>Endpoint</th>
    <th>Method</th>
    <th colspan="2">Arguments</th>
    <th>Description</th>
  </tr>
  <tr>
    <td>/commands/console</td>
    <td>POST</td>
    <td>body</td>
    <td>command text (e.g "give player diamond 1")</td>
    <td>run a command from the console immediatly</td>
  </tr>
  <tr>
    <td>/commands/player/:id</td>
    <td>POST</td>
    <td>body</td>
    <td>command text (e.g "give player diamond 1")</td>
    <td>run a command as player matching the uuid, if the player is not online the command will be queued till they join the server</td>
  </tr>
  <tr>
    <td rowspan="2">/commands/console/queue/:for</td>
    <td rowspan="2">POST</td>
    <td>body</td>
    <td>the command to run (e.g "give player diamond 1")</td>
    <td rowspan="2">run a command from the console immediatly</td>
  </tr>
  <tr>
    <td>:for</td>
    <td>the scenario to wait for</td>
  </tr>
  <tr>
    <td rowspan="3">/commands/console/queue/:for/:option</td>
    <td rowspan="3">POST</td>
    <td>body</td>
    <td>the command to run (e.g "give player diamond 1")</td>
    <td rowspan="3">run a command from the console immediatly</td>
  </tr>
  <tr>
    <td>:for</td>
    <td>the scenario to wait for</td>
  </tr>
  <tr>
    <td>:option</td>
    <td>data for the scenario check</td>
  </tr>
  <tr>
    <td>/commands/console</td>
    <td>POST</td>
    <td>body</td>
    <td>the command to run (e.g "give player diamond 1")</td>
    <td>run a command from the console immediatly</td>
  </tr>
  <tr>
    <td>/commands/console</td>
    <td>POST</td>
    <td>body</td>
    <td>the command to run (e.g "give player diamond 1")</td>
    <td>run a command from the console immediatly</td>
  </tr>
  <tr>
    <td>/commands/console</td>
    <td>POST</td>
    <td>body</td>
    <td>the command to run (e.g "give player diamond 1")</td>
    <td>run a command from the console immediatly</td>
  </tr>
</table>
