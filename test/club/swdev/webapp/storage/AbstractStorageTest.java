package club.swdev.webapp.storage;

import club.swdev.webapp.exception.ItemAlreadyPresentInStorageException;
import club.swdev.webapp.exception.ItemNotPresentInStorageException;
import club.swdev.webapp.exception.StorageException;
import club.swdev.webapp.model.Resume;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractStorageTest {
    private final Storage storage;

    private static final String UUID_1 = "111";
    private static final String UUID_2 = "222";
    private static final String UUID_3 = "333";
    private static final String UUID_4 = "444";

    private static final Resume RESUME_1;
    private static final Resume RESUME_2;
    private static final Resume RESUME_3;
    private static final Resume RESUME_4;

    static {
        RESUME_1 = new Resume(UUID_1);
        RESUME_2 = new Resume(UUID_2);
        RESUME_3 = new Resume(UUID_3);
        RESUME_4 = new Resume(UUID_4);
    }

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void size() {
        assertSize(3);
    }

    @Test
    public void get() {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test
    public void getNoExistent() {
        assertThrows(ItemNotPresentInStorageException.class, () -> storage.get("dummy"));
    }

    @Test
    public void getAll() {
        Resume[] expectedList = {RESUME_1, RESUME_2, RESUME_3};
        assertArrayEquals(expectedList, storage.getAll());
    }

    @Test
    public void save()  {
        storage.save(RESUME_4);
        assertSize(4);
        assertGet(RESUME_4);
    }

    @Test
    public void saveExistent() {
        assertThrows(ItemAlreadyPresentInStorageException.class, () -> storage.save(RESUME_1));
    }

    @Test
    public void saveBeyondCapacity()  {
        if (storage instanceof AbstractArrayStorage) {
            try {
                for (int i = 4; i <= AbstractArrayStorage.STORAGE_CAPACITY; i++) {
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
        Resume newResume = new Resume(UUID_1);
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
        assertSize(2);
        assertThrows(ItemNotPresentInStorageException.class, () -> storage.get(UUID_1));
    }

    @Test
    public void deleteNonExistent() {
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

