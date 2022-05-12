package club.swdev.webapp.storage;

import club.swdev.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private final Map<String, Resume> map = new HashMap<>();

    public int size() {
        return map.size();
    }

    protected Resume doGet(Object uuid) {
        return map.get((String) uuid);
    }

    public Resume[] getAll() {
        Object[] objArray = map.values().toArray(new Resume[size()]);
        return (Resume[]) objArray;
    }

    protected void doSave(Resume resume, Object uuid) {
        map.put(resume.getUuid(), resume);
    }

    protected void doUpdate(Resume resume, Object uuid) {
        map.put((String) uuid, resume);
    }

    protected void doDelete(Object uuid) {
        map.remove((String) uuid);
    }

    public void clear() {
        map.clear();
    }

    protected String getItemLocation(String uuid) {
        if (map.containsKey(uuid)) {
            return uuid;
        }
        return null;
    }

    protected boolean isItemLocated(Object itemLocation) {
        return itemLocation != null;
    }
}
