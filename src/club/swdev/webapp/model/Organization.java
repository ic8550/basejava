package club.swdev.webapp.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Organization {
    private String name;
    private final String url;
    private final List<Activity> activities;

    public Organization(String name, String url, List<Activity> activities) {
        if (name == null || name.isBlank()) {
            throw new RuntimeException("Error creating Organization: name cannot be null or empty");
        }
        this.name = name;
        this.url = url == null ? "" : url;
        this.activities = activities == null ? new ArrayList<>() : activities;
    }

    public Organization(String name, String url, Activity[] activities) {
        if (name == null || name.isBlank()) {
            throw new RuntimeException("Error creating Organization: name cannot be null or empty");
        }
        this.name = name;
        this.url = url == null ? "" : url;
        this.activities = activities == null ? new ArrayList<>() : Arrays.asList(activities);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public List<Activity> getActivities() {
        return activities;
    }
}
