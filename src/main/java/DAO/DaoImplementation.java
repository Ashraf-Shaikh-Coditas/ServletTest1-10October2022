package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoImplementation implements PersonDao{
    public void insert(Person person) {
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
    }

    public ResultSet fetchPerson(String username) {
        ResultSet resultSet ;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = ConnectionDB.connect();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from person where email = '"+username+"'");
            resultSet = preparedStatement.executeQuery();

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }


}
