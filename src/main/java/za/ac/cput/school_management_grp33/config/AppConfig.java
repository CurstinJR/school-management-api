/**
 * @Author Curstin Rose - 220275408
 * AppConfig.java
 * Created on 14 June 2022
 */
package za.ac.cput.school_management_grp33.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class AppConfig {

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages/ValidationMessages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}
