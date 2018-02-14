# HackMaster2.0
<br />
HackMaster 2.0 is a turn based 2-player card game in which you play cards which expend resources. In return the cards can be used for either upgrading, defending, or attacking. The cards cost resources, and resources are incremented by resource rate each time it's the players turn (every second time). <br /><br />

## Major packages:
<br />
The main code of the project is currently split between four packages: Business, Objects, Persistence, and Presentation. These  packages is located in the directory HackMaster20/app/src/main/java/HackMaster. <br /> <br /> 

The business package contains the files: <br />
DeckManager.java<br />
GameManager.java<br />
ResourceManager.java<br /><br /> 

The Objects package contains the files: <br /> 
CardClass.java<br /> 
CardResource.java<br /> 
EnemyAI.java<br /> 
PlayerClass.java<br /> 
PlayerStatsSaves.java<br /> 
ResourceClass.java<br /> <br /> 

The Persistence package contains the files:<br /> 
CardList.java<br /><br /> 

The Presentation package contains the files:<br /> 
DrawToScreen.java<br /> 
MainActivity.java<br /> <br /> 
   
  The project also contains a test package that mirrors the packages listed above. The test packages are located in HackMaster20/app/src/test/java/HackMasterTest/ <br /> <br />
## Log File
 <br />
The log file (log.txt) is located in the root directory. <br /> <br />
  
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
