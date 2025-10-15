package dev.danielmcpherson.resume_builder;

import dev.danielmcpherson.resume_builder.util.*;
import dev.danielmcpherson.resume_builder.model.*;

import freemarker.template.Configuration;
import java.nio.file.Paths;

public class App {
    public static void main(String[] args) {
        Resume resume = FileReader.readFile("resume.yaml");

        YAMLGenerator yamlGenerator = new YAMLGenerator("Hello, can you understand me?", Paths.get("master.txt"));
        yamlGenerator.generateYaml();

        try {
            Configuration configuration = FreemarkerUtil.createConfiguration();
            String html = FreemarkerUtil.renderResumeHtml(configuration, resume);
            FreemarkerUtil.renderToPDF(configuration, html, Paths.get("new_resume.pdf"));

        } catch (Exception e) {
            System.out.println("Couldn't create PDF. Recieved error: " + e.toString());
            System.exit(1);
        }
    }
}
