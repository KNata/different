package Servlets;

import DAO.RouteDAO;
import DAO.VisitorDAO;
import Model.Visitor;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet", urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        VisitorDAO visitorDAO = new VisitorDAO();
        Visitor theVisitor = visitorDAO.findByLoginAndPassword(login, password);
        if(theVisitor != null) {
            HttpSession session = request.getSession();
            System.out.println(theVisitor.getVisitorRole());
            System.out.println(theVisitor.getVisitorRole().equals(String.valueOf(Visitor.ROLE.ADMIN)));
            if (theVisitor.getVisitorRole().equals(String.valueOf(Visitor.ROLE.ADMIN))) {
                session.setAttribute("admin", login);
                System.out.println(session.getAttribute("admin"));
                response.sendRedirect("/views/adminView/adminMainPage.jsp");
            } else if (theVisitor.getVisitorRole().equals(String.valueOf(Visitor.ROLE.DRIVER))) {
                session.setAttribute("driver", login);
                response.sendRedirect("/views/userView/driverMainPage.jsp");

            }
        }else{
            response.sendRedirect("/index.jsp");
            PrintWriter out= response.getWriter();
            out.println("<font color=red>Either user name or password is wrong.</font>");

        }


    }

}

