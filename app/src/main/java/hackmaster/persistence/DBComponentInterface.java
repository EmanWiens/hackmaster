package hackmaster.persistence;

import java.sql.Statement;

public interface DBComponentInterface {
    void open(Statement statement);
    void close();
}
