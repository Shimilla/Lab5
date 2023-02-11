/**
 * Class Main
 */

import console.Console;
import console.ConsoleCreateDragon;
import console.ConsoleFileManager;
import console.ConsoleModernizeCollection;
import dragon.*;
import fileManager.FileManager;


import java.io.File;
import java.nio.file.Files;
import java.util.*;
//import Console.Console;

public class Main {
    public static void main(String[] args) {

        CollectionManager dragons = new CollectionManager();
                Console console = new Console(dragons,
                new ConsoleCreateDragon(dragons),
                new ConsoleModernizeCollection(dragons),
                new ConsoleFileManager(dragons));

        console.getStarted();

    }
}
