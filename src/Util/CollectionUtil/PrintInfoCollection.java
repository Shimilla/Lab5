package Util.CollectionUtil;

import Dragon.CollectionManager;
import Dragon.Dragon;

public class PrintInfoCollection {
    public static void help() {
        System.out.println("""
                The following commands are supported by the program :\s
                help,\s
                info,\s
                show,\s
                addDragon(dragon),\s
                update(collectionManager,id, dragon),
                remove_by_id (id),\s
                clear,\s
                save(fileName, collection),\s
                execute_script(fileName, collectionManager),\s
                exit,\s
                remove_at(index),\s
                add_if_min(collectionManager, dragon),\s
                remove_greater(collectionManager, lastElem);""");
    }

    public static void info(CollectionManager collectionManager) {
        System.out.println("collection is a class of " + CollectionManager.class.getTypeName());
        System.out.println("Collection was created by" + collectionManager.getDateInit());
        System.out.printf("Collection has %s elements \n",collectionManager.getDragons().size());
    }

    public static void show(CollectionManager collectionManager) {
        for (Dragon dragon : collectionManager.getDragons()) {
            System.out.println(dragon);
        }
    }
}
