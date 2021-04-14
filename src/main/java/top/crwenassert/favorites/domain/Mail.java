package top.crwenassert.favorites.domain;

import lombok.Data;

/**
 * ClassName: Mail
 * Description:
 * date: 2020/7/27 12:29
 *
 * @author crwen
 * @create 2020-07-27-12:29
 * @since JDK 1.8
 */
@Data
public class Mail {
    private String recipient;
    private String subject;
    private String content;

    public Mail(String recipient, String subject, String content) {
        this.recipient = recipient;
        this.subject = subject;
        this.content = content;
    }
}
