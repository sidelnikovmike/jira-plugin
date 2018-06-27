package com.propellerads.libs.jira.plugin.results;

import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.plugin.webfragment.contextproviders.AbstractJiraContextProvider;
import com.atlassian.jira.plugin.webfragment.model.JiraHelper;
import com.atlassian.jira.user.ApplicationUser;
import com.propellerads.libs.jira.plugin.results.domain.TestsResultsEntity;
import com.propellerads.libs.jira.plugin.results.services.data.IDataService;
import com.propellerads.libs.jira.plugin.results.services.data.impl.ConsulDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class TestsResultsIndicator extends AbstractJiraContextProvider {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestsResultsIndicator.class);
    private IDataService dataService = new ConsulDataService();

    @Override
    public Map getContextMap(ApplicationUser applicationUser, JiraHelper jiraHelper) {
        Map contextMap = new HashMap();
        Issue currentIssue = (Issue) jiraHelper.getContextParams().get("issue");
        String issueKey = currentIssue.getKey();
        TestsResultsEntity testsResultsEntity = dataService.getTestsResults(issueKey);
        if (testsResultsEntity != null) {
            contextMap = resultsToMap(testsResultsEntity);
        } else {
            LOGGER.info(String.format("Issue with key `%s` not found in storage", issueKey));
            contextMap.put("hasResults", false);
        }
        return contextMap;
    }



    private Map resultsToMap(TestsResultsEntity testsResultsEntity) {
        Map contextMap = new HashMap();
        contextMap.put("hasResults", true);
        contextMap.put("passedCount", testsResultsEntity.getPassedCount());
        contextMap.put("failedCount", testsResultsEntity.getFailedCount());
        contextMap.put("brokenCount", testsResultsEntity.getBrokenCount());
        contextMap.put("skippedCount", testsResultsEntity.getSkippedCount());
        contextMap.put("allureLink", testsResultsEntity.getAllureLink());
        contextMap.put("teamCityLink", testsResultsEntity.getTeamCityLink());
        contextMap.put("time", testsResultsEntity.getTime());
        contextMap.put("isPassed",
                testsResultsEntity.getFailedCount() == 0 &&
                        testsResultsEntity.getBrokenCount() == 0);
        contextMap.put("isFailed",
                testsResultsEntity.getFailedCount() > 0);

        contextMap.put("isBroken",
                testsResultsEntity.getBrokenCount() > 0 &&
                        testsResultsEntity.getPassedCount() == 0 &&
                        testsResultsEntity.getFailedCount() == 0);
        return contextMap;
    }

}
