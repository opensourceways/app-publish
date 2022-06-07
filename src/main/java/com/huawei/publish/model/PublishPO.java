package com.huawei.publish.model;

import java.util.List;

/**
 * publish main model
 */
public class PublishPO {
    private String gpgKeyUrl;
    private String keyFileName;
    private String rpmKey;
    private String fileKey;
    private String tempDir;
    private String conflict = "skip";//skip/overwrite/error
    List<FilePO> files;
    private List<RepoIndex> repoIndexList;

    public String getGpgKeyUrl() {
        return gpgKeyUrl;
    }

    public void setGpgKeyUrl(String gpgKeyUrl) {
        this.gpgKeyUrl = gpgKeyUrl;
    }

    public String getKeyFileName() {
        return keyFileName;
    }

    public void setKeyFileName(String keyFileName) {
        this.keyFileName = keyFileName;
    }

    public String getRpmKey() {
        return rpmKey;
    }

    public void setRpmKey(String rpmKey) {
        this.rpmKey = rpmKey;
    }

    public String getFileKey() {
        return fileKey;
    }

    public void setFileKey(String fileKey) {
        this.fileKey = fileKey;
    }

    public String getTempDir() {
        return tempDir;
    }

    public void setTempDir(String tempDir) {
        this.tempDir = tempDir;
    }

    public List<FilePO> getFiles() {
        return files;
    }

    public void setFiles(List<FilePO> files) {
        this.files = files;
    }

    public List<RepoIndex> getRepoIndexList() {
        return repoIndexList;
    }

    public void setRepoIndexList(List<RepoIndex> repoIndexList) {
        this.repoIndexList = repoIndexList;
    }

    public String getConflict() {
        return conflict;
    }

    public void setConflict(String conflict) {
        this.conflict = conflict;
    }
}
