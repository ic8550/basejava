package club.swdev.webapp.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Organization {
    private String name;
    private final String url;
    private final List<Activity> activities;

    public Organization() {
        this.name = "";
        this.url = null;
        this.activities = new ArrayList<>();
    }

    public Organization(String name, String url, List<Activity> activities) {
        this.name = name;
        this.url = url;
        this.activities = activities;
    }

    public Organization(String name, String url, Activity[] activities) {
        this.name = name;
        this.url = url;
        this.activities = Arrays.asList(activities);
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
