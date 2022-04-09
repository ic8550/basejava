package club.swdev.webapp.exception;

public class ItemPresentInStorageException extends StorageException {
    public ItemPresentInStorageException(String uuid) {
        super("Resume " + uuid + " already present in storage", uuid);
    }
}
