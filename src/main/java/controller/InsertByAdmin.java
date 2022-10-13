package controller;

import DAO.ConnectionDB;
import DAO.Person;

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

@WebServlet("/insertbyadmin")
public class InsertByAdmin extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        int id = Integer.parseInt(req.getParameter("id"));
        String firstName = req.getParameter("firstname");
        String lastName = req.getParameter("lastname");
        long number = Long.parseLong(req.getParameter("number"));
        int age = Integer.parseInt(req.getParameter("age"));
        String email = req.getParameter("email");
        String city = req.getParameter("city");

        Person person = new Person(id,firstName,lastName,number,age,email,city);


        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = ConnectionDB.connect();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into person values (?,?,?,?,?,?,?)");

            preparedStatement.setInt(1,person.getId());
            preparedStatement.setString(2, person.getFirstName());
            preparedStatement.setString(3, person.getLastName());
            preparedStatement.setLong(4,person.getNumber());
            preparedStatement.setInt(5,person.getAge());
            preparedStatement.setString(6, person.getEmail());
            preparedStatement.setString(7,person.getCity());

            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        out.println("User Added Successfully");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("admin.html");
        requestDispatcher.include(req,resp);
    }
}
