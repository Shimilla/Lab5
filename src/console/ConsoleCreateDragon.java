package console;

import Users.User;
import dragon.CollectionManager;
import dragon.Dragon;
import dragon.DragonType;
import DAO.*;
import java.sql.Connection;
import java.util.Scanner;

public class ConsoleCreateDragon extends ConsoleCollectionBuilder {
    private DragonsDAO dragonsDAO;

    public ConsoleCreateDragon(CollectionManager collectionManager, DragonsDAO dragonsDAO) {
        super(collectionManager);
        this.dragonsDAO = dragonsDAO;
    }

    public Dragon createDragon(Scanner scFile, User owner) {
        long age = getAgeDragon(scFile);
        String name = getNameDragon(scFile);
        int weight = getWeightDragon(scFile);
        DragonType dragonType = getDragonType(scFile);
        Dragon dragon = new Dragon(name, age, weight, dragonType, owner);
        return dragon;
    }

    void addDragon(Scanner scanner, Connection connection, User user) {
        Dragon dragon = createDragon(scanner, user);
        if (dragonsDAO.insertDragon(dragon, connection)) {
            getCollectionManager().getIdFromDb(dragon, connection);
            getCollectionManager().addDragon(dragon);
            System.out.println("Dragon was added : " + dragon);
        } else {
            System.out.println("Database crash \n");
        }
    }

}
