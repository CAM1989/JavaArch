package ru.gb.system_patterns;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection connection = DBConnection.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "username TEXT," +
                "password TEXT)");
        UserRepository repository = new UserRepository(connection);

//            statement.executeUpdate("INSERT INTO users (username, password)"
//                    + "VALUES ('Bob', 'Bob');");
//            statement.executeUpdate("INSERT INTO users (username, password)"
//                    + "VALUES ('John', 'John');");


        repository.beginTransaction();
        repository.insert(new User("Bob", "Bob"));
        repository.insert(new User("John", "John"));
        repository.update(new User("John", "John2"));
        repository.delete(new User("John", "John"));

        repository.commit();
        System.out.println(repository.findById(1L).orElseThrow());
    }
}
