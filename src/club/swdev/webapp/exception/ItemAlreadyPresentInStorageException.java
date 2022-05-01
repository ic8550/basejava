package club.swdev.webapp.exception;

public class ItemAlreadyPresentInStorageException extends StorageException {
    public ItemAlreadyPresentInStorageException(String uuid) {
        super("Resume " + uuid + " already present in storage", uuid);
    }
}
