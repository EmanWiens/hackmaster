package hackmaster.business;

public abstract class Game implements GameInterface {
    public static final int hand = 6;
    abstract void PlayCard(); // method that this one may have but may not
}
