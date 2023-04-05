package Users;

import com.google.common.hash.HashCode;
import dragon.CollectionManager;
import dragon.Dragon;

public class User {
    private int id;
    private final String name;
    private final String surname;
    private final String login;
    private final String hash;

    public User(String name, String surname, String login, String hash) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.hash = hash;
    }

    public User(int id, String name, String surname, String login, String hash) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.hash = hash;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getLogin() {
        return login;
    }

    public String getHash() {
        return hash;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void showMine(CollectionManager collectionManager) {
        if (!collectionManager.getMyDragons(this).isEmpty()) {
            for (Dragon dragon : collectionManager.getMyDragons(this)) {
                System.out.println(dragon);
            }
        } else {
            System.out.println("Collection is empty");
        }
    }

}
