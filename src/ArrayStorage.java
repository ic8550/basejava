/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    /**
     * final int MAX_SIZE - storage parameter, the maximum capacity of the storage
     */
    private final int MAX_SIZE = 10000;

    Resume[] storage = new Resume[MAX_SIZE];

    /**
     * Replaces all nonnull Resumes in storage[] with the null value.
     */
    void clear() {
        int arrSize = size();
        int i = 0;
        while (i < arrSize) {
            storage[i] = null;
            i++;
        }
    }

    /**
     * Adds a Resume with a given uuid to the storage[],
     * provided such Resume is not there already.
     */
    void save(Resume resume) {
        int arrSize = size();
        if (arrSize >= MAX_SIZE) {
            return;
        }
        if (get(resume.toString()) == null) {
            storage[arrSize] = resume;
        }
    }

    /**
     * @return Resume with a given uuid or null if there is no such Resume in storage[].
     */
    Resume get(String uuid) {
        if (uuid != null) {
            int i = 0;
            int arrSize = size();
            while (i < arrSize) {
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
        int arrSize = size();
        if (arrSize == 0) {
            return;
        }
        int i = 0;
        while (i < arrSize) {
            if (storage[i].toString().equals(uuid)) {
                int j = i;
                while (j < arrSize - 1) {
                    storage[j] = storage[j + 1];
                    j++;
                }
                storage[arrSize - 1] = null;
                return;
            }
            i++;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] all = new Resume[size()];
        int arrSize = size();
        int i = 0;
        while (i < arrSize) {
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
        int i = 0;
        while (i < MAX_SIZE) {
            if (storage[i] != null) {
                i++;
            } else {
                break;
            }
        }
        return i;
    }
}
