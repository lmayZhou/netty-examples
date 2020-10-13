package com.lmaye.ms.starter.minio.service;

import java.io.File;
import java.io.InputStream;

/**
 * -- MinIo File Store Service
 *
 * @author lmay.Zhou
 * @date 2020/10/12 19:13 星期一
 * @since Email: lmay_zlm@meten.com
 */
public interface IMinIoFileStoreService {
    /**
     * 创建 Bucket
     *
     * @param bucketName bucket名称
     * @return boolean
     */
    boolean createBucket(String bucketName);

    /**
     * 删除 Bucket
     *
     * @param bucketName bucket名称
     * @return boolean
     */
    boolean deleteBucket(String bucketName);

    /**
     * 文件存储
     *
     * @param file     文件
     * @param fileName 文件名称
     * @return String
     */
    String saveFile(File file, String fileName);

    /**
     * 文件存储
     *
     * @param is       文件流
     * @param fileName 文件名称
     * @return String
     */
    String saveStream(InputStream is, String fileName);

    /**
     * 文件存储
     * - 指定 Bucket
     *
     * @param bucket Bucket
     * @param file   文件
     * @return String
     */
    String saveAssignBucket(String bucket, File file);

    /**
     * 文件存储
     * - 指定 Bucket、文件名
     *
     * @param bucket   Bucket
     * @param file     文件
     * @param fileName 文件名称
     * @return String
     */
    String saveAssignBucket(String bucket, File file, String fileName);

    /**
     * 文件存储
     * - 指定 Bucket、文件流、文件名
     *
     * @param bucket   Bucket
     * @param is       文件流
     * @param fileName 文件名称
     * @return String
     */
    String saveAssignBucket(String bucket, InputStream is, String fileName);

    /**
     * 文件删除
     *
     * @param fileName 文件名称
     * @return boolean
     */
    boolean delete(String fileName);

    /**
     * 文件删除
     * - 指定 Bucket
     *
     * @param bucket   Bucket
     * @param fileName 文件名称
     * @return boolean
     */
    boolean deleteAssignBucket(String bucket, String fileName);

    /**
     * 获取文件流
     *
     * @param fileName 文件名称
     * @return InputStream
     */
    InputStream getStream(String fileName);

    /**
     * 获取文件流
     * - 指定 Bucket
     *
     * @param bucket   Bucket
     * @param fileName 文件名称
     * @return InputStream
     */
    InputStream getStreamAssignBucket(String bucket, String fileName);

    /**
     * 获取文件
     *
     * @param fileName 文件名称
     * @return File
     */
    File getFile(String fileName);

    /**
     * 获取文件
     * - 指定 Bucket
     *
     * @param bucket   Bucket
     * @param fileName 文件名称
     * @return File
     */
    File getFileAssignBucket(String bucket, String fileName);

    /**
     * 文件下载
     *
     * @param fileName 文件名称
     */
    void download(String fileName);

    /**
     * 文件下载
     * - 指定 Bucket
     *
     * @param bucket   Bucket
     * @param fileName 文件名称
     */
    void downloadAssignBucket(String bucket, String fileName);
}
