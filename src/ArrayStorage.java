/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    /**
     * final int MAX_SIZE - storage parameter, the maximum capacity of the storage
     */
    private final int MAX_SIZE = 3;

    Resume[] storage = new Resume[MAX_SIZE];

    /**
     * Size of nonempty (nonnull) part of storage[] -- Number of contiguous
     * nonnull Resumes in the storage[] array, starting from the beginning
     * of storage[].
     */
    int size = 0;

    /**
     * Replaces all nonnull Resumes in storage[] with the null value.
     */
    void clear() {
        int i = 0;
        while (i < size) {
            storage[i] = null;
            i++;
        }
        size = 0;
    }

    /**
     * Adds a Resume with a given uuid to the storage[],
     * provided such Resume is not there already.
     */
    void save(Resume resume) {
        if (size >= MAX_SIZE) {
            return;
        }
        if (get(resume.toString()) == null) {
            storage[size] = resume;
            size++;
        }
    }

    /**
     * @return Resume with a given uuid or null if there is no such Resume in storage[].
     */
    Resume get(String uuid) {
        if (uuid != null) {
            int i = 0;
            while (i < size) {
                if (storage[i].toString().equals(uuid)) {
                    return storage[i];
                }
                i++;
            }
        }
        return null;
    }

    /**
     * Removes a Resume with a given uuid while squeezing the rest of Resumes
     * in the storage[] towards the beginning of the storage so that nonnull Resumes
     * remain contiguous.
     */
    void delete(String uuid) {
        if (uuid == null) {
            return;
        }
        if (size == 0) {
            return;
        }
        int i = 0;
        while (i < size) {
            if (storage[i].toString().equals(uuid)) {
                int j = i;
                while (j < size - 1) {
                    storage[j] = storage[j + 1];
                    j++;
                }
                storage[size - 1] = null;
                size--;
                return;
            }
            i++;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] all = new Resume[size];
        int i = 0;
        while (i < size) {
            all[i] = storage[i];
            i++;
        }
        return all;
    }

    /**
     * @return int number of contiguous nonnull Resumes of the storage[] array,
     * starting from the beginning of storage[].
     */
    int size() {
        return size;
    }
}
