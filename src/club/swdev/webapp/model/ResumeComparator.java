package club.swdev.webapp.model;

import java.util.Comparator;

public class ResumeComparator implements Comparator<Resume> {
    @Override
    public int compare(Resume r1, Resume r2) {
        int result = r1.getFullName().compareTo(r2.getFullName());
        if (result != 0) {
            return result;
        }
        return r1.getUuid().compareTo(r2.getUuid());
    }
}
