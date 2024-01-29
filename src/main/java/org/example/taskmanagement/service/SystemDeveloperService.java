package org.example.taskmanagement.service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.example.taskmanagement.configuration.FlagProperties;
import org.example.taskmanagement.model.Developer;
import org.example.taskmanagement.repository.DeveloperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class SystemDeveloperService {
    @Autowired
    private DeveloperRepository developerRepository;

    @Autowired
    private FlagProperties flagProperties;

    @PostConstruct
    public void addSystemDeveloper() {
        if(flagProperties.isSaveSysDeveloper()) {
            Developer systemDeveloper = new Developer("System Developer", "sys.dev@example.com", null);
            developerRepository.save(systemDeveloper);
        }
    }

    @PreDestroy
    public void deleteSystemDeveloper() {
        if (flagProperties.isSaveSysDeveloper()) {
            Developer systemDeveloper = developerRepository.findByEmail("sys.dev@example.com");
            if (systemDeveloper != null) {
                developerRepository.delete(systemDeveloper);
            }
        }
    }
}
