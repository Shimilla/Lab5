package console;

import DAO.UsersDAO;
import Users.Registration;
import Users.User;

import java.sql.Connection;
import java.util.Scanner;

public class ConsoleUserRegistation {
    private Registration registration;
    private UsersDAO usersDAO = new UsersDAO();

    public ConsoleUserRegistation(Registration registration) {
        this.registration = registration;
    }

    public void executeRegistration(Scanner scanner, Connection connection) {
        boolean isCorrect = false;
        ConsoleCreateUser consoleCreateUser = new ConsoleCreateUser();
        while (!isCorrect) {
            User user = consoleCreateUser.createUser(scanner);
            registration.initId(user, connection);
            isCorrect = userVerification(user.getLogin());
            if (isCorrect) {
                if (usersDAO.insertUser(user, connection)) {
                    registration.register(user);
                } else {
                    System.out.println("Database crash \n");
                }
            } else {
                System.out.println("Such a user is already registered \n");
            }
        }
    }

    private boolean userVerification(String login) {
        if (registration.isRegistered(login)) {
            return false;
        } else {
            return true;
        }
    }
}
