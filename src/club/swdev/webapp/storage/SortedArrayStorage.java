package club.swdev.webapp.storage;

import club.swdev.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    /**
     * @return an index (a position in the array) of the Resume with a given uuid
     */
    protected int getIndex(String uuid) {
        if (uuid == null) {
            return -1;
        }
        Resume resume = new Resume();
        resume.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, resume);
    }

    protected void insertElement(Resume resume, int index) {
        insertItem(resume, -index - 1);
    }

    protected void deleteElement(int index) {
        System.arraycopy(storage, 0, storage, index, size - index - 1);
    }

    private void insertItem(Resume resume, int index) {
        storage[index] = resume;
        System.arraycopy(storage, 0, storage, index + 1, size - index);
    }
}
