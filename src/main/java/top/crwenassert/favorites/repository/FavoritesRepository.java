package top.crwenassert.favorites.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.crwenassert.favorites.domain.Favorites;

import java.util.List;

/**
 * ClassName: FavoritesRepository
 * Description:
 * date: 2020/7/27 0:00
 *
 * @author crwen
 * @create 2020-07-27-0:00
 * @since JDK 1.8
 */
public interface FavoritesRepository extends JpaRepository<Favorites, Long> {

    List<Favorites> getByUserId(Long userId);

    List<Favorites> getByParentId(Long parentId);

    Favorites getByUserIdAndName(Long useId, String name);

    Favorites getById(Long id);
}
