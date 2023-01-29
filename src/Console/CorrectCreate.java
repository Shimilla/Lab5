package Console;

import Dragon.CollectionManager;
import Dragon.Dragon;
import Dragon.DragonType;

import java.util.Locale;
import java.util.Scanner;

class CorrectCreate {
    static Dragon createDragon(Scanner scFile, CollectionManager collectionManager) {
        long age = getAgeDragon(scFile, collectionManager);
        String name = getNameDragon(scFile, collectionManager);
        int weight = getWeightDragon(scFile, collectionManager);
        DragonType dragonType = getDragonType(scFile, collectionManager);
        Dragon dragon = new Dragon(name, age, weight, dragonType);
        collectionManager.initIdDragon(dragon);
        return dragon;
    }

    static void addDragonToCollection(Scanner scanner, CollectionManager collectionManager) {
        Dragon dragon = createDragon(scanner, collectionManager);
        collectionManager.addDragon(dragon);
        System.out.println("Was added a Dragon \n");
    }

    /**
     * Initialize the age Dragon
     */
    private static long getAgeDragon(Scanner scAge, CollectionManager collectionManager) {

        boolean isCorrectAge = false;
        long age = 0;
        while (!isCorrectAge) {
            System.out.println("Enter the age: \n");
            try {
                age = Long.parseLong(scAge.nextLine());
                if (age < 0) {
                    System.out.println("the age must be a whole number greater than zero \n");
                } else {
                    isCorrectAge = true;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("check that the data entered is correct, the age must be a whole number greater than zero \n");
            }
        }
        return age;
    }

    /**
     * Initialize the name Dragon
     */
    private static String getNameDragon(Scanner scName, CollectionManager collectionManager) {

        boolean isCorrectName = false;
        String name = "";
        while (!isCorrectName) {
            try {
                System.out.println("Enter the name: \n");
                name = scName.nextLine();
                if (name.equals("")) {
                    throw new NullPointerException();
                }
                if (Integer.parseInt(name) % 2 == 1 || Integer.parseInt(name) % 2 == 0) {
                    System.out.println("the name can't be a number \n");
                }
            } catch (NumberFormatException e) {
                isCorrectName = true;
            } catch (NullPointerException ex) {
                System.out.println("the name can't be empty \n");
            }
        }
        return name;
    }

    /**
     * Initialize the weight Dragon
     */
    private static int getWeightDragon(Scanner scWeight, CollectionManager collectionManager) {

        boolean isCorrectWeight = false;
        int weight = 0;
        while (!isCorrectWeight) {
            System.out.println("Enter the weight: \n");
            try {
                weight = Integer.parseInt(scWeight.nextLine());
                if (weight < 0) {
                    System.out.println("the weight must be a whole number greater than zero \n");
                } else {
                    isCorrectWeight = true;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("check that the data entered is correct, the weight must be a whole number greater than zero \n");
            }
        }
        return weight;
    }

    /**
     * Initialize the Dragon type
     */
    private static DragonType getDragonType(Scanner scDragonType, CollectionManager collectionManager) {
        boolean isCorrectType = false;
        String type = "";
        DragonType dragonType = null;

        while (!isCorrectType) {
            try {
                System.out.println("Enter one of the suggested types \n");
                DragonType.getDragonType();
                type = scDragonType.nextLine().trim();
                if (type.equals("")) {
                    throw new NullPointerException();
                }

                if (type.equalsIgnoreCase(DragonType.AIR.name()) ||
                type.equalsIgnoreCase(DragonType.WATER.name()) ||
                type.equalsIgnoreCase(DragonType.FIRE.name()) ||
                type.equalsIgnoreCase(DragonType.UNDERGROUND.name())) {
                    dragonType = DragonType.valueOf(type.toUpperCase(Locale.ROOT));
                    isCorrectType = true;
                }
            } catch (NullPointerException e) {
                System.out.println("Dragon type can't be empty \n");
            }
        }
        return dragonType;
    }


}
