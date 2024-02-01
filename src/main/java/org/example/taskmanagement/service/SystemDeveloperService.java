package org.example.taskmanagement.service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
//import javax.annotation.PostConstruct;
//import javax.annotation.PreDestroy;
import jakarta.transaction.Transactional;
import org.example.taskmanagement.configuration.FlagProperties;
import org.example.taskmanagement.configuration.SystemDeveloperProperties;
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
    private SystemDeveloperProperties systemDeveloperProperties;

    @Autowired
    private FlagProperties flagProperties;

    @PostConstruct
    @Transactional
    public void addSystemDeveloper() {
        if(flagProperties.isSaveSysDeveloper()) {
            Developer systemDeveloper = new Developer(systemDeveloperProperties.getSystemDeveloperName(), systemDeveloperProperties.getSystemDeveloperEmail(), null);
            developerRepository.save(systemDeveloper);
        }
    }

    @PreDestroy
    @Transactional
    public void deleteSystemDeveloper() {
        if (flagProperties.isSaveSysDeveloper()) {
            Developer systemDeveloper = developerRepository.findByEmail(systemDeveloperProperties.getSystemDeveloperEmail());
            if (systemDeveloper != null) {
                developerRepository.delete(systemDeveloper);
            }
        }
    }
}
