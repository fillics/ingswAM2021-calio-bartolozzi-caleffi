# Prova Finale Ingegneria del Software 2021
## Gruppo: AM20
* Filippo Caliò (10628126) - filippo.calio@mail.polimi.it
* Beatrice Bartolozzi (10683294) - beatrice.bartolozzi@mail.polimi.it
* Giovanni Caleffi (10665233) - giovanni.caleffi@mail.polimi.it
  

Master of Renaissance Board Game is the final test of "Software Engineering", course of "Computer Science Engineering" held at Politecnico di Milano (2020/2021).\
Teacher: Alessandro Margara

![Master of Renaissance](https://github.com/fillics/ingswAM2021-calio-bartolozzi-caleffi/blob/master/src/main/resources/images/github/masters-of-renaissance.jpg)

# Setup
In the deliveries folder there are two multi-platform jar files, one to set the Server up and the other one to start the Client.


### Server
The Server can be run with the following command:

```
> java -jar Server.jar 
```

This command can be followed by the following argument:

`-port`: followed by the desired port number between MIN_PORT and MAX_PORT as argument;
(for example: `-port 1234`). If you run it without the port, it will ask you the port.

|   MIN_PORT  | MAX_PORT |
|:--------:|:--------:|
|  1024 | 65535 |


### Client
The Client can be run with the following command:

```
> java -jar Client.jar 
```

This command can be followed by the following argument:

`-cli`: this command sets the Client on Command Line Interface (CLI) mode.\
`-gui`: this command sets the Client on Graphical User Interface (GUI) mode.\
`-default`: to connect to the server using the server port and the IP address saved in the [ClientConnection.json](https://github.com/fillics/ingswAM2021-calio-bartolozzi-caleffi/blob/master/src/main/resources/json/ClientConnection.json) file

If you run, for example without `-default`, the command `java -jar Client.jar -cli`, it will ask you the server port and the ip address.\
If you run the commands without any arguments, it will also ask you which view interface you want to use.

# Implemented Functionalities
| Functionality        | Status  | 
| ------------- |:-------------:| 
| Basic rules     | :heavy_check_mark: | 
| Complete rules    | :heavy_check_mark:     |
| Socket    | :heavy_check_mark:     |   
| CLI    | :heavy_check_mark:     |   
| GUI    | :heavy_check_mark:     |   
| Multiple games    | :heavy_check_mark:     |   
| Resilience to Disconnections | :heavy_check_mark:      |    


## Build
Use maven to build jar files for both the Client and the Server by choosing the appropriate Maven Profile.


To build the Server, issue:
> mvn clean
> mvn package -P Server

To build the Client, issue:
> mvn clean
> mvn package -P Client

After these processes both jars can be found in the builds folder.

### Tools
StarUML - UML Diagram\
Maven - Dependency Management\
IntelliJ - IDE\
Swing - Graphical Framework


### License
This project is developed in collaboration with Politecnico di Milano and Cranio Creations.
