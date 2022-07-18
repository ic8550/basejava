package club.swdev.webapp.storage;

import club.swdev.webapp.exception.ItemAlreadyPresentInStorageException;
import club.swdev.webapp.exception.ItemNotPresentInStorageException;
import club.swdev.webapp.model.Resume;
import club.swdev.webapp.util.UtilResumes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractStorageTest {
    protected static final File STORAGE_DIR = new File("C:\\projects\\basejava\\storage");

    protected final Storage storage;

    private static final Resume RESUME_1 = UtilResumes.fillWithNumber(1);
    private static final Resume RESUME_2 = UtilResumes.fillWithNumber(2);
    private static final Resume RESUME_3 = UtilResumes.fillWithNumber(3);
    private static final Resume RESUME_4 = UtilResumes.fillWithNumber(4);
    private static final Resume RESUME_5 = UtilResumes.fillWithNumber(5);
    private static final Resume RESUME_6 = UtilResumes.fillWithNumber(6);

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    public void populateStorageForTesting() {
        storage.clear();

        /**
         * We put resumes in the storage in an order that is exactly opposite
         * to that of a sorted storage.
         * Besides, in order to test sorting by 'uuid' when 'fullName' is the same,
         * we set the same 'fullName' field ("Name-N Surname-N")
         * to resumes RESUME_4, RESUME_5, and RESUME_6.
         */

        RESUME_6.setFullName("Name-N Surname-N");
        storage.save(RESUME_6);

        RESUME_5.setFullName("Name-N Surname-N");
        storage.save(RESUME_5);

        RESUME_4.setFullName("Name-N Surname-N");
        storage.save(RESUME_4);

        storage.save(RESUME_3);

        storage.save(RESUME_2);

        storage.save(RESUME_1);
    }

    @Test
    public void size() {
        assertSize(6);
    }

    @Test
    public void get() {
        assertGet(RESUME_4); // try getting a random resume out of the six previously saved
    }

    @Test
    public void getNonexistent() {
        assertThrows(ItemNotPresentInStorageException.class, () -> storage.get("foo"));
    }

    @Test
    public void getAllSorted() {
        Resume[] resumeArray = {RESUME_1, RESUME_2, RESUME_3, RESUME_4, RESUME_5, RESUME_6};
        List<Resume> expectedList = Arrays.asList(resumeArray);
        assertIterableEquals(expectedList, storage.getAllSorted());
    }

    @Test
    public void save() {
        clear();
        storage.save(RESUME_1);
        assertSize(1);
        assertGet(RESUME_1);
    }

    @Test
    public void saveExistent() {
        assertThrows(ItemAlreadyPresentInStorageException.class, () -> storage.save(RESUME_1));
    }

    @Test
    public void update() {
        Resume newResume = UtilResumes.fillWithNumber(1);
        newResume.setFullName("Newname-1 Newsurname-1");
        storage.update(newResume);
        assertEquals(newResume, storage.get(newResume.getUuid()));
    }

    @Test
    public void updateNonexistent() {
        assertThrows(ItemNotPresentInStorageException.class, () -> storage.update(UtilResumes.fillOut("foo")));
    }

    @Test
    public void delete() {
        storage.delete(RESUME_1.getUuid());
        assertSize(5);
        assertThrows(ItemNotPresentInStorageException.class, () -> storage.get(RESUME_1.getUuid()));
    }

    @Test
    public void deleteNonexistent() {
        assertThrows(ItemNotPresentInStorageException.class, () -> storage.delete("foo"));
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
    }

    protected void assertGet(Resume resume) {
        assertEquals(resume, storage.get(resume.getUuid()));
    }

    protected void assertSize(int size) {
        assertEquals(size, storage.size());
    }
}

