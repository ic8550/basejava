package club.swdev.webapp.storage;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses(
        {
                ArrayStorageTest.class,
                SortedArrayStorageTest.class,
                ListStorageTest.class,
                MapStorageTest.class,
                ObjectFileStorageTest.class,
                ObjectPathStorageTest.class,
                XmlPathStorageTest.class,
                JsonPathStorageTest.class
        }
)

public class StorageTest {
}
