package dragon;

import DAO.DragonsDAO;
import Users.User;
import util.printInfoCollection;

import java.sql.Connection;
import java.util.*;


public class CollectionManager {
    private final String dateInit = new Date().toString();
    private List<Dragon> dragons = new ArrayList<>();
    private final util.printInfoCollection printInfoCollection = new printInfoCollection();
    private final DragonsDAO dragonsDAO = new DragonsDAO();

    public void addDragon(Dragon dragon) {
        dragons.add(dragon);
    }

    public void setDragon(int index, Dragon dragon) {
        dragons.set(index, dragon);
    }

    public void clear(Connection connection, User user) {
        if (dragonsDAO.isEmptyTableByUser(connection, user)) {
            System.out.println("Your table has no entries \n");
        } else {
            if (dragonsDAO.clear(connection, user)) {
                removeByOwnerId(user.getId());
                System.out.println("The collection has been purged \n");
            } else {
                System.out.println("Database crash \n");
            }
        }
    }

    private void removeByOwnerId(int ownerId) {
        Iterator<Dragon> iterator = dragons.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getOwner().getId() == ownerId) {
                iterator.remove();
            }
        }
    }

    public ArrayList<Dragon> getMyDragons(User user) {
        ArrayList<Dragon> myDragons = new ArrayList<>();
        for (Dragon dragon : dragons) {
            if (dragon.getOwner().getId() == user.getId()) {
                myDragons.add(dragon);
            }
        }
        return myDragons;
    }

    public void remove_by_id(int id) {
        int count = 0;
        Iterator<Dragon> iterator = dragons.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getId() == id) {
                iterator.remove();
                count++;
                System.out.printf("Dragon with id=%d has been deleted \n", id);
                break;
            }
        }
        if (count == 0) {
            System.out.println("ID was not found");
        }
    }

    public List<Dragon> getDragons() {
        return dragons;
    }

    public void initDragons(List<Dragon> dragons) {
        this.dragons = dragons;
    }

    public void getIdFromDb(Dragon dragon, Connection connection) {
        int id = dragonsDAO.getId(connection);
        dragon.setId(id);
    }

    public void initIdDragon(Dragon dragon, Connection connection) {
        int maxId = 0;
        if (dragons.size() == 0) {
            maxId = 0;
        } else {
            maxId = dragonsDAO.getId(connection);
        }
        dragon.setId(maxId + 1);
    }

    public void help() {
        printInfoCollection.help();
    }

    public void info() {
        printInfoCollection.info(getClass().getName(), dateInit, dragons.size());
    }

    public void show() {
        List<Dragon> copy = new ArrayList<>(dragons);
        printInfoCollection.show(copy);
    }

    public void update(int id, Dragon dragon) {
        int index = 0;
        boolean hasId = false;

        for (Dragon drag : dragons) {
            if (drag.getId() == id) {
                setDragon(index, dragon);
                dragon.setId(id);
                System.out.printf("Collection element with ID = %d was updated \n", id);
                System.out.println();
                hasId = true;
                return;
            }
            index++;
        }
        if (!hasId) {
            System.out.println("ID was not found, check if the entered ID is correct \n");
        }
    }

    public void add_if_min(Dragon dragon, Connection connection, User user) {
        if (dragonsDAO.isEmptyTableByUser(connection, user)) {
            if (dragonsDAO.insertDragon(dragon, connection)) {
                addDragon(dragon);
                System.out.printf("The element %s was successfully added \n", dragon.getName());
                System.out.println();
            } else {
                System.out.println("Database crash \n");
            }
        } else {
            ArrayList<Dragon> myDragons = getMyDragons(user);
            Collections.sort(myDragons);
            long min = myDragons.get(0).getAge();
            if (dragon.getAge() < min) {
                if (dragonsDAO.insertDragon(dragon, connection)) {
                    addDragon(dragon);
                    System.out.printf("The element %s was successfully added \n", dragon.getName());
                    System.out.println();
                } else {
                    System.out.println("Database crash \n");
                }
            } else {
                System.out.println("The element could not be added. " +
                        "The value of the element to be added must be smaller than the smallest element in the collection.");
                System.out.println();
            }
        }
    }

    public void remove_greater(Dragon dragon, Connection connection, User user) {
        if (dragonsDAO.isEmptyTableByUser(connection, user)) {
            System.out.println("You have an empty collection \n");
            return;
        } else {
            Collections.sort(dragons);
            List<Dragon> updateList = new ArrayList<>();
            if (dragonsDAO.remove_greater(dragon, connection, user)) {
                for (Dragon dragonOld : dragons) {
                    if (dragonOld.getAge() <= dragon.getAge() || dragonOld.getOwner().getId() != dragon.getOwner().getId()) {
                        updateList.add(dragonOld);
                    }
                }
                initDragons(updateList);
                System.out.println("the following items remain in the collection: \n");
                show();
                System.out.println();
            } else {
                System.out.println("Database crash \n");
            }
        }


    }

    public List<Integer> getListId() {
        List<Integer> listId = new ArrayList<>(dragons.size());
        for (Dragon dragon : dragons) {
            listId.add(dragon.getId());
        }
        return listId;
    }

    public Dragon getDragonById(int id) {
        for (Dragon dragon : dragons) {
            if (dragon.getId() == id) {
                return dragon;
            }
        }
        return null;
    }
}
