package DAO;

import Model.Driver;
import Model.Visitor;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class VisitorDAOTest {

    private VisitorDAO visitorDAO;

    @Before
    public void setUP(){
        visitorDAO = new VisitorDAO();
    }

    @Test
    public void addRecord() throws SQLException {
        Driver theDriver = Driver.newBuilder().setDriverID("677777").setDriverName("Ivan Ivan").build();
        DriverDAO driverDAO = new DriverDAO();
        driverDAO.addRecord(theDriver);
        Visitor theVisitor = Visitor.newBuilder().setVisitorID(9).setVisitorName("Ivan Ivan")
                .setVisitorLogin("ivann").setVisitorPassword("8888").setVisitorRole("Driver").setDriver(theDriver)
                .build();
        assertTrue(visitorDAO.addRecord(theVisitor));
    }

    @Test
    public void deleteRecord()  throws SQLException{
        assertTrue(visitorDAO.deleteRecord("9"));
    }

    @Test
    public void findAll() throws SQLException{
        assertNotNull(visitorDAO.findAll());
    }

    @Test
    public void findByID() throws SQLException{
        assertNotNull(visitorDAO.findByLogin("bodia"));
    }

    @Test
    public void findByName() throws SQLException {
        //assertNotNull(visitorDAO.findByName("Bogdan Zatorsky"));
    }
}