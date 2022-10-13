package controller;

import DAO.DaoImplementation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        HttpSession httpSession = req.getSession();
        httpSession.setAttribute("username",username);

        if(username.equals("admin@123")) {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("admin.html");
            requestDispatcher.forward(req,resp);
        }

        DaoImplementation daoImplementation = new DaoImplementation();
        ResultSet resultSet = daoImplementation.fetchPerson(username);

        String temp = null ;
        try {
            if(resultSet.next()) {
                temp = String.valueOf(resultSet.getLong(4));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(temp.equals(password)) {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("welcome");
            requestDispatcher.forward(req,resp);
        } else {
            out.println("Credentials doesnt match : ");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("login.html");
            requestDispatcher.include(req,resp);
        }
    }
}
