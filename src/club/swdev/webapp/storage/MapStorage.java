package club.swdev.webapp.storage;

import club.swdev.webapp.model.Resume;
import club.swdev.webapp.model.ResumeComparator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private final Map<String, Resume> map = new HashMap<>();

    public int size() {
        return map.size();
    }

    protected Resume doGet(Object uuid) {
        return map.get((String) uuid);
    }

    public List<Resume> getAllSorted() {
        List<Resume> sortedList = new ArrayList<>(map.values());
        sortedList.sort(new ResumeComparator());
        return sortedList;
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
        return map.containsKey(uuid) ? uuid : null;
    }

    protected boolean isItemLocated(Object itemLocation) {
        return itemLocation != null;
    }
}
