package FileManager;

import Dragon.CollectionManager;
import Dragon.Dragon;
import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileManager {

    public static void save(String fileName, CollectionManager collection) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
            Gson gson = new Gson();
            String str = gson.toJson(collection.getDragons());
            byte[] strToBytes = str.getBytes();
            fileOutputStream.write(strToBytes);
            System.out.println("collection is successfully recorded \n");
            System.out.println(str);
        } catch (NullPointerException n) {
            System.out.println("null");
        } catch (FileNotFoundException e) {
            System.out.println("File not found, check path to file \n");
        } catch (IOException ex) {
            System.out.println("Ошибка хз \n");
        }

    }

    public static void execute_script(String fileName, CollectionManager collectionManager) {
        // коллекция должна заполняться значениями из файла json
        String fileGson = null;
        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            while (bufferedReader.ready()) {
                fileGson = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found, check path to file \n");
        } catch (IOException ex) {
            System.out.println("Hard exception \n");
        }

        Gson gson = new Gson();
        Dragon[] dragons = gson.fromJson(fileGson, Dragon[].class);
        List<Dragon> tempArr = Arrays.asList(dragons);
        List<Dragon> dragonList = new ArrayList<>(tempArr);
        collectionManager.initDragons(dragonList);
        collectionManager.initIdCollection();
    }
}
