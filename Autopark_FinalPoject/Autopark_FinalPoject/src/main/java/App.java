import DAO.BusDAO;
import DAO.DriverDAO;
import DAO.VisitorDAO;
import Model.Bus;
import Model.Driver;
import Model.Visitor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class App {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        System.out.println("Hello World");

//        BusDAO theBusDAO = new BusDAO();
//        ArrayList<Bus> buses = theBusDAO.findAll();
//        System.out.println(buses.size());
//        Driver theDriver = Driver.newBuilder().setDriverID("HFJ3i6554").setDriverName("Stepan Ivanenko").build();
//        Visitor theVisitor = Visitor.newBuilder().setVisitorID(5).setVisitorName("Stepan Ivanenko")
//                .setVisitorLogin("Stepan").setVisitorPassword("1234").setVisitorRole("Driver").setDriver(theDriver)
//                .build();
        // VisitorDAO visitorDAO = new VisitorDAO();

        DriverDAO diverDAO = new DriverDAO();
        ArrayList<Driver> drivers = diverDAO.findAll();
        for (int i = 0; i < drivers.size(); i++) {
            System.out.println(drivers.get(i).toString());
        }
    }
}
