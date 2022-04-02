package club.swdev.webapp.storage;

import club.swdev.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {
    /**
     * Adds a club.swdev.webapp.model.Resume with a given uuid to the storage[],
     * provided such club.swdev.webapp.model.Resume is not there already.
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
     * Updates a club.swdev.webapp.model.Resume with a given uuid after checking for its presence in storage[].
     */
    public void update(Resume resume) {
        if (resume == null) {
            System.out.println("\nERROR: update(): resume object is null\n");
            return;
        }
        String uuid = resume.getUuid();
        if (uuid == null) {
            System.out.println("\nERROR: update(): resume uuid=null\n");
            return;
        }
        int index = getIndex(resume.getUuid());
        if (index >= 0) {
            storage[index] = resume;
        } else {
            System.out.println("\nERROR: update(): resume with uuid="
                    + "\"" + uuid + "\""
                    + " not found in storage\n");
        }
    }

    /**
     * Removes a club.swdev.webapp.model.Resume with a given uuid while squeezing the rest of Resumes
     * in the storage[] towards the beginning of the storage so that nonnull Resumes
     * remain contiguous.
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

    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
