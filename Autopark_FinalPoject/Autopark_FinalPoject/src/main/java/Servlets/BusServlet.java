package Servlets;

import DAO.BusDAO;
import DAO.DriverDAO;
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
import java.util.ArrayList;

@WebServlet(name = "BusServlet", urlPatterns = "/BusServlet")
public class BusServlet extends HttpServlet {

    private BusDAO busDAO = new BusDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("searchAction");
        if (action != null) {
            switch (action) {
                case "searchById":
                    searchBuseByID(request, response);
                    break;
                case "searchByName":
                    searchBusByName(request, response);
                    break;
            }
        }else{
            ArrayList<Bus> resultList = busDAO.findAll();
            forwardListBuses(request, response, resultList);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        switch (action) {
            case "add":
                addNewBus(request, response);
                break;
            case "remove":
                deleteBus(request, response);
                break;
            case "edit":
                editBus(request, response);
                break;
        }

    }

    private void forwardListBuses(HttpServletRequest request, HttpServletResponse response, ArrayList<Bus> busList) throws IOException, ServletException {
        String nextJSP = "/views/adminView/seeAllBusesPage.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        request.setAttribute("busList", busList);
        dispatcher.forward(request, response);
    }

    private void searchBuseByID (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String busID = request.getParameter("idBus");
        Bus theBus = busDAO.findByID(busID);
        request.setAttribute("bus", theBus);
        request.setAttribute("action", "edit");
        String nextJSP = "/adminView/addNewBusPage.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        dispatcher.forward(request, response);
    }

    private void searchBusByName(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String busName = request.getParameter("busName");
        Bus theBus = busDAO.findByName(busName);
        request.setAttribute("bus", theBus);
        request.setAttribute("action", "edit");
        String nextJSP = "/adminView/addNewBusPage.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        dispatcher.forward(request, response);
    }

    private void addNewBus(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String busID = request.getParameter("idBus");
        String busModel = request.getParameter("busName");
        int maxCountOfPassangers = Integer.parseInt(request.getParameter("maxPassegers"));
        int miles = Integer.parseInt(request.getParameter("miles"));
        boolean passedService = Boolean.parseBoolean(request.getParameter("maintance"));


        Bus theBus = Bus.newBuilder().setBusID(busID).setBusModel(busModel).setmaxCountOfPassagers(maxCountOfPassangers)
                .setMiles(miles).setPassedService(passedService).build();
        boolean wasAdded = busDAO.addRecord(theBus);
        ArrayList<Bus> busList = busDAO.findAll();
        request.setAttribute("bus", theBus);
        if (wasAdded) {
            String message = "The new bus has been successfully created";
            request.setAttribute("message", message);
            forwardListBuses(request, response, busList);
        }
    }

    private void deleteBus(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String busID = request.getParameter("idBus");
        boolean wasDeleted = busDAO.deleteRecord(busID);
        if (wasDeleted) {
            String message = "The bus was successfully removed";
            request.setAttribute("message", message);
            ArrayList<Bus> driverList = busDAO.findAll();
            forwardListBuses(request, response, driverList);
        }
    }

    private void editBus(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String busID = request.getParameter("idBus");
        int miles = Integer.parseInt(request.getParameter("miles"));
        boolean passedService = Boolean.parseBoolean(request.getParameter("maintance"));

        boolean wasUpdated = busDAO.update(busID, miles, passedService);
        String message = null;
        if (wasUpdated) {
            message = "The route has been  updated successfully";
        }
        ArrayList<Bus> busList = busDAO.findAll();
        request.setAttribute("idBus", busID);
        request.setAttribute("message", message);
        forwardListBuses(request, response, busList);
    }
}
