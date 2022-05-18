package club.swdev.webapp.storage;

import club.swdev.webapp.exception.ItemAlreadyPresentInStorageException;
import club.swdev.webapp.exception.ItemNotPresentInStorageException;
import club.swdev.webapp.exception.StorageException;
import club.swdev.webapp.model.Resume;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static club.swdev.webapp.storage.AbstractArrayStorage.STORAGE_CAPACITY;
import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractStorageTest {
    private final Storage storage;

    private static final String UUID_1 = "111";
    private static final String NAME_1 = "N-1";
    private static final String UUID_2 = "222";
    private static final String NAME_2 = "N-2";
    private static final String UUID_3 = "333";
    private static final String NAME_3 = "N-3";
    private static final String UUID_4 = "444";
    private static final String NAME_4 = "N-N";

    private static final String UUID_5 = "555";
    private static final String NAME_5 = "N-N";

    private static final String UUID_6 = "666";
    private static final String NAME_6 = "N-N";

    private static final String UUID_7 = "777";
    private static final String NAME_7 = "N-7";

    private static final Resume RESUME_1;
    private static final Resume RESUME_2;
    private static final Resume RESUME_3;
    private static final Resume RESUME_4;

    private static final Resume RESUME_5;
    private static final Resume RESUME_6;
    private static final Resume RESUME_7;

    static {
        RESUME_1 = new Resume(UUID_1, NAME_1);
        RESUME_2 = new Resume(UUID_2, NAME_2);
        RESUME_3 = new Resume(UUID_3, NAME_3);
        RESUME_4 = new Resume(UUID_4, NAME_4);
        RESUME_5 = new Resume(UUID_5, NAME_5);
        RESUME_6 = new Resume(UUID_6, NAME_6);
        RESUME_7 = new Resume(UUID_7, NAME_7);
    }

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    public void setUp() {
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
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
        assertGet(RESUME_4);
        assertGet(RESUME_5);
        assertGet(RESUME_6);
    }

    @Test
    public void getNonexistent() {
        assertThrows(ItemNotPresentInStorageException.class, () -> storage.get("dummy"));
    }

    @Test
    public void getAllSorted() {
        Resume[] resumeArray = {RESUME_1, RESUME_2, RESUME_3, RESUME_4, RESUME_5, RESUME_6};
        List<Resume> expectedList = Arrays.asList(resumeArray);
        assertIterableEquals(expectedList, storage.getAllSorted());
    }

    @Test
    public void save() {
        if (storage instanceof AbstractArrayStorage) {
            storage.clear();
        }
        storage.save(RESUME_7);
        assertGet(RESUME_7);
    }

    @Test
    public void saveExistent() {
        assertThrows(ItemAlreadyPresentInStorageException.class, () -> storage.save(RESUME_1));
    }

    @Test
    public void saveBeyondCapacity() {
        if (storage instanceof AbstractArrayStorage) {
            try {
                for (int i = storage.size(); i < STORAGE_CAPACITY; i++) {
                    storage.save(new Resume());
                }
            } catch (StorageException e) {
                fail("Error: storage overflow within storage capacity");
            }
            assertThrows(StorageException.class, () -> storage.save(new Resume()));
        }
    }

    @Test
    public void update() {
        Resume newResume = new Resume(UUID_1, "N-11");
        storage.update(newResume);
        assertSame(newResume, storage.get(UUID_1));
    }

    @Test
    public void updateNonexistent() {
        assertThrows(ItemNotPresentInStorageException.class, () -> storage.update(new Resume("dummy")));
    }

    @Test
    public void delete() {
        storage.delete(UUID_1);
        assertSize(5);
        assertThrows(ItemNotPresentInStorageException.class, () -> storage.get(UUID_1));
    }

    @Test
    public void deleteNonexistent() {
        assertThrows(ItemNotPresentInStorageException.class, () -> storage.delete("dummy"));
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
    }

    private void assertGet(Resume r) {
        assertEquals(r, storage.get(r.getUuid()));
    }

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }
}

