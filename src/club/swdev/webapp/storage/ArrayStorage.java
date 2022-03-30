package club.swdev.webapp.storage;

import club.swdev.webapp.model.Resume;

import static java.util.Arrays.copyOf;
import static java.util.Arrays.fill;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    /**
     * final int MAX_SIZE - storage parameter, the maximum capacity of the storage
     */
    private final int MAX_SIZE = 3;

    private final Resume[] storage = new Resume[MAX_SIZE];

    /**
     * Size of nonempty (nonnull) part of storage[] -- Number of contiguous
     * nonnull Resumes in the storage[] array, starting from the beginning
     * of storage[].
     */
    private int size = 0;

    /**
     * Replaces all nonnull Resumes in storage[] with the null value.
     */
    public void clear() {
        fill(storage, 0, size, null);
        size = 0;
    }

    /**
     * Adds a club.swdev.webapp.model.Resume with a given uuid to the storage[],
     * provided such club.swdev.webapp.model.Resume is not there already.
     */
    public void save(Resume resume) {
        if (resume == null) {
            System.out.println("\nERROR: save(): resume object is null\n");
            return;
        }
        String uuid = resume.getUuid();
        if (uuid == null) {
            System.out.println("\nERROR: save(): resume uuid=null\n");
            return;
        }
        if (uuid.equals("")) {
            System.out.println("\nERROR: save(): resume uuid=\"\"\n");
            return;
        }
        if (size >= MAX_SIZE) {
            System.out.println("\nERROR: save(): storage is full; cannot add new resume\n");
            return;
        }
        if (findIndex(resume.getUuid()) >= 0) {
            System.out.println("\nERROR: update(): resume with uuid="
                    + "\"" + uuid + "\""
                    + " is already in storage\n");
            return;
        }
        storage[size] = resume;
        size++;
    }

    /**
     * Updates a club.swdev.webapp.model.Resume with a given uuid after checking for its presence in storage[].
     */
    public void update(Resume resume) {
        if (resume == null) {
            System.out.println("\nERROR: update(): resume object is null\n");
            return;
        }
        String uuid = resume.getUuid();
        if (uuid == null) {
            System.out.println("\nERROR: update(): resume uuid=null\n");
            return;
        }
        int index = findIndex(resume.getUuid());
        if (index >= 0) {
            storage[index] = resume;
        } else {
            System.out.println("\nERROR: update(): resume with uuid="
                    + "\"" + uuid + "\""
                    + " not found in storage\n");
        }
    }

    /**
     * @return club.swdev.webapp.model.Resume with a given uuid or null if there is no such club.swdev.webapp.model.Resume in storage[].
     */
    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index < 0) {
            System.out.println("\nERROR: get(): resume with uuid="
                    + "\"" + uuid + "\""
                    + " not found in storage\n");
            return null;
        }
        return storage[index];
    }

    /**
     * Removes a club.swdev.webapp.model.Resume with a given uuid while squeezing the rest of Resumes
     * in the storage[] towards the beginning of the storage so that nonnull Resumes
     * remain contiguous.
     */
    public void delete(String uuid) {
        if (size == 0) {
            System.out.println("\nERROR: delete(): resume with uuid="
                    + "\"" + uuid + "\""
                    + " not found in storage\n");
            return;
        }
        int index = findIndex(uuid);
        if (index < 0) {
            System.out.println("\nERROR: delete(): resume with uuid="
                    + "\"" + uuid + "\""
                    + " not found in storage\n");
        } else {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return copyOf(storage, size);
    }

    /**
     * @return int number of contiguous nonnull Resumes of the storage[] array,
     * starting from the beginning of storage[].
     */
    public int size() {
        return size;
    }

    private int findIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
