package Servlets;

import DAO.RouteDAO;
import DAO.VisitorDAO;
import Model.Driver;
import Model.Route;
import Model.Visitor;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "RouteDriverServlet", urlPatterns = "/RouteDriverServlet")
public class RouteDriverServlet extends HttpServlet {

    private RouteDAO routeDAO = new RouteDAO();
    private VisitorDAO visitorDAO = new VisitorDAO();


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        showDriverStory(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println(action);
        switch (action) {
            case "editDriver":
                editDiver(request, response);
                break;
        }
    }

    private void showDriverStory(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        System.out.println(session.getAttribute("driver"));
        VisitorDAO visitorDAO = new VisitorDAO();
        Visitor theVisitor = visitorDAO.findByLogin((String)session.getAttribute("driver"));
        routeDAO.setDriverID(theVisitor.getTheDriver().getDriverID());
        System.out.println(theVisitor.getTheDriver().getDriverID());
        ArrayList<Route> driverStoryList = routeDAO.showDriverInfo();
        System.out.println(driverStoryList.size());
        System.out.println(driverStoryList.toArray() + "\n");

        if (driverStoryList.size() == 0) {
            System.out.println("1");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/commonView/errorPage.jsp");
            dispatcher.forward(request, response);
            System.out.println("2");
        } else {
            System.out.println("3");
            request.setAttribute("routeStoryList", driverStoryList);
            System.out.println("4");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/userView/seeMyRoutes.jsp");
            dispatcher.forward(request, response);
        }


    }

    private void editDiver(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        System.out.println("jjj");
        String driverName = (String) session.getAttribute("driver");
        Visitor theVisitor = visitorDAO.findByLogin(driverName);
        String newDriverPassword = request.getParameter("driverPassword");
        System.out.println(newDriverPassword);
        boolean wasUpdated = visitorDAO.update(theVisitor.getVisitorLogin(), newDriverPassword);
        if (wasUpdated) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/commonView/successPage.jsp");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/commonView/errorPage.jsp");
            dispatcher.forward(request, response);
        }



    }

    }
