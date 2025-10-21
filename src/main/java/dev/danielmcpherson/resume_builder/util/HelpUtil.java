package dev.danielmcpherson.resume_builder.util;

public class HelpUtil {
    public static void showHelpMenu() {
        System.out.println("Usage:");
        System.out.println("  resume-builder build <inputYamlFile> <outputFile>");
        System.out.println("      Builds a resume PDF from an existing YAML file.");
        System.out.println();
        System.out.println("  resume-builder generate <job_description> <master_file> <output_folder>");
        System.out.println("      Generates a resume based on a job description and master file,");
        System.out.println("      saving both the YAML and PDF to the specified output folder.");
        System.out.println();
        System.out.println("  resume-builder help");
        System.out.println("      Displays this help message.");
    }

    public static void showExampleYaml() {
        try {
            // Path to the example YAML file
            java.nio.file.Path path = java.nio.file.Path.of("src/main/resources/templates/resume_template.yaml");

            // Check if the file exists
            if (!java.nio.file.Files.exists(path)) {
                System.out.println("Example YAML not found at " + path.toString());
                return;
            }

            // Read and print the file content
            String content = java.nio.file.Files.readString(path);
            System.out.println("Example YAML:");
            System.out.println(content);

            System.out.println("Note:");
            System.out.println("You can use Markdown styling for making text bold, italics, or to turn it into a link.");

        } catch (Exception e) {
            System.out.println("Failed to read example YAML. Received error:");
            e.printStackTrace();
        }
    }
}
