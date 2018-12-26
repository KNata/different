package Servlets;

import DAO.DriverDAO;
import Model.Driver;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "DriverServlet", urlPatterns = "/DriverServlet")
public class DriverServlet extends HttpServlet {

    private DriverDAO driverDAO = new DriverDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("searchAction");
        if (action != null) {
            switch (action) {
                case "searchById":
                    searchDriverByID(request, response);
                    break;
                case "searchByName":
                    searchDriverByName(request, response);
                    break;
            }
        }else{
            ArrayList<Driver> resultList = driverDAO.findAll();
            forwardListDrivers(request, response, resultList);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        switch (action) {
            case "add":
                addNewDriver(request, response);
                break;
            case "remove":
                deleteDriver(request, response);
                break;
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
        String driverID = request.getParameter("driverID");
        String driverName = request.getParameter("driverName");

        Driver theDriver = Driver.newBuilder().setDriverID(driverID).setDriverName(driverName).build();
        boolean wasAdded = driverDAO.addRecord(theDriver);
        ArrayList<Driver> driverList = driverDAO.findAll();
        request.setAttribute("driver", theDriver);
        if (wasAdded) {
            String message = "The new driver has been successfully created";
            request.setAttribute("message", message);
            forwardListDrivers(request, response, driverList);
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
