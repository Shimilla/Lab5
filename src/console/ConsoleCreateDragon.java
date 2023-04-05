package console;

import Callable.InsertDragonCall;
import Users.User;
import dragon.CollectionManager;
import dragon.Dragon;
import dragon.DragonType;
import DAO.*;

import java.sql.Connection;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.ReentrantLock;

public class ConsoleCreateDragon extends ConsoleCollectionBuilder {
    private DragonsDAO dragonsDAO;
    private ReentrantLock reentrantLock = new ReentrantLock();

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

    public Dragon createDragonWithId(Scanner scFile, Connection connection, User owner) {
        long age = getAgeDragon(scFile);
        String name = getNameDragon(scFile);
        int weight = getWeightDragon(scFile);
        DragonType dragonType = getDragonType(scFile);
        Dragon dragon = new Dragon(name, age, weight, dragonType, owner);
        getCollectionManager().initIdDragon(dragon, connection);
        return dragon;
    }

//    void addDragon(Scanner scanner, Connection connection, User user, ExecutorService executorService) {
//        Dragon dragon = createDragon(scanner, user);
//        executorService.submit();
//        if (dragonsDAO.insertDragon(dragon, connection)) {
//            getCollectionManager().getIdFromDb(dragon, connection);
//            getCollectionManager().addDragon(dragon);
//            System.out.println("Dragon was added : " + dragon);
//        } else {
//            System.out.println("Database crash \n");
//        }
//    }

    void addDragon(Scanner scanner, Connection connection, User user, ExecutorService executorService) {
        ReentrantLock reentrantLock = new ReentrantLock();

        Dragon dragon = createDragon(scanner, user);
        InsertDragonCall insertDragonCall = new InsertDragonCall(dragon, dragonsDAO, connection);
        try {
            if (executorService.submit(insertDragonCall).get()) {
                reentrantLock.lock();
                getCollectionManager().getIdFromDb(dragon, connection);
                getCollectionManager().addDragon(dragon);
                reentrantLock.unlock();
                System.out.println("Dragon was added : " + dragon);
            } else {
                System.out.println("Database crash \n");
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }


}
