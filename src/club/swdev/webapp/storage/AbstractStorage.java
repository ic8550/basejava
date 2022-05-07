package club.swdev.webapp.storage;

import club.swdev.webapp.exception.ItemAlreadyPresentInStorageException;
import club.swdev.webapp.exception.ItemNotPresentInStorageException;
import club.swdev.webapp.exception.StorageException;
import club.swdev.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract Resume doGet(Object itemLocation);

    protected abstract void doSave(Resume resume, Object itemLocation);

    protected abstract void doUpdate(Resume resume, Object itemLocation);

    protected abstract void doDelete(Object itemLocation);

    protected abstract Object getItemLocation(String uuid);

    protected abstract boolean isItemLocated(Object itemLocation);

    public Resume get(String uuid) {
        Object itemLocation = getExistingItemLocation(uuid);
        return doGet(itemLocation);
    }

    public void save(Resume resume) {
        if (resume == null) {
            throw new StorageException("Error in save(): resume object is null", null);
        }
        if (resume.getUuid() == null) {
            throw new StorageException("Error in save(): resume's uuid is null", null);
        }
        if (resume.getUuid().equals("")) {
            throw new StorageException("Error in save(): resume uuid is empty (uuid=\"\")", "");
        }
        Object itemLocation = getMissingItemLocation(resume.getUuid());
        doSave(resume, itemLocation);
    }

    public void update(Resume resume) {
        if (resume == null) {
            throw new StorageException("Error in update(): resume object is null", null);
        }
        if (resume.getUuid() == null) {
            throw new StorageException("Error in update(): resume's uuid is null", null);
        }
        if (resume.getUuid().equals("")) {
            throw new StorageException("Error in update(): resume's uuid is empty (uuid=\"\")", "");
        }
        Object itemLocation = getExistingItemLocation(resume.getUuid());
        doUpdate(resume, itemLocation);
    }

    public void delete(String uuid) {
        if (uuid == null) {
            throw new StorageException("Error in delete(): resume's uuid is null", null);
        }
        if (uuid.equals("")) {
            throw new StorageException("Error in delete(): resume's uuid is empty (uuid=\"\")", "");
        }
        Object itemLocation = getExistingItemLocation(uuid);
        doDelete(itemLocation);
    }

    private Object getExistingItemLocation(String uuid) {
        Object itemLocation = getItemLocation(uuid);
        if (!isItemLocated(itemLocation)) {
            throw new ItemNotPresentInStorageException(uuid);
        }
        return itemLocation;
    }

    private Object getMissingItemLocation(String uuid) {
        Object itemLocation = getItemLocation(uuid);
        if (isItemLocated(itemLocation)) {
            throw new ItemAlreadyPresentInStorageException(uuid);
        }
        return itemLocation;
    }
}