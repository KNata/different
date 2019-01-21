package Servlets;

import DAO.BusDAO;
import DAO.DriverDAO;
import DAO.RouteDAO;
import Model.Bus;
import Model.Driver;
import Model.Route;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

@WebServlet(name = "RouteServlet", urlPatterns = "/RouteServlet")
public class RouteServlet extends HttpServlet {

    private RouteDAO routeDAO = new RouteDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        showAllRoutes(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        System.out.println(action);
        switch (action) {
            case "addNewRoute":
                addNewRoute(request, response);
                break;
            case "removeRoute":
                deleteRoute(request, response);
                break;
            case "edit":
                editRoute(request, response);
                break;
        }

    }

    private void showAllRoutes(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ArrayList<Route> routeList = routeDAO.findAll();
        if (routeList.size() == 0) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/commonView/errorPage.jsp");
            dispatcher.forward(request, response);
        }
        String nextJSP = "/views/adminView/seeAllRoutes.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        request.setAttribute("routeList", routeList);
        dispatcher.forward(request, response);
    }

    private void searchDriverByID (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String routeID = request.getParameter("idRoute");
        Route theRoute = routeDAO.findByID(routeID);
        request.setAttribute("route", theRoute);
        request.setAttribute("action", "edit");
        String nextJSP = "/adminView/addNewRoutePage.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        dispatcher.forward(request, response);
    }


    private void addNewRoute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String routeID = request.getParameter("idRoute");
        String routeTitle = request.getParameter("routeName");
        String busID = request.getParameter("busID");
        String driverID = request.getParameter("driverID");
        String cityOfDeparture = request.getParameter("cityOfDeparture");
        String cityOfArrival = request.getParameter("cityOfArrival");
        String routeDuration = request.getParameter("routeDuration");
        String departureTime = request.getParameter("departureTime");
        String arrivalTime = request.getParameter("arrivalTime");
        java.sql.Date departureTimeInDateFormat = convertDate(departureTime);
        java.sql.Date arrivalTimeInDateFormat = convertDate(arrivalTime);

        if (routeID != null && busID != null && driverID != null && routeTitle != null && cityOfArrival != null && cityOfDeparture != null
                && routeDuration != null && departureTime != null && arrivalTime != null && (departureTime != arrivalTime)
                && (departureTimeInDateFormat.getTime() < arrivalTimeInDateFormat.getTime())) {
            BusDAO busDAO = new BusDAO();
            DriverDAO driverDAO = new DriverDAO();
            if (busDAO.findByID(busID) != null && driverDAO.isDriverInSystem(driverID)) {
                if(routeDAO.findByID(routeID) == null && (!routeDAO.findByID(routeID).getRouteStartTime().equals(departureTimeInDateFormat)) && (!routeDAO.findByID(routeID).getDriverID().equals(driverID))) {
                Driver theDriver = driverDAO.findByID(driverID);
                Bus theBus = busDAO.findByID(busID);
                Route theRoute = Route.newBuilder().setRouteID(Integer.valueOf(routeID)).setRouteTitle(routeTitle).setBusID(busID)
                        .setDriver(driverID).setRouteBegin(cityOfDeparture).setRouteEnd(cityOfArrival)
                        .setRouteDuration(Integer.valueOf(routeDuration)).setRouteStartTime(departureTime).setRouteEndTime(arrivalTime).build();
                boolean wasAdded = routeDAO.addRecord(theRoute);
                if (wasAdded) {
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/commonView/successPage.jsp");
                    dispatcher.forward(request, response);
                } else {
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/commonView/errorPage.jsp");
                    dispatcher.forward(request, response);
                } }
            } else {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/commonView/errorPage.jsp");
                dispatcher.forward(request, response);
            }
      }

    }


    private void deleteRoute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String routeID = request.getParameter("idRoute");
        System.out.println("idRoute " + routeID);
        if (routeID != null && routeDAO.findByID(routeID) != null) {
            System.out.println("1");
            boolean wasDeleted = routeDAO.deleteRecord(routeID);
            System.out.println(wasDeleted + "was deleted");
            if (wasDeleted) {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/commonView/successPage.jsp");
                dispatcher.forward(request, response);
                PrintWriter out= response.getWriter();
                out.println("<font color=red>Done.</font>");
                dispatcher.include(request, response);
            } else {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/commonView/errorPage.jsp");
                dispatcher.forward(request, response);
                PrintWriter out= response.getWriter();
                out.println("<font color=red>Fail.</font>");
                dispatcher.include(request, response);
            }
        } else {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/adminView/deleteVisitor.jsp");
            dispatcher.forward(request, response);
            PrintWriter out= response.getWriter();
            out.println("<font color=red>Fail.</font>");
            dispatcher.include(request, response);
        }
    }

    private void editRoute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String routeID = request.getParameter("idRoute");
        String busID = request.getParameter("busID");
        String driverID = request.getParameter("driverID");
        String departureTime = request.getParameter("departureTime");
        String arrivalTime = request.getParameter("arrivalTime");
        int duration = Integer.valueOf(request.getParameter("routeDuration"));

        System.out.println("Route ID " + routeID);
        System.out.println("Bus ID " + busID);
        System.out.println("Driver ID " + driverID);
        System.out.println("departureTime " + departureTime);
        System.out.println("arrivalTime " + arrivalTime);
        System.out.println("duration " + duration);

        BusDAO busDAO = new BusDAO();
        DriverDAO driverDAO = new DriverDAO();
        if (routeID != null && busID != null && driverID != null && departureTime != null && arrivalTime != null
                && duration != 0 && busDAO.findByID(busID) != null && driverDAO.findByID(driverID) != null && routeDAO.findByID(routeID) != null) {
            boolean wasUpdated = routeDAO.update(Integer.valueOf(routeID), driverID, busID, departureTime, arrivalTime, duration);
            System.out.println(wasUpdated);
            if (wasUpdated) {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/commonView/successPage.jsp");
                dispatcher.forward(request, response);
            } else {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/commonView/errorPage.jsp");
                dispatcher.forward(request, response);
                System.out.println("Error because update procedure was failed");
            }
        } else {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/commonView/errorPage.jsp");
            dispatcher.forward(request, response);
            System.out.println("Error inside verification block");
        }
}

    private void showDriverStory(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ArrayList<Route> driverStoryList = routeDAO.showDriverInfo();
        System.out.println(driverStoryList.size());
        request.setAttribute("routeStoryList", driverStoryList);
    }


    private java.sql.Date convertDate (String stringToConvert) {
        java.sql.Date resultDate = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        java.util.Date date = null;
        try {
            date = sdf.parse(stringToConvert);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        resultDate = new Date(date.getTime());
        return resultDate;
    }
}
