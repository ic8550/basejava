package club.swdev.webapp;

import java.io.File;
import java.io.IOException;

public class MainTestFile {
    public static void main(String[] args) {
        printDir("./src", getIndent(0), 4);
    }

    public static void printDir(String dirPath, String indent, int indentValue) {
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
                    printDir(inode.getCanonicalPath(), indent + getIndent(indentValue), indentValue);
                } catch (IOException e) {
                    throw new RuntimeException("Error getting directory's canonical path", e);
                }
            }
        }
    }

    public static String getIndent(int indentValue) {
        char[] arrayOfChar = new char[indentValue];
        for (int i = 0; i < indentValue; i++) {
            arrayOfChar[i] = ' ';
        }
        return String.valueOf(arrayOfChar);
    }
}
