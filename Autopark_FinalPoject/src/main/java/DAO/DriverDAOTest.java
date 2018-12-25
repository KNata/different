package DAO;

import Model.Driver;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class DriverDAOTest {

    private DriverDAO driverDao;

    @Before
    public void setUp() throws Exception {
        driverDao = new DriverDAO();
    }

    @Test
    public void addRecord() throws SQLException {
        Driver theDriver = Driver.newBuilder().setDriverID("ДЛ6739").setDriverName("Наталія Кіселикt").build();
        assertTrue(driverDao.addRecord(theDriver));
    }

    @Test
    public void deleteRecord() throws SQLException {
        assertTrue(driverDao.deleteRecord("Наталія Кіселик"));
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