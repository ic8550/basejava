package club.swdev.webapp;

import club.swdev.webapp.exception.StorageException;
import club.swdev.webapp.model.Resume;
import club.swdev.webapp.storage.ListStorage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Interactive test for club.swdev.webapp.storage.ArrayStorage implementation
 * (just run, no need to understand)
 */
public class MainList {
    private final static ListStorage LIST_STORAGE = new ListStorage();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Resume r;
        while (true) {
            System.out.print("\nВведите одну из команд - (list | size | save uuid | update uuid | delete uuid | get uuid | clear | exit): ");
            String[] params = reader.readLine().trim().toLowerCase().split(" ");
            if (params.length < 1 || params.length > 2) {
                System.out.println("Неверная команда.");
                continue;
            }
            String uuid = null;
            if (params.length == 2) {
                uuid = params[1].intern();
            }
            switch (params[0]) {
                case "list":
                    printAll();
                    break;
                case "size":
                    System.out.println(LIST_STORAGE.size());
                    break;
                case "save":
                    r = new Resume(uuid);
                    try {
                        LIST_STORAGE.save(r);
                    } catch (StorageException e) {
                        System.out.println("Error save(): " + e.getMessage());
                    }
                    printAll();
                    break;
                case "update":
                    r = new Resume(uuid);
                    try {
                        LIST_STORAGE.update(r);
                    } catch (StorageException e) {
                        System.out.println("Error update(): " + e.getMessage());
                    }
                    printAll();
                    break;
                case "delete":
                    try {
                        LIST_STORAGE.delete(uuid);
                    } catch (StorageException e) {
                        System.out.println("Error delete(): " + e.getMessage());
                    }
                    printAll();
                    break;
                case "get":
                    try {
                        Resume resume = LIST_STORAGE.get(uuid);
                        System.out.println(resume);
                    } catch (StorageException e) {
                        System.out.println("Error in get(): " + e.getMessage());
                    }
                    break;
                case "clear":
                    LIST_STORAGE.clear();
                    printAll();
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Неверная команда.");
                    break;
            }
        }
    }

    static void printAll() {
        List<Resume> all = LIST_STORAGE.getAllSorted();
        System.out.println("----------------------------");
        if (all.size() == 0) {
            System.out.println("Empty");
        } else {
            for (Resume r : all) {
                System.out.println(r);
            }
        }
        System.out.println("----------------------------");
    }
}
