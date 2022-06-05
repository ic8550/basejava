package club.swdev.webapp.model;

import java.util.ArrayList;
import java.util.Arrays;

public class TextSection {
    private String title;
    private ArrayList<String> descriptions;

    public TextSection() {
    }

    public TextSection(String title) {
        if (title == null) {
            throw new RuntimeException("TextSection's title cannot be 'null'");
        }
        if (title.equals("")) {
            throw new RuntimeException("TextSection's title cannot be an empty string");
        }
        this.title = title;
        this.descriptions = null;
    }

    public TextSection(String title, ArrayList<String> descriptions) {
        this(title);
        if (descriptions == null) {
            throw new RuntimeException("TextSection's descriptions cannot be 'null'");
        }
        this.descriptions = descriptions;
    }

    public TextSection(String title, String[] descriptions) {
        this(title);
        if (descriptions == null) {
            this.descriptions = new ArrayList<>();
            return;
        }
        this.descriptions = new ArrayList<>(Arrays.asList(descriptions));
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(ArrayList<String> descriptions) {
        this.descriptions = descriptions;
    }
    public void setDescriptions(String[] descriptions) {
        this.descriptions = new ArrayList<>(Arrays.asList(descriptions));
    }
}
