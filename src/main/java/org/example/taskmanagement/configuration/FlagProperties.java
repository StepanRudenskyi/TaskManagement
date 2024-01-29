package org.example.taskmanagement.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "flag")
@Data
public class FlagProperties {
    private boolean saveSysDeveloper = true;
}
