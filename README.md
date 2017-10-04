# Orion-API-taxi Example

This project encapsulates all the communication details related to the [FIWARE's Orion Context Broker](https://catalogue.fiware.org/enablers/publishsubscribe-context-broker-orion-context-broker) and its API Rest, offering a clean interface for Java developers in the [mobiliWARE](http://www.treelogic.com/en/MOBILIWARE.html) project.

In particular, this project implements the java classes related to the most relevant entities of the project's datamodel.

![alt text](https://raw.githubusercontent.com/reciomario/Orion-API-taxi-example/07e883af96b0a8afe4cf71dbb68585cdf85267ea/Datamodel.png)

In addition, it also implements methods and "fake" data for testing the architecture in a development environment. 

# Installation and pre-requisities

User may need to set his own parameters in the Constants.java class:
* A running FIWARE instance with the Orion Context Broker deployed and listening in a  **[FIWARE_API_IP]:[FIWARE_API_PORT]**
* FIWARE account credentials **[OAUTH_USER_NAME] / [OAUTH_USER_PASS]**
