package Servlets;

import DAO.DriverDAO;
import Model.Driver;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "DriverServlet", urlPatterns = "/DriverServlet")
public class DriverServlet extends HttpServlet {

    private static Logger theLogger;

    private DriverDAO driverDAO = new DriverDAO();

    static {
        theLogger = Logger.getLogger(DriverServlet.class);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       // String action = request.getParameter("searchAction");
       // if (action != null) {
//            switch (action) {
//                case "searchById":
//                    searchDriverByID(request, response);
//                    break;
//                case "searchByName":
//                    searchDriverByName(request, response);
//                    break;
//            }
//
        DriverDAO driverDAO = new DriverDAO();
        ArrayList<Driver> allDriversList = driverDAO.findAll();
        System.out.println(allDriversList.size());
        request.setAttribute("driverList", allDriversList);
        request.getRequestDispatcher("/adminView/seeAllDriversPage.jsp").forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        switch (action) {
            case "addNewDriver":
                addNewDriver(request, response);
                break;
            case "remove":
                deleteDriver(request, response);
                break;
            case "seeAllDrivers":
                fullListOfDrivers(request, response);
                break;
        }

    }

    public void fullListOfDrivers(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Driver> driverList = driverDAO.findAll();
        for(int i = 0; i < driverList.size(); i++) {
            System.out.println(driverList.get(i).toString());
        }
        System.out.println(driverList.size());
        if (driverList.size() == 0) {
            System.out.println("1");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/commonView/errorPage.jsp");
            dispatcher.forward(request, response);
            System.out.println("2");
        } else {
            System.out.println("3");
            String nextJSP = "/views/adminView/seeAllDriversPage.jsp";
            request.setAttribute("driverList", driverList);
            System.out.println("4");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
            dispatcher.forward(request, response);
        }
    }

    private void forwardListDrivers(HttpServletRequest request, HttpServletResponse response, ArrayList<Driver> driverList) throws IOException, ServletException {
        String nextJSP = "/views/adminView/seeAllDriversPage.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        request.setAttribute("driverList", driverList);
        dispatcher.forward(request, response);
    }

    private void searchDriverByID (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String driverID = request.getParameter("idDriver");
        Driver theDriver = driverDAO.findByID(driverID);
        request.setAttribute("driver", theDriver);
        request.setAttribute("action", "edit");
        String nextJSP = "/adminView/addNewDriverPage.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        dispatcher.forward(request, response);
    }

    private void searchDriverByName(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String driverName = request.getParameter("driverName");
        Driver theDriver = driverDAO.findByName(driverName);
        request.setAttribute("driver", theDriver);
        request.setAttribute("action", "edit");
        String nextJSP = "/adminView/addNewDriverPage.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
        dispatcher.forward(request, response);
    }

    private void addNewDriver(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String driverID = request.getParameter("idDriver");
        String driverName = request.getParameter("driverName");
        Pattern pattern = Pattern.compile("[A-Z]{2}\\d{5}");
        Matcher matcher = pattern.matcher(driverID);
        boolean isMatch = matcher.find();
        if (isMatch) {
            Driver theDriver = Driver.newBuilder().setDriverID(driverID).setDriverName(driverName).build();
            boolean wasAdded = driverDAO.addRecord(theDriver);
            System.out.println(driverID);
            System.out.println(driverName);
            System.out.println(wasAdded);
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

    private void deleteDriver(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        DriverDAO driverDAO = new DriverDAO();
        String driverID = request.getParameter("driverID");
        boolean wasDeleted = driverDAO.deleteRecord(driverID);
        if (wasDeleted) {
            String message = "The driver was successfully removed";
            request.setAttribute("message", message);
            ArrayList<Driver> driverList = driverDAO.findAll();
            forwardListDrivers(request, response, driverList);
        }
    }
}
