package dev.danielmcpherson.resume_builder.util;

import dev.danielmcpherson.resume_builder.model.Resume;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.nio.file.Path;
import java.io.OutputStream;
import java.io.FileOutputStream;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;
import freemarker.core.HTMLOutputFormat;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;

public class FreemarkerUtil {

    public static Configuration createConfiguration() throws Exception {
        Configuration cfg = new Configuration(new Version("2.3.33"));

        cfg.setClassLoaderForTemplateLoading(
            FreemarkerUtil.class.getClassLoader(), 
            "/templates"
        );

        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);
        cfg.setFallbackOnNullLoopVariable(false);
        cfg.setNumberFormat("computer");
        cfg.setOutputFormat(HTMLOutputFormat.INSTANCE);
        cfg.setAutoEscapingPolicy(Configuration.ENABLE_IF_DEFAULT_AUTO_ESCAPING_POLICY);

        return cfg;
    }

    public static String renderResumeHtml(Configuration cfg, Resume resume) throws Exception {
        Template template = cfg.getTemplate("resume_template.ftl");

        Map<String, Object> model = new HashMap<>();
        model.put("resume", resume);

        try (StringWriter out = new StringWriter()) {
            template.process(model, out);
            return out.toString();
        }
    }

    public static void renderToPDF(Configuration config, String html, Path outputFile) {
        try (OutputStream os = new FileOutputStream(outputFile.toFile())) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.useFastMode();
            builder.withHtmlContent(html, null);
            builder.toStream(os);
            builder.run();
        } catch (Exception e) {
            throw new RuntimeException("Couldn't render PDF", e);
        }

    }
}

