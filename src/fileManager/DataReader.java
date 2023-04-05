package fileManager;

import DAO.DragonsDAO;
import Users.User;
import console.ConsoleCreateDragon;
import dragon.CollectionManager;
import dragon.Dragon;
import dragon.DragonType;
import dragon.DragonValidate;

import java.io.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Locale;

public class DataReader {
    private final FileReader fileReader;
    private final BufferedReader bufferedReader;
    private final CollectionManager collectionManager;
    private final DragonsDAO dragonsDAO;

    public DataReader(FileReader fileReader, BufferedReader bufferedReader, CollectionManager collectionManager, DragonsDAO dragonsDAO) {
        this.collectionManager = collectionManager;
        this.fileReader = fileReader;
        this.bufferedReader = bufferedReader;
        this.dragonsDAO = dragonsDAO;
    }

    private Dragon getDragonFromFile(Connection connection) {
        boolean isWork = true;
        DragonValidate dragonValidate = new DragonValidate();
        while (isWork) {
            try {
                long age = Integer.parseInt(bufferedReader.readLine());
                if (dragonValidate.isCorrectAge(age)) {
                    String name = bufferedReader.readLine();
                    if (dragonValidate.isCorrectName(name)) {
                        int weight = Integer.parseInt(bufferedReader.readLine());
                        if (dragonValidate.isCorrectWeight(weight)) {
                            String typeName = bufferedReader.readLine().toUpperCase(Locale.ROOT).trim();
                            if (dragonValidate.isCorrectDragon(typeName)) {
                                DragonType dragonType = DragonType.valueOf(typeName);
                                return createDragon(age, name, weight, dragonType, connection);
                            }
                        }
                    }
                }

            } catch (FileNotFoundException e) {
                System.out.println("File not found, check path to file \n");
            } catch (IOException ex) {
                System.out.println("Hard exception \n");
            } catch (NumberFormatException | NullPointerException exe) {
                System.out.println("Parameters for the commands 'add' are not filled in correctly, check the content of the lines \n");
                isWork = false;
            }
        }
        return null;
    }

    public void addDragon(Connection connection, User user) {
        Dragon dragon = getDragonFromFile(connection);
        dragon.setOwner(user);
        if (dragon == null) {
            return;
        }
        if (dragonsDAO.insertDragon(dragon, connection)) {
            collectionManager.getIdFromDb(dragon, connection);
            collectionManager.addDragon(dragon);
            System.out.println("Dragon was added : " + dragon);
            System.out.println();
        } else {
            System.out.println("Database crash \n");
        }
    }

    private Dragon createDragon(long age, String name, int weight, DragonType dragonType, Connection connection) {
        Dragon dragon = new Dragon(name, age, weight, dragonType);
        collectionManager.initIdDragon(dragon, connection);
        return dragon;
    }

    public void update_id(Connection connection, User user) {
        DragonValidate dragonValidate = new DragonValidate();
        try {
            if (!dragonsDAO.isEmptyTableByUser(connection, user)) {
                int id = Integer.parseInt(bufferedReader.readLine());
                Dragon myDragon = collectionManager.getDragonById(id);
                if (myDragon.getOwner().getId() == user.getId()) {
                    if (dragonValidate.isCorrectId(id, collectionManager)) {
                        Dragon dragon = getDragonFromFile(connection);
                        dragon.setOwner(user);
                        if (dragon == null) {
                            return;
                        }
                        if (dragonsDAO.update_id(id, dragon, connection)) {
                            collectionManager.update(id, dragon);
                        } else {
                            System.out.println("Database crash \n");
                        }
                    }
                } else {
                    System.out.println("The dragon has another master. Enter 'show_mine' to get a list of your dragons \n");
                }

            } else {
                System.out.println("Your table has no entries \n");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found, check path to file \n");
        } catch (IOException ex) {
            System.out.println("Hard exception \n");
        } catch (NumberFormatException | NullPointerException exe) {
            System.out.println("Parameters for the commands 'update_id' are not filled in correctly, check the content of the lines \n");
        }
    }

    public void remove_by_id(Connection connection, User user) {
        DragonValidate dragonValidate = new DragonValidate();
        try {
            if (!dragonsDAO.isEmptyTableByUser(connection, user)) {
                int id = Integer.parseInt(bufferedReader.readLine());
                if (dragonValidate.isCorrectId(id, collectionManager)) {
                    Dragon myDragon = collectionManager.getDragonById(id);
                    if (myDragon.getOwner().getId() == user.getId()) {
                        if (dragonsDAO.remove_by_id(id, connection)) {
                            collectionManager.remove_by_id(id);
                        } else {
                            System.out.println("Database crash \n");
                        }
                    }  else {
                        System.out.println("The dragon has another master. Enter 'show_mine' to get a list of your dragons \n");
                    }
                }
            } else {
                System.out.println("Your table has no entries \n");
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found, check path to file \n");
        } catch (IOException ex) {
            System.out.println("Hard exception \n");
        } catch (NumberFormatException | NullPointerException exe) {
            System.out.println("Parameters for the commands 'remove_by_id' are not filled in correctly, check the content of the lines \n");
        }
    }

    public void remove_at(Connection connection, User user) {
        DragonValidate dragonValidate = new DragonValidate();
        try {
            if (dragonsDAO.isEmptyTableByUser(connection, user)) {
                System.out.println("You cannot remove an item by index from an empty collection \n");
                return;
            } else {
                int index = Integer.parseInt(bufferedReader.readLine());
                if (dragonValidate.isCorrectIndex(index, collectionManager, user)) {
                    int id = 0;
                    if ((id = dragonsDAO.remove_at_AndGetId(index, connection, user)) > 0) {
                        collectionManager.remove_by_id(id);
                    } else {
                        System.out.println("Database crash \n");
                    }
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found, check path to file \n");
        } catch (IOException ex) {
            System.out.println("Hard exception \n");
        } catch (NumberFormatException | NullPointerException exe) {
            System.out.println("Parameters for the commands 'update_id' are not filled in correctly, check the content of the lines \n");
        }
    }

    public void add_if_min(Connection connection, User user) {
        try {
            Dragon dragon = getDragonFromFile(connection);
            dragon.setOwner(user);
            if (dragon == null) {
                return;
            }
            collectionManager.add_if_min(dragon, connection, user);

        } catch (NumberFormatException | NullPointerException exe) {
            System.out.println("Parameters for the commands 'update_id' are not filled in correctly, check the content of the lines \n");
        }
    }

    public void remove_greater(Connection connection, User user) {
        try {
            Dragon dragon = getDragonFromFile(connection);
            dragon.setOwner(user);
            if (dragon == null) {
                return;
            }
            collectionManager.remove_greater(dragon, connection, user);

        } catch (NumberFormatException | NullPointerException exe) {
            System.out.println("Parameters for the commands 'update_id' are not filled in correctly, check the content of the lines \n");
        }
    }

    public void save() {
        FileManager fileManager = new FileManager(collectionManager);
        try {
            File file = new File(bufferedReader.readLine());
            fileManager.save(file);
        } catch (FileNotFoundException e) {
            System.out.println("File not found, check path to file \n");
        } catch (IOException ex) {
            System.out.println("Hard exception \n");
        } catch (NumberFormatException | NullPointerException exe) {
            System.out.println("Parameters for the commands 'save' are not filled in correctly, check the content of the lines \n");
        }
    }

    public void exit() {
        System.exit(0);
    }

}
