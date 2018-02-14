##HackMaster2.0 ReadMe.txt

HackMaster 2.0 is a turn based 2-player card game in which you play cards which expend resources. In return the cards can be used for either upgrading, defending, or attacking. The cards cost resources, and resources are incremented by resource rate each time it's the players turn (every second time). 

##Major packages:

The main code of the project is currently split between four packages: Business, Objects, Persistence, and Presentation. These packages are in the directory HackMaster20/app/src/main/java/HackMaster. 

The business package contains the files: 
•	DeckManager.java - Deals the cards and asks the interface to draw to the screen
•	GameManager.java - Initializes and tracks single player game as it progresses. Holds a copy of DeckManager and ResourceManager. Also stores the players of the current game.
•	ResourceManager.java - Deals with the card and players' resources (draw to screen)

The Objects package contains the files: 
•	CardClass.java - A card in the game. Costs resources, gives something in return
•	CardResource.java - The resources that the card holds
•	EnemyAI.java (extends PlayerClass) - The enemy AI in single player
•	PlayerClass.java - A player in the game. Deals with resources and cards
•	PlayerStatsSaves.java - Holds the players stats (overall app-user stats) does not currently track anything
•	ResourceClass.java - The resources that a player has (also holds player health)

The Persistence package contains the files:
•	CardList.java - The mock interface to our database
The Presentation package contains the files:
•	DrawToScreen.java (Interface) - Implemented by MainActivity.java so that Business Class can ask to print to the screen
•	MainActivity.java

The project also contains test packages that mirrors the packages listed above. The test packages are located in HackMaster20/app/src/test/java/HackMasterTest/ 

##Log File

The log file (log.txt) is located in the root directory. 

##Repository Link

Use Iteration-1 branch for marking
https://github.com/drFruitFace/HackMaster20.git

##The button Layout/Hierarchy

Single Player - The single player option is functional. The game is limited but works and is tested. Only known bug is that "Botnet gen." never goes up.
In game: Pause works.
Android Back button - prompts you if you want to leave the game
Pause - shows you the pause screen and pauses the game
Exit game - all prompt you if you want to exit the game
Resume game - Android back button - brings you back in game
Stats - brings you to the player stats page
Back - brings you back to pause menu
Android back button - brings you back in game

Local Multiplayer - Multiplayer - Options - Has no functionality yet
Stats: brings you to the same stats page as in the game
Android back button - Back - brings you to main menu

##Android studio

Build once, if build doesn't work go to build->clean build. Then hit play.
If you can't run the simulator got on tools->android->sync gradle.
