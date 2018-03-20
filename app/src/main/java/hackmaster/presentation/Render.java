package hackmaster.presentation;

import hackmaster.business.Game;

public class Render {
    private Game gameInSession;
    private MainActivity mainActivity;
    private MusicManager musicManager;
    private static Render internalCopy;

    private Render(Game gameInSes, MainActivity mainAct, MusicManager musicManag) {
        gameInSession = gameInSes;
        mainActivity = mainAct;
        musicManager = musicManag;
    }

    public static Render setUpRender(Game gameInSes, MainActivity mainAct, MusicManager musicManag) {
        Render render = null;

        if (internalCopy == null) {
            render = new Render(gameInSes, mainAct, musicManag);
            internalCopy = render;
        }

        return render;
    }

    public static void updateRender(Game gameInSes, MainActivity mainAct, MusicManager musicManag) {
        internalCopy.gameInSession = gameInSes;
        internalCopy.mainActivity = mainAct;
        internalCopy.musicManager = musicManag;
    }

    public void update() {

    }
}
