# REPORT - Integrative Project

##### Course Unit:
* Lapr3 - Laboratory/Project III

##### Professors:
* Ana Isabel Gaspar Freitas
* Antonio Manuel da Silva Pereira
* José Avelino Da Silva Marinho
* Orlando Jorge Coelho De Moura Sousa
* Lijian Meng

##### TeamMembers:
Constitution of the Group

| Student's Number	   | Student's Name	      |
|--------------|------------------------------|
| **1201371**  | Eduardo Silva                |
| **1191606**  | Pedro Marques                |
| **1200610**  | Diogo Cascais                |
| **1191242**  | Maureen Ahshu                |

##### Client:
* Nuno Miguel Gomes Bettencourt

## Glossary:
* [Glossary](docs/US108/Glossary.md)

## Abstract:
  A cargo shipping company requires a software system to handle their logistics. This company
operates through land and sea, across different continents and has several warehouses spread along the
world.

## Introduction:
  In this project, the group applied concepts of analysis, modelling and objectoriented programming to develop
a system that supports the management of a logistics company.
The development of this project is directly connected with the practices learned and applied until the moment,
namely on the course units of Applied Physics (FSIAP), Computer Architecture (ARQCP), Data
Structures (ESINF) and Databases (BDDAD), and Laboratory/Project III (LAPR3. This project is based on an iterative and
incremental development process. 
In this first increment were created user stories, developed in java, to answer to the following requirements:
"The maritime transport is provided by different ships. Ships send messages during voyages to the
US Coast Guard. These messages contain the positional and displacement information of each ship at
a given time". 
This work comprises the topics of Requirements Engineering, Engineering Analysis, Engineering Design and, to finish, 
an assessment regarding self and peer performance.

## User Stories:
  Regarding the present increment, sprint 1, 2, 3 and 4, the user stories answer the following requirements 
"The maritime transport is provided by different ships. Ships send messages during voyages to the
US Coast Guard. These messages contain the positional and displacement information of each ship at
a given time". To achieve this goal, the work is divided in various user stories:

### Sprint 1:
| US  | Description                                                                                           |  Acceptance criteria  |                 
|:----|:------------------------------------------------------------------------------------------------------|:----------------------|
| [US101](docs/US101/US101.md) [ESINF] |  As a traffic manager, I which to import ships from a text file into a BST. |  No data lost.  |
| [US102](docs/US102/US102.md) [ESINF] |  As a traffic manager I which to search the details of a ship using any of its codes: MMSI, IMO or Call Sign. |  Correct use of OOP concepts.  |
| [US103](docs/US103/US103.md) [ESINF] |  As a traffic manager I which to have the positional messages temporally organized and associated with each of the ships  |  Efficient access of any position value(s) of a ship on a period or date.  |
| [US104](docs/US104/US104.md) [ESINF] |  As a traffic manager I which to make a Summary of a ship's movements. |  For a given ship return in an appropriate structure one of its codes (MMSI, IMO or Call Sign), Vessel Name, Start Base Date Time, End Base Date Time, Total Movement Time, Total Number of Movements, Max SOG, Mean SOG, Max COG, Mean COG, Departure Latitude, Departure Longitude, Arrival Latitude, Arrival Longitude, Travelled Distance and Delta Distance.  |
| [US105](docs/US105/US105.md) [ESINF] |  As a traffic manager I which to list for all ships the MMSI, the total number of movements, Travelled Distance and Delta Distance. |  Ordered by Travelled Distance and total number of movements(descending/ascending).  |
| [US106](docs/US106/US106.md) [ESINF] |  Get the top-N ships with the most kilometres travelled and their average speed (MeanSOG). |  In a period (initial/final Base Date Time) grouped by Vessel Type.  |
| [US107](docs/US107/US107.md) [ESINF] |  Return pairs of ships with routes with close departure/arrival coordinates (no more than 5 Kms away) and with different Travelled Distance. |  Sorted by the MMSI code of the 1st ship and in descending order of the Travelled Distance difference. Do not consider ships with Travelled Distance less than 10 kms.  |
| [US108] [BDDAD] |  As Project Manager, I want the team to develop the data model required to support all the functionality and to fulfill the purpose of the system to develop. This data model is to be designed following a systematic data modeling methodology. |  The result should include (1) the conceptual data model, (2) the logical data model according to the database technology to use, (3) the physical data model to be implemented at the selected DBMS, (4) a data dictionary describing the relevant details of the database elements and (5) a clear and concise justification supporting the selected database technology.  |
| [US109] [BDDAD] |  As Project Manager, I want the team to draft an SQL script to test whether the database verifies all the data integrity restrictions that are required to fulfil the purpose of the system and the business constraints of the UoD. |  There is a catalogue of data integrity restrictions grouped by type(Domain, Identity, Referential, Application) clearly stated. For each data integrity restriction in the catalogue there is a set of SQL instructions that verify the restriction. All SQL instruction in the data integrity verification script are accompanied by a comment that describes the expected result  |
| [US110] [BDDAD] |  As Project Manager, I want the team to define the naming conventions to apply when defining identifiers or writing SQL or PL/SQL code. The naming conventions may evolve as new database and programming objects are known. The naming conventions guide should be organized in a way to facilitate its maintenance. |  There are naming conventions clearly stated to create databases and database objects. The minimum set includes tables, attributes, constraints, primary and foreign keys. The naming conventions are available in a way that makes them easy to understand and complete in a continuous way.  |
| [US111] [BDDAD] |  As Project Manager, I want the team to create a SQL script to load the database with a minimum set of data sufficient to carry out data integrity verification and functional testing. This script shall produce a bootstrap report providing the number of tuples/rows in each relation/table. |  The bootstrap SQL script runs and loads the database as expected with no errors. The bootstrap report is generated and correct, i.e., all tables are mentioned, and their cardinality is correct.  |

### Sprint 2:

| US  | Description                                                                                           |  Acceptance criteria  |
|:----|:------------------------------------------------------------------------------------------------------|:----------------------|
| [US201](docs/US201/US201.md) [ESINF] |  As a Port manager, I which to import ports from a text file and create a 2D-tree with port locations. |  2D-tree balanced.  |
| [US202](docs/US202/US202.md) [ESINF] |  As a Traffic manager, I which to find the closest port of a ship given its CallSign, on a certain DateTime.  |  Using 2D-tree to find closest port.  |
| [US203](docs/US203/US203.md) [BDDAD] |  As Project Manager, I want the team to develop the data model required to support all the functionality and to fulfill the purpose of the system to develop.  |  The following deliverables are expected: (1) revised relational data model in 3NF, (2) revised SQL script to create the database schema in Oracle(physical data model) and (3) database bootstrap script. It is possible to run a SQL script to create the database schema in a complete and consistent way without errors. It is possible to run a script to load the database with enough data to explore the database and run the user stories (database bootstrap script).  |
| [US204](docs/US204/US204.md) [BDDAD] |  As Client, I want to know the current situation of a specific container being used to transport my goods. |  Clients provide the container identifier and get the type and the concrete instance of its current location, e.g., PORT, Leixões or SHIP, WeFly.  |
| [US205](docs/US205/US205.md) [BDDAD] |  As Ship Captain, I want the list of containers to be offloaded in the next port, including container identifier, type, position, and load.  |   “next port” is properly identified. The containers being offloaded are properly identified. Output is in accordance with the specification wrt the information about each container.  |
| [US206](docs/US206/US206.md) [BDDAD] |  As Ship Captain, I want the list of containers to be loaded in the next port, including container identifier, type, and load.  |  “next port” is properly identified. The containers being loaded are properly identified. Output is in accordance with the specification wrt the information about each container.  |
| [US207](docs/US207/US207.md) [BDDAD] |  As Ship Captain, I want to know how many cargo manifests I have transported during a given year and the average number of containers per manifest.  |   Only the cargo manifests of the specified year are considered. Average containers per cargo manifest are properly computed.  |
| [US208](docs/US208/US208.md) [BDDAD] |  As Ship Captain, I want to know the occupancy rate (percentage) of a given ship for a given cargo manifest. Occupancy rate is the ratio between total number of containers in the ship coming from a given manifest and the total capacity of the ship, i.e., the maximum number of containers the ship can load.  |  Ship and cargo manifest are correctly identified. Occupancy rate is properly computed.  |
| [US209](docs/US209/US209.md) [BDDAD] |  As Ship Captain, I want to know the occupancy rate of a given ship at a given moment.  |  Ship is properly identified. Reuses US208. Occupancy rate is properly computed.  |
| [US210](docs/US210/US210.md) [BDDAD] |  As Traffic manager, I need to know which ships will be available on Monday next week and their location.  |   Monday next week is properly identified. Only available ships are returned. All available ships are returned  |

### Sprint 3:

| US  | Description                                                                                           |  Acceptance criteria  |
|:----|:------------------------------------------------------------------------------------------------------|:----------------------|
| [US301](docs/US301/US301.md) [ESINF] |  As a Traffic Manager, I which to import data from countries, ports, borders and seadists files from the database to build a freight network. |  The capital of a country has a direct connection with the capitals of the countries with which it borders. The ports of a country, besides connecting with all the ports of the same country, the port closest to the capital of the country connects with it; and finally, each port of a country connects with the n closest ports of any other country. The calculation of distances in Kms between capitals, and ports and capitals must be done using the GPS coordinates. The graph must be implemented using the adjacency matrix representation and ensuring the indistinct manipulation of capitals and seaports.  |
| [US302](docs/US302/US302.md) [ESINF] |  As a Traffic manager, I wish to colour the map using as few colours as possible.  |  Neighbouring countries must not share the same colour.  |
| [US303](docs/US303/US303.md) [ESINF] |  As a Traffic Manager, I wish to know which places (cities or ports) are closest to all other places (closeness places).  |  Return the n closeness locals by continent. The measure of proximity is calculated as the average of the shortest path length from the local to all other locals in the network.  |
| [US304](docs/US304/US304.md) [BDDAD] |  As a Ship Captain, I want to have access to audit trails for a given container of a given cargo manifest, that is, I want to have access to a list of all operations performed on a given container of a given manifest, in chronological order. For each operation I want to know: the user/login that performed it, the date and time the operation was performed, the type of operation (INSERT, UPDATE, DELETE), the container identifier and the cargo manifest identifier.  |  There is a table for recording audit trails, i.e., record all write-operations involving containers of a cargo manifest. Proper mechanisms for recording write-operations involving containers of a cargo manifest are implemented (INSERT, UPDATE, DELETE). A simple and effective audit trail consultation process is implemented.  |
| [US305](docs/US305/US305.md) [BDDAD] |  As a Client, I want to know the route of a specific container I am leasing.  |   Users provide their registration code, the container identifier and get its path, from source to current location indicating time of arrival and departure at each location and mean of transport (ship or truck) between each pair of locations. When the provided identifier is not valid or, being valid, is not leased by the client, a warning is returned.  |
| [US306](docs/US306/US306.md) [BDDAD] |  As a Port Manager, I want to know the occupancy rate of each warehouse and an estimate of the containers leaving the warehouse during the next 30 days.  |  For each warehouse the required output is available. The 30 days period is properly considered.  |
| [US307](docs/US307/US307.md) [BDDAD] |  As a Port Manager, I intend to get a warning whenever I issue a cargo manifest destined for a warehouse whose available capacity is insufficient to accommodate the new manifest.  |   Destination warehouse is properly identified. Warehouse available capacity is properly computed. The warning is triggered when required.  |
| [US308](docs/US308/US308.md) [BDDAD] |  As a Traffic Manager, I want to have a system that ensures that the number of containers in a manifest does not exceed the ship's available capacity.  |  The destination ship is properly identified. Ship’s available capacity is properly computed. The warning is triggered when required.   |
| [US309](docs/US309/US309.md) [BDDAD] |  As a Traffic Manager, I do not allow a cargo manifest for a particular ship to be registered in the system on a date when the ship is already occupied.  |  The ship is properly identified. Ship’s availability is properly computed. A warning or an exception is triggered when required.  |
| [US310](docs/US310/US310.md) [BDDAD] |  As a Port Manager, I intend to have a map of the occupation of the existing resources in the port during a given month.  |  Occupation of resources is restricted to the month provided. The reported occupation respects actual port capacity.  |
| [US311](docs/US311/US311.md) [BDDAD] |  As a Ship Captain, I want to provide a database access account, with a login “crew” and password “bd7wd5aF”, which gives access exclusively to the information of the containers that are loaded on my ship. The information about each container to be made available is: identifier, type, position and load.  |  The user account is created. Only the required permissions are granted. Only the containers loaded at the Captain’s ship are available for consultation. Only the described data is publicly available through this “crew” account.  |
| [US312](docs/US312/US312.md) [BDDAD] |  As a Client, I want to know the current situation of a specific container being used to transport my goods – US204.  |  When the provided identifier is not valid or, being valid, is not leased by the client, a warning is returned. This warning has two elements, the error code and the identifier of the container. The error code will be: 10 – invalid container id or 11 – container is not leased by a client.  |
| [US313](docs/US313/US313.md) [ARQCP] |  As a Port Staff, given a Cargo Manifest, I wish to fill a statically reserved matrix in memory with each container's ID in its respective place.  |  The matrix should be statically reserved in C, considering the maximum. capacity of the ship, with all positions set to zero. The function should be developed in C.  |
| [US314](docs/US314/US314.md) [ARQCP] |  As a Port Staff, I wish to know the total number of free/occupied slots in the transport vehicle.  |  The number of free/occupied slots should be determined by an Assembly function that traverses the matrix filled with the containers’ IDs. The function should return an eight-byte value, where the number of free slots is placed in the four most significant bytes, and the number of occupied slots in the four least significant bytes.  |
| [US315](docs/US315/US315.md) [ARQCP] |  As a Port Staff, I wish to know if a container is there or not.  |  The free/occupied position should be determined by an Assembly function that verifies the matrix filled with the containers’ IDs. The function should return 1 if a container is there or 0, otherwise.  |
| [US316](docs/US316/US316.md) [ARQCP] |  As a Port Staff, I wish to know the total number of occupied slots.  |  Using the Assembly function developed in the previous US, develop another Assembly function that traverses an array of positions and determines the total number of occupied slots.  |
| [US317](docs/US317/US317.md) [FSIAP] |  As a Ship Chief Electrical Engineer, I want to know what set of materials to use in a container, to operate at temperatures of 7 °C.  |  What types of materials should I used to make up the outer walls. What kind of materials should I use for the middle layers. What types of materials should I use for the interior walls.  |
| [US318](docs/US318/US318.md) [FSIAP] |  As a Ship Chief Electrical Engineer, I want to know what set of materials to use in a container, to operate at temperatures of -5 °C.  |  What types of materials should I used to make up the outer walls. What kind of materials should I use for the middle layers. What types of materials should I use for the interior walls.  |
| [US319](docs/US319/US319.md) [FSIAP] |  As a Ship Chief Electrical Engineer, I want to know the thermal resistance, for each operating temperature, of each container that must contain at least three different materials in its walls. One for the outer wall, one for the intermediate material, and one for the inner wall.  |  For each container, working at a temperature of 7ºC, determine the thermal resistance it offers, according to the choice of materials made. For each container working at a temperature of -5ºC, determine the thermal resistance it offers according to the choice of materials.  |
| [US320](docs/US320/US320.md) [FSIAP] |  As a Ship Chief Electrical Engineer, I intend to present in a summary document, the choice of materials considered for the two types of containers considered, and their thermal resistances.  |  Present in a document (summary)(pdf), the choices considered for each type of container, as well as the respective thermal resistances.  |

### Sprint 4:

| US  | Description                                                                                           |  Acceptance criteria  |
|:----|:------------------------------------------------------------------------------------------------------|:----------------------|
| [US401](docs/US401/US401.md) [ESINF] |  As a Traffic manager, I wish to know which ports are more critical (have greater centrality) in this freight network  |  Return the n ports with greater centrality. The centrality of a port is defined by the number of shortest paths that pass through it.  |
| [US402](docs/US402/US402.md) [ESINF] |  As a Traffic manager, I wish to know the shortest path between two locals (city and/or port).  |  Land path (only includes land routes, may start/end in port/city). Maritime path (only includes ports). Land or sea path (may include cities and ports). Obligatorily passing through n indicated places.  |
| [US403](docs/US403/US403.md) [ESINF] |  As a Traffic manager, I wish to know the most efficient circuit that starts from a source location and visits the greatest number of other locations once, returning to the starting location and with the shortest total distance.  |  Implement one of the heuristics used for this type of circuit.  |
| [US404](docs/US404/US404.md) [BDDAD] |  As Fleet Manager, I want to know the number of days each ship has been idle since the beginning of the current year.  |  Current year is properly identified. Idle time is correctly computed per ship. Ships with no idle time are also reported.  |
| [US405](docs/US405/US405.md) [BDDAD] |  As Fleet Manager, I want to know the average occupancy rate per manifest of a given ship during a given period.  |  Ship is properly identified and considered. Period is properly identified and considered. Average occupancy rate per manifest and ship is correctly computed. |
| [US406](docs/US406/US406.md) [BDDAD] |  As Fleet Manager, I want to know which ship voyages – place and date of origin and destination – had an occupancy rate below a certain threshold; by default, consider an occupancy rate threshold of 66%. Only the trips already concluded are to be considered.  |  Reuses US405. Average occupancy rate is properly computed. Trips still ongoing are not considered for the occupancy rate calculations.  |
| [US407](docs/US407/US407.md) [BDDAD] |  As Port manager, I intend to generate, a week in advance, the loading and unloading map based on ships and trucks load manifests and corresponding travel plans, to anticipate the level of sufficient and necessary resources (loading and unloading staff, warehouse staff, ...).  |  Week in advance is properly identified. Loading and unloading map is comprehensive. Loading and unloading map is clear with respect to the sufficient and necessary resources for loading and unloading tasks.  |
| [US408](docs/US408/US408.md) [BDDAD] |  As Port manager, I intend to develop a data model to build a Data Warehouse to analyse the volume of maritime traffic between any two ports. The fact to be analysed is the traffic volume measured by the indicators “number of containers”, “accumulated number of containers” and “target number of containers”. These indicators refer to the number of containers that are in transit between two ports/locations on the first day of each month. The dimensions to consider are Time, Port of origin and Port of destination. The Time dimension has a hierarchy with the following levels: Year, Month. The location/Port is subject to the following hierarchy: Continent, Country, Port. An estimate of the upper cardinality of the dimension and fact tables must be indicated.  |  Fact tables are properly identified and described. Dimension tables are properly identified and described. The star/snowflake model is consistent with the purpose of the specified data warehouse app as well as the fact and dimension tables previously identified. The estimate of the cardinalities is coherent with the data model and properly justified. An SQL script to load and query the data warehouse to support a proof of concept is available and runs with no errors.  |
| [US409](docs/US409/US409.md) [ARQCP] |  As a Port staff, given a Cargo Manifest, I wish to fill a dynamically reserved array in memory with all the container's information in its respective place.  |  The array should be dynamically reserved in C, adjusting the size of the array to the amount of handled data. All the details of a container can be present in more than one file. As such, define a struct that represents a container. Therefore, in the end, you should have a matrix of structs. The data types chosen for each field of the struct should be adjusted to the types of values they store. This document is no longer valid once printed. minimum required. Also consider the order of fields that minimizes memory consumption. The function should be developed in C.  |
| [US410](docs/US410/US410.md) [ARQCP] |  As a Ship Chief Electrical Engineer, given the position of a container, I want to know the amount of needed energy to keep the container at its required temperature.  |  Develop an Assembly function, that given a position of a container, determines if that container is refrigerated or not, considering the information stored in the array of containers. The function should return 1 if a container is refrigerated or 0, otherwise. Develop a C function that, using the previous function developed in Assembly to know if a container is refrigerated, determines the amount of needed energy to keep the container at its required temperature.  |
| [US411](docs/US411/US411.md) [ARQCP] |  As a Ship Chief Electrical Engineer, I want to receive an alert when the current energy generation units are not enough to provide energy to all refrigerated containers at once.  |  The needed calculations should be done in C.  |
| [US412](docs/US412/US412.md) [FSIAP] |  As Ship Chief Electrical Engineer, We intend to know how much energy to supply, for each container, in a determined trip, with an exterior temperature of 20 ºC, and a travel time of 2h30.  |  The total energy to be delivered to a container with an operating temperature of 7 °C. The total energy to be delivered to a container with an operating temperature of - 5 °C.  |
| [US413](docs/US413/US413.md) [FSIAP] |  As Ship Chief Electrical Engineer, The objective is to know the total energy to be supplied to the set of containers in a certain established trip, assuming that all the containers have the same behaviour.  |  Know the journey time. Know the temperature of the trip, or sections of the trip. Determine the total energy to be supplied to the set of containers with an operating temperature of 7 °C. Determine the total energy to be supplied to the set of containers with an operating temperature of - 5 °C.  |
| [US414](docs/US414/US414.md) [FSIAP] |  As Ship Chief Electrical Engineer, I want to know how much energy to supply to the container cargo, in a voyage (or route), depending on the position of the containers on the ship. Admitting that the interior containers, or the sides not exposed directly to the"sun", maintain the initial temperature, or of departure. However, the exposed sides may present temperature variations during the trip.  |  Know the journey time. Know the temperatures of the travel sections. Know how many sides of each container are subject to temperature variation. Determine the energy required for one trip of the containers at a temperature of 7 ºC, depending on their position in the load. Determine the energy required for one trip of the containers at a temperature of -5 ºC, depending on their position in the cargo.  |
| [US415](docs/US415/US415.md) [FSIAP] |  As the Ship's Captain, I need to know how many auxiliary power equipment are needed for the voyage, knowing that each one supplies a maximum of 75 KW.  |  Know the amount of energy required per type of container for a trip. Determine the total energy required to load containers on a given voyage(or route) as a function of their position in the load. Determine how many generators of the stated power are required for the trip.  |
| [US416](docs/US416/US416.md) [FSIAP] |  As Ship's Captain, I intend to submit a summary document, with the following items.  |  Present in a document (summary)(pdf), the necessary energy of each container, of each type (internal temperatures), with an external temperature of 20 ºC, and a travel time of 2h30. Present in a summary document, pdf, the total energy to supply, to the set of containers, in a established trip, assuming that all containers have the same behaviour. Present in a summary document, pdf, the energy to supply to the container load, in one trip (or route), as a function of the position of the containers on the vessel, and of the interior temperature of the two types of containers considered. Present in a summary document, pdf, the number of generators required for the voyage, or sectors of the trip.  |
| [US417](docs/US417/US417.md) [LAPR3] |  As the Ship Captain, I want the technical team to search for at least three types of ship/vessels that are better suited to the task (e.g., depending on the type of cargo), in which the “control” bridge can assume three positions, one in the bow, one in the stern, and finally in the midship.  |  Search the different types of vessels for transporting different types of cargo. Containers, or solids in bulk. Identify the differentiating characteristics.  |
| [US418](docs/US418/US418.md) [LAPR3] |  As the Ship Captain, I want the determine the unladen center of mass for each vessel (if different) according to its characteristics. For calculation purposes, consider known geometric figures.  |  Make a sketch of the vessel's geometric figure. Identify/choose a reference for the calculation. Determine the center of mass for the different vessels (consider that the vessel is all made of the same material).  |
| [US419](docs/US419/US419.md) [LAPR3] |  As the Ship Captain, I want to know where to position, for example, one hundred(100) containers on the vessel, such that the center of mass remains at xx and yy, determined in the previous point.   |  Identify the area/volume of a container and its center of mass. The distribution of the mass inside the container will be considered uniform. Make a sketch of the distribution and loading on the vessel. Calculate the center of mass of the sketch performed.  |
| [US420](docs/US420/US420.md) [LAPR3] |  As the Ship Captain I want to know for a specific vessel, how much did the vessel sink, assuming that each container has half a ton of mass.  |  Determine the total mass placed on the vessel and the pressure exerted by it on the water. Determine the difference in height that the vessel has suffered, above water level.  |


## Conclusion:
  This report describes the content and methodology developed for the implementation of sprint 1, 2, 3 and 4, either 
through planning and organization or critical analysis.
Regarding group organization, the application of Scrum methodology and the attribution of the role of Scrum 
master to one of the members, facilitated and improved the communication and interaction between the members
of the group.
Thus, the group performed the tasks assigned with responsibility and autonomy.
The development of this increment allowed the group to acquire knowledge in terms
of allocation of learnings, which allows an overview of the interconnections of the contents
studied.


-------------------------------------------------------------------------




## Engineering Analysis:

### [DM](docs/DM/DM.md) - Domain Model for the whole project:
![DM](docs/DM/DM.png)

### [UCD](docs/UCD/UCD.md) - Use Case Diagram for the whole project:
![UCD](docs/UCD/UCD.svg)

## Engineering Design:

### [CD](docs/CD/CD.md) - Class Diagram for the whole project:
![CD](docs/CD/CD.svg)


### Relational Model for the whole project:
* Sprint 1
![RM](docs/US108/RM.png)

* Sprint 2
![RM](docs/US203/RM.png)

* Sprint 3
![RM](docs/RM/RM.png)

* Sprint 4
![RM](docs/RM/RM.png)

## Sprint 1 Self and Peer-Assessment:

* Group and Self Assessment:
  ![Image1](docs/Sprint1_Assessment/Image1.png)
  
* User Stories:
  ![Image2](docs/Sprint1_Assessment/Image2.png)

* Code Quality:
  ![Image3](docs/Sprint1_Assessment/Image3.png)

* Project Development:
  ![Image4](docs/Sprint1_Assessment/Image4.png)
  
* Project Management:
  ![Image5](docs/Sprint1_Assessment/Image5.png)

## Sprint 2 Self and Peer-Assessment:

* Group and Self Assessment:
  ![Image1](docs/Sprint2_Assessment/Image1.png)

* User Stories:
  ![Image2](docs/Sprint2_Assessment/Image2.png)

* Code Quality:
  ![Image3](docs/Sprint2_Assessment/Image3.png)

* Project Development:
  ![Image4](docs/Sprint2_Assessment/Image4.png)

* Project Management:
  ![Image5](docs/Sprint2_Assessment/Image5.png)


## Sprint 3 Self and Peer-Assessment:

* Group and Self Assessment:
  ![Image1](docs/Sprint3_Assessment/Image1.png)

* User Stories:
  ![Image2](docs/Sprint3_Assessment/Image2.png)

* Code Quality:
  ![Image3](docs/Sprint3_Assessment/Image3.png)

* Project Development:
  ![Image4](docs/Sprint3_Assessment/Image4.png)

* Project Management:
  ![Image5](docs/Sprint3_Assessment/Image5.png)


## Sprint 4 Self and Peer-Assessment:

* Group and Self Assessment:
  ![Image1](docs/Sprint4_Assessment/Image1.png)

* User Stories:
  ![Image2](docs/Sprint4_Assessment/Image2.png)

* Code Quality:
  ![Image3](docs/Sprint4_Assessment/Image3.png)

* Project Development:
  ![Image4](docs/Sprint4_Assessment/Image4.png)

* Project Management:
  ![Image5](docs/Sprint4_Assessment/Image5.png)

------------------------------------------------------------------------------------


# Usefull Project Information

This is the repository template used for student repositories in LAPR Projets.

## Java source files

Java source and test files are located in folder src.

## Maven file

Pom.xml file controls the project build.

### Notes
In this file, DO NOT EDIT the following elements:

* groupID
* artifactID
* version
* properties

Beside, students can only add dependencies to the specified section of this file.

## Eclipse files

The following files are solely used by Eclipse IDE:

* .classpath
* .project

## IntelliJ Idea IDE files

The following folder is solely used by Intellij Idea IDE :

* .idea

# How was the .gitignore file generated?
.gitignore file was generated based on https://www.gitignore.io/ with the following keywords:

- Java
- Maven
- Eclipse
- NetBeans
- Intellij

# Who do I talk to?
In case you have any problem, please email Nuno Bettencourt (nmb@isep.ipp.pt).

# How do I use Maven?

## How to run unit tests?

Execute the "test" goals.

```shell
$ mvn test
```
## How to generate the javadoc for source code?

Execute the "javadoc:javadoc" goal.

```shell
$ mvn javadoc:javadoc
```
This generates the source code javadoc in folder "target/site/apidocs/index.html".

## How to generate the javadoc for test cases code?

Execute the "javadoc:test-javadoc" goal.

```shell
$ mvn javadoc:test-javadoc
```
This generates the test cases javadoc in folder "target/site/testapidocs/index.html".

## How to generate Jacoco's Code Coverage Report?

Execute the "jacoco:report" goal.

```shell
$ mvn test jacoco:report
```

This generates a jacoco code coverage report in folder "target/site/jacoco/index.html".

## How to generate PIT Mutation Code Coverage?

Execute the "org.pitest:pitest-maven:mutationCoverage" goal.

```shell
$ mvn test org.pitest:pitest-maven:mutationCoverage
```
This generates a PIT Mutation coverage report in folder "target/pit-reports/YYYYMMDDHHMI".

## How to combine different maven goals in one step?

You can combine different maven goals in the same command. For example, to locally run your project just like on jenkins, use:

```shell
$ mvn clean test jacoco:report org.pitest:pitest-maven:mutationCoverage
```
## How to perform a faster pit mutation analysis?

Do not clean build => remove "clean"

Reuse the previous report => add "-Dsonar.pitest.mode=reuseReport"

Use more threads to perform the analysis. The number is dependent on each computer CPU => add "-Dthreads=4"

Temporarily remove timestamps from reports.

Example:
```shell
$ mvn test jacoco:report org.pitest:pitest-maven:mutationCoverage -DhistoryInputFile=target/fasterPitMutationTesting-history.txt -DhistoryOutputFile=target/fasterPitMutationTesting-history.txt -Dsonar.pitest.mode=reuseReport -Dthreads=4 -DtimestampedReports=false
```
## Where do I configure my database connection?

Each group should configure their database connection on the file:
* src/main/resources/application.properties