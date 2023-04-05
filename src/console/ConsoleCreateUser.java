package console;

import Users.Encoder;
import Users.User;
import com.google.common.hash.HashCode;

import java.util.Scanner;

public class ConsoleCreateUser {

    public User createUser(Scanner scanner) {
        return new User(getCorrectName(scanner), getCorrectSurname(scanner), getCorrectLogin(scanner), getCorrectPass(scanner));
    }

    private String getCorrectName(Scanner scName) {
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

    private String getCorrectSurname(Scanner scSurname) {
        boolean isCorrectSurname = false;
        String surname = "";
        while (!isCorrectSurname) {
            try {
                System.out.println("Enter the surname: \n");
                surname = scSurname.nextLine();
                if (surname.equals("")) {
                    throw new NullPointerException();
                }
                if (Integer.parseInt(surname) % 2 == 1 || Integer.parseInt(surname) % 2 == 0) {
                    System.out.println("The surname can't be a number \n");
                }
            } catch (NumberFormatException e) {
                isCorrectSurname = true;
            } catch (NullPointerException ex) {
                System.out.println("The surname can't be empty \n");
            }
        }
        return surname;
    }

    public String getCorrectLogin(Scanner scLogin) {
        boolean isCorrectLogin = false;
        String login = "";
        while (!isCorrectLogin) {
            try {
                System.out.println("Enter the login: \n");
                login = scLogin.nextLine();
                if (login.equals("")) {
                    throw new NullPointerException();
                }
                if (Integer.parseInt(login) % 2 == 1 || Integer.parseInt(login) % 2 == 0) {
                    System.out.println("The login can't be a number \n");
                }
            } catch (NumberFormatException e) {
                isCorrectLogin = true;
            } catch (NullPointerException ex) {
                System.out.println("The login can't be empty \n");
            }
        }
        return login;
    }

    public String getCorrectPass(Scanner scPass) {
        boolean isCorrectPass = false;
        String password = "";
        while (!isCorrectPass) {
            try {
                System.out.println("Enter the password: \n");
                password = scPass.nextLine();
                if (password.equals("")) {
                    throw new NullPointerException();
                }
                if (Integer.parseInt(password) % 2 == 1 || Integer.parseInt(password) % 2 == 0) {
                    System.out.println("The password can't be a number \n");
                }
            } catch (NumberFormatException e) {
                isCorrectPass = true;
            } catch (NullPointerException ex) {
                System.out.println("The password can't be empty \n");
            }
        }
        Encoder encoder = new Encoder();
        HashCode hashCode = encoder.getEncryptPassword(password);
        return hashCode.toString();
    }
}
