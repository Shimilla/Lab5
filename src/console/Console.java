package console;

import DAO.CashLoader;
import DAO.Connect;
import Users.Registration;
import Users.User;
import dragon.*;
import util.printInfoCollection;

import java.sql.Connection;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import Callable.*;

public class Console {
    private static boolean isWork = false;
    private final CollectionManager collectionManager;
    private final ConsoleCreateDragon consoleCreateDragon;
    private final ConsoleModernizeCollection consoleModernizeCollection;
    private final ConsoleFileManager consoleFileManager;
    private final printInfoCollection printInfoCollection;
    private final Connect connect;
    private final Registration registration;
    private final ConsoleUsersAuthorization consoleUsersAuthorization;
    private final ExecutorService executorService;

    public Console(CollectionManager collectionManager,
                   ConsoleCreateDragon consoleCreateDragon,
                   ConsoleModernizeCollection consoleModernizeCollection,
                   ConsoleFileManager consoleFileManager,
                   printInfoCollection printInfoCollection,
                   Connect connect,
                   Registration registration,
                   ConsoleUsersAuthorization consoleUsersAuthorization,
                   ExecutorService executorService) {

        this.collectionManager = collectionManager;
        this.consoleCreateDragon = consoleCreateDragon;
        this.consoleModernizeCollection = consoleModernizeCollection;
        this.consoleFileManager = consoleFileManager;
        this.printInfoCollection = printInfoCollection;
        this.connect = connect;
        this.registration = registration;
        this.consoleUsersAuthorization = consoleUsersAuthorization;
        this.executorService = executorService;
    }

    private Connection getConnect() {
        ConnectCall connectCall = new ConnectCall(connect);
        Future<Connection> connectionFuture = executorService.submit(connectCall);
        try {
            Connection connection = connectionFuture.get();
            if (connection != null) {
                return connection;
            }
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Thread interruption \n");
        }
        return null;
    }

    private User getUser(Scanner scanner, Connection connection) {
        try {
            UserCall userCall = new UserCall(consoleUsersAuthorization, scanner, connection);
            Future<User> userFuture = executorService.submit(userCall);
            User user = userFuture.get();
            if (user != null) {
                return userFuture.get();
            }
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Thread interruption \n");
        }
        return null;
    }

    private void getCashDragons(CashLoader cashLoader, Connection connection) {
        executorService.submit(() -> cashLoader.getCashDragons(collectionManager, connection));
    }

    private void getCashUsers(CashLoader cashLoader, Registration registration, Connection connection) {
        executorService.submit(() -> cashLoader.getCashUsers(registration, connection));
    }

    public void getStarted() {
        isWork = true;
        Scanner scanner = new Scanner(System.in);
        CashLoader cashLoader = new CashLoader();
        Connection connection = getConnect();
        getCashDragons(cashLoader, connection);
        getCashUsers(cashLoader, registration, connection);
        User user = getUser(scanner, connection);
        collectionManager.help();
        chooseMethod(scanner, connection, user, executorService);
        executorService.shutdown();

    }

    private void chooseMethod(Scanner scanner, Connection connection, User user, ExecutorService executorService) {
        while (isWork) {
            System.out.println("Enter one of the available commands \n");
            String method = scanner.nextLine().trim();
            switch (method.toLowerCase(Locale.ROOT)) {
                case "help" -> collectionManager.help();
                case "info" -> {
                    executorService.submit(collectionManager::info);
                    printInfoCollection.pressEnter();
                }
                case "show" -> {
                    executorService.submit(collectionManager::show);
                    printInfoCollection.pressEnter();
                }
                case "add" -> {
                    consoleCreateDragon.addDragon(scanner, connection, user, executorService);
                    printInfoCollection.pressEnter();
                }
                case "exit" -> exit();
                case "update_id" -> {
                    consoleModernizeCollection.consoleUpdateID(scanner, connection, user);
                    printInfoCollection.pressEnter();
                }
                case "clear" -> {
                    collectionManager.clear(connection, user);
                    printInfoCollection.pressEnter();
                }
                case "save" -> {
                    consoleFileManager.save(scanner);
                    printInfoCollection.pressEnter();
                }
                case "remove_by_id" -> {
                    consoleModernizeCollection.consoleRemoveID(scanner, connection, user);
                    printInfoCollection.pressEnter();
                }
                case "execute_script" -> {
                    consoleFileManager.executeFile(scanner, connection, user);
                    printInfoCollection.pressEnter();
                }
                case "remove_at" -> {
                    consoleModernizeCollection.consoleRemoveIndex(scanner, connection, user);
                    printInfoCollection.pressEnter();

                }
                case "add_if_min" -> {
                    collectionManager.add_if_min(consoleCreateDragon.createDragon(scanner, user), connection, user);
                    printInfoCollection.pressEnter();
                }
                case "remove_greater" -> {
                    collectionManager.remove_greater(consoleCreateDragon.createDragon(scanner, user), connection, user);
                    printInfoCollection.pressEnter();
                }
                case "show_mine" -> user.showMine(collectionManager);
                default -> System.out.println("invalid command, for a list of available commands type \"help\" \n");
            }
        }
    }

    private void exit() {
        isWork = false;
        System.out.println("The program has been completed");
    }

}
