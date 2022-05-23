package club.swdev.webapp.model;

import java.util.UUID;

/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume> {

    // Unique identifier
    private final String uuid;

    private String fullName;

    public Resume(String fullName) {
        this(UUID.randomUUID().toString(),fullName);
    }

    public Resume(String uuid, String fullName) {
        if (uuid == null) {
            throw new RuntimeException("uuid cannot be 'null'");
        }
        if (uuid.equals("")) {
            throw new RuntimeException("uuid cannot be an empty string");
        }
        if (fullName == null) {
            throw new RuntimeException("fullName cannot be 'null'");
        }
        if (fullName.equals("")) {
            throw new RuntimeException("fullName cannot be an empty string");
        }
        this.uuid = uuid;
        this.fullName = fullName;
    }

    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "full name: " + fullName + "    (uuid: " + uuid + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Resume resume = (Resume) obj;
        return uuid.equals(resume.uuid);
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    @Override
    public int compareTo(Resume obj) {
        return uuid.compareTo(obj.uuid);
    }
}
