package top.crwenassert.favorites.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import top.crwenassert.favorites.converter.TaskConvert;
import top.crwenassert.favorites.domain.Task;
import top.crwenassert.favorites.domain.User;
import top.crwenassert.favorites.service.TaskService;
import top.crwenassert.favorites.service.UserService;
import top.crwenassert.favorites.utils.ResultVOUtil;
import top.crwenassert.favorites.vo.Pagination;
import top.crwenassert.favorites.vo.ResultVO;
import top.crwenassert.favorites.vo.TaskVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * ClassName: TaskController
 * Description:
 * date: 2020/8/31 18:09
 *
 * @author crwen
 * @create 2020-08-31-18:09
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/task")
@Slf4j
public class TaskController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;


    @GetMapping("/list/{id}")
    public ResultVO<List<TaskVO>> listTasks(@PathVariable("id") Long id,
                                            @RequestParam(value = "page", defaultValue = "1") Integer page,
                                            @RequestParam(value = "size", defaultValue = "10") Integer size) {

        List<TaskVO> taskVOList = taskService.getTasksByKindId(id, page - 1, size);

        return ResultVOUtil.success(taskVOList);
    }

    @PostMapping("/add")
    public ResultVO addTask(TaskVO taskVO) {
        Task task = TaskConvert.convertToTask(taskVO);
        //System.out.println(task);
        taskService.save(task);
        return ResultVOUtil.success();
    }

    @GetMapping("/history")
    public ResultVO<List<TaskVO>> history(HttpServletRequest request,
                                          @RequestParam(value = "page", defaultValue = "1") Integer page,
                                          @RequestParam(value = "size", defaultValue = "10") Integer size) {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("user");
        User user = userService.getUserByUsername(username);
        if (user == null) {
            log.warn("【查看历史】 用户未登录");
            return ResultVOUtil.loginError("用户未登录");
        }
        Page<TaskVO> history = taskService.getHistory(user.getId(), page - 1, size);
        return ResultVOUtil.success(history.getContent(),
                new Pagination(history.getTotalPages(), size, history.getTotalElements()));
    }

    @PostMapping("/finished")
    public ResultVO finished(HttpServletRequest request, Long id) {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("user");
        User user = userService.getUserByUsername(username);
        if (user == null) {
            log.warn("【查看历史】 用户未登录");
            return ResultVOUtil.loginError("用户未登录");
        }
        taskService.finished(id);
        return ResultVOUtil.success();
    }

    @PostMapping("/delete")
    public ResultVO delete(HttpServletRequest request, Long id) {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("user");
        User user = userService.getUserByUsername(username);
        if (user == null) {
            log.warn("【查看历史】 用户未登录");
            return ResultVOUtil.loginError("用户未登录");
        }
        taskService.delete(id);
        return ResultVOUtil.success();
    }

}
