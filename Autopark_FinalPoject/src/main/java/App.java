import DAO.BusDAO;
import DAO.VisitorDAO;
import Model.Bus;
import Model.Driver;
import Model.Visitor;

import java.sql.SQLException;
import java.util.ArrayList;

public class App {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        System.out.println("Hello World");

        BusDAO theBusDAO = new BusDAO();
        ArrayList<Bus> buses = theBusDAO.findAll();
        System.out.println(buses.size());
        Driver theDriver = Driver.newBuilder().setDriverID("HFJ3i6554").setDriverName("Stepan Ivanenko").build();
        Visitor theVisitor = Visitor.newBuilder().setVisitorID(5).setVisitorName("Stepan Ivanenko")
                .setVisitorLogin("Stepan").setVisitorPassword("1234").setVisitorRole("Driver").setDriver(theDriver)
                .build();
        VisitorDAO visitorDAO = new VisitorDAO();
        System.out.println(visitorDAO.addRecord(theVisitor));

    }
}
