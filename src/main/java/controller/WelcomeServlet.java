package controller;

import DAO.DaoImplementation;

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

@WebServlet("/welcome")
public class WelcomeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("username");

        out.println("<h1>Welcome " + username + "</h1>");

        DaoImplementation daoImplementation = new DaoImplementation();
        ResultSet resultSet = daoImplementation.fetchPerson(username);


        out.println("USER DATA : ");

        out.println("<table border=4 px >" +
                "  <tr>" +
                "    <th>Id</th><th>First Name</th><th>LastName</th><th>Mobile</th><th>Age</th><th>Email</th><th>City</th>\n" +
                "  </tr>");

        try {
            if(resultSet.next()) {

                out.println("<tr><td>"+resultSet.getInt(1)+"</td>" +
                        "<td>"+resultSet.getString(2)+"</td>"+
                        "<td>"+resultSet.getString(3)+"</td>"+
                        "<td>"+resultSet.getLong(4)+"</td>"+
                        "<td>"+resultSet.getInt(5)+"</td>"+
                        "<td>"+resultSet.getString(6)+"</td>"+
                        "<td>"+resultSet.getString(7)+"</td>"
                );
            }
            System.out.println("</table>");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        out.println("<a href='login.html'>Logout</a>");

    }
}
