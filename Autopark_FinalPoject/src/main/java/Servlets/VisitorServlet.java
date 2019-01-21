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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "VisitorServlet", urlPatterns = "/VisitorServlet")
    public class VisitorServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private VisitorDAO visitorDAO = new VisitorDAO();

        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            showAllVisitors(request, response);

        }

        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            showAllVisitors(request, response);
            String action = request.getParameter("action");
            System.out.println(action);
            switch (action) {
                case "addNewVisitor":
                    addNewVisitor(request, response);
                    break;
                case "removeVisitor":
                    deleteVisitor(request, response);
                    break;
                case "editAdmin":
                    editVisitorAdmin(request, response);
                    break;
            }

        }

        private void showAllVisitors(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
            ArrayList<Visitor> visitorList = visitorDAO.findAll();
            System.out.println(visitorList.size());
            if (visitorList.size() == 0) {
                System.out.println("1");
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/commonView/errorPage.jsp");
                dispatcher.forward(request, response);
                System.out.println("2");
            } else {
                System.out.println("3");
                request.setAttribute("visitorList", visitorList);
                System.out.println("4");
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/adminView/allVisitors.jsp");
                dispatcher.forward(request, response);
            }
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
            System.out.println("idVisitor " + visitorID);
            boolean wasFound = visitorDAO.findByID(visitorID);
            System.out.println("wasFound " + wasFound);
           if (visitorID != null && wasFound) {
                boolean wasDeleted = visitorDAO.deleteRecord(visitorID);
                System.out.println("wasDeleted " + wasDeleted);
                if (wasDeleted) {
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



        private void editVisitorAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
            String visitorLogin = request.getParameter("visitorLogin");
            String passwodToChange = request.getParameter("visitorPassword");
            String visitorRole = request.getParameter("visitorRole");

            boolean wasEdited = visitorDAO.updateForAdmin(visitorLogin, passwodToChange, visitorRole);
            if (wasEdited) {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/adminView/adminMainPage.jsp");
                dispatcher.forward(request, response);
                System.out.println("done");
            }
        }
}
