package top.crwenassert.favorites.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import top.crwenassert.favorites.converter.DateConverter;
import top.crwenassert.favorites.converter.TaskConvert;
import top.crwenassert.favorites.domain.Kinds;
import top.crwenassert.favorites.domain.Task;
import top.crwenassert.favorites.eumn.TaskEnum;
import top.crwenassert.favorites.repository.KindsRepository;
import top.crwenassert.favorites.repository.TaskRepository;
import top.crwenassert.favorites.service.TaskService;
import top.crwenassert.favorites.vo.TaskVO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ClassName: TaskServiceImpl
 * Description:
 * date: 2020/8/31 19:57
 *
 * @author crwen
 * @create 2020-08-31-19:57
 * @since JDK 1.8
 */
@Service
@Slf4j
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private KindsRepository kindsRepository;

    @Override
    public void save(Task task) {
      if (task == null) {
          log.warn("【新增任务】 任务为空");
      } else {
          if (task.getExpectionTime().getTime() < System.currentTimeMillis()) {
              task.setStatus(TaskEnum.ALARM.getStatus());
          }
          task.setCreateTime(new Date());
          task.setUpdateTime(task.getCreateTime());
          taskRepository.save(task);
          log.info("【新增任务】 " + task.getContent());
      }
    }

    @Override
    public List<TaskVO> getTasksByKindId(Long kindId, int page, int size) {
        // 获取数据
        Pageable pageable = PageRequest.of(page, size);
        Page<Task> taskPage = taskRepository.getByKindId(kindId, pageable);
        // 转换
        List<TaskVO> taskVOList = TaskConvert.convertToTaskVO(taskPage.getContent());
        Page<TaskVO> taskVOPage = new PageImpl<>(taskVOList, pageable, taskPage.getTotalElements());
        return taskVOPage.getContent();
    }

    @Override
    public Page<TaskVO> getHistory(Long userId, int page, int size) {
        List<Kinds> kindsList = kindsRepository.getKindsByUserId(userId);
        List<Long> kindsIdList = new ArrayList<>(kindsList.size());
        kindsList.forEach( kinds -> kindsIdList.add(kinds.getId()));

        Pageable pageable = PageRequest.of(page, size);
        Page<Task> taskPage = taskRepository.getByStatusAndKindIdIn(TaskEnum.FINISHED.getStatus(),
                kindsIdList, pageable);
        // 转换
        List<TaskVO> taskVOList = TaskConvert.convertToTaskVO(taskPage.getContent());
        Page<TaskVO> taskVOPage = new PageImpl<>(taskVOList, pageable, taskPage.getTotalElements());

        return taskVOPage;
    }

    @Override
    public Task update(TaskVO taskVO) {
        Task task = taskRepository.getById(taskVO.getId());
        if (task == null) {
            log.error("【更新任务】没有此任务: " + task.getContent());
            return null;
        }
        // 设置新值
        task.setStatus(taskVO.getStatus());
        task.setContent(taskVO.getContent());
        task.setExpectionTime(DateConverter.convert(taskVO.getExpectionTime()));
        task.setUpdateTime(task.getExpectionTime());
        // 更新
        Task update = taskRepository.save(task);
        log.info("【更新任务】" + task.getContent());
        return update;
    }


    @Override
    public Task delete(TaskVO taskVO) {
        Task task = taskRepository.getById(taskVO.getId());
        if (task == null) {
            log.error("【更新任务状态】没有此任务: " + task.getContent());
            return null;
        }
        // 设置状态
        task.setStatus(taskVO.getStatus());
        task.setUpdateTime(new Date());
        // 更新
        Task update = taskRepository.save(task);
        log.info("【更新任务状态】" + task.getContent());
        return update;
    }

    @Override
    public void finished(Long id) {
        Task task = taskRepository.getById(id);
        if (task == null) {
            log.warn("【完成任务】 没有该任务");
            return ;
        }
        task.setStatus(TaskEnum.FINISHED.getStatus());
        task.setFinishTime(new Date());
        task.setUpdateTime(task.getFinishTime());
        taskRepository.save(task);
    }

    @Override
    public void delete(Long id) {
        Task task = taskRepository.getById(id);
        if (task == null) {
            log.warn("【删除任务】 没有该任务");
            return ;
        }
        task.setStatus(TaskEnum.DELETED.getStatus());
        task.setUpdateTime(new Date());
        taskRepository.save(task);
    }

    @Override
    public List<Task> getByStatusIn(List<Integer> statusList) {
        List<Task> taskList = taskRepository.getByStatusIn(statusList);
        return taskList;
    }

    @Override
    public List<Task> getByKinds(List<Long> kindIdList) {
        List<Task> taskList = taskRepository.getByKindIdIn(kindIdList);
        return taskList;
    }
}
