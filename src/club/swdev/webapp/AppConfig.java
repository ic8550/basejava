package club.swdev.webapp;

import club.swdev.webapp.storage.SqlStorage;
import club.swdev.webapp.storage.Storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppConfig {
    private static final File APP_PROPS_FILE = new File("config/app.properties");
    private static final AppConfig APP_CONFIG = new AppConfig();

    private final File storageDir;
    private final String dbUrl;
    private final String dbUser;
    private final String dbPassword;
    private Storage storage;

    public static AppConfig getAppConfig() {
        return APP_CONFIG;
    }

    private AppConfig() {
        try (InputStream streamFromAppPropsFile = new FileInputStream(APP_PROPS_FILE)) {
            Properties appProps = new Properties();
            appProps.load(streamFromAppPropsFile);
            dbUrl = appProps.getProperty("db.url");
            dbUser = appProps.getProperty("db.user");
            dbPassword = appProps.getProperty("db.password");
            storageDir = new File(appProps.getProperty("storage.dir"));
            storage = new SqlStorage(dbUrl, dbUser, dbPassword);
        } catch (IOException e) {
            throw new IllegalStateException("Invalid config file " + APP_PROPS_FILE.getAbsolutePath());
        }
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public String getDbUser() {
        return dbUser;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public File getStorageDir() {
        return storageDir;
    }

    public Storage getStorage() {
        return storage;
    }
}
