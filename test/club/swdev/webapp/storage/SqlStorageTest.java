package club.swdev.webapp.storage;

import club.swdev.webapp.AppConfig;

public class SqlStorageTest extends AbstractStorageTest {

    public SqlStorageTest() {
        super(AppConfig.getAppConfig().getStorage());
    }
}
