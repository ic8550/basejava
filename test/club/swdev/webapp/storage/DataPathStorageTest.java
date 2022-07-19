package club.swdev.webapp.storage;

import club.swdev.webapp.storage.serializer.DataStreamSerializer;
import club.swdev.webapp.util.UtilFS;

public class DataPathStorageTest extends AbstractStorageTest {

    public DataPathStorageTest() {
        super(new PathStorage(UtilFS.getDirectoryName("../test-data-storage"), new DataStreamSerializer()));
    }
}
