package DAO;

import Users.User;
import dragon.Dragon;

import java.sql.*;
import java.util.concurrent.locks.ReentrantLock;

public class DragonsDAO {
    private PreparedStatement preparedStatement;
    private ReentrantLock reentrantLock = new ReentrantLock();

    public boolean insertDragon(Dragon dragon, Connection connection) {
        reentrantLock.lock();
        try {
            String sql = "INSERT INTO Dragons (name, age, weight, dragon_type, owner_id) VALUES (?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, dragon.getName());
            preparedStatement.setLong(2, dragon.getAge());
            preparedStatement.setInt(3, dragon.getWeight());
            preparedStatement.setString(4, dragon.getType().toString());
            preparedStatement.setInt(5, dragon.getOwner().getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
        return true;
    }

    public void selectDragons(Connection connection) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Dragons");
            while (resultSet.next()) {
                System.out.println("id: " + resultSet.getInt("id"));
                System.out.println("name: " + resultSet.getString("name"));
                System.out.println("age: " + resultSet.getString("age"));
                System.out.println("weight: " + resultSet.getString("weight"));
                System.out.println("dragon_type " + resultSet.getString("dragon_type"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isEmptyTable(Connection connection) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT Count(*) FROM Dragons");
            while (resultSet.next()) {
                if (resultSet.getInt("count") == 0) {
                    System.out.println("The table has no records \n");
                    return true;
                } else {
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean isEmptyTableByUser(Connection connection, User user) {
        try {
            String sql = "SELECT Count(*) FROM Dragons WHERE owner_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getInt("count") == 0) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean update_id(int id, Dragon dragon, Connection connection) {
        try {
            String sql = "UPDATE dragons SET name = ?, age = ?, weight = ?, dragon_type = ? " +
                    "WHERE id = ? ";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, dragon.getName());
            preparedStatement.setLong(2, dragon.getAge());
            preparedStatement.setInt(3, dragon.getWeight());
            preparedStatement.setString(4, dragon.getType().toString());
            preparedStatement.setInt(5, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getId(Connection connection) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT MAX(id) FROM dragons");
            resultSet.next();
            return resultSet.getInt("max");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean remove_by_id(int id, Connection connection) {
        try {
            String sql = "DELETE FROM dragons WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean clear(Connection connection, User user) {
        try {
            String sql = "DELETE FROM dragons WHERE owner_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int remove_at_AndGetId(int index, Connection connection, User user) {
        int id = 0;
        try {
            String sql = "SELECT id FROM dragons WHERE owner_id = ? " +
                    "ORDER BY id";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.executeQuery();
            int count = 1;
            while (resultSet.next()) {
                if (count == index) {
                    id = resultSet.getInt("id");
                    remove_by_id(id, connection);
                    return id;
                }
                count++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public boolean remove_greater(Dragon dragon, Connection connection, User user) {
        try {
            String sql = "DELETE FROM dragons WHERE (age > ? AND owner_id = ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, dragon.getAge());
            preparedStatement.setInt(2, user.getId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
