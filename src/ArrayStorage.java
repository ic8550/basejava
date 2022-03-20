/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final int RESUME_MAX_QTY = 10000;
    Resume[] storage = new Resume[RESUME_MAX_QTY];

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

    void save(Resume r) {
    }

    Resume get(String uuid) {
        return null;
    }

    void delete(String uuid) {
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
        while (i < RESUME_MAX_QTY) {
            if (storage[i] != null) {
                i++;
            } else {
                break;
            }
        }
        return i;
    }
}
