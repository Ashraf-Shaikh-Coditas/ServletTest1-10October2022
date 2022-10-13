package controller;

import DAO.DaoImplementation;
import DAO.Person;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
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

        DaoImplementation daoImplementation = new DaoImplementation();
        daoImplementation.insert(person);

        out.println("Record Added Successfully . ");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("login.html");
        requestDispatcher.include(req,resp);


    }
}
