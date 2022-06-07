package com.huawei.publish.model;

/**
 * repo index operation model
 */
public class RepoIndex {
    private String repoPath;

    private String indexType;

    public String getRepoPath() {
        return repoPath;
    }

    public void setRepoPath(String repoPath) {
        this.repoPath = repoPath;
    }

    public String getIndexType() {
        return indexType;
    }

    public void setIndexType(String indexType) {
        this.indexType = indexType;
    }
}
