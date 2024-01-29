package org.example.taskmanagement.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "taskmanager")
@Data
public class ApplicationProperties {

    private String systemDeveloperName;
    private String systemDeveloperEmail;

}
