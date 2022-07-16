package club.swdev.webapp.storage;

import club.swdev.webapp.storage.serializer.ObjectStreamSerializer;
import club.swdev.webapp.util.FileSystems;

public class ObjectPathStorageTest extends AbstractStorageTest {
    public ObjectPathStorageTest() {
        super(new PathStorage(FileSystems.getDirectoryName("../test-path-storage"), new ObjectStreamSerializer()));
    }
}
