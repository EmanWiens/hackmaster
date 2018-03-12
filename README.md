# HackMaster2.0
<br />
Branches to look for: 
  Iteration 1

HackMaster 2.0 is a turn based 2-player card game in which you play cards which expend resources. In return the cards can be used for either upgrading, defending, or attacking. The cards cost resources, and resources are incremented by resource rate each time it's the players turn (every second time). <br /><br />

## Major packages:
<br />
The main code of the project is currently split between four packages: Business, Objects, Persistence, and Presentation. These  packages are in the directory HackMaster20/app/src/main/java/HackMaster. <br /> <br /> 

The business package contains the files: <br />
•DeckManager.java - Deals the cards and asks the interface to draw to the screen<br />
•GameManager.java - Initilizes and tracks single player game as it progresses. Holds a copy of DeckManager and ResourceManager. 
Also stores the players of the current game.<br />
•ResourceManager.java - Deals with the card and players' resources (draw to screen)<br /><br /> 

The Objects package contains the files: <br /> 
•CardClass.java - A card in the game. Costs resources, gives something in return<br /> 
•CardResource.java - The resources that the card holds<br /> 
•EnemyAI.java (extends PlayerClass) - The enemy AI in single player<br /> 
•PlayerClass.java - A player in the game. Deals with resources and cards<br /> 
•PlayerStatsSaves.java - Holds the players stats (overall app-user stats) does not currently track anything<br /> 
•ResourceClass.java - The resources that a player has (also holds player health)<br /> <br /> 

The Persistence package contains the files:<br /> 
•CardList.java - The mock interface to our database<br /><br /> 

The Presentation package contains the files:<br /> 
•INTERFACE DrawToScreen.java - Implemented by MainActivity.java so that Business Class can ask to print to the screen<br /> 
•MainActivity.java<br /> <br /> 
   
The project also contains test packages that mirrors the packages listed above. The test packages are located in HackMaster20/app/src/test/java/HackMasterTest/ <br /> <br />

## Log File
 <br />
The log file (log.txt) is located in the root directory of the repo. <br /> <br />
  
## The button Layout/Hierarchy
 <br />
Single Player - The single player option is functional. The game is limited but works and is tested. Only known bug is that "Botnet gen." never goes up.<br />
  In game: Pause works.<br />
    Android Back button - prompts you if you want to leave the game<br />
    Pause - shows you the pause screen and pauses the game<br />
      Exit game - all prompt you if you want to exit the game<br />
      Resume game - Android back button - brings you back in game<br />
      Stats - brings you to the player stats page<br />
        Back - brings you back to pause menu<br />
        Android back button - brings you back in game<br /><br />
Local Multiplayer - Multiplayer - Options - Has no functionality yet<br />
Stats: brings you to the same stats page as in the game<br />
  Android back button - Back - brings you to main menu<br /><br />

## Andriod studio 
 <br />
Build once, if build doesn't work go to build->clean build. Then hit play.<br /> 
If you can't run the simulator got on tools->android->sync gradle.<br />

## Credit to Music
 <br />
  "Java" Song : https://www.youtube.com/watch?v=b-Cr0EWwaTk 
  "Dual_Core" Song: https://www.youtube.com/watch?v=FoUWHfh733Y&index=21&list=RDiN1uaITfA1c
  "Hacker" Song: https://www.youtube.com/watch?v=iN1uaITfA1c&index=1&list=RDiN1uaITfA1c
  "Welcome To Our World" Song: https://www.youtube.com/watch?v=rLsJCCNXUto&list=RDiN1uaITfA1c&index=3
  "Pirate Music" Song: https://www.youtube.com/watch?v=Gc74aRe7OLM
 <br /> 
