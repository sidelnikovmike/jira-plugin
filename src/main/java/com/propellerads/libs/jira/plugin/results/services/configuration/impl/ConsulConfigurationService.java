package com.propellerads.libs.jira.plugin.results.services.configuration.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.propellerads.libs.consul.ConsulClient;
import com.propellerads.libs.jira.plugin.results.domain.JiraProject;
import com.propellerads.libs.jira.plugin.results.services.configuration.IConfigurationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

/**
 * implementation to read configuration from consul
 */
public class ConsulConfigurationService implements IConfigurationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsulConfigurationService.class);
    private static final String PROJECTS_LIST_PATH = "/jira/config/projects.json";
    private ConsulClient consulClient;
    private Gson gson;

    public ConsulConfigurationService() {
        consulClient = new ConsulClient().useLoggers(false);
        gson = new GsonBuilder().create();
    }

    @Override
    public List<JiraProject> getAvailableProjects() {
        try {
            String projectsData = consulClient.getData(PROJECTS_LIST_PATH);
            if (projectsData != null) {
                return gson.fromJson(projectsData, new TypeToken<List<JiraProject>>() {
                }.getType());
            }
        } catch (Throwable e) {
            LOGGER.warn("Error while getting data from consul: " + e.getMessage());
            //do nothing
        }
        return Collections.emptyList();
    }
}
