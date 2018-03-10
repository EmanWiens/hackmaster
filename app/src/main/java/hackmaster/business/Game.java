package hackmaster.business;

public abstract class Game implements GameInterface {
    public static final int hand = 5;
    abstract void PlayCard();
}
