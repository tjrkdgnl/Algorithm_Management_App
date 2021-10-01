package com.jeoksyeo.algorithmmanagement.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stats {

    @SerializedName("problemCount")
    @Expose
    private Integer problemCount;
    @SerializedName("problemVotedCount")
    @Expose
    private Integer problemVotedCount;
    @SerializedName("userCount")
    @Expose
    private Integer userCount;
    @SerializedName("contributorCount")
    @Expose
    private Integer contributorCount;
    @SerializedName("contributionCount")
    @Expose
    private Integer contributionCount;

    public Integer getProblemCount() {
        return problemCount;
    }

    public void setProblemCount(Integer problemCount) {
        this.problemCount = problemCount;
    }

    public Integer getProblemVotedCount() {
        return problemVotedCount;
    }

    public void setProblemVotedCount(Integer problemVotedCount) {
        this.problemVotedCount = problemVotedCount;
    }

    public Integer getUserCount() {
        return userCount;
    }

    public void setUserCount(Integer userCount) {
        this.userCount = userCount;
    }

    public Integer getContributorCount() {
        return contributorCount;
    }

    public void setContributorCount(Integer contributorCount) {
        this.contributorCount = contributorCount;
    }

    public Integer getContributionCount() {
        return contributionCount;
    }

    public void setContributionCount(Integer contributionCount) {
        this.contributionCount = contributionCount;
    }
}