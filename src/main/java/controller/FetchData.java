package controller;

import DAO.ConnectionDB;
import DAO.DaoImplementation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/fetchbyadmin")
public class FetchData extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
       ResultSet resultSet;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = ConnectionDB.connect();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from person");
            resultSet = preparedStatement.executeQuery();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        out.println("USER DATA : ");

        out.println("<table border=4 px >" +
                "  <tr>" +
                "    <th>Id</th><th>First Name</th><th>LastName</th><th>Mobile</th><th>Age</th><th>Email</th><th>City</th>\n" +
                "  </tr>");

        try {
            while(resultSet.next()) {

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
