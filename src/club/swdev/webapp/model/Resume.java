package club.swdev.webapp.model;

import java.util.*;

/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume> {

    // Unique identifier
    private final String uuid;
    private String fullName;

    private EnumMap<ContactType, String> contacts;

    private EnumMap<SectionType, AbstractSection> sections;

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

    public EnumMap<ContactType, String> getContacts() {
        return contacts;
    }

    public void setContacts(EnumMap<ContactType, String> contacts) {
        this.contacts = contacts;
    }

    public EnumMap<SectionType, AbstractSection> getSections() {
        return sections;
    }

    public void setSections(EnumMap<SectionType, AbstractSection> sections) {
        this.sections = sections;
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
