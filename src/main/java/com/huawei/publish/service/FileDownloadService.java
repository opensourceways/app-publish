package com.huawei.publish.service;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

@Component
public class FileDownloadService {

    /**
     * @param url      downloadUrl
     * @param dir      save path
     * @param fileName file name
     */
    public void downloadHttpUrl(String url, String dir, String fileName) {
        try {
            File dirFile = new File(dir);
            if (!dirFile.exists()) {
                dirFile.mkdirs();
            }
            HttpClient client = new HttpClient();
            GetMethod getMethod = new GetMethod(url);
            client.executeMethod(getMethod);
            InputStream is = getMethod.getResponseBodyAsStream();

            int cache = 10 * 1024;
            FileOutputStream fileOut = new FileOutputStream(dir + "/" + fileName);
            byte[] buffer = new byte[cache];
            int ch = 0;
            while ((ch = is.read(buffer)) != -1) {
                fileOut.write(buffer, 0, ch);
            }
            is.close();
            fileOut.flush();
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
