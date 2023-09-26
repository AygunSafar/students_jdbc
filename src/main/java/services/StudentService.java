package services;

import Enums.Queries;
import connection.DBConnection;
import exceptions.StudentNotFoundException;
import models.Student;
import org.postgresql.core.Query;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static Enums.Queries.*;

public class StudentService {


    private  Connection connection;
    private  PreparedStatement preparedStatement;


    public  void createStudentTable() {
        connection = DBConnection.getConnection();
        String query = CREATE_MYSTUDENT_TABLE.getQuery();

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBConnection.closeConnection(connection);
            closePreparedStatement(preparedStatement);

            System.out.println("Connection is closed");
        }

    }

    public  void createStudent(Student student) {
        String query = INSERT_STUDENT.getQuery();
        connection = DBConnection.getConnection();
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, student.getID());
            preparedStatement.setString(2, student.getName());
            preparedStatement.setString(3, student.getSurname());
            preparedStatement.setInt(4, student.getAge());

            preparedStatement.executeUpdate();

            System.out.println("Student is created");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBConnection.closeConnection(connection);
            closePreparedStatement(preparedStatement);
            System.out.println("Connection is closed");

        }

    }

    public  Student getStudent(Long id) {
        connection = DBConnection.getConnection();
        String query = GET_STUDENT_BY_ID.getQuery();

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                long studentId = result.getLong("id");
                String name = result.getString("name");
                String surname = result.getString("surname");
                int age = result.getInt("age");

                Student student = new Student(studentId, name, surname, age);
                return student;

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBConnection.closeConnection(connection);
            closePreparedStatement(preparedStatement);

        }
        throw new StudentNotFoundException("Student with this id is not exist");
    }

    public  void update(Long id, String name) {
        connection = DBConnection.getConnection();
        String query = Queries.UPDATE_STUDENT_NAME.getQuery();
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
            System.out.println("Student's name is updated");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closePreparedStatement(preparedStatement);
            DBConnection.closeConnection(connection);
            System.out.println("Connection is closed");

        }
    }

    public  void deleteStudent(Long id) {

        connection = DBConnection.getConnection();

        String query = DELETE_STUDENT.getQuery();

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {

            closePreparedStatement(preparedStatement);
            DBConnection.closeConnection(connection);
        }

    }

    public  List<Student> getAllStudents() {
        connection = DBConnection.getConnection();

        String query = Queries.GET_ALL_STUDENTS.getQuery();

        List<Student> studentList = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement(query);
            ResultSet results = preparedStatement.executeQuery();

            while (results.next()) {
                Long id = results.getLong("id");
                String name = results.getString("name");
                String surname = results.getString("surname");
                int age = results.getInt("age");

                studentList.add(new Student(id, name, surname, age));
            }
            return studentList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closePreparedStatement(preparedStatement);
            DBConnection.closeConnection(connection);
        }
    }


    public static void closePreparedStatement(PreparedStatement preparedStatement) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
