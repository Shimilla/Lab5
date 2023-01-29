package Console;

import Dragon.CollectionManager;
import Util.CollectionUtil.UpdateCollection;

import java.util.Scanner;

public class CorrectRemove {
    static void correctRemoveID(Scanner scanner, CollectionManager collectionManager) {
        int id = 0;
        try {
            id = getID(scanner);
            collectionManager.remove_by_id(id);
        } catch (IndexOutOfBoundsException e) {
            System.out.printf("element with an id=%d does not exist \n", id);
        }

    }

    static void correctRemoveIndex(Scanner scanner, CollectionManager collectionManager) {
        int index = 0;
        try {
            index = getIndex(scanner);
            collectionManager.remove_at(index);
        } catch (IndexOutOfBoundsException e) {
            System.out.printf("element with an index=%d does not exist \n", index);
        }
    }

    private static int getID(Scanner scanner) {
        boolean isCorrectId = false;
        int id = 0;
        while (!isCorrectId) {
            System.out.println("Enter the id:");
            try {
                id = Integer.parseInt(scanner.nextLine());
                if (id < 0) {
                    System.out.println("the id must be a whole number greater than zero");
                } else {
                    isCorrectId = true;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("check that the data entered is correct, the id must be a whole number greater than zero");
            }
        }
        return id;
    }

    private static int getIndex(Scanner scanner) {
        boolean isCorrectIndex = false;
        int index = 0;
        while (!isCorrectIndex) {
            System.out.println("Enter the index: \n");
            try {
                index = Integer.parseInt(scanner.nextLine());
                if (index < 0) {
                    System.out.println("the index must be a whole number greater than zero \n");
                } else {
                    isCorrectIndex = true;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("check that the data entered is correct, the index must be a whole number greater than zero \n");
            }
        }
        return index;
    }
}
