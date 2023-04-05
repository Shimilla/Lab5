package environment;

import java.io.File;

public class Environment {
    public File getFile() {
        String path = System.getenv("path");
        if (path != null) {
            return new File(path);
        } else {
            System.out.println("You need to create an environment variable in order to extract the collection from the file." +
                    " The program will now work with an empty collection. \n");
            return new File("");
        }
    }
}
