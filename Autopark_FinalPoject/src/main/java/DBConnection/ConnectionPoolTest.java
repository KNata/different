package DBConnection;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class ConnectionPoolTest {

    @org.junit.Before
    public void setUp() throws Exception {
    }

    @org.junit.Test
    public void getConnection() throws SQLException {
        assertNotNull(ConnectionPool.getConnection());
    }
}