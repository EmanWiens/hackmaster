package hackmaster20.business;

import java.util.ArrayList;

import hackmaster20.objects.CardClass;
import hackmaster20.objects.CardResource;
import hackmaster20.objects.ResourceClass;

/**
 * Created by Owner on 1/29/2018.
 */

public class
CardsList {

    //
    public static CardClass[] presetCards() {
        // make some cards where you set all the specs
        int count = 0;
        ArrayList<CardClass> cards = new ArrayList<CardClass>(20);

        // TO DO make a list of cards

        CardClass tempCard = new CardClass(0, "CPU Boost", "Upgrade", "Upgrade your CPU",
                new CardResource(new ResourceClass(0, -10, 0, 0, 0,1, 0), null));
        cards.add(tempCard);
        count++;

        tempCard = new CardClass(1, "More Cores", "Defense", "Upgrade your CPU",
                new CardResource(new ResourceClass(0, -5, 10, 0, 0,0, 0), null));
        cards.add(tempCard);
        count++;

        tempCard = new CardClass(2, "bot.net", "Attack", "Upgrade your CPU",
                new CardResource(new ResourceClass(0, -5, 10, -3, 0,0, 2), null));
        cards.add(tempCard);
        count++;

        tempCard = new CardClass(3, "cut some wires", "Defense", "Upgrade your CPU",
                new CardResource(new ResourceClass(0, 0, 0, -20, 0,0, 0)
                        , new ResourceClass(-10, 0, 0, 0, 0,0, 0)));
        cards.add(tempCard);
        count++;

        tempCard = new CardClass(count, "Upgrade Botnet", "Upgrade", "Increase Botnet Rate",
                new CardResource(new ResourceClass(0, -2, 0, 0, 0, 0, 150), null));
        cards.add(tempCard);
        count++;

        tempCard = new CardClass(count, "Upgrade CPU", "Upgrade", "Increase CPU Calculations",
                new CardResource(new ResourceClass(0, -2, 0, 0, 0, 10, 0), null));
        cards.add(tempCard);
        count++;

        tempCard = new CardClass(count, "Upgrade Hash Rate", "Upgrade", "Increase Cryptocurrency Mining Rate",
                new CardResource(new ResourceClass(0, -2, 0, +1, 0, 0, 0), null));
        cards.add(tempCard);
        count++;

        tempCard = new CardClass(count, "Upgrade Hash Rate", "Upgrade", "Increase Cryptocurrency Mining Rate",
                new CardResource(new ResourceClass(0, -2, 0, +1, 0, 0, 0), null));
        cards.add(tempCard);
        count++;

        tempCard = new CardClass(count, "DDOS", "Attack", "Deals a Decent Amount of Damage to the Enemy",
                new CardResource(new ResourceClass(0, 0, 0, -500, 0, 0, 0), new ResourceClass(-20, 0, 0, 0, 0, 0, 0)));
        cards.add(tempCard);
        count++;

        tempCard = new CardClass(count, "File Transfer", "Attack", "Steal Enemy Resources",
                new CardResource(new ResourceClass(8, 2, 20, -700, 0, 0, 0), new ResourceClass(-8, -2, -20, -300, 0, 0, 0)));
        cards.add(tempCard);
        count++;

        tempCard = new CardClass(count, "Pop-up", "Attack", "Deals a Small Amount of Damage to the Enemy",
                new CardResource(new ResourceClass(0, 0, 0, -150, 0, 0, 0), new ResourceClass(-4, 0, 0, 0, 0, 0, 0)));
        cards.add(tempCard);
        count++;

        tempCard = new CardClass(count, "Anti-Virus", "Defence", "Gain a Small Amount of Health",
                new CardResource(new ResourceClass(5, 0, -10, 0, 0, 0, 0), null));
        cards.add(tempCard);
        count++;

        tempCard = new CardClass(count, "Firewall", "Defence", "Gain a Decent Amount of Health",
                new CardResource(new ResourceClass(10, 0, -40, 0, 0, 0, 0), null));
        cards.add(tempCard);
        count++;

        tempCard = new CardClass(count, "Play the Market", "Upgrade", "Spend 4 Cryptocoins Gain 8 Back",
                new CardResource(new ResourceClass(0, 4, 0, 0, 0, 0, 0), null));
        cards.add(tempCard);
        count++;

        tempCard = new CardClass(count, "Overclock", "Upgrade", "Spend 4 Cryptocoins to Gain 40 Processing Power",
                new CardResource(new ResourceClass(0, -4, 40, 0, 0, 0, 0), null));
        cards.add(tempCard);
        count++;

        tempCard = new CardClass(count, "Server Farm", "Upgrade", "Spend 4 Cryptocoins gain 400 Botnets",
                new CardResource(new ResourceClass(0, -4, 400, 0, 0, 0, 0), null));
        cards.add(tempCard);
        count++;

        tempCard = new CardClass(count, "Expand", "Upgrade", "Increase the Rate of All Resources",
                new CardResource(new ResourceClass(0, -10, 0, 0, 1, 5, 100), null));
        cards.add(tempCard);
        count++;

        tempCard = new CardClass(count, "Market Crash", "Upgrade", "Enemy Loses 20 Cryptocoins",
                new CardResource(new ResourceClass(0, -8, 0, 0, 0, 0, 0), new ResourceClass(0, -20, 0, 0, 0, 0, 0)));
        cards.add(tempCard);
        count++;

        tempCard = new CardClass(count, "Network Outage", "Upgrade", "Enemy Loses 450 Botnets",
                new CardResource(new ResourceClass(0, -8, 0, 0, 0, 0, 0), new ResourceClass(0, 0, 0, -450, 0, 0, 0)));
        cards.add(tempCard);
        count++;

        tempCard = new CardClass(count, "Throttle", "Upgrade", "Enemy Loses 20 Processing Power",
                new CardResource(new ResourceClass(0, -8, 0, 0, 0, 0, 0), new ResourceClass(0, 0, -40, 0, 0, 0, 0)));
        cards.add(tempCard);
        count++;

        tempCard = new CardClass(count, "Hack!!", "Attack", "Deal a Small Amount of Damage to the Enemy",
                new CardResource(new ResourceClass(0, -2, 0, 0, 0, 0, 0), new ResourceClass(5, 0, 0, 0, 0, 0, 0)));
        cards.add(tempCard);
        count++;

        tempCard = new CardClass(count, "Debug", "Attack", "Gain a Small Amount of Health",
                new CardResource(new ResourceClass(5, -2, 0, 0, 0, 0, 0), null));
        cards.add(tempCard);
        count++;

        tempCard = new CardClass(count, "Exploit", "Attack", "Gain a Small Amount of Health and Deal Damage to the Enemy",
                new CardResource(new ResourceClass(5, -5, 0, 0, 0, 0, 0), new ResourceClass(10, 0, 0, 0, 0, 0, 0)));
        cards.add(tempCard);
        count++;

        tempCard = new CardClass(count, "Zero Day", "Attack", "Deal a Large Amount of Damage to the Enemy While Also Taking Away Resources",
                new CardResource(new ResourceClass(0, -10, 0, 0, 0, 0, 0), new ResourceClass(20, 0, 0, 0, -1, -5, -100)));
        cards.add(tempCard);
        count++;

        return cards.toArray(new CardClass[0]);
    }
}