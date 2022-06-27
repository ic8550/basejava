package club.swdev.webapp.storage;

import club.swdev.webapp.exception.StorageException;
import club.swdev.webapp.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.List;

public abstract class AbstractFileStorage extends AbstractStorage<File> {
    private final File storageDir;

    protected AbstractFileStorage(File storageDir) {
        if (storageDir == null) {
            throw new StorageException("Error initializing storage: storage directory is null", null);
        }
        if (!storageDir.isDirectory()) {
            throw new IllegalArgumentException("Error initializing storage: storage directory " + storageDir.getAbsolutePath() + " is not a directory");
        }
        if (!storageDir.canRead() || !storageDir.canWrite()) {
            throw new IllegalArgumentException("Error initializing storage: storage directory " + storageDir.getAbsolutePath() + " is not readable/writable");
        }
        this.storageDir = storageDir;
    }

    protected abstract Resume doRead(File resumeFile) throws IOException;
    protected abstract void doWrite(Resume resume, File resumeFile) throws IOException;

    protected Resume doGet(File resumeFile) {
        try {
            return doRead(resumeFile);
        } catch (IOException e) {
            throw new StorageException("Error reading from file ", resumeFile.getName(), e);
        }
    }

    protected void doSave(Resume resume, File resumeFile) {
        try {
            resumeFile.createNewFile();
            doWrite(resume, resumeFile);
        } catch (IOException e) {
            throw new StorageException("Error trying to create file " + resumeFile.getAbsolutePath(), resumeFile.getName(), e);
        }
    }

    protected void doUpdate(Resume resume, File resumeFile) {
        try {
            doWrite(resume, resumeFile);
        } catch (IOException e) {
            throw new StorageException("Error writing to file " + resumeFile.getAbsolutePath(), resumeFile.getName(), e);
        }
    }

    protected void doDelete(File resumeFile) {
        if (!resumeFile.delete()) {
            throw new StorageException("Error trying to delete file ", resumeFile.getName());
        }
    }

    protected File getItemLocation(String uuid) {
        return new File(storageDir, uuid);
    }

    protected boolean isItemLocated(File resumeFile) {
        return resumeFile.exists();
    }

    protected List<Resume> getList() {
        return null;
    }

    public int size() {
        String[] list = storageDir.list();
        if (list == null) {
            throw new StorageException("Error reading storage directory", null);
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
