package DAO;

import Users.User;
import java.sql.*;


public class UsersDAO {
    private PreparedStatement preparedStatement;

    public boolean insertUser(User user, Connection connection) {
        try {
            String sql = "INSERT INTO users (user_id, name, surname, login, hash_code) VALUES (?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getSurname());
            preparedStatement.setString(4, user.getLogin());
            preparedStatement.setString(5, user.getHash());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public int getId(Connection connection) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT MAX(user_id) FROM users");
            resultSet.next();
            return resultSet.getInt("max");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
