package club.swdev.webapp.storage;

import club.swdev.webapp.exception.StorageException;
import club.swdev.webapp.model.Resume;
import club.swdev.webapp.storage.serializer.ObjectStreamSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileStorage extends AbstractStorage<File> {
    private final File storageDir;

    protected FileStorage(File storageDir) {
        if (storageDir == null) {
            throw new StorageException("Error initializing storage: storage directory is null");
        }
        if (!storageDir.isDirectory()) {
            throw new IllegalArgumentException("Error initializing storage: storage directory " + storageDir.getAbsolutePath() + " is not a directory");
        }
        if (!storageDir.canRead() || !storageDir.canWrite()) {
            throw new IllegalArgumentException("Error initializing storage: storage directory " + storageDir.getAbsolutePath() + " is not readable/writable");
        }
        this.storageDir = storageDir;
    }

    protected Resume doRead(File file) throws IOException {
        ObjectStreamSerializer objectStreamSerializer = new ObjectStreamSerializer();
        return objectStreamSerializer.doRead(new FileInputStream(file));
    }

    protected void doWrite(Resume resume, File file) throws IOException {
        ObjectStreamSerializer objectStreamSerializer = new ObjectStreamSerializer();
        objectStreamSerializer.doWrite(resume, new FileOutputStream(file));
    }

    protected Resume doGet(File file) {
        try {
            return doRead(file);
        } catch (IOException e) {
            throw new StorageException("Error reading from file ", file.getName(), e);
        }
    }

    protected void doSave(Resume resume, File file) {
        try {
            if (file.createNewFile()) {
                doUpdate(resume, file);
            } else {
                System.out.println("Error trying to create file " + file.getAbsolutePath() + ": file already exists.");
            }
        } catch (IOException e) {
            throw new StorageException("Error trying to create file " + file.getAbsolutePath(), file.getName(), e);
        }
    }

    protected void doUpdate(Resume resume, File file) {
        try {
            doWrite(resume, file);
        } catch (IOException e) {
            throw new StorageException("Error writing to file " + file.getAbsolutePath(), file.getName(), e);
        }
    }

    protected void doDelete(File file) {
        if (!file.delete()) {
            throw new StorageException("Error trying to delete file ", file.getName());
        }
    }

    protected File getItemLocation(String uuid) {
        return new File(storageDir, uuid);
    }

    protected boolean isItemLocated(File file) {
        return file.exists();
    }

    protected List<Resume> doCopyAll() {
        List<Resume> resumes = new ArrayList<>();
        File[] resumeArray = storageDir.listFiles();
        if (resumeArray == null) {
            throw new NullPointerException("Error getting directory's file list: listFiles() returned null");
        }
        for (File resume : resumeArray) {
            resumes.add(doGet(resume));
        }
        return resumes;
    }

    public int size() {
        String[] list = storageDir.list();
        if (list == null) {
            throw new StorageException("Error reading storage directory");
        }
        return list.length;
    }

    public void clear() {
        File[] files = storageDir.listFiles();
        if (files != null) {
            for (File file : files) {
                doDelete(file);
            }
        }
    }
}
