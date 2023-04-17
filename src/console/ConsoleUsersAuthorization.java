package console;

import Users.Registration;
import Users.User;

import java.sql.Connection;
import java.util.Locale;
import java.util.Scanner;

public class ConsoleUsersAuthorization {
    private final Registration registration;
    private final ConsoleUserRegistation consoleUserRegistation;

    public ConsoleUsersAuthorization(Registration registration) {
        this.registration = registration;
        this.consoleUserRegistation = new ConsoleUserRegistation(registration);
    }

    public User executeAuthorization(Scanner scanner, Connection connection) {
        ConsoleCreateUser consoleCreateUser = new ConsoleCreateUser();
        while (true) {
            System.out.println("Authorization... \n");
            String login = consoleCreateUser.getCorrectLogin(scanner);
            if (registration.isRegistered(login)) {
                System.out.println("Authentication... \n");
                checkPass(login, scanner, consoleCreateUser);
                User user = registration.getUserByLogin(login);
                return user;
            }
            if (!registration.isRegistered(login)) {
                System.out.println("User is not registered \n");
                chooseMethod(scanner, connection);
            }
        }
    }

    private boolean checkPass(String login, Scanner scanner, ConsoleCreateUser consoleCreateUser) {
        while (true) {
            String hashCode = consoleCreateUser.getCorrectPass(scanner);
            if (registration.getUserByLogin(login).getHash().equals(hashCode)) {
                return true;
            } else {
                System.out.println("Incorrect password \n");
            }
        }
    }

    private void chooseMethod(Scanner scanner, Connection connection) {
        System.out.println("If you want to log in again, enter 'aut' \n");
        System.out.println("If you want to register, type 'reg' \n");
        String method = "";
        while (true) {
            method = scanner.nextLine().trim().toLowerCase(Locale.ROOT);
            if (method.equals("aut")) {
                return;
            } else if (method.equals("reg")) {
                System.out.println("User registration... \n");
                consoleUserRegistation.executeRegistration(scanner, connection);
                return;
            } else {
                System.out.println("Enter 'aut' - for authorization, 'reg' - for registration \n");
            }
        }
    }
}
