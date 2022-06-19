package club.swdev.webapp.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Period {
    private LocalDate startDate;
    private LocalDate endDate;
    private String title;
    private String description;

    public Period() {
    }

    public Period(LocalDate startDate, LocalDate endDate, String title, String description) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.title = title;
        this.description = description;
    }

    public Period(String startDate, String endDate, String title, String description) {
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

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
