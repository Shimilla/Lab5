package Util.CollectionUtil;

import Dragon.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class UpdateCollection {
    public static void update(CollectionManager collectionManager, int id, Dragon dragon) {
        collectionManager.remove_by_id(id);
        dragon.setId(id);
        collectionManager.addDragon(dragon);
        System.out.printf("Collection element with index %d was updated \n", id);
    }

    public static void add_if_min(CollectionManager collectionManager, Dragon dragon) {
        int minAgeDragonFromCollection = 0;
        int i = 0;
        long minAge = 0;

        for (Dragon dr : collectionManager.getDragons()) {
            if (i == 0) {
                minAgeDragonFromCollection = (int) collectionManager.getDragon(i).getAge();
                i++;
            }
            minAge = Math.min(minAgeDragonFromCollection, dr.getAge());
        }

        if (dragon.getAge() < minAge) {
            collectionManager.addDragon(dragon);
            System.out.printf("The element %s was successfully added \n", dragon.getClass() + " " + dragon.getName());
        } else {
            System.out.printf("The element could not be added. The value of the element to be added must be smaller than the smallest element in the collection. \n");
        }
    }

    public static void remove_greater(CollectionManager collectionManager, Dragon dragon) {
        List<Dragon> updateList = new ArrayList<>();

        for (Dragon dragonOld : collectionManager.getDragons()) {
            if (dragonOld.getAge() < dragon.getAge()) {
                updateList.add(dragonOld);
            }
        }
        collectionManager.clear();
        collectionManager.initDragons(updateList);
        System.out.println("the following items remain in the collection: \n");
        PrintInfoCollection.show(collectionManager);
        System.out.println();
    }

}
