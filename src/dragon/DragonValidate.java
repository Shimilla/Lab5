package dragon;

import Users.User;

public class DragonValidate {

    public boolean isCorrectAge(long age) {
        boolean isCorrAge = false;
        try {
            if (age <= 0) {
                System.out.println("The age must be a whole number greater than zero \n");
            } else {
                isCorrAge = true;
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Check that the data entered is correct, the weight must be a whole number greater than zero \n");
        }
        return isCorrAge;
    }

    public boolean isCorrectName(String name) {
        boolean isCorrName = false;
        try {
            if (name.equals("")) {
                throw new NullPointerException();
            }
            if (Integer.parseInt(name) % 2 == 1 || Integer.parseInt(name) % 2 == 0) {
                System.out.println("The name can't be a number \n");
            }
        } catch (NumberFormatException e) {
            isCorrName = true;
        } catch (NullPointerException ex) {
            System.out.println("The name can't be empty \n");
        }
        return isCorrName;
    }

    /**
     * Initialize the weight Dragon
     */
    public boolean isCorrectWeight(int weight) {
        boolean isCorrWeight = false;
        try {
            if (weight <= 0) {
                System.out.println("The weight must be a whole number greater than zero \n");
            } else {
                isCorrWeight = true;
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Check that the data entered is correct, the weight must be a whole number greater than zero \n");
        }
        return isCorrWeight;
    }

    public boolean isCorrectDragon(String type) {
        boolean isCorrType = false;
        try {
            if (type.equals("")) {
                throw new NullPointerException();
            }
            if (type.equalsIgnoreCase(DragonType.AIR.name()) ||
                    type.equalsIgnoreCase(DragonType.WATER.name()) ||
                    type.equalsIgnoreCase(DragonType.FIRE.name()) ||
                    type.equalsIgnoreCase(DragonType.UNDERGROUND.name())) {
                isCorrType = true;
            }
        } catch (NullPointerException e) {
            System.out.println("Dragon type can't be empty \n");
        }
        return isCorrType;
    }

    public boolean isCorrectId(int id, CollectionManager collectionManager) {
        boolean isCorrId = false;
        try {
            if (id <= 0) {
                System.out.println("The id must be a whole number greater than zero \n");
                // добавить условие на проверку на своих драконов
            } else if (!collectionManager.getListId().contains(id)) {
                System.out.println("ID was not found, check if the entered ID is correct \n");
            } else {
                isCorrId = true;
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Check that the data entered is correct, the id must be a whole number greater than zero \n");
        }
        return isCorrId;
    }

    public boolean isCorrectIndex(int index, CollectionManager collectionManager, User user) {
        boolean isCorrectIndex = false;
        try {
            if (index <= 0) {
                System.out.println("The index must be a whole number greater than zero \n");
            } else if (index >= collectionManager.getMyDragons(user).size()) {
                System.out.println("ID was not found, check if the entered ID is correct \n");
            } else {
                isCorrectIndex = true;
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Check that the data entered is correct, the index must be a whole number greater than zero \n");
        }
        return isCorrectIndex;
    }
}
