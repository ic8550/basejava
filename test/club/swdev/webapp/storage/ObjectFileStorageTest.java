package club.swdev.webapp.storage;

import club.swdev.webapp.storage.serializer.ObjectStreamSerializer;
import club.swdev.webapp.util.FileSystems;

public class ObjectFileStorageTest extends AbstractStorageTest {
    public ObjectFileStorageTest() {
        super(new FileStorage(FileSystems.getDirectory("../test-file-storage"), new ObjectStreamSerializer()));
    }
}
