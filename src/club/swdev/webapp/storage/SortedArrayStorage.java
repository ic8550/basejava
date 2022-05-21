package club.swdev.webapp.storage;

import club.swdev.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    /**
     * @return a "location", (an index, a position in the array) of the Resume with a given uuid
     */
    protected Integer getItemLocation(String uuid) {
        if (uuid == null) {
            return -1;
        }
        Resume resume = new Resume(uuid, "Name");
        return Arrays.binarySearch(storage, 0, size, resume);
    }

    protected void insertElement(Resume resume, int index) {
        int insertionPoint = -index - 1;
        System.arraycopy(storage, insertionPoint, storage, insertionPoint + 1, size - insertionPoint);
        storage[insertionPoint] = resume;
    }

    protected void deleteElement(int index) {
        System.arraycopy(storage, index + 1, storage, index, size - index - 1);
    }
}
