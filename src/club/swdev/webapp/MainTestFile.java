package club.swdev.webapp;

import java.io.File;
import java.io.IOException;

public class MainTestFile {
    public static void main(String[] args) {
        // printDir("./src/club/swdev/webapp");
        printDir("./src", "    ");
    }

    public static void printDir(String dirPath, String indent) {
        File dir = new File(dirPath);
        System.out.println(indent + dir.getName());
        File[] files = dir.listFiles();
        if (files == null) {
            return;
        }
        for (File inode : files) {
            if (!inode.isDirectory()) {
                System.out.println(indent + "    " + inode.getName());
            } else {
                try {
                    printDir(inode.getCanonicalPath(), indent);
                } catch (IOException e) {
                    throw new RuntimeException("Error getting directory's canonical path", e);
                }
            }
        }
    }
}
