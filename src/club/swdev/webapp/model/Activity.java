package club.swdev.webapp.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Activity {
    private LocalDate startDate;
    private LocalDate endDate;
    private String title;
    private String description;

    public Activity() {
    }

    public Activity(LocalDate startDate, LocalDate endDate, String title, String description) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.description = description;
    }

    public Activity(String startDate, String endDate, String title, String description) {
        //convert String to LocalDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        this.startDate = LocalDate.parse(startDate, formatter);
        this.endDate = endDate.equals("") ? null : LocalDate.parse(endDate, formatter);
        this.title = title;
        this.description = description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }


}
