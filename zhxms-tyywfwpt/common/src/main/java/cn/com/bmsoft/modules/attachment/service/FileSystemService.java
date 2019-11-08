package cn.com.bmsoft.modules.attachment.service;

import java.io.File;
import java.io.InputStream;
import java.util.Map;

public interface FileSystemService {

    /**
     *
     * @param fileName
     * @param inputStream
     * @return
     * @throws Exception
     */
    Map<String, Object> uploadFile(String fileName, InputStream inputStream) throws Exception;

    /**
     *
     * @param fileName
     * @param file
     * @return
     * @throws Exception
     */
    Map<String, Object> uploadFile(String fileName, File file) throws Exception;

    /**
     *
     * @param targetPath
     * @return
     * @throws Exception
     */
    File downloadFile(String targetPath) throws Exception;

    /**
     *
     * @param targetPath
     * @return
     * @throws Exception
     */
    boolean deleteFile(String targetPath) throws Exception;

}
