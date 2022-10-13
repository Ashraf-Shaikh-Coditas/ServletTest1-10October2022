package controller;

import DAO.ConnectionDB;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/deletebyadmin")
public class DeleteByAdmin extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        String name = req.getParameter("username");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = ConnectionDB.connect();

            PreparedStatement preparedStatement = connection.prepareStatement("delete from person where email = '"+name+"'");

            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        out.println("Record deleted successfully .");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("admin.html");
        requestDispatcher.include(req,resp);
    }
}
