package club.swdev.webapp.model;

public class ActivityInstance {
    private String formDate;
    private String toDate;
    private TextSection occupation;

    public ActivityInstance() {
    }

    public ActivityInstance(String formDate, String toDate, TextSection occupation) {
        this.formDate = formDate;
        this.toDate = toDate;
        this.occupation = occupation;
    }

    public ActivityInstance(String formDate, String toDate, String title, String[] descriptions) {
        this.formDate = formDate;
        this.toDate = toDate;
        this.occupation = new TextSection(title, descriptions);
    }

    public String getFormDate() {
        return formDate;
    }

    public void setFormDate(String formDate) {
        this.formDate = formDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public TextSection getOccupation() {
        return occupation;
    }

    public void setOccupation(TextSection occupation) {
        this.occupation = occupation;
    }

    public void setOccupation(String title, String[] descriptions) {
        this.occupation = new TextSection(title, descriptions);
    }
}
