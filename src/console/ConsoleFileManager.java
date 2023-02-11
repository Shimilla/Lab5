package console;

import dragon.CollectionManager;
import fileManager.FileManager;
import util.PrintInfoCollection;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class ConsoleFileManager {
    CollectionManager collectionManager;

    public ConsoleFileManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    void save(Scanner fileName) {
        FileManager fileManager = new FileManager(collectionManager);
        System.out.println("Enter the file name for save");
        File file = new File(fileName.nextLine().trim());
        if (!file.exists()) {
            System.out.println("File is not exists");
            return;
        }
        if (!Files.isWritable(file.toPath())) {
            System.out.println("You do not have write access to the file");
            return;
        }
        if (file.isDirectory()) {
            System.out.println("The file cannot be a directory \n");
            return;
        }
        fileManager.save(file);
        System.out.println("File was successfully saved");
    }

    void executeFile(Scanner fileName) {
        boolean isFileCorrect = false;
        PrintInfoCollection printInfoCollection = new PrintInfoCollection();
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
                fileManager.execute_script(file);
                isFileCorrect = true;
            } catch (NullPointerException e) {
                System.out.println("Make sure the file is located correctly \n");
            }
        }
        System.out.println("Collection was filled with values from the file \n");
    }

    void executeFile(File fileName) {
        boolean isFileCorrect = false;
        PrintInfoCollection printInfoCollection = new PrintInfoCollection();
        FileManager fileManager = new FileManager(collectionManager);
        while (!isFileCorrect) {
            try {
                if (!fileName.exists()) {
                    System.out.println("File is not exists \n");
                    printInfoCollection.isEmpty(collectionManager);
                    return;
                }
                if (!Files.isReadable(fileName.toPath())) {
                    System.out.println("You do not have permissions to read from the file \n");
                    printInfoCollection.isEmpty(collectionManager);
                    return;
                }
                if (fileName.isDirectory()) {
                    System.out.println("The file cannot be a directory \n");
                    printInfoCollection.isEmpty(collectionManager);
                    return;
                }
                fileManager.execute_script(fileName);
                isFileCorrect = true;
            } catch (NullPointerException e) {
                System.out.println("Make sure the file is located correctly \n");
            }
        }
        System.out.println("Collection was filled with values from the file \n");
    }
}
