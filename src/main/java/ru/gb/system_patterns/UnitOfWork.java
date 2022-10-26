package ru.gb.system_patterns;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UnitOfWork {
    private final List<User> newUsers = new ArrayList<>();
    private final List<User> updateUser = new ArrayList<>();
    private final List<User> deleteUser = new ArrayList<>();

    private final UserMapper userMapper;

    public UnitOfWork(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public void registerNew(User user) {
        this.newUsers.add(user);
    }

    public void registerUpdate(User user) {
        this.updateUser.add(user);
    }

    public void registerDelete(User user) {
        this.deleteUser.add(user);
    }

    public void commit() throws SQLException {
        insert();
        update();
        delete();
        clear();
    }

    private void clear() {
        this.updateUser.clear();
        this.newUsers.clear();
        this.deleteUser.clear();
    }

    private void delete() throws SQLException {
        for (User user : deleteUser) {
            userMapper.delete(user);
        }
    }

    private void update() throws SQLException {
        for (User user : updateUser) {
            userMapper.update(user);
        }
    }

    private void insert() throws SQLException {
        for (User user : newUsers) {
            userMapper.insert(user);
        }
    }
}
