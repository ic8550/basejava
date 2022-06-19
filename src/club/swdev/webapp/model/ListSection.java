package club.swdev.webapp.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListSection extends AbstractSection {
    private List<String> content;

    public ListSection() {
        super();
        this.content = new ArrayList<>();
    }

    public ListSection(List<String> content) {
        super();
        this.content = content;
    }

    public ListSection(String[] content) {
        super();
        this.content = Arrays.asList(content);
    }

    public List<String> getContent() {
        return content;
    }

    public void setContent(List<String> content) {
        this.content = content;
    }
}
