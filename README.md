# HackMaster
<br />
Branches to look for: 
  Iteration 2

HackMaster is a turn based 2-player card game in which you play cards which expend resources. In return the cards can be used for either upgrading, defending, or attacking. The cards cost resources, and resources are incremented by resource rate each time it's the players turn (every second time). <br /><br />

## Major packages:
<br />
The main code of the project is currently split between five packages: Application, Business, Objects, Persistence, and Presentation. These  packages are in the directory hackmaster/app/src/main/java/hackmaster. <br /> <br /> 

The application package cointains the files: <br /> 
•DBController.java - Manages the name and path of the database. Gets called to start the initial database.<br /> 
•Services.java - Starts and stops the database and controls which database is connected.<br /> <br />

The business package contains the files: <br />
•DeckManager.java - Gets cards from the database and deals cards when necessary.<br />
•Game.java - Tracks the game as it progresses. Can check if a player can play a card and if a player has lost all of their
health.<br />
•MultiplayerGame.java - Subclass of Game. Manages the turn and resources of a multiplayer game.<br />
•ResourceManager.java - Applies cards played to the players.<br />
•SetUpSingleGame.java - Initializes the game and deck.<br />
•SinglePlayerGame.java - Subclass of Game. Manages the turn and resources of a singleplayer game.<br /><br />

The Objects package contains the files: <br /> 
•CardClass.java - A card in the game. Costs resources, gives something in return<br /> 
•EnemyAI.java (extends PlayerClass) - The enemy AI in single player<br /> 
•PlayerClass.java - A player in the game. Has resources and cards<br /> 
•PlayerStatsSaves.java - Holds the players stats (overall app-user stats) does not currently track anything<br /> 
•ResourceClass.java - The resources that a player or a card has (also holds player health)<br /> <br /> 

The Persistence package contains the files:<br /> 
•CardDataAccess.java - Implements CardDataAccessInterface. Uses SQL statements to query the card database.<br /> 
•CardDataAccessInterface.java - Extends DBComponentInterface. Interface for card database.<br /> 
•DBComponentInterface.java - Interface for database access. Gets statements passes from DataAccessObject.<br /> 
•DBInterface.java - Interface for database initialization.<br /> 
•DataAccessObject.java Implements DBInterface. Initializes and opens the database. Creates statements for database 
access.<br /> 
•PlayerDataAccess.java - Implements PlayerDataAccessInterface. Uses SQL statements to query the player stats database.<br /> 
•PlayerDataAccessInterface.java - Extends DBComponentInterface. Interface for player stats database.<br /><br /> 

The Presentation package contains the files:<br /> 
•MainActivity.java - Tracks player input and updates the screen.<br />
•MusicManager.java - Selects the music to be played.<br />
•SplashActivity.java - Loading screen for the app.<br /> <br />
   
The project also contains test packages that mirrors the packages listed above. The test packages are located in hackmaster/app/src/test/java/HackMasterTest/ <br /> <br />

## Log File
 <br />
The log file (log.txt) is located in the root directory of the repo. <br /> <br />
  
## The button Layout/Hierarchy
 <br />
Main Menu: <br />
Single Player - Launches a single player game versus the AI<br />
Multiplayer - Launches a multiplayer game where you take turns back and forth<br />
Stats - Brings you to the stats page<br />
  Android back button - brings you to main menu<br /><br />
In game: <br />
  Discard Button - Discard the next card touched instead of playing it<br />
  Android Back button - prompts you if you want to leave the game<br />
  Pause - shows you the pause screen and pauses the game<br />
    Exit game - all prompt you if you want to exit the game<br />
    Resume game - Android back button - brings you back in game<br />
    Stats - brings you to the player stats page<br />
      Back - brings you back to pause menu<br />
      Android back button - brings you back in game<br /><br />


## Andriod studio 
 <br />
Build once, if build doesn't work go to build->clean build. Then hit play.<br /> 
If you can't run the simulator got on tools->android->sync gradle.<br />

## Credit to Music
<br />"Java" Song : https://www.youtube.com/watch?v=b-Cr0EWwaTk<br /> 
<br />"Dual_Core" Song: https://www.youtube.com/watch?v=FoUWHfh733Y&index=21&list=RDiN1uaITfA1c <br /> 
<br />"Hacker" Song: https://www.youtube.com/watch?v=iN1uaITfA1c&index=1&list=RDiN1uaITfA1c<br /> 
<br />"Welcome To Our World" Song: https://www.youtube.com/watch?v=rLsJCCNXUto&list=RDiN1uaITfA1c&index=3<br /> 
<br />"Pirate Music" Song: https://www.youtube.com/watch?v=Gc74aRe7OLM <br />
