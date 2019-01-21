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
        showAllBuses(request, response);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println(action);
        switch (action) {
            case "addNewBus":
                addNewBus(request, response);
                break;
            case "editBus":
                editBus(request, response);
                break;
            case "removeBus":
                deleteBus(request, response);
        }

    }

    private void showAllBuses(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ArrayList<Bus> busList = busDAO.findAll();
        if (busList.size() == 0) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/commonView/errorPage.jsp");
            dispatcher.forward(request, response);
        } else {
            String nextJSP = "/views/adminView/seeAllBuses.jsp";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
            request.setAttribute("busList", busList);
            dispatcher.forward(request, response);
        }
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
                    response.sendRedirect("/views/commonView/successPage.jsp");
                }
            } else {
                response.sendRedirect("/views/commonView/errorPage.jsp");
            }
        }


    }

    private void deleteBus(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String busID = request.getParameter("idBus");
        System.out.println(busID);
        boolean wasDeleted = busDAO.deleteRecord(busID);
        System.out.println("wasDeleted " + wasDeleted);
        if (wasDeleted) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/commonView/successPage.jsp");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/commonView/errorPage.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void editBus(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String busID = request.getParameter("idBus");
        int miles = Integer.parseInt(request.getParameter("miles"));
        boolean passedService = Boolean.parseBoolean(request.getParameter("passedService"));
        System.out.println("idBus " + busID);
        System.out.println("miles " + miles);
        System.out.println("passedService " + passedService);
        boolean wasUpdated = busDAO.update(busID, miles, passedService);
        System.out.println("wasUpdated " + wasUpdated);
        if (wasUpdated) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/commonView/successPage.jsp");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/commonView/errorPage.jsp");
            dispatcher.forward(request, response);
        }
    }
}
