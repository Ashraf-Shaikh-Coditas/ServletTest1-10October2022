package DAO;

import java.sql.ResultSet;

public interface PersonDao {
    void insert(Person person);
    ResultSet fetchPerson(String username);


}
