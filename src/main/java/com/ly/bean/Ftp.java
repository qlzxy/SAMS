package com.ly.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "ftp")
//ConfigurationProperties和Component必须一起使用
    public class Ftp {
    private String hostname;
    private int port;
    private String username;
    private String password;
    private String savePath;
    private String reSavePath;

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
