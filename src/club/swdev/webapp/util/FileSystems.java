package club.swdev.webapp.util;

import java.io.File;

public class FileSystems {
    public static File getDirectory(String dirPath) {
        File directory = new File(dirPath);
        if (!directory.exists()) {
           directory.mkdir();
        }
        File[] dirFiles = directory.listFiles();
        if (directory.listFiles() != null) {
            for (File file : dirFiles) {
                file.getAbsoluteFile().delete();
            }
        }
        return directory;
    }
}
