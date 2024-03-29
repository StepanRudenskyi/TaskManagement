package org.example.taskmanagement.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "taskmanager")
@Data
public class SystemDeveloperProperties {

    private String systemDeveloperName;
    private String systemDeveloperEmail;

}
