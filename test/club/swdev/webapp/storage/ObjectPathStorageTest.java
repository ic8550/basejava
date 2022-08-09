package club.swdev.webapp.storage;

import club.swdev.webapp.storage.serializer.ObjectStreamSerializer;

public class ObjectPathStorageTest extends AbstractStorageTest {
    public ObjectPathStorageTest() {
        // super(new PathStorage(UtilFS.getDirectoryName("../test-path-storage"), new ObjectStreamSerializer()));
        super(new PathStorage(AbstractStorageTest.STORAGE_DIR.getAbsolutePath(), new ObjectStreamSerializer()));
    }
}
