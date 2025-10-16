package dev.danielmcpherson.resume_builder.model;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import io.github.cdimascio.dotenv.Dotenv;

import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.chat.completions.ChatCompletion;
import com.openai.models.chat.completions.ChatCompletionCreateParams;
import com.openai.models.ChatModel;
import com.openai.client.OpenAIClient;

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

    public String generateYaml() {
        // Gets all the person's experience, skills, etc from the master file
        String masterInformation = getMasterInformation();
        String chatGPTResponse = callChatGPT(masterInformation);

        System.out.println("Generating your custom resume...");
        System.out.println(chatGPTResponse);

        return chatGPTResponse;
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

    private String analyseJobDescription() {
        OpenAIClient client = OpenAIOkHttpClient.builder().fromEnv().apiKey(dotenv.get("OPENAI_API_KEY")).build();
        ChatCompletionCreateParams params = ChatCompletionCreateParams.builder()
            .addSystemMessage(analysisPrompt)
            .addUserMessage("Here is the job description:\n\n" + jobDescription)
            .model(ChatModel.GPT_5_NANO)
            .build();

        ChatCompletion chatCompletion = client.chat().completions().create(params);
        System.out.println(chatCompletion.choices().get(0).message().content().get());
        return chatCompletion.choices().get(0).message().content().get();
    }

    private String callChatGPT(String masterInformation) {
        String jobAnalysis = analyseJobDescription();

        OpenAIClient client = OpenAIOkHttpClient.builder().fromEnv().apiKey(dotenv.get("OPENAI_API_KEY")).build();
        ChatCompletionCreateParams params = ChatCompletionCreateParams.builder()
            .addSystemMessage(resumeCreationPrompt)
            .addUserMessage("Here is the job description analysis:\n\n" + jobAnalysis)
            .addUserMessage("And here's all the person's experience (the master file):\n\n" + masterInformation)
            .addUserMessage("And finally, here's the example format you must respond with:\n\n" + exampleFormat)
            .model(ChatModel.GPT_5_NANO)
            .build();

        ChatCompletion chatCompletion = client.chat().completions().create(params);
        return chatCompletion.choices().get(0).message().content().get();
    }
}
