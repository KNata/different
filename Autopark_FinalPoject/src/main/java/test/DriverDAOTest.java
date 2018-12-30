package test;

import DAO.DriverDAO;
import Model.Bus;
import Model.Driver;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DriverDAOTest {

    @Mock
    private DataSource dataSource;
    @Mock
    private Connection connection;
    @Mock
    private PreparedStatement stmt;
    @Mock
    private ResultSet resultSet;

    private Driver theDriver;
    private DriverDAO driverDao;

    @Before
    public void setUp() throws Exception {
        driverDao = new DriverDAO();
        Assert.assertNotNull(dataSource);
        when(connection.prepareStatement(any(String.class))).thenReturn(stmt);
        when(dataSource.getConnection()).thenReturn(connection);

        theDriver = Driver.newBuilder().setDriverID("КРА6539").setDriverName("Джон Рузвельт").build();
        when(resultSet.first()).thenReturn(true);
        when(resultSet.getString(1)).thenReturn(theDriver.getDriverID());
        when(resultSet.getString(2)).thenReturn(theDriver.getDriverName());
        when(stmt.executeQuery()).thenReturn(resultSet);    }

    @Test
    public void addRecord() throws SQLException {
        theDriver = Driver.newBuilder().setDriverID("ДЛ67395").setDriverName("Наталія Кіселикк").build();
        assertTrue(driverDao.addRecord(theDriver));
    }

    @Test
    public void deleteRecord() throws SQLException {
        assertTrue(driverDao.deleteRecord("Наталія Кіселикк"));
    }

    @Test
    public void findAll() throws SQLException {
        assertNotNull(driverDao.findAll());
    }

    @Test
    public void findByID() throws SQLException {
        assertNotNull(driverDao.findByID("RD676"));
    }

    @Test
    public void findByName() throws SQLException {
        assertNotNull(driverDao.findByName("Bogdan Zatorsky"));
    }
}