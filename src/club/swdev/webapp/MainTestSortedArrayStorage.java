package club.swdev.webapp;

import club.swdev.webapp.exception.StorageException;
import club.swdev.webapp.model.Resume;
import club.swdev.webapp.storage.SortedArrayStorage;

/**
 * Test for your ListStorage implementation
 */
public class MainTestSortedArrayStorage {
    static final SortedArrayStorage STORAGE = new SortedArrayStorage();

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
        STORAGE.clear();
        printAll();
        printSize();

        System.out.println("Get resume with uuid=null:");
        try {
            Resume resume = STORAGE.get(null);
            System.out.println(resume);
        } catch (StorageException e) {
            System.out.println("Error in get(): " + e.getMessage());
        }
        printAll();
        printSize();

        System.out.println("Get resume with uuid=\"\":");
        try {
            Resume resume = STORAGE.get("");
            System.out.println(resume);
        } catch (StorageException e) {
            System.out.println("Error in get(): " + e.getMessage());
        }

        printAll();
        printSize();

        System.out.println("Get resume with uuid=\"dummy\":");
        try {
            Resume resume = STORAGE.get("dummy");
            System.out.println(resume);
        } catch (StorageException e) {
            System.out.println("Error in get(): " + e.getMessage());
        }

        printAll();
        printSize();

        System.out.println("Delete resume with uuid=null:");
        try {
            STORAGE.delete(null);
        } catch (StorageException e) {
            System.out.println(e.getMessage());
        }
        printAll();
        printSize();

        System.out.println("Delete resume with uuid=\"\":");
        try {
            STORAGE.delete("");
        } catch (StorageException e) {
            System.out.println(e.getMessage());
        }
        printAll();
        printSize();

        System.out.println("Delete resume with uuid=\"dummy\":");
        try {
            STORAGE.delete("dummy");
        } catch (StorageException e) {
            System.out.println("Error delete(): " + e.getMessage());
        }
        printAll();
        printSize();

        System.out.println("Save null:");
        try {
            STORAGE.save(null);
        } catch (StorageException e) {
            System.out.println(e.getMessage());
        }
        printAll();
        printSize();

        System.out.println("Save resume with uuid=null:");
        try {
            STORAGE.save(rNullUuid);
        } catch (StorageException e) {
            System.out.println(e.getMessage());
        }
        printAll();
        printSize();

        System.out.println("Save resume with uuid=\"\":");
        try {
            STORAGE.save(rEmptyUuid);
        } catch (StorageException e) {
            System.out.println(e.getMessage());
        }
        printAll();
        printSize();

        System.out.println("Save r3:");
        try {
            STORAGE.save(r3);
        } catch (StorageException e) {
            System.out.println(e.getMessage());
        }
        printAll();
        printSize();

        System.out.println("Save r3 again:");
        try {
            STORAGE.save(r33);
        } catch (StorageException e) {
            System.out.println("Error save(): " + e.getMessage());
        }
        printAll();
        printSize();

        System.out.println("Save r2:");
        try {
            STORAGE.save(r2);
        } catch (StorageException e) {
            System.out.println("Error save(): " + e.getMessage());
        }
        printAll();
        printSize();

        System.out.println("Save r1:");
        try {
            STORAGE.save(r1);
        } catch (StorageException e) {
            System.out.println("Error save(): " + e.getMessage());
        }
        printAll();
        printSize();

        System.out.println("Save r4:");
        try {
            STORAGE.save(r4);
        } catch (StorageException e) {
            System.out.println("Error save(): " + e.getMessage());
        }
        printAll();
        printSize();

        System.out.println("Get resume with uuid=null:");
        try {
            Resume resume = STORAGE.get(null);
            System.out.println(resume);
        } catch (StorageException e) {
            System.out.println("Error in get(): " + e.getMessage());
        }
        printAll();
        printSize();

        System.out.println("Get resume with uuid=\"\":");
        try {
            Resume resume = STORAGE.get("");
            System.out.println(resume);
        } catch (StorageException e) {
            System.out.println("Error in get(): " + e.getMessage());
        }
        printAll();
        printSize();

        System.out.println("Get resume with uuid=\"dummy\":");
        try {
            Resume resume = STORAGE.get("dummy");
            System.out.println(resume);
        } catch (StorageException e) {
            System.out.println("Error in get(): " + e.getMessage());
        }
        printAll();
        printSize();

        System.out.println("Get r1:");
        try {
            Resume resume = STORAGE.get(r1.getUuid());
            System.out.println(resume);
        } catch (StorageException e) {
            System.out.println("Error in get(): " + e.getMessage());
        }
        printAll();
        printSize();

        System.out.println("Update null:");
        try {
            STORAGE.update(null);
        } catch (StorageException e) {
            System.out.println(e.getMessage());
        }
        printAll();
        printSize();

        System.out.println("Update resume with uuid=null:");
        try {
            STORAGE.update(rNullUuid);
        } catch (StorageException e) {
            System.out.println(e.getMessage());
        }
        printAll();
        printSize();

        System.out.println("Update resume with uuid=\"\":");
        try {
            STORAGE.update(rEmptyUuid);
        } catch (StorageException e) {
            System.out.println(e.getMessage());
        }
        printAll();
        printSize();

        System.out.println("Update r2:");
        try {
            STORAGE.update(r22);
        } catch (StorageException e) {
            System.out.println("Error in update(): " + e.getMessage());
        }
        printAll();
        printSize();

        System.out.println("Delete resume with uuid=null:");
        try {
            STORAGE.delete(null);
        } catch (StorageException e) {
            System.out.println(e.getMessage());
        }
        printAll();
        printSize();

        System.out.println("Delete resume with uuid=\"\":");
        try {
            STORAGE.delete("");
        } catch (StorageException e) {
            System.out.println(e.getMessage());
        }
        printAll();
        printSize();

        System.out.println("Delete resume with uuid=\"dummy\":");
        try {
            STORAGE.delete("dummy");
        } catch (StorageException e) {
            System.out.println("Error in delete(): " + e.getMessage());
        }
        printAll();
        printSize();

        System.out.println("Delete r3:");
        try {
            STORAGE.delete(r3.getUuid());
        } catch (StorageException e) {
            System.out.println("Error in delete(): " + e.getMessage());
        }
        printAll();
        printSize();

        System.out.println("Clear:");
        STORAGE.clear();
        printAll();
        printSize();
    }

    static void printAll() {
        Resume[] all = STORAGE.getAll();
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
        System.out.println("Storage size: " + STORAGE.size() + "\n\n");
    }
}
