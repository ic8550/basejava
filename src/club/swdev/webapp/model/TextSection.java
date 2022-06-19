package club.swdev.webapp.model;

public class TextSection extends AbstractSection {
    private String text;

    public TextSection() {
        super();
        this.text = "";
    }

    public TextSection(String text) {
        super();
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

    public void setText(String text) {
        this.text = text;
    }
}
