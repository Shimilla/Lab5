package Users;
import DAO.UsersDAO;

import java.sql.Connection;
import java.util.ArrayList;


public class Registration {
    private ArrayList<User> users = new ArrayList<>();
    private Encoder encoder = new Encoder();
    private UsersDAO usersDAO = new UsersDAO();

    public void register(User user) {
        users.add(user);
        System.out.printf("The user '%s' was registered \n", user.getName() + " " + user.getSurname());
    }

    public boolean isRegistered(String login) {
        for (User user : users) {
            if (user.getLogin().equals(login)) {
                return true;
            }
        }
        return false;
    }

    public void initId(User user, Connection connection) {
        int maxId = 0;
        if (users.size() == 0) {
            maxId = 0;
        } else {
            maxId = usersDAO.getId(connection);
        }
        user.setId(maxId + 1);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public User getUserByLogin(String login) {
        for (User user : users) {
            if (user.getLogin().equals(login)) {
                return user;
            }
        }
        return null;
    }
}
