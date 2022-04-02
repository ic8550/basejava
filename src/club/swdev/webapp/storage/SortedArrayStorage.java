package club.swdev.webapp.storage;

import club.swdev.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    /**
     * Adds a Resume with a given uuid to the storage[],
     * provided such Resume is not there already.
     */
    public void save(Resume resume) {
        if (resume == null) {
            System.out.println("\nERROR: save(): resume object is null\n");
            return;
        }
        String uuid = resume.getUuid();
        if (uuid == null) {
            System.out.println("\nERROR: save(): resume uuid=null\n");
            return;
        }
        if (uuid.equals("")) {
            System.out.println("\nERROR: save(): resume uuid=\"\"\n");
            return;
        }
        if (size >= STORAGE_CAPACITY) {
            System.out.println("\nERROR: save(): storage is full; cannot add new resume\n");
            return;
        }
        if (getIndex(resume.getUuid()) >= 0) {
            System.out.println("\nERROR: update(): resume with uuid="
                    + "\"" + uuid + "\""
                    + " is already in storage\n");
            return;
        }
        storage[size] = resume;
        size++;
    }

    /**
     * Removes a Resume with a given uuid while making sure that
     * the remaining nonnull Resumes are still contiguous.
     */
    public void delete(String uuid) {
        if (size == 0) {
            System.out.println("\nERROR: delete(): resume with uuid="
                    + "\"" + uuid + "\""
                    + " not found in storage\n");
            return;
        }
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("\nERROR: delete(): resume with uuid="
                    + "\"" + uuid + "\""
                    + " not found in storage\n");
        } else {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }

    /**
     * @return an index (a position in the array) of the Resume with a given uuid
     */
    protected int getIndex(String uuid) {
        Resume resume = new Resume();
        resume.setUuid(uuid);
        return Arrays.binarySearch(storage, resume);
    }
}
