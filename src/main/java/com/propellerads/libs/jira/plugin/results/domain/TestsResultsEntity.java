package com.propellerads.libs.jira.plugin.results.domain;

public class TestsResultsEntity {
    private int passedCount;
    private int failedCount;
    private int brokenCount;
    private int skippedCount;
    private String allureLink;
    private String teamCityLink;
    private String time;

    public int getPassedCount() {
        return passedCount;
    }

    public void setPassedCount(int passedCount) {
        this.passedCount = passedCount;
    }

    public int getFailedCount() {
        return failedCount;
    }

    public void setFailedCount(int failedCount) {
        this.failedCount = failedCount;
    }

    public int getBrokenCount() {
        return brokenCount;
    }

    public void setBrokenCount(int brokenCount) {
        this.brokenCount = brokenCount;
    }

    public int getSkippedCount() {
        return skippedCount;
    }

    public void setSkippedCount(int skippedCount) {
        this.skippedCount = skippedCount;
    }

    public String getAllureLink() {
        return allureLink;
    }

    public void setAllureLink(String allureLink) {
        this.allureLink = allureLink;
    }

    public String getTeamCityLink() {
        return teamCityLink;
    }

    public void setTeamCityLink(String teamCityLink) {
        this.teamCityLink = teamCityLink;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public TestsResultsEntity withPassedCount(int passedCount) {
        this.passedCount = passedCount;
        return this;
    }

    public TestsResultsEntity withFailedCount(int failedCount) {
        this.failedCount = failedCount;
        return this;
    }

    public TestsResultsEntity withBrokenCount(int brokenCount) {
        this.brokenCount = brokenCount;
        return this;
    }

    public TestsResultsEntity withSkippedCount(int skippedCount) {
        this.skippedCount = skippedCount;
        return this;
    }

    public TestsResultsEntity withAllureLink(String allureLink) {
        this.allureLink = allureLink;
        return this;
    }

    public TestsResultsEntity withTeamCityLink(String teamCityLink) {
        this.teamCityLink = teamCityLink;
        return this;
    }

    public TestsResultsEntity withTime(String time) {
        this.time = time;
        return this;
    }


}
