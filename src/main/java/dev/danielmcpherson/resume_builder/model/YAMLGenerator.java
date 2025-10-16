package dev.danielmcpherson.resume_builder.model;

import java.io.File;
import java.util.Scanner;
import java.time.Instant;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import io.github.cdimascio.dotenv.Dotenv;
import dev.danielmcpherson.resume_builder.util.*;

import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.chat.completions.ChatCompletion;
import com.openai.models.chat.completions.ChatCompletionCreateParams;
import com.openai.models.ChatModel;
import com.openai.client.OpenAIClient;
import com.fasterxml.jackson.databind.ObjectMapper;

public class YAMLGenerator {
    private String analysisPrompt;
    private String resumeCreationPrompt;
    private String exampleFormat; // The expected output from ChatGPT
    private String jobDescription;
    private Path masterFile; // The file containing all the person's skills, experience, etc
    private String customInstructions;
    private Dotenv dotenv;

    public YAMLGenerator(String jobDescription, Path masterFile) {
        setPrompt();
        setExampleFormat();
        this.dotenv = Dotenv.load();
        this.jobDescription = jobDescription;
        this.masterFile = masterFile;
    }

    public YAMLGenerator(String jobDescription, Path masterFile, String customInstructions) {
        setPrompt();
        setExampleFormat();
        this.dotenv = Dotenv.load();
        this.jobDescription = jobDescription;
        this.masterFile = masterFile;
        this.customInstructions = customInstructions;
    }

    private void setPrompt() {
        try {
            this.analysisPrompt = new String(Files.readString(Paths.get("src/main/resources/templates/analysis_prompt.txt")));
            this.resumeCreationPrompt = new String(Files.readString(Paths.get("src/main/resources/templates/resume_creation_prompt.txt")));
        } catch (Exception e) {
            System.out.println("Couldn't read the prompts. Received error:");
            e.printStackTrace();

            System.exit(1);
        }

    }

    public String generateResume() {
        // Gets all the person's experience, skills, etc from the master file
        String masterInformation = getMasterInformation();
        String chatGPTResponse = callChatGPT(masterInformation);

        System.out.println("Generating your custom resume...");

        return chatGPTResponse;
    }

    public static String saveYaml(String yaml, Resume resume) {
        String companyName = null;

        try {
            Path jobFilePath = Paths.get("job_description.txt");
            companyName = Files.readAllLines(jobFilePath, StandardCharsets.UTF_8).get(0).trim();
        } catch (Exception e) {
            System.err.println("Failed to read company name from job_description.txt: " + e.getMessage());
        }

        if (companyName == null || companyName.isEmpty()) {
            companyName = Long.toString(Instant.now().toEpochMilli());
        }

        Path folderPath = Paths.get("generated", companyName);
        String fileName = companyName + ".yaml";
        Path filePath = folderPath.resolve(fileName);

        try {
            Files.createDirectories(folderPath);
            Files.writeString(filePath, yaml, StandardCharsets.UTF_8);
        } catch (Exception e) {
            System.err.println("Failed to save YAML: " + e.getMessage());
            System.out.println("YAML content:\n" + yaml);
            System.exit(1);
        }

        return companyName;
    }

    // Reads the masterFile and returns its contents
    private String getMasterInformation() {
        System.out.println("Reading master file...");
        try {
            return new String(Files.readString(masterFile));
        } catch (Exception e) {
            System.out.println("Couldn't read master information. Received error:");
            e.printStackTrace();

            System.exit(1);
            return null;
        }
    }

    private void setExampleFormat() {
        try {
            this.exampleFormat = new String(Files.readString(Paths.get("src/main/resources/templates/resume_template.yaml")));
        } catch (Exception e) {
            System.out.println("Couldn't read example file. Received error:");
            e.printStackTrace();

            System.exit(1);
        }

    }

    private JobAnalysis analyseJobDescription() {
        OpenAIClient client = OpenAIOkHttpClient.builder().fromEnv().apiKey(dotenv.get("OPENAI_API_KEY")).build();
        ChatCompletionCreateParams params = ChatCompletionCreateParams.builder()
            .addSystemMessage(analysisPrompt)
            .addUserMessage("Here is the job description:\n\n" + jobDescription)
            .model(ChatModel.GPT_5_NANO)
            .build();

        ChatCompletion chatCompletion = client.chat().completions().create(params);
        String response = chatCompletion.choices().get(0).message().content().get();

        ObjectMapper mapper = new ObjectMapper();
        JobAnalysis analysis = new JobAnalysis();
        try {
            // Parse model output into a JobAnalysis object
            analysis = mapper.readValue(response, JobAnalysis.class);
        } catch (Exception e) {
            System.err.println("Error parsing JSON from analysis response: " + e.getMessage());
            System.exit(1);
        }

        analysis.setAnalysis(response);
        System.out.println(response);
        return analysis;
    }

    private String callChatGPT(String masterInformation) {
        JobAnalysis jobAnalysis = analyseJobDescription();

        OpenAIClient client = OpenAIOkHttpClient.builder().fromEnv().apiKey(dotenv.get("OPENAI_API_KEY")).build();
        ChatCompletionCreateParams params = ChatCompletionCreateParams.builder()
            .addSystemMessage(resumeCreationPrompt)
            .addUserMessage("Here is the job description analysis:\n\n" + jobAnalysis.getAnalysis())
            .addUserMessage("And here's all the person's experience (the master file):\n\n" + masterInformation)
            .addUserMessage("And finally, here's the example format you must respond with:\n\n" + exampleFormat)
            .model(ChatModel.GPT_5_NANO)
            .build();

        ChatCompletion chatCompletion = client.chat().completions().create(params);
        return chatCompletion.choices().get(0).message().content().get();
    }
}
