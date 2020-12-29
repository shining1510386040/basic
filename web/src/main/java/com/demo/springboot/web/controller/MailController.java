package com.demo.springboot.web.controller;

import com.demo.springboot.web.mail.MailService;
import com.demo.springboot.web.vo.MailVo;
import com.demo.springboot.web.vo.ServiceResult;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 发送邮件
 * @date 2020/12/29 14:25
 * @see
 */
@Controller
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private MailService mailService;

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 场景：输入评论text，发送邮件
     * @date 2020/12/29 14:40
     */
    @PostMapping("/send")
    @ResponseBody
    public ServiceResult comment(@RequestParam String author, @RequestParam String mail,
                                 @RequestParam String url, @RequestParam String text) {

        if (StringUtils.isBlank(text)) {
            return new ServiceResult("500", "请输入邮件内容");
        }

        if (StringUtils.isBlank(author) && author.length() > 50) {
            return new ServiceResult("500", "姓名过长");
        }

        if (StringUtils.isBlank(mail)) {
            // todo 校验邮箱格式正则
            return new ServiceResult("500", "邮箱格式不正确");
        }

        if (StringUtils.isBlank(url)) {
            // todo 校验url格式
            return new ServiceResult("500", "URL格式不正确");
        }

        if (text.length() > 200) {
            return new ServiceResult("500", "评论过长");
        }

        // todo 保存评论到db
        //发送邮件
        MailVo mailVo = new MailVo();
        // 发件人
        mailVo.setFrom(mail);
        // 收件人
        mailVo.setTo("huifeidezhu_12138@163.com");
        // 主题
        mailVo.setSubject("收到您朋友【" + author + "】的信件,Ta的邮箱:" + mail);
        // 内容
        mailVo.setText(text);
        MailVo ret = mailService.sendMail(mailVo);
        if (ret.getStatus().equals("fail")) {
            return new ServiceResult("500", ret.getError());
        } else {
            return new ServiceResult("200", "评论成功");
        }
    }

}
