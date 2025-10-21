package dev.danielmcpherson.resume_builder.util;

import java.util.List;

import dev.danielmcpherson.resume_builder.model.Resume;
import dev.danielmcpherson.resume_builder.model.Section;
import dev.danielmcpherson.resume_builder.model.SectionItem;

import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.util.ast.Node;

import org.jsoup.safety.Safelist;
import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;

public class YAMLSanitiserUtil {
    private static String markdownToSafeHtml(String markdown) {
        if (markdown == null) return null;
        Parser parser = Parser.builder().build();
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        Node document = parser.parse(markdown);
        String html = renderer.render(document);
        Safelist safelist = Safelist.none()
        .addTags("strong", "b", "i", "em", "a")
        .addAttributes("a", "href", "title")
        .addProtocols("a", "href", "http", "https", "mailto");
        
        return Jsoup.clean(html, "", safelist, new Document.OutputSettings().prettyPrint(false)).replaceAll("<a ", "<a target=\"_blank\" ");
    }

    public static void preprocessResumeForMarkdown(Resume resume) {
        if (resume == null) return;

        // Person's Information
        if (resume.getPerson_information() != null) {
            if (resume.getPerson_information().getName() != null) {
                resume.getPerson_information().setName(markdownToSafeHtml(resume.getPerson_information().getName()));
            }

            List<String> details = resume.getPerson_information().getDetails();
            if (details != null) {
                for (int i = 0; i < details.size(); i++) {
                    details.set(i, markdownToSafeHtml(details.get(i)));
                }
            }
        }

        // Personal statement
        if (resume.getPersonal_statement() != null && resume.getPersonal_statement().getStatement() != null) {
            String s = resume.getPersonal_statement().getStatement();
            resume.getPersonal_statement().setStatement(markdownToSafeHtml(s));
        }

        // Person information (like contact details)
        if (resume.getPerson_information() != null && resume.getPerson_information().getDetails() != null) {
            List<String> details = resume.getPerson_information().getDetails();
            for (int i = 0; i < details.size(); i++) {
                details.set(i, markdownToSafeHtml(details.get(i)));
            }
        }

        // Resume sections
        if (resume.getSections() != null) {
            for (Section sec : resume.getSections()) {
                if (sec == null || sec.getItems() == null) continue;
                for (SectionItem item : sec.getItems()) {
                    if (item == null) continue;
                    if (item.getTitle() != null) item.setTitle(markdownToSafeHtml(item.getTitle()));
                    if (item.getSubtitle() != null) item.setSubtitle(markdownToSafeHtml(item.getSubtitle()));
                    if (item.getMeta() != null) item.setMeta(markdownToSafeHtml(item.getMeta()));

                    List<String> bullets = item.getBullet_points();
                    if (bullets != null) {
                        for (int i = 0; i < bullets.size(); i++) {
                            bullets.set(i, markdownToSafeHtml(bullets.get(i)));
                        }
                    }
                }
            }
        }
    }
}

