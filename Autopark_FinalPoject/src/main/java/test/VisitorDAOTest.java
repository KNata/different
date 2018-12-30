package test;

import DAO.DriverDAO;
import DAO.VisitorDAO;
import Model.Driver;
import Model.Visitor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.sql.DataSource;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class VisitorDAOTest {

    @Mock
    private DataSource dataSource;
    @Mock
    private Connection connection;
    @Mock
    private PreparedStatement stmt;
    @Mock
    private ResultSet resultSet;

    private Visitor theVisitor;
    private VisitorDAO visitorDAO;

    @Before
    public void setUp() throws Exception {
        visitorDAO = new VisitorDAO();
        Assert.assertNotNull(dataSource);
        when(connection.prepareStatement(any(String.class))).thenReturn(stmt);
        when(dataSource.getConnection()).thenReturn(connection);

        Driver theDriver = Driver.newBuilder().setDriverID("999").setDriverName("Someone Someone").build();
        DriverDAO driverDAO = new DriverDAO();
        driverDAO.addRecord(theDriver);
        theVisitor = Visitor.newBuilder().setVisitorID(15).setVisitorName("Someone Someone")
                .setVisitorLogin("Someone").setVisitorPassword("9999").setVisitorRole("Driver").setDriver(theDriver)
                .build();
        when(resultSet.first()).thenReturn(true);
        when(resultSet.getInt(1)).thenReturn(theVisitor.getVisitorID());
        when(resultSet.getString(2)).thenReturn(theVisitor.getVisitorLogin());
        when(resultSet.getString(3)).thenReturn(theVisitor.getVisitorPassword());
        when(resultSet.getString(4)).thenReturn(theVisitor.getVisitorRole());
        when(resultSet.getString(5)).thenReturn(theVisitor.getVisitorName());
        when(resultSet.getString(6)).thenReturn(theVisitor.getTheDriver().getDriverID());

        when(stmt.executeQuery()).thenReturn(resultSet);    }

    @Test
    public void addRecord() throws SQLException {
        Driver theDriver = Driver.newBuilder().setDriverID("677777").setDriverName("Ivan Ivan").build();
        DriverDAO driverDAO = new DriverDAO();
        driverDAO.addRecord(theDriver);
        Visitor theVisitor = Visitor.newBuilder().setVisitorID(14).setVisitorName("Ivan Ivan")
                .setVisitorLogin("ivann").setVisitorPassword("8888").setVisitorRole("Driver").setDriver(theDriver)
                .build();
        assertTrue(visitorDAO.addRecord(theVisitor));
    }

    @Test
    public void deleteRecord() throws SQLException {
        assertTrue(visitorDAO.deleteRecord("9"));
    }

    @Test
    public void findAll() throws SQLException {
        assertNotNull(visitorDAO.findAll());
    }

    @Test
    public void findByID() throws SQLException {
        assertNotNull(visitorDAO.findByLogin("bodia"));
    }

    @Test
    public void findByLoginAndPassword() throws SQLException {
        assertNotNull(visitorDAO.findByLoginAndPassword("bodia", "4321"));
    }

    @Test
    public void findByName() throws SQLException {
        //assertNotNull(visitorDAO.findByName("Bogdan Zatorsky"));
    }

    @Test
    public void update() {
        assertTrue(visitorDAO.update("bodia", "1234"));
    }

    @Test
    public void updateForAdmin() {
        assertTrue(visitorDAO.updateForAdmin("bodia", "0987", "admin"));
    }

}