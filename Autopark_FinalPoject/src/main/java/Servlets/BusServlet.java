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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "BusServlet", urlPatterns = "/BusServlet")
public class BusServlet extends HttpServlet {

    private BusDAO busDAO = new BusDAO();

    public void doGet(HttpServletRequest request, HttpServletResponse response)
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
                case "seeAllBuses":
                    showAllBuses(request, response);
                    break;
            }
        }else{
            ArrayList<Bus> resultList = busDAO.findAll();
            forwardListBuses(request, response, resultList);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        switch (action) {
            case "addNewBus":
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

    private void showAllBuses(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ArrayList<Bus> busList = busDAO.findAll();
        if (busList.size() == 0) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/commonView/errorPage.jsp");
            dispatcher.forward(request, response);
        } else {
            String nextJSP = "/views/adminView/seeAllBusesPage.jsp";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
            request.setAttribute("busList", busList);
            dispatcher.forward(request, response);
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

        if (busID != null && busModel != null && maxCountOfPassangers != 0 && miles != 0) {
            Pattern pattern = Pattern.compile("[A-Z]{3}\\d{6}");
            Matcher matcher = pattern.matcher(busID);
            boolean isMatch = matcher.find();
            if (isMatch) {
                Bus theBus = Bus.newBuilder().setBusID(busID).setBusModel(busModel).setmaxCountOfPassagers(maxCountOfPassangers)
                        .setMiles(miles).setPassedService(passedService).build();
                boolean wasAdded = busDAO.addRecord(theBus);
                ArrayList<Bus> busList = busDAO.findAll();
                request.setAttribute("bus", theBus);
                if (wasAdded) {
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/commonView/successPage.jsp");
                    dispatcher.forward(request, response);
                } else {
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/commonView/errorPage.jsp");
                    dispatcher.forward(request, response);
                }
            } else {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/commonView/errorPage.jsp");
                dispatcher.forward(request, response);
            }
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
