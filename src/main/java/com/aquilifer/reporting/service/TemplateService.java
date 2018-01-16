package com.aquilifer.reporting.service;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.StringWriter;
import java.util.Locale;
import java.util.Map;


public class TemplateService {

    public String processHMTLTemplate(String templateName, Map<String, Object> jsonAsMap) {

        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("com/aquilifer/reporting/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML");

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        Context context = new Context(Locale.getDefault(), jsonAsMap);

        StringWriter stringWriter = new StringWriter();
        templateEngine.process(templateName, context, stringWriter);
        return stringWriter.toString();
    }

}
