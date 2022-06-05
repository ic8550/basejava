package club.swdev.webapp.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;

/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume> {

    // Unique identifier
    private final String uuid;
    private String fullName;
    private Map<String, String> contacts;
    private ArrayList<String> objectives;
    private ArrayList<String> personal;
    private ArrayList<String> achievements;
    private ArrayList<String> skills;
    private ArrayList<Activity> employments;
    private ArrayList<Activity> education;

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

    public Map<String, String> getContacts() {
        return contacts;
    }

    public void setContacts(Map<String, String> contacts) {
        this.contacts = contacts;
    }

    public ArrayList<String> getObjectives() {
        return objectives;
    }

    public void setObjectives(ArrayList<String> objectives) {
        this.objectives = objectives;
    }
    public void setObjectives(String[] objectives) {
        this.objectives = new ArrayList<>(Arrays.asList(objectives));
    }

    public ArrayList<String> getPersonal() {
        return personal;
    }

    public void setPersonal(ArrayList<String> personal) {
        this.personal = personal;
    }
    public void setPersonal(String[] personal) {
        this.personal = new ArrayList<>(Arrays.asList(personal));
    }

    public ArrayList<String> getAchievements() {
        return achievements;
    }

    public void setAchievements(ArrayList<String> achievements) {
        this.achievements = achievements;
    }
    public void setAchievements(String[] achievements) {
        this.achievements = new ArrayList<>(Arrays.asList(achievements));
    }

    public ArrayList<String> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<String> skills) {
        this.skills = skills;
    }
    public void setSkills(String[] skills) {
        this.skills = new ArrayList<>(Arrays.asList(skills));
    }

    public ArrayList<Activity> getEmployments() {
        return employments;
    }

    public void setEmployments(ArrayList<Activity> employments) {
        this.employments = employments;
    }
    public void setEmployments(Activity[] employments) {
        this.employments = new ArrayList<>(Arrays.asList(employments));
    }

    public ArrayList<Activity> getEducation() {
        return education;
    }

    public void setEducation(ArrayList<Activity> education) {
        this.education = education;
    }
    public void setEducation(Activity[] education) {
        this.education = new ArrayList<>(Arrays.asList(education));
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
