package club.swdev.webapp.storage;

import club.swdev.webapp.exception.StorageException;
import club.swdev.webapp.model.Resume;
import club.swdev.webapp.model.ResumeComparator;

import java.util.Arrays;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
    /**
     * final int STORAGE_CAPACITY - storage characteristic, the maximum capacity of the storage
     */
    protected static final int STORAGE_CAPACITY = 6;

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
    public Resume doGet(Object index) {
        return storage[(Integer) index];
    }

    /**
     * @return an array, containing all nonnull/nonempty Resumes in storage[]
     */
    public List<Resume> getAllSorted() {
        Resume[] resumesArray = Arrays.copyOf(storage, size);
        List<Resume> resumesList = Arrays.asList(resumesArray);
        resumesList.sort(new ResumeComparator());
        return resumesList;
    }

    /**
     * Adds a Resume with a given uuid to the storage[],
     * provided such Resume is not there already.
     */
    public void doSave(Resume resume, Object index) {
        if (size >= STORAGE_CAPACITY) {
            throw new StorageException("Storage overflow", resume.getUuid());
        } else {
            insertElement(resume, (Integer) index);
            size++;
        }
    }

    /**
     * Updates a Resume with a given uuid after checking for its presence in storage[].
     */
    public void doUpdate(Resume resume, Object index) {
        storage[(Integer) index] = resume;
    }

    /**
     * Removes a Resume with a given uuid while making sure that
     * the remaining nonnull Resumes are still contiguous.
     */
    public void doDelete(Object index) {
        deleteElement((Integer) index);
        storage[size - 1] = null;
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

    protected boolean isItemLocated(Object index) {
        return (Integer) index >= 0;
    }

    /**
     * @return a "location" (a position in the array) of the Resume with a given uuid
     */
    protected abstract Integer getItemLocation(String uuid);

    protected abstract void insertElement(Resume resume, int index);

    protected abstract void deleteElement(int index);
}
