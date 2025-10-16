package dev.danielmcpherson.resume_builder;

import dev.danielmcpherson.resume_builder.util.*;
import dev.danielmcpherson.resume_builder.model.*;

import freemarker.template.Configuration;
import java.nio.file.Paths;
import java.nio.file.Files;

public class App {
    public static void main(String[] args) {
        // Resume resume = FileReader.readFile("resume.yaml");
        String jobDescription = "";
        
        try {
            System.out.println("Reading job description...");
            jobDescription = new String(Files.readString(Paths.get("job_description.txt")));
        } catch (Exception e) {
            System.out.println("Couldn't read job description. Received error:");
            e.printStackTrace();
            System.exit(1);
        }

        YAMLGenerator yamlGenerator = new YAMLGenerator(jobDescription, Paths.get("master.txt"));
        Resume resume = FileReader.readFromString(yamlGenerator.generateYaml());
        // yamlGenerator.saveYaml(resume);

        try {
            System.out.println("Building resume...");
            Configuration configuration = FreemarkerUtil.createConfiguration();
            String html = FreemarkerUtil.renderResumeHtml(configuration, resume);
            FreemarkerUtil.renderToPDF(configuration, html, Paths.get("new_resume.pdf"));

            System.out.println("Resume built!");
        } catch (Exception e) {
            System.out.println("Couldn't create PDF. Recieved error: " + e.toString());
            System.exit(1);
        }
    }
}
