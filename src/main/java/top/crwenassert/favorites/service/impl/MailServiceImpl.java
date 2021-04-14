package top.crwenassert.favorites.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import top.crwenassert.favorites.domain.Task;
import top.crwenassert.favorites.service.MailService;
import top.crwenassert.favorites.utils.GenerateNumberUtil;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

/**
 * ClassName: MailServiceImpl
 * Description:
 * date: 2020/7/27 13:14
 *
 * @author crwen
 * @create 2020-07-27-13:14
 * @since JDK 1.8
 */
@Service
@Slf4j
public class MailServiceImpl implements MailService {

    //@Value("${mail.fromMail.receiver}")
    //private String receiver;

    private final String prefix = "<html lang=\"en\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
            "    <title>Document</title>\n" +
            "</head>\n" +
            "<body>\n" +
            "您有以下任务即将超过预期时间或已超过预期时间：" +
            " <table style=\"text-align: center; margin-left: 25%;margin-top: 1%;\" border=\"1\"  cellpadding=\"0\" cellspacing=\"0\">\n" +
            "    <th>任务名称</th>\n" +
            "    <th>预期完成时间</th>";


    private final String subfix = " </table>   \n" +
            "</body>\n" +
            "</html>";

    @Value("${mail.fromMail.sender}")
    private String sender;

    @Resource
    private JavaMailSender javaMailSender;

    @Override
    public String registerMail(String receiver) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper;
        String verifyCode = "";
        try {
            messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setFrom(sender);
            messageHelper.setTo(receiver);
            messageHelper.setSubject("favorite list");
            verifyCode = GenerateNumberUtil.generateVerifyCode();
            String content = "欢迎注册 favorite list</br>验证码：" + verifyCode + "</br>有效时间 15 分钟";
            messageHelper.setText(content, true);
            javaMailSender.send(message);
            log.info("【邮件发送】 邮件已发送", receiver);
        } catch (MessagingException e) {
            log.error("【邮件发送】 邮件发送异常！", e);
        }


        return verifyCode;
    }



    public boolean sendAlarmMail(String receiver, List<Task> taskList) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper;
        StringBuffer content = new StringBuffer();
        content.append(prefix);
        try {
            messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setFrom(sender);
            messageHelper.setTo(receiver);
            messageHelper.setSubject("favorite list");
            for (Task task : taskList) {
                String temp = "    <tr>" +
                        "<td>" + task.getContent() + "</td>" +
                        "<td>" + task.getExpectionTime() + "</td>" +
                        "    </tr>";
                content.append(temp);
            }
            content.append(subfix);
            messageHelper.setSubject("favorite list 任务提醒小助手");
            messageHelper.setText(content.toString(), true);
            javaMailSender.send(message);
            log.info("【邮件发送】 邮件已发送", receiver);
        } catch (MessagingException e) {
            log.error("【邮件发送】 邮件发送异常！", e);
        }

        return true;
    }
}
