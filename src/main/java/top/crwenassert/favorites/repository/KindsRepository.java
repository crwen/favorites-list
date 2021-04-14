package top.crwenassert.favorites.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import top.crwenassert.favorites.domain.Kinds;

import java.util.List;

/**
 * ClassName: KindsRepository
 * Description:
 * date: 2020/8/21 23:29
 *
 * @author crwen
 * @create 2020-08-21-23:29
 * @since JDK 1.8
 */
public interface KindsRepository extends JpaRepository<Kinds, Long> {
    Page<Kinds> getKindsByUserId(long userId,  Pageable pageable);
    List<Kinds> getKindsByUserId(long userId);
    List<Kinds> getByIdIn(List<Long> kindsIdList);
}
