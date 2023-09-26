package connection;

import exceptions.InvalidCredentialException;
import exceptions.PostgresClassNotFoundException;
import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static Connection connection=null;
    public  static Connection getConnection() {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new PostgresClassNotFoundException("Postgres Class not found");
        }
        try {
          connection=  DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres",
                    "postgres",
                    "password");
            System.out.println("Succesfully connected");
        } catch (SQLException e) {
            throw new InvalidCredentialException("Database credentials are invalid");
        }

        return connection;
    }

    public static void closeConnection(Connection connection){
        try {
            if(connection!=null)
                connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
