/**
 * Class Main
 */

import DAO.Connect;
import DAO.DragonsDAO;
import Users.Registration;
import console.*;
import dragon.CollectionManager;
import util.printInfoCollection;

public class Main {
    public static void main(String[] args) {
        CollectionManager dragons = new CollectionManager();
        DragonsDAO dragonsDAO = new DragonsDAO();
        Registration registration = new Registration();

        Console console = new Console(dragons,
                new ConsoleCreateDragon(dragons, dragonsDAO),
                new ConsoleModernizeCollection(dragons, dragonsDAO),
                new ConsoleFileManager(dragons),
                new printInfoCollection(),
                new Connect(),
                registration,
                new ConsoleUsersAuthorization(registration)
        );
        console.getStarted();
    }
}