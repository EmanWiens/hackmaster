package hackmaster20.persistence;

import hackmaster20.business.ResourceManager;
import hackmaster20.objects.CardClass;
import hackmaster20.objects.ResourceClass;

/**
 * Created by Owner on 1/29/2018.
 */

public class CardsList {
    public static CardClass[] presetCards() {
        // make some cards where you set all the specs
        int count = 0;
        CardClass cards[] = new CardClass[7];

        // TO DO make a list of cards

        CardClass tempCard = new CardClass(0, "CPU Boost", "Upgrade", "Upgrade your CPU",
                new ResourceManager(new ResourceClass(0, -10, 0, 0, 0,1, 0), null));
        cards[count] = tempCard;
        // tempCard.show();
        count++;

        tempCard = new CardClass(0, "CPU Boost", "Upgrade", "Upgrade your CPU",
                new ResourceManager(new ResourceClass(0, -10, 0, 0, 0,1, 0), null));
        cards[count] = tempCard;
        // tempCard.show();
        count++;

        tempCard = new CardClass(1, "More Cores", "Defense", "Upgrade your CPU",
                new ResourceManager(new ResourceClass(0, -5, 10, 0, 0,0, 0), null));
        cards[count] = tempCard;
        count++;

        tempCard = new CardClass(1, "bot.net", "Attack", "Upgrade your CPU",
                new ResourceManager(new ResourceClass(0, -5, 10, -3, 0,0, 2), null));
        cards[count] = tempCard;
        count++;

        tempCard = new CardClass(1, "cut some wires", "Defense", "Upgrade your CPU",
                new ResourceManager(new ResourceClass(0, 0, 0, -20, 0,0, 0)
                        , new ResourceClass(-10, 0, 0, 0, 0,0, 0)));
        cards[count] = tempCard;
        count++;

        tempCard = new CardClass(1, "More Cores", "Defense", "Upgrade your CPU",
                new ResourceManager(new ResourceClass(0, -5, 10, 0, 0,0, 0), null));
        cards[count] = tempCard;
        count++;

        tempCard = new CardClass(1, "More Cores", "Defense", "Upgrade your CPU",
                new ResourceManager(new ResourceClass(0, -5, 10, 0, 0,0, 0), null));
        cards[count] = tempCard;
        count++;

        return cards;
    }
}
