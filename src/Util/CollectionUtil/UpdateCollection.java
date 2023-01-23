package Util.CollectionUtil;

import Dragon.*;

public class UpdateCollection {
    public static void update(CollectionManager collectionManager, int id, Dragon dragon) {
        collectionManager.getDragons().add(id, dragon);
        System.out.printf("Collection element with index %d was updated", id);
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
            System.out.printf("The element could not be added. The value of the element to be added must be smaller than the smallest element in the collection.");
        }
    }

    public static void remove_greater(CollectionManager collectionManager, Dragon dragon) {
        for (Dragon drag : collectionManager.getDragons()) {
            if (drag.getAge() > dragon.getAge()) {
                collectionManager.getDragons().remove(drag);
                System.out.printf("Element %s was deleted", drag.getName());
            }
        }
    }

}
