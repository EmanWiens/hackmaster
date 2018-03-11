package hackmaster.presentation;

import android.os.Handler;

import hackmaster.business.Game;

public class RenderTimer {
    int milli;
    Handler handler;

    public RenderTimer(int milli) {
        this.milli = milli;

        handler = new Handler();
        handler.postDelayed(delayTimer(), milli);
    }

    public Runnable delayTimer() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                Game.setPlayerTurn(true);
            }
        };
        return r;
    }
}
