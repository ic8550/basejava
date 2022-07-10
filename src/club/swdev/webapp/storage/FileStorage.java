package club.swdev.webapp.storage;

import club.swdev.webapp.model.Resume;
import club.swdev.webapp.storage.serializer.ObjectStreamSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileStorage extends AbstractFileStorage {
    protected FileStorage(File storageDir) {
        super(storageDir);
    }

    protected Resume doRead(File file) throws IOException {
        ObjectStreamSerializer oss = new ObjectStreamSerializer();
        return oss.doRead(new FileInputStream(file));
    }

    protected void doWrite(Resume resume, File file) throws IOException {
        ObjectStreamSerializer oss = new ObjectStreamSerializer();
        oss.doWrite(resume, new FileOutputStream(file));
    }
}
