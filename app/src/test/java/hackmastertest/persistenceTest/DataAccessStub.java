package hackmastertest.persistenceTest;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import hackmaster.objects.CardClass;
import hackmaster.objects.PlayerStatsSaves;
import hackmaster.objects.ResourceClass;
import hackmaster.persistence.CardDataAccessInterface;
import hackmaster.persistence.DBInterface;
import hackmaster.persistence.PlayerDataAccessInterface;


public class DataAccessStub implements DBInterface, PlayerDataAccessInterface, CardDataAccessInterface {
    private String dbName;
    private String dbType = "stub";

    private ArrayList<PlayerStatsSaves> players;
    private ArrayList<CardClass> cards;

    public DataAccessStub(String dbName) { this.dbName = dbName; }

    public void open(String dbPath) {
        PlayerStatsSaves player;
        CardClass tempCard;

        players = new ArrayList<PlayerStatsSaves>();
        player = new PlayerStatsSaves(100, "Gary Chalmers", 0, 0, 0, 0);
        players.add(player);
        player = new PlayerStatsSaves(200, "Selma Bouvier", 1, 1, 2, 1);
        players.add(player);
        player = new PlayerStatsSaves(300, "Arnie Pye", 50, 20, 70, 20);
        players.add(player);
        player = new PlayerStatsSaves(400, "Bailey Bailey", 100, 100, 200, 30);
        players.add(player);

        cards = new ArrayList<CardClass>();
        tempCard = new CardClass(0, "CPU Boost", "Upgrade", "Upgrade your CPU",
                new ResourceClass(0, -5, 1, 0, 0,0, 0),  new ResourceClass(-10, 0, 0, 0, 0,0, 0));
        cards.add(tempCard);
        tempCard = new CardClass(1, "More Cores", "Defense", "Upgrade your CPU",
                new ResourceClass(0, -5, 1, 0, 0,0, 0), new ResourceClass(-10, 0, 0, 0, 0,0, 0));
        cards.add(tempCard);
        tempCard = new CardClass(2, "bot.net", "Upgrade", "Upgrade your CPU",
                new ResourceClass(0, 0, 0, -5, 0,0, 1), null);
        cards.add(tempCard);
        tempCard = new CardClass(3, "Сut some wires", "Defense", "Upgrade your CPU",
                new ResourceClass(0, 0, 0, -5, 0,0, 0), new ResourceClass(-10, 0, 0, 0, 0,0, 0));
        cards.add(tempCard);
        tempCard = new CardClass(4, "Upgrade Botnet", "Upgrade", "Increase Botnet Rate",
                new ResourceClass(0, -2, 0, 0, 0, 0, 1), null);
        cards.add(tempCard);
        tempCard = new CardClass(5, "Upgrade CPU", "Upgrade", "Increase CPU Calculations",
                new ResourceClass(0, -5, 0, 0, 0, 10, 0), null);
        cards.add(tempCard);
        tempCard = new CardClass(6, "Upgrade Hash Rate", "Upgrade", "Increase Cryptocurrency Mining Rate",
                new ResourceClass(0, -2, 0, +2, 0, 0, 0), null);
        cards.add(tempCard);
        tempCard = new CardClass(7, "DDOS", "Attack", "Deals a Decent Amount of Damage to the Enemy",
                new ResourceClass(0, 0, 0, -6, 0, 0, 0), new ResourceClass(-15, 0, 0, 0, 0, 0, 0));
        cards.add(tempCard);
        tempCard = new CardClass(8, "File Transfer", "Attack", "Steal Enemy Resources",
                new ResourceClass(0, 5, 1, -4, 0, 0, 0), new ResourceClass(-1, -5, -1, -2, 0, 0, 0));
        cards.add(tempCard);
        tempCard = new CardClass(9, "Pop-up", "Attack", "Deals a Small Amount of Damage to the Enemy",
                new ResourceClass(0, 0, 0, -1, 0, 0, 0), new ResourceClass(-5, 0, 0, 0, 0, 0, 0));
        cards.add(tempCard);
        tempCard = new CardClass(10, "Anti-Virus", "Defence", "Gain a Small Amount of Health",
                new ResourceClass(10, 0, -2, 0, 0, 0, 0), null);
        cards.add(tempCard);
        tempCard = new CardClass(11, "Firewall", "Defence", "Gain a Decent Amount of Health",
                new ResourceClass(20, 0, -2, 0, 0, 0, 0), null);
        cards.add(tempCard);
        tempCard = new CardClass(12, "Play the Market", "Upgrade", "Spend 5 Cryptocoins +2 HCoinRate",
                new ResourceClass(0, -5, 2, 0, 0, 0, 0), null);
        cards.add(tempCard);
        tempCard = new CardClass(13, "Overclock", "Upgrade", "-10 Health +2hCoinRate",
                new ResourceClass(-10, 0, 2, 0, 0, 0, 0), null);
        cards.add(tempCard);
        tempCard = new CardClass(14, "Server Farm", "Upgrade", "Spend 2 Cryptocoins gain 2 Botnets",
                new ResourceClass(0, -2, -1, 2, 2, 0, 0), null);
        cards.add(tempCard);
        tempCard = new CardClass(15, "Expand", "Upgrade", "Increase the Rate of All Resources",
                new ResourceClass(0, -6, 0, 0, 1, 4, 1), null);
        cards.add(tempCard);
        tempCard = new CardClass(16, "Market Crash", "Upgrade", "Enemy Loses 20 Cryptocoins",
                new ResourceClass(0, -10, 0, 0, 0, 0, 0), new ResourceClass(0, -20, -1, 0, 0, 0, 0));
        cards.add(tempCard);
        tempCard = new CardClass(17, "Network Outage", "Upgrade", "Enemy Loses 10 Botnets",
                new ResourceClass(0, -5, 0, 0, 0, 0, 0), new ResourceClass(0, 0, 0, -10, 0, 0, 0));
        cards.add(tempCard);
        tempCard = new CardClass(18, "Throttle", "Upgrade", "Enemy Loses 20 Processing Power",
                new ResourceClass(0, -10, 0, 0, 0, 0, 0), new ResourceClass(0, 0, -3, 0, 0, 0, 0));
        cards.add(tempCard);
        tempCard = new CardClass(19, "Hack!!", "Attack", "Deal a Small Amount of Damage to the Enemy",
                new ResourceClass(0, 0, 0, -10, 0, 0, 0), new ResourceClass(-20, 0, 0, 0, 0, 0, 0));
        cards.add(tempCard);
        tempCard = new CardClass(20, "Debug", "Attack", "Gain a Small Amount of Health",
                new ResourceClass(0, 5, 0, -10, 0, 0, 0), new ResourceClass(-20, 0, 0, 0, 0, 0, 0));
        cards.add(tempCard);
        tempCard = new CardClass(21, "Exploit", "Attack", "Gain a Small Amount of Health and Deal Damage to the Enemy",
                new ResourceClass(5, 5, 0, -15, 0, 0, 0), new ResourceClass(-25, 0, 0, 0, 0, 0, 0));
        cards.add(tempCard);
        tempCard = new CardClass(22, "Zero Day", "Attack", "Deal a Large Amount of Damage to the Enemy While Also Taking Away Resources",
                new ResourceClass(0, 5, 0, -10, 0, 0, 0), new ResourceClass(-10, 0, 0, 0, -2, -2, -2));
        cards.add(tempCard);
        tempCard = new CardClass(23, "Attack+", "Attack", "Deal a Decent Amount of Damage to the Enemy",
                new ResourceClass(0, -5, 0, 0, 0, 0, 0), new ResourceClass(-10, 0, 0, 0, 0, 0, 0));
        cards.add(tempCard);
        tempCard = new CardClass(24, "Attack++", "Attack", "Deal a Decent Amount of Damage to the Enemy",
                new ResourceClass(0, -6, 0, 2, 0, 0, 0), new ResourceClass(-15, 0, 0, 0, 0, 0, 0));
        cards.add(tempCard);
        tempCard = new CardClass(25, "Attack#", "Attack", "Deal a Decent Amount of Damage to the Enemy",
                new ResourceClass(0, 4, 0, -5, 0, 0, 0), new ResourceClass(-5, 0, 0, 0, 0, 0, 0));
        cards.add(tempCard);
        tempCard = new CardClass(26, "Extreme Hack", "Attack", "Extreme Use of CPU",
                new ResourceClass(10, 0, 0, 0, 0, -50, 0), new ResourceClass(-30, 0, 0, 0, 0, 0, 0));
        cards.add(tempCard);
        tempCard = new CardClass(27, "Epic Hack", "Attack", "Extreme Use of CPU",
                new ResourceClass(0, 0, 0, 0, 0, -30, 0), new ResourceClass(-30, 0, 0, 0, 0, 0, 0));
        cards.add(tempCard);
        tempCard = new CardClass(28, "Mass Hack", "Attack", "Extreme Use of CPU",
                new ResourceClass(20, 0, 0, 0, 0, -50, 0), new ResourceClass(-25, 0, 0, 0, 0, 0, 0));
        cards.add(tempCard);
    }

    public void close() { }


    @Override
    public void open(Statement statement) { }

    @Override
    public Statement getNewStatement() {
        return null;
    }

    public String getPlayerSequential(List<PlayerStatsSaves> playerResult) {
        String result = null;
        playerResult.addAll(players);
        return result;
    }

    public String getPlayersNamesList(List<String> playerResult) {
        String result = null;
        for(PlayerStatsSaves player : players) playerResult.add(player.getName());
        return result;
    }

    public ArrayList<PlayerStatsSaves> getPlayerRandom(int playerID) {
        ArrayList<PlayerStatsSaves> player = new ArrayList<PlayerStatsSaves>();
        for(PlayerStatsSaves playerNode : players) {
            if(playerNode.getPlayerID()==playerID) player.add(playerNode);
        }
        return player;
    }

    public String insertPlayer(PlayerStatsSaves player){
        String result = null;
        players.add(player);
        return result;
    }

    public String updatePlayer(PlayerStatsSaves player) {
        String result = null;
        int index = players.indexOf(player);
        if (index >= 0) {
            players.set(index, player);
        }
        return result;
    }

    public String removePlayer(int playerID) {
        String result = null;
        PlayerStatsSaves player = new PlayerStatsSaves(playerID);
        int index = players.indexOf(player);
        if (index >= 0)
        {
            players.remove(index);
        }
        return result;
    }

    public String getCardSequential(List<CardClass> cardResult) {
        String result = null;
        cardResult.addAll(cards);
        return result;
    }

    public String getRandomDeck(List<CardClass> cardResult, Random random) {
        String result = null;
        cardResult.addAll(cards);
        for (int i = cardResult.size() - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            CardClass card = cardResult.get(index);
            cardResult.set(index, cardResult.get(i));
            cardResult.set(i, card);
        }
        return result;
    }

    public ArrayList<CardClass> getCardRandom(int cardID) {
        ArrayList<CardClass> card = new ArrayList<CardClass>();
        for(CardClass cardNode : cards) {
            if(cardNode.getID()==cardID) card.add(cardNode);
        }
        return card;
    }

    public String insertCard(CardClass card) {
        String result = null;
        cards.add(card);
        return result;
    }

    public String updateCard(CardClass card) {
        String result = null;
        int index = cards.indexOf(card);
        if (index >= 0) {
            cards.set(index, card);
        }
        return result;
    }

    public String removeCard(int cardID) {
        String result = null;
        CardClass card = new CardClass(cardID);
        int index = cards.indexOf(card);
        if (index >= 0) {
            cards.remove(index);
        }
        return result;
    }
}
