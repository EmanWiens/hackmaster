package hackmasterOG.persistence;

import java.sql.Statement;

public interface DBInterface {
    void open(String string);
    void close();
    Statement getNewStatement();
}
