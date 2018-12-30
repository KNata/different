package test;

import DAO.BusDAO;
import Model.Bus;
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
public class BusDAOTest {

    @Mock
    private DataSource dataSource;
    @Mock
    private Connection connection;
    @Mock
    private PreparedStatement stmt;
    @Mock
    private ResultSet resultSet;

    private Bus theBus;
    private BusDAO busDAO;


    @Before
    public void setUp() throws Exception {
        busDAO = new BusDAO();
        Assert.assertNotNull(dataSource);
        when(connection.prepareStatement(any(String.class))).thenReturn(stmt);
        when(dataSource.getConnection()).thenReturn(connection);

        theBus = Bus.newBuilder().setBusID("GTK6543").setBusModel("Scania").setPassedService(true).setMiles(1000)
                .setmaxCountOfPassagers(70).build();
        when(resultSet.first()).thenReturn(true);
        when(resultSet.getString(1)).thenReturn(theBus.getBusID());
        when(resultSet.getString(2)).thenReturn(theBus.getBusModel());
        when(resultSet.getBoolean(3)).thenReturn(theBus.isPassedService());
        when(resultSet.getInt(4)).thenReturn(theBus.getMaxCountOfPassagers());
        when(resultSet.getInt(5)).thenReturn(theBus.getMiles());
        when(stmt.executeQuery()).thenReturn(resultSet);
    }

    @Test
    public void addRecord() throws SQLException {
        Bus theBus = Bus.newBuilder().setBusID("MK89888").setBusModel("Scania").setmaxCountOfPassagers(150)
                .setMiles(3222).setPassedService(false).build();
        assertTrue(busDAO.addRecord(theBus));
    }

    @Test
    public void deleteRecord() throws SQLException {
        assertTrue(busDAO.deleteRecord("NM4673"));
    }

    @Test
    public void findAll() throws SQLException {
        assertNotNull(busDAO.findAll());
    }

    @Test
    public void findByID() throws SQLException {
        assertNotNull(busDAO.findByID("KI677"));
    }

    @Test
    public void findByName() throws SQLException {
        assertNotNull(busDAO.findByName("Bogdan"));
    }

    @Test
    public void update() throws SQLException {
        assertTrue(busDAO.update("KI677", 90, false));
    }
}