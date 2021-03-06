# Football Manager

With this program you can create new players, football teams, add new players to the team and move players from one team to another

### Postman commands

###### Inject Data:
- Get: localhost:8080/inject

###### Player:
- Post: localhost:8080/players/add    
body: {"firstName":"Ромелу", "lastName":"Лукаку","age":26, "experience":4}
- Post: localhost:8080/players/add_all    
body: [{"firstName":"Хаким", "lastName":"Зиеш","age":24, "experience":3},{"firstName":"Эдуар", "lastName":"Менди","age":14, "experience":2}]
- Get: localhost:8080/players/get/{id}
- Get: localhost:8080/players/get_all/{teamId}
- Put: localhost:8080/players/update/{id}   
body {"firstName":"qwerty", "lastName":"","age":14, "experience":2}
- Delete: localhost:8080/players/delete/{id}

###### Teams
- Post: localhost:8080/teams/add   
body: {"name":"Арсенал", "transferCosts":5,"playersId":[1, 2], "budget":10000000, "country":"Великобритания", "town":"Лондон Боро оф Ислингтон, Лондон"}
- Post: localhost:8080/teams//add_all  
body: [{"name":"Челси", "transferCosts":8,"playersId":[5, 6], "budget":20000000, "country":"Великобритания", "town":" Лондон"}, {"name":"Арсенал", "transferCosts":5,"playersId":[1, 2], "budget":10000000, "country":"Великобритания", "town":"Лондон Боро оф Ислингтон, Лондон"}]
- Get: localhost:8080/teams/get/{id}
- Get: localhost:8080/teams/get_all
- Get: localhost:8080/teams/transfer  
params: ?playerTeamId=1&playerId=1&buyingTeamId=2
- Put: localhost:8080/teams/update/{id}    
body: {"name":"Liverpool", "transferCosts":5,"playersId":[1, 2], "budget":10000000, "country":"England", "town":"Liverpool"}
- Delete: localhost:8080/teams/delete/{id}

###### TransferPlayers
- Post: localhost:8080/transfer_players/add  
body: {"playerId":1, "teamId":1,"cost":10000}
- Post: localhost:8080/transfer_players/add_all  
body: [{"playerId":1, "teamId":1,"cost":10000}, {"playerId":2, "teamId":3,"cost":20000}]
- Get: localhost:8080/transfer_players/get/{id}
- Get: localhost:8080/transfer_players/get_all/{teamId}
- Put: localhost:8080/transfer_players/update/{id}
body: {"playerId":1, "teamId":1,"cost":11000}
- Delete: localhost:8080/transfer_players/delete/{id}

## Implementation details and technologies

### Project based on 3-layer architecture:
>- Presentation layer (controllers)
>- Application layer (services)
>- Data access layer (DAO)

### Technologies
>- Spring Boot
>- Spring Boot WEB
>- Spring Boot DATA
>- Hibernate
>- Hibernate validator
>- MySQL
>- JSON
>- Lombok
>- Maven

### Diagram DB
![drawing](http://dl3.joxi.net/drive/2022/01/24/0052/3292/3415260/60/6bdfa3f9f5.jpg)

## Setup
>1. Configure Apache Tomcat(V - 9.0.55)
>2. Install MySQL(V - 8.0)
>3. Create new schema, change name your database, passwod and user in application.properties