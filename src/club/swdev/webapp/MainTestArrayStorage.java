package club.swdev.webapp;

import club.swdev.webapp.model.Resume;
import club.swdev.webapp.storage.ArrayStorage;

/**
 * Test for your ArrayStorage implementation
 */
public class MainTestArrayStorage {
    static final ArrayStorage ARRAY_STORAGE = new ArrayStorage();

    public static void main(String[] args) {
        Resume r3 = new Resume("uuid3");
        Resume r33 = new Resume("uuid3");
        Resume r2 = new Resume("uuid2");
        Resume r22 = new Resume("uuid2");
        Resume r1 = new Resume("uuid1");
        Resume r4 = new Resume("uuid4");

        Resume rNullUuid = new Resume(null);
        Resume rEmptyUuid = new Resume("");

        printAll();
        printSize();

        System.out.println("Clear:");
        ARRAY_STORAGE.clear();
        printAll();
        printSize();

        System.out.println("Get resume with uuid=null:");
        System.out.println(ARRAY_STORAGE.get(null));
        printAll();
        printSize();

        System.out.println("Get resume with uuid=\"\":");
        System.out.println(ARRAY_STORAGE.get(""));
        printAll();
        printSize();

        System.out.println("Get resume with uuid=\"dummy\":");
        System.out.println(ARRAY_STORAGE.get("dummy"));
        printAll();
        printSize();

        System.out.println("Delete resume with uuid=null:");
        ARRAY_STORAGE.delete(null);
        printAll();
        printSize();

        System.out.println("Delete resume with uuid=\"\":");
        ARRAY_STORAGE.delete("");
        printAll();
        printSize();

        System.out.println("Delete resume with uuid=\"dummy\":");
        ARRAY_STORAGE.delete("dummy");
        printAll();
        printSize();

        System.out.println("Save null:");
        ARRAY_STORAGE.save(null);
        printAll();
        printSize();

        System.out.println("Save resume with uuid=null:");
        ARRAY_STORAGE.save(rNullUuid);
        printAll();
        printSize();

        System.out.println("Save resume with uuid=\"\":");
        ARRAY_STORAGE.save(rEmptyUuid);
        printAll();
        printSize();

        System.out.println("Save r3:");
        ARRAY_STORAGE.save(r3);
        printAll();
        printSize();

        System.out.println("Save r3 again:");
        ARRAY_STORAGE.save(r33);
        printAll();
        printSize();

        System.out.println("Save r2:");
        ARRAY_STORAGE.save(r2);
        printAll();
        printSize();

        System.out.println("Save r1:");
        ARRAY_STORAGE.save(r1);
        printAll();
        printSize();

        System.out.println("Save r4:");
        ARRAY_STORAGE.save(r4);
        printAll();
        printSize();

        System.out.println("Get resume with uuid=null:");
        System.out.println(ARRAY_STORAGE.get(null));
        printAll();
        printSize();

        System.out.println("Get resume with uuid=\"\":");
        System.out.println(ARRAY_STORAGE.get(""));
        printAll();
        printSize();

        System.out.println("Get resume with uuid=\"dummy\":");
        System.out.println(ARRAY_STORAGE.get("dummy"));
        printAll();
        printSize();

        System.out.println("Get r1:");
        System.out.println(ARRAY_STORAGE.get(r1.getUuid()));
        printAll();
        printSize();

        System.out.println("Update null:");
        ARRAY_STORAGE.update(null);
        printAll();
        printSize();

        System.out.println("Update resume with uuid=null:");
        ARRAY_STORAGE.update(rNullUuid);
        printAll();
        printSize();

        System.out.println("Update resume with uuid=\"\":");
        ARRAY_STORAGE.update(rEmptyUuid);
        printAll();
        printSize();

        System.out.println("Update r2:");
        ARRAY_STORAGE.update(r22);
        printAll();
        printSize();

        System.out.println("Delete resume with uuid=null:");
        ARRAY_STORAGE.delete(null);
        printAll();
        printSize();

        System.out.println("Delete resume with uuid=\"\":");
        ARRAY_STORAGE.delete("");
        printAll();
        printSize();

        System.out.println("Delete resume with uuid=\"dummy\":");
        ARRAY_STORAGE.delete("dummy");
        printAll();
        printSize();

        System.out.println("Delete r3:");
        ARRAY_STORAGE.delete(r3.getUuid());
        printAll();
        printSize();

        System.out.println("Clear:");
        ARRAY_STORAGE.clear();
        printAll();
        printSize();
    }

    static void printAll() {
        Resume[] all = ARRAY_STORAGE.getAll();
        System.out.println("\nStorage state:");
        System.out.println("----------------------------");
        if (all.length == 0) {
            System.out.println("Empty");
        } else {
            for (Resume r : all) {
                System.out.println(r);
            }
        }
        System.out.println("----------------------------");
    }
    static void printSize() {
        System.out.println("Storage size: " + ARRAY_STORAGE.size() + "\n\n");
    }
}
