package dev.danielmcpherson.resume_builder.util;

import dev.danielmcpherson.resume_builder.model.*;

import java.io.FileInputStream;
import java.io.InputStream;

import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class FileReader {

    // Reads a yaml file
    public static Resume readFile(String pathToFile) {
        LoaderOptions options = new LoaderOptions();
        Yaml yaml = new Yaml(options);

        try (InputStream inputStream = new FileInputStream(pathToFile)) {
            Resume resume = yaml.loadAs(inputStream, Resume.class);
            System.out.println(resume);

            return resume;
        } catch (Exception e) {
            System.out.println("Failed to read YAML file. Recieved error: " + e.toString());
            System.exit(1);
            return null;
        }
    }

    public static Resume readFromString(String yamlContent) {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            return mapper.readValue(yamlContent, Resume.class);
        } catch (Exception e) {
            System.out.println("Failed to read YAML input. Recieved error: " + e.toString());
            System.exit(1);
            return null;
        }
    }


}
