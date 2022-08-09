package club.swdev.webapp.util;

import club.swdev.webapp.model.AbstractSection;
import club.swdev.webapp.model.Resume;
import club.swdev.webapp.model.TextSection;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JsonParserTest {
    private final Resume RESUME_7 = UtilResumes.fillWithAllButOrganization(7);

    @Test
    public void testResume() throws Exception {
        String json = JsonParser.write(RESUME_7);
        System.out.println(json);
        Resume resume = JsonParser.read(json, Resume.class);
        assertEquals(RESUME_7, resume);
    }

    @Test
    public void write() throws Exception {
        AbstractSection section1 = new TextSection("Objective1");
        String json = JsonParser.write(section1, AbstractSection.class);
        System.out.println(json);
        AbstractSection section2 = JsonParser.read(json, AbstractSection.class);
        assertEquals(section1, section2);
    }
}