package Servlets.Command.Visitor;

import DAO.DriverDAO;
import DAO.VisitorDAO;
import Model.Driver;
import Model.Visitor;
import Servlets.Command.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddNewVisitorCommand implements Command {

    private VisitorDAO visitorDAO;

    public AddNewVisitorCommand(VisitorDAO aVisitorDAO) {
        visitorDAO = aVisitorDAO;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse responce) throws ServletException, IOException {
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
                    request.getRequestDispatcher("/views/commonView/successPage.jsp").forward(request, responce);
                } else {
                    request.getRequestDispatcher("/views/commonView/errorPage.jsp").forward(request, responce);
                }
            }
        } else {
            request.getRequestDispatcher("/views/commonView/errorPage.jsp").forward(request, responce);
        }
    }
}
