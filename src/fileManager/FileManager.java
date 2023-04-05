package fileManager;

import DAO.DragonsDAO;
import Users.User;
import dragon.CollectionManager;
import com.google.gson.Gson;
import dragon.Dragon;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public void execute_script(File fileName, Connection connection, User user) {
        String line;
        DragonsDAO dragonsDAO = new DragonsDAO();
        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            DataReader dataReader = new DataReader(fileReader, bufferedReader, collectionManager, dragonsDAO);

            while ((line = bufferedReader.readLine()) != null) {
                if (line.equalsIgnoreCase("add")) {
                    dataReader.addDragon(connection, user);
                } else if (line.equalsIgnoreCase("help")) {
                    collectionManager.help();
                } else if (line.equalsIgnoreCase("info")) {
                    collectionManager.info();
                } else if (line.equalsIgnoreCase("update_id")) {
                    dataReader.update_id(connection, user);
                } else if (line.equalsIgnoreCase("remove_by_id")) {
                    dataReader.remove_by_id(connection, user);
                } else if (line.equalsIgnoreCase("clear")) {
                    collectionManager.clear(connection, user);
                } else if (line.equalsIgnoreCase("save")) {
                    dataReader.save();
                } else if (line.equalsIgnoreCase("exit")) {
                    dataReader.exit();
                } else if (line.equalsIgnoreCase("remove_at")) {
                    dataReader.remove_at(connection, user);
                } else if (line.equalsIgnoreCase("add_if_min")) {
                    dataReader.add_if_min(connection, user);
                } else if (line.equalsIgnoreCase("remove_greater")) {
                    dataReader.remove_greater(connection, user);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("You entered the wrong file name \n");
        } catch (IOException ex) {
            System.out.println("Hard exception \n");
        }
    }

    public void readDateFile(File fileName) {
        String fileGson = null;
        String line;

        try (FileReader fileReader = new FileReader(fileName.toString());
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            while ((line = bufferedReader.readLine()) != null) {
                fileGson = line;
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
