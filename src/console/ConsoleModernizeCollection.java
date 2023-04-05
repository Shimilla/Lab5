package console;

import DAO.DragonsDAO;
import Users.User;
import dragon.CollectionManager;
import dragon.Dragon;

import java.sql.Connection;
import java.util.Scanner;

public class ConsoleModernizeCollection extends ConsoleCollectionBuilder {
    private final DragonsDAO dragonsDAO;

    public ConsoleModernizeCollection(CollectionManager collectionManager, DragonsDAO dragonsDAO) {
        super(collectionManager);
        this.dragonsDAO = dragonsDAO;
    }

//    public void consoleUpdateID(Scanner scanner) {
//        int id = 0;
//        try {
//            if (super.getCollectionManager().getDragons().isEmpty()) {
//                System.out.println("You cannot update an item by ID from an empty collection \n");
//                return;
//            }
//            id = getID(scanner);
//            getCollectionManager().update(id, new ConsoleCreateDragon(getCollectionManager(), dragonsDAO).createDragon(scanner));
//        } catch (IndexOutOfBoundsException e) {
//            System.out.printf("Element with an id=%d does not exist \n", id);
//        }
//    }

    public void consoleUpdateID(Scanner scanner, Connection connection, User user) {
        int id = 0;
        try {
            //проверка на пустоту таблицы в БД
            if (!dragonsDAO.isEmptyTableByUser(connection, user)) {
                id = getID(scanner);
                // проверка на своих драконов
                Dragon myDragon = getCollectionManager().getDragonById(id);
                if (myDragon.getOwner().getId() == user.getId()) {
                    Dragon dragon;
                    if (dragonsDAO.update_id(id, dragon = new ConsoleCreateDragon(getCollectionManager(), dragonsDAO).createDragon(scanner, user), connection)) {
                        getCollectionManager().update(id, dragon);
                    } else {
                        System.out.println("Database crash \n");
                    }
                } else {
                    System.out.println("The dragon has another master. Enter 'show_mine' to get a list of your dragons \n");
                    return;
                }
            } else {
                System.out.println("Your table has no entries \n");
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.printf("Element with an id=%d does not exist \n", id);
        }
    }

//    public void consoleRemoveID(Scanner scanner) {
//        int id = 0;
//        try {
//            if (super.getCollectionManager().getDragons().isEmpty()) {
//                System.out.println("You cannot remove an item by ID from an empty collection \n");
//                return;
//            }
//            id = getID(scanner);
//            getCollectionManager().remove_by_id(id);
//        } catch (IndexOutOfBoundsException e) {
//            System.out.printf("Element with an id=%d does not exist \n", id);
//        }
//    }

    public void consoleRemoveID(Scanner scanner, Connection connection, User user) {
        int id = 0;
        try {
            //проверка на пустоту таблицы в БД
            if (!dragonsDAO.isEmptyTableByUser(connection, user)) {
                id = getID(scanner);
                Dragon myDragon = getCollectionManager().getDragonById(id);
                if (myDragon.getOwner().getId() == user.getId()) {
                    if (dragonsDAO.remove_by_id(id, connection)) {
                        getCollectionManager().remove_by_id(id);
                    } else {
                        System.out.println("Database crash \n");
                    }
                } else {
                    System.out.println("The dragon has another master. Enter 'show_mine' to get a list of your dragons \n");
                    return;
                }
            } else {
                System.out.println("Your table has no entries \n");
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.printf("Element with an id=%d does not exist \n", id);
        }
    }

//    public void consoleRemoveIndex(Scanner scanner) {
//        int index = 0;
//        try {
//            if (super.getCollectionManager().getDragons().isEmpty()) {
//                System.out.println("You cannot remove an item by index from an empty collection \n");
//                return;
//            }
//            index = getIndex(scanner);
//            getCollectionManager().remove_at(index);
//        } catch (IndexOutOfBoundsException e) {
//            System.out.printf("Element with an index=%d does not exist \n", index);
//        }
//    }

    public void consoleRemoveIndex(Scanner scanner, Connection connection, User user) {
        int index = 0;
        try {
            if (dragonsDAO.isEmptyTableByUser(connection, user)) {
                System.out.println("You cannot remove an item by index from an empty collection \n");
                return;
            } else {
                index = getIndex(scanner, user);
                int id = 0;
                if ((id = dragonsDAO.remove_at_AndGetId(index, connection, user)) > 0) {
                    getCollectionManager().remove_by_id(id);
                } else {
                    System.out.println("Database crash \n");
                }
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.printf("Element with an index=%d does not exist \n", index);
        }
    }

}
