package console;

import dragon.CollectionManager;
import dragon.DragonType;
import dragon.DragonValidate;

import java.util.Locale;
import java.util.Scanner;

public abstract class ConsoleCollectionBuilder {
    private final CollectionManager collectionManager;

    public ConsoleCollectionBuilder(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public CollectionManager getCollectionManager() {
        return collectionManager;
    }

    protected int getID(Scanner scId) {
        boolean isCorrectId = false;
        int id = 0;
        while (!isCorrectId) {
            System.out.println("Enter the id: \n");
            try {
                id = Integer.parseInt(scId.nextLine());
                if (id <= 0) {
                    System.out.println("the id must be a whole number greater than zero \n");
                } else if (!collectionManager.getListId().contains(id)) {
                    System.out.println("ID was not found, check if the entered ID is correct \n");
                } else {
                    isCorrectId = true;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("check that the data entered is correct, the id must be a whole number greater than zero \n");
            }
        }
        return id;
    }

    protected int getIndex(Scanner scIndex) {
        boolean isCorrectIndex = false;
        int index = 0;
        while (!isCorrectIndex) {
            System.out.println("Enter the index: \n");
            try {
                index = Integer.parseInt(scIndex.nextLine());
                if (index <= 0) {
                    System.out.println("the index must be a whole number greater than zero \n");
                } else if (index >= collectionManager.getDragons().size()) {
                    System.out.println("ID was not found, check if the entered ID is correct \n");
                } else {
                    isCorrectIndex = true;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("check that the data entered is correct, the index must be a whole number greater than zero \n");
            }
        }
        return index;
    }

    /**
     * Initialize the age Dragon
     */
    protected long getAgeDragon(Scanner scAge) {

        boolean isCorrectAge = false;
        long age = 0;
        while (!isCorrectAge) {
            System.out.println("Enter the age: \n");
            try {
                age = Long.parseLong(scAge.nextLine());
                if (age <= 0) {
                    System.out.println("The age must be a whole number greater than zero \n");
                } else {
                    isCorrectAge = true;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Check that the data entered is correct, the age must be a whole number greater than zero \n");
            }
        }
        return age;
    }

    /**
     * Initialize the name Dragon
     */
    protected String getNameDragon(Scanner scName) {

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
                    System.out.println("The name can't be a number \n");
                }
            } catch (NumberFormatException e) {
                isCorrectName = true;
            } catch (NullPointerException ex) {
                System.out.println("The name can't be empty \n");
            }
        }
        return name;
    }

    /**
     * Initialize the weight Dragon
     */
    protected int getWeightDragon(Scanner scWeight) {

        boolean isCorrectWeight = false;
        int weight = 0;
        while (!isCorrectWeight) {
            System.out.println("Enter the weight: \n");
            try {
                weight = Integer.parseInt(scWeight.nextLine());
                if (weight <= 0) {
                    System.out.println("The weight must be a whole number greater than zero \n");
                } else {
                    isCorrectWeight = true;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Check that the data entered is correct, the weight must be a whole number greater than zero \n");
            }
        }
        return weight;
    }

    /**
     * Initialize the Dragon type
     */
    protected DragonType getDragonType(Scanner scDragonType) {
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
