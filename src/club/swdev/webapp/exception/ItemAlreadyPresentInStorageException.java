package club.swdev.webapp.exception;

public class ItemAlreadyPresentInStorageException extends StorageException {
    public ItemAlreadyPresentInStorageException(String uuid) {
        super("Resume with uuid=\\\"\" + uuid + \"\\\" already present in storage", uuid);
    }
}
