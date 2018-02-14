# HackMaster2.0
<br />
HackMaster 2.0 is a turn based 2-player card game in which you play cards for a cost of resources. Cards are used for either upgrading, defending, or attacking. The cards cost resources, and resources are incremented by resource rate each time it's the players turn (every second time). <br /><br />

## Major packages:
  Presentation - MainView <br />
  Business - GameManager, DeckManager, ResourceManager <br />
  Objects - CardClass, CardsResource, PlayerClass, EnemyAI extends PlayerClass, PlayerStats, ResourceClass <br />
  Persietence - CardsList<br /><br />
  

## Log.txt
The log file is in the root directory.
<br /><br />

## The button Layout/Hierarchy
Single Player - The single player option is functional. The game is limited but works and is tested. Only known bug is that "Botnet gen." never goes up.<br />
  In game: Pause works.<br />
    Android Back button - prompts you if you want to leave the game<br />
    Pause - shows you the pause screen and pauses the game<br />
      Exit game - all promt you if you want to exit the game<br />
      Resume game - Android back button - brings you back in game<br />
      Stats - brings you to the player stats page<br />
        Back - brnigs you back to pause menu<br />
        Android back button - brings you back in game<br /><br />
Local Multiplayer - Multiplayer - Options - Has no functionanlity yet<br />
Stats: brings you to the same stats page as in the game<br />
  Android back button - Back - brings you to main menu<br /><br />


## Andriod studio 
Build once, if build doesn't work go to build->clean build. Then hit play.<br /> 
If you can't run the simulator got on tools->android->sync gradle.<br />
