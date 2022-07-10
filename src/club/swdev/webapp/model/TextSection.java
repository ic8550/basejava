package club.swdev.webapp.model;

public class TextSection extends AbstractSection {
    private static final long serialVersionUID = 1L;
    private final String text;

    public TextSection() {
        this.text = "";
    }

    public TextSection(String text) {
        if (text == null) {
            throw new RuntimeException("TextSection's text content cannot be 'null'");
        }
        if (text.equals("")) {
            throw new RuntimeException("TextSection's text content cannot be an empty string");
        }
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
