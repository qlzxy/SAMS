package com.ly.service;

import com.ly.bean.StudentScoreCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.MessagingException;
import java.io.File;
/**
 * @author qlz小羽 SAMS
 * @create 2020-10-17 21:13
 */
@Service
public class MyUtil {
        public static List listByListHashMap(List<HashMap<String,Object>> listHashMap){
            List list=new ArrayList();
            Map map = new HashMap();
            for(HashMap hashMap :listHashMap){
                Iterator iterator = hashMap.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry entry = (Map.Entry) iterator.next();
                    Object key = entry.getKey();
                    Object value = entry.getValue();
                    map.put(key, value);
                }
                StudentScoreCourse ssc=new StudentScoreCourse();
                ssc.setStudent_id(String.valueOf(map.get("student_id")));
                ssc.setStudent_name(String.valueOf( map.get("student_name")));
                ssc.setYu_wen(String.valueOf(map.get("yu_wen")));
                ssc.setShu_xue(String.valueOf(map.get("shu_xue")));
                ssc.setYing_yu(String.valueOf(map.get("ying_yu")));
                ssc.setWu_li(String.valueOf(map.get("wu_li")));
                ssc.setHua_xue(String.valueOf(map.get("hua_xue")));
                ssc.setSheng_wu(String.valueOf(map.get("sheng_wu")));
                ssc.setZheng_zhi(String.valueOf(map.get("zheng_zhi")));
                ssc.setLi_shi(String.valueOf(map.get("li_shi")));
                ssc.setDi_li(String.valueOf(map.get("di_li")));
                ssc.setSum_score(String.valueOf(map.get("sum_score")));
                ssc.setRank_score(String.valueOf(map.get("rank_score")));
                list.add(ssc);
            }
            return list;
        }
        private final Logger logger = LoggerFactory.getLogger(this.getClass());
        @Value("${spring.mail.username}")
        private String from;
        @Autowired
        private JavaMailSender mailSender;
        /**
         * 简单文本邮件
         * @param to 接收者邮件
         * @param subject 邮件主题
         * @param contnet 邮件内容
         */
        public void sendSimpleMail(String to, String subject, String contnet){
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(contnet);
            message.setFrom(from);
            mailSender.send(message);
        }

        /**
         * HTML 文本邮件
         * @param to 接收者邮件
         * @param subject 邮件主题
         * @param contnet HTML内容
         * @throws MessagingException
         */
        public void sendHtmlMail(String to, String subject, String contnet) throws MessagingException {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(contnet, true);
            helper.setFrom(from);
            mailSender.send(message);
        }

        /**
         * 附件邮件
         * @param to 接收者邮件
         * @param subject 邮件主题
         * @param contnet HTML内容
         * @param filePath 附件路径
         * @throws MessagingException
         */
        public void sendAttachmentsMail(String to, String subject, String contnet,
                                        String filePath) throws MessagingException {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(contnet, true);
            helper.setFrom(from);
            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = file.getFilename();
            helper.addAttachment(fileName, file);
            mailSender.send(message);
        }
        /**
         * 图片邮件
         * @param to 接收者邮件
         * @param subject 邮件主题
         * @param contnet HTML内容
         * @param rscPath 图片路径
         * @param rscId 图片ID
         * @throws MessagingException
         */
        public void sendInlinkResourceMail(String to, String subject, String contnet,
                                           String rscPath, String rscId) {
            logger.info("发送静态邮件开始: {},{},{},{},{}", to, subject, contnet, rscPath, rscId);

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = null;
            try {
                helper = new MimeMessageHelper(message, true);
                helper.setTo(to);
                helper.setSubject(subject);
                helper.setText(contnet, true);
                helper.setFrom(from);
                FileSystemResource res = new FileSystemResource(new File(rscPath));
                helper.addInline(rscId, res);
                mailSender.send(message);
                logger.info("发送静态邮件成功!");
            } catch (MessagingException e) {
                logger.info("发送静态邮件失败: ", e);
            }
        }

}
