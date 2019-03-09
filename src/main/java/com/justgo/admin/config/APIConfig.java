package com.justgo.admin.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * API配置文件
 */
@Component
@ConfigurationProperties(prefix = "justgo.admin")
public class APIConfig {
    /**
     * 轮播图上传路径
     */
    private static String bannerUploadURL;

    /**
     * 上传临时文件夹
     */
    private static String uploadTemp;

    /**
     * 图片服务器地址
     */
    private static String imageServerURL;

    /**
     * 后台图片上传根路径
     */
    private static String ImgUploadRootPath;

    /**
     * 获取  轮播图上传路径
     *
     * @return 轮播图上传路径
     */
    public static String getBannerUploadURL() {
        return APIConfig.bannerUploadURL;
    }

    /**
     * 设置  轮播图上传路径
     *
     * @param bannerUploadURL 轮播图上传路径
     */
    public void setBannerUploadURL(String bannerUploadURL) {
        APIConfig.bannerUploadURL = bannerUploadURL;
    }

    /**
     * 获取  上传临时文件夹
     *
     * @return 上传临时文件夹
     */
    public static String getUploadTemp() {
        return APIConfig.uploadTemp;
    }

    /**
     * 设置  上传临时文件夹
     *
     * @param uploadTemp 上传临时文件夹
     */
    public void setUploadTemp(String uploadTemp) {
        APIConfig.uploadTemp = uploadTemp;
    }

    /**
     * 获取  图片服务器地址
     *
     * @return 图片服务器地址
     */
    public static String getImageServerURL() {
        return APIConfig.imageServerURL;
    }

    /**
     * 设置  图片服务器地址
     *
     * @param imageServerURL 图片服务器地址
     */
    public void setImageServerURL(String imageServerURL) {
        APIConfig.imageServerURL = imageServerURL;
    }

    public static String getImgUploadRootPath() {
        return ImgUploadRootPath;
    }

    public void setImgUploadRootPath(String imgUploadRootPath) {
        ImgUploadRootPath = imgUploadRootPath;
    }
}
