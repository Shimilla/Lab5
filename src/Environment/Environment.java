package Environment;

import java.io.File;

public class Environment {
    public File getFile() {
        String path = System.getenv("path");
        return new File(path);
    }
}
