package Console;

import Dragon.CollectionManager;
import FileManager.FileManager;

import java.util.Scanner;

public class CorrectFileExecute {
    static void executeFile(Scanner scanner, CollectionManager collectionManager) {
        boolean isFileCorrect = false;
        while (!isFileCorrect) {
            try {
                System.out.println("Enter the name of file : \n");
                FileManager.execute_script(scanner.nextLine(), collectionManager);
                isFileCorrect = true;
            } catch (NullPointerException e) {
                System.out.println("Make sure the file is located correctly \n");
            }
        }
        System.out.println("collection was filled with values from the file \n");
    }
}
