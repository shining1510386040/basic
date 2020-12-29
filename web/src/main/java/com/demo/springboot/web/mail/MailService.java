package com.demo.springboot.web.mail;

import com.demo.springboot.web.vo.MailVo;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 邮件服务接口
 * @date 2020/12/29 14:18
 * @see
 */
public interface MailService {
    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 发送邮件
     * @date 2020/12/29 14:20
     */
    MailVo sendMail(MailVo mailVo);
}
