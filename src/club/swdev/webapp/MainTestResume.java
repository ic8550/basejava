package club.swdev.webapp;

import club.swdev.webapp.model.*;
import club.swdev.webapp.util.Resumes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.EnumMap;
import java.util.List;
import java.util.UUID;

public class MainTestResume {
    public static Resume resume;

    public static void main(String[] args) {
        resume = Resumes.fillOut(UUID.randomUUID().toString(), "Григорий Кислин");

        printResume(resume);
    }

    public static void printResume(Resume resume) {
        System.out.println();
        printFullName(resume.getFullName());
        System.out.println();
        printContacts(resume.getContacts());
        System.out.println();
        printSections(resume.getSections());
    }

    public static void printFullName(String fullName) {
        System.out.println(fullName);
    }

    public static void printContacts(EnumMap<ContactType, String> contacts) {
        System.out.println("CONTACTS:");
        contacts.forEach((key, value) -> System.out.println("    " + key + ": " + value));
    }

    public static void printSections(EnumMap<SectionType, AbstractSection> sections) {
        sections.forEach((key, value) -> printSection(key));
    }

    public static void printSection(SectionType sectionType) {
        switch (sectionType) {
            case OBJECTIVE -> {
                System.out.println("OBJECTIVE:");
                printTextSection(resume.getSections().get(SectionType.OBJECTIVE), "    ");
            }
            case PERSONAL -> {
                System.out.println("\nPERSONAL:");
                printTextSection(resume.getSections().get(SectionType.PERSONAL), "    ");
            }
            case ACHIEVEMENTS -> {
                System.out.println("\nACHIEVEMENTS:");
                printListSection((ListSection) resume.getSections().get(SectionType.ACHIEVEMENTS), "    ");
            }
            case QUALIFICATIONS -> {
                System.out.println("\nQUALIFICATIONS:");
                printListSection((ListSection) resume.getSections().get(SectionType.QUALIFICATIONS), "    ");
            }
            case EXPERIENCE -> {
                System.out.println("\nEXPERIENCE:");
                printOrganizationSection((OrganizationSection) resume.getSections().get(SectionType.EXPERIENCE), "    ");
            }
            case EDUCATION -> {
                System.out.println("\nEDUCATION:");
                printOrganizationSection((OrganizationSection) resume.getSections().get(SectionType.EDUCATION), "    ");
            }
        }
    }

    public static void printTextSection(AbstractSection textSection, String indent) {
        System.out.print(indent);
        System.out.println(((TextSection) textSection).getText());
    }

    public static void printListSection(ListSection listSection, String indent) {
        List<String> content = listSection.getContent();
        for (String s : content) {
            System.out.print(indent);
            System.out.println(s);
        }
    }

    public static void printOrganizationSection(OrganizationSection organizationSection, String indent) {
        List<Organization> organizations = organizationSection.getOrganizations();
        for (Organization organization : organizations) {
            printOrganization(organization, indent);
        }
    }

    public static void printOrganization(Organization organization, String indent) {
        String url = organization.getUrl();
        String name = organization.getName();
        System.out.print(indent + name);
        if (url != null) {
            System.out.println(" (" + url + ")");
        } else {
            System.out.println();
        }
        List<Activity> activities = organization.getActivities();
        for (Activity activity : activities) {
            printPeriod(activity, indent);
        }
    }

    public static void printPeriod(Activity activity, String indent) {
        String startDateFormatted = activity.getStartDate().format(DateTimeFormatter.ofPattern("MM/yyyy"));
        LocalDate endDate = activity.getEndDate();
        String endDateFormatted = (endDate == null)
                ? "по настоящее время"
                : endDate.format(DateTimeFormatter.ofPattern("MM/yyyy"));
        System.out.println(indent + indent + startDateFormatted + " - " + endDateFormatted);
        System.out.println(indent + indent + indent + activity.getTitle());
        String description = activity.getDescription();
        if (description != null) {
            System.out.println(indent + indent + indent + indent + description);
        }
    }
}

