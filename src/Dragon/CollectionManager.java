package Dragon;

import java.util.*;


public class CollectionManager {
    private final String dateInit = new Date().toString();
    private List<Dragon> dragons = new ArrayList<>();

    public void addDragon(Dragon dragon) {
        dragons.add(dragon);
    }

    public String getDateInit() {
        return dateInit;
    }

    public void clear () {
        dragons.clear();
        System.out.println("the collection has been purged \n");
    }

    public void remove_by_id (int id) {
        int count = 0;
        Iterator<Dragon> iterator = dragons.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getId() == id) {
                iterator.remove();
                count++;
                System.out.printf("dragon with id=%d has been deleted \n", id);
            }
        }
        if (count == 0) {
            System.out.println("ID was not found");
        }
    }

    public void remove_at (int index) {
        dragons.remove(index);
        System.out.printf("the dragon with the index %d has been deleted \n", index);
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
        int maxId = dragons.get(dragons.size()-1).getId();
        dragon.setId(maxId + 1);
        System.out.printf("The object %s has changed its id to %d \n", dragon.getName(), dragon.getId());
    }
}
