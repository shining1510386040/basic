package com.demo.springboot.web.mail;

import com.demo.springboot.web.vo.MailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 发送邮件服务
 * @date 2020/12/29 14:17
 * @see
 */
@Service
public class MailServiceImpl implements MailService {
    /**
     * 邮件工具类
     */
    @Autowired
    private JavaMailSenderImpl mailSender;

    @Async
    @Override
    public MailVo sendMail(MailVo mailVo) {
        try {
            //1.检测邮件
            checkMail(mailVo);
            //2.发送邮件
            sendMimeMail(mailVo);
            //3.保存邮件
            return saveMail(mailVo);
        } catch (Exception e) {
            mailVo.setStatus("fail");
            mailVo.setError(e.getMessage());
            return mailVo;
        }
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 检测邮件信息类
     * @date 2020/12/29 14:21
     */
    private void checkMail(MailVo mailVo) {
        if (StringUtils.isEmpty(mailVo.getTo())) {
            throw new RuntimeException("邮件收信人不能为空");
        }
        if (StringUtils.isEmpty(mailVo.getSubject())) {
            throw new RuntimeException("邮件主题不能为空");
        }
        if (StringUtils.isEmpty(mailVo.getText())) {
            throw new RuntimeException("邮件内容不能为空");
        }
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 发送复杂邮件信息
     * @date 2020/12/29 14:22
     */
    private void sendMimeMail(MailVo mailVo) {
        try {
            //true表示支持复杂类型,包含附件的
            MimeMessageHelper messageHelper = new MimeMessageHelper(mailSender.createMimeMessage(), true);
            //mailVo.setFrom(getMailSendFrom());//邮件发信人从配置项读取
            //邮件发信人
            messageHelper.setFrom(mailVo.getFrom());
            //邮件收信人
            messageHelper.setTo(mailVo.getTo().split(","));
            //邮件主题
            messageHelper.setSubject(mailVo.getSubject());
            //邮件内容
            messageHelper.setText(mailVo.getText());
            if (!StringUtils.isEmpty(mailVo.getCc())) {
                //抄送
                messageHelper.setCc(mailVo.getCc().split(","));
            }
            if (!StringUtils.isEmpty(mailVo.getBcc())) {
                //密送
                messageHelper.setCc(mailVo.getBcc().split(","));
            }
            if (mailVo.getMultipartFiles() != null) {
                //添加邮件附件
                for (MultipartFile multipartFile : mailVo.getMultipartFiles()) {
                    messageHelper.addAttachment(multipartFile.getOriginalFilename(), multipartFile);
                }
            }
            if (StringUtils.isEmpty(mailVo.getSentDate())) {
                //发送时间
                mailVo.setSentDate(new Date());
                messageHelper.setSentDate(mailVo.getSentDate());
            }
            mailSender.send(messageHelper.getMimeMessage());
            //正式发送邮件
            mailVo.setStatus("ok");
        } catch (Exception e) {
            //发送失败
            throw new RuntimeException(e);
        }
    }

    //保存邮件
    private MailVo saveMail(MailVo mailVo) {

        //将邮件保存到数据库.. todo

        return mailVo;
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 获取邮件发信人
     * @date 2020/12/29 14:24
     */
    public String getMailSendFrom() {
        return mailSender.getJavaMailProperties().getProperty("from");
    }
}
