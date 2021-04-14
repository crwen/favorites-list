package top.crwenassert.favorites.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import top.crwenassert.favorites.domain.Task;

import java.util.List;

/**
 * ClassName: TaskRepository
 * Description:
 * date: 2020/8/31 19:00
 *
 * @author crwen
 * @create 2020-08-31-19:00
 * @since JDK 1.8
 */
public interface TaskRepository extends JpaRepository<Task, Long> {
    Page<Task> getByKindId(Long kindId, Pageable pageable);
    Page<Task> getByStatusAndKindIdIn(Integer status, List<Long> kindIds, Pageable pageable);
    Task getById(Long id);
    List<Task> getByStatusIn(List<Integer> statusList);
    List<Task> getByKindIdIn(List<Long> kindsIdList);
}
