package ru.kpfu.config;

import org.apache.coyote.http11.AbstractHttp11Protocol;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan({
        "ru.kpfu.services"
})
@EnableJpaRepositories(basePackages = "ru.kpfu.repositories")
@EntityScan(basePackages = "ru.kpfu.entity")
@EnableJpaAuditing
public class TrelloImplConfiguration implements WebMvcConfigurer {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setCacheSeconds(10); //reload messages every 10 seconds
        return messageSource;
    }

    @Bean
    TomcatConnectorCustomizer headerRejectionCustomizer() {
        return (connector) ->
                ((AbstractHttp11Protocol<?>)connector.getProtocolHandler()).setRejectIllegalHeader(false);
    }
}
