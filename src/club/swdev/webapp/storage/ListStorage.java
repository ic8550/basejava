package club.swdev.webapp.storage;

import club.swdev.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {
    private final List<Resume> list = new ArrayList<>();

    public int size() {
        return list.size();
    }

    protected Resume doGet(Integer itemLocation) {
        return list.get(itemLocation);
    }

    public List<Resume> getList() {
        return list;
    }

    protected void doSave(Resume resume, Integer itemLocation) {
        list.add(resume);
    }

    protected void doUpdate(Resume resume, Integer itemLocation) {
        list.set(itemLocation, resume);
    }

    protected void doDelete(Integer itemLocation) {
        list.remove(itemLocation.intValue());
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

    protected boolean isItemLocated(Integer itemLocation) {
        return itemLocation != null;
    }
}
