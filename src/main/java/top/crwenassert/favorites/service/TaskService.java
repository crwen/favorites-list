package top.crwenassert.favorites.service;

import org.springframework.data.domain.Page;
import top.crwenassert.favorites.domain.Task;
import top.crwenassert.favorites.vo.TaskVO;

import java.util.List;

/**
 * ClassName: TaskService
 * Description:
 * date: 2020/8/31 19:56
 *
 * @author crwen
 * @create 2020-08-31-19:56
 * @since JDK 1.8
 */
public interface TaskService {
    void save(Task task);

    List<TaskVO> getTasksByKindId(Long kindId, int page, int size);
    Page<TaskVO> getHistory(Long userId, int page, int size);
    Task update(TaskVO taskVO);
    Task delete(TaskVO taskVO);
    //Task deleteByName(String names);

    void finished(Long id);

    void delete(Long id);

    List<Task> getByStatusIn(List<Integer> statusList);
    List<Task> getByKinds(List<Long> kindIdList);
}
