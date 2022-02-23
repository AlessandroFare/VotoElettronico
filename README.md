# VotoElettronico

Electronic Voting System and Ballot box made with Java and MySQL.

* You can register and configure voting session or vote in the election or referendum.
* There are two types of Users: elector and admin.
* The project is implemented with 4 design patterns: MVC, DAO, Observer and Singleton.
* The GUI is made with Java Swing.

## Docs

[VotoElettronico_Docs](https://github.com/AlessandroFare/VotoElettronico/blob/master/Docs_VotoElettronico.pdf)

## Built with:

* [Java](https://www.java.com/it/)
* [MySQL](https://www.mysql.com/it/)

## Project

All GUI and UML Diagram images are on images folder.

### Avvio

<img src="images_gui/Avvio.png">

### Gestione Elezioni

<img src="images_gui/GestioneCP.png">

### Registrazione Elettore

<img width="650" src="images_gui/RegistrazioneElettore.png">

### Navigation Map

<img width="650" src="images_gui/Navigation_Map.png">

## UML Diagram

### Use Case

<img src="images_diagram/use_case/Use_Case.png">

### Class Diagram

<img src="images_diagram/class_diagram/Class_Diagram.png">

### Sequence Diagram (Gestione Sessione di Voto)

<img src="images_diagram/sequence_diagram/Sequence_Diagram_GestioneSessioneVoto.png">

### Activity Diagram (Votazione)

<img src="images_diagram/activity_diagram/Activity_Diagram_Votazione.png">

### FSM Diagram (Registrazione)

<img src="images_diagram/fsm_diagram/FSM_Registrazione.png">

### Component Diagram

<img src="images_diagram/component_diagram/Component_Diagram.png">

## Prerequisites

You have to install MySQL DBMS to import my dumps.\
Connector file .jar is already in this repo.

## Usage

Open Voto-Elettronico on Eclipse as a project.\
Click on project -> properties > java build path > Add external files jar.\
Then add the two files .jar on the libs folder.\
Finally run VotoElettronico.java.

## Author

* **Alessandro Farè**
