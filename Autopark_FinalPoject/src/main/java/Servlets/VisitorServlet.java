package Servlets;

import DAO.DriverDAO;
import DAO.VisitorDAO;
import Model.Driver;
import Model.Visitor;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "VisitorServlet", urlPatterns = "/VisitorServlet")
    public class VisitorServlet extends HttpServlet {

        private VisitorDAO visitorDAO = new VisitorDAO();

        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            String action = request.getParameter("searchAction");
            if (action != null) {
                switch (action) {
                    case "searchById":
                        searchVisitorByLogin(request, response);
                        break;
                }
            }else{
                ArrayList<Visitor> resultList = visitorDAO.findAll();
                forwardListVisitors(request, response, resultList);
            }
        }

        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {

            String action = request.getParameter("action");
            System.out.println(action);
            switch (action) {
                case "addNewVisitor":
                    addNewVisitor(request, response);
                    break;
                case "remove":
                    deleteVisitor(request, response);
                    break;
                case "edit":
                    editVisitor(request, response);
                    break;
                case "editAdmin":
                    editVisitorAdmin(request, response);
                    break;
            }

        }

        private void forwardListVisitors(HttpServletRequest request, HttpServletResponse response, ArrayList<Visitor> visitorList) throws IOException, ServletException {
            String nextJSP = "/views/adminView/seeAllVisitorsPage.jsp";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
            request.setAttribute("visitorList", visitorList);
            dispatcher.forward(request, response);
        }

        private void searchVisitorByLogin (HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
            String visitorLogin = request.getParameter("login");
            Visitor theVisitor = visitorDAO.findByLogin(visitorLogin);
            request.setAttribute("visitor", theVisitor);
            request.setAttribute("action", "edit");
            String nextJSP = "/adminView/addNewVisitorPage.jsp";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
            dispatcher.forward(request, response);
        }

        private void addNewVisitor(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
            int visitorID = Integer.valueOf(request.getParameter("idVisitor"));
            String visitorLogin = request.getParameter("visitorLogin");
            String visitorPassword = request.getParameter("visitorPassword");
            String visitorName = request.getParameter("visitorName");
            String visitorRole = request.getParameter("visitorRole");
            String driverID = request.getParameter("driverId");
            Pattern pattern = Pattern.compile("[A-Z]{2}\\d{5}");
            Matcher matcher = pattern.matcher(driverID);
            boolean isMatch = matcher.find();
            if (isMatch) {
                DriverDAO driverDAO = new DriverDAO();
                if (visitorID != 0 && visitorLogin != null && visitorName != null && visitorPassword != null && visitorRole != null) {
                    Driver theDriver = Driver.newBuilder().setDriverID(driverID).setDriverName(visitorName).build();
                    driverDAO.addRecord(theDriver);
                    Visitor theVisitor = Visitor.newBuilder().setVisitorID(visitorID).setVisitorName(visitorName)
                            .setVisitorLogin(visitorLogin).setVisitorPassword(visitorPassword).setVisitorRole(visitorRole).setDriver(theDriver)
                            .build();
                    boolean wasAdded = visitorDAO.addRecord(theVisitor);
                    System.out.println(wasAdded);
                    if (wasAdded) {
                        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/commonView/successPage.jsp");
                        dispatcher.forward(request, response);
                    } else {
                        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/commonView/errorPage.jsp");
                        dispatcher.forward(request, response);
                    }
                }
            } else {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/commonView/errorPage.jsp");
                dispatcher.forward(request, response);
            }
        }

        private void deleteVisitor(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
            String visitorID = request.getParameter("visitorID");
            boolean wasDeleted = visitorDAO.deleteRecord(visitorID);
            if (wasDeleted) {
                String message = "The visitor was successfully removed";
                request.setAttribute("message", message);
                ArrayList<Visitor> visitorList = visitorDAO.findAll();
                forwardListVisitors(request, response, visitorList);
            }
        }

        private void editVisitor(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
            String visitorLogin = request.getParameter("login");
            String passwodToChange = request.getParameter("password");

            boolean wasEdited = visitorDAO.update(visitorLogin, passwodToChange);
            if (wasEdited) {
                String message = "The visitor was successfully edited";
                request.setAttribute("message", message);
                ArrayList<Visitor> visitorList = visitorDAO.findAll();
                forwardListVisitors(request, response, visitorList);
            }
        }

        private void editVisitorAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
            String visitorLogin = request.getParameter("login");
            String passwodToChange = request.getParameter("password");
            String visitorRole = request.getParameter("visitorRole");

            boolean wasEdited = visitorDAO.updateForAdmin(visitorLogin, passwodToChange, visitorRole);
            if (wasEdited) {
                String message = "The visitor was successfully edited";
                request.setAttribute("message", message);
                ArrayList<Visitor> visitorList = visitorDAO.findAll();
                forwardListVisitors(request, response, visitorList);
            }
        }
}
