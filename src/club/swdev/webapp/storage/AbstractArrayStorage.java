package club.swdev.webapp.storage;

import club.swdev.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    /**
     * final int STORAGE_CAPACITY - storage characteristic, the maximum capacity of the storage
     */
    protected static final int STORAGE_CAPACITY = 3;

    /**
     * Array based storage for Resumes
     */
    protected Resume[] storage = new Resume[STORAGE_CAPACITY];

    /**
     * Size of nonempty (nonnull) part of storage[] -- Number of contiguous
     * nonnull Resumes in the storage[] array, starting from the beginning
     * of storage[].
     */
    protected int size = 0;

    /**
     * @return int number of contiguous nonnull Resumes of the storage[] array,
     * starting from the beginning of the storage[].
     */
    public int size() {
        return size;
    }

    /**
     * @return a Resume object with a given uuid or null if there is no such Resume in storage[].
     */
    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Resume " + uuid + " not found");
            return null;
        }
        return storage[index];
    }

    /**
     * @return an array, containing all nonnull/nonempty Resumes in storage[]
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    /**
     * Adds a Resume with a given uuid to the storage[],
     * provided such Resume is not there already.
     */
    public void save(Resume resume) {
        Resume res = new Resume();
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
        if (size >= STORAGE_CAPACITY) {
            System.out.println("\nERROR: save(): storage is full; cannot add new resume\n");
            return;
        }
        int index = getIndex(uuid);
        if (index >= 0) {
            System.out.println("\nERROR: update(): resume with uuid="
                    + "\"" + uuid + "\""
                    + " is already in storage\n");
            return;
        }
        insertElement(resume, -index - 1);
        size++;
    }

    /**
     * Updates a Resume with a given uuid after checking for its presence in storage[].
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
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            storage[index] = resume;
        } else {
            System.out.println("\nERROR: update(): resume with uuid="
                    + "\"" + uuid + "\""
                    + " not found in storage\n");
        }
    }

    /**
     * Removes a Resume with a given uuid while making sure that
     * the remaining nonnull Resumes are still contiguous.
     */
    public void delete(String uuid) {
        if (size == 0) {
            System.out.println("\nERROR: delete(): resume with uuid="
                    + "\"" + uuid + "\""
                    + " not found in storage\n");
            return;
        }
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("\nERROR: delete(): resume with uuid="
                    + "\"" + uuid + "\""
                    + " not found in storage\n");
            return;
        }
        deleteElement(index);
        size--;
    }

    /**
     * Removes all Resumes from storage[] by replacing all nonnull Resumes in storage[]
     * with the null value.
     */
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    /**
     * @return an index (a position in the array) of the Resume with a given uuid
     */
    protected abstract int getIndex(String uuid);

    protected abstract void insertElement(Resume resume, int index);

    protected abstract void deleteElement(int index);
}
