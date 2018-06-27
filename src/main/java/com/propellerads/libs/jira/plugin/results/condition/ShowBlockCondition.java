package com.propellerads.libs.jira.plugin.results.condition;

import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.plugin.webfragment.conditions.AbstractIssueWebCondition;
import com.atlassian.jira.plugin.webfragment.model.JiraHelper;
import com.atlassian.jira.project.Project;
import com.atlassian.jira.user.ApplicationUser;
import com.propellerads.libs.jira.plugin.results.domain.JiraProject;
import com.propellerads.libs.jira.plugin.results.services.configuration.IConfigurationService;
import com.propellerads.libs.jira.plugin.results.services.configuration.impl.ConsulConfigurationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ShowBlockCondition  extends AbstractIssueWebCondition {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShowBlockCondition.class);
    private IConfigurationService configurationService;

    public ShowBlockCondition() {
        configurationService = new ConsulConfigurationService();
    }

    /**
     * if flag is false - block will be displayed
     */
    @Override
    public boolean shouldDisplay(ApplicationUser applicationUser, Issue issue, JiraHelper jiraHelper) {
        String projectKey = getProjectKey(issue);
        if (projectKey != null) {
            LOGGER.info(String.format("Project key is `%s`", projectKey));
            if (isApplicableProjectKey(projectKey)) {
                LOGGER.info("Project key is OK. Block will be shown");
                return false;
            }
            return true;
        } else {
            LOGGER.warn("No project key was found in condition. Panel will be shown by default.");
            return false;
        }
    }

    private boolean isApplicableProjectKey(String projectKey) {
        for (JiraProject jiraProject : configurationService.getAvailableProjects()) {
            if (jiraProject.getKey().equalsIgnoreCase(projectKey)) {
                return true;
            }
        }
        return false;
    }

    private String getProjectKey(Issue issue) {
        Project project = issue.getProjectObject();
        if (project != null) {
            return project.getKey();
        }
        return null;
    }


}
