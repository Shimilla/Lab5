package Callable;

import DAO.DragonsDAO;
import dragon.Dragon;

import java.sql.Connection;
import java.util.concurrent.Callable;
import java.util.concurrent.locks.ReentrantLock;

public class InsertDragonCall implements Callable<Boolean> {
    private Dragon dragon;
    private DragonsDAO dragonsDAO;
    private Connection connection;

    public InsertDragonCall(Dragon dragon, DragonsDAO dragonsDAO, Connection connection) {
        this.dragon = dragon;
        this.dragonsDAO = dragonsDAO;
        this.connection = connection;
    }

    @Override
    public Boolean call() {
        return dragonsDAO.insertDragon(dragon, connection);
    }
}
