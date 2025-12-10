package de.fi.webapp.service.config;


import de.fi.webapp.YamlPropertySourceFactory;
import de.fi.webapp.service.MailServiceDummy;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:mail.yml",factory = YamlPropertySourceFactory.class)
@ConfigurationProperties(prefix="mail")
public class MailConfig {

    private String host;
    private String username;
    private String password;
    private String protocol;

    public void setHost(final String host) {
        this.host = host;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public void setProtocol(final String protocol) {
        this.protocol = protocol;
    }

    @Bean
    public MailServiceDummy mailServiceDummy() {
        return new MailServiceDummy(username,password);
    }
}
