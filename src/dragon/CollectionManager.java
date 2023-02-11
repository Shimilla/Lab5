package dragon;

import util.PrintInfoCollection;

import java.util.*;


public class CollectionManager {
    private final String dateInit = new Date().toString();
    private List<Dragon> dragons = new ArrayList<>();
    private final PrintInfoCollection printInfoCollection = new PrintInfoCollection();

    public void addDragon(Dragon dragon) {
        dragons.add(dragon);
    }

    public void setDragon(int index, Dragon dragon) {
        dragons.set(index,dragon);
    }

    public void clear () {
        dragons.clear();
        System.out.println("The collection has been purged \n");
    }

    public void remove_by_id (int id) {
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

    public void remove_at (int index) {
        dragons.remove(index);
        System.out.printf("The dragon with the index %d has been deleted \n", index);
    }

    public Dragon getDragon (int index) {
        return dragons.get(index);
    }

    public List<Dragon> getDragons() {
        return dragons;
    }

    public void initDragons(List<Dragon> dragons) {
        this.dragons = dragons;
    }

    public void initIdCollection() {
        int i = 1;
        for (Dragon dragon : dragons) {
            dragon.setId(i++);
            System.out.printf("The object %s has changed its id to %d \n", dragon.getName(), dragon.getId());
        }
    }

    public void initIdDragon(Dragon dragon) {
        int maxId = 0;
        if (dragons.size() == 0) {
            maxId = 0;
        } else {
            maxId = dragons.get(dragons.size()-1).getId();
        }
        dragon.setId(maxId + 1);
        System.out.printf("The object %s has changed its id to %d \n", dragon.getName(), dragon.getId());
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

    public void add_if_min(Dragon dragon) {

        if (dragons.isEmpty()) {
            addDragon(dragon);
            System.out.printf("The element %s was successfully added \n", dragon.getName());
            System.out.println();
        } else {
            Collections.sort(dragons);
            long min = dragons.get(0).getAge();

            if (dragon.getAge() < min) {
                addDragon(dragon);
                System.out.println();
            } else {
                System.out.println("The element could not be added. " +
                        "The value of the element to be added must be smaller than the smallest element in the collection.");
                System.out.println();
            }
        }

    }

    public void remove_greater(Dragon dragon) {
        Collections.sort(dragons);
        List<Dragon> updateList = new ArrayList<>();

        for (Dragon dragonOld : dragons) {
            if (dragonOld.getAge() <= dragon.getAge()) {
                updateList.add(dragonOld);
            }
        }
        initDragons(updateList);
        System.out.println("the following items remain in the collection: \n");
        show();
        System.out.println();
    }

    public List<Integer> getListId() {
        List<Integer> listId = new ArrayList<>(dragons.size());
        for (Dragon dragon : dragons) {
            listId.add(dragon.getId());
        }
        return listId;
    }

}
