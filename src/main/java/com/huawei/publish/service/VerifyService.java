package com.huawei.publish.service;

import com.huawei.publish.model.PublishPO;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * verify service
 */
public class VerifyService {
    private static Logger log = Logger.getLogger(VerifyService.class);
    private String gpgKeyUrl;
    private String keyFileName;
    private String rpmKey;
    private String fileKey;

    public VerifyService(String gpgKeyUrl, String keyFileName, String rpmKey, String fileKey) {
        this.gpgKeyUrl = gpgKeyUrl;
        this.keyFileName = keyFileName;
        this.rpmKey = rpmKey;
        this.fileKey = fileKey;
    }

    public VerifyService(PublishPO publishPO) {
        this.gpgKeyUrl = publishPO.getGpgKeyUrl();
        this.keyFileName = publishPO.getKeyFileName();
        this.rpmKey = publishPO.getRpmKey();
        this.fileKey = publishPO.getFileKey();
    }

    /**
     * @param cmd cmd
     * @return output
     * @throws IOException
     * @throws InterruptedException
     */
    public String execCmd(String cmd) throws IOException, InterruptedException {
        log.info("cmd:" + cmd);
        Runtime runtime = Runtime.getRuntime();
        Process exec = runtime.exec(cmd);
        exec.waitFor();
        String output = getExecOutput(exec);
        log.info("output:" + output);
        return output;
    }

    /**
     * verify rpm files
     *
     * @param filePath rpm file path
     * @return success
     */
    public boolean rpmVerify(String filePath) throws IOException, InterruptedException {
        if (!execCmd("rpm -q gpg-pubkey-*").contains(rpmKey)) {
            execCmd("wget " + gpgKeyUrl);
            execCmd("rpm --import " + keyFileName);
        }
        String output = execCmd("rpm -K " + filePath);
        return output.contains("OK") && !output.contains("not");
    }

    /**
     * verify sha256
     *
     * @param filePath file path
     * @param sha256   sha256
     * @return
     */
    public boolean checksum256Verify(String filePath, String sha256) {
        try {
            return execCmd("sha256sum " + filePath).contains(sha256);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return false;
    }

    /**
     * verify asc file
     *
     * @param filePath asc file path
     * @return success
     */
    public boolean fileVerify(String filePath) {
        try {
            if (!execCmd("gpg -k | grep " + fileKey).contains(fileKey)) {
                execCmd("wget " + gpgKeyUrl);
                execCmd("gpg --import " + keyFileName);
            }
            return execCmd("gpg --verify " + filePath).contains("Primary key fingerprint");
        } catch (Exception e) {
            log.error("rpm verify error,file:" + filePath + " error:" + e.getMessage());
        }
        return false;
    }

    private String getExecOutput(Process exec) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
        BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(exec.getErrorStream()));
        StringBuilder sb = new StringBuilder();
        try {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            while ((line = bufferedReader2.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return sb + "";
    }
}
