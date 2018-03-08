package com.gy.pdf;
import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;

import com.hg.xdoc.XDocViewer;  
  
public class Test {  
    /** 
     * XDOC阅读器测试 
     * @param args 
     */  
    public static void main(String[] args) {  
        try {  
            JFrame f = new JFrame("XDOC文档阅读器");  
            Container p = f.getContentPane();  
            //实例化XDoc阅读器  
            XDocViewer v = new XDocViewer();  
            //加入到面板中  
            p.add(v, BorderLayout.CENTER);  
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
            f.setSize(800, 600);  
            f.setVisible(true);  
            f.setExtendedState(JFrame.MAXIMIZED_BOTH);  
            //指定url打开文件  
            v.open("D:\\favoritePackage\\hana语法详解.pdf","");  
 /*           FtpUseBean ftpUseBean = new FtpUseBean();
            ftpUseBean.setHost("192.168.0.104");
            ftpUseBean.setPort(21);
            ftpUseBean.setUserName("fttpUser");
            ftpUseBean.setPassword("ftp123456_");
            ftpUseBean.setFtpPath("wq\\49c430bba2bc8383d7f58d697f3ea4d1.jpg");
            FtpUtil ftpUtil = new FtpUtil();
            ftpUtil.setFtpUseBean(ftpUseBean);
            boolean ftpLogin = ftpUtil.ftpLogin();
//            ftpUtil.setFtpToUtf8();
            if(ftpLogin){
            	String ftpurl = ftpUseBean.getFTPURL();
            	InputStream downloadBufferByURL = ftpUtil.downloadBufferByURL(ftpurl);
            	File file = new File("F:\\11111111111111111.jpg");
            	FTPFile[] listFtpFiles = ftpUtil.listFtpFiles();
            	InputStream is = ftpUtil.downFtpFile("49c430bba2bc8383d7f58d697f3ea4d1.jpg");
//            	ftpUtil.close();
            }*/
//            FTPClient ftpClient = new FTPClient();
//            ftpClient.connect("192.168.0.104", 21);
//            //登录FTP服务器
//            ftpClient.login("fttpUser", "ftp123456_");
//            //验证FTP服务器是否登录成功
//            int replyCode = ftpClient.getReplyCode();
//            //切换FTP目录
//            ftpClient.changeWorkingDirectory("wq\\");
//            File localFile = new File("F:\\11111111111111111.jpg");
//            OutputStream os = new FileOutputStream(localFile);
////            ftpClient.retrieveFile("49c430bba2bc8383d7f58d697f3ea4d1.jpg", os);
//            InputStream retrieveFileStream = ftpClient.retrieveFileStream("1.txt");
//            
//            v.open(retrieveFileStream);
//            v.open("F:\\11111111111111111.jpg");
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
     
}  