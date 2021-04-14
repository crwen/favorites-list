package top.crwenassert.favorites.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import top.crwenassert.favorites.domain.Task;
import top.crwenassert.favorites.repository.TaskRepository;
import top.crwenassert.favorites.service.TaskService;
import top.crwenassert.favorites.vo.TaskVO;

import java.util.Arrays;

/**
 * ClassName: TaskServiceImplTest
 * Description:
 * date: 2020/9/1 20:27
 *
 * @author crwen
 * @create 2020-09-01-20:27
 * @since JDK 1.8
 */
@SpringBootTest
class TaskServiceImplTest {
    @Autowired
    private TaskService taskService;
    @Autowired
    private TaskRepository taskRepository;
    @Test
    void getHistory() {
        Page<TaskVO> history = taskService.getHistory(1L, 0, 1);
        System.out.println(history);
        Page<Task> taskPage = taskRepository.getByStatusAndKindIdIn(3, Arrays.asList(1L, 2L), PageRequest.of(0, 2));
        System.out.println(taskPage.getContent());
        System.out.println(taskPage.getTotalPages());
        System.out.println(taskPage.getTotalElements());
    }
}