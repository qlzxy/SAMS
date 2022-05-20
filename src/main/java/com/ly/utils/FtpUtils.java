package com.ly.utils;

import com.ly.bean.Ftp;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.swing.*;
import java.io.*;

/**
 * @author qlz小羽 YFileSystem
 * @create 2022-02-09 16:31
 */
@Component
public class FtpUtils{

    @Autowired(required = false)
    private Ftp ftp;

    private static Ftp ftpBean;

    @PostConstruct
    public  void init() {
        ftpBean = ftp;
        System.out.println("初始化完成");
    }


    // 连接ftp
    public static FTPClient getConnection() {
        FTPClient ftpClient = new FTPClient();
        try {
            // 设置连接机器
            ftpClient.connect(ftpBean.getHostname(), ftpBean.getPort());
            ftpClient.login(ftpBean.getUsername(), ftpBean.getPassword());
            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                ftpClient.disconnect(); // 断开连接
                return null;
            } else {
                System.out.println("ftp连接成功");
            }

            // 将文件类型设置成二进制
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            // 创建要存储文件夹的目录: 主要目录只能一级一级创建，不能一次创建多层; 在 选择创建目录是一定要看是否有写权限，不然失败
            ftpClient.makeDirectory(ftpBean.getSavePath());
            // 改变默认存放的位置
            ftpClient.changeWorkingDirectory(ftpBean.getSavePath());
            //开启被动模式，否则文件上传不成功，也不报错
            ftpClient.enterLocalPassiveMode();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
        return ftpClient;
    }


    // 上传文件
    public static void uploadFile(String fileName, InputStream inputStream) {

        FTPClient ftpClient = getConnection();
        if (ftpClient == null) {
            return;
        }
        try {
            boolean result = ftpClient.storeFile(fileName, inputStream);
            System.out.println("文件保存：" + ( result ? "成功" : "失败"));
            inputStream.close();
            ftpClient.logout();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 文件下载

    /**
     *
     * @param fileName
     * @param localPath  不指定表示下载到当前项目下
     */
    public static void downloadFile(String localPath, String fileName) {
        FTPClient ftpClient = getConnection();
        if (ftpClient == null) {
            return;
        }
        try {
            FTPFile[] ftpFiles = ftpClient.listFiles();
            for (FTPFile ftpFile : ftpFiles) {
                if (fileName.equals(ftpFile.getName())) {
                    File file = new File(localPath+fileName);
                    OutputStream outputStream = new FileOutputStream(file);
                    boolean result = ftpClient.retrieveFile(ftpFile.getName(), outputStream);
                    System.out.println("下载结果：" + result);
                    outputStream.close();
                }
            }
            ftpClient.logout();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
