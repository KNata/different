package DAO;

import Model.Bus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.sql.SQLException;

import static org.junit.Assert.*;

//@RunWith(MockitoJUnitRunner.class)
public class BusDAOTest {

    private BusDAO busDao;

    @Before
    public void setUp() throws Exception {
        busDao = new BusDAO();
    }

    @Test
    public void addRecord() throws SQLException {
        Bus theBus = Bus.newBuilder().setBusID("MK8988").setBusModel("Scania").setmaxCountOfPassagers(150)
                .setMiles(3222).setPassedService(false).build();
        assertTrue(busDao.addRecord(theBus));
    }

    @Test
    public void deleteRecord() throws SQLException {
        assertTrue(busDao.deleteRecord("NM4673"));
    }

    @Test
    public void findAll() throws SQLException {
        assertNotNull(busDao.findAll());
    }

    @Test
    public void findByID() throws SQLException {
        assertNotNull(busDao.findByID("KI677"));
    }

    @Test
    public void findByName() throws SQLException {
        assertNotNull(busDao.findByName("Bogdan"));
    }
}