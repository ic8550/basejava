package club.swdev.webapp;

import java.io.File;
import java.io.IOException;

public class MainTestFile {
    public static void main(String[] args) {
        // printDir("./src/club/swdev/webapp");
        printDir("./src", getOffset(4), 2);
    }

    public static void printDir(String dirPath, String indent, int offsetSize) {
        File dir = new File(dirPath);
        File[] files = dir.listFiles();
        if (files == null) {
            return;
        }
        for (File inode : files) {
            if (inode.isFile()) {
                System.out.println(indent + inode.getName());
            } else if (inode.isDirectory()) {
                System.out.println(indent + inode.getName() + "/");
                try {
                    printDir(inode.getCanonicalPath(), indent + getOffset(offsetSize), offsetSize);
                } catch (IOException e) {
                    throw new RuntimeException("Error getting directory's canonical path", e);
                }
            }
        }
    }

    public static String getOffset(int offsetSize) {
        char[] arrayOfChar = new char[offsetSize];
        for (int i = 0; i < offsetSize; i++) {
            arrayOfChar[i] = ' ';
        }
        return String.valueOf(arrayOfChar);
    }
}
