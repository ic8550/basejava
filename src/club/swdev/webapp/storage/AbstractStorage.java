package club.swdev.webapp.storage;

import club.swdev.webapp.exception.ItemAlreadyPresentInStorageException;
import club.swdev.webapp.exception.ItemNotPresentInStorageException;
import club.swdev.webapp.exception.StorageException;
import club.swdev.webapp.model.Resume;

import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage<Location> implements Storage {

    protected abstract Resume doGet(Location itemLocation);

    protected abstract void doSave(Resume resume, Location itemLocation);

    protected abstract void doUpdate(Resume resume, Location itemLocation);

    protected abstract void doDelete(Location itemLocation);

    protected abstract Location getItemLocation(String uuid);

    protected abstract boolean isItemLocated(Location itemLocation);

    protected abstract List<Resume> getList();

    public Resume get(String uuid) {
        Location itemLocation = getExistentItemLocation(uuid);
        return doGet(itemLocation);
    }

    public List<Resume> getAllSorted() {
        List<Resume> list = getList();
        list.sort(Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid));
        return list;
    }

    public void save(Resume resume) {
        if (resume == null) {
            throw new StorageException("Error in save(): resume object is null", null);
        }
        Location itemLocation = getNonexistentItemLocation(resume.getUuid());
        doSave(resume, itemLocation);
    }

    public void update(Resume resume) {
        if (resume == null) {
            throw new StorageException("Error in update(): resume object is null", null);
        }
        Location itemLocation = getExistentItemLocation(resume.getUuid());
        doUpdate(resume, itemLocation);
    }

    public void delete(String uuid) {
        if (uuid == null) {
            throw new StorageException("Error in delete(): resume's uuid is null", null);
        }
        if (uuid.equals("")) {
            throw new StorageException("Error in delete(): resume's uuid is empty (uuid=\"\")", "");
        }
        Location itemLocation = getExistentItemLocation(uuid);
        doDelete(itemLocation);
    }

    private Location getExistentItemLocation(String uuid) {
        Location itemLocation = getItemLocation(uuid);
        if (!isItemLocated(itemLocation)) {
            throw new ItemNotPresentInStorageException(uuid);
        }
        return itemLocation;
    }

    private Location getNonexistentItemLocation(String uuid) {
        Location itemLocation = getItemLocation(uuid);
        if (isItemLocated(itemLocation)) {
            throw new ItemAlreadyPresentInStorageException(uuid);
        }
        return itemLocation;
    }
}