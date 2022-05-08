package club.swdev.webapp.storage;

import club.swdev.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {
    /**
     * @return a "location" (an index, a position in the array) of the Resume with a given uuid
     */
    protected Integer getItemLocation(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    protected void insertElement(Resume resume, int index) {
        storage[size] = resume;
    }

    protected void deleteElement(int index) {
        storage[index] = storage[size - 1];
    }
}
