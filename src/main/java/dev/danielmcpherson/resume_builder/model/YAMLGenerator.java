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
    private String prompt;
    private String exampleFormat; // The expected output from ChatGPT
    private String jobDescription;
    private Path masterFile; // The file containing all the person's skills, experience, etc
    private String customInstructions;

    public YAMLGenerator(String jobDescription, Path masterFile) {
        setPrompt();
        setExampleFormat();
        this.jobDescription = jobDescription;
        this.masterFile = masterFile;
    }

    public YAMLGenerator(String jobDescription, Path masterFile, String customInstructions) {
        setPrompt();
        setExampleFormat();
        this.jobDescription = jobDescription;
        this.masterFile = masterFile;
        this.customInstructions = customInstructions;
    }

    private void setPrompt() {
        this.prompt = "You are to be given a job description. I want you to analyse it, and see what kind of job it is. Test for a wide variety of things, for example: is it a fast-pased job, where you'd be expected to get your task handed in on-time, or is it more laid-back place that focuses more on being like a family. Analyse the job description as much as possible, and thing about all the different facets and requirements for the job. You are also going to be given the contents of a file, containing a bunch of information about a person's experience (their work experience, skills, certificates, etc). Your job is to pick the most relevant items for each of the sections (work experience, skills, etc) and compile them together in a standardised YAML format which you'll be given. You can pick multiple items from the sections, just pick the best ones that'll relate to the job at hand. Strictly do not change any information or add your own.";
    }

    public String generateYaml() {
        // Gets all the person's experience, skills, etc from the master file
        String masterInformation = getMasterInformation();
        System.out.println(callChatGPT(masterInformation));

        return "";
    }

    // Reads the masterFile and returns its contents
    private String getMasterInformation() {
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

    private String callChatGPT(String masterInformation) {
        Dotenv dotenv = Dotenv.load();

        OpenAIClient client = OpenAIOkHttpClient.builder().fromEnv().apiKey(dotenv.get("OPENAI_API_KEY")).build();
        ChatCompletionCreateParams params = ChatCompletionCreateParams.builder()
            .addSystemMessage(prompt)
            .addUserMessage("Here is the job description:\n\n" + jobDescription)
            .addUserMessage("And here's all the person's experience:\n\n" + masterInformation)
            .addUserMessage(exampleFormat)
            .model(ChatModel.GPT_5_NANO)
            .build();

        ChatCompletion chatCompletion = client.chat().completions().create(params);
        return chatCompletion.toString();
    }
}
