package club.swdev.webapp.storage;

import club.swdev.webapp.storage.serializer.JsonStreamSerializer;

public class JsonPathStorageTest extends AbstractStorageTest {
    public JsonPathStorageTest() {
        // super(new PathStorage(UtilFS.getDirectoryName("../test-json-storage"), new JsonStreamSerializer()));
        super(new PathStorage(AbstractStorageTest.STORAGE_DIR.getAbsolutePath(), new JsonStreamSerializer()));
    }
}
