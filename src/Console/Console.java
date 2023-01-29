package Console;

import Dragon.CollectionManager;
import Dragon.Dragon;
import Dragon.DragonType;
import FileManager.FileManager;
import Util.CollectionUtil.PrintInfoCollection;
import Console.CorrectCreate;
import Util.CollectionUtil.UpdateCollection;

import java.util.Locale;
import java.util.Scanner;

public class Console {
    private static boolean isWork = false;

    public static void getStarted() {
        isWork = true;

        while (isWork) {
            CollectionManager dragons = new CollectionManager();
            Scanner scFile = new Scanner(System.in);;
            CorrectFileExecute.executeFile(scFile, dragons);
            PrintInfoCollection.help();
            chooseMethod(scFile, dragons);
        }
    }

    private static void chooseMethod(Scanner scanner, CollectionManager collectionManager) {
        PrintInfoCollection.help();
        boolean isRun = true;

        while (isRun) {
            System.out.println("Enter one of the available commands \n");

            String method = scanner.nextLine().trim();
            if (method.toLowerCase(Locale.ROOT).equals("help")) {
                PrintInfoCollection.help();
            } else if (method.toLowerCase(Locale.ROOT).equals("info")) {
                PrintInfoCollection.info(collectionManager);
            } else if (method.toLowerCase(Locale.ROOT).equals("show")) {
                PrintInfoCollection.show(collectionManager);
            } else if (method.toLowerCase(Locale.ROOT).equals("add")) {
                CorrectCreate.addDragonToCollection(scanner, collectionManager);
            } else if (method.toLowerCase(Locale.ROOT).equals("exit")) {
                isRun = false;
                exit();
            } else if (method.toLowerCase(Locale.ROOT).equals("update id")) {
                CorrectUpdate.correctUpdateID(scanner, collectionManager);
            } else if (method.toLowerCase(Locale.ROOT).equals("clear")) {
                collectionManager.clear();
            } else if (method.toLowerCase(Locale.ROOT).equals("save")) {
                System.out.println("Enter the file name for save");
                FileManager.save(scanner.nextLine().trim(), collectionManager);
                System.out.println("File was successfully saved");
            } else if (method.toLowerCase(Locale.ROOT).equals("remove by id")) {
                CorrectRemove.correctRemoveID(scanner, collectionManager);
            } else if (method.toLowerCase(Locale.ROOT).equals("execute script")) {
                CorrectFileExecute.executeFile(scanner, collectionManager);
            } else if (method.toLowerCase(Locale.ROOT).equals("remove_at_index")) {
                CorrectRemove.correctRemoveIndex(scanner, collectionManager);
            } else if (method.toLowerCase(Locale.ROOT).equals("add_if_min")) {
                UpdateCollection.add_if_min(collectionManager, CorrectCreate.createDragon(scanner, collectionManager));
            } else if (method.toLowerCase(Locale.ROOT).equals("remove_greater")) {
                UpdateCollection.remove_greater(collectionManager, CorrectCreate.createDragon(scanner, collectionManager));
            }
        }
    }

    private static void exit() {
        isWork = false;
    }

}
