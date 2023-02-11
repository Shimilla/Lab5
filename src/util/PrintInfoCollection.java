package util;

import dragon.CollectionManager;
import dragon.Dragon;

import java.util.List;
import java.util.Scanner;

public class PrintInfoCollection {


    public void help() {
        System.out.println("""
                The following commands are supported by the program :\s
               
                - help : Display help for available commands \s
               
                - info : Display information about the collection \s
               
                - show : Display all items in the collection \s
               
                - add : Add a new item to the collection \s
               
                - update_id : Refresh the value of the collection item by ID \s
               
                - remove_by_id : Remove an item from the collection by its ID \s
               
                - clear : Clear the collection \s
               
                - save : Save the collection to a file \s
               
                - execute_script : Execute the script from the file \s
               
                - exit : End the program \s
               
                - remove_at : Delete a collection item by index \s
               
                - add_if_min : Add a new item to the collection if its AGE is smaller than the smallest item in the collection \s
               
                - remove_greater : Remove from the collection all items that are older than the specified AGE""");
        System.out.println();
    }

    public void info(String type, String dateInit, int size) {
        System.out.println("Collection is a class of " + type);
        System.out.println("Collection was created by " + dateInit);
        System.out.printf("Collection has %s elements \n", size);
        System.out.println();
    }

    public void show(List<Dragon> dragons) {
        if (!dragons.isEmpty()) {
            for (Dragon dragon : dragons) {
                System.out.println(dragon);
            }
            System.out.println();
        } else {
            System.out.println("Collection is empty");
        }
    }

    public static void pressEnter() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press 'Enter' to continue");
        while (!scanner.nextLine().equals("")) {
            System.out.println("Press 'Enter' to continue");
        }
    }

    public void isEmpty(CollectionManager collectionManager) {
        if (collectionManager.getDragons().isEmpty()) {
            Scanner fileName = new Scanner(System.in);
            System.out.println("Enter 'any key' to continue working with an empty collection \n");
            fileName.nextLine();
        }
    }

}
