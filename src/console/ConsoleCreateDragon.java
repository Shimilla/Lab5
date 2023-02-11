package console;

import dragon.CollectionManager;
import dragon.Dragon;
import dragon.DragonType;

import java.util.Scanner;

public class ConsoleCreateDragon extends ConsoleCollectionBuilder {

    public ConsoleCreateDragon(CollectionManager collectionManager) {
        super(collectionManager);
    }

    public Dragon createDragon(Scanner scFile) {
        long age = getAgeDragon(scFile);
        String name = getNameDragon(scFile);
        int weight = getWeightDragon(scFile);
        DragonType dragonType = getDragonType(scFile);
        Dragon dragon = new Dragon(name, age, weight, dragonType);
        getCollectionManager().initIdDragon(dragon);
        return dragon;
    }

    void addDragon(Scanner scanner) {
        Dragon dragon = createDragon(scanner);
        getCollectionManager().addDragon(dragon);
        System.out.println("Dragon was added : " + dragon);
    }

}
