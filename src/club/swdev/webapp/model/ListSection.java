package club.swdev.webapp.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListSection extends AbstractSection {
    private final List<String> content;

    public ListSection() {
        this.content = new ArrayList<>();
    }

    public ListSection(List<String> content) {
        this.content = content;
    }

    public ListSection(String[] content) {
        this.content = Arrays.asList(content);
    }

    public List<String> getContent() {
        return content;
    }
}
