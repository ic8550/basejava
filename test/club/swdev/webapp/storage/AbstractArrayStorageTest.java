package club.swdev.webapp.storage;

import club.swdev.webapp.exception.ItemNotPresentInStorageException;
import club.swdev.webapp.exception.ItemAlreadyPresentInStorageException;
import club.swdev.webapp.exception.StorageException;
import club.swdev.webapp.model.Resume;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractArrayStorageTest {
    private Storage storage;

    private static final String UUID_1 = "111";
    private static final String UUID_2 = "222";
    private static final String UUID_3 = "333";
    private static final String UUID_4 = "444";

    private static final Resume RESUME_1 = new Resume(UUID_1);
    private static final Resume RESUME_2 = new Resume(UUID_2);
    private static final Resume RESUME_3 = new Resume(UUID_3);
    private static final Resume RESUME_4 = new Resume(UUID_4);

    protected AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @BeforeEach
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void size() throws Exception {
        assertSize(3);
    }

    @Test
    public void get() {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test()
    public void getNonExistent() throws Exception {
        Exception exception = assertThrows(ItemNotPresentInStorageException.class, () -> storage.get("dummy"));
        assertEquals("Resume dummy not present in storage", exception.getMessage());
    }

    @Test
    public void getAll() throws Exception {
        Resume[] array = storage.getAll();
        assertEquals(3, array.length);
        assertEquals(RESUME_1, array[0]);
        assertEquals(RESUME_2, array[1]);
        assertEquals(RESUME_3, array[2]);
    }

    @Test
    public void save() throws Exception {
        storage.save(RESUME_4);
        assertSize(4);
        assertGet(RESUME_4);
    }

    @Test
    public void saveExistent() throws Exception {
        Exception exception = assertThrows(ItemAlreadyPresentInStorageException.class, () -> storage.save(RESUME_1));
        assertEquals("Resume " + RESUME_1.getUuid() + " already present in storage", exception.getMessage());
    }

    @Test
    public void saveBeyondLimit() throws Exception {
        try {
            for (int i = 4; i < AbstractArrayStorage.STORAGE_CAPACITY; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            fail();
        }
        storage.save(new Resume());
        assertThrows(StorageException.class, () -> storage.save(new Resume()));
    }

    @Test
    void update() throws Exception {
        Resume newResume = new Resume(UUID_1);
        storage.update(newResume);
        assertTrue(newResume == storage.get(UUID_1));
    }

    @Test
    public void delete() throws Exception {
        storage.delete(UUID_1);
        assertSize(2);
        Exception exception = assertThrows(ItemNotPresentInStorageException.class, () -> storage.get(UUID_1));
        assertEquals("Resume " + UUID_1 + " not present in storage", exception.getMessage());
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        assertSize(0);
    }

    private void assertSize(int size) {
        assertEquals(size, storage.size());
    }

    private void assertGet(Resume resume) {
        assertEquals(resume, storage.get(resume.getUuid()));
    }
}