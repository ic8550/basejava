package club.swdev.webapp;

import club.swdev.webapp.model.Resume;
import club.swdev.webapp.storage.ArrayStorage;

/**
 * Test for your club.swdev.webapp.storage.ArrayStorage implementation
 */
public class MainTestArrayStorage {
    static final ArrayStorage ARRAY_STORAGE = new ArrayStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume();
        r1.setUuid("uuid1");
        r1.setData("111");
        Resume r2 = new Resume();
        r2.setUuid("uuid2");
        r2.setData("222");
        Resume r3 = new Resume();
        r3.setUuid("uuid3");
        r3.setData("333");
        Resume r222222 = new Resume();
        r222222.setUuid("uuid2");
        r222222.setData("222222");

        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);

        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));

        printAll();
        ARRAY_STORAGE.update(r222222);
        printAll();
        ARRAY_STORAGE.delete(r222222.getUuid());
        printAll();
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAll()) {
            System.out.println(r);
        }
    }
}
