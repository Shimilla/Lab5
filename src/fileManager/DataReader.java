package fileManager;

import dragon.CollectionManager;
import dragon.Dragon;
import dragon.DragonType;
import dragon.DragonValidate;

import java.io.*;
import java.util.Locale;

public class DataReader {
    private final FileReader fileReader;
    private final BufferedReader bufferedReader;
    private final CollectionManager collectionManager;

    public DataReader(FileReader fileReader, BufferedReader bufferedReader, CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
        this.fileReader = fileReader;
        this.bufferedReader = bufferedReader;
    }

    private Dragon getDragonFromFile() {
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
                                return createDragon(age, name, weight, dragonType);
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

    public void addDragon() {
        Dragon dragon = getDragonFromFile();
        if (dragon == null) {
            return;
        }
        collectionManager.addDragon(dragon);
        System.out.println("Dragon was added : " + dragon);
        System.out.println();
    }

    private Dragon createDragon(long age, String name, int weight, DragonType dragonType) {
        Dragon dragon = new Dragon(name, age, weight, dragonType);
        collectionManager.initIdDragon(dragon);
        return dragon;
    }

    public void update_id() {
        DragonValidate dragonValidate = new DragonValidate();

        try {
            int id = Integer.parseInt(bufferedReader.readLine());
            if (dragonValidate.isCorrectId(id, collectionManager)) {
                Dragon dragon = getDragonFromFile();
                if (dragon == null) {
                    return;
                }
                collectionManager.update(id, dragon);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found, check path to file \n");
        } catch (IOException ex) {
            System.out.println("Hard exception \n");
        } catch (NumberFormatException | NullPointerException exe) {
            System.out.println("Parameters for the commands 'update_id' are not filled in correctly, check the content of the lines \n");
        }
    }

    public void remove_by_id() {
        DragonValidate dragonValidate = new DragonValidate();
        try {
            int id = Integer.parseInt(bufferedReader.readLine());
            if (dragonValidate.isCorrectId(id, collectionManager)) {
                collectionManager.remove_by_id(id);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found, check path to file \n");
        } catch (IOException ex) {
            System.out.println("Hard exception \n");
        } catch (NumberFormatException | NullPointerException exe) {
            System.out.println("Parameters for the commands 'remove_by_id' are not filled in correctly, check the content of the lines \n");
        }
    }

    public void remove_at() {
        DragonValidate dragonValidate = new DragonValidate();
        try {
            int index = Integer.parseInt(bufferedReader.readLine());
            if (dragonValidate.isCorrectIndex(index, collectionManager)) {
                collectionManager.remove_at(index);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found, check path to file \n");
        } catch (IOException ex) {
            System.out.println("Hard exception \n");
        } catch (NumberFormatException | NullPointerException exe) {
            System.out.println("Parameters for the commands 'update_id' are not filled in correctly, check the content of the lines \n");
        }
    }

    public void add_if_min() {
        try {
            Dragon dragon = getDragonFromFile();
            if (dragon == null) {
                return;
            }
            collectionManager.add_if_min(dragon);

        } catch (NumberFormatException | NullPointerException exe) {
            System.out.println("Parameters for the commands 'update_id' are not filled in correctly, check the content of the lines \n");
        }
    }

    public void remove_greater() {
        try {
            Dragon dragon = getDragonFromFile();
            if (dragon == null) {
                return;
            }
            collectionManager.remove_greater(dragon);

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
