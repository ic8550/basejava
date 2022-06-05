package club.swdev.webapp.model;

import java.util.ArrayList;
import java.util.Arrays;

public class Activity {

    private String orgName;
    private String orgUrl;
    private ArrayList<ActivityInstance> activityInstances;

    public Activity() {
    }

    public Activity(String orgName) {
        this(orgName, null, (ArrayList<ActivityInstance>) null);
    }

    public Activity(String orgName, String orgUrl) {
        this(orgName, orgUrl, (ArrayList<ActivityInstance>) null);
    }

    public Activity(String orgName, String orgUrl, ArrayList<ActivityInstance> activityInstances) {
        if (orgName == null) {
            throw new RuntimeException("organisation cannot be 'null'");
        }
        if (orgName.equals("")) {
            throw new RuntimeException("organisation cannot be an empty string");
        }
        this.orgName = orgName;
        this.orgUrl = orgUrl;
        this.activityInstances = activityInstances;
    }

    public Activity(String orgName, String orgUrl, ActivityInstance[] activityInstances) {
        if (orgName == null) {
            throw new RuntimeException("organisation cannot be 'null'");
        }
        if (orgName.equals("")) {
            throw new RuntimeException("organisation cannot be an empty string");
        }
        this.orgName = orgName;
        this.orgUrl = orgUrl;
        ArrayList<ActivityInstance> list = new ArrayList<>(Arrays.asList(activityInstances));
        this.activityInstances = list;
    }
    public String getOrgName() {
        return orgName;
    }

    public String getOrgUrl() {
        return orgUrl;
    }

    public ArrayList<ActivityInstance> getActivityInstances() {
        return activityInstances;
    }
}
