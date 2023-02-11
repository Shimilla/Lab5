package console;

import Environment.Environment;
import dragon.*;

import java.util.Locale;
import java.util.Scanner;
import util.*;

public class Console {
    private static boolean isWork = false;
    private final CollectionManager collectionManager;
    private final ConsoleCreateDragon consoleCreateDragon;
    private final ConsoleModernizeCollection consoleModernizeCollection;
    private final ConsoleFileManager consoleFileManager;

    public Console(CollectionManager collectionManager,
                   ConsoleCreateDragon consoleCreateDragon,
                   ConsoleModernizeCollection consoleModernizeCollection,
                   ConsoleFileManager consoleFileManager) {
        this.collectionManager = collectionManager;
        this.consoleCreateDragon = consoleCreateDragon;
        this.consoleModernizeCollection = consoleModernizeCollection;
        this.consoleFileManager = consoleFileManager;
    }

    public void getStarted() {
        isWork = true;
        while (isWork) {
            Environment environment = new Environment();
            consoleFileManager.executeFile(environment.getFile());
            Scanner scFile = new Scanner(System.in);
            chooseMethod(scFile);
        }
    }

    private void chooseMethod(Scanner scanner) {
        while (isWork) {
            System.out.println("Enter one of the available commands \n");
            collectionManager.help();
            String method = scanner.nextLine().trim();
            switch (method.toLowerCase(Locale.ROOT)) {
                case "help" -> collectionManager.help();
                case "info" -> {
                    collectionManager.info();
                    PrintInfoCollection.pressEnter();
                }
                case "show" -> {
                    collectionManager.show();
                    PrintInfoCollection.pressEnter();
                }
                case "add" -> {
                    consoleCreateDragon.addDragon(scanner);
                    PrintInfoCollection.pressEnter();
                }
                case "exit" -> exit();
                case "update_id" -> {
                    consoleModernizeCollection.consoleUpdateID(scanner);
                    PrintInfoCollection.pressEnter();
                }
                case "clear" -> {
                    collectionManager.clear();
                }
                case "save" -> {
                    consoleFileManager.save(scanner);
                    PrintInfoCollection.pressEnter();
                }
                case "remove_by_id" -> {
                    consoleModernizeCollection.consoleRemoveID(scanner);
                    PrintInfoCollection.pressEnter();
                }
                case "execute_script" -> {
                    consoleFileManager.executeFile(scanner);
                    PrintInfoCollection.pressEnter();
                }
                case "remove_at" -> {
                    consoleModernizeCollection.consoleRemoveIndex(scanner);
                    PrintInfoCollection.pressEnter();
                }
                case "add_if_min" -> {
                    collectionManager.add_if_min(consoleCreateDragon.createDragon(scanner));
                    PrintInfoCollection.pressEnter();
                }
                case "remove_greater" -> {
                    if (collectionManager.getDragons().isEmpty()) {
                        System.out.println("You have an empty collection \n");
                        PrintInfoCollection.pressEnter();
                        break;
                    }
                    collectionManager.remove_greater(consoleCreateDragon.createDragon(scanner));
                    PrintInfoCollection.pressEnter();
                }
            }
        }
    }

    private void exit() {
        isWork = false;
        System.out.println("The program has been completed");
    }

}
