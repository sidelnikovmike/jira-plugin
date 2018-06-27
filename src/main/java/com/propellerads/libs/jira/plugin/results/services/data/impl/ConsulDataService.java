package com.propellerads.libs.jira.plugin.results.services.data.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.propellerads.libs.consul.ConsulClient;
import com.propellerads.libs.jira.plugin.results.domain.TestsResultsEntity;
import com.propellerads.libs.jira.plugin.results.services.data.IDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsulDataService implements IDataService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsulDataService.class);
    private static final String CONSUL_ISSUE_TESTS_RESULTS_PATH_PATTERN = "/jira/issues/%s.json";
    private Gson gson;
    private ConsulClient consulClient;

    public ConsulDataService() {
        gson = new GsonBuilder().create();
        consulClient = new ConsulClient().useLoggers(false);
    }


    @Override
    public TestsResultsEntity getTestsResults(String issueId) {
        try {
            String pathToIssue = String.format(CONSUL_ISSUE_TESTS_RESULTS_PATH_PATTERN, issueId);
            String testsResultsData = consulClient.getData(pathToIssue);
            if (testsResultsData != null) {
                return gson.fromJson(testsResultsData, TestsResultsEntity.class);
            }
        } catch (Throwable e) {
            LOGGER.warn("Error while getting data from consul: " + e.getMessage());
            //do nothing
        }
        return null;
    }
}
