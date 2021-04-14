package top.crwenassert.favorites.converter;

import top.crwenassert.favorites.domain.Task;
import top.crwenassert.favorites.vo.TaskVO;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: TaskConvert
 * Description:
 * date: 2020/8/31 18:32
 *
 * @author crwen
 * @create 2020-08-31-18:32
 * @since JDK 1.8
 */
public class TaskConvert {

    public static TaskVO convertToTaskVO(Task task) {
        TaskVO taskVO = new TaskVO();
        taskVO.setContent(task.getContent());
        taskVO.setExpectionTime(DateConverter.convert(task.getExpectionTime()));
        taskVO.setKindId(task.getKindId());
        taskVO.setId(task.getId());
        taskVO.setStatus(task.getStatus());
        taskVO.setFinishTime(DateConverter.convert(task.getFinishTime()));
        return taskVO;
    }

    public static List<TaskVO> convertToTaskVO(List<Task> taskList) {
        List<TaskVO> taskVOList = new ArrayList<>();
        taskList.forEach(task -> {
            taskVOList.add(convertToTaskVO(task));
        });
        return taskVOList;
    }

    public static Task convertToTask(TaskVO taskVO) {
        Task task = new Task();
        task.setContent(taskVO.getContent());
        task.setExpectionTime(DateConverter.convert(taskVO.getExpectionTime()));
        task.setKindId(taskVO.getKindId());
        task.setId(taskVO.getId());
        task.setStatus(taskVO.getStatus());
        task.setFinishTime(DateConverter.convert(taskVO.getFinishTime()));
        return task;
    }

    public static List<Task> convertToTask(List<TaskVO> taskVOList) {
        List<Task> taskList = new ArrayList<>();
        taskVOList.forEach(taskVO -> {
            taskList.add(convertToTask(taskVO));
        });
        return taskList;
    }

}
