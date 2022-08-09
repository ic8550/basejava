package club.swdev.webapp.storage;

import club.swdev.webapp.storage.serializer.ObjectStreamSerializer;

public class ObjectFileStorageTest extends AbstractStorageTest {
    public ObjectFileStorageTest() {
        // super(new FileStorage(UtilFS.getDirectory("../test-file-storage"), new ObjectStreamSerializer()));
        super(new FileStorage(AbstractStorageTest.STORAGE_DIR, new ObjectStreamSerializer()));
    }
}
