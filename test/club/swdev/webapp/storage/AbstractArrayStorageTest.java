package club.swdev.webapp.storage;

import club.swdev.webapp.exception.StorageException;
import club.swdev.webapp.util.UtilResumes;
import org.junit.jupiter.api.Test;

import static club.swdev.webapp.storage.AbstractArrayStorage.STORAGE_CAPACITY;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {
    public AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }

    @Test
    public void saveBeyondCapacity() {
        try {
            for (int i = storage.size(); i < STORAGE_CAPACITY; i++) {
                // storage.save(new Resume("FakeName"));
                String StringValueOfIndex = String.valueOf(i);
                storage.save(UtilResumes.fillOut(StringValueOfIndex, "NAME-" + StringValueOfIndex));
            }
        } catch (StorageException e) {
            fail("Error: storage overflow within storage capacity");
        }
        // assertThrows(StorageException.class, () -> storage.save(new Resume("Name")));
        assertThrows(StorageException.class, () -> storage.save(UtilResumes.fillOut(String.valueOf(STORAGE_CAPACITY), "NAME-" + STORAGE_CAPACITY)));
    }
}
