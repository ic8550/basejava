package club.swdev.webapp.storage;

import club.swdev.webapp.exception.ItemAlreadyPresentInStorageException;
import club.swdev.webapp.exception.ItemNotPresentInStorageException;
import club.swdev.webapp.model.Resume;
import club.swdev.webapp.util.Resumes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractStorageTest {
    protected final Storage storage;

    private static final String UUID_1 = "111";
    private static final String NAME_1 = "N-1";

    private static final String UUID_2 = "222";
    private static final String NAME_2 = "N-2";

    private static final String UUID_3 = "333";
    private static final String NAME_3 = "N-3";

    // The next three resumes will all have the same 'fullName' field
    // This is to test sorting by 'uuid' when 'fullName' is the same
    private static final String UUID_4 = "444";
    private static final String NAME_4 = "N-N";

    private static final String UUID_5 = "555";
    private static final String NAME_5 = "N-N";

    private static final String UUID_6 = "666";
    private static final String NAME_6 = "N-N";

    private static final Resume RESUME_1 = Resumes.fillOut(UUID_1, NAME_1);
    private static final Resume RESUME_2 = Resumes.fillOut(UUID_2, NAME_2);
    private static final Resume RESUME_3 = Resumes.fillOut(UUID_3, NAME_3);
    private static final Resume RESUME_4 = Resumes.fillOut(UUID_4, NAME_4);
    private static final Resume RESUME_5 = Resumes.fillOut(UUID_5, NAME_5);
    private static final Resume RESUME_6 = Resumes.fillOut(UUID_6, NAME_6);

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    public void populateStorageForTesting() {
        storage.clear();
        storage.save(RESUME_6);
        storage.save(RESUME_5);
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
        Resume newResume = Resumes.fillOut(UUID_1, "N-11");
        storage.update(newResume);
        assertEquals(newResume, storage.get(UUID_1));
    }

    @Test
    public void updateNonexistent() {
        assertThrows(ItemNotPresentInStorageException.class, () -> storage.update(Resumes.fillOut("foo")));
    }

    @Test
    public void delete() {
        storage.delete(UUID_1);
        assertSize(5);
        assertThrows(ItemNotPresentInStorageException.class, () -> storage.get(UUID_1));
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

    protected void assertGet(Resume r) {
        assertEquals(r, storage.get(r.getUuid()));
    }

    protected void assertSize(int size) {
        assertEquals(size, storage.size());
    }
}

