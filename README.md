# HackMaster2.0

Write a readme.txt file that describes the contents of your electronic submission. Identify the packages and major source code files. Describe where to find the log. Provide an overview of the major implemented features and where to find them in the GUI.

This is HackMaster 2.0. It's a turn based card game in which you play cards for a cost of resource. Cards are used for either upgrading, defending, or attacking. The cards cost resources, and resources are incremented by resource rate each time it's the players turn (every second time). 

## Major packages: 
  Presentation - MainView 
  Business - GameManager, DeckManager, ResourceManager
  Objects - CardClass, CardsResource, PlayerClass, EnemyAI extends PlayerClass, PlayerStats, ResourceClass
  Persietence - CardsList
  

## Log.txt
The log file is in the root directory.


## The button Layout/Hierarchy

Single Player - The single player option is functional. The game is limited but works and is tested. Only known bug is that "Botnet gen." never goes up.
  In game: Pause works.
    Android Back button - prompts you if you want to leave the game.
    Pause - shows you the pause screen and pauses the game
      Exit game - all promt you if you want to exit the game.
      Resume game - Android back button - brings you back in game
      Stats - brings you to the player stats page
        Back - brnigs you back to pause menu
        Android back button - brings you back in game
Local Multiplayer - Multiplayer - Options - Has no functionanlity yet
Stats: brings you to the same stats page as in the game
  Android back button - Back - brings you to main menu 


## Andriod studio 
Build once, if build doesn't work go to build->clean build. Then hit play.
If you can't run the simulator got on tools->android->sync gradle.
