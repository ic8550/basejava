package club.swdev.webapp.storage;

import club.swdev.webapp.storage.serializer.ObjectStreamSerializer;
import club.swdev.webapp.util.FileSystems;

public class PathStorageTest extends AbstractStorageTest {
    public PathStorageTest() {
        super(new PathStorage(FileSystems.getDirectoryName("../test-file-storage"), new ObjectStreamSerializer()));
    }
}
