package ru.kpfu.config;

import freemarker.template.TemplateExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassRelativeResourceLoader;
import org.springframework.ui.freemarker.SpringTemplateLoader;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

@Configuration
@Import(TrelloImplConfiguration.class)
public class TrelloWebConfiguration {

}
