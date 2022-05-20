package com.ly.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author qlz小羽 YFileSystem
 * @create 2022-02-09 16:29
 */
@Component
@ConfigurationProperties(prefix = "ftp")//ConfigurationProperties和Component必须一起使用
    public class Ftp {
    private String hostname; // 机器的ip
    private int port; // 端口号：21
    private String username; // 账号
    private String password; // 密码
    private String savePath; // userfile存储的位置
    private String reSavePath; // 匿名访问存储的位置

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public String getReSavePath() {
        return reSavePath;
    }

    public void setReSavePath(String reSavePath) {
        this.reSavePath = reSavePath;
    }
}
