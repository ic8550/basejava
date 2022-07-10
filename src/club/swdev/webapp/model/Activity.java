package club.swdev.webapp.model;

import java.io.Serializable;

public class Activity implements Serializable {
    private static final long serialVersionUID = 1L;
    private Duration duration;
    private String title;
    private String description;

    public Activity(Duration duration, String title, String description) {
        this.duration = duration;
        this.title = title;
        this.description = description;
    }

    public Duration getDuration() {
        return duration;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
