package club.swdev.webapp.model;

import java.util.Arrays;
import java.util.List;

public class OrganizationSection extends AbstractSection {
    private List<Organization> organizations;

    public OrganizationSection() {
        super();
    }

    public OrganizationSection(List<Organization> organizations) {
        super();
        this.organizations = organizations;
    }

    public OrganizationSection(Organization[] organizations) {
        super();
        this.organizations = Arrays.asList(organizations);
    }

    public List<Organization> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<Organization> organizations) {
        this.organizations = organizations;
    }
}
