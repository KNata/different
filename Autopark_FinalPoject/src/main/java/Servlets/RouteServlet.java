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
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

@WebServlet(name = "RouteServlet", urlPatterns = "/RouteServlet")
public class RouteServlet extends HttpServlet {

    private RouteDAO routeDAO = new RouteDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("searchAction");
        if (action != null) {
            switch (action) {
                case "searchById":
                    searchDriverByID(request, response);
                    break;
                case "searchByName":
                    searchRouteByName(request, response);
                    break;
                case "showAllRoutes":
                    searchRouteByName(request, response);
                    break;
            }
        }else{
            ArrayList<Route> resultList = routeDAO.findAll();
            forwardListRoute(request, response, resultList);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        System.out.println(action);
        switch (action) {
            case "addNewRoute":
                addNewRoute(request, response);
                break;
            case "remove":
                deleteDriver(request, response);
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
        String nextJSP = "/views/adminView/seeAllDriversPage.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        request.setAttribute("routeList", routeList);
        dispatcher.forward(request, response);
    }

    private void forwardListRoute(HttpServletRequest request, HttpServletResponse response, ArrayList<Route> routeList) throws IOException, ServletException {
        String nextJSP = "/views/adminView/seeAllDriversPage.jsp";
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

    private void searchRouteByName(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String routeName = request.getParameter("driverName");
        Route theRoute = routeDAO.findByName(routeName);
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
                if(routeDAO.findByID(routeID) == null && (routeDAO.findByID(routeID).getRouteStartTime() != departureTimeInDateFormat) && (routeDAO.findByID(routeID).getDriverID() != driverID)) {
                Driver theDriver = driverDAO.findByID(driverID);
                Bus theBus = busDAO.findByID(busID);
                Route theRoute = Route.newBuilder().setRouteID(Integer.valueOf(routeID)).setRouteTitle(routeTitle).setBus(theBus.getBusID())
                        .setDriver(theDriver.getDriverID()).setRouteBegin(cityOfDeparture).setRouteEnd(cityOfArrival)
                        .setRouteDuration(Integer.valueOf(routeDuration)).setRouteStartTime(departureTimeInDateFormat).setRouteEndTime(arrivalTimeInDateFormat).build();
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


    private void deleteDriver(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String routeID = request.getParameter("routeID");
        boolean wasDeleted = routeDAO.deleteRecord(routeID);
        if (wasDeleted) {
            String message = "The route was successfully removed";
            request.setAttribute("message", message);
            ArrayList<Route> routeList = routeDAO.findAll();
            forwardListRoute(request, response, routeList);
        }
    }

    private void editRoute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int routeID = Integer.valueOf(request.getParameter("routeID"));
        String routeTitle = request.getParameter("routeTitle");
        String busID = request.getParameter("busID");
        String driverID = request.getParameter("driverID");
        Date departureTime = Date.valueOf(request.getParameter("departureTime"));
        Date arrivalTime = Date.valueOf(request.getParameter("arrivalTime"));

        BusDAO busDAO = new BusDAO();
        DriverDAO driverDAO = new DriverDAO();

        boolean wasUpdated = routeDAO.update(routeID, driverID, busID, departureTime, arrivalTime);
        String message = null;
        if (wasUpdated) {
             message = "The route has been  updated successfully";
        }
        ArrayList<Route> employeeList = routeDAO.findAll();
        request.setAttribute("idRoute", routeID);
        request.setAttribute("message", message);
        forwardListRoute(request, response, employeeList);
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
