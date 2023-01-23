package Main;
/**
 *Class Main
 *
 */

import Dragon.*;
import FileManager.FileManager;
import Util.CollectionUtil.PrintInfoCollection;
import Util.CollectionUtil.UpdateCollection;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        CollectionManager dragons = new CollectionManager();
        Scanner scanner = new Scanner(System.in);
        FileManager.execute_script("C:\\Games\\JavaJSON", dragons);

    }
}

