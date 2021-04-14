package top.crwenassert.favorites.service.timer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.crwenassert.favorites.domain.Kinds;
import top.crwenassert.favorites.domain.Task;
import top.crwenassert.favorites.domain.User;
import top.crwenassert.favorites.eumn.TaskEnum;
import top.crwenassert.favorites.service.KindsService;
import top.crwenassert.favorites.service.MailService;
import top.crwenassert.favorites.service.TaskService;
import top.crwenassert.favorites.service.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ClassName: UpdateStatusService
 * Description:
 * date: 2020/9/2 12:39
 *
 * @author crwen
 * @create 2020-09-02-12:39
 * @since JDK 1.8
 */
@Component
@Slf4j
public class ScheduleService {

    @Autowired
    private TaskService taskService;
    @Autowired
    private MailService mailService;
    @Autowired
    private UserService userService;
    @Autowired
    private KindsService kindsService;

    ExecutorService executorService = Executors.newFixedThreadPool(10);

    /**
     * 定时计算。每天凌晨 01:00 执行一次
     */
    @Async
    @Scheduled(cron = "0 0 1 * * *")
    public void updateTask() {
        // 构造任务状态列表
        List<Integer> statusList = Arrays.asList(TaskEnum.NO_ALARM.getStatus(),
                TaskEnum.NORMAL.getStatus());
        // 找到对应状态的任务
        List<Task> taskList = taskService.getByStatusIn(statusList);
        // 更新
        if (taskList == null)
            return;

        Calendar cal = Calendar.getInstance();
        long now = cal.getTimeInMillis();
        for (Task task : taskList) {
            long expection = task.getExpectionTime().getTime();
            if (expection <= now) {
                // 更新
                //task.setStatus(TaskEnum.ALARM.getStatus());
                System.out.println(task);
            }

        }
    }

    @Async
    @Scheduled(cron = "0 0 7 * * *")
    //@Scheduled(fixedRate = 1000*2000)
    public void sendMail() {
        Calendar cal = Calendar.getInstance();
        //long now = cal.getTimeInMillis();
        cal.add(Calendar.DAY_OF_YEAR, 1);
        long day = cal.getTimeInMillis();
        List<User> userList = userService.getAll();

        for (User user : userList) {
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    sendMail(user, day);
                }
            });
        }
    }

    public void sendMail(User user, long time) {

        List<Kinds> kindsList = kindsService.getKindsByUser(user.getId());
        List<Long> kindsIds = new ArrayList<>();
        kindsList.forEach(kinds -> kindsIds.add(kinds.getId()));
        List<Task> taskList = taskService.getByKinds(kindsIds);

        List<Task> res = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getExpectionTime().getTime() < time) {
                res.add(task);
            }
        }
        if (res.size() > 0) {
            String email = user.getEmail();
            mailService.sendAlarmMail(email, res);
            log.info("【任务超时提醒】 " + user.getUsername());
        }

        //executorService.submit(new Runnable() {
        //    @Override
        //    public void run() {
        //        List<Task> res = new ArrayList<>();
        //        for (Task task : taskList) {
        //            if (task.getExpectionTime().getTime() < time) {
        //                res.add(task);
        //            }
        //        }
        //        if (res.size() > 0) {
        //            String email = user.getEmail();
        //            mailService.sendAlarmMail(email, res);
        //            log.info("【任务超时提醒】 " + user.getUsername());
        //        }
            //}
        //});



    }

    //public void sendMail() {
    //    Map<Long, List<Task>> map = new HashMap<>();
    //    List<Task> taskList = taskService.getByStatusIn(Arrays.asList(TaskEnum.ALARM.getStatus()));
    //    // 更新
    //    if (taskList == null)
    //        return;
    //
    //    Calendar cal = Calendar.getInstance();
    //    long now = cal.getTimeInMillis();
    //    cal.add(Calendar.DAY_OF_YEAR, 1);
    //    long day = cal.getTimeInMillis();
    //
    //    List<Long> kindsIdList = new ArrayList<>();
    //    for (Task task : taskList) {
    //        long expection = task.getExpectionTime().getTime();
    //        if (expection <= now + day) {
    //            kindsIdList.add(task.getKindId());
    //            //mailService.sendAlarmMail(task.get)
    //        }
    //
    //    }
    //
    //    List<Kinds> kindsList = kindsService.getByIds(kindsIdList);
    //    List<Long> userIdList = new ArrayList<>();
    //    if (kindsIdList != null) {
    //        for (Kinds kinds : kindsList) {
    //            userIdList.add(kinds.getUserId());
    //        }
    //    }
    //
    //    for (Long userId : userIdList) {
    //        User user = userService.getUserById(userId);
    //        String email = user.getEmail();
    //
    //        List<Kinds> kinds = kindsService.getKindsByUser(userId);
    //        List<Long> kindsIds =new ArrayList<>();
    //        kinds.forEach(kinds1 -> kindsIds.add(kinds1.getId()));
    //
    //        //mailService.sendAlarmMail(email, )
    //    }
    //
    //}

    /**
     * 启动时执行一次，之后每隔2秒执行一次
     */
    //@Async
    //@Scheduled(fixedRate = 1000*2)
    //public void print() {
    //    System.out.println("print method 2");
    //}
}
