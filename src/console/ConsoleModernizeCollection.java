package console;

import dragon.CollectionManager;

import java.util.Scanner;

public class ConsoleModernizeCollection extends ConsoleCollectionBuilder {
    
    public ConsoleModernizeCollection(CollectionManager collectionManager) {
        super(collectionManager);
    }

    public void consoleUpdateID(Scanner scanner) {
        int id = 0;
        try {
            if (super.getCollectionManager().getDragons().isEmpty()) {
                System.out.println("You cannot update an item by ID from an empty collection \n");
                return;
            }
            id = getID(scanner);
            getCollectionManager().update(id, new ConsoleCreateDragon(getCollectionManager()).createDragon(scanner));
        } catch (IndexOutOfBoundsException e) {
            System.out.printf("Element with an id=%d does not exist \n", id);
        }
    }

    public void consoleRemoveID(Scanner scanner) {
        int id = 0;
        try {
            if (super.getCollectionManager().getDragons().isEmpty()) {
                System.out.println("You cannot remove an item by ID from an empty collection \n");
                return;
            }
            id = getID(scanner);
            getCollectionManager().remove_by_id(id);
        } catch (IndexOutOfBoundsException e) {
            System.out.printf("Element with an id=%d does not exist \n", id);
        }
    }

    public void consoleRemoveIndex(Scanner scanner) {
        int index = 0;
        try {
            if (super.getCollectionManager().getDragons().isEmpty()) {
                System.out.println("You cannot remove an item by index from an empty collection \n");
                return;
            }
            index = getIndex(scanner);
            getCollectionManager().remove_at(index);
        } catch (IndexOutOfBoundsException e) {
            System.out.printf("Element with an index=%d does not exist \n", index);
        }
    }

}
