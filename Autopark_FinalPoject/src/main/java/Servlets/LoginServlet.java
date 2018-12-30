package Servlets;

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


    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        // get request parameters for userID and password
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
                Cookie userName = new Cookie("admin", login);
                userName.setMaxAge(30*60);
                response.addCookie(userName);
                response.sendRedirect("/views/adminView/adminMainPage.jsp");
            } else if (theVisitor.getVisitorRole().equals(String.valueOf(Visitor.ROLE.DRIVER))) {
                session.setAttribute("driver", login);
                Cookie userName = new Cookie("driver", login);
                userName.setMaxAge(30*60);
                response.addCookie(userName);
                response.sendRedirect("/views/userView/driverMainPage.jsp");

            }
        }else{
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
            PrintWriter out= response.getWriter();
            out.println("<font color=red>Either user name or password is wrong.</font>");
            rd.include(request, response);
        }

    }

}

