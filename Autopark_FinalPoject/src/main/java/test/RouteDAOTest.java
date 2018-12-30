package test;

import DAO.BusDAO;
import DAO.DriverDAO;
import DAO.RouteDAO;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class RouteDAOTest {

    private RouteDAO routeDAO;

    @Before
    public void setUp() throws Exception {
        routeDAO = new RouteDAO();
    }

    @Test
    public void deleteRecord() throws SQLException {
        assertTrue(routeDAO.deleteRecord("3"));
    }

    @Test
    public void findAll() throws SQLException {
        assertNotNull(routeDAO.findAll());
    }

    @Test
    public void findByID() throws SQLException{
        assertNotNull(routeDAO.findByID(String.valueOf(1)));
    }

    @Test
    public void findByName() throws SQLException {
        assertNotNull(routeDAO.findByName("Kyiv-Lviv"));
    }

}