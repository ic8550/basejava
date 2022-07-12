package club.swdev.webapp.storage;

import club.swdev.webapp.exception.StorageException;
import club.swdev.webapp.model.Resume;
import club.swdev.webapp.storage.serializer.StreamSerializer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileStorage extends AbstractStorage<File> {
    private final File storageDir;
    private final StreamSerializer streamSerializer;

    protected FileStorage(File storageDir, StreamSerializer streamSerializer) {
        Objects.requireNonNull(storageDir, "Error initializing storage: storage directory is null");
        if (!storageDir.isDirectory()) {
            throw new IllegalArgumentException("Error initializing storage: storage directory " + storageDir.getAbsolutePath() + " is not a directory");
        }
        if (!storageDir.canRead() || !storageDir.canWrite()) {
            throw new IllegalArgumentException("Error initializing storage: storage directory " + storageDir.getAbsolutePath() + " is not readable/writable");
        }
        this.storageDir = storageDir;
        this.streamSerializer = streamSerializer;
    }

    protected Resume doGet(File file) {
        try {
            return streamSerializer.doRead(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            throw new StorageException("Error reading from file ", file.getName(), e);
        }
    }

    protected void doSave(Resume resume, File file) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new StorageException("Error trying to create file " + file.getName(), resume.getUuid(), e);
        }
        doUpdate(resume, file);
    }

    protected void doUpdate(Resume resume, File file) {
        try {
            streamSerializer.doWrite(resume, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("Error writing to file " + file.getName(), resume.getUuid(), e);
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
        File[] resumeArray = getFilesList();
        for (File resume : resumeArray) {
            resumes.add(doGet(resume));
        }
        return resumes;
    }

    public int size() {
        return getFilesList().length;
    }

    public void clear() {
        File[] files = getFilesList();
        for (File file : files) {
            doDelete(file);
        }
    }

    private File[] getFilesList() {
        File[] files = storageDir.listFiles();
        if (files == null) {
            throw new NullPointerException("Error getting directory's files list: listFiles() returned null");
        }
        return files;
    }
}
