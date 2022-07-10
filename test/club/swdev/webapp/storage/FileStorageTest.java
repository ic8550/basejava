package club.swdev.webapp.storage;

import club.swdev.webapp.util.FileSystems;

public class FileStorageTest extends AbstractStorageTest {
    public FileStorageTest() {
        super(new FileStorage(FileSystems.getDirectory("../test-file-storage")));
    }
}
