package club.swdev.webapp.model;

import club.swdev.webapp.util.Dates;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class Duration {
    private LocalDate startDate;

    private LocalDate endDate;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public Duration(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Duration(LocalDate startDate) {
        this.startDate = startDate;
        this.endDate = Dates.NOW;
    }

    public Duration(int startYear, Month startMonth, int startDayOfMonth, int endYear, Month endMonth, int endDayOfMonth) {
        this(Dates.of(startYear, startMonth, startDayOfMonth), Dates.of(endYear, endMonth, endDayOfMonth));
    }

    public Duration(int startYear, Month startMonth, int startDayOfMonth) {
        this.startDate = Dates.of(startYear, startMonth, startDayOfMonth);
        this.endDate = Dates.NOW;
    }

    public Duration(int startYear, Month startMonth, int endYear, Month endMonth) {
        this(startYear, startMonth, 1, endYear, endMonth, 1);
    }

    public Duration(int startYear, Month startMonth) {
        this.startDate = Dates.of(startYear, startMonth, 1);
        this.endDate = Dates.NOW;
    }

    public Duration(String startDate, String endDate) {
        this.startDate = LocalDate.parse(startDate, formatter);
        this.endDate = endDate.equals("") ? Dates.NOW : LocalDate.parse(endDate, formatter);
    }

    public Duration(String startDate) {
        this.startDate = LocalDate.parse(startDate, formatter);
        this.endDate = Dates.NOW;
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
}
