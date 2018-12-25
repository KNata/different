package DBConnection;

import org.apache.commons.dbcp.BasicDataSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionPool {

    private static BasicDataSource ds = new BasicDataSource();
    private static Properties property = new Properties();


    static {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("/Users/nataliakiselyk/Documents/GitHub/JavaExternal/Autopark_FinalPoject/src/main/resources/databaseProperties.properties");
            property.load(fis);
            ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
            ds.setUrl(property.getProperty("url"));
            ds.setUsername(property.getProperty("user"));
            ds.setPassword(property.getProperty("password"));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
            }
        }
    }


    public static Connection getConnection() throws SQLException {
        Connection connection = ds.getConnection();
        return connection;
    }


}
