package hackmaster.persistence;

import java.util.ArrayList;
import java.util.List;

import hackmaster.objects.CardClass;

public interface DBInterface {
    void open(String string);

    void close();

    String getCardSequential(List<CardClass> cardDeck);

    ArrayList<CardClass> getCardRandom(CardClass card);

    String insertCard(CardClass card);

    String updateCard(CardClass card);

    String removeCard(CardClass card);

}
