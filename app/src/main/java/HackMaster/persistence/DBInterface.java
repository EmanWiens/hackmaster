package HackMaster.persistence;

import HackMaster.objects.CardClass;

/**
 * Created by Owner on 2/18/2018.
 */

public interface DBInterface {
    CardClass[] getCards();
    // TODO write the database interface and make the functions CardsList and real DB
}
