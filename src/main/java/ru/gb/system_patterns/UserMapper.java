package ru.gb.system_patterns;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserMapper {
    private final Connection connection;
    private final PreparedStatement selectUser;

    private Statement statement;
    private final Map<Long, User> identityMap = new HashMap<>();

    public UserMapper(Connection connection) throws SQLException {
        this.statement = connection.createStatement();
        this.connection = connection;
        try {
            this.selectUser = connection.prepareStatement("select username, password from users where id = ?");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<User> findById(long id) {
        User user = identityMap.get(id);
        if (user != null) {
            return Optional.of(user);
        }
        try {
            selectUser.setLong(1, id);
            ResultSet rs = selectUser.executeQuery();
            if (rs.next()) {
                user = new User(rs.getString(1), rs.getString(2));
                identityMap.put(id, user);
                return Optional.of(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    public void delete(User user) throws SQLException {
        statement.executeUpdate("DELETE FROM users WHERE username = '" + user.getLogin() + "';");
    }

    public void update(User user) throws SQLException {
        statement.executeUpdate("UPDATE users SET password = '" + user.getPassword() + "' WHERE username = '" + user.getLogin() + "';");
    }

    public void insert(User user) throws SQLException {
        statement.executeUpdate("INSERT INTO users (username, password)"
                + "VALUES ('" + user.getLogin() + "','" + user.getPassword() + "');");
    }
}
