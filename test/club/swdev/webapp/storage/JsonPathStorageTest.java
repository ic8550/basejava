package club.swdev.webapp.storage;

import club.swdev.webapp.storage.serializer.JsonStreamSerializer;
import club.swdev.webapp.util.UtilFS;

public class JsonPathStorageTest extends AbstractStorageTest {
    public JsonPathStorageTest() {
        super(new PathStorage(UtilFS.getDirectoryName("../test-json-storage"), new JsonStreamSerializer()));
    }
}
