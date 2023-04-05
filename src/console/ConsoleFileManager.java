package console;

import Users.User;
import dragon.CollectionManager;
import fileManager.FileManager;
import util.printInfoCollection;

import java.io.File;
import java.nio.file.Files;
import java.sql.Connection;
import java.util.Scanner;

public class ConsoleFileManager {
    private final CollectionManager collectionManager;

    public ConsoleFileManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    void save(Scanner fileName) {
        FileManager fileManager = new FileManager(collectionManager);
        System.out.println("Enter the file name for save");
        File file = new File(fileName.nextLine().trim());
        if (!file.exists() || file.isDirectory()) {
            System.out.println("The file was created in the directory " + file.getAbsolutePath());
        }
        fileManager.save(file);
        System.out.println("File was successfully saved");
    }

    void executeFile(Scanner fileName, Connection connection, User user) {
        boolean isFileCorrect = false;
        printInfoCollection printInfoCollection = new printInfoCollection();
        FileManager fileManager = new FileManager(collectionManager);
        while (!isFileCorrect) {
            try {
                System.out.println("Enter the name of file : \n");
                File file = new File(fileName.nextLine());
                if (!file.exists()) {
                    System.out.println("File is not exists \n");
                    printInfoCollection.isEmpty(collectionManager);
                    return;
                }
                if (!Files.isReadable(file.toPath())) {
                    System.out.println("You do not have permissions to read from the file \n");
                    printInfoCollection.isEmpty(collectionManager);
                    return;
                }
                if (file.isDirectory()) {
                    System.out.println("The file cannot be a directory \n");
                    printInfoCollection.isEmpty(collectionManager);
                    return;
                }
                fileManager.execute_script(file, connection, user);
                isFileCorrect = true;
            } catch (NullPointerException e) {
                System.out.println("Make sure the file is located correctly \n");
            }
        }
        System.out.println("Collection was filled with values from the file \n");
    }

//    void executeFile(File fileName) {
//        boolean isFileCorrect = false;
//        printInfoCollection printInfoCollection = new printInfoCollection();
//        FileManager fileManager = new FileManager(collectionManager);
//        while (!isFileCorrect) {
//            try {
//                if (!fileName.exists()) {
//                    System.out.println("File is not exists \n");
//                    printInfoCollection.isEmpty(collectionManager);
//                    return;
//                }
//                if (!Files.isReadable(fileName.toPath())) {
//                    System.out.println("You do not have permissions to read from the file \n");
//                    printInfoCollection.isEmpty(collectionManager);
//                    return;
//                }
//                if (fileName.isDirectory()) {
//                    System.out.println("The file cannot be a directory \n");
//                    printInfoCollection.isEmpty(collectionManager);
//                    return;
//                }
//                fileManager.execute_script(fileName);
//                isFileCorrect = true;
//            } catch (NullPointerException e) {
//                System.out.println("Make sure the file is located correctly \n");
//            }
//        }
//        System.out.println("Collection was filled with values from the file \n");
//    }

//    void readFile(File fileName) {
//        boolean isFileCorrect = false;
//        printInfoCollection printInfoCollection = new printInfoCollection();
//        FileManager fileManager = new FileManager(collectionManager);
//        while (!isFileCorrect) {
//            try {
//                if (!fileName.exists()) {
//                    System.out.println("File is not exists \n");
//                    printInfoCollection.isEmpty(collectionManager);
//                    return;
//                }
//                if (!Files.isReadable(fileName.toPath())) {
//                    System.out.println("You do not have permissions to read from the file \n");
//                    printInfoCollection.isEmpty(collectionManager);
//                    return;
//                }
//                if (fileName.isDirectory()) {
//                    System.out.println("The file cannot be a directory \n");
//                    printInfoCollection.isEmpty(collectionManager);
//                    return;
//                }
//                fileManager.readDateFile(fileName);
//                printInfoCollection.pressEnter();
//                isFileCorrect = true;
//            } catch (NullPointerException e) {
//                System.out.println("Make sure the file is located correctly \n");
//                printInfoCollection.isEmpty(collectionManager);
//                return;
//            }
//        }
//        System.out.println("Collection was filled with values from the file \n");
//    }

}
