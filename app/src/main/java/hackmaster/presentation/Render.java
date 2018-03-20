package hackmaster.presentation;

import com.example.owner.hackmaster20.R;

import hackmaster.business.Game;

public class Render {
    private Game gameInSession;
    private MainActivity mainActivity;
    private MusicManager musicManager;
    private static Render internalCopy;

    private static int priorityDictionary[][];
    private int contentId;

    private Render(Game gameInSes, MainActivity mainAct, MusicManager musicManag) {
        gameInSession = gameInSes;
        mainActivity = mainAct;
        musicManager = musicManag;
        contentId = 0;
    }

    static Render setUpRender(Game gameInSes, MainActivity mainAct, MusicManager musicManag) {
        Render render = null;
        if (internalCopy == null) {
            render = new Render(gameInSes, mainAct, musicManag);
            internalCopy = render;
        }
        return render;
    }

    static void updateRender(Game gameInSes, MainActivity mainAct, MusicManager musicManag) {
        internalCopy.gameInSession = gameInSes;
        internalCopy.mainActivity = mainAct;
        internalCopy.musicManager = musicManag;
    }

    public void setContentView(int contentId) {
        this.contentId = contentId;
        mainActivity.setContentView(contentId);
    }

    public void update() {

    }
}
