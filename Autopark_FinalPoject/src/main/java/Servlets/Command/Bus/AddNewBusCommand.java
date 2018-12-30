package Servlets.Command.Bus;

import DAO.BusDAO;
import Model.Bus;
import Servlets.Command.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddNewBusCommand implements Command {

    private BusDAO busDAO;

    public AddNewBusCommand(BusDAO aBusDAO) {
        busDAO = aBusDAO;
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse responce) throws ServletException, IOException {
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
                    request.getRequestDispatcher("/views/commonView/successPage.jsp").forward(request, responce);
                } else {
                    request.getRequestDispatcher("/views/commonView/errorPage.jsp").forward(request, responce);
                }
            } else {
                request.getRequestDispatcher("/views/commonView/errorPage.jsp").forward(request, responce);
            }
        }

    }
}
