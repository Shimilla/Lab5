package DAO;

import Users.Registration;
import Users.User;
import dragon.CollectionManager;
import dragon.Dragon;
import dragon.DragonType;

import java.sql.*;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReentrantLock;

public class CashLoader {
    private ReentrantLock reentrantLock = new ReentrantLock();

    public void getCashDragons(CollectionManager collectionManager, Connection connection) {
        try {
            reentrantLock.lock();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM dragons LEFT JOIN users ON dragons.owner_id = users.user_id");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                long age = resultSet.getLong("age");
                int weight = resultSet.getInt("weight");
                DragonType dragonType = DragonType.valueOf(resultSet.getString("dragon_type").trim());
                int user_id = resultSet.getInt("owner_id");
                User user = getUserById(connection, user_id);
                Dragon dragon = new Dragon(id, name, age, weight, dragonType, user);
                collectionManager.addDragon(dragon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
    }

    public void getCashUsers(Registration registration, Connection connection) {
        reentrantLock.lock();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()) {
                int user_id = resultSet.getInt("user_id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String login = resultSet.getString("login");
                String hash_code = resultSet.getString("hash_code");
                User user = new User(user_id, name, surname, login, hash_code);
                registration.addUser(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
    }

    private User getUserById(Connection connection, int id) {
        try {
            String sql = "SELECT * FROM users WHERE user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int user_id = resultSet.getInt("user_id");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            String login = resultSet.getString("login");
            String hash_code = resultSet.getString("hash_code");
            return new User(user_id, name, surname, login, hash_code);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
