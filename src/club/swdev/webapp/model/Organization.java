package club.swdev.webapp.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Organization {
    private String name;
    private final String url;
    private final List<Period> periods;

    public Organization() {
        this.name = "";
        this.url = null;
        this.periods = new ArrayList<>();
    }

    public Organization(String name, String url, List<Period> periods) {
        this.name = name;
        this.url = url;
        this.periods = periods;
    }

    public Organization(String name, String url, Period[] periods) {
        this.name = name;
        this.url = url;
        this.periods = Arrays.asList(periods);
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

    public List<Period> getPeriods() {
        return periods;
    }
}
