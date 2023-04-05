package Callable;

import DAO.Connect;

import java.sql.Connection;
import java.util.concurrent.Callable;

public class ConnectCall implements Callable<Connection> {
    private Connect connect;

    public ConnectCall(Connect connect) {
        this.connect = connect;
    }

    @Override
    public Connection call() {
        return connect.getConnect();
    }
}
