package hackmaster.persistence;

import java.util.List;

import hackmaster.objects.CardClass;

public interface CardDataAccessInterface extends DBComponentInterface{
    String getCardSequential(List<CardClass> cardResult);
    CardClass getCardRandom(CardClass newCard);
    String insertCard(CardClass card);
    String updateCard(CardClass card);
    String removeCard(CardClass card);
}
