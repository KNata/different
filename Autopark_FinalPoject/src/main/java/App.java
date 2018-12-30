import DAO.BusDAO;
import DAO.DriverDAO;
import DAO.VisitorDAO;
import Model.Bus;
import Model.Driver;
import Model.Visitor;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
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
//         VisitorDAO visitorDAO = new VisitorDAO();
//        // Visitor theVisitor = visitorDAO.findByLoginAndPassword("bodia","4321");
//         System.out.println(theVisitor.toString());


//        DriverDAO diverDAO = new DriverDAO();
//        ArrayList<Driver> drivers = diverDAO.findAll();
//        for (int i = 0; i < drivers.size(); i++) {
//            System.out.println(drivers.get(i).toString());
//        }

        String strDate = "2018-11-11 22:00:00";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        java.util.Date date = null;
        try {
            date = sdf.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Date sqlDate = new Date(date.getTime());
        System.out.println("String converted to java.sql.Date :" + date);



       // System.out.println("String converted to java.sql.Date :" + sqlDate.toString());
       // DateFormat df = new SimpleDateFormat("dd/MM/YYYY - hh:mm:ss");

      //  System.out.println("Using a dateFormat date is : " + df.format(sqlDate));

    }
}
