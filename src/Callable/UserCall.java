package Callable;

import Users.User;
import console.ConsoleUserRegistation;
import console.ConsoleUsersAuthorization;

import java.sql.Connection;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class UserCall implements Callable<User> {
    private final ConsoleUsersAuthorization consoleUsersAuthorization;
    private final Scanner scanner;
    private final Connection connection;

    public UserCall(ConsoleUsersAuthorization consoleUsersAuthorization, Scanner scanner, Connection connection) {
        this.consoleUsersAuthorization = consoleUsersAuthorization;
        this.scanner = scanner;
        this.connection = connection;
    }

    @Override
    public User call() {
        return consoleUsersAuthorization.executeAuthorization(scanner, connection);
    }
}
