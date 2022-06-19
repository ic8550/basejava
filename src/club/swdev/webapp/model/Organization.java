package club.swdev.webapp.model;

import java.util.Arrays;
import java.util.List;

public class Organization {
    private String name;
    private String url;
    List<Period> periods;

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

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Period> getPeriods() {
        return periods;
    }

    public void setPeriods(List<Period> periods) {
        this.periods = periods;
    }
}
