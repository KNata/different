import DAO.BusDAO;
import DAO.DriverDAO;
import DAO.RouteDAO;
import DAO.VisitorDAO;
import Model.Bus;
import Model.Driver;
import Model.Route;
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
//        Driver theDriver = Driver.newBuilder().setDriverID("HFJ3i6554").setDriverID("Stepan Ivanenko").build();
//        Visitor theVisitor = Visitor.newBuilder().setVisitorID(5).setVisitorName("Stepan Ivanenko")
//                .setVisitorLogin("Stepan").setVisitorPassword("1234").setVisitorRole("Driver").setDriver(theDriver)
//                .build();
//         VisitorDAO visitorDAO = new VisitorDAO();
//        // Visitor theVisitor = visitorDAO.findByLoginAndPassword("bodia","4321");
//         System.out.println(theVisitor.toString());


            VisitorDAO visitorDAO = new VisitorDAO();
        boolean wasUpdated = visitorDAO.findByID("3");
        if (wasUpdated) {
            System.out.println("OK");
        } else {
            System.out.println("-");
        }

        //        ArrayList<Route> routeList= diverDAO.findAll();
//        System.out.println(routeList.size());
//        for (int i = 0; i < routeList.size(); i++) {
//            System.out.println(routeList.get(i).toString());
//        }

//        for (int i = 0; i < routeList.size(); i++) {
//            System.out.println(routeList.get(i).getRouteID());
//            System.out.println(routeList.get(i).getRouteTitle());
//            System.out.println(routeList.get(i).getDriver().getDriverID());
//            System.out.println(routeList.get(i).getBus().getBusID());
//            System.out.println(routeList.get(i).getRouteBegin());
//            System.out.println(routeList.get(i).getRouteEnd());
//            System.out.println(routeList.get(i).getRouteStartTime());
//            System.out.println(routeList.get(i).getRouteEndTime());
//            System.out.println(routeList.get(i).getRouteDuration());
//            System.out.println();
//
//
//        }

//            VisitorDAO visitorDAO = new VisitorDAO();
//            boolean status = visitorDAO.update("ЛЕ2478", "12345678");
//            System.out.println(status);
//        boolean editVisitor = visitorDAO.updateForAdmin("oleksii", "4567", "DRIVER");
//        if (editVisitor) {
//            System.out.println("ok");
//        } else {
//            System.out.println("false");
//        }
//            RouteDAO routeDAO  = new RouteDAO();
//            routeDAO.setDriverID("ЛЕ2478");
//            ArrayList<Route> routeList = routeDAO.showDriverInfo();
//            for (int i = 0; i < routeList.size(); i++) {
//                System.out.println(routeList.get(i).toString());
//            }



//        ArrayList<Visitor> resList = visitorDAO.findAll();
//        for (int i = 0; i < resList.size(); i++) {
//            System.out.println(resList.get(i).toString());
//        }
//        Visitor theVisitor = visitorDAO.findByLogin("oleksii");
//        System.out.println(theVisitor.toString());
//        String strDate = "2018-11-11 22:00:00";
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
//        java.util.Date date = null;
//        try {
//            date = sdf.parse(strDate);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        java.sql.Date sqlDate = new Date(date.getTime());
//        System.out.println("String converted to java.sql.Date :" + date);
//
       // System.out.println("..".isEmpty());


       // System.out.println("String converted to java.sql.Date :" + sqlDate.toString());
       // DateFormat df = new SimpleDateFormat("dd/MM/YYYY - hh:mm:ss");

      //  System.out.println("Using a dateFormat date is : " + df.format(sqlDate));


    }
}
