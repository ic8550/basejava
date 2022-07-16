package club.swdev.webapp.storage;

import club.swdev.webapp.storage.serializer.XmlStreamSerializer;
import club.swdev.webapp.util.FileSystems;

public class XmlPathStorageTest extends AbstractStorageTest {
    public XmlPathStorageTest() {
        super(new PathStorage(FileSystems.getDirectoryName("../test-xml-storage"), new XmlStreamSerializer()));
    }
}
