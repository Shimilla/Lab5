package Console;

import Dragon.CollectionManager;
import Dragon.Dragon;
import Util.CollectionUtil.UpdateCollection;

import java.util.Scanner;

public class CorrectUpdate {
    static void correctUpdateID(Scanner scanner, CollectionManager collectionManager) {
        int id = 0;
        try {
            id = getID(scanner);
            UpdateCollection.update(collectionManager, id, CorrectCreate.createDragon(scanner, collectionManager));
        } catch (IndexOutOfBoundsException e) {
            System.out.printf("element with an id=%d does not exist \n", id);
        }

    }

    private static int getID(Scanner scanner) {
        boolean isCorrectId = false;
        int id = 0;
        while (!isCorrectId) {
            System.out.println("Enter the id: \n");
            try {
                id = Integer.parseInt(scanner.nextLine());
                if (id < 0) {
                    System.out.println("the id must be a whole number greater than zero \n");
                } else {
                    isCorrectId = true;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("check that the data entered is correct, the id must be a whole number greater than zero \n");
            }
        }
        return id;
    }
}
