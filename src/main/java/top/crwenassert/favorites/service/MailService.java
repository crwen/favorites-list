package top.crwenassert.favorites.service;

import top.crwenassert.favorites.domain.Task;

import java.util.List;

/**
 * ClassName: MailService
 * Description:
 * date: 2020/7/27 13:14
 *
 * @author crwen
 * @create 2020-07-27-13:14
 * @since JDK 1.8
 */
public interface MailService {

    String registerMail(String receiver);

    boolean sendAlarmMail(String receiver, List<Task> taskList);
}
