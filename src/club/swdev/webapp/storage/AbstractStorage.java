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
        Object itemLocation = getExistentItemLocation(uuid);
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
            throw new StorageException("Error in save(): resume's uuid is empty (uuid=\"\")", "");
        }
        Object itemLocation = getNonexistentItemLocation(resume.getUuid());
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
        Object itemLocation = getExistentItemLocation(resume.getUuid());
        doUpdate(resume, itemLocation);
    }

    public void delete(String uuid) {
        if (uuid == null) {
            throw new StorageException("Error in delete(): resume's uuid is null", null);
        }
        if (uuid.equals("")) {
            throw new StorageException("Error in delete(): resume's uuid is empty (uuid=\"\")", "");
        }
        Object itemLocation = getExistentItemLocation(uuid);
        doDelete(itemLocation);
    }

    private Object getExistentItemLocation(String uuid) {
        Object itemLocation = getItemLocation(uuid);
        if (!isItemLocated(itemLocation)) {
            throw new ItemNotPresentInStorageException(uuid);
        }
        return itemLocation;
    }

    private Object getNonexistentItemLocation(String uuid) {
        Object itemLocation = getItemLocation(uuid);
        if (isItemLocated(itemLocation)) {
            throw new ItemAlreadyPresentInStorageException(uuid);
        }
        return itemLocation;
    }
}