package Servlets.Command.Driver;

import DAO.DriverDAO;
import Model.Driver;
import Servlets.Command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddNewDriverCommand implements Command {

    private DriverDAO driverDAO;

    public AddNewDriverCommand(DriverDAO aDriverDAO) {
        driverDAO = aDriverDAO;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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
                request.getRequestDispatcher("/views/commonView/successPage.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/views/commonView/errorPage.jsp").forward(request, response);
            }
        } else {
            request.getRequestDispatcher("/views/commonView/errorPage.jsp").forward(request, response);
        }
    }
}
