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
        if (index == -1) {
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
    public abstract void save(Resume resume);

    /**
     * Updates a Resume with a given uuid after checking for its presence in storage[].
     */
    public abstract void update(Resume resume);

    /**
     * Removes a Resume with a given uuid while making sure that
     * the remaining nonnull Resumes are still contiguous.
     */
    public abstract void delete(String uuid);

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
}
