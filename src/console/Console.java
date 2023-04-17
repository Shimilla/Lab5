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

    public Console(CollectionManager collectionManager,
                   ConsoleCreateDragon consoleCreateDragon,
                   ConsoleModernizeCollection consoleModernizeCollection,
                   ConsoleFileManager consoleFileManager,
                   printInfoCollection printInfoCollection,
                   Connect connect,
                   Registration registration,
                   ConsoleUsersAuthorization consoleUsersAuthorization) {

        this.collectionManager = collectionManager;
        this.consoleCreateDragon = consoleCreateDragon;
        this.consoleModernizeCollection = consoleModernizeCollection;
        this.consoleFileManager = consoleFileManager;
        this.printInfoCollection = printInfoCollection;
        this.connect = connect;
        this.registration = registration;
        this.consoleUsersAuthorization = consoleUsersAuthorization;
    }

    public void getStarted() {
        isWork = true;
        Scanner scanner = new Scanner(System.in);
        CashLoader cashLoader = new CashLoader();
        Connection connection = connect.getConnect();
        cashLoader.getCashDragons(collectionManager, connection);
        cashLoader.getCashUsers(registration, connection);
        User user = consoleUsersAuthorization.executeAuthorization(scanner, connection);
        collectionManager.help();
        chooseMethod(scanner, connection, user);
    }

    private void chooseMethod(Scanner scanner, Connection connection, User user) {
        while (isWork) {
            System.out.println("Enter one of the available commands \n");
            String method = scanner.nextLine().trim();
            switch (method.toLowerCase(Locale.ROOT)) {
                case "help" -> collectionManager.help();
                case "info" -> {
                    collectionManager.info();
                    printInfoCollection.pressEnter();
                }
                case "show" -> {
                    collectionManager.show();
                    printInfoCollection.pressEnter();
                }
                case "add" -> {
                    consoleCreateDragon.addDragon(scanner, connection, user);
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
