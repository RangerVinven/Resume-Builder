package dev.danielmcpherson.resume_builder;

import dev.danielmcpherson.resume_builder.util.*;
import dev.danielmcpherson.resume_builder.model.*;

import freemarker.template.Configuration;

import java.nio.file.StandardOpenOption;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;

public class App {
    public static void main(String[] args) {
        if(args.length == 0 || (!args[0].equalsIgnoreCase("build") && !args[0].equalsIgnoreCase("generate") && !args[0].equalsIgnoreCase("help"))) {
            System.out.println("First argument must be either 'build' (for building the resume based of a given yaml file) or 'generate' (for generating the resume based off a job description and master file).");
            System.exit(0);
        }

        if(args[0].equalsIgnoreCase("build")) {
            buildResume(args);
        }

        if(args[0].equalsIgnoreCase("generate")) {
            generateResume(args);
        }
    }

    private static void buildResume(String[] args) {
        if(args.length != 3) {
            System.out.println("Must be in the format of 'resume-builder build <inputYamlFile> <outputFile>'.");
            System.exit(0);
        }

        String resumeYamlFile = args[1];
        String outputFile = args[2];

        Resume resume = FileReader.readFile(resumeYamlFile);
        writeResumeToPDF(resume, outputFile);
    }

    private static void generateResume(String[] args) {
        if(args.length != 4) {
            System.out.println("Must be in the format of 'resume-builder generate <job_description> <master_file> <output_folder>'.");
            System.exit(0);
        }

        String jobDescriptionFile = args[1];
        String masterFile = args[2];
        Path outputFolder = Path.of(args[3]);

        YAMLGenerator yamlGenerator = new YAMLGenerator(jobDescriptionFile, masterFile);
        String generatedResume = yamlGenerator.generateResume();

        Resume resume = FileReader.readFromString(generatedResume);

        try {
            Files.createDirectories(outputFolder);
            String baseName = outputFolder.getFileName().toString();

            Files.write(
                outputFolder.resolve(baseName + ".yaml"),
                generatedResume.getBytes(),
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING
            );

            writeResumeToPDF(resume, outputFolder.resolve(baseName + ".pdf").toString());

        } catch (Exception e) {
            System.out.println("Failed to generate resume. Recieved error: ");
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static void writeResumeToPDF(Resume resume, String outputFile) {
        try {
            System.out.println("Building resume...");
            Configuration configuration = FreemarkerUtil.createConfiguration();
            String html = FreemarkerUtil.renderResumeHtml(configuration, resume);

            FreemarkerUtil.renderToPDF(configuration, html, Paths.get(outputFile));

            System.out.println("Resume built and saved!");
        } catch (Exception e) {
            System.out.println("Couldn't create PDF. Recieved error: " + e.toString());
            System.exit(1);
        }
    }
} 
