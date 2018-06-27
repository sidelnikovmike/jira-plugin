package com.propellerads.libs.jira.plugin.results.services.data;

import com.propellerads.libs.jira.plugin.results.domain.TestsResultsEntity;

public interface IDataService {

    TestsResultsEntity getTestsResults(String issueId);

}
