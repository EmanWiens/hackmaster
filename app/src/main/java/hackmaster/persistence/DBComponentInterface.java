package hackmaster.persistence;

import java.sql.Statement;

/**
 * The interface for a component class, which is a part of database access
 */
public interface DBComponentInterface {
    void open(Statement statement);
    void close();
}
