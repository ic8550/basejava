package club.swdev.webapp;

import club.swdev.webapp.storage.SqlStorage;
import club.swdev.webapp.storage.Storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppConfig {
    // private static final File APP_PROPS_FILE = new File("C:\\Users\\Igor\\Documents\\Topics\\Programming\\Projects\\grigory-kislin\\basejava\\config\\app.properties");
    private static final File APP_PROPS_FILE = new File(getAppConfigFileBaseDir(), "config/app.properties");
    private static final AppConfig APP_CONFIG_INSTANCE = new AppConfig();

    private final File storageDir;
    private final Storage storage;

    public static AppConfig getConfigInstance() {
        return APP_CONFIG_INSTANCE;
    }

    private AppConfig() {
        try (InputStream is = new FileInputStream(APP_PROPS_FILE)) {
            Properties appProps = new Properties();
            appProps.load(is);
            storageDir = new File(appProps.getProperty("storage.dir"));
            storage = new SqlStorage(appProps.getProperty("db.url"), appProps.getProperty("db.user"), appProps.getProperty("db.password"));
        } catch (IOException e) {
            throw new IllegalStateException("Invalid config file " + APP_PROPS_FILE.getAbsolutePath());
        }
    }

    public File getStorageDir() {
        return storageDir;
    }

    public Storage getStorage() {
        return storage;
    }

    private static File getAppConfigFileBaseDir() {
        String appProp = System.getProperty("appConfigFileBaseDir");
        File homeDir = new File(appProp == null ? "." : appProp);
        if (!homeDir.isDirectory()) {
            throw new IllegalStateException(homeDir + " is not directory");
        }
        return homeDir;
    }
}
