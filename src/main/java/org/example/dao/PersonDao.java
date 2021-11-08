package org.example.dao;

import org.example.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDao {

    private final JdbcTemplate jdbcTemplate;

//    private static final String URL = "jdbc:mysql://localhost:3306/people";
//    private static final String USER = "root";
//    private static final String PASS = "ktghbrjy";
//
//    private static Connection connection;
//
//    static {
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            connection = DriverManager.getConnection(URL, USER, PASS);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }

    @Autowired
    public PersonDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Person> index() {
//        List<Person> people = new ArrayList<>();
//        try {
//            Statement statement = connection.createStatement();
//            String sql = "SELECT * FROM person";
//            ResultSet rs = statement.executeQuery(sql);
//
//            while(rs.next()){
//                Person person = new Person();
//                person.setId(rs.getInt("id"));
//                person.setName(rs.getString("name"));
//                person.setAge(rs.getInt("age"));
//                person.setEmail(rs.getString("email"));
//                people.add(person);
//            }
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return people;
        return jdbcTemplate.query("SELECT * FROM person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person show(int id) {
//        Person person = null;
//        try {
//            PreparedStatement statement = connection.prepareStatement(
//                    "select * from person where id=?"
//            );
//            statement.setInt(1, id);
//            ResultSet rs = statement.executeQuery();
//            rs.next();
//            person = new Person();
//            person.setId(rs.getInt("id"));
//            person.setName(rs.getString("name"));
//            person.setAge(rs.getInt("age"));
//            person.setEmail(rs.getString("email"));
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return person;
        return jdbcTemplate.query("select * from person where id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }

    public void save(Person person) {
//        try {
//            PreparedStatement statement = connection.prepareStatement("insert into PERSON (name, age, email) " +
//                                                                        "values(?, ?, ?)");
//            statement.setString(1, person.getName());
//            statement.setInt(2, person.getAge());
//            statement.setString(3, person.getEmail());
//            statement.executeUpdate();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
        jdbcTemplate.update("insert into PERSON (name, age, email) values(?, ?, ?)"
                            , person.getName(), person.getAge(), person.getEmail());
    }

    public void update(int id, Person updatePerson) {

//        try {
//            PreparedStatement statement = connection.prepareStatement(
//                    "update person set name=?, age=?, email=? where id=?"
//            );
//            statement.setString(1, updatePerson.getName());
//            statement.setInt(2, updatePerson.getAge());
//            statement.setString(3, updatePerson.getEmail());
//            statement.setInt(4, id);
//            statement.executeUpdate();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
        jdbcTemplate.update("update person set name=?, age=?, email=? where id=?"
                            , updatePerson.getName(), updatePerson.getAge(), updatePerson.getEmail());
    }

    public void delete(int id) {
//        try {
//            PreparedStatement statement = connection.prepareStatement(
//                    "DELETE FROM person WHERE id=?"
//            );
//            statement.setInt(1, id);
//            statement.executeUpdate();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
        jdbcTemplate.update("DELETE FROM person WHERE id=?", id);
    }
}
