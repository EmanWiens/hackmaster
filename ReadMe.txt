Hack_Master ReadMe

Branches to look for: Iteration-3
Hack_Master is a turn based 2-player card game in which you play cards which expend resources. In return the cards can be used for either upgrading, defending, or attacking. The cards cost resources, and resources are incremented by resource rate each time it's the players turn (every second time). 

Major packages

The main code of the project is currently split between five packages: Application, Business, Objects, Persistence, and Presentation. These packages are in the directory hackmaster/app/src/main/java/hackmaster. 

The application package contains the files: 
•AppController.java - It is used to instantiates the application database.
•DBController.java - Manages the name and path of the database.
•Services.java - Starts and stops the database and controls which database is connected.


The business package contains the files: 
•DeckManager.java - Gets cards from the database and deals cards when necessary.
•Game.java - Tracks the game as it progresses. Can check if a player can play a card and if a player has lost all of their health.
•MultiplayerGame.java - Subclass of Game. Manages the turn and resources of a multiplayer game.
•ResourceManager.java - Applies cards played to the players.
•SetUpGame.java - Initializes the game and deck.
•SinglePlayerGame.java - Subclass of Game. Manages the turn and resources of a single player game.


The Objects package contains the files: 
•CardClass.java - A card in the game. Costs resources, gives something in return
•EnemyAI.java (extends PlayerClass) - The enemy AI in single player
•PlayerClass.java - A player in the game. Has resources and cards
•PlayerStatsSaves.java - Holds the players stats (overall app-user stats) does not currently track anything
•ResourceClass.java - The resources that a player or a card has (also holds player health)


The Persistence package contains the files:
•CardDataAccess.java - Implements CardDataAccessInterface. Uses SQL statements to query the card database.
•CardDataAccessInterface.java - Extends DBComponentInterface. Interface for card database.
•DBComponentInterface.java - Interface for database access. Gets statements passes from DataAccessObject.
•DBInterface.java - Interface for database initialization.
•DataAccessObject.java Implements DBInterface. Initializes and opens the database. Creates statements for database access.
•PlayerDataAccess.java - Implements PlayerDataAccessInterface. Uses SQL statements to query the player stats database.
•PlayerDataAccessInterface.java - Extends DBComponentInterface. Interface for player stats database.


The Presentation package contains the files:
•MainActivity.java - Tracks player input.
•MusicManager.java - Selects the music to be played.
•render.java - Controls and updates the screen.
•SplashActivity.java - Loading screen for the app.


The project also contains test packages that mirrors the packages listed above. The test packages are located in hackmaster/app/src/test/java/hackmasterTest/

Log File

The log file (log.txt) is located in the root directory of the repo. 

The button Layout/Hierarchy

Main Menu: 
Single Player - Launches a single player game versus the AI
Multiplayer - Launches a multiplayer game where you take turns back and forth
Stats - Brings you to the stats page
Android back button - brings you to main menu

In game: 
Discard Button - Discard the next card touched instead of playing it
Android Back button - prompts you if you want to leave the game
Pause - shows you the pause screen and pauses the game
Exit game - all prompt you if you want to exit the game
Resume game - Android back button - brings you back in game
Stats - brings you to the player stats page
Back - brings you back to pause menu
Android back button - brings you back in game

Android studio

Build once, if build doesn't work go to build->clean build. Then hit play.
If you can't run the simulator got on tools->android->sync gradle.

New Features/Changes (Iteration 3)

Improved UI
•More stylized card images
•Added stylized buttons
•Added background images

Streamlined Rendering
•Screen rendering has been improved and revamped

Refactored Code Base
•Code base has been refactored for improved interactivity between classes

Issues

March 27, 2018: The rendering may hang when playing a card in single player. Pressing discard renders the correct cards.
Credit to Music

"Java" Song: https://www.youtube.com/watch?v=b-Cr0EWwaTk
"Dual_Core" Song: https://www.youtube.com/watch?v=FoUWHfh733Y&index=21&list=RDiN1uaITfA1c 
"Hacker" Song: https://www.youtube.com/watch?v=iN1uaITfA1c&index=1&list=RDiN1uaITfA1c
"Welcome To Our World" Song: https://www.youtube.com/watch?v=rLsJCCNXUto&list=RDiN1uaITfA1c&index=3
"Pirate Music" Song: https://www.youtube.com/watch?v=Gc74aRe7OLM 
 
