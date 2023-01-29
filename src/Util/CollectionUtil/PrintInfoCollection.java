package Util.CollectionUtil;

import Dragon.CollectionManager;
import Dragon.Dragon;

public class PrintInfoCollection {
    public static void help() {
        System.out.println("""
                The following commands are supported by the program :\s
                help \s
                info \s
                show \s
                addDragon \s
                update id \s
                remove_by_id \s
                clear \s
                save \s
                execute_script \s
                exit \s
                remove_at \s
                add_if_min \s
                remove_greater ; """);
    }

    public static void info(CollectionManager collectionManager) {
        System.out.println("collection is a class of \n" + CollectionManager.class.getTypeName());
        System.out.println("Collection was created by \n" + collectionManager.getDateInit());
        System.out.printf("Collection has %s elements \n",collectionManager.getDragons().size());
    }

    public static void show(CollectionManager collectionManager) {
        for (Dragon dragon : collectionManager.getDragons()) {
            System.out.println(dragon);
        }
    }
}
