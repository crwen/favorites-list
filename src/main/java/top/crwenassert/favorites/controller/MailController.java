package top.crwenassert.favorites.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.crwenassert.favorites.utils.ResultVOUtil;
import top.crwenassert.favorites.vo.ResultVO;

import javax.annotation.Resource;

/**
 * ClassName: MailController
 * Description:
 * date: 2020/7/27 12:03
 *
 * @author crwen
 * @create 2020-07-27-12:03
 * @since JDK 1.8
 */
@RestController
@Slf4j
public class MailController {

    @Value("${mail.fromMail.sender}")
    private String sender;

    @Value("${mail.fromMail.receiver}")
    private String receiver;


    @Resource
    private JavaMailSender javaMailSender;

    @RequestMapping("/semdMail")
    public ResultVO sendMail() {
        System.out.println(sender);
        System.out.println(receiver);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(receiver);
        message.setSubject("subject");
        message.setText("你好");
        try {
            javaMailSender.send(message);
            log.info("【邮件发送】", sender, receiver);
        } catch (Exception e) {
            log.error("【邮件发送】 发生异常", e);
        }

        return ResultVOUtil.success();
    }

}
