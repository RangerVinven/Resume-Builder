package dev.danielmcpherson.resume_builder.util;

import dev.danielmcpherson.resume_builder.model.*;

import java.io.FileInputStream;
import java.io.InputStream;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public class FileReader {

    // Reads a yaml file
    public static void readFile(String pathToFile) {
        LoaderOptions options = new LoaderOptions();
        Yaml yaml = new Yaml(options);

        try (InputStream inputStream = new FileInputStream(pathToFile)) {
            Resume resume = yaml.loadAs(inputStream, Resume.class);
            System.out.println(resume);
        } catch (Exception e) {
            System.out.println("Failed to read YAML file. Recieved error: " + e.toString());
        }

        //return new Resume();
    }

}
