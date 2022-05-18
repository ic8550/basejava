package club.swdev.webapp.storage;

import club.swdev.webapp.model.Resume;

import java.util.List;

public interface Storage {

    int size();

    Resume get(String uuid);

    List<Resume> getAllSorted();

    void save(Resume r);

    void update(Resume r);

    void delete(String uuid);

    void clear();
}