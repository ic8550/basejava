package club.swdev.webapp.storage;

import club.swdev.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private final List<Resume> list = new ArrayList<>();

    public int size() {
        return list.size();
    }

    protected Resume doGet(Object itemLocation) {
        return list.get((Integer) itemLocation);
    }

    public List<Resume> getList() {
        return list;
    }

    protected void doSave(Resume resume, Object itemLocation) {
        list.add(resume);
    }

    protected void doUpdate(Resume resume, Object itemLocation) {
        list.set((Integer) itemLocation, resume);
    }

    protected void doDelete(Object itemLocation) {
        list.remove(((Integer) itemLocation).intValue());
    }

    public void clear() {
        list.clear();
    }

    protected Integer getItemLocation(String uuid) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }

    protected boolean isItemLocated(Object itemLocation) {
        return itemLocation != null;
    }
}
