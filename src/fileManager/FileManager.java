package fileManager;

import dragon.CollectionManager;
import com.google.gson.Gson;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileManager {
    private final CollectionManager collectionManager;

    public FileManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public void save(File fileName) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
            Gson gson = new Gson();
            String str = gson.toJson(collectionManager.getDragons());
            byte[] strToBytes = str.getBytes();
            fileOutputStream.write(strToBytes);
            System.out.printf("Collection is successfully recorded to file %s \n", fileName);
            System.out.println();
        } catch (NullPointerException n) {
            System.out.println("null");
        } catch (FileNotFoundException e) {
            System.out.println("File not found, check path to file \n");
        } catch (IOException ex) {
            System.out.println("Ошибка хз \n");
        }
    }

    public void execute_script(File fileName) {
        String line;
        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            DataReader dataReader = new DataReader(fileReader, bufferedReader, collectionManager);

            while ((line = bufferedReader.readLine()) != null) {
                if (line.equalsIgnoreCase("add")) {
                    dataReader.addDragon();
                } else if (line.equalsIgnoreCase("help")) {
                    collectionManager.help();
                } else if (line.equalsIgnoreCase("info")) {
                    collectionManager.info();
                } else if (line.equalsIgnoreCase("update_id")) {
                    dataReader.update_id();
                } else if (line.equalsIgnoreCase("remove_by_id")) {
                    dataReader.remove_by_id();
                } else if (line.equalsIgnoreCase("clear")) {
                    collectionManager.clear();
                } else if (line.equalsIgnoreCase("save")) {
                    dataReader.save();
                } else if (line.equalsIgnoreCase("exit")) {
                    dataReader.exit();
                } else if (line.equalsIgnoreCase("remove_at")) {
                    dataReader.remove_at();
                } else if (line.equalsIgnoreCase("add_if_min")) {
                    dataReader.add_if_min();
                } else if (line.equalsIgnoreCase("remove_greater")) {
                    dataReader.remove_greater();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("You entered the wrong file name \n");
        } catch (IOException ex) {
            System.out.println("Hard exception \n");
        }
    }

}
