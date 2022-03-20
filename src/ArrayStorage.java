/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final int RESUME_MAX_QTY = 10000;
    Resume[] storage = new Resume[RESUME_MAX_QTY];

    void clear() {
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
        return new Resume[0];
    }

    int size() {
        return 0;
    }
}
