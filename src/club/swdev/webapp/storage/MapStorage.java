package club.swdev.webapp.storage;

import club.swdev.webapp.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapStorage extends AbstractStorage<String> {
    private final Map<String, Resume> map = new HashMap<>();

    public int size() {
        return map.size();
    }

    protected Resume doGet(String uuid) {
        return map.get(uuid);
    }

    protected List<Resume> doCopyAll() {
        return new ArrayList<>(map.values());
    }

    protected void doSave(Resume resume, String uuid) {
        map.put(resume.getUuid(), resume);
    }

    protected void doUpdate(Resume resume, String uuid) {
        map.put(uuid, resume);
    }

    protected void doDelete(String uuid) {
        map.remove(uuid);
    }

    public void clear() {
        map.clear();
    }

    protected String getItemLocation(String uuid) {
        return map.containsKey(uuid) ? uuid : null;
    }

    protected boolean isItemLocated(String itemLocation) {
        return itemLocation != null;
    }
}
