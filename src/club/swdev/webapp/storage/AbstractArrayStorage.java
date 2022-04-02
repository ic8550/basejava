package club.swdev.webapp.storage;

import club.swdev.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_CAPACITY = 3;
    protected Resume[] storage = new Resume[STORAGE_CAPACITY];
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

    public abstract void save(Resume resume);

    public abstract void update(Resume resume);

    public abstract void delete(String uuid);

    /**
     * Replaces all nonnull Resumes in storage[] with the null value.
     */
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    protected abstract int getIndex(String uuid);
}
