package club.swdev.webapp;

import club.swdev.webapp.exception.StorageException;
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
        try {
            Resume resume = ARRAY_STORAGE.get(null);
            System.out.println(resume);
        } catch (StorageException e) {
            System.out.println("Error get(): " + e.getMessage());
        }
        printAll();
        printSize();

        System.out.println("Get resume with uuid=\"\":");
        try {
            Resume resume = ARRAY_STORAGE.get("");
            System.out.println(resume);
        } catch (StorageException e) {
            System.out.println("Error get(): " + e.getMessage());
        }

        printAll();
        printSize();

        System.out.println("Get resume with uuid=\"dummy\":");
        try {
            Resume resume = ARRAY_STORAGE.get("dummy");
            System.out.println(resume);
        } catch (StorageException e) {
            System.out.println("Error get(): " + e.getMessage());
        }

        printAll();
        printSize();

        System.out.println("Delete resume with uuid=null:");
        try {
            ARRAY_STORAGE.delete(null);
        } catch (StorageException e) {
            System.out.println("Error delete(): " + e.getMessage());
        }
        printAll();
        printSize();

        System.out.println("Delete resume with uuid=\"\":");
        try {
            ARRAY_STORAGE.delete("");
        } catch (StorageException e) {
            System.out.println("Error delete(): " + e.getMessage());
        }
        printAll();
        printSize();

        System.out.println("Delete resume with uuid=\"dummy\":");
        try {
            ARRAY_STORAGE.delete("dummy");
        } catch (StorageException e) {
            System.out.println("Error delete(): " + e.getMessage());
        }
        printAll();
        printSize();

        System.out.println("Save null:");
        try {
            ARRAY_STORAGE.save(null);
        } catch (StorageException e) {
            System.out.println("Error save(): " + e.getMessage());
        }
        printAll();
        printSize();

        System.out.println("Save resume with uuid=null:");
        try {
            ARRAY_STORAGE.save(rNullUuid);
        } catch (StorageException e) {
            System.out.println("Error save(): " + e.getMessage());
        }
        printAll();
        printSize();

        System.out.println("Save resume with uuid=\"\":");
        try {
            ARRAY_STORAGE.save(rEmptyUuid);
        } catch (StorageException e) {
            System.out.println("Error save(): " + e.getMessage());
        }
        printAll();
        printSize();

        System.out.println("Save r3:");
        try {
            ARRAY_STORAGE.save(r3);
        } catch (StorageException e) {
            System.out.println("Error save(): " + e.getMessage());
        }
        printAll();
        printSize();

        System.out.println("Save r3 again:");
        try {
            ARRAY_STORAGE.save(r33);
        } catch (StorageException e) {
            System.out.println("Error save(): " + e.getMessage());
        }
        printAll();
        printSize();

        System.out.println("Save r2:");
        try {
            ARRAY_STORAGE.save(r2);
        } catch (StorageException e) {
            System.out.println("Error save(): " + e.getMessage());
        }
        printAll();
        printSize();

        System.out.println("Save r1:");
        try {
            ARRAY_STORAGE.save(r1);
        } catch (StorageException e) {
            System.out.println("Error save(): " + e.getMessage());
        }
        printAll();
        printSize();

        System.out.println("Save r4:");
        try {
            ARRAY_STORAGE.save(r4);
        } catch (StorageException e) {
            System.out.println("Error save(): " + e.getMessage());
        }
        printAll();
        printSize();

        System.out.println("Get resume with uuid=null:");
        try {
            Resume resume = ARRAY_STORAGE.get(null);
            System.out.println(resume);
        } catch (StorageException e) {
            System.out.println("Error get(): " + e.getMessage());
        }
        printAll();
        printSize();

        System.out.println("Get resume with uuid=\"\":");
        try {
            Resume resume = ARRAY_STORAGE.get("");
            System.out.println(resume);
        } catch (StorageException e) {
            System.out.println("Error get(): " + e.getMessage());
        }
        printAll();
        printSize();

        System.out.println("Get resume with uuid=\"dummy\":");
        try {
            Resume resume = ARRAY_STORAGE.get("dummy");
            System.out.println(resume);
        } catch (StorageException e) {
            System.out.println("Error get(): " + e.getMessage());
        }
        printAll();
        printSize();

        System.out.println("Get r1:");
        try {
            Resume resume = ARRAY_STORAGE.get(r1.getUuid());
            System.out.println(resume);
        } catch (StorageException e) {
            System.out.println("Error get(): " + e.getMessage());
        }
        printAll();
        printSize();

        System.out.println("Update null:");
        try {
            ARRAY_STORAGE.update(null);
        } catch (StorageException e) {
            System.out.println("Error update(): " + e.getMessage());
        }
        printAll();
        printSize();

        System.out.println("Update resume with uuid=null:");
        try {
            ARRAY_STORAGE.update(rNullUuid);
        } catch (StorageException e) {
            System.out.println("Error update(): " + e.getMessage());
        }
        printAll();
        printSize();

        System.out.println("Update resume with uuid=\"\":");
        try {
            ARRAY_STORAGE.update(rEmptyUuid);
        } catch (StorageException e) {
            System.out.println("Error update(): " + e.getMessage());
        }
        printAll();
        printSize();

        System.out.println("Update r2:");
        try {
            ARRAY_STORAGE.update(r22);
        } catch (StorageException e) {
            System.out.println("Error update(): " + e.getMessage());
        }
        printAll();
        printSize();

        System.out.println("Delete resume with uuid=null:");
        try {
            ARRAY_STORAGE.delete(null);
        } catch (StorageException e) {
            System.out.println("Error delete(): " + e.getMessage());
        }
        printAll();
        printSize();

        System.out.println("Delete resume with uuid=\"\":");
        try {
            ARRAY_STORAGE.delete("");
        } catch (StorageException e) {
            System.out.println("Error delete(): " + e.getMessage());
        }
        printAll();
        printSize();

        System.out.println("Delete resume with uuid=\"dummy\":");
        try {
            ARRAY_STORAGE.delete("dummy");
        } catch (StorageException e) {
            System.out.println("Error delete(): " + e.getMessage());
        }
        printAll();
        printSize();

        System.out.println("Delete r3:");
        try {
            ARRAY_STORAGE.delete(r3.getUuid());
        } catch (StorageException e) {
            System.out.println("Error delete(): " + e.getMessage());
        }
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
