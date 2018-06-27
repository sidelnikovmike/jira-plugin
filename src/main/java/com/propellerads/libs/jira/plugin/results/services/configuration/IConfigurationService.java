package com.propellerads.libs.jira.plugin.results.services.configuration;

import com.propellerads.libs.jira.plugin.results.domain.JiraProject;

import java.util.List;

/**
 * service to read configuration for plugin
 */
public interface IConfigurationService {

    /**
     * returns list of project on which plugin console will be shown
     *
     * @return list of {@link com.propellerads.libs.jira.plugin.results.domain.JiraProject} or empty list if no data shown
     */
    List<JiraProject> getAvailableProjects();

}
